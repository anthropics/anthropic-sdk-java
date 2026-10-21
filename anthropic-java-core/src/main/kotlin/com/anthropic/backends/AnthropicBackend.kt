package com.anthropic.backends

import com.anthropic.backends.AnthropicBackend.Builder
import com.anthropic.config.ProfileConfigProvider
import com.anthropic.core.ClientOptions
import com.anthropic.core.auth.CachingAccessTokenProvider
import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.auth.IdentityTokenProvider
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.jsonMapper
import com.anthropic.credentials.CredentialResolver
import com.anthropic.errors.NoCredentialsException
import com.anthropic.internal.core.http.withStaticAuthHeaders
import com.anthropic.internal.credentials.WorkloadIdentityCredentials
import com.fasterxml.jackson.databind.json.JsonMapper
import java.util.Optional
import java.util.concurrent.atomic.AtomicBoolean

/**
 * The Anthropic backend that manages the API key or authorization token credentials and base URL
 * required to access an Anthropic AI model on the Anthropic service.
 *
 * The [apiKey] or the [authToken] should be set (or resolved from the environment), but not both.
 * Either one is sent as `Authorization: Bearer`. The default value for the base URL is the normal
 * Anthropic API service base URL, so it only needs to be set if a different base URL is required.
 *
 * The credentials can be resolved from environment variables by calling [Builder.fromEnv].
 * Alternatively, they can be supplied directly to the builder.
 */
class AnthropicBackend
private constructor(
    private val credential: Credential,
    private val baseUrl: String,
    @get:JvmName("jsonMapper") val jsonMapper: JsonMapper,
    /**
     * Whether [baseUrl] was set explicitly or from the environment rather than defaulted. A
     * profile's `base_url` applies only when it was not: explicit > environment > profile.
     */
    private val baseUrlOverridden: Boolean,
) : Backend {

    fun apiKey(): Optional<String> = Optional.ofNullable((credential as? Credential.ApiKey)?.value)

    fun authToken(): Optional<String> =
        Optional.ofNullable((credential as? Credential.AuthToken)?.value)

    private sealed class Credential {

        /** A credential sent as it is, as `Authorization: Bearer`. */
        sealed class Static(val value: String) : Credential()

        class ApiKey(value: String) : Static(value)

        class AuthToken(value: String) : Static(value)

        class Federation(
            val identityTokenProvider: IdentityTokenProvider,
            val federationRuleId: String,
            val organizationId: String,
            val serviceAccountId: String?,
            val workspaceId: String?,
        ) : Credential()

        class Profile(val provider: ProfileConfigProvider) : Credential()

        /**
         * [Builder.fromEnv] found no API key and no auth token, so the rest of the credential
         * resolution chain runs (profile files, workload identity federation).
         */
        object EnvChain : Credential()

        /** Nothing was set and [Builder.fromEnv] was not called, so nothing is resolved. */
        object None : Credential()
    }

    fun applyCredentials(httpClient: HttpClient, clientOptionsBuilder: ClientOptions.Builder) {
        if (credential is Credential.Static) {
            // Static credentials are applied by a client-level auth layer that wraps the user's
            // interceptors, so interceptors observe the logical 1p credentials.
            clientOptionsBuilder.credentials(CredentialResult.Static(credential.value))
            return
        }

        resolveCredentials(httpClient)?.let { result ->
            clientOptionsBuilder.credentials(result)
            if (!baseUrlOverridden) {
                result.baseUrl?.let { clientOptionsBuilder.baseUrl(it) }
            }
        }
    }

    @JvmSynthetic
    internal fun resolveCredentials(httpClient: HttpClient): CredentialResult.Token? =
        when (credential) {
            is Credential.Static,
            Credential.None -> null
            is Credential.Federation ->
                CredentialResult.Token(
                    CachingAccessTokenProvider(
                        WorkloadIdentityCredentials(
                            credential.identityTokenProvider,
                            credential.federationRuleId,
                            credential.organizationId,
                            credential.serviceAccountId,
                            credential.workspaceId,
                            httpClient,
                            jsonMapper,
                        )
                    )
                )
            is Credential.Profile ->
                CredentialResolver.builder()
                    .configurationProvider(credential.provider)
                    .httpClient(httpClient)
                    .build()
                    .resolveOrNull()
            Credential.EnvChain -> CredentialResolver.fromEnv(httpClient).resolveOrNull()
        }

    private fun CredentialResolver.resolveOrNull(): CredentialResult.Token? =
        try {
            resolve()
        } catch (e: NoCredentialsException) {
            null
        }

    companion object {
        private const val PRODUCTION_URL = "https://api.anthropic.com"
        private const val ENV_API_KEY = "ANTHROPIC_API_KEY"
        private const val ENV_AUTH_TOKEN = "ANTHROPIC_AUTH_TOKEN"

        @get:JvmSynthetic internal val warnedBothEnvCredentials = AtomicBoolean()

        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv(): AnthropicBackend = builder().fromEnv().build()
    }

    override fun baseUrl(): String = baseUrl

    /**
     * Adds the static credential header if no auth headers are present.
     *
     * When the client is built via `AnthropicOkHttpClient`/`AnthropicOkHttpClientAsync`, the
     * credentials are already applied by a client-level auth layer that wraps the user's
     * interceptors (see [applyCredentials]), making this a no-op. It remains as a fallback for
     * hand-assembled clients where that layer was never installed.
     */
    override fun authorizeRequest(request: HttpRequest): HttpRequest =
        if (credential is Credential.Static) request.withStaticAuthHeaders(credential.value)
        else request

    override fun close() {}

    /**
     * A builder for a [AnthropicBackend] used to connect an Anthropic client to an Anthropic
     * backend service.
     *
     * The authorization credentials can be extracted from the environment and set on the builder by
     * calling [fromEnv] before calling [build] to create the [AnthropicBackend]. Alternatively, set
     * one credential explicitly via [apiKey], [authToken] or [configurationProvider] before calling
     * [build]. A credential set explicitly is the only one used: nothing is taken from the
     * environment, whichever kind it is.
     */
    class Builder internal constructor() {
        private var apiKey: String? = null
        private var authToken: String? = null
        private var federation: Credential.Federation? = null
        private var configurationProvider: ProfileConfigProvider? = null
        /** What [fromEnv] found, or `null` if it was not called. */
        private var env: EnvCredentials? = null
        private var baseUrl: String? = null
        private var jsonMapper: JsonMapper? = null

        fun fromEnv() = apply {
            (System.getProperty("anthropic.baseUrl") ?: getEnv("ANTHROPIC_BASE_URL"))?.let {
                baseUrl = it
            }
            env =
                EnvCredentials(
                    credentialFromEnv("anthropic.apiKey", ENV_API_KEY),
                    credentialFromEnv("anthropic.authToken", ENV_AUTH_TOKEN),
                )
        }

        private fun credentialFromEnv(property: String, variable: String): String? =
            System.getProperty(property)?.takeUnless { it.isEmpty() }
                ?: getEnv(variable)?.takeUnless { it.isEmpty() }

        @JvmSynthetic internal fun getEnv(name: String): String? = System.getenv(name)

        /** Passing `null` un-sets the API key, including one that an earlier [fromEnv] found. */
        fun apiKey(apiKey: String?) = apply {
            this.apiKey = apiKey
            env = env?.let { EnvCredentials(null, it.authToken) }
        }

        fun apiKey(apiKey: Optional<String>) = apiKey(apiKey.orElse(null))

        /** Passing `null` un-sets the auth token, including one that an earlier [fromEnv] found. */
        fun authToken(authToken: String?) = apply {
            this.authToken = authToken
            env = env?.let { EnvCredentials(it.apiKey, null) }
        }

        fun authToken(authToken: Optional<String>) = authToken(authToken.orElse(null))

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        fun jsonMapper(jsonMapper: JsonMapper?) = apply { this.jsonMapper = jsonMapper }

        @JvmSynthetic
        internal fun federationTokenProvider(
            identityTokenProvider: IdentityTokenProvider,
            federationRuleId: String,
            organizationId: String,
        ) = federationTokenProvider(identityTokenProvider, federationRuleId, organizationId, null)

        @JvmSynthetic
        internal fun federationTokenProvider(
            identityTokenProvider: IdentityTokenProvider,
            federationRuleId: String,
            organizationId: String,
            serviceAccountId: String?,
        ) =
            federationTokenProvider(
                identityTokenProvider,
                federationRuleId,
                organizationId,
                serviceAccountId,
                null,
            )

        @JvmSynthetic
        internal fun federationTokenProvider(
            identityTokenProvider: IdentityTokenProvider,
            federationRuleId: String,
            organizationId: String,
            serviceAccountId: String?,
            workspaceId: String?,
        ) = apply {
            federation =
                Credential.Federation(
                    identityTokenProvider,
                    federationRuleId,
                    organizationId,
                    serviceAccountId,
                    workspaceId,
                )
        }

        fun configurationProvider(provider: ProfileConfigProvider) = apply {
            configurationProvider = provider
        }

        /**
         * @throws IllegalStateException If more than one of [apiKey], [authToken] and
         *   [configurationProvider] is set.
         */
        fun build(): AnthropicBackend {
            val passed =
                listOfNotNull(
                    apiKey?.let { "apiKey" to Credential.ApiKey(it) },
                    authToken?.let { "authToken" to Credential.AuthToken(it) },
                    configurationProvider?.let {
                        "configurationProvider" to Credential.Profile(it)
                    },
                    federation?.let { "federationTokenProvider" to it },
                )
            check(passed.size <= 1) {
                "Pass at most one of `apiKey`, `authToken`, `configurationProvider`. " +
                    "Received ${passed.joinToString { (name, _) -> "`$name`" }}."
            }

            return AnthropicBackend(
                passed.singleOrNull()?.second ?: env?.toCredential() ?: Credential.None,
                baseUrl ?: PRODUCTION_URL,
                jsonMapper ?: jsonMapper(),
                baseUrl != null,
            )
        }

        private class EnvCredentials(val apiKey: String?, val authToken: String?) {

            fun toCredential(): Credential {
                if (
                    apiKey != null && authToken != null && !warnedBothEnvCredentials.getAndSet(true)
                ) {
                    System.err.println(
                        "Anthropic: $ENV_API_KEY and $ENV_AUTH_TOKEN are both set. " +
                            "Using $ENV_API_KEY."
                    )
                }

                return when {
                    apiKey != null -> Credential.ApiKey(apiKey)
                    authToken != null -> Credential.AuthToken(authToken)
                    else -> Credential.EnvChain
                }
            }
        }
    }
}
