package com.anthropic.foundry.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.mockito.Mockito

@ResourceLock("environment")
internal class FoundryBackendTest {
    companion object {
        // This is *not* a real API key.
        private const val MODEL_ID = "claude-45-sonnet"
        private const val API_KEY = "AKIAIOSFODNN7EXAMPLE"
        private const val TOKEN = "my-token"
        private const val RESOURCE = "my-resource"
        private const val CUSTOM_BASE_URL = "https://resource.example.com"
        private const val ANTHROPIC_VERSION = "2023-06-01"

        private const val ENV_API_KEY = "ANTHROPIC_FOUNDRY_API_KEY"
        private const val ENV_RESOURCE = "ANTHROPIC_FOUNDRY_RESOURCE"
        private const val ENV_BASE_URL = "ANTHROPIC_FOUNDRY_BASE_URL"

        private val TOKEN_SUPPLIER: Supplier<String> = Supplier { TOKEN }
    }

    @Test
    fun builderMissingEverything() {
        assertThatThrownBy { FoundryBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun builderMissingApiKeyAndBearerTokenSupplier() {
        assertThatThrownBy { FoundryBackend.builder().resource(RESOURCE).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("`apiKey` or `bearerTokenSupplier` must be set.")
    }

    @Test
    fun builderMissingResourceAndBaseUrl() {
        assertThatThrownBy { FoundryBackend.builder().apiKey(API_KEY).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("`resource` or `baseUrl` must be set.")
    }

    @Test
    fun builderApiKeyAndTokenSupplierMutuallyExclusive() {
        assertThatThrownBy {
                FoundryBackend.builder()
                    .apiKey(API_KEY)
                    .bearerTokenSupplier(TOKEN_SUPPLIER)
                    .resource(RESOURCE)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("`apiKey` and `bearerTokenSupplier` cannot both be set.")
    }

    @Test
    fun builderResourceAndBaseUrlMutuallyExclusive() {
        assertThatThrownBy {
                FoundryBackend.builder()
                    .apiKey(API_KEY)
                    .resource(RESOURCE)
                    .baseUrl(CUSTOM_BASE_URL)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("`resource` and `baseUrl` cannot both be set.")
    }

    @Test
    fun apiKeyExplicit() {
        val backend = createBackendWithApiKeyAndResource()

        assertThat(backend.apiKey).isEqualTo(API_KEY)
    }

    @Test
    fun bearerTokenSupplierExplicit() {
        val backend = createBackendWithTokenSupplierAndBaseUrl()

        assertThat(backend.bearerTokenSupplier).isNotNull
        assertThat(backend.bearerTokenSupplier!!.get()).isEqualTo(TOKEN)
    }

    @Test
    fun resourceInBaseUrl() {
        val backend = createBackendWithTokenSupplierAndResource()

        assertThat(backend.baseUrl()).isEqualTo("https://$RESOURCE.services.ai.azure.com")
    }

    @Test
    fun baseUrlCustom() {
        val backend = createBackendWithApiKeyAndBaseUrl()

        assertThat(backend.baseUrl()).isEqualTo(CUSTOM_BASE_URL)
    }

    @Test
    fun fromEnvMissingApiKey() {
        assertThatThrownBy { mockBackendBuilder().withResourceEnvVar().fromEnv() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining(ENV_API_KEY)
    }

    @Test
    fun fromEnvWithApiKey() {
        val backend = mockBackendBuilder().withApiKeyEnvVar().withResourceEnvVar().fromEnv().build()

        assertThat(backend.apiKey).isEqualTo(API_KEY)
    }

    @Test
    fun fromEnvMissingResourceAndBaseUrl() {
        assertThatThrownBy { mockBackendBuilder().withApiKeyEnvVar().fromEnv() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("$ENV_RESOURCE and $ENV_BASE_URL")
    }

    @Test
    fun fromEnvWithBothResourceAndBaseUrl() {
        assertThatThrownBy {
                mockBackendBuilder()
                    .withApiKeyEnvVar()
                    .withResourceEnvVar()
                    .withBaseUrlEnvVar()
                    .fromEnv()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("$ENV_RESOURCE and $ENV_BASE_URL")
    }

    @Test
    fun fromEnvWithResource() {
        val backend = mockBackendBuilder().withApiKeyEnvVar().withResourceEnvVar().fromEnv().build()

        assertThat(backend.resource).isEqualTo(RESOURCE)
    }

    @Test
    fun fromEnvWithBaseUrl() {
        val backend = mockBackendBuilder().withApiKeyEnvVar().withBaseUrlEnvVar().fromEnv().build()

        assertThat(backend.baseUrl).isEqualTo(CUSTOM_BASE_URL)
    }

    @Test
    fun prepareRequestNoPathSegments() {
        val backend = createBackendWithApiKeyAndResource()
        // Request does not contain any path segments.
        val request = createRequest("""{"model":"$MODEL_ID"}""")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Request missing all path segments")
    }

    @Test
    fun prepareRequestMissingV1() {
        val backend = createBackendWithApiKeyAndResource()
        // Request does not contain a "v1" path segment.
        val request1 = createRequest("""{"model":"$MODEL_ID"}""", "d1", "messages")

        assertThatThrownBy { backend.prepareRequest(request1) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")

        // Request contains a "v1" path segment, but not in first place.
        val request2 = createRequest("""{"model":"$MODEL_ID"}""", "messages", "v1")

        assertThatThrownBy { backend.prepareRequest(request2) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")
    }

    @Test
    fun prepareRequestMissingServiceName() {
        val backend = createBackendWithApiKeyAndResource()
        // Request does not contain a "messages" or other service path segment.
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Missing service name")
    }

    @Test
    fun prepareRequestUnsupportedServiceName() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "not-messages")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Service is not supported for Foundry: not-messages")
    }

    @Test
    fun prepareRequestCompletionsAPIUnsupported() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "complete")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Service is not supported for Foundry: complete")
    }

    @Test
    fun prepareRequestMessagesBatchesNotSupported() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages", "batches")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Batch API is not supported")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)

        assertThatThrownBy { backend.prepareRequest(preparedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared for Foundry")
    }

    @Test
    fun prepareRequestMessagesCountTokens() {
        val backend = createBackendWithApiKeyAndResource()
        // Model and streaming option should be ignored.
        val request =
            createRequest(
                """{"model":"$MODEL_ID", "stream":true}""",
                "v1",
                "messages",
                "count_tokens",
            )
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        assertThat(pathSegments.size).isEqualTo(4)
        assertThat(pathSegments[0]).isEqualTo("anthropic")
        assertThat(pathSegments[1]).isEqualTo("v1")
        assertThat(pathSegments[2]).isEqualTo("messages")
        assertThat(pathSegments[3]).isEqualTo("count_tokens")

        assertThat(preparedRequest.headers.names()).contains("anthropic-version")
        assertThat(preparedRequest.headers.values("anthropic-version")[0])
            .isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestMessages() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("anthropic")
        assertThat(pathSegments[1]).isEqualTo("v1")
        assertThat(pathSegments[2]).isEqualTo("messages")

        assertThat(preparedRequest.headers.names()).contains("anthropic-version")
        assertThat(preparedRequest.headers.values("anthropic-version")[0])
            .isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestSkills() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "skills")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("anthropic")
        assertThat(pathSegments[1]).isEqualTo("v1")
        assertThat(pathSegments[2]).isEqualTo("skills")
    }

    @Test
    fun prepareRequestFiles() {
        val backend = createBackendWithApiKeyAndResource()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "files")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("anthropic")
        assertThat(pathSegments[1]).isEqualTo("v1")
        assertThat(pathSegments[2]).isEqualTo("files")
    }

    @Test
    fun authorizeRequestAlreadyAuthorizedWithApiKey() {
        val backend = createBackendWithApiKeyAndResource()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("$CUSTOM_BASE_URL/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThatThrownBy { backend.authorizeRequest(authorizedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Request already authorized for Foundry.")
    }

    @Test
    fun authorizeRequestAlreadyAuthorizedWithBearerToken() {
        val backend = createBackendWithTokenSupplierAndResource()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("$CUSTOM_BASE_URL/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThatThrownBy { backend.authorizeRequest(authorizedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Request already authorized for Foundry.")
    }

    @Test
    fun authorizeRequestApiKey() {
        val backend = createBackendWithApiKeyAndResource()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("$CUSTOM_BASE_URL/path1/path2")
                .addPathSegment("path-1")
                .addPathSegment("path-2")
                .putQueryParam("param-1", "param-value-1a")
                .putQueryParam("param-1", "param-value-1b")
                .putQueryParam("param-2", "param-value-2")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value-a")
                .putHeader("X-Test", "header-value-b")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)
        val headers = authorizedRequest.headers

        // Check that the authorized request contains all the same elements that were in the
        // original request plus the new authorization header.
        assertThat(authorizedRequest.method).isEqualTo(request.method)
        assertThat(authorizedRequest.baseUrl).isEqualTo(request.baseUrl)
        assertThat(authorizedRequest.body).isEqualTo(request.body)

        assertThat(authorizedRequest.pathSegments.size).isEqualTo(2)
        assertThat(authorizedRequest.pathSegments[0]).isEqualTo("path-1")
        assertThat(authorizedRequest.pathSegments[1]).isEqualTo("path-2")

        // There is an assumption here that query parameters with multiple values store those values
        // in order of insertion. That assumption holds for the current implementation.
        assertThat(authorizedRequest.queryParams.keys().size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1").size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1")[0]).isEqualTo("param-value-1a")
        assertThat(authorizedRequest.queryParams.values("param-1")[1]).isEqualTo("param-value-1b")
        assertThat(authorizedRequest.queryParams.values("param-2").size).isEqualTo(1)
        assertThat(authorizedRequest.queryParams.values("param-2")[0]).isEqualTo("param-value-2")

        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0]).isEqualTo("on/request")

        // Check that headers with more than one value were properly preserved. There is an
        // assumption here that headers with multiple values store those values in order of
        // insertion. That assumption holds for the current implementation.
        assertThat(headers.names()).contains("X-Test")
        assertThat(headers.values("X-Test").size).isEqualTo(2)
        assertThat(headers.values("X-Test")[0]).isEqualTo("header-value-a")
        assertThat(headers.values("X-Test")[1]).isEqualTo("header-value-b")

        // Check that the critical "x-api-key" header was added and that its value is the API key.
        assertThat(headers.names()).contains("x-api-key")
        assertThat(headers.values("x-api-key").size).isEqualTo(1)
        assertThat(headers.values("x-api-key")[0]).isEqualTo(API_KEY)
    }

    @Test
    fun authorizeRequestBearerTokenSupplier() {
        val backend = createBackendWithTokenSupplierAndResource()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("$CUSTOM_BASE_URL/path1/path2")
                .addPathSegment("path-1")
                .addPathSegment("path-2")
                .putQueryParam("param-1", "param-value-1a")
                .putQueryParam("param-1", "param-value-1b")
                .putQueryParam("param-2", "param-value-2")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value-a")
                .putHeader("X-Test", "header-value-b")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)
        val headers = authorizedRequest.headers

        // Check that the authorized request contains all the same elements that were in the
        // original request plus the new authorization header.
        assertThat(authorizedRequest.method).isEqualTo(request.method)
        assertThat(authorizedRequest.baseUrl).isEqualTo(request.baseUrl)
        assertThat(authorizedRequest.body).isEqualTo(request.body)

        assertThat(authorizedRequest.pathSegments.size).isEqualTo(2)
        assertThat(authorizedRequest.pathSegments[0]).isEqualTo("path-1")
        assertThat(authorizedRequest.pathSegments[1]).isEqualTo("path-2")

        // There is an assumption here that query parameters with multiple values store those values
        // in order of insertion. That assumption holds for the current implementation.
        assertThat(authorizedRequest.queryParams.keys().size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1").size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1")[0]).isEqualTo("param-value-1a")
        assertThat(authorizedRequest.queryParams.values("param-1")[1]).isEqualTo("param-value-1b")
        assertThat(authorizedRequest.queryParams.values("param-2").size).isEqualTo(1)
        assertThat(authorizedRequest.queryParams.values("param-2")[0]).isEqualTo("param-value-2")

        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0]).isEqualTo("on/request")

        // Check that headers with more than one value were properly preserved. There is an
        // assumption here that headers with multiple values store those values in order of
        // insertion. That assumption holds for the current implementation.
        assertThat(headers.names()).contains("X-Test")
        assertThat(headers.values("X-Test").size).isEqualTo(2)
        assertThat(headers.values("X-Test")[0]).isEqualTo("header-value-a")
        assertThat(headers.values("X-Test")[1]).isEqualTo("header-value-b")

        // Check that the critical "Authorization" header was added and that its "Bearer" value is
        // the value from the bearer token supplier.
        assertThat(headers.names()).contains("Authorization")
        assertThat(headers.values("Authorization").size).isEqualTo(1)
        assertThat(headers.values("Authorization")[0]).isEqualTo("Bearer $TOKEN")
    }

    private fun parseJson(jsonData: String): ObjectNode =
        jsonMapper().readValue(jsonData, ObjectNode::class.java)

    /**
     * @param jsonData The JSON data to add to the body of request. If `null` a body will not be
     *   added to the request. If not `null`, the data must represent a valid JSON model, even a
     *   minimal `{}`, or an error will occur.
     */
    private fun createRequest(jsonData: String?, vararg pathSegments: String): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST) // A method is required.
            .addPathSegments(*pathSegments)
            .apply { jsonData?.let { body(json(jsonMapper(), parseJson(it))) } }
            .build()

    private fun createBackendWithApiKeyAndResource(): FoundryBackend =
        createBackend(apiKey = API_KEY, resource = RESOURCE)

    private fun createBackendWithApiKeyAndBaseUrl(): FoundryBackend =
        createBackend(apiKey = API_KEY, baseUrl = CUSTOM_BASE_URL)

    private fun createBackendWithTokenSupplierAndResource(): FoundryBackend =
        createBackend(bearerTokenSupplier = TOKEN_SUPPLIER, resource = RESOURCE)

    private fun createBackendWithTokenSupplierAndBaseUrl(): FoundryBackend =
        createBackend(bearerTokenSupplier = TOKEN_SUPPLIER, baseUrl = CUSTOM_BASE_URL)

    private inline fun <T, R> R.applyIfNotNull(value: T?, block: R.(T) -> R): R =
        if (value != null) block(value) else this

    private fun createBackend(
        apiKey: String? = null,
        bearerTokenSupplier: Supplier<String>? = null,
        resource: String? = null,
        baseUrl: String? = null,
    ): FoundryBackend =
        FoundryBackend.builder()
            .applyIfNotNull(apiKey) { apiKey(it) }
            .applyIfNotNull(bearerTokenSupplier) { bearerTokenSupplier(it) }
            .applyIfNotNull(resource) { resource(it) }
            .applyIfNotNull(baseUrl) { baseUrl(it) }
            .build()

    private fun mockBackendBuilder(): FoundryBackend.Builder = Mockito.spy(FoundryBackend.builder())

    private fun FoundryBackend.Builder.withApiKeyEnvVar(apiKey: String = API_KEY) = apply {
        Mockito.doReturn(apiKey).`when`(this).getEnv(ENV_API_KEY)
    }

    private fun FoundryBackend.Builder.withResourceEnvVar(resource: String = RESOURCE) = apply {
        Mockito.doReturn(resource).`when`(this).getEnv(ENV_RESOURCE)
    }

    private fun FoundryBackend.Builder.withBaseUrlEnvVar(baseUrl: String = CUSTOM_BASE_URL) =
        apply {
            Mockito.doReturn(baseUrl).`when`(this).getEnv(ENV_BASE_URL)
        }
}
