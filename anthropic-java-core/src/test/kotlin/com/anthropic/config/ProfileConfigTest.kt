package com.anthropic.config

import com.anthropic.core.jsonMapper
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class ProfileConfigTest {

    @Test
    fun parseOidcFederationConfig() {
        val json =
            """
            {
                "authentication": {
                    "type": "oidc_federation",
                    "federation_rule_id": "fdrl_123",
                    "identity_token": {
                        "source": "file",
                        "path": "/var/run/secrets/token"
                    }
                },
                "organization_id": "org_123",
                "base_url": "https://api.anthropic.com"
            }
            """
                .trimIndent()

        val config = ProfileConfig.parse(json, jsonMapper())

        val auth = config.authentication().get()
        assertThat(auth.type()).contains(AuthenticationType.OIDC_FEDERATION)
        assertThat(auth.federationRuleId()).contains("fdrl_123")
        val identityToken = auth.identityToken().get()
        assertThat(identityToken.source()).contains("file")
        assertThat(identityToken.path()).contains("/var/run/secrets/token")
        assertThat(config.organizationId()).contains("org_123")
        assertThat(config.baseUrl()).contains("https://api.anthropic.com")
    }

    @Test
    fun parseUserOAuthConfig() {
        val json =
            """
            {
                "authentication": {
                    "type": "user_oauth",
                    "client_id": "client_abc"
                }
            }
            """
                .trimIndent()

        val config = ProfileConfig.parse(json, jsonMapper())

        val auth = config.authentication().get()
        assertThat(auth.type()).contains(AuthenticationType.USER_OAUTH)
        assertThat(auth.clientId()).contains("client_abc")
    }

    @Test
    fun loadFromFile(@TempDir tempDir: Path) {
        val configFile = tempDir.resolve("test.json")
        configFile
            .toFile()
            .writeText(
                """
                {
                    "authentication": {
                        "type": "oidc_federation",
                        "federation_rule_id": "fdrl_456"
                    },
                    "organization_id": "org_456"
                }
                """
                    .trimIndent()
            )

        val config = ProfileConfig.load(configFile, jsonMapper())

        assertThat(config.authentication().get().federationRuleId()).contains("fdrl_456")
        assertThat(config.organizationId()).contains("org_456")
    }

    @Test
    fun fillMissingFromEnv() {
        val config =
            ProfileConfig.builder()
                .authentication(
                    AuthenticationConfig.builder().type(AuthenticationType.OIDC_FEDERATION).build()
                )
                .build()

        val filled =
            config.fillMissingFromEnv(
                envFederationRuleId = "fdrl_env",
                envOrganizationId = "org_env",
                envIdentityTokenFile = "/env/token",
                envIdentityToken = null,
                envServiceAccountId = "svac_env",
            )

        val auth = filled.authentication().get()
        assertThat(auth.federationRuleId()).contains("fdrl_env")
        assertThat(filled.organizationId()).contains("org_env")
        assertThat(auth.identityToken().get().path()).contains("/env/token")
    }

    @Test
    fun fillMissingDoesNotOverridePresent() {
        val config =
            ProfileConfig.builder()
                .authentication(
                    AuthenticationConfig.builder()
                        .type(AuthenticationType.OIDC_FEDERATION)
                        .federationRuleId("fdrl_config")
                        .identityToken(
                            IdentityTokenConfig.builder()
                                .source("file")
                                .path("/config/token")
                                .build()
                        )
                        .build()
                )
                .organizationId("org_config")
                .build()

        val filled =
            config.fillMissingFromEnv(
                envFederationRuleId = "fdrl_env",
                envOrganizationId = "org_env",
                envIdentityTokenFile = "/env/token",
                envIdentityToken = null,
                envServiceAccountId = null,
            )

        val auth = filled.authentication().get()
        assertThat(auth.federationRuleId()).contains("fdrl_config")
        assertThat(filled.organizationId()).contains("org_config")
        assertThat(auth.identityToken().get().path()).contains("/config/token")
    }
}
