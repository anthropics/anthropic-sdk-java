package com.anthropic.credentials

import com.anthropic.config.AuthenticationType
import com.anthropic.config.ConfigurationFileProvider
import com.anthropic.config.ProfileConfig
import com.anthropic.config.ProfileConfigProvider
import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.CachingAccessTokenProvider
import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.auth.FileIdentityTokenProvider
import com.anthropic.core.auth.IdentityTokenProvider
import com.anthropic.core.auth.InMemoryIdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.jsonMapper
import com.anthropic.errors.CredentialResolutionException
import com.anthropic.errors.CredentialSource
import com.anthropic.errors.CredentialSourceState
import com.anthropic.errors.NoCredentialsException
import com.anthropic.internal.config.CachedTokenData
import com.anthropic.internal.config.ConfigDir
import com.anthropic.internal.config.CredentialsCache
import com.anthropic.internal.credentials.UserOAuthCredentials
import com.anthropic.internal.credentials.WorkloadIdentityCredentials
import com.fasterxml.jackson.databind.json.JsonMapper
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.CompletableFuture

/**
 * Implements the SDK credential-resolution chain for OAuth and federation credentials.
 *
 * NOTE: Static credentials (ANTHROPIC_API_KEY, ANTHROPIC_AUTH_TOKEN) are NOT resolved here. They
 * are handled by AnthropicBackend via AnthropicOkHttpClient.builder().fromEnv(), which sends API
 * keys as `X-Api-Key` headers (not `Authorization: Bearer`).
 *
 * The resolver walks an ordered list of credential sources and returns the first one that yields a
 * usable credential. The overall shape is **ctor > env > auto/files**: explicit constructor
 * arguments beat environment variables, which beat anything auto-discovered on disk.
 *
 * The four steps, in order:
 * 1. Explicit constructor arguments (credentials / configurationProvider)
 * 2. Explicit profile selection (ANTHROPIC_PROFILE)
 * 3. Direct env-var federation (ANTHROPIC_FEDERATION_RULE_ID + ORGANIZATION_ID + token)
 * 4. Fallback active profile (active_config file, or literal "default")
 *
 * If a step matches but then fails (e.g. profile file missing, malformed JSON), the resolver does
 * NOT silently fall through to the next step — that would mask config mistakes. It records the
 * failure in `attemptedSources` and returns null only when the step legitimately doesn't apply.
 *
 * If no step yields a credential, `NoCredentialsException` is thrown with the full list of
 * attempted sources so the user can see why each one was skipped.
 */
internal class CredentialResolver
private constructor(
    private val credentials: CredentialResult?,
    private val configurationProvider: ProfileConfigProvider?,
    private val envProfile: String?,
    private val envFederationRuleId: String?,
    private val envOrganizationId: String?,
    private val envIdentityTokenFile: String?,
    private val envIdentityToken: String?,
    private val envServiceAccountId: String?,
    private val envWorkspaceId: String?,
    private val configDir: Path?,
    private val httpClient: HttpClient?,
    private val jsonMapper: JsonMapper,
) {

    /**
     * Walks the credential chain top to bottom and returns the first credential found. Each step
     * appends to `attemptedSources` so that, if we fall off the end, the resulting
     * [NoCredentialsException] explains exactly what was tried and why.
     */
    internal fun resolve(): CredentialResult {
        val attemptedSources = mutableListOf<CredentialSource>()

        step1ExplicitCredentials(attemptedSources)?.let {
            return it
        }
        step1bConfigurationProvider(attemptedSources)?.let {
            return it
        }
        step2ExplicitProfile(attemptedSources)?.let {
            return it
        }
        step3EnvFederation(attemptedSources)?.let {
            return it
        }
        step4FallbackProfile(attemptedSources)?.let {
            return it
        }

        throw NoCredentialsException(attemptedSources)
    }

    private fun step1ExplicitCredentials(
        sources: MutableList<CredentialSource>
    ): CredentialResult? {
        if (credentials != null) {
            return credentials
        }

        sources.add(CredentialSource("explicit credentials", CredentialSourceState.NOT_SET))
        return null
    }

    private fun step1bConfigurationProvider(
        sources: MutableList<CredentialSource>
    ): CredentialResult? {
        val provider = configurationProvider ?: return null
        return resolveFromConfigurationProvider(provider, sources, "configuration provider")
    }

    private fun resolveFromConfigurationProvider(
        provider: ProfileConfigProvider,
        sources: MutableList<CredentialSource>,
        sourceName: String,
        profileName: String? = null,
        cacheDir: Path? = null,
    ): CredentialResult? {
        val config =
            try {
                provider.get()
            } catch (e: Exception) {
                sources.add(
                    CredentialSource(sourceName, CredentialSourceState.LOAD_FAILED, e.message)
                )
                return null
            }
        return resolveFromConfig(config, sources, sourceName, profileName, cacheDir)
    }

    private fun resolveFromConfig(
        config: ProfileConfig,
        sources: MutableList<CredentialSource>,
        sourceName: String,
        profileName: String? = null,
        cacheDir: Path? = null,
    ): CredentialResult? {
        val auth = config.authentication().orElse(null)
        if (auth == null) {
            sources.add(
                CredentialSource(
                    sourceName,
                    CredentialSourceState.PARTIAL,
                    "no authentication config",
                )
            )
            return null
        }

        return when (auth.type().orElse(null)) {
            AuthenticationType.OIDC_FEDERATION -> {
                val client = httpClient
                if (client == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "HTTP client required",
                        )
                    )
                    return null
                }

                val federationRuleId = auth.federationRuleId().orElse(null)
                if (federationRuleId == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "federation_rule_id is required",
                        )
                    )
                    return null
                }

                val organizationId = config.organizationId().orElse(null)
                if (organizationId == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "organization_id is required",
                        )
                    )
                    return null
                }

                val identityTokenConfig = auth.identityToken().orElse(null)
                if (identityTokenConfig == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "identity_token is required",
                        )
                    )
                    return null
                }

                val identityTokenProvider =
                    try {
                        createIdentityTokenProvider(identityTokenConfig, sourceName)
                    } catch (e: Exception) {
                        sources.add(
                            CredentialSource(
                                sourceName,
                                CredentialSourceState.LOAD_FAILED,
                                e.message,
                            )
                        )
                        return null
                    }

                val workloadCredentials =
                    WorkloadIdentityCredentials(
                        identityTokenProvider,
                        federationRuleId,
                        organizationId,
                        auth.serviceAccountId().orElse(null),
                        config.workspaceId().orElse(null),
                        client,
                        jsonMapper,
                    )

                val tokenProvider: AccessTokenProvider =
                    if (profileName != null && cacheDir != null) {
                        val credentialsPath =
                            auth.credentialsPath().orElse(null)?.let { cacheDir.resolve(it) }
                                ?: cacheDir.resolve("credentials").resolve("$profileName.json")
                        val fileCache = CredentialsCache(credentialsPath, jsonMapper)
                        FileCachingCredentialProvider(workloadCredentials, fileCache)
                    } else {
                        workloadCredentials
                    }

                val cachingProvider = CachingAccessTokenProvider(tokenProvider)

                CredentialResult(
                    cachingProvider,
                    config.baseUrl().orElse(null),
                    // For federation profiles workspace_id is sent in the jwt-bearer exchange body,
                    // not as a request header (the minted token is already workspace-scoped, so the
                    // header would be ignored).
                    null,
                )
            }
            AuthenticationType.USER_OAUTH -> {
                val credentialsPathStr = auth.credentialsPath().orElse(null)
                val credentialsPath =
                    if (credentialsPathStr != null) {
                        val path = Paths.get(credentialsPathStr)
                        if (path.isAbsolute) {
                            path
                        } else if (cacheDir != null) {
                            cacheDir.resolve(path)
                        } else {
                            sources.add(
                                CredentialSource(
                                    sourceName,
                                    CredentialSourceState.PARTIAL,
                                    "credentials_path is relative but no config directory available",
                                )
                            )
                            return null
                        }
                    } else if (profileName != null && cacheDir != null) {
                        cacheDir.resolve("credentials").resolve("$profileName.json")
                    } else {
                        sources.add(
                            CredentialSource(
                                sourceName,
                                CredentialSourceState.PARTIAL,
                                "user_oauth requires credentials_path or profile context",
                            )
                        )
                        return null
                    }

                val effectiveProfileName = profileName ?: "custom"

                val credentialsCache = CredentialsCache(credentialsPath, jsonMapper)

                val cachedToken = credentialsCache.read()
                if (cachedToken == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "credentials file missing or has no access_token",
                        )
                    )
                    return null
                }

                val clientId = auth.clientId().orElse(null)
                if (clientId != null && httpClient == null) {
                    sources.add(
                        CredentialSource(
                            sourceName,
                            CredentialSourceState.PARTIAL,
                            "HTTP client required for user_oauth refresh",
                        )
                    )
                    return null
                }

                val tokenProvider =
                    UserOAuthCredentials(
                        credentialsCache,
                        effectiveProfileName,
                        clientId,
                        if (clientId != null) httpClient else null,
                        jsonMapper,
                    )
                val cachingProvider = CachingAccessTokenProvider(tokenProvider)

                CredentialResult(
                    cachingProvider,
                    config.baseUrl().orElse(null),
                    config.workspaceId().orElse(null),
                )
            }
            null -> {
                sources.add(
                    CredentialSource(
                        sourceName,
                        CredentialSourceState.PARTIAL,
                        "authentication type is required",
                    )
                )
                null
            }
        }
    }

    private fun step2ExplicitProfile(sources: MutableList<CredentialSource>): CredentialResult? {
        val profile = envProfile ?: return null

        val dir =
            configDir
                ?: throw CredentialResolutionException(
                    profile,
                    "ANTHROPIC_PROFILE=$profile is set, but no config directory could be located " +
                        "(checked ANTHROPIC_CONFIG_DIR, ~/.config/anthropic, %APPDATA%\\Anthropic)",
                )

        val configPath = dir.resolve("configs").resolve("$profile.json")
        if (!Files.exists(configPath)) {
            throw CredentialResolutionException(
                profile,
                "ANTHROPIC_PROFILE=$profile is set, but profile config file not found at $configPath",
            )
        }

        val fileProvider =
            ConfigurationFileProvider.builder()
                .profile(profile)
                .configDir(dir)
                .jsonMapper(jsonMapper)
                .envFederationRuleId(envFederationRuleId)
                .envOrganizationId(envOrganizationId)
                .envIdentityTokenFile(envIdentityTokenFile)
                .envIdentityToken(envIdentityToken)
                .envServiceAccountId(envServiceAccountId)
                .envWorkspaceId(envWorkspaceId)
                .build()

        val stepSources = mutableListOf<CredentialSource>()
        val result =
            resolveFromConfigurationProvider(
                fileProvider,
                stepSources,
                "ANTHROPIC_PROFILE ($profile)",
                profile,
                dir,
            )
        if (result != null) return result

        val reason = stepSources.lastOrNull()?.toString() ?: "unknown reason"
        throw CredentialResolutionException(
            profile,
            "ANTHROPIC_PROFILE=$profile is set, but profile could not be resolved: $reason",
        )
    }

    private fun step3EnvFederation(sources: MutableList<CredentialSource>): CredentialResult? {
        val federationRuleId = envFederationRuleId
        val organizationId = envOrganizationId

        if (federationRuleId == null || organizationId == null) {
            if (
                federationRuleId != null ||
                    organizationId != null ||
                    envIdentityTokenFile != null ||
                    envIdentityToken != null
            ) {
                sources.add(
                    CredentialSource(
                        "env federation",
                        CredentialSourceState.PARTIAL,
                        "requires ANTHROPIC_FEDERATION_RULE_ID, ANTHROPIC_ORGANIZATION_ID, and identity token",
                    )
                )
            }
            return null
        }

        val identityTokenProvider: IdentityTokenProvider =
            when {
                envIdentityTokenFile != null -> FileIdentityTokenProvider(envIdentityTokenFile)
                envIdentityToken != null -> InMemoryIdentityTokenProvider(envIdentityToken)
                else -> {
                    sources.add(
                        CredentialSource(
                            "env federation",
                            CredentialSourceState.PARTIAL,
                            "requires ANTHROPIC_IDENTITY_TOKEN_FILE or ANTHROPIC_IDENTITY_TOKEN",
                        )
                    )
                    return null
                }
            }

        val client = httpClient
        if (client == null) {
            sources.add(
                CredentialSource(
                    "env federation",
                    CredentialSourceState.PARTIAL,
                    "HTTP client required",
                )
            )
            return null
        }

        val workloadCredentials =
            WorkloadIdentityCredentials(
                identityTokenProvider,
                federationRuleId,
                organizationId,
                envServiceAccountId,
                envWorkspaceId,
                client,
                jsonMapper,
            )

        val cachingProvider = CachingAccessTokenProvider(workloadCredentials)
        return CredentialResult(cachingProvider)
    }

    private fun step4FallbackProfile(sources: MutableList<CredentialSource>): CredentialResult? {
        val dir = configDir
        if (dir == null) {
            sources.add(
                CredentialSource(
                    "default profile",
                    CredentialSourceState.NOT_FOUND,
                    "config directory not found",
                )
            )
            return null
        }

        val profileName = readActiveConfig(dir) ?: "default"

        val configPath = dir.resolve("configs").resolve("$profileName.json")
        if (!Files.exists(configPath)) {
            sources.add(
                CredentialSource(
                    "default profile ($profileName)",
                    CredentialSourceState.NOT_FOUND,
                    "profile config not found",
                )
            )
            return null
        }

        val fileProvider =
            ConfigurationFileProvider.builder()
                .profile(profileName)
                .configDir(dir)
                .jsonMapper(jsonMapper)
                .envFederationRuleId(envFederationRuleId)
                .envOrganizationId(envOrganizationId)
                .envIdentityTokenFile(envIdentityTokenFile)
                .envIdentityToken(envIdentityToken)
                .envServiceAccountId(envServiceAccountId)
                .envWorkspaceId(envWorkspaceId)
                .build()

        return resolveFromConfigurationProvider(
            fileProvider,
            sources,
            "default profile ($profileName)",
            profileName,
            dir,
        )
    }

    private fun readActiveConfig(dir: Path): String? {
        val activeConfigFile = dir.resolve("active_config")
        if (!Files.exists(activeConfigFile)) {
            return null
        }
        return try {
            String(Files.readAllBytes(activeConfigFile)).trim()
        } catch (e: Exception) {
            null
        }
    }

    private fun createIdentityTokenProvider(
        config: com.anthropic.config.IdentityTokenConfig,
        profileName: String,
    ): IdentityTokenProvider {
        val path =
            config.path().orElse(null)
                ?: throw CredentialResolutionException(
                    profileName,
                    "identity_token path is required",
                )

        return when (config.source().orElse(null)) {
            "file" -> FileIdentityTokenProvider(path)
            "value" -> InMemoryIdentityTokenProvider(path)
            else ->
                throw CredentialResolutionException(
                    profileName,
                    "Unknown identity_token source: ${config.source().orElse(null)}",
                )
        }
    }

    companion object {
        @JvmStatic fun builder(): Builder = Builder()

        @JvmStatic
        fun fromEnv(httpClient: HttpClient): CredentialResolver {
            return builder()
                .envProfile(System.getenv("ANTHROPIC_PROFILE"))
                .envFederationRuleId(System.getenv("ANTHROPIC_FEDERATION_RULE_ID"))
                .envOrganizationId(System.getenv("ANTHROPIC_ORGANIZATION_ID"))
                .envIdentityTokenFile(System.getenv("ANTHROPIC_IDENTITY_TOKEN_FILE"))
                .envIdentityToken(System.getenv("ANTHROPIC_IDENTITY_TOKEN"))
                .envServiceAccountId(System.getenv("ANTHROPIC_SERVICE_ACCOUNT_ID"))
                // Coerce empty string to null so a defaulted-but-empty CI variable doesn't put
                // "workspace_id": "" on the wire. The builder setter applies the same coercion so
                // resolvers built directly (e.g. in tests) behave identically.
                .envWorkspaceId(
                    System.getenv("ANTHROPIC_WORKSPACE_ID")?.takeUnless { it.isEmpty() }
                )
                .configDir(ConfigDir.resolve()?.let { Paths.get(it) })
                .httpClient(httpClient)
                .build()
        }
    }

    class Builder {
        private var credentials: CredentialResult? = null
        private var configurationProvider: ProfileConfigProvider? = null
        private var envProfile: String? = null
        private var envFederationRuleId: String? = null
        private var envOrganizationId: String? = null
        private var envIdentityTokenFile: String? = null
        private var envIdentityToken: String? = null
        private var envServiceAccountId: String? = null
        private var envWorkspaceId: String? = null
        private var configDir: Path? = null
        private var httpClient: HttpClient? = null
        private var jsonMapper: JsonMapper = jsonMapper()

        @JvmSynthetic
        internal fun credentials(credentials: CredentialResult?) = apply {
            this.credentials = credentials
        }

        fun configurationProvider(provider: ProfileConfigProvider?) = apply {
            this.configurationProvider = provider
        }

        fun envProfile(envProfile: String?) = apply { this.envProfile = envProfile }

        fun envFederationRuleId(envFederationRuleId: String?) = apply {
            this.envFederationRuleId = envFederationRuleId
        }

        fun envOrganizationId(envOrganizationId: String?) = apply {
            this.envOrganizationId = envOrganizationId
        }

        fun envIdentityTokenFile(envIdentityTokenFile: String?) = apply {
            this.envIdentityTokenFile = envIdentityTokenFile
        }

        fun envIdentityToken(envIdentityToken: String?) = apply {
            this.envIdentityToken = envIdentityToken
        }

        fun envServiceAccountId(envServiceAccountId: String?) = apply {
            this.envServiceAccountId = envServiceAccountId
        }

        fun envWorkspaceId(envWorkspaceId: String?) = apply {
            // Coerce empty string to null so a defaulted-but-empty CI variable doesn't put
            // "workspace_id": "" on the wire.
            this.envWorkspaceId = envWorkspaceId?.takeUnless { it.isEmpty() }
        }

        fun configDir(configDir: Path?) = apply { this.configDir = configDir }

        fun httpClient(httpClient: HttpClient?) = apply { this.httpClient = httpClient }

        fun jsonMapper(jsonMapper: JsonMapper) = apply { this.jsonMapper = jsonMapper }

        fun build(): CredentialResolver =
            CredentialResolver(
                credentials,
                configurationProvider,
                envProfile,
                envFederationRuleId,
                envOrganizationId,
                envIdentityTokenFile,
                envIdentityToken,
                envServiceAccountId,
                envWorkspaceId,
                configDir,
                httpClient,
                jsonMapper,
            )
    }

    /**
     * Persists exchanged access tokens to a per-profile JSON file so they survive across
     * short-lived process invocations.
     */
    private class FileCachingCredentialProvider(
        private val delegate: AccessTokenProvider,
        private val cache: CredentialsCache,
    ) : AccessTokenProvider {
        override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken {
            if (!forceRefresh) {
                cache.read()?.let { cached -> if (!cached.isExpired()) return cached }
            }
            val token = delegate.get(baseUrl, forceRefresh)
            cache.writeRecord(
                CachedTokenData(
                    type = "access_token",
                    accessToken = token.token,
                    expiresAt = token.expiresAt?.epochSecond,
                    refreshToken = null,
                )
            )
            return token
        }

        override fun getAsync(
            baseUrl: String,
            forceRefresh: Boolean,
        ): CompletableFuture<AccessToken> {
            if (!forceRefresh) {
                cache.read()?.let { cached ->
                    if (!cached.isExpired()) return CompletableFuture.completedFuture(cached)
                }
            }
            return delegate.getAsync(baseUrl, forceRefresh).thenApplyAsync { token ->
                cache.writeRecord(
                    CachedTokenData(
                        type = "access_token",
                        accessToken = token.token,
                        expiresAt = token.expiresAt?.epochSecond,
                        refreshToken = null,
                    )
                )
                token
            }
        }
    }
}
