package com.anthropic.backends

import com.anthropic.config.InMemoryProfileConfigProvider
import com.anthropic.config.ProfileConfig
import com.anthropic.config.ProfileConfigProvider
import com.anthropic.core.RequestOptions
import com.anthropic.core.auth.InMemoryIdentityTokenProvider
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.jsonMapper
import com.anthropic.helpers.captureStderr
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Optional
import java.util.concurrent.CompletableFuture
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.mockito.Mockito

internal class AnthropicBackendTest {
    companion object {
        private const val PRODUCTION_URL = "https://api.anthropic.com"
        private const val OTHER_URL = "https://api.example.com"
        private const val API_KEY = "test-api-key-123456789"
        private const val AUTH_TOKEN = "test-auth-token-987654321"
        private const val ENV_API_KEY = "env-api-key-123456789"
        private const val ENV_AUTH_TOKEN = "env-auth-token-987654321"
    }

    enum class CredentialOption(
        val optionName: String,
        val set: (AnthropicBackend.Builder) -> Unit,
    ) {
        API_KEY("apiKey", { it.apiKey(AnthropicBackendTest.API_KEY) }),
        AUTH_TOKEN("authToken", { it.authToken(AnthropicBackendTest.AUTH_TOKEN) }),
        CONFIGURATION_PROVIDER(
            "configurationProvider",
            {
                it.configurationProvider(
                    InMemoryProfileConfigProvider.of(ProfileConfig.builder().build())
                )
            },
        ),
        FEDERATION_TOKEN_PROVIDER(
            "federationTokenProvider",
            {
                it.federationTokenProvider(
                    identityTokenProvider = InMemoryIdentityTokenProvider("test"),
                    federationRuleId = "fdrl_test123",
                    organizationId = "org_test456",
                )
            },
        ),
    }

    @Test
    fun baseUrlExplicit() {
        val backend = createBackend(null, AUTH_TOKEN, OTHER_URL)

        assertThat(backend.baseUrl()).isEqualTo(OTHER_URL)
    }

    @Test
    fun baseUrlDefault() {
        val backend = createBackend(API_KEY, null, null)

        assertThat(backend.baseUrl()).isEqualTo(PRODUCTION_URL)
    }

    @Test
    fun prepareRequestKeepsVersionHeader() {
        // The client sets "anthropic-version" on every request; the backend must not touch it.
        val backend = createBackend()
        val request =
            createRequest().toBuilder().putHeader("anthropic-version", "2024-01-01").build()

        val preparedRequest = backend.prepareRequest(request)

        assertThat(preparedRequest.headers.values("anthropic-version"))
            .containsExactly("2024-01-01")
    }

    @Test
    fun authorizeRequestWithApiKey() {
        val backend = createBackend(API_KEY, null, null)
        // It is not necessary to call "prepareRequest" or resolve the URL
        // before calling "authorizeRequest" on an "AnthropicBackend".
        val request = createRequest()
        val headersCount = request.headers.names().size
        val authorizedRequest = backend.authorizeRequest(request)

        assertThat(request.headers.values("X-Api-Key")).isEmpty()
        assertThat(request.headers.values("Authorization")).isEmpty()

        assertThat(authorizedRequest.headers.names().size).isEqualTo(headersCount + 1)
        assertThat(authorizedRequest.headers.values("Authorization").size).isEqualTo(1)
        assertThat(authorizedRequest.headers.values("Authorization")[0])
            .isEqualTo("Bearer $API_KEY")
        assertThat(authorizedRequest.headers.values("X-Api-Key")).isEmpty()
    }

    @Test
    fun authorizeRequestAuthToken() {
        val backend = createBackend(null, AUTH_TOKEN, null)
        val request = createRequest()
        val headersCount = request.headers.names().size
        val authorizedRequest = backend.authorizeRequest(request)

        assertThat(request.headers.values("X-Api-Key")).isEmpty()
        assertThat(request.headers.values("Authorization")).isEmpty()

        assertThat(authorizedRequest.headers.names().size).isEqualTo(headersCount + 1)
        assertThat(authorizedRequest.headers.values("Authorization").size).isEqualTo(1)
        assertThat(authorizedRequest.headers.values("Authorization")[0])
            .isEqualTo("Bearer $AUTH_TOKEN")
        assertThat(authorizedRequest.headers.values("X-Api-Key")).isEmpty()
    }

    @Test
    fun authorizeRequestAlreadyAuthorizedWithApiKeyHeader() {
        val backend = createBackend(API_KEY, null, null)
        val request = createRequest().toBuilder().putHeader("X-Api-Key", "other-api-key").build()

        assertThat(backend.authorizeRequest(request)).isSameAs(request)
    }

    @Test
    fun authorizeRequestAlreadyAuthorizedWithApiKey() {
        val backend = createBackend(API_KEY, null, null)
        val authorizedRequest = backend.authorizeRequest(createRequest())

        val secondAuthorizedRequest = backend.authorizeRequest(authorizedRequest)
        assertThat(secondAuthorizedRequest).isSameAs(authorizedRequest)
    }

    @Test
    fun authorizeRequestAlreadyAuthorizedWithAuthToken() {
        val backend = createBackend(null, AUTH_TOKEN, null)
        val authorizedRequest = backend.authorizeRequest(createRequest())

        val secondAuthorizedRequest = backend.authorizeRequest(authorizedRequest)
        assertThat(secondAuthorizedRequest).isSameAs(authorizedRequest)
    }

    @ParameterizedTest
    @EnumSource
    fun builderTwoCredentialsSet(first: CredentialOption) {
        for (second in CredentialOption.entries - first) {
            val builder = AnthropicBackend.builder().also(first.set).also(second.set)
            val received = listOf(first, second).sorted().joinToString { "`${it.optionName}`" }

            assertThatThrownBy { builder.build() }
                .isExactlyInstanceOf(IllegalStateException::class.java)
                .hasMessage(
                    "Pass at most one of `apiKey`, `authToken`, `configurationProvider`. " +
                        "Received $received."
                )
        }
    }

    @Test
    fun builderNullUnsetsCredential() {
        val backend =
            AnthropicBackend.builder()
                .apiKey(API_KEY)
                .apiKey(null)
                .authToken(AUTH_TOKEN)
                .authToken(Optional.empty())
                .apiKey(API_KEY)
                .build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.authToken()).isEmpty
    }

    @Test
    fun builderApiKeyAndAuthTokenBothNotSet() {
        // There is expected to be no enforcement of the absence of both
        // credential values.
        assertThatNoException().isThrownBy { createBackend(null, null, null) }
    }

    @Test
    fun builderCredentialsFromStrings() {
        // Check that if the "(...: String?)" setters are called, that they set
        // their respective fields and do not get their wires crossed.
        val apiKeyBackend = createBackend(API_KEY, null, null)
        val authTokenBackend = createBackend(null, AUTH_TOKEN, null)

        assertThat(apiKeyBackend.apiKey()).hasValue(API_KEY)
        assertThat(apiKeyBackend.authToken()).isEmpty
        assertThat(authTokenBackend.apiKey()).isEmpty
        assertThat(authTokenBackend.authToken()).hasValue(AUTH_TOKEN)
    }

    @Test
    fun builderCredentialsFromOptionals() {
        // Check that if the "(...: Optional<String>)" setters are called, that
        // they set their respective fields and do not get their wires crossed.
        val apiKeyBackend = AnthropicBackend.builder().apiKey(Optional.ofNullable(API_KEY)).build()
        val authTokenBackend =
            AnthropicBackend.builder().authToken(Optional.ofNullable(AUTH_TOKEN)).build()

        assertThat(apiKeyBackend.apiKey()).hasValue(API_KEY)
        assertThat(apiKeyBackend.authToken()).isEmpty
        assertThat(authTokenBackend.apiKey()).isEmpty
        assertThat(authTokenBackend.authToken()).hasValue(AUTH_TOKEN)
    }

    @Test
    fun builderCredentialsFromOptionalsWithNulls() {
        val backend =
            AnthropicBackend.builder()
                .apiKey(Optional.ofNullable(null))
                .authToken(Optional.ofNullable(null))
                .build()

        assertThat(backend.apiKey()).isEmpty
        assertThat(backend.authToken()).isEmpty
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderCredentialsFromEnv() {
        withEnvCredentials {
            val backend = AnthropicBackend.builder().fromEnv().build()

            assertThat(backend.apiKey()).hasValue(ENV_API_KEY)
            assertThat(backend.authToken()).isEmpty
            assertThat(backend.authorizeRequest(createRequest()).headers.values("Authorization"))
                .containsExactly("Bearer $ENV_API_KEY")
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderBothCredentialsFromEnvWarnOncePerProcess() {
        val builders = List(2) { builderWithEnvVariables(ENV_API_KEY, ENV_AUTH_TOKEN) }

        withNoEarlierWarning {
            val first = captureStderr { builders[0].fromEnv().build() }
            val second = captureStderr { builders[1].fromEnv().build() }

            assertThat(first)
                .isEqualTo(
                    "Anthropic: ANTHROPIC_API_KEY and ANTHROPIC_AUTH_TOKEN are both set. " +
                        "Using ANTHROPIC_API_KEY." +
                        System.lineSeparator()
                )
            assertThat(second).isEmpty()
        }
    }

    @ParameterizedTest
    @CsvSource("$ENV_API_KEY,", ",$ENV_AUTH_TOKEN", "$ENV_API_KEY,''", "'',$ENV_AUTH_TOKEN")
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderOneCredentialFromEnvDoesNotWarn(apiKey: String?, authToken: String?) {
        val builder = builderWithEnvVariables(apiKey, authToken)

        withNoEarlierWarning { assertThat(captureStderr { builder.fromEnv().build() }).isEmpty() }
    }

    @ParameterizedTest
    @EnumSource
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderCredentialDoesNotWarn(option: CredentialOption) {
        withEnvCredentials {
            withNoEarlierWarning {
                val stderr = captureStderr {
                    AnthropicBackend.builder().fromEnv().also(option.set).build()
                    AnthropicBackend.builder().also(option.set).fromEnv().build()
                }

                assertThat(stderr).isEmpty()
            }
        }
    }

    @ParameterizedTest
    @CsvSource("'',$ENV_AUTH_TOKEN", "$ENV_API_KEY,''", "'',''")
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderEmptyCredentialFromEnvIsNotSet(apiKey: String, authToken: String) {
        val fromVariables = builderWithEnvVariables(apiKey, authToken).fromEnv().build()
        lateinit var fromProperties: AnthropicBackend
        withEnvCredentials(apiKey, authToken) {
            fromProperties = builderWithEnvVariables().fromEnv().build()
        }

        for (backend in listOf(fromVariables, fromProperties)) {
            assertThat(backend.apiKey().getOrNull()).isEqualTo(apiKey.ifEmpty { null })
            assertThat(backend.authToken().getOrNull()).isEqualTo(authToken.ifEmpty { null })
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderEmptyPropertyFallsBackToEnvVariable() {
        withEnvCredentials(apiKey = "", authToken = "") {
            val apiKeyBackend = builderWithEnvVariables(apiKey = ENV_API_KEY).fromEnv().build()
            val authTokenBackend =
                builderWithEnvVariables(authToken = ENV_AUTH_TOKEN).fromEnv().build()

            assertThat(apiKeyBackend.apiKey()).hasValue(ENV_API_KEY)
            assertThat(authTokenBackend.authToken()).hasValue(ENV_AUTH_TOKEN)
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun builderApiKeyVariableBeatsAuthTokenProperty() {
        withEnvCredentials(apiKey = null, authToken = ENV_AUTH_TOKEN) {
            val backend = builderWithEnvVariables(apiKey = ENV_API_KEY).fromEnv().build()

            assertThat(backend.apiKey()).hasValue(ENV_API_KEY)
            assertThat(backend.authToken()).isEmpty
        }
    }

    @ParameterizedTest
    @EnumSource
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderCredentialBeatsCredentialsFromEnv(option: CredentialOption) {
        withEnvCredentials {
            val envFirst = AnthropicBackend.builder().fromEnv().also(option.set).build()
            val envLast = AnthropicBackend.builder().also(option.set).fromEnv().build()

            for (backend in listOf(envFirst, envLast)) {
                assertThat(backend.apiKey().getOrNull())
                    .isEqualTo(API_KEY.takeIf { option == CredentialOption.API_KEY })
                assertThat(backend.authToken().getOrNull())
                    .isEqualTo(AUTH_TOKEN.takeIf { option == CredentialOption.AUTH_TOKEN })
            }
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderNullApiKeyKeepsAuthTokenFromEnv() {
        withEnvCredentials {
            val backend = AnthropicBackend.builder().fromEnv().apiKey(null).build()

            assertThat(backend.apiKey()).isEmpty
            assertThat(backend.authToken()).hasValue(ENV_AUTH_TOKEN)
        }
    }

    @Test
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun builderNullCredentialsDropCredentialsFromEnv() {
        withEnvCredentials {
            val backend = AnthropicBackend.builder().fromEnv().apiKey(null).authToken(null).build()

            assertThat(backend.apiKey()).isEmpty
            assertThat(backend.authToken()).isEmpty
        }
    }

    @Test
    fun builderAcceptsFederationTokenProvider() {
        val identityProvider = InMemoryIdentityTokenProvider("test")

        val backend =
            AnthropicBackend.builder()
                .federationTokenProvider(
                    identityTokenProvider = identityProvider,
                    federationRuleId = "fdrl_test123",
                    organizationId = "org_test456",
                )
                .build()

        assertThat(backend).isNotNull()
    }

    @Test
    fun builderAcceptsConfigurationProvider() {
        val provider =
            object : ProfileConfigProvider {
                override fun get(): ProfileConfig = ProfileConfig.builder().build()
            }

        val backend = AnthropicBackend.builder().configurationProvider(provider).build()

        assertThat(backend).isNotNull()
    }

    @Test
    fun resolveCredentialsWithFederationConfigReturnsCredentials() {
        val identityProvider = InMemoryIdentityTokenProvider("test-identity-token")
        val mockHttpClient = createMockHttpClient()

        val backend =
            AnthropicBackend.builder()
                .federationTokenProvider(
                    identityTokenProvider = identityProvider,
                    federationRuleId = "fdrl_test",
                    organizationId = "org_test",
                )
                .build()

        val credentials = backend.resolveCredentials(mockHttpClient)
        assertThat(credentials).isNotNull()
        assertThat(credentials!!.provider).isNotNull()
    }

    @Test
    fun federationTokenProviderPassesWorkspaceIdInExchangeBody() {
        val identityProvider = InMemoryIdentityTokenProvider("test-identity-token")
        var exchangeBody: Map<String, String>? = null
        val mockHttpClient =
            object : HttpClient {
                override fun execute(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): HttpResponse {
                    val out = ByteArrayOutputStream()
                    request.body().getOrNull()?.writeTo(out)
                    exchangeBody = jsonMapper().readValue(out.toString("UTF-8"))
                    return object : HttpResponse {
                        override fun statusCode() = 200

                        override fun headers() = Headers.builder().build()

                        override fun body() =
                            ByteArrayInputStream(
                                """{"access_token": "tok", "expires_in": 3600}""".toByteArray()
                            )

                        override fun close() {}
                    }
                }

                override fun executeAsync(
                    request: HttpRequest,
                    requestOptions: RequestOptions,
                ): CompletableFuture<HttpResponse> =
                    CompletableFuture.completedFuture(execute(request, requestOptions))

                override fun close() {}
            }

        val backend =
            AnthropicBackend.builder()
                .federationTokenProvider(
                    identityTokenProvider = identityProvider,
                    federationRuleId = "fdrl_test",
                    organizationId = "org_test",
                    serviceAccountId = null,
                    workspaceId = "wrkspc_x",
                )
                .build()

        val credentials = backend.resolveCredentials(mockHttpClient)
        assertThat(credentials).isNotNull()
        credentials!!.provider.get("https://api.anthropic.com", false)
        assertThat(exchangeBody).isNotNull
        assertThat(exchangeBody!!["workspace_id"]).isEqualTo("wrkspc_x")
    }

    @Test
    fun resolveCredentialsReturnsNullWithoutFederationConfig() {
        val backend = AnthropicBackend.builder().apiKey("test-key").build()
        val mockHttpClient = createMockHttpClient()

        assertThat(backend.resolveCredentials(mockHttpClient)).isNull()
    }

    @Test
    fun resolveCredentialsReturnsNullWhenApiKeySet() {
        val backend = AnthropicBackend.builder().apiKey("test-key").fromEnv().build()
        val mockHttpClient = createMockHttpClient()

        assertThat(backend.resolveCredentials(mockHttpClient)).isNull()
    }

    private fun createMockHttpClient(): HttpClient =
        object : HttpClient {
            override fun execute(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): HttpResponse {
                throw UnsupportedOperationException()
            }

            override fun executeAsync(
                request: HttpRequest,
                requestOptions: RequestOptions,
            ): CompletableFuture<HttpResponse> {
                throw UnsupportedOperationException()
            }

            override fun close() {}
        }

    /** Runs [block] with the static credentials set as system properties, or cleared if `null`. */
    private fun withEnvCredentials(
        apiKey: String? = ENV_API_KEY,
        authToken: String? = ENV_AUTH_TOKEN,
        block: () -> Unit,
    ) {
        val properties = mapOf("anthropic.apiKey" to apiKey, "anthropic.authToken" to authToken)
        val previous = properties.keys.associateWith { System.getProperty(it) }
        properties.forEach { (key, value) -> setOrClearProperty(key, value) }
        try {
            block()
        } finally {
            previous.forEach { (key, value) -> setOrClearProperty(key, value) }
        }
    }

    private fun builderWithEnvVariables(
        apiKey: String? = null,
        authToken: String? = null,
    ): AnthropicBackend.Builder {
        val builder = Mockito.spy(AnthropicBackend.builder())
        Mockito.doReturn(apiKey).`when`(builder).getEnv("ANTHROPIC_API_KEY")
        Mockito.doReturn(authToken).`when`(builder).getEnv("ANTHROPIC_AUTH_TOKEN")
        return builder
    }

    /** Runs [block] as if this process had not yet warned about the credentials from the env. */
    private fun withNoEarlierWarning(block: () -> Unit) {
        val previous = AnthropicBackend.warnedBothEnvCredentials.getAndSet(false)
        try {
            block()
        } finally {
            AnthropicBackend.warnedBothEnvCredentials.set(previous)
        }
    }

    private fun setOrClearProperty(key: String, value: String?) {
        if (value == null) System.clearProperty(key) else System.setProperty(key, value)
    }

    private fun createBackend(): AnthropicBackend = createBackend(API_KEY, null, null)

    /** @param baseUrl If `null`, the default production URL is assumed. */
    private fun createBackend(
        apiKey: String?,
        authToken: String?,
        baseUrl: String?,
    ): AnthropicBackend =
        AnthropicBackend.builder()
            .apiKey(apiKey)
            .authToken(authToken)
            .apply {
                if (baseUrl != null) {
                    baseUrl(baseUrl)
                }
            }
            .build()

    private fun createRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST) // A method is required.
            .baseUrl(PRODUCTION_URL)
            .build()
}
