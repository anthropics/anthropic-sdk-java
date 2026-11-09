package com.anthropic.vertex.backends

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import java.util.concurrent.CopyOnWriteArrayList
import org.assertj.core.api.AbstractThrowableAssert
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RequestPathTest {

    companion object {
        private const val BASE_URL = "https://vertex.example.com"
        private const val PROJECT = "vertex-project-12345-b6"
        private const val REGION = "us-central1"
        private const val MODEL = "claude-sonnet-4-5"

        private const val MODELS =
            "$BASE_URL/v1/projects/$PROJECT/locations/$REGION/publishers/anthropic/models"
    }

    private class RequestNotSentException : RuntimeException()

    /**
     * Records the URL and the authorization of each request as the transport resolved it and
     * [backend] authorized it.
     */
    private class RecordingBackend(private val backend: Backend) : Backend by backend {
        val urls = CopyOnWriteArrayList<String>()
        val authorizations = CopyOnWriteArrayList<List<String>>()

        override fun authorizeRequest(request: HttpRequest): HttpRequest {
            val authorizedRequest = backend.authorizeRequest(request)
            urls.add(authorizedRequest.baseUrl)
            authorizations.add(authorizedRequest.headers.values("Authorization"))
            throw RequestNotSentException()
        }
    }

    /** The base URL is set so that the region is in the path only, and not in the host too. */
    private fun createBackend(
        project: String = PROJECT,
        region: String = REGION,
        baseUrl: String = BASE_URL,
    ) =
        RecordingBackend(
            VertexBackend.builder()
                .googleCredentials(
                    GoogleCredentials.create(
                        AccessToken.newBuilder().setTokenValue("not-a-real-token").build()
                    )
                )
                .project(project)
                .region(region)
                .baseUrl(baseUrl)
                .build()
        )

    /** The path of [countTokens] holds no model. */
    private fun assertEachCallThrows(
        backend: Backend,
        model: String = MODEL,
        countTokens: Boolean = true,
        headers: Map<String, List<String>> = emptyMap(),
        thrown: (AbstractThrowableAssert<*, *>) -> Unit,
    ) {
        val client =
            AnthropicOkHttpClient.builder()
                .backend(backend)
                .maxRetries(0)
                .putAllHeaders(headers)
                .build()
        val createParams =
            MessageCreateParams.builder().model(model).maxTokens(1).addUserMessage("hello").build()
        val countTokensParams =
            MessageCountTokensParams.builder().model(model).addUserMessage("hello").build()
        val calls =
            mutableListOf<() -> Unit>(
                { client.messages().create(createParams) },
                { client.messages().createStreaming(createParams) },
            )
        if (countTokens) {
            calls += { client.messages().countTokens(countTokensParams) }
        }

        try {
            assertThat(calls).allSatisfy { thrown(assertThatThrownBy { it() }) }
        } finally {
            client.close()
        }
    }

    @ParameterizedTest
    @CsvSource(
        "a;b, a%3Bb",
        "a;, a%3B",
        "a;b;c, a%3Bb%3Bc",
        "...;x, ...%3Bx",
        "..x;y, ..x%3By",
        "x;.., x%3B..",
        "m:otherVerb;x, m:otherVerb%3Bx",
        "a%3Bb, a%253Bb",
        ".., ..",
        "., .",
        "../../x, ..%2F..%2Fx",
        "claude-sonnet-4-5@20250929, claude-sonnet-4-5@20250929",
    )
    fun model_isInOnePathSegmentWithTheMethodNameAndNoSemicolon(model: String, encoded: String) {
        val backend = createBackend()

        assertEachCallThrows(backend, model) {
            it.isExactlyInstanceOf(RequestNotSentException::class.java)
        }

        assertThat(backend.urls)
            .containsExactly(
                "$MODELS/$encoded:rawPredict",
                "$MODELS/$encoded:streamRawPredict",
                "$MODELS/count-tokens:rawPredict",
            )
    }

    @Test
    fun model_semicolonIsEscapedWhereOtherPartsOfTheUrlKeepTheirs() {
        val backend =
            createBackend(project = "a;b", region = "c;d", baseUrl = "$BASE_URL/e;f?g=/h;i")

        assertEachCallThrows(backend, model = "j;k") {
            it.isExactlyInstanceOf(RequestNotSentException::class.java)
        }

        val models = "$BASE_URL/e;f/v1/projects/a;b/locations/c;d/publishers/anthropic/models"
        assertThat(backend.urls)
            .containsExactly(
                "$models/j%3Bk:rawPredict?g=/h;i",
                "$models/j%3Bk:streamRawPredict?g=/h;i",
                "$models/count-tokens:rawPredict?g=/h;i",
            )
    }

    @Test
    fun model_semicolonIsEscapedWhenTheRequestIsAlreadyAuthorized() {
        val backend = createBackend()
        val authorization = listOf("Bearer not-the-token-of-the-backend")

        assertEachCallThrows(
            backend,
            model = "a;b",
            countTokens = false,
            headers = mapOf("Authorization" to authorization),
        ) {
            it.isExactlyInstanceOf(RequestNotSentException::class.java)
        }

        assertThat(backend.authorizations).containsExactly(authorization, authorization)
        assertThat(backend.urls)
            .containsExactly("$MODELS/a%3Bb:rawPredict", "$MODELS/a%3Bb:streamRawPredict")
    }

    @ParameterizedTest
    @CsvSource(
        "'', `model` must be non-empty",
        ";, `pathSegment` must be non-empty",
        ";x, `pathSegment` must be non-empty",
        ".;x, Path segment \".;x:",
        "..;, Path segment \"..;:",
        "..;x, Path segment \"..;x:",
    )
    fun model_emptyOrDotSegmentBeforeSemicolon_sendsNothing(model: String, message: String) {
        val backend = createBackend()

        assertEachCallThrows(backend, model, countTokens = false) {
            it.isExactlyInstanceOf(IllegalStateException::class.java)
                .hasMessageStartingWith(message)
        }

        assertThat(backend.urls).isEmpty()
    }

    @ParameterizedTest
    @CsvSource("a;b, a;b", "a;, a;", "...;x, ...;x", "x;.., x;..", "a%3Bb, a%253Bb", "../x, ..%2Fx")
    fun projectAndRegion_areOnePathSegmentEachThatKeepsSemicolons(value: String, encoded: String) {
        val backend = createBackend(project = value, region = value)
        val models = "$BASE_URL/v1/projects/$encoded/locations/$encoded/publishers/anthropic/models"

        assertEachCallThrows(backend) {
            it.isExactlyInstanceOf(RequestNotSentException::class.java)
        }

        assertThat(backend.urls)
            .containsExactly(
                "$models/$MODEL:rawPredict",
                "$models/$MODEL:streamRawPredict",
                "$models/count-tokens:rawPredict",
            )
    }

    @ParameterizedTest
    @CsvSource(
        ";x, `pathSegment` must be non-empty",
        ".;x, Path segment \".;x\" is a dot-segment",
        "..;x, Path segment \"..;x\" is a dot-segment",
    )
    fun projectOrRegion_emptyOrDotSegmentBeforeSemicolon_sendsNothing(
        value: String,
        message: String,
    ) {
        val backends = listOf(createBackend(project = value), createBackend(region = value))

        assertThat(backends).allSatisfy { backend ->
            assertEachCallThrows(backend) {
                it.isExactlyInstanceOf(IllegalStateException::class.java)
                    .hasMessageStartingWith(message)
            }
            assertThat(backend.urls).isEmpty()
        }
    }
}
