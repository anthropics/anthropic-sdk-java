package com.anthropic.client.okhttp

import com.anthropic.backends.AnthropicBackend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.config.AuthenticationConfig
import com.anthropic.config.AuthenticationType
import com.anthropic.config.ProfileConfig
import com.anthropic.config.ProfileConfigProvider
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.verification.LoggedRequest
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Path
import java.util.concurrent.atomic.AtomicBoolean
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assumptions.assumeTrue
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
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderCredentialIgnoresEnvCredentials(@TempDir tempDir: Path) {
        stubModel()

        withEnvCredentials {
            for (option in Option.entries - Option.FROM_ENV) {
                assertThat(authorizationsSent(tempDir, Option.FROM_ENV, option))
                    .containsExactly(option.authorization, option.authorization)
                assertThat(authorizationsSent(tempDir, option, Option.FROM_ENV))
                    .containsExactly(option.authorization, option.authorization)
            }
        }
    }

    @Test
    fun twoBuilderCredentialsThrow(@TempDir tempDir: Path) {
        val options = Option.entries - Option.FROM_ENV

        for (first in options) {
            for (second in options - first) {
                assertThatThrownBy { client(tempDir, first, second) }
                    .isExactlyInstanceOf(IllegalStateException::class.java)
                    .hasMessageStartingWith("Pass at most one of")
                assertThatThrownBy { clientAsync(tempDir, first, second) }
                    .isExactlyInstanceOf(IllegalStateException::class.java)
                    .hasMessageStartingWith("Pass at most one of")
            }
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun envApiKeyBeatsEnvAuthToken(@TempDir tempDir: Path) {
        stubModel()

        withEnvCredentials {
            assertThat(authorizationsSent(tempDir, Option.FROM_ENV))
                .containsExactly(Option.FROM_ENV.authorization, Option.FROM_ENV.authorization)
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun envApiKeyAndEnvAuthTokenWarnOncePerProcess(@TempDir tempDir: Path) {
        withEnvCredentials {
            for (async in listOf(false, true)) {
                withNoEarlierWarning {
                    val first = captureStderr { build(async, tempDir, Option.FROM_ENV) }
                    val second = captureStderr { build(async, tempDir, Option.FROM_ENV) }

                    assertThat(first)
                        .isEqualTo(
                            "Anthropic: ANTHROPIC_API_KEY and ANTHROPIC_AUTH_TOKEN are both set. " +
                                "Using ANTHROPIC_API_KEY." +
                                System.lineSeparator()
                        )
                    assertThat(second).isEmpty()
                }
            }
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun oneEnvCredentialDoesNotWarn(@TempDir tempDir: Path) {
        assumeTrue(System.getenv("ANTHROPIC_API_KEY").isNullOrEmpty())
        assumeTrue(System.getenv("ANTHROPIC_AUTH_TOKEN").isNullOrEmpty())

        for (property in listOf("anthropic.apiKey", "anthropic.authToken")) {
            withSystemProperty(property, "env-credential") {
                withNoEarlierWarning {
                    val stderr = captureStderr {
                        build(async = false, tempDir, Option.FROM_ENV)
                        build(async = true, tempDir, Option.FROM_ENV)
                    }

                    assertThat(stderr).isEmpty()
                }
            }
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderCredentialDoesNotWarn(@TempDir tempDir: Path) {
        withEnvCredentials {
            withNoEarlierWarning {
                val stderr = captureStderr {
                    for (option in Option.entries - Option.FROM_ENV) {
                        build(async = false, tempDir, Option.FROM_ENV, option)
                        build(async = true, tempDir, Option.FROM_ENV, option)
                    }
                }

                assertThat(stderr).isEmpty()
            }
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun withOptionsKeepsCredentialFromEnv(@TempDir tempDir: Path) {
        stubModel()

        lateinit var original: AnthropicClient
        lateinit var originalAsync: AnthropicClientAsync
        withEnvCredentials {
            original = client(tempDir, Option.FROM_ENV)
            originalAsync = clientAsync(tempDir, Option.FROM_ENV)
        }

        val authorizations =
            authorizationsSent(
                original.withOptions { it.maxRetries(0) },
                originalAsync.withOptions { it.maxRetries(0) },
            )

        assertThat(authorizations)
            .containsExactly(Option.FROM_ENV.authorization, Option.FROM_ENV.authorization)
    }

    @Test
    fun apiKeyHeaderSetByHand() {
        stubModel()

        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .putHeader("X-Api-Key", "header-api-key")
                .build()
        client.models().retrieve(MODEL_ID)

        val request = singleModelRequest()
        assertThat(request.header("X-Api-Key").values()).containsExactly("header-api-key")
        assertThat(request.containsHeader("Authorization")).isFalse()
    }

    @Test
    fun apiKeyAndAuthorizationHeadersAreBothSent() {
        stubModel()

        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .putHeader("X-Api-Key", "header-api-key")
                .putHeader("Authorization", "Bearer header-auth-token")
                .build()
        client.models().retrieve(MODEL_ID)

        val request = singleModelRequest()
        assertThat(request.header("X-Api-Key").values()).containsExactly("header-api-key")
        assertThat(request.header("Authorization").values())
            .containsExactly("Bearer header-auth-token")
    }

    @Test
    fun withOptionsAuthorizationHeaderOverridesApiKey() {
        stubModel()

        val client = AnthropicOkHttpClient.builder().baseUrl(baseUrl).apiKey("test-api-key").build()
        client
            .withOptions { it.putHeader("Authorization", "Bearer header-auth-token") }
            .models()
            .retrieve(MODEL_ID)

        assertThat(singleModelRequest().header("Authorization").values())
            .containsExactly("Bearer header-auth-token")
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
        verify(
            1,
            postRequestedFor(urlPathEqualTo("/env/v1/oauth/token"))
                .withoutHeader("anthropic-version"),
        )
        verify(0, postRequestedFor(urlPathEqualTo("/profile/v1/oauth/token")))
        assertRequestedVia("/env", REFRESHED_TOKEN)
    }

    /** A builder call, and the `Authorization` value sent when it decides the credential. */
    private enum class Option(val authorization: String) {
        FROM_ENV("Bearer env-api-key"),
        API_KEY("Bearer explicit-api-key"),
        AUTH_TOKEN("Bearer explicit-auth-token"),
        CONFIGURATION_PROVIDER("Bearer $PROFILE_TOKEN"),
    }

    private fun client(tempDir: Path, vararg options: Option): AnthropicClient =
        options
            .fold(AnthropicOkHttpClient.builder()) { builder, option ->
                when (option) {
                    Option.FROM_ENV -> builder.fromEnv()
                    Option.API_KEY -> builder.apiKey("explicit-api-key")
                    Option.AUTH_TOKEN -> builder.authToken("explicit-auth-token")
                    Option.CONFIGURATION_PROVIDER ->
                        builder.configurationProvider(profileWithBaseUrl(tempDir, baseUrl))
                }
            }
            .baseUrl(baseUrl)
            .build()

    private fun clientAsync(tempDir: Path, vararg options: Option): AnthropicClientAsync =
        options
            .fold(AnthropicOkHttpClientAsync.builder()) { builder, option ->
                when (option) {
                    Option.FROM_ENV -> builder.fromEnv()
                    Option.API_KEY -> builder.apiKey("explicit-api-key")
                    Option.AUTH_TOKEN -> builder.authToken("explicit-auth-token")
                    Option.CONFIGURATION_PROVIDER ->
                        builder.configurationProvider(profileWithBaseUrl(tempDir, baseUrl))
                }
            }
            .baseUrl(baseUrl)
            .build()

    private fun build(async: Boolean, tempDir: Path, vararg options: Option) {
        if (async) clientAsync(tempDir, *options) else client(tempDir, *options)
    }

    /** The `Authorization` values that a sync and an async client built with [options] send. */
    private fun authorizationsSent(tempDir: Path, vararg options: Option): List<String> =
        authorizationsSent(client(tempDir, *options), clientAsync(tempDir, *options))

    private fun authorizationsSent(
        client: AnthropicClient,
        clientAsync: AnthropicClientAsync,
    ): List<String> {
        resetAllRequests()
        client.models().retrieve(MODEL_ID)
        clientAsync.models().retrieve(MODEL_ID).get()
        val requests = findAll(getRequestedFor(urlPathMatching(".*/v1/models/$MODEL_ID")))
        assertThat(requests).noneMatch { it.containsHeader("X-Api-Key") }
        return requests.flatMap { it.header("Authorization").values() }
    }

    private fun stubModel() {
        stubFor(
            get(urlPathMatching(".*/v1/models/$MODEL_ID")).willReturn(ok().withBody(MODEL_JSON))
        )
    }

    private fun singleModelRequest(): LoggedRequest {
        val requests = findAll(getRequestedFor(urlPathMatching(".*/v1/models/$MODEL_ID")))
        assertThat(requests).hasSize(1)
        return requests[0]
    }

    private fun assertRequestedVia(prefix: String, token: String = PROFILE_TOKEN) {
        val request = singleModelRequest()
        assertThat(request.url).isEqualTo("$prefix/v1/models/$MODEL_ID")
        // Proves the profile resolved, so the base URL assertions aren't vacuous.
        assertThat(request.header("Authorization").values()).containsExactly("Bearer $token")
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

    /** Runs [block] with both static credentials set as system properties. */
    private fun withEnvCredentials(block: () -> Unit) {
        withSystemProperty("anthropic.apiKey", "env-api-key") {
            withSystemProperty("anthropic.authToken", "env-auth-token", block)
        }
    }

    /** Runs [block] as if this process had not yet warned about the credentials from the env. */
    private fun withNoEarlierWarning(block: () -> Unit) {
        val field = AnthropicBackend::class.java.getDeclaredField("warnedBothEnvCredentials")
        field.isAccessible = true
        val warned = checkNotNull(field.get(null)) as AtomicBoolean
        val previous = warned.getAndSet(false)
        try {
            block()
        } finally {
            warned.set(previous)
        }
    }

    private fun captureStderr(block: () -> Unit): String {
        val originalErr = System.err
        val stderr = ByteArrayOutputStream()
        System.setErr(PrintStream(stderr, true))
        try {
            block()
        } finally {
            System.setErr(originalErr)
        }
        return stderr.toString()
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
