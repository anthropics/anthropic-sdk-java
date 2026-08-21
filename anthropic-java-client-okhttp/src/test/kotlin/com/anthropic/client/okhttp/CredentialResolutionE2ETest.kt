package com.anthropic.client.okhttp

import com.anthropic.config.AuthenticationConfig
import com.anthropic.config.AuthenticationType
import com.anthropic.config.ProfileConfig
import com.anthropic.config.ProfileConfigProvider
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.nio.file.Path
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class CredentialResolutionE2ETest {

    private lateinit var baseUrl: String

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
    }

    @Test
    fun zeroConfigWithEnvApiKey() {
        stubFor(
            post(urlPathEqualTo("/v1/messages")).willReturn(ok().withBody("""{"result": "ok"}"""))
        )

        val client = AnthropicOkHttpClient.builder().baseUrl(baseUrl).apiKey("test-api-key").build()

        assertThat(client).isNotNull
    }

    @Test
    fun builderWithExplicitApiKey() {
        val client = AnthropicOkHttpClient.builder().apiKey("explicit-api-key").build()
        assertThat(client).isNotNull()
    }

    @Test
    fun profileBaseUrlAppliesByDefault(@TempDir tempDir: Path) {
        stubModel()

        val client =
            AnthropicOkHttpClient.builder()
                .configurationProvider(profileWithBaseUrl(tempDir, "$baseUrl/profile"))
                .build()
        client.models().retrieve(MODEL_ID)

        assertRequestedVia("/profile")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun envBaseUrlOverridesProfileBaseUrl(@TempDir tempDir: Path) {
        stubModel()

        withSystemProperty("anthropic.baseUrl", "$baseUrl/env") {
            val client =
                AnthropicOkHttpClient.builder()
                    .fromEnv()
                    .configurationProvider(profileWithBaseUrl(tempDir, "$baseUrl/profile"))
                    .build()
            client.models().retrieve(MODEL_ID)
        }

        assertRequestedVia("/env")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun explicitBaseUrlOverridesEnvAndProfileBaseUrl(@TempDir tempDir: Path) {
        stubModel()

        withSystemProperty("anthropic.baseUrl", "$baseUrl/env") {
            val client =
                AnthropicOkHttpClient.builder()
                    .fromEnv()
                    .configurationProvider(profileWithBaseUrl(tempDir, "$baseUrl/profile"))
                    .baseUrl("$baseUrl/explicit")
                    .build()
            client.models().retrieve(MODEL_ID)
        }

        assertRequestedVia("/explicit")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun envBaseUrlOverridesProfileBaseUrlForTokenRefresh(@TempDir tempDir: Path) {
        stubModel()
        stubFor(
            post(urlPathMatching(".*/v1/oauth/token"))
                .willReturn(
                    ok().withBody("""{"access_token": "$REFRESHED_TOKEN", "expires_in": 3600}""")
                )
        )

        withSystemProperty("anthropic.baseUrl", "$baseUrl/env") {
            val client =
                AnthropicOkHttpClient.builder()
                    .fromEnv()
                    .configurationProvider(
                        profileWithBaseUrl(tempDir, "$baseUrl/profile", expired = true)
                    )
                    .build()
            client.models().retrieve(MODEL_ID)
        }

        // The refresh grant must follow the same base URL precedence as API requests.
        verify(1, postRequestedFor(urlPathEqualTo("/env/v1/oauth/token")))
        verify(0, postRequestedFor(urlPathEqualTo("/profile/v1/oauth/token")))
        assertRequestedVia("/env", REFRESHED_TOKEN)
    }

    private fun stubModel() {
        stubFor(
            get(urlPathMatching(".*/v1/models/$MODEL_ID")).willReturn(ok().withBody(MODEL_JSON))
        )
    }

    private fun assertRequestedVia(prefix: String, token: String = PROFILE_TOKEN) {
        val requests = findAll(getRequestedFor(urlPathMatching(".*/v1/models/$MODEL_ID")))
        assertThat(requests).hasSize(1)
        assertThat(requests[0].url).isEqualTo("$prefix/v1/models/$MODEL_ID")
        // Proves the profile resolved, so the base URL assertions aren't vacuous.
        assertThat(requests[0].header("Authorization").values()).containsExactly("Bearer $token")
    }

    /**
     * A `user_oauth` profile with the given `base_url`. When [expired], the on-disk access token is
     * stale and refreshable, so the first request triggers a `refresh_token` grant.
     */
    private fun profileWithBaseUrl(
        dir: Path,
        profileBaseUrl: String,
        expired: Boolean = false,
    ): ProfileConfigProvider {
        val credentialsFile = dir.resolve("credentials.json")
        val expiresAt = if (expired) 0 else 4102444800
        credentialsFile
            .toFile()
            .writeText(
                """{"type": "oauth_token", "access_token": "$PROFILE_TOKEN", "expires_at": $expiresAt, "refresh_token": "rt-test"}"""
            )
        val config =
            ProfileConfig.builder()
                .authentication(
                    AuthenticationConfig.builder()
                        .type(AuthenticationType.USER_OAUTH)
                        .credentialsPath(credentialsFile.toAbsolutePath().toString())
                        .clientId(if (expired) "client_test" else null)
                        .build()
                )
                .baseUrl(profileBaseUrl)
                .build()
        return object : ProfileConfigProvider {
            override fun get(): ProfileConfig = config
        }
    }

    private fun withSystemProperty(key: String, value: String, block: () -> Unit) {
        val previous = System.getProperty(key)
        System.setProperty(key, value)
        try {
            block()
        } finally {
            if (previous == null) System.clearProperty(key) else System.setProperty(key, previous)
        }
    }

    companion object {
        private const val MODEL_ID = "claude-test"
        private const val PROFILE_TOKEN = "profile-access-token"
        private const val REFRESHED_TOKEN = "refreshed-access-token"
        private const val MODEL_JSON =
            """{"id":"$MODEL_ID","created_at":"2025-02-19T00:00:00Z","display_name":"Claude Test","type":"model"}"""
    }
}
