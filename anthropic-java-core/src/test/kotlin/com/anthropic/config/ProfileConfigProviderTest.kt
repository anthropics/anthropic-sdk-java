package com.anthropic.config

import com.anthropic.core.jsonMapper
import com.anthropic.errors.CredentialResolutionException
import java.nio.file.Files
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class ProfileConfigProviderTest {

    @Test
    fun profileConfigProviderInterfaceExists() {
        val provider: ProfileConfigProvider =
            object : ProfileConfigProvider {
                override fun get(): ProfileConfig = ProfileConfig.builder().build()
            }

        assertThat(provider.get()).isNotNull()
    }

    @Test
    fun inMemoryProfileConfigProviderReturnsProvidedConfig() {
        val config =
            ProfileConfig.builder()
                .baseUrl("https://custom.api.com")
                .organizationId("org_123")
                .build()

        val provider = InMemoryProfileConfigProvider.of(config)

        assertThat(provider.get()).isSameAs(config)
        assertThat(provider.get().baseUrl()).contains("https://custom.api.com")
        assertThat(provider.get().organizationId()).contains("org_123")
    }

    @Test
    fun inMemoryProfileConfigProviderWithAuthentication() {
        val config =
            ProfileConfig.builder()
                .baseUrl("https://custom.api.com")
                .organizationId("org_123")
                .authentication(
                    AuthenticationConfig.builder()
                        .type(AuthenticationType.OIDC_FEDERATION)
                        .federationRuleId("fdrl_123")
                        .identityToken(
                            IdentityTokenConfig.builder().source("value").path("jwt-token").build()
                        )
                        .build()
                )
                .build()

        val provider = InMemoryProfileConfigProvider.of(config)

        val resolved = provider.get()
        assertThat(resolved.baseUrl()).contains("https://custom.api.com")
        assertThat(resolved.organizationId()).contains("org_123")
        val auth = resolved.authentication().get()
        assertThat(auth.type()).contains(AuthenticationType.OIDC_FEDERATION)
        assertThat(auth.federationRuleId()).contains("fdrl_123")
    }

    @Test
    fun configurationFileProviderLoadsFromDisk(@TempDir tempDir: Path) {
        val configsDir = tempDir.resolve("configs")
        Files.createDirectories(configsDir)

        val configFile = configsDir.resolve("test-profile.json")
        Files.write(
            configFile,
            """
            {
                "base_url": "https://custom.api.com",
                "organization_id": "org_123",
                "authentication": {
                    "type": "oidc_federation",
                    "federation_rule_id": "fdrl_456",
                    "identity_token": {
                        "source": "file",
                        "path": "/path/to/token"
                    }
                }
            }
            """
                .trimIndent()
                .toByteArray(),
        )

        val provider =
            ConfigurationFileProvider.builder()
                .profile("test-profile")
                .configDir(tempDir)
                .jsonMapper(jsonMapper())
                .build()

        val config = provider.get()
        assertThat(config.baseUrl()).contains("https://custom.api.com")
        assertThat(config.organizationId()).contains("org_123")
        val auth = config.authentication().get()
        assertThat(auth.type()).contains(AuthenticationType.OIDC_FEDERATION)
        assertThat(auth.federationRuleId()).contains("fdrl_456")
    }

    @Test
    fun configurationFileProviderFillsMissingFromEnv(@TempDir tempDir: Path) {
        val configsDir = tempDir.resolve("configs")
        Files.createDirectories(configsDir)

        val configFile = configsDir.resolve("test-profile.json")
        Files.write(
            configFile,
            """
            {
                "organization_id": "org_123",
                "authentication": {
                    "type": "oidc_federation"
                }
            }
            """
                .trimIndent()
                .toByteArray(),
        )

        val provider =
            ConfigurationFileProvider.builder()
                .profile("test-profile")
                .configDir(tempDir)
                .jsonMapper(jsonMapper())
                .envFederationRuleId("fdrl_env")
                .envIdentityTokenFile("/env/path/to/token")
                .build()

        val config = provider.get()
        val auth = config.authentication().get()
        assertThat(auth.federationRuleId()).contains("fdrl_env")
        val identityToken = auth.identityToken().get()
        assertThat(identityToken.source()).contains("file")
        assertThat(identityToken.path()).contains("/env/path/to/token")
    }

    @Test
    fun configurationFileProviderThrowsWhenNotFound(@TempDir tempDir: Path) {
        val provider =
            ConfigurationFileProvider.builder()
                .profile("nonexistent")
                .configDir(tempDir)
                .jsonMapper(jsonMapper())
                .build()

        assertThatThrownBy { provider.get() }
            .isInstanceOf(CredentialResolutionException::class.java)
            .hasMessageContaining("not found")
    }

    @Test
    fun configurationFileProviderRejectsPathTraversalWithForwardSlash(@TempDir tempDir: Path) {
        assertThatThrownBy {
                ConfigurationFileProvider.builder()
                    .profile("../../../etc/passwd")
                    .configDir(tempDir)
                    .jsonMapper(jsonMapper())
                    .build()
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Profile name must not contain path separators")
    }

    @Test
    fun configurationFileProviderRejectsPathTraversalWithBackslash(@TempDir tempDir: Path) {
        assertThatThrownBy {
                ConfigurationFileProvider.builder()
                    .profile("..\\..\\windows\\system32")
                    .configDir(tempDir)
                    .jsonMapper(jsonMapper())
                    .build()
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Profile name must not contain path separators")
    }

    @Test
    fun configurationFileProviderRejectsParentDirectoryReference(@TempDir tempDir: Path) {
        assertThatThrownBy {
                ConfigurationFileProvider.builder()
                    .profile("profile..name")
                    .configDir(tempDir)
                    .jsonMapper(jsonMapper())
                    .build()
            }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining(
                "Profile name must not contain path separators or parent directory references"
            )
    }
}
