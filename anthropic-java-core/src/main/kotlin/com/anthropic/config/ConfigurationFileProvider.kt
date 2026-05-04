package com.anthropic.config

import com.anthropic.errors.CredentialResolutionException
import com.fasterxml.jackson.databind.json.JsonMapper
import java.nio.file.NoSuchFileException
import java.nio.file.Path

internal class ConfigurationFileProvider
private constructor(
    private val profile: String,
    private val configDir: Path,
    private val jsonMapper: JsonMapper,
    private val envFederationRuleId: String? = null,
    private val envOrganizationId: String? = null,
    private val envIdentityTokenFile: String? = null,
    private val envIdentityToken: String? = null,
    private val envServiceAccountId: String? = null,
) : ProfileConfigProvider {

    init {
        require(!profile.contains('/') && !profile.contains('\\') && !profile.contains("..")) {
            "Profile name must not contain path separators or parent directory references: $profile"
        }
    }

    private var cachedConfig: ProfileConfig? = null

    @Synchronized
    override fun get(): ProfileConfig {
        cachedConfig?.let {
            return it
        }

        val configPath = configDir.resolve("configs").resolve("$profile.json")

        val rawConfig =
            try {
                ProfileConfig.load(configPath, jsonMapper)
            } catch (e: NoSuchFileException) {
                throw CredentialResolutionException(
                    profile,
                    "Config file not found at $configPath",
                    e,
                )
            } catch (e: Exception) {
                throw CredentialResolutionException(profile, "Failed to parse profile config", e)
            }

        val config =
            rawConfig.fillMissingFromEnv(
                envFederationRuleId,
                envOrganizationId,
                envIdentityTokenFile,
                envIdentityToken,
                envServiceAccountId,
            )

        cachedConfig = config
        return config
    }

    @Synchronized
    fun invalidate() {
        cachedConfig = null
    }

    companion object {
        @JvmStatic fun builder(): Builder = Builder()
    }

    class Builder internal constructor() {
        private var profile: String? = null
        private var configDir: Path? = null
        private var jsonMapper: JsonMapper? = null
        private var envFederationRuleId: String? = null
        private var envOrganizationId: String? = null
        private var envIdentityTokenFile: String? = null
        private var envIdentityToken: String? = null
        private var envServiceAccountId: String? = null

        fun profile(profile: String) = apply { this.profile = profile }

        fun configDir(configDir: Path) = apply { this.configDir = configDir }

        fun jsonMapper(jsonMapper: JsonMapper) = apply { this.jsonMapper = jsonMapper }

        fun envFederationRuleId(value: String?) = apply { this.envFederationRuleId = value }

        fun envOrganizationId(value: String?) = apply { this.envOrganizationId = value }

        fun envIdentityTokenFile(value: String?) = apply { this.envIdentityTokenFile = value }

        fun envIdentityToken(value: String?) = apply { this.envIdentityToken = value }

        fun envServiceAccountId(value: String?) = apply { this.envServiceAccountId = value }

        fun fromEnv() = apply {
            envFederationRuleId(System.getenv("ANTHROPIC_FEDERATION_RULE_ID"))
            envOrganizationId(System.getenv("ANTHROPIC_ORGANIZATION_ID"))
            envIdentityTokenFile(System.getenv("ANTHROPIC_IDENTITY_TOKEN_FILE"))
            envIdentityToken(System.getenv("ANTHROPIC_IDENTITY_TOKEN"))
            envServiceAccountId(System.getenv("ANTHROPIC_SERVICE_ACCOUNT_ID"))
        }

        fun build(): ConfigurationFileProvider {
            return ConfigurationFileProvider(
                requireNotNull(profile),
                requireNotNull(configDir),
                requireNotNull(jsonMapper),
                envFederationRuleId,
                envOrganizationId,
                envIdentityTokenFile,
                envIdentityToken,
                envServiceAccountId,
            )
        }
    }
}
