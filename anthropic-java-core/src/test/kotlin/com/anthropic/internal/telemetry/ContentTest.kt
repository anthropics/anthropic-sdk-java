package com.anthropic.internal.telemetry

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.RateLimitException
import com.anthropic.errors.SseException
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.messages.ContentBlockParam
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.MessageParam
import com.anthropic.models.messages.RawMessageStreamEvent
import com.anthropic.models.messages.TextBlockParam
import com.anthropic.models.messages.ThinkingBlockParam
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.context.Context
import io.opentelemetry.sdk.trace.data.SpanData
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ContentTest : TracingTestSupport() {

    private companion object {

        /** The name of each case of `telemetry/size-bound-cases.json`, and the case. */
        @JvmStatic
        fun sizeBoundCases(): List<Arguments> =
            ContentTest::class
                .java
                .getResourceAsStream("/telemetry/size-bound-cases.json")
                .use { jsonMapper().readTree(it) }
                .map { Arguments.of(it.get("name").asText(), it) }

        /** The name of each case of `telemetry/tool-span-size-bound-cases.json`, and the case. */
        @JvmStatic
        fun toolSpanSizeBoundCases(): List<Arguments> =
            ContentTest::class
                .java
                .getResourceAsStream("/telemetry/tool-span-size-bound-cases.json")
                .use { jsonMapper().readTree(it) }
                .map { Arguments.of(it.get("name").asText(), it) }
    }

    @Test
    fun metadataOnly_recordsNoContent() {
        stubMessage()
        client.messages().create(messageParams())
        stubStream()
        client.messages().createStreaming(messageParams()).use { it.stream().findFirst() }
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON.replace("rate_limit_error", "invalid_request_error"))
                )
        )
        assertThrows<AnthropicException> { client.messages().create(messageParams()) }
        stubToolLoop()
        client.beta().messages().toolRunner(toolParams()).toList()

        assertThat(recorder.spans).isNotEmpty().allSatisfy { span ->
            assertThat(attributesOf(span))
                .doesNotContainKeys(
                    "gen_ai.system_instructions",
                    "gen_ai.input.messages",
                    "gen_ai.output.messages",
                    "gen_ai.tool.call.arguments",
                    "gen_ai.tool.call.result",
                    "anthropic.tool_result.content",
                    "anthropic.content.truncated",
                )
            assertThat(span.attributeText())
                .doesNotContain("SECRET", "Hi there", "Paris", "Foggy", "slow down", "hello")
            assertThat(span.events).isEmpty()
        }
    }

    @Test
    fun environmentVariable_selectsContentModeOnlyForContent() {
        val mode = System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE")
        assumeTrue(mode != null)
        stubMessage()
        val client = clientBuilder(OpenTelemetryOptions.fromEnv().traces().contentMode()).build()

        client.messages().create(messageParams())

        val recorded = recorder.only().string("gen_ai.input.messages") != null
        assertThat(recorded).isEqualTo(mode.equals("content", ignoreCase = true))
    }

    @Test
    fun contentMode_recordsRequestAndResponseContent() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().create(messageParams())

        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions"))
            .isEqualTo("""[{"type":"text","content":"Be brief. SYSTEM-SECRET"}]""")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo(
                """[{"role":"user","parts":[{"type":"text","content":"hello USER-SECRET"}]}]"""
            )
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo("""[{"role":"assistant","parts":[{"type":"text","content":"Hi there"}]}]""")
        assertThat(attributesOf(span)).doesNotContainKey("anthropic.content.truncated")
    }

    @Test
    fun contentMode_stream_recordsTheOutputOfTheSameMessageNotStreamed() {
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        stubMessage(STREAMED_MESSAGE_JSON)
        client.messages().create(messageParams())
        stubStream()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val (notStreamed, streamed) = recorder.spans
        assertThat(streamed.string("gen_ai.output.messages"))
            .isEqualTo(STREAMED_OUTPUT_MESSAGES)
            .isEqualTo(notStreamed.string("gen_ai.output.messages"))
        assertThat(streamed.string("gen_ai.input.messages")).contains("hello USER-SECRET")
    }

    @Test
    fun contentMode_betaStream_recordsTheOutputOfTheSameMessageNotStreamed() {
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        stubMessage(STREAMED_MESSAGE_JSON)
        client.beta().messages().create(betaMessageParams())
        stubStream()

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }

        val (notStreamed, streamed) = recorder.spans
        assertThat(streamed.string("gen_ai.output.messages"))
            .isEqualTo(STREAMED_OUTPUT_MESSAGES)
            .isEqualTo(notStreamed.string("gen_ai.output.messages"))
        assertThat(streamed.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
    }

    @Test
    fun contentMode_asyncStream_recordsTheOutputOfTheSameMessageNotStreamed() {
        stubStream()
        val done = CompletableFuture<Void>()

        asyncClient(OpenTelemetryContentMode.CONTENT)
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
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(STREAMED_OUTPUT_MESSAGES)
        assertThat(span.string("gen_ai.input.messages")).contains("hello USER-SECRET")
    }

    @Test
    fun contentMode_stream_eventTheAccumulatorRejects_recordsNoOutput() {
        stubStream(MALFORMED_STREAM_BODY)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        val events = mutableListOf<RawMessageStreamEvent>()
        client.messages().createStreaming(messageParams()).use { it.stream().forEach(events::add) }

        // The application reads every event, the stray one too, and the call does not fail.
        assertThat(events).hasSize(STREAM_EVENTS.size + 1)
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(span.long("gen_ai.usage.output_tokens")).isEqualTo(7)
        assertThat(span.string("anthropic.message.stop_reason")).isEqualTo("tool_use")
        assertThat(span.string("gen_ai.input.messages")).isNotNull()
        assertThat(span.string("gen_ai.output.messages")).isNull()
    }

    @Test
    fun contentMode_stream_removesTheOldestPartsOverTheSizeBound() {
        // The streamed output counts toward the bound like one that is not streamed: it is
        // recorded whole, and the oldest input parts make room for it.
        val output = "y".repeat(450 * 1024)
        stubStream(
            sse(
                *STREAM_EVENTS.map { it.replace(""""text":" there"""", """"text":"$output"""") }
                    .toTypedArray()
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val big = "x".repeat(200 * 1024)
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addUserMessage("first $big")
                .addAssistantMessage("second $big")
                .addUserMessage("third $big")
                .addAssistantMessage("fourth")
                .build()

        client.messages().createStreaming(params).use { it.stream().forEach {} }

        // A message whose part went stays, with no parts.
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        val recorded = span.string("gen_ai.output.messages")!!
        assertThat(recorded).isEqualTo(STREAMED_OUTPUT_MESSAGES.replace(" there", output))
        val input = span.string("gen_ai.input.messages")!!
        assertThat(input)
            .startsWith(
                """[{"role":"user","parts":[]},{"role":"assistant","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"third """
            )
            .endsWith("""{"role":"assistant","parts":[{"type":"text","content":"fourth"}]}]""")
            .doesNotContain("first ", "second ", "truncated", "omitted_messages")
        assertThat(input.length + recorded.length).isLessThan(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_stream_removesTheFirstOutputPartsOverTheSizeBound() {
        // The accumulated Message is reduced like one that is not streamed: with no input part
        // left, its first part goes, and the tool call after it is whole.
        val text = "y".repeat(600 * 1024)
        val location = "z".repeat(600 * 1024)
        stubStream(
            sse(
                *STREAM_EVENTS.map {
                        it.replace(""""text":" there"""", """"text":"$text"""")
                            .replace("Paris", location)
                    }
                    .toTypedArray()
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addUserMessage("first")
                .addUserMessage("second " + "x".repeat(500 * 1024))
                .build()

        client.messages().createStreaming(params).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        val input = span.string("gen_ai.input.messages")!!
        assertThat(input).isEqualTo("""[{"role":"user","parts":[]},{"role":"user","parts":[]}]""")
        val recorded = span.string("gen_ai.output.messages")!!
        assertThat(recorded)
            .isEqualTo(
                """[{"role":"assistant","parts":[{"type":"tool_call","id":"toolu_1",""" +
                    """"name":"get_weather","arguments":{"location":"$location"}}]}]"""
            )
        assertThat(input.length + recorded.length).isLessThan(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_stream_oneOutputPartOverTheSizeBound_isCutFromItsEnd() {
        val huge = "y".repeat(CONTENT_LIMIT + 1)
        stubStream(
            sse(
                *(STREAM_EVENTS.take(5) + STREAM_EVENTS.drop(9))
                    .map { it.replace(""""text":" there"""", """"text":"$huge"""") }
                    .toTypedArray()
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        // The one part of the accumulated Message is over the limit by itself: after every part
        // of the request, the end of its text goes, as much as it takes.
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        val kept = partContents(span.string("gen_ai.output.messages")!!).single()
        assertThat("Hi$huge").startsWith(kept)
        assertThat(contentSize(span)).isEqualTo(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_streamEndedBeforeMessageStop_recordsTheOutputThatArrived() {
        stubStream(TRUNCATED_STREAM_BODY)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        // The text block never got its `content_block_stop`: its deltas count all the same.
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("api_error")
        assertThat(span.string("gen_ai.input.messages")).isNotNull()
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
        assertThat(span.string("anthropic.message.stop_reason")).isNull()
        assertThat(span.long("gen_ai.usage.output_tokens")).isNull()
    }

    @Test
    fun contentMode_betaStreamEndedBeforeMessageStop_recordsTheOutputThatArrived() {
        stubStream(TRUNCATED_STREAM_BODY)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
    }

    @Test
    fun contentMode_asyncStreamEndedBeforeMessageStop_recordsTheOutputThatArrived() {
        stubStream(TRUNCATED_STREAM_BODY)

        asyncClient(OpenTelemetryContentMode.CONTENT)
            .messages()
            .createStreaming(messageParams())
            .subscribe {}
            .onCompleteFuture()
            .handle { _, _ -> }
            .get(30, TimeUnit.SECONDS)

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("api_error")
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
    }

    @Test
    fun contentMode_streamEndedInsideAToolUseBlock_recordsTheInputTheBlockStartedWith() {
        stubStream(sse(*STREAM_EVENTS.take(7).toTypedArray()))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        // The accumulator parses the input of a tool call once its block is complete.
        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo(
                """[{"role":"assistant","parts":[{"type":"text","content":"Hi there"},""" +
                    """{"type":"tool_call","id":"toolu_1","name":"get_weather","arguments":{}}]}]"""
            )
    }

    @Test
    fun contentMode_streamEndedByAnErrorEvent_recordsTheOutputThatArrived() {
        stubStream(TRUNCATED_STREAM_BODY + ERROR_EVENT)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        assertThrows<SseException> {
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("error.type")).isEqualTo("overloaded_error")
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
    }

    @Test
    fun contentMode_streamClosedEarly_recordsTheOutputThatArrived() {
        stubStream()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { response ->
            val events = response.stream().iterator()
            repeat(3) { events.next() }
        }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("error.type")).isNull()
        assertThat(span.string("gen_ai.input.messages")).isNotNull()
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
    }

    @Test
    fun contentMode_asyncStreamClosedEarly_recordsTheOutputThatArrived() {
        stubStream()

        val response =
            asyncClient(OpenTelemetryContentMode.CONTENT)
                .messages()
                .createStreaming(messageParams())
        response.subscribe { if (it.isContentBlockDelta()) response.close() }
        response.onCompleteFuture().get(30, TimeUnit.SECONDS)

        val span = recorder.await(1).single()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
    }

    @Test
    fun contentMode_streamEndedBeforeMessageStart_recordsNoOutput() {
        stubStream(ERROR_EVENT)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        assertThrows<SseException> {
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        val span = recorder.only()
        assertThat(span.string("error.type")).isEqualTo("overloaded_error")
        assertThat(span.string("gen_ai.input.messages")).isNotNull()
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
    }

    @Test
    fun contentMode_streamEndedAfterAnEventTheAccumulatorRejects_recordsNoOutput() {
        stubStream(sse(*(STREAM_EVENTS.take(4) + STRAY_DELTA_EVENT).toTypedArray()))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
    }

    @Test
    fun contentMode_streamEndedBeforeMessageStop_cutsItsOutputToTheSizeBound() {
        val huge = "y".repeat(CONTENT_LIMIT + 1)
        stubStream(TRUNCATED_STREAM_BODY.replace(""""text":"Hi"""", """"text":"$huge""""))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        assertThat(huge).startsWith(partContents(span.string("gen_ai.output.messages")!!).single())
        assertThat(contentSize(span)).isEqualTo(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_errorResponse_recordsRequestContent() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        assertThrows<RateLimitException> { client.messages().create(messageParams()) }

        val span = recorder.only()
        assertThat(span.string("gen_ai.input.messages")).isNotNull()
        assertThat(span.string("gen_ai.output.messages")).isNull()
    }

    @Test
    fun contentMode_removesTheOldestPartsOverTheSizeBound() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val big = "x".repeat(600 * 1024)
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addUserMessage("first $big")
                .addAssistantMessage("second $big")
                .addUserMessage("third $big")
                .addAssistantMessage("fourth")
                .build()

        client.messages().create(params)

        // A message whose part went stays, with no parts.
        val span = recorder.only()
        val input = span.string("gen_ai.input.messages")!!
        assertThat(input)
            .startsWith(
                """[{"role":"user","parts":[]},{"role":"assistant","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"third """
            )
            .endsWith("""{"role":"assistant","parts":[{"type":"text","content":"fourth"}]}]""")
            .doesNotContain("first ", "second ", "truncated", "omitted_messages")
        assertThat(input.length).isLessThan(CONTENT_LIMIT)
        // The output is whole: the oldest parts of the input alone made room.
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo("""[{"role":"assistant","parts":[{"type":"text","content":"Hi there"}]}]""")
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_countsAToolResultsBlocksTowardTheSizeBound() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        // The result's text is short: its citation and its search result make it too large.
        val big = "x".repeat(600 * 1024)
        val content =
            jsonMapper()
                .readTree(
                    """
                    [{"type": "tool_result", "tool_use_id": "toolu_1", "content": [
                      {"type": "text", "text": "cited",
                       "citations": [{"type": "char_location", "cited_text": "$big", "document_index": 0, "document_title": "t", "start_char_index": 0, "end_char_index": 5}]},
                      {"type": "search_result", "source": "https://example.com", "title": "Found",
                       "content": [{"type": "text", "text": "$big"}]}
                    ]}]
                    """
                )
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addMessage(
                    MessageParam.builder()
                        .role(MessageParam.Role.USER)
                        .content(JsonValue.fromJsonNode(content))
                        .build()
                )
                .addUserMessage("later")
                .build()

        client.messages().create(params)

        // The whole part is removed, and nothing in it is shortened.
        val span = recorder.only()
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo(
                """[{"role":"user","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"later"}]}]"""
            )
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    /** A Message as the API returns it, whose content is a text block for each of [texts]. */
    private fun textsMessageJson(vararg texts: String): String =
        """
        {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
         "content": [${texts.joinToString(",") { """{"type": "text", "text": "$it"}""" }}],
         "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
        """

    @Test
    fun contentMode_removesTheOutputsFirstPartsOverTheSizeBound() {
        val big = "y".repeat(600 * 1024)
        stubMessage(textsMessageJson("one $big", "two $big", "three"))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system("Be brief.")
                .addUserMessage("first")
                .addUserMessage("second " + "x".repeat(600 * 1024))
                .build()

        client.messages().create(params)

        // No part of the request is left and the span is still over the limit, so the output's
        // first part goes. That is enough, so the parts after it are whole.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]},{"role":"user","parts":[]}]""")
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(outputOf("two $big", "three"))
        assertThat(contentSize(span)).isLessThan(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_oneInputPartOverTheSizeBound_leavesItsMessageWithoutParts() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addUserMessage("x".repeat(CONTENT_LIMIT + 1))
                .build()

        client.messages().create(params)

        // An output part follows the input's one part, so it is not cut: it goes, and its message
        // stays. That brings the span under the limit, so the output is whole.
        val span = recorder.only()
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo("""[{"role":"assistant","parts":[{"type":"text","content":"Hi there"}]}]""")
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_oneOutputPartOverTheSizeBound_isCutFromItsEnd() {
        val huge = "y".repeat(CONTENT_LIMIT + 1)
        stubMessage(textsMessageJson(huge))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().create(messageParams())

        // After every part of the request, the end of the one output part's text goes.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        val kept = partContents(span.string("gen_ai.output.messages")!!).single()
        assertThat(huge).startsWith(kept)
        assertThat(contentSize(span)).isEqualTo(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @ParameterizedTest
    @ValueSource(longs = [100L, 500L, 1500L])
    fun maxContentBytes_cutsTheContentAtTheBoundInPlaceOfTheDefault(maxContentBytes: Long) {
        val large = "y".repeat(2000)
        stubMessage(textsMessageJson(large))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes).build()

        client.messages().create(messageParams())

        // The content is far under the default bound. After every part of the request, the end of
        // the one output part's text goes, as much as it takes.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        val kept = partContents(span.string("gen_ai.output.messages")!!).single()
        assertThat(large).startsWith(kept).isNotEqualTo(kept)
        assertThat(contentSize(span)).isEqualTo(maxContentBytes.toInt())
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun maxContentBytes_stream_cutsTheOutputAtTheBound() {
        val large = "y".repeat(2000)
        stubStream(
            sse(
                *(STREAM_EVENTS.take(5) + STREAM_EVENTS.drop(9))
                    .map { it.replace(""""text":" there"""", """"text":"$large"""") }
                    .toTypedArray()
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes = 300).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        val kept = partContents(span.string("gen_ai.output.messages")!!).single()
        assertThat("Hi$large").startsWith(kept).isNotEqualTo(kept)
        assertThat(contentSize(span)).isEqualTo(300)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun maxContentBytes_errorResponse_cutsTheRequestAtTheBound() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes = 60).build()

        assertThrows<RateLimitException> { client.messages().create(messageParams()) }

        // The system prompt goes whole, and then the end of the one message's text.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        val kept = partContents(span.string("gen_ai.input.messages")!!).single()
        assertThat("hello USER-SECRET").startsWith(kept).isNotEqualTo(kept)
        assertThat(contentSize(span)).isEqualTo(60)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun maxContentBytes_overTheContent_leavesItWhole() {
        stubMessage()
        val whole = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val bounded =
            clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes = 100_000).build()

        whole.messages().create(messageParams())
        bounded.messages().create(messageParams())

        val (expected, actual) = recorder.await(2)
        assertThat(contentSize(actual)).isEqualTo(contentSize(expected)).isLessThan(100_000)
        assertThat(attributesOf(actual)).doesNotContainKey("anthropic.content.truncated")
    }

    @Test
    fun maxContentBytes_overTheDefault_keepsContentOverTheDefaultBound() {
        val huge = "y".repeat(CONTENT_LIMIT + 1)
        stubMessage(textsMessageJson(huge))
        val client =
            clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes = Long.MAX_VALUE)
                .build()

        client.messages().create(messageParams())

        val span = recorder.only()
        assertThat(partContents(span.string("gen_ai.output.messages")!!)).containsExactly(huge)
        assertThat(span.string("gen_ai.input.messages")).contains("hello USER-SECRET")
        assertThat(attributesOf(span)).doesNotContainKey("anthropic.content.truncated")
    }

    @Test
    fun maxContentBytes_metadataOnly_recordsNoContent() {
        stubMessage()
        val client = clientBuilder(maxContentBytes = 100).build()

        client.messages().create(messageParams())

        assertThat(attributesOf(recorder.only()))
            .doesNotContainKeys(
                "gen_ai.system_instructions",
                "gen_ai.input.messages",
                "gen_ai.output.messages",
                "anthropic.content.truncated",
            )
    }

    @ParameterizedTest
    @CsvSource(
        // The renamed property selects the content mode, and the new one sets the bound.
        "anthropic.openTelemetryTracesContentMode, content, , true, false",
        "anthropic.openTelemetryTracesContentMode, CONTENT, 120, true, true",
        // A value that is no whole number of at least 1 leaves the default bound.
        "anthropic.openTelemetryTracesContentMode, content, 0, true, false",
        "anthropic.openTelemetryTracesContentMode, content, sixty, true, false",
        // The property's old name is not read.
        "anthropic.openTelemetryContentMode, content, 120, false, false",
    )
    @ResourceLock(Resources.SYSTEM_PROPERTIES)
    fun fromEnv_appliesTheSystemPropertiesOfTheTraces(
        contentModeProperty: String,
        contentMode: String,
        maxContentBytes: String?,
        recordsContent: Boolean,
        truncates: Boolean,
    ) {
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE") == null)
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES") == null)
        System.setProperty(contentModeProperty, contentMode)
        maxContentBytes?.let {
            System.setProperty("anthropic.openTelemetryTracesMaxContentBytes", it)
        }
        try {
            stubMessage()
            val fromEnv =
                AnthropicOkHttpClient.builder()
                    .openTelemetry(options())
                    .fromEnv()
                    .baseUrl(baseUrl)
                    .apiKey("my-anthropic-api-key")
                    .maxRetries(0)
                    .build()

            fromEnv.messages().create(messageParams())
            // A client built without `fromEnv` ignores the properties.
            client.messages().create(messageParams())

            val (span, ignoring) = recorder.await(2)
            assertThat(span.string("gen_ai.output.messages") != null).isEqualTo(recordsContent)
            assertThat(attributesOf(span).containsKey("anthropic.content.truncated"))
                .isEqualTo(truncates)
            if (truncates) {
                assertThat(contentSize(span)).isLessThanOrEqualTo(120)
            }
            assertThat(attributesOf(ignoring))
                .doesNotContainKeys("gen_ai.output.messages", "anthropic.content.truncated")
        } finally {
            System.clearProperty(contentModeProperty)
            System.clearProperty("anthropic.openTelemetryTracesMaxContentBytes")
        }
    }

    @Test
    fun environmentVariable_setsTheMostContentOfASpan() {
        val variable = System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES")
        assumeTrue(variable != null)
        val traces = OpenTelemetryOptions.fromEnv().traces()
        val large = "y".repeat(2000)
        stubMessage(textsMessageJson(large))
        val client =
            clientBuilder(OpenTelemetryContentMode.CONTENT, traces.maxContentBytes().getOrNull())
                .build()

        client.messages().create(messageParams())

        val valid = variable.toLongOrNull()?.takeIf { it >= 1 }
        assertThat(traces.maxContentBytes()).isEqualTo(Optional.ofNullable(valid))
        val truncated = attributesOf(recorder.only()).containsKey("anthropic.content.truncated")
        if (valid != null && valid <= large.length) {
            assertThat(truncated).isTrue()
        }
        if (valid == null || valid >= 2 * large.length) {
            assertThat(truncated).isFalse()
        }
    }

    @Test
    fun contentMode_removesAToolCallWholeAndThenCutsTheTextAfterIt() {
        // Each of these is two chars and four bytes, which the cut does not come between.
        val text = "😀".repeat(CONTENT_LIMIT / 2)
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": [{"type": "tool_use", "id": "toolu_1", "name": "f", "input": {"a": 1}},
                         {"type": "text", "text": "$text"}],
             "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().create(messageParams())

        val span = recorder.only()
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        val kept = partContents(span.string("gen_ai.output.messages")!!).single()
        assertThat(text).startsWith(kept)
        assertThat(kept.length % 2).isEqualTo(0)
        assertThat(contentSize(span)).isBetween(CONTENT_LIMIT - 3, CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_systemInstructionsOverTheSizeBound_goFirstAndWhole() {
        stubMessage(textsMessageJson("one", "two"))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val huge = "s".repeat(CONTENT_LIMIT + 1)
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system(huge)
                .addUserMessage("first")
                .addUserMessage("second")
                .build()

        client.messages().create(params)

        // The system instructions are the oldest part. They are not cut, because other parts
        // follow them. The emptied list stays in its attribute, and the rest is whole.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(keptTexts(span.string("gen_ai.input.messages")!!))
            .containsExactly("first", "second")
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(outputOf("one", "two"))
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_theNewestPartsAreWholeWhileOlderPartsCanGo() {
        val big = "y".repeat(600 * 1024)
        stubMessage(textsMessageJson(big))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val system = "s".repeat(600 * 1024)
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system(system)
                .addUserMessage("first")
                .build()

        client.messages().create(params)

        // Without the system instructions the span is under the limit, so nothing else goes.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(keptTexts(span.string("gen_ai.input.messages")!!)).containsExactly("first")
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(outputOf(big))
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_errorResponse_removesTheFirstPartsOfTheRequestOverTheSizeBound() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val texts = listOf("a", "b", "c").map { it.repeat(600 * 1024) }
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .systemOfTextBlockParams(texts.map { TextBlockParam.builder().text(it).build() })
                .addUserMessage("first")
                .build()

        assertThrows<RateLimitException> { client.messages().create(params) }

        // The request's content is fitted without a response: the first two parts of the system
        // instructions go, and the rest is whole.
        val span = recorder.only()
        assertThat(partContents(span.string("gen_ai.system_instructions")!!))
            .containsExactly(texts[2])
        assertThat(keptTexts(span.string("gen_ai.input.messages")!!)).containsExactly("first")
        assertThat(span.string("gen_ai.output.messages")).isNull()
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_errorResponse_cutsTheLastTextPartOfTheRequestOverTheSizeBound() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val huge = "x".repeat(CONTENT_LIMIT + 1)
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system("Be brief.")
                .addUserMessage("first")
                .addUserMessage(huge)
                .build()

        assertThrows<RateLimitException> { client.messages().create(params) }

        // No response follows, so the newest input part is the last part, and its end goes.
        val span = recorder.only()
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        val input = span.string("gen_ai.input.messages")!!
        assertThat(input).startsWith("""[{"role":"user","parts":[]},{"role":"user","parts":[{""")
        val kept = jsonMapper().readTree(input)[1].get("parts").single().get("content").asText()
        assertThat(huge).startsWith(kept)
        assertThat(contentSize(span)).isEqualTo(CONTENT_LIMIT)
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_recordsStopSequencesAsAnArray() {
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val params = messageParams().toBuilder().stopSequences(listOf("STOP-SECRET", "###")).build()
        val betaParams = betaMessageParams().toBuilder().addStopSequence("STOP-SECRET").build()

        stubMessage()
        client.messages().create(params)
        client.beta().messages().create(betaParams)
        stubStream()
        client.messages().createStreaming(params).use { it.stream().forEach {} }
        client.beta().messages().createStreaming(betaParams).use { it.stream().forEach {} }

        val (unary, betaUnary, stream, betaStream) = recorder.spans
        assertThat(unary.strings("gen_ai.request.stop_sequences"))
            .containsExactly("STOP-SECRET", "###")
        assertThat(stream.strings("gen_ai.request.stop_sequences"))
            .containsExactly("STOP-SECRET", "###")
        assertThat(betaUnary.strings("gen_ai.request.stop_sequences"))
            .containsExactly("STOP-SECRET")
        assertThat(betaStream.strings("gen_ai.request.stop_sequences"))
            .containsExactly("STOP-SECRET")
        assertThat(attributesOf(unary)).doesNotContainKey("anthropic.content.truncated")
    }

    @Test
    fun contentMode_noStopSequences_recordsNone() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().create(messageParams())
        client.messages().create(messageParams().toBuilder().stopSequences(emptyList()).build())

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(span.string("gen_ai.input.messages")).isNotNull()
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.request.stop_sequences")
        }
    }

    @Test
    fun metadataOnly_recordsNoStopSequences() {
        val params = messageParams().toBuilder().addStopSequence("STOP-SECRET").build()
        stubMessage()
        client.messages().create(params)
        stubStream()
        client.messages().createStreaming(params).use { it.stream().forEach {} }

        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.request.stop_sequences")
            assertThat(span.attributeText()).doesNotContain("STOP-SECRET")
        }
    }

    @Test
    fun contentMode_stopSequencesCountTowardTheSizeBound() {
        // The two messages fit the limit alone, and only the newer one's part beside the stop
        // sequences.
        stubMessage(textsMessageJson("one"))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val stops = listOf("a".repeat(225 * 1024), "b".repeat(225 * 1024))
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .addUserMessage("first " + "x".repeat(225 * 1024))
                .addUserMessage("second " + "x".repeat(225 * 1024))
                .stopSequences(stops)
                .build()

        client.messages().create(params)

        val span = recorder.only()
        assertThat(span.strings("gen_ai.request.stop_sequences")).isEqualTo(stops)
        val input = span.string("gen_ai.input.messages")!!
        assertThat(input)
            .startsWith(
                """[{"role":"user","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"second """
            )
        assertThat(span.string("gen_ai.output.messages")).isEqualTo(outputOf("one"))
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_stopSequencesAtTheSizeBound_stayWholeAndEmptyTheRest() {
        stubMessage(STREAMED_MESSAGE_JSON)
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val stops = listOf("s".repeat(CONTENT_LIMIT - 1), "t")
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system("Be brief.")
                .addUserMessage("first")
                .stopSequences(stops)
                .build()

        client.messages().create(params)

        // The stop sequences are not over the limit alone, so they stay and leave no room: every
        // part goes, the last one too, which is a tool call, and what stays is over the limit.
        val span = recorder.only()
        assertThat(span.strings("gen_ai.request.stop_sequences")).isEqualTo(stops)
        assertThat(span.string("gen_ai.system_instructions")).isEqualTo("[]")
        assertThat(span.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo("""[{"role":"assistant","parts":[]}]""")
        assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_stopSequencesOverTheSizeBound_areLeftOutWhole() {
        stubMessage(textsMessageJson("one"))
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val params =
            MessageCreateParams.builder()
                .model("claude-requested")
                .maxTokens(1)
                .system("Be brief.")
                .addUserMessage("first")
                .stopSequences(listOf("s".repeat(CONTENT_LIMIT), "t"))
                .build()

        client.messages().create(params)
        stubStream()
        client.messages().createStreaming(params).use { it.stream().forEach {} }

        // Together the stop sequences are one over the limit, so the span goes without them. They
        // then take no room, and the rest stays whole.
        assertThat(recorder.spans).hasSize(2).allSatisfy { span ->
            assertThat(attributesOf(span)).doesNotContainKey("gen_ai.request.stop_sequences")
            assertThat(span.string("gen_ai.system_instructions"))
                .isEqualTo("""[{"type":"text","content":"Be brief."}]""")
            assertThat(span.string("gen_ai.input.messages"))
                .isEqualTo(jsonMapper().writeValueAsString(textMessages("first")))
            assertThat(attributesOf(span)).containsEntry("anthropic.content.truncated", true)
        }
        val (unary, stream) = recorder.spans
        assertThat(unary.string("gen_ai.output.messages")).isEqualTo(outputOf("one"))
        assertThat(stream.string("gen_ai.output.messages")).isNotNull()
    }

    private fun textMessages(vararg texts: String): List<ObjectNode> =
        texts.map { text ->
            jsonMapper().createObjectNode().put("role", "user").apply {
                putArray("parts").addObject().put("type", "text").put("content", text)
            }
        }

    /** The `content` of each part in [json], a list of parts or of one message with parts. */
    private fun partContents(json: String): List<String> {
        val list = jsonMapper().readTree(json)
        val parts = list.path(0).get("parts") ?: list
        return parts.map { it.get("content").asText() }
    }

    /** The bytes of UTF-8 of the content attributes of [span] that can be reduced. */
    private fun contentSize(span: SpanData): Int =
        listOf("gen_ai.system_instructions", "gen_ai.input.messages", "gen_ai.output.messages")
            .sumOf { utf8Bytes(span.string(it)) }

    /** The bytes that [text] is in UTF-8, as an exporter encodes it. Zero for no text. */
    private fun utf8Bytes(text: String?): Int = text?.toByteArray(Charsets.UTF_8)?.size ?: 0

    /** The text of each message in [json], each of which has one part, a text part. */
    private fun keptTexts(json: String): List<String> =
        jsonMapper().readTree(json).map { it.get("parts").single().get("content").asText() }

    /** The JSON of [node], or `null` for an attribute that the span does not have. */
    private fun jsonOrNull(node: JsonNode?): String? =
        node?.takeUnless { it.isNull }?.let { jsonMapper().writeValueAsString(it) }

    /** The parts of `gen_ai.system_instructions` for [fitContent]: those of [parts], a list. */
    private fun systemParts(parts: JsonNode?): PartList? =
        parts?.takeUnless { it.isNull }?.let { PartList("[", "]", it.map { part -> Part(part) }) }

    /** The messages of an attribute for [fitContent]: those of [messages], a list. */
    private fun messageParts(messages: JsonNode?): List<PartList>? =
        messages
            ?.takeUnless { it.isNull }
            ?.map { message ->
                PartList(
                    """{"role":"${message.get("role").asText()}","parts":[""",
                    "]}",
                    message.get("parts").map { Part(it) },
                )
            }

    /** The messages for [fitContent] of the user's [texts], each the one part of its message. */
    private fun textMessageParts(vararg texts: String): List<PartList> =
        messageParts(jsonMapper().valueToTree(textMessages(*texts)))!!

    /**
     * What [fitContent] makes of each case of `telemetry/size-bound-cases.json`: content over a
     * small bound, in the conventions' format. The SDKs of the other languages have the same cases.
     * The bounds and sizes are in bytes of UTF-8, and the sizes are checked against the encoded
     * attributes. The stop sequences stay as the input has them, unless the case expects `null` for
     * them: the span then goes without them.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("sizeBoundCases")
    fun fitContent_sharedCase(name: String, case: JsonNode) {
        val input = case.get("input")
        val stopSequences = input.get("stop_sequences")?.map { it.asText() }.orEmpty()

        val fitted =
            fitContent(
                jsonMapper(),
                case.get("bound").asInt(),
                stopSequences.sumOf { utf8Bytes(it) },
                systemParts(input.get("system_instructions")),
                messageParts(input.get("input_messages")),
                messageParts(input.get("output_messages")),
            )

        val expected = case.get("expected")
        assertThat(fitted.keepsUnreduced)
            .describedAs(name)
            .isEqualTo(expected.get("stop_sequences")?.isNull != true)
        assertThat(fitted.systemInstructions)
            .isEqualTo(jsonOrNull(expected.get("system_instructions")))
        assertThat(fitted.inputMessages).isEqualTo(jsonOrNull(expected.get("input_messages")))
        assertThat(fitted.outputMessages).isEqualTo(jsonOrNull(expected.get("output_messages")))
        assertThat(fitted.truncated).isEqualTo(case.get("expected_truncated").asBoolean())
        val size =
            (if (fitted.keepsUnreduced) stopSequences.sumOf { utf8Bytes(it) } else 0) +
                utf8Bytes(fitted.systemInstructions) +
                utf8Bytes(fitted.inputMessages) +
                utf8Bytes(fitted.outputMessages)
        assertThat(size).isEqualTo(case.get("size_after").asInt())
        // The size is counted when anything was over the bound, and is no less otherwise.
        if (fitted.truncated) {
            assertThat(fitted.size).isEqualTo(size)
        } else {
            assertThat(fitted.size).isBetween(size, MAX_UTF8_BYTES_PER_CHAR * size)
        }
        case.get("size_before")?.let { before ->
            val whole = input.fields().asSequence().filter { it.key != "stop_sequences" }
            assertThat(whole.sumOf { utf8Bytes(jsonOrNull(it.value)) }).isEqualTo(before.asInt())
        }
    }

    /**
     * This may change. The last `text` part stays, with an empty `content`, when the span is over
     * the limit even with none of its `content`. The other reading is that the part then goes.
     */
    @Test
    fun fitContent_lastTextPartWithNoRoomForAnyContent_staysWithEmptyContent() {
        // The stop sequence is not over the limit alone, so it stays, and it leaves no room.
        val stopSequence = "STOP-" + "x".repeat(95)

        val fitted =
            fitContent(
                jsonMapper(),
                limit = 100,
                unreducedSize = stopSequence.length,
                system = null,
                input = textMessageParts("question"),
                output = null,
            )

        assertThat(fitted.inputMessages)
            .isEqualTo("""[{"role":"user","parts":[{"type":"text","content":""}]}]""")
        assertThat(fitted.systemInstructions).isNull()
        assertThat(fitted.outputMessages).isNull()
        assertThat(fitted.truncated).isTrue()
        assertThat(fitted.size).isGreaterThan(100)

        // A text part that is empty to begin with stays as it is, and nothing was cut.
        val empty =
            fitContent(jsonMapper(), 100, stopSequence.length, null, textMessageParts(""), null)
        assertThat(empty.inputMessages).isEqualTo(fitted.inputMessages)
        assertThat(empty.truncated).isFalse()
    }

    @Test
    fun fitContent_measuresBytesOfUtf8NotStringLength() {
        // Each message is 200,000 chars but 600 kB of UTF-8, so only by length do the two fit.
        val wide = "€".repeat(200_000)
        val messages = textMessages("0$wide", "1$wide")

        val fitted =
            fitContent(
                jsonMapper(),
                CONTENT_LIMIT,
                0,
                null,
                messageParts(jsonMapper().valueToTree(messages)),
                null,
            )

        assertThat(fitted.inputMessages)
            .isEqualTo(
                """[{"role":"user","parts":[]},""" +
                    jsonMapper().writeValueAsString(messages.drop(1)).drop(1)
            )
        assertThat(fitted.truncated).isTrue()
        assertThat(fitted.size).isEqualTo(utf8Bytes(fitted.inputMessages)).isLessThan(CONTENT_LIMIT)
    }

    @Test
    fun utf8Length_isTheBytesOfTheEncodedText() {
        val texts = listOf("", "plain", "é", "€", "😀", "a\u007f\u0080߿ࠀ￿😀z")

        assertThat(texts.map { utf8Length(it) }).isEqualTo(texts.map { utf8Bytes(it) })
        assertThat(texts.map { utf8Length(it) }.take(5)).containsExactly(0, 5, 2, 3, 4)
        // Half a surrogate pair counts as two bytes, and is encoded as one, a `?`.
        assertThat(utf8Length("\ud83d")).isEqualTo(2)
        assertThat(utf8Length("a\ude00\ud83db")).isEqualTo(6)
        assertThat(utf8Bytes(jsonMapper().writeValueAsString("a\ude00\ud83db"))).isEqualTo(6)
    }

    @Test
    fun fitContent_keepsContentThatFillsTheLimitInBytesAndCutsItWhenOneByteOver() {
        val text = "é€😀".repeat(10)
        val whole = jsonMapper().writeValueAsString(textMessages(text))
        assertThat(utf8Bytes(whole)).isEqualTo(whole.length + 50)
        fun fit(limit: Int) = fitContent(jsonMapper(), limit, 0, null, textMessageParts(text), null)

        // Three bytes for each char fit, so nothing is counted: the size is that upper bound.
        val byLength = fit(3 * (whole.length + 4))
        assertThat(byLength.inputMessages).isEqualTo(whole)
        assertThat(byLength.truncated).isFalse()
        assertThat(byLength.size).isEqualTo(3 * whole.length)

        // The limit is inclusive, and it is in bytes.
        val exact = fit(utf8Bytes(whole))
        assertThat(exact.inputMessages).isEqualTo(whole)
        assertThat(exact.truncated).isFalse()
        assertThat(exact.size).isEqualTo(utf8Bytes(whole))

        // One byte less has no room for the last character, which is four bytes and two chars.
        val oneOver = fit(utf8Bytes(whole) - 1)
        assertThat(oneOver.inputMessages)
            .isEqualTo(jsonMapper().writeValueAsString(textMessages(text.dropLast(2))))
        assertThat(oneOver.truncated).isTrue()
        assertThat(oneOver.size).isEqualTo(utf8Bytes(whole) - 4)
    }

    @Test
    fun requestContent_keepsStopSequencesThatFillTheLimitInBytesAndNotWhenOneByteOver() {
        val stopSequences = listOf("é".repeat(20), "€".repeat(20))
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()
        val over = recorder.tracerProvider.get("test").spanBuilder("over").startSpan()

        // 40 chars, and 100 bytes.
        val kept = setRequestContent(span, jsonMapper(), null, null, stopSequences, limit = 100)
        val leftOut = setRequestContent(over, jsonMapper(), null, null, stopSequences, limit = 99)
        span.end()
        over.end()

        assertThat(kept.unreducedSize).isEqualTo(100)
        assertThat(kept.fitted.keepsUnreduced).isTrue()
        assertThat(kept.fitted.truncated).isFalse()
        assertThat(leftOut.fitted.keepsUnreduced).isFalse()
        assertThat(leftOut.fitted.truncated).isTrue()
        val (first, second) = recorder.await(2)
        assertThat(first.strings("gen_ai.request.stop_sequences")).isEqualTo(stopSequences)
        assertThat(attributesOf(second))
            .doesNotContainKey("gen_ai.request.stop_sequences")
            .containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun responseContent_thatFitsByLengthButNotInBytes_isFittedWithTheRequest() {
        val question = "q".repeat(20)
        val answer = "€".repeat(40)
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        // The request's 76 bytes and the output's 101 chars are within 220. Its 181 bytes are not.
        val request =
            setRequestContent(span, jsonMapper(), null, requestMessages(question), limit = 220)
        assertThat(request.fitted.truncated).isFalse()
        setResponseContent(span, jsonMapper(), request, responseParts(answer))
        span.end()

        val together =
            fitContent(
                jsonMapper(),
                220,
                0,
                null,
                textMessageParts(question),
                messageParts(jsonMapper().readTree(outputOf(answer))),
            )
        val recorded = recorder.only()
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
            .isEqualTo(together.inputMessages)
        assertThat(recorded.string("gen_ai.output.messages"))
            .isEqualTo(outputOf(answer))
            .isEqualTo(together.outputMessages)
        assertThat(contentSize(recorded)).isEqualTo(28 + 181).isEqualTo(together.size)
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun fitContent_keepsEveryPartThatFitsTheLimitExactly() {
        val messages = textMessages("old", "b".repeat(1000), "c".repeat(1000))
        val withoutOldest =
            """[{"role":"user","parts":[]},""" +
                jsonMapper().writeValueAsString(messages.drop(1)).drop(1)

        // Nothing is set aside for a marker: the newest two parts fill the limit to the character.
        val exact =
            fitContent(
                jsonMapper(),
                withoutOldest.length,
                0,
                null,
                textMessageParts("old", "b".repeat(1000), "c".repeat(1000)),
                null,
            )
        assertThat(exact.inputMessages).isEqualTo(withoutOldest)
        assertThat(exact.inputMessages).doesNotContain("old", "truncated", "omitted_messages")
        assertThat(exact.truncated).isTrue()

        val oneLess =
            fitContent(
                jsonMapper(),
                withoutOldest.length - 1,
                0,
                null,
                textMessageParts("old", "b".repeat(1000), "c".repeat(1000)),
                null,
            )
        assertThat(oneLess.inputMessages)
            .isEqualTo(
                """[{"role":"user","parts":[]},{"role":"user","parts":[]},""" +
                    jsonMapper().writeValueAsString(messages.drop(2)).drop(1)
            )
        assertThat(oneLess.truncated).isTrue()
    }

    @Test
    fun fitContent_asksNoJsonOfThePartsBeforeTheFirstThatDoesNotFit() {
        val mapper = SpyMapper()
        val big = "a".repeat(225_000)

        val fitted =
            fitContent(
                mapper,
                CONTENT_LIMIT,
                0,
                null,
                textMessageParts("0$big", "1$big", "2$big", "3$big", "4$big"),
                null,
            )

        // The newest three parts fit, the one before them does not, and the oldest is not asked.
        assertThat(mapper.serialized).hasSize(4)
        assertThat(fitted.inputMessages)
            .startsWith(
                """[{"role":"user","parts":[]},{"role":"user","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"2a"""
            )
            .contains("3a", "4a")
        assertThat(fitted.size).isLessThanOrEqualTo(CONTENT_LIMIT)
        assertThat(fitted.truncated).isTrue()
    }

    private fun textParts(vararg texts: String): List<String> =
        texts.map { """{"type":"text","content":"$it"}""" }

    private fun outputOf(vararg texts: String): String =
        """[{"role":"assistant","parts":[${textParts(*texts).joinToString(",")}]}]"""

    @Test
    fun fitContent_partsGoFromTheStartOfAnyNumberOfOutputMessages() {
        val output =
            jsonMapper()
                .readTree(
                    """
                    [{"role": "assistant", "parts": [{"type": "text", "content": "one"},
                                                     {"type": "tool_call", "id": "toolu_1", "name": "f", "arguments": {}}]},
                     {"role": "assistant", "parts": []},
                     {"role": "assistant", "parts": [{"type": "reasoning", "content": "two"},
                                                     {"type": "text", "content": "three"}]}]
                    """
                )
        val emptied =
            """{"role":"assistant","parts":[]},{"role":"assistant","parts":[]},""" +
                """{"role":"assistant","parts":["""
        val reasoning = """{"type":"reasoning","content":"two"}"""
        val text = """{"type":"text","content":"three"}"""
        fun fit(limit: Int) =
            fitContent(
                jsonMapper(),
                limit,
                0,
                systemParts(jsonMapper().readTree("""[{"type":"text","content":"system"}]""")),
                textMessageParts("question"),
                messageParts(output),
            )
        val shells = """[][{"role":"user","parts":[]}][$emptied]}]""".length

        val whole = fit(1000)
        assertThat(whole.outputMessages).isEqualTo(jsonMapper().writeValueAsString(output))
        assertThat(whole.truncated).isFalse()

        // The parts of the first message go before those of the last one, and no message goes.
        val lastMessage = fit(shells + reasoning.length + 1 + text.length)
        assertThat(lastMessage.systemInstructions).isEqualTo("[]")
        assertThat(lastMessage.inputMessages).isEqualTo("""[{"role":"user","parts":[]}]""")
        assertThat(lastMessage.outputMessages).isEqualTo("[$emptied$reasoning,$text]}]")
        assertThat(lastMessage.truncated).isTrue()

        // The reasoning goes whole, and the text after it is cut once it is the one part left.
        val lastPart = fit(shells + text.length)
        assertThat(lastPart.outputMessages).isEqualTo("[$emptied$text]}]")
        val cut = fit(shells + text.length - 1)
        assertThat(cut.outputMessages).isEqualTo("""[$emptied{"type":"text","content":"thre"}]}]""")
        assertThat(cut.size).isEqualTo(shells + text.length - 1)
    }

    @Test
    fun fitContent_cutsTextBetweenCharactersThatTheJsonEscapes() {
        // A char that is three bytes in the JSON, one that is two, a surrogate pair, which is four,
        // and chars that are escaped and take two and six.
        val text = "世é😀\n\u0001".repeat(4)
        val part = jsonMapper().writeValueAsString(mapOf("type" to "text", "content" to text))
        val output = jsonMapper().readTree("""[{"role":"assistant","parts":[$part]}]""")

        for (limit in outputOf().length until outputOf().length + utf8Bytes(part)) {
            val fitted = fitContent(jsonMapper(), limit, 0, null, null, messageParts(output))

            assertThat(fitted.truncated).isTrue()
            val content = partContents(fitted.outputMessages!!).single()
            assertThat(text).startsWith(content)
            assertThat(content.lastOrNull()?.isHighSurrogate() ?: false).isFalse()
            if (limit >= outputOf("").length) {
                assertThat(fitted.size).isEqualTo(utf8Bytes(fitted.outputMessages))
                assertThat(fitted.size).isLessThanOrEqualTo(limit)
            } else {
                assertThat(content).isEmpty()
            }
            // The next character would not have fitted.
            val next =
                text.take(content.length + if (text[content.length].isHighSurrogate()) 2 else 1)
            val withNext =
                jsonMapper().writeValueAsString(mapOf("type" to "text", "content" to next))
            assertThat(outputOf().length + utf8Bytes(withNext)).isGreaterThan(limit)
        }
    }

    @Test
    fun toolResultContent_string_isOneTextBlock() {
        val blocks = toolResultContent(TextNode("README.md\nsrc"))

        assertThat(jsonMapper().writeValueAsString(blocks))
            .isEqualTo("""[{"type":"text","text":"README.md\nsrc"}]""")
        assertThat(toolResultContent(null)).isEmpty()
    }

    @Test
    fun toolResultContent_list_keepsTextBlocksAndTheTypeOfTheOthers() {
        val content =
            jsonMapper()
                .readTree(
                    """
                    [
                      {"type": "text", "text": "A chart"},
                      {"type": "image", "source": {"type": "base64", "media_type": "image/png", "data": "SECRET"}}
                    ]
                    """
                )

        val blocks = toolResultContent(content)

        assertThat(jsonMapper().writeValueAsString(blocks))
            .isEqualTo("""[{"type":"text","text":"A chart"},{"type":"image"}]""")
    }

    /**
     * What the span of a tool run has of each case of `telemetry/tool-span-size-bound-cases.json`:
     * the content of a tool span over a small bound. The SDKs of the other languages have the same
     * cases. The bounds and sizes are in bytes of UTF-8, and the sizes are checked against the
     * encoded attributes. An attribute for which the case expects `null` is one that the span goes
     * without.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("toolSpanSizeBoundCases")
    fun toolSpan_sharedCase(name: String, case: JsonNode) {
        val input = case.get("input")
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient())
                .openTelemetry(
                    options(OpenTelemetryContentMode.CONTENT, case.get("bound").asLong())
                )
                .build()
        val toolInput = BetaToolUseBlockParam.Input.builder()
        input.get("tool_call_arguments").fields().forEach { (field, value) ->
            toolInput.putAdditionalProperty(field, JsonValue.fromJsonNode(value))
        }
        val toolUse =
            BetaToolUseBlockParam.builder().id("toolu_1").name("f").input(toolInput.build()).build()
        val result =
            BetaToolResultBlockParam.builder()
                .toolUseId("toolu_1")
                .content(JsonValue.fromJsonNode(input.get("tool_result_content")))
                .build()

        val span =
            startToolUseSpan(
                clientOptions,
                ToolUseSpan.CUSTOM,
                toolUse,
                description = null,
                conversationId = null,
                Context.current(),
            )!!
        endToolUseSpan(clientOptions, span, toolUse, ToolRunOutcome.Succeeded(result))

        val expected = case.get("expected")
        val tool = recorder.only()
        val arguments = tool.string("gen_ai.tool.call.arguments")
        val content = tool.string("anthropic.tool_result.content")
        assertThat(arguments)
            .describedAs(name)
            .isEqualTo(jsonOrNull(expected.get("tool_call_arguments")))
        assertThat(content).isEqualTo(jsonOrNull(expected.get("tool_result_content")))
        assertThat(attributesOf(tool).containsKey("anthropic.content.truncated"))
            .isEqualTo(case.get("expected_truncated").asBoolean())
        assertThat(utf8Bytes(arguments) + utf8Bytes(content))
            .isEqualTo(case.get("size_after").asInt())
    }

    /** A mapper that lists every value it serializes to a string. */
    private class SpyMapper : JsonMapper(jsonMapper()) {
        val serialized = CopyOnWriteArrayList<Any?>()

        override fun writeValueAsString(value: Any?): String {
            serialized.add(value)
            return super.writeValueAsString(value)
        }
    }

    /** The `messages` of a request whose messages are the user's [texts]. */
    private fun requestMessages(vararg texts: String): ArrayNode =
        jsonMapper().createArrayNode().apply {
            texts.forEach { addObject().put("role", "user").put("content", it) }
        }

    /** The parts of a response whose content blocks are [texts]. */
    private fun responseParts(vararg texts: String): ArrayNode =
        contentParts(
            jsonMapper().createArrayNode().apply {
                texts.forEach { addObject().put("type", "text").put("text", it) }
            }
        )

    @Test
    fun responseContent_thatFitsBesideTheRequest_serializesTheRequestOnce() {
        val mapper = SpyMapper()
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        val request =
            setRequestContent(
                span,
                mapper,
                TextNode("Be brief."),
                requestMessages("one", "two", "three"),
            )
        // The part of the system instructions, and each input message's part and what is around
        // it.
        assertThat(mapper.serialized).hasSize(7)
        setResponseContent(span, mapper, request, responseParts("Hi", "there"))
        span.end()

        // Each output part and what is around them, and no part of the request again.
        assertThat(mapper.serialized).hasSize(10)
        val recorded = recorder.only()
        assertThat(recorded.string("gen_ai.system_instructions"))
            .isEqualTo("""[{"type":"text","content":"Be brief."}]""")
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo(jsonMapper().writeValueAsString(textMessages("one", "two", "three")))
        assertThat(recorded.string("gen_ai.output.messages")).isEqualTo(outputOf("Hi", "there"))
        assertThat(attributesOf(recorded)).doesNotContainKey("anthropic.content.truncated")
    }

    @Test
    fun requestContent_withAMapperThatIndentsItsJson_isJsonAllTheSame() {
        // The application can set the client's mapper. The JSON around a message's parts does not
        // come from it, and the two go together.
        val mapper = jsonMapper().rebuild().enable(SerializationFeature.INDENT_OUTPUT).build()
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        val request = setRequestContent(span, mapper, null, requestMessages("one", "two"))
        setResponseContent(span, mapper, request, responseParts("Hi"))
        span.end()

        val recorded = recorder.only()
        assertThat(jsonMapper().readTree(recorded.string("gen_ai.input.messages")))
            .isEqualTo(jsonMapper().valueToTree(textMessages("one", "two")))
        assertThat(jsonMapper().readTree(recorded.string("gen_ai.output.messages")))
            .isEqualTo(jsonMapper().readTree(outputOf("Hi")))
    }

    @Test
    fun responseContent_thatDoesNotFitBesideTheRequest_removesTheOldestPartsForIt() {
        // Two of the three input parts fit the limit alone, and one beside the output.
        val texts = listOf("a", "b", "c").map { it.repeat(300 * 1024) }
        val output = "y".repeat(225 * 1024)
        val mapper = SpyMapper()
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        val request = setRequestContent(span, mapper, null, requestMessages(*texts.toTypedArray()))
        assertThat(request.fitted.inputMessages)
            .startsWith("""[{"role":"user","parts":[]},{"role":"user","parts":[{""")
            .contains(texts[1], texts[2])
        setResponseContent(span, mapper, request, responseParts(output))
        span.end()

        // What is around each message's parts, the three input parts, the oldest found not to fit,
        // and the output part: the request is fitted again from the JSON that was kept.
        assertThat(mapper.serialized).hasSize(8)
        // What fitting the request and the response together gives.
        val together =
            fitContent(
                jsonMapper(),
                CONTENT_LIMIT,
                0,
                null,
                textMessageParts(*texts.toTypedArray()),
                messageParts(jsonMapper().readTree(outputOf(output))),
            )
        val recorded = recorder.only()
        assertThat(recorded.string("gen_ai.output.messages"))
            .isEqualTo(outputOf(output))
            .isEqualTo(together.outputMessages)
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo(together.inputMessages)
            .startsWith(
                """[{"role":"user","parts":[]},{"role":"user","parts":[]},""" +
                    """{"role":"user","parts":[{"type":"text","content":"ccc"""
            )
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun responseContent_textPartCutAsTheLastPartOfTheRequest_goesWholeOnceAnOutputPartFollowsIt() {
        val text = "q".repeat(100)
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        // Without a response the request's one part is the last part, so its end is cut off.
        val request =
            setRequestContent(span, jsonMapper(), null, requestMessages(text), limit = 100)
        assertThat(request.fitted.inputMessages)
            .isEqualTo(jsonMapper().writeValueAsString(textMessages(text.take(44))))
            .hasSize(100)
        assertThat(request.fitted.truncated).isTrue()
        setResponseContent(span, jsonMapper(), request, responseParts("Hi"))
        span.end()

        // With the response it is the first of two parts, so it goes whole: what fitting the
        // request as it was and the response together gives.
        val together =
            fitContent(
                jsonMapper(),
                100,
                0,
                null,
                textMessageParts(text),
                messageParts(jsonMapper().readTree(outputOf("Hi"))),
            )
        val recorded = recorder.only()
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
            .isEqualTo(together.inputMessages)
        assertThat(recorded.string("gen_ai.output.messages"))
            .isEqualTo(outputOf("Hi"))
            .isEqualTo(together.outputMessages)
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun responseContent_besideARequestOverTheLimitWithNothingToRemove_isFittedToo() {
        val stopSequence = "s".repeat(100)
        val messages = jsonMapper().readTree("""[{"role": "user", "content": []}]""")
        val output =
            jsonMapper()
                .readTree(
                    """
                    [{"type": "text", "text": "Hi"},
                     {"type": "tool_use", "id": "toolu_1", "name": "f", "input": {}}]
                    """
                )
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        // The stop sequence fills the limit and stays, and the request has no part to remove.
        val request =
            setRequestContent(span, jsonMapper(), null, messages, listOf(stopSequence), limit = 100)
        assertThat(request.fitted.truncated).isFalse()
        assertThat(request.fitted.size).isGreaterThan(100)
        setResponseContent(span, jsonMapper(), request, contentParts(output))
        span.end()

        // The response has parts, and they go, the last one too, which is a tool call.
        val recorded = recorder.only()
        assertThat(recorded.strings("gen_ai.request.stop_sequences")).containsExactly(stopSequence)
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo("""[{"role":"user","parts":[]}]""")
        assertThat(recorded.string("gen_ai.output.messages")).isEqualTo(outputOf())
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun responseContent_besideARequestWhoseStopSequencesWereLeftOut_hasTheRoomTheyWouldTake() {
        val stopSequence = "s".repeat(120)
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        // The stop sequence alone is one over the limit, so the span goes without it, and that is
        // truncation although no part goes.
        val request =
            setRequestContent(
                span,
                jsonMapper(),
                null,
                requestMessages("q"),
                listOf(stopSequence),
                limit = 119,
            )
        assertThat(request.fitted.keepsUnreduced).isFalse()
        assertThat(request.fitted.truncated).isTrue()
        assertThat(request.fitted.size).isEqualTo(request.fitted.inputMessages!!.length)
        setResponseContent(span, jsonMapper(), request, responseParts("a"))
        span.end()

        // With the response the stop sequence is left out again and takes no room, so the input's
        // 57 and the output's 62 fill the limit whole.
        val recorded = recorder.only()
        assertThat(attributesOf(recorded)).doesNotContainKey("gen_ai.request.stop_sequences")
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo(jsonMapper().writeValueAsString(textMessages("q")))
        assertThat(recorded.string("gen_ai.output.messages")).isEqualTo(outputOf("a"))
        assertThat(contentSize(recorded)).isEqualTo(119)
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun responseContent_withoutParts_cutsTheLastPartOfTheRequestForTheRoomThatIsLeft() {
        val text = "q".repeat(100)
        val span = recorder.tracerProvider.get("test").spanBuilder("content").startSpan()

        val request =
            setRequestContent(span, jsonMapper(), null, requestMessages(text), limit = 100)
        setResponseContent(span, jsonMapper(), request, responseParts())
        span.end()

        // The request's one part is still the last part, and the output message takes room.
        val recorded = recorder.only()
        assertThat(recorded.string("gen_ai.output.messages")).isEqualTo(outputOf())
        assertThat(recorded.string("gen_ai.input.messages"))
            .isEqualTo(jsonMapper().writeValueAsString(textMessages(text.take(11))))
        assertThat(contentSize(recorded)).isEqualTo(100)
        assertThat(attributesOf(recorded)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_otherBlocksKeepTheirTypeOnly() {
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": [{"type": "redacted_thinking", "data": "PRIVATE"}, {"type": "text", "text": "ok"}],
             "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().create(messageParams())

        assertThat(recorder.only().string("gen_ai.output.messages"))
            .isEqualTo(
                """[{"role":"assistant","parts":[{"type":"anthropic_redacted_thinking"},{"type":"text","content":"ok"}]}]"""
            )
    }

    @Test
    fun contentMode_thinkingBlocksAreReasoningPartsWithoutTheirSignature() {
        // The second thinking block is a hidden one: its thinking is empty.
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": [
               {"type": "thinking", "thinking": "Let me see", "signature": "SIGNATURE-1"},
               {"type": "thinking", "thinking": "", "signature": "SIGNATURE-2"},
               {"type": "text", "text": "ok"}
             ],
             "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        // The request replays an earlier turn of the assistant with its thinking.
        val params =
            messageParams()
                .toBuilder()
                .addAssistantMessageOfBlockParams(
                    listOf(
                        ContentBlockParam.ofThinking(
                            ThinkingBlockParam.builder()
                                .thinking("Earlier thought")
                                .signature("SIGNATURE-0")
                                .build()
                        ),
                        ContentBlockParam.ofRedactedThinking("PRIVATE"),
                    )
                )
                .addUserMessage("go on")
                .build()

        client.messages().create(params)

        val span = recorder.only()
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo(
                """[{"role":"assistant","parts":[{"type":"reasoning","content":"Let me see"},{"type":"reasoning","content":""},{"type":"text","content":"ok"}]}]"""
            )
        assertThat(span.string("gen_ai.input.messages"))
            .contains(
                """{"role":"assistant","parts":[{"type":"reasoning","content":"Earlier thought"},{"type":"anthropic_redacted_thinking"}]}"""
            )
        assertThat(span.attributeText()).doesNotContain("SIGNATURE", "PRIVATE")
    }

    @Test
    fun contentMode_toolResultsKeepTheirContentBlocks() {
        stubMessage()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val content =
            jsonMapper()
                .readTree(
                    """
                    [
                      {"type": "image", "source": {"type": "base64", "media_type": "image/png", "data": "PRIVATE"}},
                      {"type": "document", "source": {"type": "text", "media_type": "text/plain", "data": "PRIVATE"}},
                      {"type": "tool_result", "tool_use_id": "toolu_1", "content": "sunny"},
                      {"type": "tool_result", "tool_use_id": "toolu_2", "content": [
                        {"type": "text", "text": "cited",
                         "citations": [
                           {"type": "char_location", "cited_text": "quote", "document_index": 0, "document_title": "t", "start_char_index": 0, "end_char_index": 5},
                           {"type": "web_search_result_location", "cited_text": "quote", "title": "t", "url": "https://example.com", "encrypted_index": "OPAQUE"}
                         ],
                         "cache_control": {"type": "ephemeral"}},
                        {"type": "search_result", "source": "https://example.com", "title": "Found",
                         "content": [{"type": "text", "text": "hit"}]},
                        {"type": "image", "source": {"type": "base64", "media_type": "image/png", "data": "PRIVATE"}}
                      ]},
                      {"type": "tool_result", "tool_use_id": "toolu_3"}
                    ]
                    """
                )
        val params =
            messageParams()
                .toBuilder()
                .addMessage(
                    MessageParam.builder()
                        .role(MessageParam.Role.USER)
                        .content(JsonValue.fromJsonNode(content))
                        .build()
                )
                .build()

        client.messages().create(params)

        val span = recorder.only()
        val message = jsonMapper().readTree(span.string("gen_ai.input.messages")).last()
        // A part that is not a tool result has its prefixed type only. A tool result's response
        // has the blocks as they are sent: text and search result blocks with all their fields
        // but the encrypted ones, the type only of any other, without a prefix, and none for a
        // result without content.
        assertThat(message)
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        {"role": "user", "parts": [
                          {"type": "anthropic_image"},
                          {"type": "anthropic_document"},
                          {"type": "tool_call_response", "id": "toolu_1", "response": [{"type": "text", "text": "sunny"}]},
                          {"type": "tool_call_response", "id": "toolu_2", "response": [
                            {"type": "text", "text": "cited",
                             "citations": [
                               {"type": "char_location", "cited_text": "quote", "document_index": 0, "document_title": "t", "start_char_index": 0, "end_char_index": 5},
                               {"type": "web_search_result_location", "cited_text": "quote", "title": "t", "url": "https://example.com"}
                             ],
                             "cache_control": {"type": "ephemeral"}},
                            {"type": "search_result", "source": "https://example.com", "title": "Found",
                             "content": [{"type": "text", "text": "hit"}]},
                            {"type": "image"}
                          ]},
                          {"type": "tool_call_response", "id": "toolu_3", "response": []}
                        ]}
                        """
                    )
            )
        assertThat(span.attributeText()).doesNotContain("PRIVATE", "OPAQUE")
    }

    @Test
    fun contentMode_serverAndMcpToolBlocksAreToolCallsAndTheirResponses() {
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": [
               {"type": "server_tool_use", "id": "srvtoolu_1", "name": "web_search", "input": {"query": "otters"}, "toolset_name": "stray"},
               {"type": "web_search_tool_result", "tool_use_id": "srvtoolu_1", "content": [
                 {"type": "web_search_result", "title": "Otters", "url": "https://example.com", "encrypted_content": "OPAQUE"}
               ]},
               {"type": "mcp_tool_use", "id": "mcptoolu_1", "name": "lookup", "server_name": "docs", "input": {"key": "a"}, "toolset_name": "stray"},
               {"type": "mcp_tool_result", "tool_use_id": "mcptoolu_1", "is_error": false,
                "content": [{"type": "text", "text": "found"}]}
             ],
             "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        // The request replays an earlier turn of the assistant.
        val replayed =
            jsonMapper()
                .readTree(
                    """
                    [
                      {"type": "server_tool_use", "id": "srvtoolu_2", "name": "code_execution", "input": {"code": "1"}, "toolset_name": "stray"},
                      {"type": "code_execution_tool_result", "tool_use_id": "srvtoolu_2", "content":
                        {"type": "code_execution_result", "stdout": "1", "stderr": "", "return_code": 0,
                         "content": [{"type": "code_execution_output", "file_id": "file_1"}]}},
                      {"type": "code_execution_tool_result", "tool_use_id": "srvtoolu_3", "content":
                        {"type": "encrypted_code_execution_result", "encrypted_stdout": "OPAQUE", "stderr": "", "return_code": 0, "content": []}},
                      {"type": "web_search_tool_result", "tool_use_id": "srvtoolu_4", "content":
                        {"type": "web_search_tool_result_error", "error_code": "max_uses_exceeded"}},
                      {"type": "text_editor_code_execution_tool_result", "tool_use_id": "srvtoolu_5", "content":
                        {"type": "text_editor_code_execution_view_result", "file_type": "image", "content": "PRIVATE"}},
                      {"type": "web_fetch_tool_result", "tool_use_id": "srvtoolu_6", "content":
                        {"type": "web_fetch_result", "url": "https://example.com",
                         "content": {"type": "document", "title": "Otters", "source": {"type": "text", "media_type": "text/plain", "data": "PRIVATE"}}}},
                      {"type": "bash_code_execution_tool_result", "tool_use_id": "srvtoolu_7"},
                      {"type": "mcp_tool_use", "id": "mcptoolu_2", "name": "lookup", "server_name": "docs", "input": {"key": "b"}, "toolset_name": "stray"},
                      {"type": "mcp_tool_result", "tool_use_id": "mcptoolu_2", "is_error": false, "content": "plain"}
                    ]
                    """
                )
        val params =
            betaMessageParams()
                .toBuilder()
                .addMessage(
                    BetaMessageParam.builder()
                        .role(BetaMessageParam.Role.ASSISTANT)
                        .content(JsonValue.fromJsonNode(replayed))
                        .build()
                )
                .addUserMessage("go on")
                .build()

        client.beta().messages().create(params)

        val span = recorder.only()
        assertThat(jsonMapper().readTree(span.string("gen_ai.output.messages")))
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        [{"role": "assistant", "parts": [
                          {"type": "tool_call", "id": "srvtoolu_1", "name": "web_search", "arguments": {"query": "otters"}},
                          {"type": "tool_call_response", "id": "srvtoolu_1", "response": [
                            {"type": "web_search_result", "title": "Otters", "url": "https://example.com"}
                          ]},
                          {"type": "tool_call", "id": "mcptoolu_1", "name": "lookup", "arguments": {"key": "a"}, "anthropic_mcp_server_name": "docs"},
                          {"type": "tool_call_response", "id": "mcptoolu_1", "response": [{"type": "text", "text": "found"}]}
                        ]}]
                        """
                    )
            )
        // A result that is one object is kept, without its encrypted fields and without image and
        // document data. An error is such an object too. A result without content is empty.
        assertThat(jsonMapper().readTree(span.string("gen_ai.input.messages"))[1])
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        {"role": "assistant", "parts": [
                          {"type": "tool_call", "id": "srvtoolu_2", "name": "code_execution", "arguments": {"code": "1"}},
                          {"type": "tool_call_response", "id": "srvtoolu_2", "response":
                            {"type": "code_execution_result", "stdout": "1", "stderr": "", "return_code": 0,
                             "content": [{"type": "code_execution_output", "file_id": "file_1"}]}},
                          {"type": "tool_call_response", "id": "srvtoolu_3", "response":
                            {"type": "encrypted_code_execution_result", "stderr": "", "return_code": 0, "content": []}},
                          {"type": "tool_call_response", "id": "srvtoolu_4", "response":
                            {"type": "web_search_tool_result_error", "error_code": "max_uses_exceeded"}},
                          {"type": "tool_call_response", "id": "srvtoolu_5", "response":
                            {"type": "text_editor_code_execution_view_result", "file_type": "image"}},
                          {"type": "tool_call_response", "id": "srvtoolu_6", "response":
                            {"type": "web_fetch_result", "url": "https://example.com", "content": {"type": "document"}}},
                          {"type": "tool_call_response", "id": "srvtoolu_7", "response": []},
                          {"type": "tool_call", "id": "mcptoolu_2", "name": "lookup", "arguments": {"key": "b"}, "anthropic_mcp_server_name": "docs"},
                          {"type": "tool_call_response", "id": "mcptoolu_2", "response": [{"type": "text", "text": "plain"}]}
                        ]}
                        """
                    )
            )
        assertThat(span.attributeText()).doesNotContain("OPAQUE", "PRIVATE")
    }

    @Test
    fun contentMode_stream_recordsStreamedServerAndMcpToolBlocks() {
        stubStream(
            sse(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"m","content":[],"stop_reason":null,"usage":{"input_tokens":1,"output_tokens":1}}}""",
                """{"type":"content_block_start","index":0,"content_block":{"type":"server_tool_use","id":"srvtoolu_1","name":"web_search","input":{},"toolset_name":"stray"}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"input_json_delta","partial_json":"{\"query\":\"otters\"}"}}""",
                """{"type":"content_block_stop","index":0}""",
                """{"type":"content_block_start","index":1,"content_block":{"type":"web_search_tool_result","tool_use_id":"srvtoolu_1","content":[{"type":"web_search_result","title":"Otters","url":"https://example.com","encrypted_content":"OPAQUE"}]}}""",
                """{"type":"content_block_stop","index":1}""",
                """{"type":"content_block_start","index":2,"content_block":{"type":"mcp_tool_use","id":"mcptoolu_1","name":"lookup","server_name":"docs","input":{},"toolset_name":"stray"}}""",
                """{"type":"content_block_delta","index":2,"delta":{"type":"input_json_delta","partial_json":"{\"key\":\"a\"}"}}""",
                """{"type":"content_block_stop","index":2}""",
                """{"type":"content_block_start","index":3,"content_block":{"type":"mcp_tool_result","tool_use_id":"mcptoolu_1","is_error":false,"content":"plain"}}""",
                """{"type":"content_block_stop","index":3}""",
                """{"type":"message_delta","delta":{"stop_reason":"end_turn"},"usage":{"output_tokens":2}}""",
                """{"type":"message_stop"}""",
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().createStreaming(betaMessageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(jsonMapper().readTree(span.string("gen_ai.output.messages")))
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        [{"role": "assistant", "parts": [
                          {"type": "tool_call", "id": "srvtoolu_1", "name": "web_search", "arguments": {"query": "otters"}},
                          {"type": "tool_call_response", "id": "srvtoolu_1", "response": [
                            {"type": "web_search_result", "title": "Otters", "url": "https://example.com"}
                          ]},
                          {"type": "tool_call", "id": "mcptoolu_1", "name": "lookup", "arguments": {"key": "a"}, "anthropic_mcp_server_name": "docs"},
                          {"type": "tool_call_response", "id": "mcptoolu_1", "response": [{"type": "text", "text": "plain"}]}
                        ]}]
                        """
                    )
            )
        assertThat(span.attributeText()).doesNotContain("OPAQUE")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun contentMode_onlyAToolUseWithAToolsetNameHasItOnItsToolCall(beta: Boolean) {
        // The name is a string, null or absent. A tool result names its toolset too.
        val toolUses =
            """
            [{"type": "tool_use", "id": "toolu_1", "name": "screenshot", "input": {}, "toolset_name": "computer"},
             {"type": "tool_use", "id": "toolu_2", "name": "f", "input": {"a": 1}, "toolset_name": null},
             {"type": "tool_use", "id": "toolu_3", "name": "g", "input": {}}]
            """
        val toolResults =
            """
            [{"type": "tool_result", "tool_use_id": "toolu_1", "content": "done", "toolset_name": "computer"}]
            """
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": $toolUses,
             "stop_reason": "tool_use", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val replayed = JsonValue.fromJsonNode(jsonMapper().readTree(toolUses))
        val results = JsonValue.fromJsonNode(jsonMapper().readTree(toolResults))

        if (beta) {
            client
                .beta()
                .messages()
                .create(
                    betaMessageParams()
                        .toBuilder()
                        .addMessage(
                            BetaMessageParam.builder()
                                .role(BetaMessageParam.Role.ASSISTANT)
                                .content(replayed)
                                .build()
                        )
                        .addMessage(
                            BetaMessageParam.builder()
                                .role(BetaMessageParam.Role.USER)
                                .content(results)
                                .build()
                        )
                        .build()
                )
        } else {
            client
                .messages()
                .create(
                    messageParams()
                        .toBuilder()
                        .addMessage(
                            MessageParam.builder()
                                .role(MessageParam.Role.ASSISTANT)
                                .content(replayed)
                                .build()
                        )
                        .addMessage(
                            MessageParam.builder()
                                .role(MessageParam.Role.USER)
                                .content(results)
                                .build()
                        )
                        .build()
                )
        }

        val span = recorder.only()
        val toolCalls =
            """
            {"role": "assistant", "parts": [
              {"type": "tool_call", "id": "toolu_1", "name": "screenshot", "arguments": {}, "anthropic_toolset_name": "computer"},
              {"type": "tool_call", "id": "toolu_2", "name": "f", "arguments": {"a": 1}},
              {"type": "tool_call", "id": "toolu_3", "name": "g", "arguments": {}}
            ]}
            """
        assertThat(jsonMapper().readTree(span.string("gen_ai.output.messages")))
            .isEqualTo(jsonMapper().readTree("[$toolCalls]"))
        val input = jsonMapper().readTree(span.string("gen_ai.input.messages"))
        assertThat(input[1]).isEqualTo(jsonMapper().readTree(toolCalls))
        assertThat(input[2])
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        {"role": "user", "parts": [
                          {"type": "tool_call_response", "id": "toolu_1", "response": [{"type": "text", "text": "done"}]}
                        ]}
                        """
                    )
            )
        // The name comes after the arguments.
        val named = """"arguments":{},"anthropic_toolset_name":"computer"}"""
        assertThat(span.string("gen_ai.output.messages")).contains(named)
        assertThat(span.string("gen_ai.input.messages")).contains(named)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun contentMode_stream_onlyAToolUseWithAToolsetNameHasItOnItsToolCall(beta: Boolean) {
        stubStream(
            sse(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"m","content":[],"stop_reason":null,"usage":{"input_tokens":1,"output_tokens":1}}}""",
                """{"type":"content_block_start","index":0,"content_block":{"type":"tool_use","id":"toolu_1","name":"screenshot","input":{},"toolset_name":"computer"}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"input_json_delta","partial_json":""}}""",
                """{"type":"content_block_stop","index":0}""",
                """{"type":"content_block_start","index":1,"content_block":{"type":"tool_use","id":"toolu_2","name":"f","input":{},"toolset_name":null}}""",
                """{"type":"content_block_delta","index":1,"delta":{"type":"input_json_delta","partial_json":"{\"a\":1}"}}""",
                """{"type":"content_block_stop","index":1}""",
                """{"type":"content_block_start","index":2,"content_block":{"type":"tool_use","id":"toolu_3","name":"g","input":{}}}""",
                """{"type":"content_block_delta","index":2,"delta":{"type":"input_json_delta","partial_json":"{}"}}""",
                """{"type":"content_block_stop","index":2}""",
                """{"type":"message_delta","delta":{"stop_reason":"tool_use"},"usage":{"output_tokens":2}}""",
                """{"type":"message_stop"}""",
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        if (beta) {
            client.beta().messages().createStreaming(betaMessageParams()).use {
                it.stream().forEach {}
            }
        } else {
            client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }
        }

        val span = recorder.only()
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo(
                """[{"role":"assistant","parts":[""" +
                    """{"type":"tool_call","id":"toolu_1","name":"screenshot","arguments":{},"anthropic_toolset_name":"computer"},""" +
                    """{"type":"tool_call","id":"toolu_2","name":"f","arguments":{"a":1}},""" +
                    """{"type":"tool_call","id":"toolu_3","name":"g","arguments":{}}]}]"""
            )
    }

    @Test
    fun contentMode_stream_recordsTheStreamedThinkingAsAReasoningPart() {
        stubStream(
            sse(
                """{"type":"message_start","message":{"id":"msg_1","type":"message","role":"assistant","model":"m","content":[],"stop_reason":null,"usage":{"input_tokens":1,"output_tokens":1}}}""",
                """{"type":"content_block_start","index":0,"content_block":{"type":"thinking","thinking":"","signature":""}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"thinking_delta","thinking":"Let me"}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"thinking_delta","thinking":" see"}}""",
                """{"type":"content_block_delta","index":0,"delta":{"type":"signature_delta","signature":"SIGNATURE"}}""",
                """{"type":"content_block_stop","index":0}""",
                """{"type":"content_block_start","index":1,"content_block":{"type":"text","text":""}}""",
                """{"type":"content_block_delta","index":1,"delta":{"type":"text_delta","text":"ok"}}""",
                """{"type":"content_block_stop","index":1}""",
                """{"type":"content_block_start","index":2,"content_block":{"type":"redacted_thinking","data":"PRIVATE"}}""",
                """{"type":"content_block_stop","index":2}""",
                """{"type":"message_delta","delta":{"stop_reason":"end_turn"},"usage":{"output_tokens":2}}""",
                """{"type":"message_stop"}""",
            )
        )
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.string("gen_ai.output.messages"))
            .isEqualTo(
                """[{"role":"assistant","parts":[{"type":"reasoning","content":"Let me see"},{"type":"text","content":"ok"},{"type":"anthropic_redacted_thinking"}]}]"""
            )
        assertThat(span.attributeText()).doesNotContain("SIGNATURE", "PRIVATE")
    }

    @Test
    fun metadataOnly_recordsNoThinking() {
        stubMessage(
            """
            {"id": "msg_1", "type": "message", "role": "assistant", "model": "m",
             "content": [{"type": "thinking", "thinking": "Let me see", "signature": "SIGNATURE"}],
             "stop_reason": "end_turn", "usage": {"input_tokens": 1, "output_tokens": 1}}
            """
        )

        client.messages().create(messageParams())

        val span = recorder.only()
        assertThat(attributesOf(span)).doesNotContainKey("gen_ai.output.messages")
        assertThat(span.attributeText()).doesNotContain("Let me see", "SIGNATURE")
    }
}
