package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.LogLevel
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.StreamRecording
import com.anthropic.core.opentelemetry.endApiCallSpanWhenStreamEnds
import com.anthropic.errors.SseException
import com.anthropic.models.messages.RawMessageStreamEvent
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.json.JsonMapper
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.stubbing.Scenario
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.sdk.trace.data.SpanData
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.mock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class StreamRecordingTest : TracingTestSupport() {

    @Test
    fun messagesCreateStreaming_readToTheEnd() {
        stubStream()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.messages.create")
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(attributesOf(span)).containsEntry("gen_ai.request.stream", true)
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
        assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(15)
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.long("gen_ai.usage.reasoning.output_tokens")).isEqualTo(4)
        assertThat(span.long("anthropic.usage.cache_creation.ephemeral_5m_input_tokens"))
            .isEqualTo(3)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNotNegative()
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
    }

    @Test
    fun messagesCreateStreaming_convertsNoEventToJson() {
        stubStream()
        // Every value that recording converts to a JSON tree.
        val converted = CopyOnWriteArrayList<Any?>()
        val mapper =
            object : JsonMapper(jsonMapper()) {
                override fun <T : JsonNode?> valueToTree(fromValue: Any?): T {
                    converted.add(fromValue)
                    return super.valueToTree(fromValue)
                }
            }
        val client = clientBuilder().jsonMapper(mapper).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        assertThat(recorder.only().long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(converted.filterIsInstance<RawMessageStreamEvent>()).isEmpty()
    }

    @Test
    fun messagesCreateStreaming_recordsTheResponseAttributesOfTheCallThatIsNotStreamed() {
        // The Message that `STREAM_BODY` streams, with its final usage.
        stubMessage(
            """
            {
              "id": "msg_1", "type": "message", "role": "assistant", "model": "claude-served",
              "content": [{"type": "text", "text": "Hi there"}],
              "stop_reason": "tool_use",
              "usage": {
                "input_tokens": 10, "output_tokens": 7,
                "cache_read_input_tokens": 2, "cache_creation_input_tokens": 3,
                "cache_creation": {"ephemeral_5m_input_tokens": 3, "ephemeral_1h_input_tokens": 0},
                "service_tier": "standard",
                "output_tokens_details": {"thinking_tokens": 4}
              }
            }
            """
        )
        client.messages().create(messageParams())
        val notStreamed = responseAttributesOf(recorder.only())
        recorder.spans.clear()

        stubStream()
        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        assertThat(notStreamed)
            .containsKeys("gen_ai.response.id", "gen_ai.usage.output_tokens")
            .containsEntry("anthropic.message.stop_reason", "tool_use")
        assertThat(responseAttributesOf(recorder.only())).isEqualTo(notStreamed)
    }

    /** The attributes that come from the Message, whether it is streamed or not. */
    private fun responseAttributesOf(span: SpanData): Map<String, Any> =
        attributesOf(span).filterKeys {
            it != "gen_ai.response.time_to_first_chunk" &&
                (it.startsWith("gen_ai.response.") ||
                    it.startsWith("gen_ai.usage.") ||
                    it.startsWith("anthropic.usage.") ||
                    it.startsWith("anthropic.response.") ||
                    it == "anthropic.message.stop_reason")
        }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun messagesCreateStreaming_metadataOnly_keepsTheContentEventsFromTheAccumulator() {
        // The stray delta is one the accumulator rejects, so it logs a failure if it gets there.
        stubStream(MALFORMED_STREAM_BODY)
        val client = clientBuilder().logLevel(LogLevel.DEBUG).build()

        val log = standardErrorOf {
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        assertThat(log).doesNotContain("Could not record")
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun messagesCreateStreaming_eventTheAccumulatorRejects_logsNoContent() {
        // A text delta whose text is not a string: the accumulator's error quotes the value.
        stubStream(
            sse(
                *(STREAM_EVENTS.take(2) +
                        """{"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":{"leak":"OUTPUT-SECRET"}}}""" +
                        STREAM_EVENTS.drop(2))
                    .toTypedArray()
            )
        )
        val client =
            clientBuilder(OpenTelemetryContentMode.CONTENT).logLevel(LogLevel.DEBUG).build()

        val events = mutableListOf<RawMessageStreamEvent>()
        val log = standardErrorOf {
            client.messages().createStreaming(messageParams()).use {
                it.stream().forEach(events::add)
            }
        }

        // The application reads every event, and the span ends as that of a complete stream.
        assertThat(events).hasSize(STREAM_EVENTS.size + 1)
        assertThat(log.lines().filter { it.startsWith("Could not record") })
            .containsExactly(
                "Could not record a stream event on its span: " +
                    "com.anthropic.errors.AnthropicInvalidDataException"
            )
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
        assertThat(span.string("gen_ai.output.messages")).isNull()
        assertThat(span.attributeText()).doesNotContain("OUTPUT-SECRET")
    }

    /** What [block] writes to the standard error stream, where the SDK logs. */
    private fun standardErrorOf(block: () -> Unit): String {
        val original = System.err
        val captured = ByteArrayOutputStream()
        System.setErr(PrintStream(captured, true, "UTF-8"))
        try {
            block()
        } finally {
            System.setErr(original)
        }
        return captured.toString("UTF-8")
    }

    @ParameterizedTest
    @ValueSource(strings = ["onEvent", "onEnd"])
    fun recordingThatThrows_neitherFailsTheReadNorKeepsTheSpanOpen(failing: String) {
        // The SDK's own recording catches what it throws, and the stream that ends the span does
        // not count on that.
        val recording =
            object : StreamRecording<String> {
                override fun onEvent(event: String) {
                    check(failing != "onEvent") { "BOOM $event" }
                }

                override fun onEnd(): Boolean {
                    check(failing != "onEnd") { "BOOM" }
                    return true
                }
            }
        val call =
            ApiCall(
                recorder.tracerProvider.get("test").spanBuilder("call").startSpan(),
                ClientOptions.builder().httpClient(mock<HttpClient>()).build(),
            )
        val source =
            object : StreamResponse<String> {
                override fun stream(): Stream<String> = Stream.of("first", "second")

                override fun close() {}
            }
        val response =
            object : HttpResponse {
                override fun statusCode(): Int = 200

                override fun headers(): Headers =
                    Headers.builder().put("Content-Type", "text/event-stream").build()

                override fun body(): InputStream = ByteArrayInputStream(ByteArray(0))

                override fun close() {}
            }

        val events =
            endApiCallSpanWhenStreamEnds(call, source, response, recording).use {
                it.stream().collect(Collectors.toList())
            }

        assertThat(events).containsExactly("first", "second")
        assertThat(recorder.only().name).isEqualTo("call")
    }

    @Test
    fun betaMessagesCreateStreaming_fallbackBlock_recordsTheModelThatServed() {
        // The beta accumulator takes the Message's model from a fallback block, as the API does
        // for a Message that is not streamed. The block's event reaches it without content mode.
        stubStream(
            sse(
                *(STREAM_EVENTS.take(1) +
                        """{"type":"content_block_start","index":0,"content_block":{"type":"fallback","from":{"model":"claude-served"},"to":{"model":"claude-fallback"},"trigger":{"type":"refusal"}}}""" +
                        """{"type":"content_block_stop","index":0}""" +
                        STREAM_EVENTS.takeLast(2))
                    .toTypedArray()
            )
        )

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-fallback")
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNull()
    }

    @Test
    fun messagesCreateStreaming_closedEarly_leavesStatusUnset() {
        stubStream()

        client.messages().createStreaming(messageParams()).use { it.stream().findFirst() }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
        assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(15)
        assertThat(span.string("anthropic.response.service_tier")).isEqualTo("standard")
        assertThat(span.long("gen_ai.usage.output_tokens")).isNull()
        assertThat(span.string("anthropic.message.stop_reason")).isNull()
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNull()
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
    }

    /**
     * The application read the whole Message, and closed the stream before the server ended it: a
     * cancellation all the same.
     */
    @Test
    fun messagesCreateStreaming_closedAfterMessageStop_leavesStatusUnset() {
        stubStream()

        client.messages().createStreaming(messageParams()).use { response ->
            val events = response.stream().iterator()
            val read = List(STREAM_EVENTS.size) { events.next() }
            assertThat(read.last().isMessageStop()).isTrue()
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNotNegative()
    }

    @Test
    fun betaMessagesCreateStreaming_closedAfterMessageStop_leavesStatusUnset() {
        stubStream()

        client.beta().messages().createStreaming(betaMessageParams()).use { response ->
            val events = response.stream().iterator()
            val read = List(STREAM_EVENTS.size) { events.next() }
            assertThat(read.last().isMessageStop()).isTrue()
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }

    /** Reads the first event of a streamed Message, and drops the response without closing it. */
    private fun readFirstEventAndAbandon() {
        client.messages().createStreaming(messageParams()).stream().findFirst()
    }

    @Test
    fun messagesCreateStreaming_abandoned_leavesStatusUnsetOnceReclaimed() {
        stubStream()

        readFirstEventAndAbandon()

        val span = awaitReclaimed(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.string("gen_ai.response.model")).isEqualTo("claude-served")
        assertThat(span.long("gen_ai.usage.output_tokens")).isNull()
        assertThat(span.string("anthropic.message.stop_reason")).isNull()
        // The span ended once.
        collectGarbage()
        assertThat(recorder.spans).hasSize(1)
    }

    @Test
    fun messagesCreateStreaming_closedEarlyThenReclaimed_endsTheSpanOnce() {
        stubStream()

        client.messages().createStreaming(messageParams()).use { it.stream().findFirst() }
        collectGarbage()

        assertThat(recorder.only().status.statusCode).isEqualTo(StatusCode.UNSET)
    }

    @Test
    fun messagesCreateStreaming_readToTheEndThenReclaimed_keepsStatusOk() {
        stubStream()

        client.messages().createStreaming(messageParams()).stream().forEach {}
        collectGarbage()

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }

    /** An application that holds only the stream is still reading it. */
    @Test
    fun messagesCreateStreaming_streamHeldWithoutItsResponse_keepsTheSpanOpen() {
        stubStream()

        val events = client.messages().createStreaming(messageParams()).stream().iterator()
        events.next()
        collectGarbage()

        assertThat(recorder.spans).isEmpty()
        events.forEachRemaining {}
        assertThat(recorder.only().status.statusCode).isEqualTo(StatusCode.OK)
    }

    @Test
    fun messagesCreateStreaming_endedBeforeMessageStop_isAnError() {
        stubStream(TRUNCATED_STREAM_BODY)

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("api_error")
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.long("gen_ai.usage.output_tokens")).isNull()
        assertThat(span.string("anthropic.message.stop_reason")).isNull()
        // The accumulator of a client that records no content gets none of the Message's.
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
    }

    @Test
    fun messagesCreateStreaming_endedAfterMessageDelta_keepsWhatMessageDeltaCarried() {
        // The server ends the body after `message_delta` and before `message_stop`.
        stubStream(sse(*STREAM_EVENTS.dropLast(1).toTypedArray()))

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        asyncClient()
            .messages()
            .createStreaming(messageParams())
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy { span ->
            assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
            assertThat(span.string("error.type")).isEqualTo("api_error")
            assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(span.long("gen_ai.usage.input_tokens")).isEqualTo(15)
            assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
            assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
            assertThat(span.long("gen_ai.usage.reasoning.output_tokens")).isEqualTo(4)
        }
    }

    @Test
    fun betaMessagesCreateStreaming_endedAfterMessageDelta_keepsTheTokensOfItsCompactions() {
        val compaction =
            """{"type":"compaction","input_tokens":100,"cache_read_input_tokens":20,"cache_creation_input_tokens":3,"output_tokens":40}"""
        val events =
            STREAM_EVENTS.dropLast(1).map {
                if (!it.contains("message_delta")) it
                else it.replace(""""usage":{""", """"usage":{"iterations":[$compaction],""")
            }
        stubStream(sse(*events.toTypedArray()))

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }
        asyncClient()
            .beta()
            .messages()
            .createStreaming(betaMessageParams())
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy { span ->
            assertThat(span.string("error.type")).isEqualTo("api_error")
            assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
            assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
            assertThat(span.long("anthropic.usage.compaction.input_tokens")).isEqualTo(123)
            assertThat(span.long("anthropic.usage.compaction.output_tokens")).isEqualTo(40)
        }
    }

    @Test
    fun betaMessagesCreateStreaming_endedBeforeMessageDelta_hasNoneOfWhatMessageDeltaCarries() {
        // Up to the last `content_block_stop`: the server ends the body before `message_delta`.
        stubStream(sse(*STREAM_EVENTS.dropLast(2).toTypedArray()))

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }
        asyncClient()
            .beta()
            .messages()
            .createStreaming(betaMessageParams())
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy { span ->
            assertThat(span.string("error.type")).isEqualTo("api_error")
            assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
            assertThat(attributesOf(span))
                .doesNotContainKeys(
                    "anthropic.message.stop_reason",
                    "gen_ai.usage.output_tokens",
                    "gen_ai.usage.reasoning.output_tokens",
                    "anthropic.usage.compaction.input_tokens",
                    "anthropic.usage.compaction.output_tokens",
                )
        }
    }

    private fun stubEvents() {
        stubFor(
            get(urlPathEqualTo("/v1/sessions/sess_1/events/stream"))
                .willReturn(ok(EVENTS_BODY).withHeader("Content-Type", "text/event-stream"))
        )
    }

    @Test
    fun eventStream_closedEarly_leavesStatusUnset() {
        stubEvents()

        client.beta().sessions().events().streamStreaming("sess_1").use { it.stream().findFirst() }

        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.sessions.events.stream")
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
    }

    /** An event stream has no end event, so the server ending it does not complete the call. */
    @Test
    fun eventStream_endedByServer_leavesStatusUnset() {
        stubEvents()

        val events =
            client.beta().sessions().events().streamStreaming("sess_1").use { it.stream().count() }

        assertThat(events).isEqualTo(2)
        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.sessions.events.stream")
        assertThat(span.hasEnded()).isTrue()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("gen_ai.provider.name")).isEqualTo("anthropic")
        assertThat(span.string("server.address")).isEqualTo("localhost")
        assertThat(span.long("http.response.status_code")).isEqualTo(200)
    }

    @Test
    fun messagesCreateStreaming_errorEvent_recordsEnvelopeType() {
        stubStream(STREAM_ERROR_BODY)

        assertThrows<SseException> {
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("overloaded_error")
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
    }

    /** A `ping` and the events before the first delta are no chunk of the response. */
    @Test
    fun messagesCreateStreaming_measuresTimeToFirstChunkToTheFirstContentBlockDelta() {
        stubStream(sse("""{"type":"ping"}""", *STREAM_EVENTS.toTypedArray()))

        client.messages().createStreaming(messageParams()).use { response ->
            val events = response.stream().iterator()
            val read = List(2) { events.next() }
            assertThat(read.last().isContentBlockStart()).isTrue()
            Thread.sleep(300)
            events.forEachRemaining {}
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isGreaterThanOrEqualTo(0.25)
    }

    @Test
    fun betaMessagesCreateStreaming_measuresTimeToFirstChunkToTheFirstContentBlockDelta() {
        stubStream()

        client.beta().messages().createStreaming(betaMessageParams()).use { response ->
            val events = response.stream().iterator()
            val read = List(2) { events.next() }
            assertThat(read.last().isContentBlockStart()).isTrue()
            Thread.sleep(300)
            events.forEachRemaining {}
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isGreaterThanOrEqualTo(0.25)
    }

    @ParameterizedTest
    @ValueSource(
        strings =
            [
                """{"type":"text_delta","text":"Hi"}""",
                """{"type":"input_json_delta","partial_json":"{}"}""",
                """{"type":"thinking_delta","thinking":"Hm"}""",
                """{"type":"signature_delta","signature":"sig"}""",
                """{"type":"citations_delta","citation":{"type":"char_location","cited_text":"Hi","document_index":0,"document_title":null,"start_char_index":0,"end_char_index":2,"file_id":null}}""",
            ]
    )
    fun messagesCreateStreaming_contentBlockDeltaOfAnyType_setsTimeToFirstChunk(delta: String) {
        stubStream(
            sse(
                *(STREAM_EVENTS.take(2) +
                        """{"type":"content_block_delta","index":0,"delta":$delta}""" +
                        """{"type":"content_block_stop","index":0}""" +
                        STREAM_EVENTS.takeLast(2))
                    .toTypedArray()
            )
        )

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNotNegative()
    }

    /** A Message whose blocks arrive whole has no chunk to time. */
    @Test
    fun messagesCreateStreaming_noContentBlockDelta_hasNoTimeToFirstChunk() {
        stubStream(
            sse(
                *(STREAM_EVENTS.take(1) +
                        """{"type":"content_block_start","index":0,"content_block":{"type":"text","text":"Hi there"}}""" +
                        """{"type":"content_block_stop","index":0}""" +
                        STREAM_EVENTS.takeLast(2))
                    .toTypedArray()
            )
        )

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isNull()
    }

    @Test
    fun messagesCreateStreaming_retried_measuresTimeToFirstChunkFromTheFirstAttempt() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("retry")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("retried")
                .willReturn(aResponse().withStatus(529).withHeader("Retry-After-Ms", "200"))
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("retry")
                .whenScenarioStateIs("retried")
                .willReturn(ok(STREAM_BODY).withHeader("Content-Type", "text/event-stream"))
        )
        val client = clientBuilder().maxRetries(1).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.double("gen_ai.response.time_to_first_chunk")).isGreaterThanOrEqualTo(0.2)
        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))).hasSize(2)
    }

    @Test
    fun asyncStream_readToTheEnd() {
        stubStream()
        val client = asyncClient()

        val done = CompletableFuture<Void>()
        client
            .messages()
            .createStreaming(messageParams())
            .subscribe {}
            .onCompleteFuture()
            .whenComplete { _, error ->
                if (error == null) done.complete(null) else done.completeExceptionally(error)
            }
        done.get(30, TimeUnit.SECONDS)

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }

    @Test
    fun asyncStream_closedAfterMessageStop_leavesStatusUnset() {
        stubStream()

        val response = asyncClient().messages().createStreaming(messageParams())
        response.subscribe { if (it.isMessageStop()) response.close() }
        response.onCompleteFuture().get(30, TimeUnit.SECONDS)

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }
}
