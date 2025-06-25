package com.anthropic.vertex.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.bodyToJson
import com.anthropic.core.http.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.node.ObjectNode
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@ResourceLock("environment")
internal class VertexBackendTest {
    companion object {
        // This is *not* a real token.
        private const val ACCESS_TOKEN = "bXad1rxDyYfoRm8adi66C6dgX6x8eIp2Yx9y2VK3"
        // This is *not* a real ID.
        private const val GC_PROJECT = "vertex-project-12345-b6"
        private const val MODEL_ID = "claude-3-sonnet"
        private const val GC_REGION = "us-central1"
        private const val ANTHROPIC_VERSION = "vertex-2023-10-16"
    }

    @Test
    fun builderMissingEverything() {
        assertThatThrownBy { VertexBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun builderMissingCredentials() {
        assertThatThrownBy { VertexBackend.builder().region(GC_REGION).project(GC_PROJECT).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("googleCredentials")
    }

    @Test
    fun builderMissingRegion() {
        assertThatThrownBy {
                VertexBackend.builder()
                    .googleCredentials(createCredentials())
                    .project(GC_PROJECT)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("region")
    }

    @Test
    fun builderMissingProject() {
        assertThatThrownBy {
                VertexBackend.builder()
                    .googleCredentials(createCredentials())
                    .region(GC_REGION)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("project")
    }

    @Test
    fun baseUrl() {
        val backend = createBackend()

        assertThat(backend.baseUrl()).isEqualTo("https://$GC_REGION-aiplatform.googleapis.com")
    }

    @Test
    fun baseUrlOtherRegion() {
        // Try with a *different* region to confirm that it is not hard-coded.
        val backend1 = createBackend()
        val otherRegion = "europe-west1"
        val backend2 = createBackend(backend1.googleCredentials, otherRegion, backend1.project)

        assertThat(GC_REGION).isNotEqualTo(otherRegion)
        assertThat(backend2.baseUrl()).isEqualTo("https://$otherRegion-aiplatform.googleapis.com")
    }

    @Test
    fun baseUrlGlobalRegion() {
        val backend1 = createBackend()
        val globalBackend = createBackend(backend1.googleCredentials, "global", backend1.project)

        assertThat(globalBackend.baseUrl()).isEqualTo("https://aiplatform.googleapis.com")
    }

    @Test
    fun prepareRequestMissingJSON() {
        val backend = createBackend()
        val request = createRequest(null, "v1", "messages")

        assertThat(request.body).isNull()
        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Request has no body")
    }

    @Test
    fun prepareRequestEmptyJSON() {
        val backend = createBackend()
        val request = createRequest("{}", "v1", "messages")

        assertThat(request.body).isNotNull()
        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("No model found in body")
    }

    @Test
    fun prepareRequestNoPathSegments() {
        val backend = createBackend()
        // Request does not contain any path segments.
        val request = createRequest("""{"model":"$MODEL_ID"}""")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")
    }

    @Test
    fun prepareRequestMissingV1() {
        val backend = createBackend()
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
        val backend = createBackend()
        // Request does not contain a "messages" or "complete" path segment.
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Missing service name")
    }

    @Test
    fun prepareRequestUnsupportedServiceName() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "not-messages")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Service is not supported for Vertex: not-messages")
    }

    @Test
    fun prepareRequestMessagesBatchesNotSupported() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages", "batches")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Batch API is not supported")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)

        assertThatThrownBy { backend.prepareRequest(preparedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared for Vertex")
    }

    @Test
    fun prepareRequestMessagesCountTokens() {
        val backend = createBackend()
        // Streaming option should be ignored (i.e., later we should see
        // "rawPredict", not "streamRawPredict" in the path segments).
        val request =
            createRequest(
                """{"model":"$MODEL_ID", "stream":true}""",
                "v1",
                "messages",
                "count_tokens",
            )
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("count-tokens:rawPredict")

        // The implementation (like the Python SDK) does not remove the "model"
        // or "stream" properties from the JSON body if "count_tokens" is
        // requested, but it still adds the "anthropic_version" property.
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()
        assertThat(json!!.get("model").asText()).isEqualTo(MODEL_ID)
        assertThat(json.get("stream").asBoolean()).isEqualTo(true)
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestMessages() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("$MODEL_ID:rawPredict")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added. "stream" was never there.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestMessagesStreaming() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID", "stream":true}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("$MODEL_ID:streamRawPredict")

        // In the JSON body, "model" should be removed, "stream" should be
        // retained and "anthropic_version" should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream").asBoolean()).isEqualTo(true)
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestMessagesStreamingExplicitlyDisabled() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID","stream":false}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("$MODEL_ID:rawPredict")

        // In the JSON body, "model" should be removed, "stream" should be
        // retained and "anthropic_version" should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream").asBoolean()).isEqualTo(false)
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestComplete() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "complete")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("$MODEL_ID:rawPredict")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added. "stream" was never there.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun prepareRequestCompleteStreaming() {
        val backend = createBackend()
        val request = createRequest("""{"model":"$MODEL_ID","stream":true}""", "v1", "complete")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(pathSegments.size).isEqualTo(9)
        assertThat(pathSegments[0]).isEqualTo("v1")
        assertThat(pathSegments[1]).isEqualTo("projects")
        assertThat(pathSegments[2]).isEqualTo(GC_PROJECT)
        assertThat(pathSegments[3]).isEqualTo("locations")
        assertThat(pathSegments[4]).isEqualTo(GC_REGION)
        assertThat(pathSegments[5]).isEqualTo("publishers")
        assertThat(pathSegments[6]).isEqualTo("anthropic")
        assertThat(pathSegments[7]).isEqualTo("models")
        assertThat(pathSegments[8]).isEqualTo("$MODEL_ID:streamRawPredict")

        // In the JSON body, "model" should be removed, "stream" should be
        // retained and "anthropic_version" should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream").asBoolean()).isEqualTo(true)
        assertThat(json.get("anthropic_version").asText()).isEqualTo(ANTHROPIC_VERSION)
    }

    @Test
    fun authorizeRequestAlreadyAuthorized() {
        val backend = createBackend()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://$GC_REGION-aiplatform.googleapis.com/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThatThrownBy { backend.authorizeRequest(authorizedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Request already authorized for Vertex.")
    }

    @Test
    fun authorizeRequest() {
        val backend = createBackend()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://$GC_REGION-aiplatform.googleapis.com/path1/path2")
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

        // Check that the authorized request contains all the same elements that
        // were in the original request plus the new authorization header.
        assertThat(authorizedRequest.method).isEqualTo(request.method)
        assertThat(authorizedRequest.baseUrl).isEqualTo(request.baseUrl)
        assertThat(authorizedRequest.body).isEqualTo(request.body)

        assertThat(authorizedRequest.pathSegments.size).isEqualTo(2)
        assertThat(authorizedRequest.pathSegments[0]).isEqualTo("path-1")
        assertThat(authorizedRequest.pathSegments[1]).isEqualTo("path-2")

        // There is an assumption here that query parameters with multiple
        // values store those values in order of insertion. That assumption
        // holds for the current implementation.
        assertThat(authorizedRequest.queryParams.keys().size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1").size).isEqualTo(2)
        assertThat(authorizedRequest.queryParams.values("param-1")[0]).isEqualTo("param-value-1a")
        assertThat(authorizedRequest.queryParams.values("param-1")[1]).isEqualTo("param-value-1b")
        assertThat(authorizedRequest.queryParams.values("param-2").size).isEqualTo(1)
        assertThat(authorizedRequest.queryParams.values("param-2")[0]).isEqualTo("param-value-2")

        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0]).isEqualTo("on/request")

        // Check that headers with more than one value were properly preserved.
        // There is an assumption here that headers with multiple values store
        // those values in order of insertion. That assumption holds for the
        // current implementation.
        assertThat(headers.names()).contains("X-Test")
        assertThat(headers.values("X-Test").size).isEqualTo(2)
        assertThat(headers.values("X-Test")[0]).isEqualTo("header-value-a")
        assertThat(headers.values("X-Test")[1]).isEqualTo("header-value-b")

        // Check that the critical "Authorization" header was added and that its
        // "Bearer" value is the "accessToken" value captured in the backend's
        // Google credentials.
        assertThat(headers.names()).contains("Authorization")
        assertThat(headers.values("Authorization").size).isEqualTo(1)
        assertThat(headers.values("Authorization")[0]).isEqualTo("Bearer $ACCESS_TOKEN")
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

    private fun createBackend(): VertexBackend =
        createBackend(createCredentials(), GC_REGION, GC_PROJECT)

    private fun createBackend(
        googleCredentials: GoogleCredentials,
        region: String,
        project: String,
    ): VertexBackend =
        VertexBackend.builder()
            .googleCredentials(googleCredentials)
            .region(region)
            .project(project)
            .build()

    private fun createCredentials(): GoogleCredentials =
        GoogleCredentials.create(AccessToken.newBuilder().setTokenValue(ACCESS_TOKEN).build())
}
