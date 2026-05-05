package com.anthropic.credentials

import com.anthropic.config.AuthenticationConfig
import com.anthropic.config.AuthenticationType
import com.anthropic.config.IdentityTokenConfig
import com.anthropic.config.ProfileConfig
import com.anthropic.config.ProfileConfigProvider
import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.AccessToken
import com.anthropic.core.auth.AccessTokenProvider
import com.anthropic.core.auth.CredentialResult
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.jsonMapper
import com.anthropic.errors.NoCredentialsException
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.file.Files
import java.nio.file.Path
import java.time.Instant
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir

internal class CredentialResolverTest {

    @Test
    fun step1ExplicitCredentialsWins(@TempDir tempDir: Path) {
        val mockProvider =
            object : AccessTokenProvider {
                override fun get(baseUrl: String, forceRefresh: Boolean): AccessToken =
                    AccessToken("explicit-cred-token")

                override fun getAsync(
                    baseUrl: String,
                    forceRefresh: Boolean,
                ): CompletableFuture<AccessToken> =
                    CompletableFuture.completedFuture(AccessToken("explicit-cred-token"))
            }
        val explicitCreds = CredentialResult(mockProvider, "https://custom.url", "wrk_123")

        val resolver = CredentialResolver.builder().credentials(explicitCreds).build()

        val result = resolver.resolve()

        assertThat(result).isSameAs(explicitCreds)
        assertThat(result.baseUrl).isEqualTo("https://custom.url")
        assertThat(result.workspaceId).isEqualTo("wrk_123")
    }

    @Test
    fun resolveWithExplicitConfigurationProvider(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val config =
            ProfileConfig.builder()
                .authentication(
                    AuthenticationConfig.builder()
                        .type(AuthenticationType.OIDC_FEDERATION)
                        .federationRuleId("fdrl_config_provider")
                        .identityToken(
                            IdentityTokenConfig.builder()
                                .source("file")
                                .path(tokenFile.toAbsolutePath().toString())
                                .build()
                        )
                        .build()
                )
                .organizationId("org_config_provider")
                .baseUrl("https://custom.config.provider")
                .workspaceId("wrk_config_provider")
                .build()

        val configProvider =
            object : ProfileConfigProvider {
                override fun get(): ProfileConfig = config
            }

        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(200, """{"access_token": "config-provider-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .configurationProvider(configProvider)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        assertThat(result.baseUrl).isEqualTo("https://custom.config.provider")
        // For oidc_federation, workspace_id goes in the jwt-bearer exchange body, not the
        // anthropic-workspace-id header, so CredentialResult.workspaceId must be null.
        assertThat(result.workspaceId).isNull()
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("config-provider-token")
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrk_config_provider")
    }

    @Test
    fun explicitConfigurationProviderBeatsEnvVars(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val config =
            ProfileConfig.builder()
                .authentication(
                    AuthenticationConfig.builder()
                        .type(AuthenticationType.OIDC_FEDERATION)
                        .federationRuleId("fdrl_config_provider")
                        .identityToken(
                            IdentityTokenConfig.builder()
                                .source("file")
                                .path(tokenFile.toAbsolutePath().toString())
                                .build()
                        )
                        .build()
                )
                .organizationId("org_config_provider")
                .build()

        val configProvider =
            object : ProfileConfigProvider {
                override fun get(): ProfileConfig = config
            }

        val mockClient = MockHttpClient { _ ->
            createResponse(
                200,
                """{"access_token": "config-provider-beats-env", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .configurationProvider(configProvider)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("config-provider-beats-env")
    }

    @Test
    fun step2ExplicitProfileWins(@TempDir tempDir: Path) {
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("explicit-profile.json")
        profileConfig
            .toFile()
            .writeText(
                """
        {
            "authentication": {
                "type": "oidc_federation",
                "federation_rule_id": "fdrl_explicit",
                "identity_token": {
                    "source": "file",
                    "path": "${tokenFile.toAbsolutePath()}"
                }
            },
            "organization_id": "org_explicit",
            "workspace_id": "wrkspc_explicit"
        }
        """
                    .trimIndent()
            )

        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(
                200,
                """{"access_token": "explicit-profile-token", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("explicit-profile")
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("explicit-profile-token")
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrkspc_explicit")
    }

    @Test
    fun step3EnvFederationWins(@TempDir tempDir: Path) {
        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val mockClient = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "env-federation-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .envFederationRuleId("fdrl_env")
                .envOrganizationId("org_env")
                .envIdentityTokenFile(tokenFile.toAbsolutePath().toString())
                .configDir(tempDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("env-federation-token")
    }

    @Test
    fun step3EnvFederationWithIdentityTokenValue(@TempDir tempDir: Path) {
        val mockClient = MockHttpClient { _ ->
            createResponse(
                200,
                """{"access_token": "env-federation-value-token", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envFederationRuleId("fdrl_env")
                .envOrganizationId("org_env")
                .envIdentityToken("inline-identity-token")
                .configDir(tempDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("env-federation-value-token")
    }

    @Test
    fun step3EnvFederationPassesWorkspaceId(@TempDir tempDir: Path) {
        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(
                200,
                """{"access_token": "env-federation-workspace-token", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envFederationRuleId("fdrl_env")
                .envOrganizationId("org_env")
                .envIdentityToken("inline-identity-token")
                .envWorkspaceId("wrkspc_01abc")
                .configDir(tempDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        result.provider.get("https://api.anthropic.com", false)
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrkspc_01abc")
    }

    @Test
    fun step3EnvFederationCoercesEmptyWorkspaceIdToUnset(@TempDir tempDir: Path) {
        // ANTHROPIC_WORKSPACE_ID="" (a defaulted-but-empty CI variable) is treated as unset —
        // never put "workspace_id": "" on the wire.
        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(
                200,
                """{"access_token": "env-federation-empty-workspace", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envFederationRuleId("fdrl_env")
                .envOrganizationId("org_env")
                .envIdentityToken("inline-identity-token")
                .envWorkspaceId("")
                .configDir(tempDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        assertThat(result.workspaceId).isNull()
        result.provider.get("https://api.anthropic.com", false)
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!).doesNotContainKey("workspace_id")
    }

    @Test
    fun step4FallbackProfileWins(@TempDir tempDir: Path) {
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("default.json")
        profileConfig
            .toFile()
            .writeText(
                """
        {
            "authentication": {
                "type": "oidc_federation",
                "federation_rule_id": "fdrl_default",
                "identity_token": {
                    "source": "file",
                    "path": "${tokenFile.toAbsolutePath()}"
                }
            },
            "organization_id": "org_default"
        }
        """
                    .trimIndent()
            )

        val mockClient = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "default-profile-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("default-profile-token")
    }

    @Test
    fun step4FallbackProfileUsesActiveConfig(@TempDir tempDir: Path) {
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val activeConfigFile = configDir.resolve("active_config")
        activeConfigFile.toFile().writeText("my-active-profile")

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("my-active-profile.json")
        profileConfig
            .toFile()
            .writeText(
                """
        {
            "authentication": {
                "type": "oidc_federation",
                "federation_rule_id": "fdrl_active",
                "identity_token": {
                    "source": "file",
                    "path": "${tokenFile.toAbsolutePath()}"
                }
            },
            "organization_id": "org_active"
        }
        """
                    .trimIndent()
            )

        val mockClient = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "active-profile-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("active-profile-token")
    }

    @Test
    fun throwsNoCredentialsWhenNothingMatches(@TempDir tempDir: Path) {
        val resolver = CredentialResolver.builder().configDir(tempDir).build()

        assertThatThrownBy { resolver.resolve() }
            .isInstanceOf(NoCredentialsException::class.java)
            .hasMessageContaining("No credentials found")
    }

    @Test
    fun envVarsFillMissingInProfile(@TempDir tempDir: Path) {
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("partial-profile.json")
        profileConfig
            .toFile()
            .writeText(
                """
                {
                    "authentication": {
                        "type": "oidc_federation"
                    }
                }
                """
                    .trimIndent()
            )

        val mockClient = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "filled-profile-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("partial-profile")
                .envFederationRuleId("fdrl_env")
                .envOrganizationId("org_env")
                .envIdentityTokenFile(tokenFile.toAbsolutePath().toString())
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("filled-profile-token")
    }

    @Test
    fun envWorkspaceIdFillsMissingInProfile(@TempDir tempDir: Path) {
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("no-workspace-profile.json")
        profileConfig
            .toFile()
            .writeText(
                """
                {
                    "authentication": {
                        "type": "oidc_federation",
                        "federation_rule_id": "fdrl_file",
                        "identity_token": {
                            "source": "file",
                            "path": "${tokenFile.toAbsolutePath()}"
                        }
                    },
                    "organization_id": "org_file"
                }
                """
                    .trimIndent()
            )

        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(
                200,
                """{"access_token": "filled-workspace-token", "expires_in": 3600}""",
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("no-workspace-profile")
                .envWorkspaceId("wrkspc_from_env")
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        // For oidc_federation, workspace_id goes in the jwt-bearer exchange body, not the
        // anthropic-workspace-id header, so CredentialResult.workspaceId must be null.
        assertThat(result.workspaceId).isNull()
        result.provider.get("https://api.anthropic.com", false)
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrkspc_from_env")
    }

    @Test
    fun configFileWorkspaceIdBeatsEnvVar(@TempDir tempDir: Path) {
        // Profile config wins over ANTHROPIC_WORKSPACE_ID — same precedence model as
        // organization_id and the rest of the env-fillable fields.
        val configDir = tempDir
        val configsDir = configDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("identity-token")
        tokenFile.toFile().writeText("test-identity-token")

        val profileConfig = configsDir.resolve("with-workspace-profile.json")
        profileConfig
            .toFile()
            .writeText(
                """
                {
                    "authentication": {
                        "type": "oidc_federation",
                        "federation_rule_id": "fdrl_file",
                        "identity_token": {
                            "source": "file",
                            "path": "${tokenFile.toAbsolutePath()}"
                        }
                    },
                    "organization_id": "org_file",
                    "workspace_id": "wrkspc_file"
                }
                """
                    .trimIndent()
            )

        var exchangeBody: Map<String, String>? = null
        val mockClient = MockHttpClient { request ->
            exchangeBody = parseJsonParams(extractBody(request))
            createResponse(200, """{"access_token": "tok", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("with-workspace-profile")
                .envWorkspaceId("wrkspc_env")
                .configDir(configDir)
                .httpClient(mockClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result).isNotNull
        result.provider.get("https://api.anthropic.com", false)
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrkspc_file")
    }

    @Test
    fun step2ExplicitProfileUsesConfigurationFileProvider(@TempDir tempDir: Path) {
        val configsDir = tempDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("token.jwt")
        Files.write(tokenFile, "test-jwt-token".toByteArray())

        val configFile = configsDir.resolve("my-profile.json")
        Files.write(
            configFile,
            """
            {
                "organization_id": "org_profile",
                "authentication": {
                    "type": "oidc_federation",
                    "federation_rule_id": "fdrl_profile",
                    "identity_token": {
                        "source": "file",
                        "path": "${tokenFile.toString().replace("\\", "\\\\")}"
                    }
                }
            }
        """
                .trimIndent()
                .toByteArray(),
        )

        val mockHttpClient = MockHttpClient { request ->
            createResponse(
                200,
                """
                {
                    "access_token": "wif-access-token",
                    "expires_in": 3600
                }
                """
                    .trimIndent(),
            )
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("my-profile")
                .configDir(tempDir)
                .httpClient(mockHttpClient)
                .build()

        val result = resolver.resolve()
        assertThat(result).isNotNull
    }

    @Test
    fun profileResolutionUsesFileCache(@TempDir tempDir: Path) {
        val configsDir = tempDir.resolve("configs")
        Files.createDirectories(configsDir)

        val credentialsDir = tempDir.resolve("credentials")
        Files.createDirectories(credentialsDir)

        val tokenFile = tempDir.resolve("token.jwt")
        Files.write(tokenFile, "test-jwt-token".toByteArray())

        val configFile = configsDir.resolve("test-profile.json")
        Files.write(
            configFile,
            """
            {
                "organization_id": "org_test",
                "authentication": {
                    "type": "oidc_federation",
                    "federation_rule_id": "fdrl_test",
                    "identity_token": {
                        "source": "file",
                        "path": "${tokenFile.toString().replace("\\", "\\\\")}"
                    }
                }
            }
        """
                .trimIndent()
                .toByteArray(),
        )

        val cacheFile = credentialsDir.resolve("test-profile.json")
        val futureExpiry = java.time.Instant.now().plusSeconds(3600).epochSecond
        Files.write(
            cacheFile,
            """
            {
                "type": "access_token",
                "access_token": "cached-token-from-file",
                "expires_at": $futureExpiry
            }
        """
                .trimIndent()
                .toByteArray(),
        )

        val mockHttpClient = MockHttpClient { request ->
            throw AssertionError("HTTP client should not be called when cache is valid")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("test-profile")
                .configDir(tempDir)
                .httpClient(mockHttpClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()
        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("cached-token-from-file")
    }

    @Test
    fun profileResolutionWritesToFileCache(@TempDir tempDir: Path) {
        val configsDir = tempDir.resolve("configs")
        Files.createDirectories(configsDir)

        val tokenFile = tempDir.resolve("token.jwt")
        Files.write(tokenFile, "test-jwt-token".toByteArray())

        val configFile = configsDir.resolve("write-cache-profile.json")
        Files.write(
            configFile,
            """
            {
                "organization_id": "org_write",
                "authentication": {
                    "type": "oidc_federation",
                    "federation_rule_id": "fdrl_write",
                    "identity_token": {
                        "source": "file",
                        "path": "${tokenFile.toString().replace("\\", "\\\\")}"
                    }
                }
            }
        """
                .trimIndent()
                .toByteArray(),
        )

        val mockHttpClient = MockHttpClient { request ->
            createResponse(200, """{"access_token": "new-wif-token", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("write-cache-profile")
                .configDir(tempDir)
                .httpClient(mockHttpClient)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()
        assertThat(result).isNotNull
        val token = result.provider.get("https://api.anthropic.com", false)
        assertThat(token.token).isEqualTo("new-wif-token")

        val cacheFile = tempDir.resolve("credentials").resolve("write-cache-profile.json")
        assertThat(Files.exists(cacheFile)).isTrue()
        val cacheContent = String(Files.readAllBytes(cacheFile))
        assertThat(cacheContent).contains("new-wif-token")
        assertThat(cacheContent).contains("access_token")
    }

    @Test
    fun userOAuth_externallyRotated_resolvesWithoutHttpCall(@TempDir tempDir: Path) {
        val configDir = tempDir.resolve("configs")
        java.nio.file.Files.createDirectories(configDir)
        configDir
            .resolve("myprofile.json")
            .toFile()
            .writeText("""{"authentication": {"type": "user_oauth"}}""")
        val credsDir = tempDir.resolve("credentials")
        java.nio.file.Files.createDirectories(credsDir)
        credsDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """{"type": "access_token", "access_token": "sidecar-at", "expires_at": ${Instant.now().plusSeconds(60).epochSecond}}"""
            )
        var httpCalled = false
        val client = MockHttpClient { _ ->
            httpCalled = true
            error("should not be called for externally-rotated profile")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("myprofile")
                .configDir(tempDir)
                .httpClient(client)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()
        val token = result.provider.get("https://api.anthropic.com", false)

        assertThat(token.token).isEqualTo("sidecar-at")
        assertThat(httpCalled).isFalse()
    }

    @Test
    fun userOAuth_workspaceIdPropagatedToCredentialResult(@TempDir tempDir: Path) {
        // Regression: workspace_id suppression on CredentialResult is scoped to oidc_federation
        // only. Non-federation profiles (e.g. user_oauth) must still emit the
        // anthropic-workspace-id header, so the resolved CredentialResult must carry workspaceId.
        val configDir = tempDir.resolve("configs")
        java.nio.file.Files.createDirectories(configDir)
        configDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """{"authentication": {"type": "user_oauth"}, "workspace_id": "wrkspc_oauth"}"""
            )
        val credsDir = tempDir.resolve("credentials")
        java.nio.file.Files.createDirectories(credsDir)
        credsDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """{"type": "access_token", "access_token": "sidecar-at", "expires_at": ${Instant.now().plusSeconds(60).epochSecond}}"""
            )
        val client = MockHttpClient { _ -> error("should not be called") }

        val resolver =
            CredentialResolver.builder()
                .envProfile("myprofile")
                .configDir(tempDir)
                .httpClient(client)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result.workspaceId).isEqualTo("wrkspc_oauth")
    }

    @Test
    fun userOAuth_envWorkspaceIdFillsMissingAndPropagatesToCredentialResult(
        @TempDir tempDir: Path
    ) {
        // ANTHROPIC_WORKSPACE_ID fills workspace_id uniformly across profile types — not just
        // federation. For user_oauth the filled value surfaces as the anthropic-workspace-id
        // request header via CredentialResult.workspaceId (federation routes it into the exchange
        // body instead and suppresses the header).
        val configDir = tempDir.resolve("configs")
        java.nio.file.Files.createDirectories(configDir)
        configDir
            .resolve("myprofile.json")
            .toFile()
            .writeText("""{"authentication": {"type": "user_oauth"}}""")
        val credsDir = tempDir.resolve("credentials")
        java.nio.file.Files.createDirectories(credsDir)
        credsDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """{"type": "access_token", "access_token": "sidecar-at", "expires_at": ${Instant.now().plusSeconds(60).epochSecond}}"""
            )
        val client = MockHttpClient { _ -> error("should not be called") }

        val resolver =
            CredentialResolver.builder()
                .envProfile("myprofile")
                .envWorkspaceId("wrkspc_env")
                .configDir(tempDir)
                .httpClient(client)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()

        assertThat(result.workspaceId).isEqualTo("wrkspc_env")
    }

    @Test
    fun userOAuth_expiredTokenStillResolvesWhenRefreshAvailable(@TempDir tempDir: Path) {
        val configDir = tempDir.resolve("configs")
        java.nio.file.Files.createDirectories(configDir)
        configDir
            .resolve("myprofile.json")
            .toFile()
            .writeText("""{"authentication": {"type": "user_oauth", "client_id": "client_abc"}}""")
        val credsDir = tempDir.resolve("credentials")
        java.nio.file.Files.createDirectories(credsDir)
        credsDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """
            {"type": "access_token",
             "access_token": "expired-at",
             "expires_at": ${Instant.now().minusSeconds(60).epochSecond},
             "refresh_token": "rt-abc"}
            """
                    .trimIndent()
            )
        val client = MockHttpClient { _ ->
            createResponse(200, """{"access_token": "freshly-refreshed", "expires_in": 3600}""")
        }

        val resolver =
            CredentialResolver.builder()
                .envProfile("myprofile")
                .configDir(tempDir)
                .httpClient(client)
                .jsonMapper(jsonMapper())
                .build()

        val result = resolver.resolve()
        val token = result.provider.get("https://api.anthropic.com", false)

        assertThat(token.token).isEqualTo("freshly-refreshed")
    }

    @Test
    fun userOAuth_missingHttpClient_recordsPartialFailure(@TempDir tempDir: Path) {
        val configDir = tempDir.resolve("configs")
        java.nio.file.Files.createDirectories(configDir)
        configDir
            .resolve("myprofile.json")
            .toFile()
            .writeText("""{"authentication": {"type": "user_oauth", "client_id": "client_abc"}}""")
        val credsDir = tempDir.resolve("credentials")
        java.nio.file.Files.createDirectories(credsDir)
        credsDir
            .resolve("myprofile.json")
            .toFile()
            .writeText(
                """
            {"type": "access_token",
             "access_token": "at",
             "expires_at": ${Instant.now().plusSeconds(60).epochSecond},
             "refresh_token": "rt"}
            """
                    .trimIndent()
            )

        val resolver =
            CredentialResolver.builder()
                .envProfile("myprofile")
                .configDir(tempDir)
                .jsonMapper(jsonMapper())
                .build()

        assertThatThrownBy { resolver.resolve() }
            .isInstanceOf(com.anthropic.errors.CredentialResolutionException::class.java)
            .hasMessageContaining("HTTP client required for user_oauth refresh")
    }

    private fun extractBody(request: HttpRequest): String {
        val body = request.body ?: return ""
        val outputStream = ByteArrayOutputStream()
        body.writeTo(outputStream)
        return outputStream.toString("UTF-8")
    }

    private fun parseJsonParams(body: String): Map<String, String> = jsonMapper().readValue(body)

    private fun createResponse(statusCode: Int, body: String): HttpResponse {
        return object : HttpResponse {
            override fun statusCode() = statusCode

            override fun headers() = com.anthropic.core.http.Headers.builder().build()

            override fun body() = ByteArrayInputStream(body.toByteArray())

            override fun close() {}
        }
    }

    private class MockHttpClient(private val syncHandler: (HttpRequest) -> HttpResponse) :
        HttpClient {
        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            return syncHandler(request)
        }

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            return CompletableFuture.completedFuture(syncHandler(request))
        }

        override fun close() {}
    }
}
