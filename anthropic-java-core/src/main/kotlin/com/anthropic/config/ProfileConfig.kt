package com.anthropic.config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.json.JsonMapper
import java.nio.file.Files
import java.nio.file.Path
import java.util.Optional

enum class AuthenticationType {
    @JsonProperty("oidc_federation") OIDC_FEDERATION,
    @JsonProperty("user_oauth") USER_OAUTH,
}

@JsonIgnoreProperties(ignoreUnknown = true)
class IdentityTokenConfig
private constructor(
    @JsonProperty("source") private val source: String?,
    @JsonProperty("path") private val path: String?,
) {
    companion object {
        @JvmStatic fun builder(): Builder = Builder()
    }

    fun source(): Optional<String> = Optional.ofNullable(source)

    fun path(): Optional<String> = Optional.ofNullable(path)

    fun toBuilder(): Builder = Builder().from(this)

    class Builder internal constructor() {
        private var source: String? = null
        private var path: String? = null

        @JvmSynthetic
        internal fun from(config: IdentityTokenConfig) = apply {
            source = config.source
            path = config.path
        }

        fun source(source: String?) = apply { this.source = source }

        fun source(source: Optional<String>) = source(source.orElse(null))

        fun path(path: String?) = apply { this.path = path }

        fun path(path: Optional<String>) = path(path.orElse(null))

        fun build(): IdentityTokenConfig = IdentityTokenConfig(source, path)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AuthenticationConfig
private constructor(
    @JsonProperty("type") private val type: AuthenticationType?,
    @JsonProperty("federation_rule_id") private val federationRuleId: String?,
    @JsonProperty("service_account_id") private val serviceAccountId: String?,
    @JsonProperty("identity_token") private val identityToken: IdentityTokenConfig?,
    @JsonProperty("client_id") private val clientId: String?,
    @JsonProperty("scope") private val scope: String?,
    @JsonProperty("credentials_path") private val credentialsPath: String?,
) {
    companion object {
        @JvmStatic fun builder(): Builder = Builder()
    }

    fun type(): Optional<AuthenticationType> = Optional.ofNullable(type)

    fun federationRuleId(): Optional<String> = Optional.ofNullable(federationRuleId)

    fun serviceAccountId(): Optional<String> = Optional.ofNullable(serviceAccountId)

    fun identityToken(): Optional<IdentityTokenConfig> = Optional.ofNullable(identityToken)

    fun clientId(): Optional<String> = Optional.ofNullable(clientId)

    fun scope(): Optional<String> = Optional.ofNullable(scope)

    fun credentialsPath(): Optional<String> = Optional.ofNullable(credentialsPath)

    fun toBuilder(): Builder = Builder().from(this)

    class Builder internal constructor() {
        private var type: AuthenticationType? = null
        private var federationRuleId: String? = null
        private var serviceAccountId: String? = null
        private var identityToken: IdentityTokenConfig? = null
        private var clientId: String? = null
        private var scope: String? = null
        private var credentialsPath: String? = null

        @JvmSynthetic
        internal fun from(config: AuthenticationConfig) = apply {
            type = config.type
            federationRuleId = config.federationRuleId
            serviceAccountId = config.serviceAccountId
            identityToken = config.identityToken
            clientId = config.clientId
            scope = config.scope
            credentialsPath = config.credentialsPath
        }

        fun type(type: AuthenticationType?) = apply { this.type = type }

        fun type(type: Optional<AuthenticationType>) = type(type.orElse(null))

        fun federationRuleId(federationRuleId: String?) = apply {
            this.federationRuleId = federationRuleId
        }

        fun federationRuleId(federationRuleId: Optional<String>) =
            federationRuleId(federationRuleId.orElse(null))

        fun serviceAccountId(serviceAccountId: String?) = apply {
            this.serviceAccountId = serviceAccountId
        }

        fun serviceAccountId(serviceAccountId: Optional<String>) =
            serviceAccountId(serviceAccountId.orElse(null))

        fun identityToken(identityToken: IdentityTokenConfig?) = apply {
            this.identityToken = identityToken
        }

        fun identityToken(identityToken: Optional<IdentityTokenConfig>) =
            identityToken(identityToken.orElse(null))

        fun clientId(clientId: String?) = apply { this.clientId = clientId }

        fun clientId(clientId: Optional<String>) = clientId(clientId.orElse(null))

        fun scope(scope: String?) = apply { this.scope = scope }

        fun scope(scope: Optional<String>) = scope(scope.orElse(null))

        fun credentialsPath(credentialsPath: String?) = apply {
            this.credentialsPath = credentialsPath
        }

        fun credentialsPath(credentialsPath: Optional<String>) =
            credentialsPath(credentialsPath.orElse(null))

        fun build(): AuthenticationConfig =
            AuthenticationConfig(
                type,
                federationRuleId,
                serviceAccountId,
                identityToken,
                clientId,
                scope,
                credentialsPath,
            )
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ProfileConfig
private constructor(
    @JsonProperty("authentication") private val authentication: AuthenticationConfig?,
    @JsonProperty("base_url") private val baseUrl: String?,
    @JsonProperty("organization_id") private val organizationId: String?,
    @JsonProperty("workspace_id") private val workspaceId: String?,
) {
    companion object {
        @JvmStatic fun builder(): Builder = Builder()

        @JvmSynthetic
        internal fun parse(json: String, mapper: JsonMapper): ProfileConfig {
            return mapper.readValue(json, ProfileConfig::class.java)
        }

        @JvmSynthetic
        internal fun load(path: Path, mapper: JsonMapper): ProfileConfig {
            val json = String(Files.readAllBytes(path))
            return parse(json, mapper)
        }
    }

    fun authentication(): Optional<AuthenticationConfig> = Optional.ofNullable(authentication)

    fun baseUrl(): Optional<String> = Optional.ofNullable(baseUrl)

    fun organizationId(): Optional<String> = Optional.ofNullable(organizationId)

    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    fun toBuilder(): Builder = Builder().from(this)

    @JvmSynthetic
    internal fun fillMissingFromEnv(
        envFederationRuleId: String?,
        envOrganizationId: String?,
        envIdentityTokenFile: String?,
        envIdentityToken: String?,
        envServiceAccountId: String?,
        envWorkspaceId: String?,
    ): ProfileConfig {
        val auth = authentication ?: return this
        val authIdentityToken = auth.identityToken().orElse(null)
        val filledIdentityToken =
            if (
                authIdentityToken == null &&
                    (envIdentityTokenFile != null || envIdentityToken != null)
            ) {
                if (envIdentityTokenFile != null) {
                    IdentityTokenConfig.builder().source("file").path(envIdentityTokenFile).build()
                } else {
                    IdentityTokenConfig.builder().source("value").path(envIdentityToken).build()
                }
            } else {
                authIdentityToken
            }

        val filledAuth =
            auth
                .toBuilder()
                .federationRuleId(auth.federationRuleId().orElse(envFederationRuleId))
                .serviceAccountId(auth.serviceAccountId().orElse(envServiceAccountId))
                .identityToken(filledIdentityToken)
                .build()

        return toBuilder()
            .authentication(filledAuth)
            .organizationId(organizationId ?: envOrganizationId)
            .workspaceId(workspaceId ?: envWorkspaceId)
            .build()
    }

    class Builder internal constructor() {
        private var authentication: AuthenticationConfig? = null
        private var baseUrl: String? = null
        private var organizationId: String? = null
        private var workspaceId: String? = null

        @JvmSynthetic
        internal fun from(config: ProfileConfig) = apply {
            authentication = config.authentication
            baseUrl = config.baseUrl
            organizationId = config.organizationId
            workspaceId = config.workspaceId
        }

        fun authentication(authentication: AuthenticationConfig?) = apply {
            this.authentication = authentication
        }

        fun authentication(authentication: Optional<AuthenticationConfig>) =
            authentication(authentication.orElse(null))

        fun baseUrl(baseUrl: String?) = apply { this.baseUrl = baseUrl }

        fun baseUrl(baseUrl: Optional<String>) = baseUrl(baseUrl.orElse(null))

        fun organizationId(organizationId: String?) = apply { this.organizationId = organizationId }

        fun organizationId(organizationId: Optional<String>) =
            organizationId(organizationId.orElse(null))

        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.orElse(null))

        fun build(): ProfileConfig =
            ProfileConfig(authentication, baseUrl, organizationId, workspaceId)
    }
}
