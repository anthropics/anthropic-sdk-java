package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.models.messages.MessageCreateParams
import java.util.concurrent.CopyOnWriteArrayList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

internal class RequestPathTest {

    companion object {
        private const val HOST = "https://bedrock-runtime.us-east-1.amazonaws.com"
    }

    private class RequestNotSentException : RuntimeException()

    /** Records the URL of each request as the transport resolved it and [backend] signed it. */
    private class RecordingBackend(private val backend: Backend) : Backend by backend {
        val urls = CopyOnWriteArrayList<String>()

        override fun authorizeRequest(request: HttpRequest): HttpRequest {
            urls.add(backend.authorizeRequest(request).baseUrl)
            throw RequestNotSentException()
        }
    }

    private val backend =
        RecordingBackend(
            BedrockBackend.builder()
                .awsCredentials(
                    AwsBasicCredentials.create(
                        "AKIAIOSFODNN7EXAMPLE",
                        "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
                    )
                )
                .region(Region.US_EAST_1)
                .build()
        )

    private val client = AnthropicOkHttpClient.builder().backend(backend).maxRetries(0).build()

    @AfterEach fun closeClient() = client.close()

    private fun params(model: String): MessageCreateParams =
        MessageCreateParams.builder().model(model).maxTokens(1).addUserMessage("hello").build()

    @ParameterizedTest
    @CsvSource("a;b, a;b", "a;, a;", "...;x, ...;x", "x;.., x;..", "a%3Bb, a%253Bb", "../x, ..%2Fx")
    fun model_isOnePathSegmentThatKeepsSemicolons(model: String, encoded: String) {
        assertThatThrownBy { client.messages().create(params(model)) }
            .isExactlyInstanceOf(RequestNotSentException::class.java)
        assertThatThrownBy { client.messages().createStreaming(params(model)) }
            .isExactlyInstanceOf(RequestNotSentException::class.java)

        assertThat(backend.urls)
            .containsExactly(
                "$HOST/model/$encoded/invoke",
                "$HOST/model/$encoded/invoke-with-response-stream",
            )
    }

    @ParameterizedTest
    @CsvSource(
        ";x, `pathSegment` must be non-empty",
        ".;x, Path segment \".;x\" is a dot-segment",
        "..;x, Path segment \"..;x\" is a dot-segment",
    )
    fun model_emptyOrDotSegmentBeforeSemicolon_sendsNothing(model: String, message: String) {
        assertThatThrownBy { client.messages().create(params(model)) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith(message)
        assertThatThrownBy { client.messages().createStreaming(params(model)) }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith(message)

        assertThat(backend.urls).isEmpty()
    }
}
