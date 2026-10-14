package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.models.messages.MessageCreateParams
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.baggage.Baggage
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanContext
import io.opentelemetry.api.trace.TraceFlags
import io.opentelemetry.api.trace.TraceState
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.Context
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.context.propagation.TextMapGetter
import io.opentelemetry.context.propagation.TextMapPropagator
import io.opentelemetry.context.propagation.TextMapSetter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

/**
 * The client sends the trace context headers with a signed request, and the signature leaves them
 * out: an HTTP client instrumentation may replace them after the request is signed, and a signature
 * that covered them would then no longer match.
 */
@ResourceLock("GlobalOpenTelemetry")
internal class TraceContextSigningTest {

    companion object {
        private const val TRACE_ID = "0af7651916cd43dd8448eb211c80319c"
        private const val SPAN_ID = "b7ad6b7169203331"
        private val CREDENTIALS =
            AwsBasicCredentials.create(
                "AKIAIOSFODNN7EXAMPLE",
                "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
            )
    }

    private class Captured : RuntimeException()

    /** Keeps the request that [backend] authorized, and fails the call before it is sent. */
    private class CapturingBackend(private val backend: Backend) : Backend by backend {
        var authorized: HttpRequest? = null

        override fun authorizeRequest(request: HttpRequest): HttpRequest {
            authorized = backend.authorizeRequest(request)
            throw Captured()
        }
    }

    /** A propagator of the application's own, with a header that no standard names. */
    private object CustomPropagator : TextMapPropagator {
        override fun fields(): Collection<String> = listOf("X-Custom-Trace")

        override fun <C : Any?> inject(context: Context, carrier: C?, setter: TextMapSetter<C>) =
            setter.set(carrier, "X-Custom-Trace", "custom-value")

        override fun <C : Any?> extract(
            context: Context,
            carrier: C?,
            getter: TextMapGetter<C>,
        ): Context = context
    }

    @BeforeEach
    fun setUp() {
        GlobalOpenTelemetry.resetForTest()
    }

    @AfterEach
    fun tearDown() {
        GlobalOpenTelemetry.resetForTest()
    }

    private fun bedrock(): Backend =
        BedrockBackend.builder().awsCredentials(CREDENTIALS).region(Region.US_EAST_1).build()

    private fun mantle(): Backend =
        BedrockMantleBackend.builder().awsCredentials(CREDENTIALS).region(Region.US_EAST_1).build()

    /**
     * The request that a client of [backend] signs for a Messages call made inside a sampled span
     * with a trace state and baggage, with [propagators] registered.
     */
    private fun signedRequest(
        backend: Backend,
        vararg propagators: TextMapPropagator,
    ): HttpRequest {
        GlobalOpenTelemetry.set(
            OpenTelemetry.propagating(
                ContextPropagators.create(TextMapPropagator.composite(*propagators))
            )
        )
        val capturing = CapturingBackend(backend)
        val client = AnthropicOkHttpClient.builder().backend(capturing).maxRetries(0).build()
        val parent =
            Span.wrap(
                SpanContext.create(
                    TRACE_ID,
                    SPAN_ID,
                    TraceFlags.getSampled(),
                    TraceState.builder().put("vendor", "value").build(),
                )
            )
        val context =
            Context.root().with(parent).with(Baggage.builder().put("tenant", "acme").build())
        try {
            context.makeCurrent().use {
                client
                    .messages()
                    .create(
                        MessageCreateParams.builder()
                            .model("anthropic.claude-3-5-sonnet-20240620-v1:0")
                            .maxTokens(1)
                            .addUserMessage("hello")
                            .build()
                    )
            }
        } catch (e: Captured) {
            // The request was signed, which is all that the test needs.
        } finally {
            client.close()
        }
        return capturing.authorized!!
    }

    /** The lower-case names that the `SignedHeaders` of [request]'s signature lists. */
    private fun signedHeaders(request: HttpRequest): List<String> =
        request.headers
            .values("Authorization")
            .single()
            .substringAfter("SignedHeaders=")
            .substringBefore(",")
            .split(";")

    private fun assertTraceContextSentUnsigned(backend: Backend) {
        val request =
            signedRequest(
                backend,
                W3CTraceContextPropagator.getInstance(),
                W3CBaggagePropagator.getInstance(),
            )

        assertThat(request.headers.values("traceparent"))
            .containsExactly("00-$TRACE_ID-$SPAN_ID-01")
        assertThat(request.headers.values("tracestate")).containsExactly("vendor=value")
        assertThat(request.headers.values("baggage")).containsExactly("tenant=acme")
        assertThat(signedHeaders(request))
            .contains("host", "content-type")
            .doesNotContain("traceparent", "tracestate", "baggage")
    }

    private fun assertPropagatorFieldsSentUnsigned(backend: Backend) {
        val request =
            signedRequest(backend, W3CTraceContextPropagator.getInstance(), CustomPropagator)

        assertThat(request.headers.values("X-Custom-Trace")).containsExactly("custom-value")
        assertThat(request.headers.values("traceparent")).hasSize(1)
        assertThat(signedHeaders(request))
            .contains("host", "content-type")
            .doesNotContain("x-custom-trace", "traceparent")
    }

    @Test fun bedrock_sendsTraceContextHeadersUnsigned() = assertTraceContextSentUnsigned(bedrock())

    @Test
    fun bedrock_sendsPropagatorFieldsUnsigned() = assertPropagatorFieldsSentUnsigned(bedrock())

    @Test
    fun bedrockMantle_sendsTraceContextHeadersUnsigned() = assertTraceContextSentUnsigned(mantle())

    @Test
    fun bedrockMantle_sendsPropagatorFieldsUnsigned() = assertPropagatorFieldsSentUnsigned(mantle())
}
