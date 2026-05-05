package com.anthropic.backends

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
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.Optional
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class AnthropicBackendTest {
    companion object {
        private const val PRODUCTION_URL = "https://api.anthropic.com"
        private const val OTHER_URL = "https://api.example.com"
        private const val API_KEY = "test-api-key-123456789"
        private const val AUTH_TOKEN = "test-auth-token-987654321"
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
    fun prepareRequest() {
        val backend = createBackend()
        val request = createRequest()
        val headersCount = request.headers.names().size
        val preparedRequest = backend.prepareRequest(request)

        assertThat(request.headers.values("anthropic-version")).isEmpty()

        assertThat(preparedRequest.headers.names().size).isEqualTo(headersCount + 1)
        assertThat(preparedRequest.headers.values("anthropic-version").size).isEqualTo(1)
        assertThat(preparedRequest.headers.values("anthropic-version")[0]).isEqualTo("2023-06-01")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        val backend = createBackend()
        val preparedRequest = backend.prepareRequest(createRequest())

        assertThatThrownBy { backend.prepareRequest(preparedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared")
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
        assertThat(authorizedRequest.headers.values("X-Api-Key").size).isEqualTo(1)
        assertThat(authorizedRequest.headers.values("X-Api-Key")[0]).isEqualTo(API_KEY)
        assertThat(authorizedRequest.headers.values("Authorization")).isEmpty()
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

    @Test
    fun builderApiKeyAndAuthTokenBothSet() {
        // There is expected to be no enforcement of the mutual exclusion of the
        // two incompatible credential values.
        assertThatNoException().isThrownBy { createBackend(API_KEY, AUTH_TOKEN, null) }
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
        val backend = createBackend(API_KEY, AUTH_TOKEN, null)

        assertThat(backend.apiKey).isEqualTo(API_KEY)
        assertThat(backend.authToken).isEqualTo(AUTH_TOKEN)
    }

    @Test
    fun builderCredentialsFromOptionals() {
        // Check that if the "(...: Optional<String>)" setters are called, that
        // they set their respective fields and do not get their wires crossed.
        val backend =
            AnthropicBackend.builder()
                .apiKey(Optional.ofNullable(API_KEY))
                .authToken(Optional.ofNullable(AUTH_TOKEN))
                .build()

        assertThat(backend.apiKey).isEqualTo(API_KEY)
        assertThat(backend.authToken).isEqualTo(AUTH_TOKEN)
    }

    @Test
    fun builderCredentialsFromOptionalsWithNulls() {
        val backend =
            AnthropicBackend.builder()
                .apiKey(Optional.ofNullable(null))
                .authToken(Optional.ofNullable(null))
                .build()

        assertThat(backend.apiKey).isNull()
        assertThat(backend.authToken).isNull()
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
                    request.body?.writeTo(out)
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
            .build()
}
