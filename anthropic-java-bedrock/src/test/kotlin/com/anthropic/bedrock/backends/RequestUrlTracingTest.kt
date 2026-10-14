package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.models.messages.MessageCreateParams
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanBuilder
import io.opentelemetry.api.trace.SpanContext
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import io.opentelemetry.context.Context
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

/**
 * The backend rewrites the path of a request in the HTTP client, below the tracing of the API call,
 * which so records the path that the SDK built and not the one that is sent.
 */
internal class RequestUrlTracingTest {

    private class Captured : RuntimeException()

    /** Keeps the request that [backend] authorized, and fails the call before it is sent. */
    private class CapturingBackend(private val backend: Backend) : Backend by backend {
        var authorized: HttpRequest? = null

        override fun authorizeRequest(request: HttpRequest): HttpRequest {
            authorized = backend.authorizeRequest(request)
            throw Captured()
        }
    }

    /** A tracer provider whose spans all record their string attributes in [attributes]. */
    private class RecordingTracerProvider : TracerProvider {
        val attributes = ConcurrentHashMap<String, String>()

        private val span =
            object : Span by Span.getInvalid() {
                override fun isRecording(): Boolean = true

                override fun <T : Any> setAttribute(key: AttributeKey<T>, value: T?): Span {
                    (value as? String)?.let { attributes[key.key] = it }
                    return this
                }

                override fun setAllAttributes(attributes: Attributes): Span {
                    attributes.forEach { key, value ->
                        (value as? String)?.let {
                            this@RecordingTracerProvider.attributes[key.key] = it
                        }
                    }
                    return this
                }
            }

        private val builder =
            object : SpanBuilder {
                override fun setParent(context: Context): SpanBuilder = this

                override fun setNoParent(): SpanBuilder = this

                override fun addLink(spanContext: SpanContext): SpanBuilder = this

                override fun addLink(spanContext: SpanContext, attributes: Attributes) = this

                override fun setAttribute(key: String, value: String?): SpanBuilder = apply {
                    value?.let { attributes[key] = it }
                }

                override fun setAttribute(key: String, value: Long): SpanBuilder = this

                override fun setAttribute(key: String, value: Double): SpanBuilder = this

                override fun setAttribute(key: String, value: Boolean): SpanBuilder = this

                override fun <T : Any> setAttribute(key: AttributeKey<T>, value: T?): SpanBuilder =
                    apply {
                        (value as? String)?.let { attributes[key.key] = it }
                    }

                override fun setSpanKind(spanKind: SpanKind): SpanBuilder = this

                override fun setStartTimestamp(startTimestamp: Long, unit: TimeUnit) = this

                override fun startSpan(): Span = span
            }

        override fun get(instrumentationScopeName: String): Tracer = Tracer { builder }

        override fun get(instrumentationScopeName: String, version: String): Tracer = Tracer {
            builder
        }
    }

    @Test
    fun urlPath_isTheFirstPartyPathAndNotTheBedrockPathThatIsSent() {
        val backend =
            CapturingBackend(
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
        val tracerProvider = RecordingTracerProvider()
        val client =
            AnthropicOkHttpClient.builder()
                .backend(backend)
                .maxRetries(0)
                .openTelemetry(
                    OpenTelemetryOptions.builder().tracerProvider(tracerProvider).build()
                )
                .build()

        try {
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .model("anthropic.claude-3-5-sonnet-20240620-v1:0")
                        .maxTokens(1)
                        .addUserMessage("hello")
                        .build()
                )
        } catch (e: Captured) {
            // The request was built and signed, which is all that the test needs.
        } finally {
            client.close()
        }

        val host = "https://bedrock-runtime.us-east-1.amazonaws.com"
        assertThat(backend.authorized!!.baseUrl)
            .isEqualTo("$host/model/anthropic.claude-3-5-sonnet-20240620-v1:0/invoke")
        assertThat(tracerProvider.attributes)
            .containsEntry("http.route", "/v1/messages")
            .containsEntry("url.path", "/v1/messages")
            .containsEntry("url.full", "$host/v1/messages")
    }
}
