package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.Sleeper
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.models.messages.RawMessageStreamEvent
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.sdk.trace.data.SpanData
import java.io.InputStream
import java.time.Duration
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

/**
 * The API call span is the current span while the HTTP client sends each attempt, so that a span
 * that an instrumentation of the HTTP client starts is its child, and it is no longer current once
 * the call returns. It is not current in the application's code that reads the call's stream: a
 * loop over the stream, or a handler that the application subscribes to it.
 */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class CurrentSpanTest : TracingTestSupport() {

    /**
     * An HTTP client that starts a span named `attempt` for each request, as an instrumentation of
     * the HTTP client does, and answers with each of [responses] in turn: a status code and a body.
     * It answers an asynchronous request from a thread of its own.
     */
    private inner class InstrumentedHttpClient(
        private val contentType: String,
        vararg responses: Pair<Int, String>,
    ) : HttpClient {
        private val responses = CopyOnWriteArrayList(responses.toList())

        private fun respond(): HttpResponse {
            val attempt = recorder.tracerProvider.get("http").spanBuilder("attempt").startSpan()
            val (status, body) = responses.removeAt(0)
            attempt.end()
            return object : HttpResponse {
                override fun statusCode(): Int = status

                override fun headers(): Headers =
                    Headers.builder().put("Content-Type", contentType).build()

                override fun body(): InputStream = body.byteInputStream()

                override fun close() {}
            }
        }

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            respond()

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            // Started where the client calls, as an instrumentation does, and completed elsewhere.
            val response = respond()
            return onAnotherThread { response }
        }

        override fun close() {}
    }

    /**
     * A future that a new thread completes with [value], so that what follows it runs neither on
     * the caller's thread nor on a shared pool that other tests may keep busy.
     */
    private fun <T> onAnotherThread(value: () -> T): CompletableFuture<T> {
        val future = CompletableFuture<T>()
        Thread { future.complete(value()) }.start()
        return future
    }

    /** Backs off for no time, and resumes an asynchronous call on a thread of its own. */
    private inner class ImmediateSleeper : Sleeper {
        override fun sleep(duration: Duration) {}

        override fun sleepAsync(duration: Duration): CompletableFuture<Void> =
            onAnotherThread<Void?> { null }.thenApply { it }

        override fun close() {}
    }

    private fun clientOptions(httpClient: HttpClient, maxRetries: Int = 0): ClientOptions =
        ClientOptions.builder()
            .httpClient(httpClient)
            .sleeper(ImmediateSleeper())
            .maxRetries(maxRetries)
            .openTelemetry(options())
            .build()

    private fun attempts(): List<SpanData> = recorder.spans.filter { it.name == "attempt" }

    @Test
    fun httpClientSpan_isAChildOfTheCallSpan_andTheCallerSpanIsCurrentAgainAfterTheCall() {
        val client =
            AnthropicClientImpl(
                clientOptions(InstrumentedHttpClient("application/json", 200 to MESSAGE_JSON))
            )
        val caller = recorder.tracerProvider.get("app").spanBuilder("handle_order").startSpan()

        caller.makeCurrent().use {
            client.messages().create(messageParams())

            assertThat(Span.current()).isSameAs(caller)
        }
        caller.end()

        val call = recorder.named("anthropic.messages.create")
        assertThat(call.parentSpanId).isEqualTo(caller.spanContext.spanId)
        assertThat(attempts().single().parentSpanId).isEqualTo(call.spanId)
    }

    @Test
    fun httpClientSpan_ofEachAttempt_isAChildOfTheCallSpan() {
        val client =
            AnthropicClientImpl(
                clientOptions(
                    InstrumentedHttpClient(
                        "application/json",
                        500 to """{"type":"error","error":{"type":"api_error","message":"no"}}""",
                        200 to MESSAGE_JSON,
                    ),
                    maxRetries = 1,
                )
            )

        client.messages().create(messageParams())

        val call = recorder.named("anthropic.messages.create")
        assertThat(attempts()).hasSize(2).allSatisfy {
            assertThat(it.parentSpanId).isEqualTo(call.spanId)
        }
    }

    @Test
    fun asyncHttpClientSpan_ofEachAttempt_isAChildOfTheCallSpan() {
        // The retry runs on the thread that ends the backoff, not where the application called.
        val client =
            AnthropicClientAsyncImpl(
                clientOptions(
                    InstrumentedHttpClient(
                        "application/json",
                        500 to """{"type":"error","error":{"type":"api_error","message":"no"}}""",
                        200 to MESSAGE_JSON,
                    ),
                    maxRetries = 1,
                )
            )
        val caller = recorder.tracerProvider.get("app").spanBuilder("handle_order").startSpan()

        caller.makeCurrent().use {
            val future = client.messages().create(messageParams())

            assertThat(Span.current()).isSameAs(caller)
            future.get(30, TimeUnit.SECONDS)
            assertThat(Span.current()).isSameAs(caller)
        }
        caller.end()

        val call = recorder.await(4).single { it.name == "anthropic.messages.create" }
        assertThat(call.parentSpanId).isEqualTo(caller.spanContext.spanId)
        assertThat(attempts()).hasSize(2).allSatisfy {
            assertThat(it.parentSpanId).isEqualTo(call.spanId)
        }
    }

    @Test
    fun stream_whileTheApplicationReadsIt_leavesTheCallerSpanCurrent() {
        val client =
            AnthropicClientImpl(
                clientOptions(InstrumentedHttpClient("text/event-stream", 200 to STREAM_BODY))
            )
        val caller = recorder.tracerProvider.get("app").spanBuilder("handle_order").startSpan()
        var events = 0

        caller.makeCurrent().use {
            client.messages().createStreaming(messageParams()).use { stream ->
                assertThat(Span.current()).isSameAs(caller)
                stream.stream().forEach {
                    events++
                    assertThat(Span.current()).isSameAs(caller)
                }
            }
            assertThat(Span.current()).isSameAs(caller)
        }
        caller.end()

        assertThat(events).isEqualTo(STREAM_EVENTS.size)
        val call = recorder.named("anthropic.messages.create")
        assertThat(call.parentSpanId).isEqualTo(caller.spanContext.spanId)
        assertThat(attempts().single().parentSpanId).isEqualTo(call.spanId)
    }

    @Test
    fun asyncStream_handlerDoesNotRunWithTheCallSpanCurrent() {
        val client =
            AnthropicClientAsyncImpl(
                clientOptions(InstrumentedHttpClient("text/event-stream", 200 to STREAM_BODY))
            )
        val tracer = recorder.tracerProvider.get("app")
        val caller = tracer.spanBuilder("handle_order").startSpan()
        // The span that is current in each call of the handler, on the handler's thread, and
        // whether the call span had ended by then.
        val currentInOnNext = CopyOnWriteArrayList<Span>()
        val callEndedInOnNext = CopyOnWriteArrayList<Boolean>()
        val currentInOnComplete = CompletableFuture<Span>()

        val stream =
            caller.makeCurrent().use {
                client
                    .messages()
                    .createStreaming(messageParams())
                    .subscribe(
                        object : AsyncStreamResponse.Handler<RawMessageStreamEvent> {
                            override fun onNext(value: RawMessageStreamEvent) {
                                currentInOnNext.add(Span.current())
                                callEndedInOnNext.add(
                                    recorder.spans.any { it.name.startsWith("anthropic") }
                                )
                                // What the application's handler starts is not a child of the call.
                                tracer.spanBuilder("handle_event").startSpan().end()
                            }

                            override fun onComplete(error: Optional<Throwable>) {
                                currentInOnComplete.complete(Span.current())
                            }
                        }
                    )
            }
        stream.onCompleteFuture().get(30, TimeUnit.SECONDS)
        caller.end()

        // The handler runs on the executor's thread, where the application made no span current.
        assertThat(currentInOnNext).hasSize(STREAM_EVENTS.size).allSatisfy {
            assertThat(it.spanContext.isValid).isFalse()
        }
        assertThat(currentInOnComplete.get(30, TimeUnit.SECONDS).spanContext.isValid).isFalse()
        // The call span stays open while the handler reads, and ends once the stream has.
        assertThat(callEndedInOnNext).hasSize(STREAM_EVENTS.size).containsOnly(false)
        val call = recorder.await(STREAM_EVENTS.size + 3).single { it.name.startsWith("anthropic") }
        assertThat(call.hasEnded()).isTrue()
        assertThat(call.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(call.parentSpanId).isEqualTo(caller.spanContext.spanId)
        assertThat(recorder.spans.filter { it.name == "handle_event" })
            .hasSize(STREAM_EVENTS.size)
            .allSatisfy {
                assertThat(it.parentSpanId).isNotEqualTo(call.spanId)
                assertThat(it.traceId).isNotEqualTo(call.traceId)
                assertThat(it.parentSpanContext.isValid).isFalse()
            }
    }
}
