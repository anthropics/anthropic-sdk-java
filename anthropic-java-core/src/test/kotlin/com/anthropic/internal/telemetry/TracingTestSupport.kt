package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.OpenTelemetryTraces
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.messages.MessageCreateParams
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.stubbing.Scenario
import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.sdk.common.CompletableResultCode
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.data.SpanData
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor
import io.opentelemetry.sdk.trace.export.SpanExporter
import java.lang.ref.WeakReference
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

/**
 * What the tracing tests share: the API's responses, a client whose spans a [Recorder] keeps, the
 * stubs of the mock server, and readers of a span's attributes.
 */
internal abstract class TracingTestSupport {

    companion object {

        const val MESSAGE_JSON =
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "text", "text": "Hi there"}],
              "stop_reason": "end_turn",
              "usage": {
                "input_tokens": 10, "output_tokens": 5,
                "cache_creation_input_tokens": 3, "cache_read_input_tokens": 2,
                "cache_creation": {"ephemeral_5m_input_tokens": 1, "ephemeral_1h_input_tokens": 2},
                "service_tier": "standard"
              }
            }
            """

        const val TOOL_USE_MESSAGE_JSON =
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "tool_use", "id": "toolu_1", "name": "get_weather", "input": {"location": "Paris"}}],
              "stop_reason": "tool_use",
              "usage": {"input_tokens": 10, "output_tokens": 5}
            }
            """

        const val FINAL_MESSAGE_JSON =
            """
            {
              "id": "msg_2", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "text", "text": "Foggy"}],
              "stop_reason": "end_turn",
              "usage": {"input_tokens": 20, "output_tokens": 5}
            }
            """

        const val ERROR_JSON =
            """{"type": "error", "error": {"type": "rate_limit_error", "message": "slow down: hello"}}"""

        val STREAM_EVENTS =
            listOf(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"claude-served","content":[],"stop_reason":null,"usage":{"input_tokens":10,"output_tokens":1,"cache_read_input_tokens":2,"cache_creation_input_tokens":3,"cache_creation":{"ephemeral_5m_input_tokens":3,"ephemeral_1h_input_tokens":0},"service_tier":"standard"}}}""",
                """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hi"}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":" there"}}""",
                """{"type":"content_block_stop","index":0}""",
                """{"type":"content_block_start","index":1,"content_block":{"type":"tool_use","id":"toolu_1","name":"get_weather","input":{}}}""",
                """{"type":"content_block_delta","index":1,"delta":{"type":"input_json_delta","partial_json":"{\"location\":"}}""",
                """{"type":"content_block_delta","index":1,"delta":{"type":"input_json_delta","partial_json":"\"Paris\"}"}}""",
                """{"type":"content_block_stop","index":1}""",
                """{"type":"message_delta","delta":{"stop_reason":"tool_use"},"usage":{"output_tokens":7,"output_tokens_details":{"thinking_tokens":4}}}""",
                """{"type":"message_stop"}""",
            )

        val STREAM_BODY = sse(*STREAM_EVENTS.toTypedArray())

        // The Message that `STREAM_BODY` streams, as a call that does not stream returns it.
        const val STREAMED_MESSAGE_JSON =
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [
                {"type": "text", "text": "Hi there"},
                {"type": "tool_use", "id": "toolu_1", "name": "get_weather", "input": {"location": "Paris"}}
              ],
              "stop_reason": "tool_use",
              "usage": {"input_tokens": 10, "output_tokens": 7}
            }
            """

        const val STREAMED_OUTPUT_MESSAGES =
            """[{"role":"assistant","parts":[{"type":"text","content":"Hi there"},{"type":"tool_call","id":"toolu_1","name":"get_weather","arguments":{"location":"Paris"}}]}]"""

        // A delta for a content block that never started, which the SDK's accumulator rejects.
        const val STRAY_DELTA_EVENT =
            """{"type":"content_block_delta","index":-1,"delta":{"type":"text_delta","text":"stray"}}"""

        val MALFORMED_STREAM_BODY =
            sse(*(STREAM_EVENTS.take(4) + STRAY_DELTA_EVENT + STREAM_EVENTS.drop(4)).toTypedArray())

        // The server ends the body before `message_stop`.
        val TRUNCATED_STREAM_BODY =
            sse(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"claude-served","content":[],"stop_reason":null,"usage":{"input_tokens":10,"output_tokens":1}}}""",
                """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":""}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hi"}}""",
            )

        // What the SDK's accumulator holds of the Message when `TRUNCATED_STREAM_BODY` ends.
        const val TRUNCATED_OUTPUT_MESSAGES =
            """[{"role":"assistant","parts":[{"type":"text","content":"Hi"}]}]"""

        val EVENTS_BODY =
            sse(
                """{"type":"session.status_running","id":"sevt_1"}""",
                """{"type":"session.status_idle","id":"sevt_2"}""",
            )

        const val ERROR_EVENT =
            "event: error\ndata: {\"type\":\"error\",\"error\":{\"type\":\"overloaded_error\",\"message\":\"Overloaded\"}}\n\n"

        val STREAM_ERROR_BODY =
            sse(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"claude-served","content":[],"stop_reason":null,"usage":{"input_tokens":10,"output_tokens":1}}}"""
            ) + ERROR_EVENT

        fun sse(vararg events: String): String =
            events.joinToString("") { data ->
                val type = data.substringAfter("\"type\":\"").substringBefore('"')
                "event: $type\ndata: $data\n\n"
            }

        init {
            registerGlobal()
        }

        /** The SDK injects trace headers with the propagator the application registered. */
        fun registerGlobal() {
            try {
                GlobalOpenTelemetry.set(
                    OpenTelemetry.propagating(
                        ContextPropagators.create(W3CTraceContextPropagator.getInstance())
                    )
                )
            } catch (e: IllegalStateException) {
                // Another test class in this JVM registered it first.
            }
        }
    }

    /** Records every span that ends. */
    protected class Recorder : SpanExporter {
        val spans = CopyOnWriteArrayList<SpanData>()
        val tracerProvider: SdkTracerProvider =
            SdkTracerProvider.builder().addSpanProcessor(SimpleSpanProcessor.create(this)).build()

        override fun export(spans: Collection<SpanData>): CompletableResultCode {
            this.spans.addAll(spans)
            return CompletableResultCode.ofSuccess()
        }

        override fun flush(): CompletableResultCode = CompletableResultCode.ofSuccess()

        override fun shutdown(): CompletableResultCode = CompletableResultCode.ofSuccess()

        fun only(): SpanData {
            assertThat(spans).hasSize(1)
            return spans[0]
        }

        fun named(name: String): SpanData = spans.single { it.name == name }

        /** Waits until [count] spans have ended. */
        fun await(count: Int): List<SpanData> {
            val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
            while (spans.size < count && System.nanoTime() < deadline) {
                Thread.sleep(10)
            }
            assertThat(spans).hasSize(count)
            return spans
        }
    }

    /** Collects garbage until [count] spans have ended. */
    protected fun awaitReclaimed(count: Int): List<SpanData> {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (recorder.spans.size < count && System.nanoTime() < deadline) {
            System.gc()
            Thread.sleep(50)
        }
        assertThat(recorder.spans).hasSize(count)
        return recorder.spans
    }

    /** Collects garbage until the span named [name] has ended. */
    protected fun awaitReclaimed(name: String): SpanData {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (recorder.spans.none { it.name == name } && System.nanoTime() < deadline) {
            System.gc()
            Thread.sleep(50)
        }
        assertThat(recorder.spans.map { it.name }).contains(name)
        return recorder.named(name)
    }

    /** Collects garbage, and gives the runtime time to close what it reclaimed. */
    protected fun collectGarbage() {
        val collected = WeakReference(Any())
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (collected.get() != null && System.nanoTime() < deadline) {
            System.gc()
            Thread.sleep(50)
        }
        assertThat(collected.get()).isNull()
        System.gc()
        Thread.sleep(200)
    }

    protected lateinit var baseUrl: String
    protected lateinit var recorder: Recorder
    protected lateinit var client: AnthropicClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        recorder = Recorder()
        client = clientBuilder().build()
    }

    protected fun options(
        contentMode: OpenTelemetryContentMode = OpenTelemetryContentMode.METADATA_ONLY,
        maxContentBytes: Long? = null,
    ): OpenTelemetryOptions =
        OpenTelemetryOptions.builder()
            .tracerProvider(recorder.tracerProvider)
            .traces(
                OpenTelemetryTraces.builder()
                    .contentMode(contentMode)
                    .maxContentBytes(maxContentBytes)
                    .build()
            )
            .build()

    protected fun clientBuilder(
        contentMode: OpenTelemetryContentMode = OpenTelemetryContentMode.METADATA_ONLY,
        maxContentBytes: Long? = null,
    ) =
        AnthropicOkHttpClient.builder()
            .baseUrl(baseUrl)
            .apiKey("my-anthropic-api-key")
            .maxRetries(0)
            .openTelemetry(options(contentMode, maxContentBytes))

    protected fun asyncClient(
        contentMode: OpenTelemetryContentMode = OpenTelemetryContentMode.METADATA_ONLY
    ): AnthropicClientAsync =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(baseUrl)
            .apiKey("my-anthropic-api-key")
            .maxRetries(0)
            .openTelemetry(options(contentMode))
            .build()

    protected fun messageParams(): MessageCreateParams =
        MessageCreateParams.builder()
            .model("claude-requested")
            .maxTokens(100)
            .system("Be brief. SYSTEM-SECRET")
            .addUserMessage("hello USER-SECRET")
            .putAdditionalHeader("anthropic-beta", "beta-one, beta-two")
            .build()

    protected fun betaMessageParams(): BetaMessageCreateParams =
        BetaMessageCreateParams.builder()
            .model("claude-requested")
            .maxTokens(100)
            .addUserMessage("hello USER-SECRET")
            .build()

    protected fun stubMessage(body: String = MESSAGE_JSON) {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(
                    ok(body.trimIndent())
                        .withHeader("Content-Type", "application/json")
                        .withHeader("request-id", "req_1")
                        .withHeader("anthropic-organization-id", "org_1")
                        .withHeader("anthropic-workspace-id", "wrkspc_1")
                )
        )
    }

    protected fun stubStream(body: String = STREAM_BODY) {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(ok(body).withHeader("Content-Type", "text/event-stream"))
        )
    }

    protected fun SpanData.string(key: String): String? =
        attributes.get(AttributeKey.stringKey(key))

    protected fun SpanData.long(key: String): Long? = attributes.get(AttributeKey.longKey(key))

    protected fun SpanData.double(key: String): Double? =
        attributes.get(AttributeKey.doubleKey(key))

    protected fun SpanData.strings(key: String): List<String>? =
        attributes.get(AttributeKey.stringArrayKey(key))

    /** Every attribute value of the span, to check that no content leaks into it. */
    protected fun SpanData.attributeText(): String =
        attributes.asMap().values.joinToString("\n") + "\n" + status.description

    protected fun attributesOf(span: SpanData): Map<String, Any> =
        span.attributes.asMap().mapKeys { it.key.key }

    protected fun stubToolLoop(toolUseMessage: String = TOOL_USE_MESSAGE_JSON) {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(toolUseMessage.trimIndent()).withHeader("Content-Type", "application/json")
                )
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs("second")
                .willReturn(
                    ok(FINAL_MESSAGE_JSON.trimIndent())
                        .withHeader("Content-Type", "application/json")
                )
        )
    }

    protected fun toolParams(): BetaMessageCreateParams =
        BetaMessageCreateParams.builder()
            .model("claude-requested")
            .maxTokens(100)
            .addUserMessage("Weather in Paris? USER-SECRET")
            .addTool(GetWeather::class.java)
            .addTool(BrokenTool::class.java)
            .build()
}

@JsonClassDescription("Get the weather in a given location")
internal class GetWeather : Supplier<String> {
    lateinit var location: String

    override fun get(): String = "The weather in $location is foggy"
}

@JsonClassDescription("A tool that fails")
internal class BrokenTool : Supplier<String> {
    lateinit var location: String

    override fun get(): String = throw UnsupportedOperationException("BOOM $location")
}

@JsonClassDescription("A tool whose run the application interrupts")
internal class StoppingTool : Supplier<String> {
    lateinit var location: String

    override fun get(): String = throw InterruptedException("stopped looking up $location")
}
