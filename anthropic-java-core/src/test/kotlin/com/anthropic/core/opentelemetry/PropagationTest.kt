package com.anthropic.core.opentelemetry

import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.Sleeper
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanBuilder
import io.opentelemetry.api.trace.SpanContext
import io.opentelemetry.api.trace.TraceFlags
import io.opentelemetry.api.trace.TraceState
import io.opentelemetry.api.trace.Tracer
import io.opentelemetry.api.trace.TracerProvider
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.Context
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.context.propagation.TextMapGetter
import io.opentelemetry.context.propagation.TextMapPropagator
import io.opentelemetry.context.propagation.TextMapSetter
import java.io.InputStream
import java.lang.reflect.Proxy
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Isolated
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

// Every client in the JVM reads the registered OpenTelemetry, which the tests replace.
@Isolated
internal class PropagationTest {

    /** The W3C propagator, counting how often the SDK asks it to inject. */
    private class CountingPropagator : TextMapPropagator {
        val injects = AtomicInteger()
        private val w3c = W3CTraceContextPropagator.getInstance()

        override fun fields(): Collection<String> = w3c.fields()

        override fun <C> inject(context: Context, carrier: C?, setter: TextMapSetter<C>) {
            injects.incrementAndGet()
            w3c.inject(context, carrier, setter)
        }

        override fun <C> extract(context: Context, carrier: C?, getter: TextMapGetter<C>): Context =
            w3c.extract(context, carrier, getter)
    }

    /** A provider whose every span is [CALL_SPAN], which does not record. */
    private object CallSpanTracerProvider : TracerProvider {
        // Every method of a span builder but startSpan returns the builder.
        private val spanBuilder =
            SPAN_BUILDER.cast(
                Proxy.newProxyInstance(SPAN_BUILDER.classLoader, arrayOf(SPAN_BUILDER)) {
                    proxy,
                    method,
                    _ ->
                    if (method.name == "startSpan") CALL_SPAN else proxy
                }
            )

        override fun get(instrumentationScopeName: String): Tracer = Tracer { spanBuilder }

        override fun get(instrumentationScopeName: String, instrumentationScopeVersion: String) =
            get(instrumentationScopeName)
    }

    /** Answers 503 to the first request, which the client then resends, and 200 to the second. */
    private class ResendingHttpClient : HttpClient {
        val requests = CopyOnWriteArrayList<HttpRequest>()

        /** The ID of the span that is current as each request is sent. */
        val currentSpanIds = CopyOnWriteArrayList<String>()

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            requests += request
            currentSpanIds += Span.current().spanContext.spanId
            return response(if (requests.size == 1) 503 else 200)
        }

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> =
            CompletableFuture.completedFuture(execute(request, requestOptions))

        override fun close() {}

        private fun response(statusCode: Int) =
            object : HttpResponse {
                override fun statusCode(): Int = statusCode

                override fun headers(): Headers = Headers.builder().build()

                override fun body(): InputStream = "{}".byteInputStream()

                override fun close() {}
            }
    }

    private object NoSleep : Sleeper {
        override fun sleep(duration: Duration) {}

        override fun sleepAsync(duration: Duration): CompletableFuture<Void> =
            CompletableFuture.completedFuture(null)

        override fun close() {}
    }

    private val propagator = CountingPropagator()

    @BeforeEach
    fun registerPropagator() {
        GlobalOpenTelemetry.resetForTest()
        GlobalOpenTelemetry.set(OpenTelemetry.propagating(ContextPropagators.create(propagator)))
    }

    @AfterEach
    fun reset() {
        GlobalOpenTelemetry.resetForTest()
        System.clearProperty(PROPERTY)
    }

    /**
     * Calls the API inside [PARENT_SPAN], and returns the HTTP client that sent the call's two
     * requests.
     */
    private fun callApi(async: Boolean, openTelemetry: OpenTelemetryOptions): ResendingHttpClient {
        val httpClient = ResendingHttpClient()
        val client =
            AnthropicClientImpl(
                ClientOptions.builder()
                    .httpClient(httpClient)
                    .baseUrl("http://localhost")
                    .sleeper(NoSleep)
                    .maxRetries(1)
                    .openTelemetry(openTelemetry)
                    .apiKey("my-anthropic-api-key")
                    .build()
            )
        PARENT_SPAN.makeCurrent().use {
            if (async) {
                client
                    .async()
                    .messages()
                    .create(
                        MessageCreateParams.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_OPUS_5_5)
                            .build()
                    )
                    .get()
            } else {
                client
                    .messages()
                    .create(
                        MessageCreateParams.builder()
                            .maxTokens(1024L)
                            .addUserMessage("Hello, world")
                            .model(Model.CLAUDE_OPUS_5_5)
                            .build()
                    )
            }
        }
        assertThat(httpClient.requests).hasSize(2)
        return httpClient
    }

    @ParameterizedTest
    @MethodSource("clients")
    fun propagationOn_everyRequestCarriesTheTraceContext(
        async: Boolean,
        tracerProvider: TracerProvider?,
        spanId: String,
    ) {
        val openTelemetry = OpenTelemetryOptions.builder().tracerProvider(tracerProvider).build()

        val httpClient = callApi(async, openTelemetry)

        assertThat(httpClient.requests).allSatisfy { request ->
            assertThat(request.headers.values("traceparent"))
                .containsExactly("00-$TRACE_ID-$spanId-01")
        }
    }

    @ParameterizedTest
    @MethodSource("clients")
    fun propagationOff_thePropagatorIsNeverAsked(async: Boolean, tracerProvider: TracerProvider?) {
        val openTelemetry =
            OpenTelemetryOptions.builder().tracerProvider(tracerProvider).propagation(false).build()

        val httpClient = callApi(async, openTelemetry)

        assertThat(propagator.injects.get()).isEqualTo(0)
        assertThat(httpClient.requests).allSatisfy { request ->
            assertThat(request.headers.names()).doesNotContain("traceparent", "tracestate")
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun propagationOff_aSpanThatDoesNotRecordIsStillCurrent(async: Boolean) {
        val openTelemetry =
            OpenTelemetryOptions.builder()
                .tracerProvider(CallSpanTracerProvider)
                .propagation(false)
                .build()

        val httpClient = callApi(async, openTelemetry)

        assertThat(httpClient.currentSpanIds).containsExactly(CALL_SPAN_ID, CALL_SPAN_ID)
    }

    @Test
    fun fromEnv_withoutTheProperty_propagationIsOn() {
        assertThat(OpenTelemetryOptions.fromEnv().propagation()).isTrue()
    }

    @ParameterizedTest
    @CsvSource("false,false", "FALSE,false", "0,true", "off,true")
    fun fromEnv_onlyFalseTurnsPropagationOff(value: String, expected: Boolean) {
        System.setProperty(PROPERTY, value)

        assertThat(OpenTelemetryOptions.fromEnv().propagation()).isEqualTo(expected)
    }

    @Test
    fun withEnv_replacesOnlyPropagation() {
        System.setProperty(PROPERTY, "false")

        val options = OpenTelemetryOptions.builder().traces(false).build().withEnv()

        assertThat(options.propagation()).isFalse()
        assertThat(options.traces().enabled()).isFalse()
    }

    companion object {
        private const val PROPERTY = "anthropic.openTelemetryPropagation"
        private const val TRACE_ID = "0af7651916cd43dd8448eb211c80319c"
        private const val PARENT_SPAN_ID = "b7ad6b7169203331"
        private const val CALL_SPAN_ID = "00f067aa0ba902b7"

        private val SPAN_BUILDER = SpanBuilder::class.java
        private val PARENT_SPAN = sampledSpan(PARENT_SPAN_ID)
        private val CALL_SPAN = sampledSpan(CALL_SPAN_ID)

        private fun sampledSpan(spanId: String): Span =
            Span.wrap(
                SpanContext.create(
                    TRACE_ID,
                    spanId,
                    TraceFlags.getSampled(),
                    TraceState.getDefault(),
                )
            )

        /**
         * A blocking and an asynchronous client, each with a tracer provider of its own and with
         * the registered no-op one, whose spans have the context of their parent.
         */
        @JvmStatic
        fun clients(): Stream<Arguments> =
            Stream.of(
                Arguments.of(false, CallSpanTracerProvider, CALL_SPAN_ID),
                Arguments.of(false, null, PARENT_SPAN_ID),
                Arguments.of(true, CallSpanTracerProvider, CALL_SPAN_ID),
                Arguments.of(true, null, PARENT_SPAN_ID),
            )
    }
}
