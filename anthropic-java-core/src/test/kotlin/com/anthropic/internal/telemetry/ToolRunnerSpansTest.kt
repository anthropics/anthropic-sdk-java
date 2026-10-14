package com.anthropic.internal.telemetry

import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.OpenTelemetryRequestOptions
import com.anthropic.core.opentelemetry.OpenTelemetryTraces
import com.anthropic.errors.RateLimitException
import com.anthropic.helpers.BetaToolRunner
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.helpers.McpBetaTool
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaJsonOutputFormat
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.anthropic.models.beta.messages.ToolRunnerCreateParams
import com.anthropic.services.blocking.beta.MessageService
import com.fasterxml.jackson.annotation.JsonClassDescription
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.resetAllRequests
import com.github.tomakehurst.wiremock.client.WireMock.resetAllScenarios
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.stubbing.Scenario
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanContext
import io.opentelemetry.api.trace.SpanId
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.context.Context
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.data.SpanData
import io.opentelemetry.sdk.trace.samplers.Sampler
import java.util.concurrent.atomic.AtomicReference
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ToolRunnerSpansTest : TracingTestSupport() {

    @Test
    fun toolRunner_toolUseOfAToolset_recordsTheToolsetName() {
        stubToolLoop(
            TOOL_USE_MESSAGE_JSON.replace(
                """"name": "get_weather"""",
                """"name": "get_weather", "toolset_name": "computer"""",
            )
        )

        client.beta().messages().toolRunner(toolParams()).toList()

        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.string("anthropic.toolset.name")).isEqualTo("computer")
    }

    @Test
    fun toolRunner_emitsLoopAndToolSpans() {
        stubToolLoop()
        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()
        val parent = recorder.tracerProvider.get("app").spanBuilder("handle_order").startSpan()

        val messages =
            parent.makeCurrent().use {
                client.beta().messages().toolRunner(toolParams(), requestOptions).toList()
            }
        parent.end()

        assertThat(messages).hasSize(2)
        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.kind).isEqualTo(SpanKind.INTERNAL)
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(loop.parentSpanId).isEqualTo(parent.spanContext.spanId)
        assertThat(loop.string("gen_ai.operation.name")).isEqualTo("invoke_agent")
        assertThat(loop.string("gen_ai.provider.name")).isEqualTo("anthropic")
        assertThat(loop.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(loop.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        assertThat(loop.long("gen_ai.usage.input_tokens")).isNull()
        assertThat(attributesOf(loop))
            .doesNotContainKeys("anthropic.message.stop_reason", "gen_ai.response.finish_reasons")
        assertThat(attributesOf(loop)).doesNotContainKey("gen_ai.output.type")

        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.kind).isEqualTo(SpanKind.INTERNAL)
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(tool.parentSpanId).isEqualTo(loop.spanId)
        assertThat(tool.string("gen_ai.operation.name")).isEqualTo("execute_tool")
        assertThat(tool.string("gen_ai.tool.name")).isEqualTo("get_weather")
        assertThat(tool.string("gen_ai.tool.call.id")).isEqualTo("toolu_1")
        assertThat(tool.string("gen_ai.tool.type")).isEqualTo("function")
        assertThat(tool.string("gen_ai.provider.name")).isEqualTo("anthropic")
        // The description is metadata, which the span has without content mode.
        assertThat(tool.string("gen_ai.tool.description"))
            .isEqualTo("Get the weather in a given location")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isNull()
        assertThat(tool.string("anthropic.tool_result.content")).isNull()
        assertThat(tool.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        // The Messages tool runner has no Session.
        assertThat(attributesOf(tool))
            .doesNotContainKeys(
                "anthropic.session.id",
                "anthropic.session.thread.id",
                "anthropic.toolset.name",
            )

        val calls = recorder.spans.filter { it.name == "anthropic.messages.create" }
        assertThat(calls).hasSize(2)
        assertThat(calls).allSatisfy {
            assertThat(it.parentSpanId).isEqualTo(loop.spanId)
            assertThat(it.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        }
        assertThat(calls.map { it.string("anthropic.message.stop_reason") })
            .containsExactly("tool_use", "end_turn")
        assertThat(recorder.spans.map { it.name })
            .containsExactly(
                "anthropic.messages.create",
                "anthropic.custom_tool_use get_weather",
                "anthropic.messages.create",
                "anthropic.messages.tool_runner",
                "handle_order",
            )
    }

    @Test
    fun toolRunner_loopAndToolSpans_haveNoneOfTheHttpAttributesOfARequestSpan() {
        stubToolLoop()

        client.beta().messages().toolRunner(toolParams()).toList()

        val httpAttributes =
            arrayOf(
                "http.request.method",
                "http.route",
                "http.request.resend_count",
                "http.response.status_code",
                "url.scheme",
                "url.path",
                "url.query",
                "url.full",
            )
        val helpers = recorder.spans.filter { it.kind == SpanKind.INTERNAL }
        assertThat(helpers.map { it.name })
            .containsExactly(
                "anthropic.custom_tool_use get_weather",
                "anthropic.messages.tool_runner",
            )
        assertThat(helpers).allSatisfy {
            assertThat(attributesOf(it)).doesNotContainKeys(*httpAttributes)
        }
        // The requests that the loop sends have their own spans, which do have them.
        assertThat(recorder.spans.filter { it.kind == SpanKind.CLIENT }).hasSize(2).allSatisfy {
            assertThat(it.string("http.request.method")).isEqualTo("POST")
            assertThat(it.string("http.route")).isEqualTo("/v1/messages")
            assertThat(it.string("url.full")).isEqualTo("$baseUrl/v1/messages")
        }
    }

    @Test
    fun toolRunner_toolError_failsToolSpan() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "broken_tool"))
                        .withHeader("Content-Type", "application/json")
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

        client.beta().messages().toolRunner(toolParams()).toList()

        val tool = recorder.named("anthropic.custom_tool_use broken_tool")
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(tool.string("error.type")).isEqualTo("tool_error")
        assertThat(tool.status.description).isEmpty()
        assertThat(tool.string("gen_ai.tool.description")).isEqualTo("A tool that fails")
        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.conversation.id")
        assertThat(recorder.named("anthropic.messages.tool_runner").status.statusCode)
            .isEqualTo(StatusCode.OK)
    }

    /** The params of a conversation whose last message calls the tool `get_weather`. */
    private fun paramsEndingWithAToolUse(): BetaMessageCreateParams {
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("toolu_1")
                .name("get_weather")
                .input(
                    BetaToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("location", JsonValue.from("Paris"))
                        .build()
                )
                .build()
        return toolParams()
            .toBuilder()
            .addAssistantMessageOfBetaContentBlockParams(
                listOf(BetaContentBlockParam.ofToolUse(toolUse))
            )
            .build()
    }

    @Test
    fun toolRunner_toolRunOutsideALoop_recordsTheConversationId() {
        val params = paramsEndingWithAToolUse()
        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()
        val runner = client.beta().messages().toolRunner(params, requestOptions)

        // The application runs the tool of the last message itself, and never starts the loop.
        assertThat(runner.lastToolResponse()).isPresent()

        assertThat(recorder.spans.map { it.name })
            .containsExactly("anthropic.custom_tool_use get_weather")
        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        assertThat(tool.string("gen_ai.tool.call.id")).isEqualTo("toolu_1")
        // No span is current in the application, so the tool span is a root span.
        assertThat(tool.parentSpanId).isEqualTo(SpanId.getInvalid())
    }

    @Test
    fun toolRunner_toolRunByTheApplication_isAChildOfTheApplicationsCurrentSpan() {
        stubToolLoop()
        val handleOrder = recorder.tracerProvider.get("app").spanBuilder("handle_order").startSpan()

        abandonAfter(client.beta().messages().toolRunner(paramsEndingWithAToolUse())) { runner ->
            // The loop has started, so the tool runner span is open, but it is not current in the
            // application's own code.
            runner.iterator().next()
            handleOrder.makeCurrent().use { assertThat(runner.lastToolResponse()).isPresent() }
        }
        handleOrder.end()
        awaitReclaimed("anthropic.messages.tool_runner")

        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.parentSpanId).isEqualTo(handleOrder.spanContext.spanId)
        assertThat(recorder.named("anthropic.messages.create").parentSpanId)
            .isEqualTo(recorder.named("anthropic.messages.tool_runner").spanId)
    }

    /** The model calls the tool `lookup`, and then answers. */
    private fun stubLookupLoop() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "lookup"))
                        .withHeader("Content-Type", "application/json")
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

    /**
     * Params with the tool `lookup` as an MCP helper makes it, whose definition has [description],
     * the MCP server's, or none.
     */
    private fun mcpToolParams(description: String?): BetaMessageCreateParams {
        val mcpTool =
            McpBetaTool.builder()
                .name("lookup")
                .definition(
                    BetaTool.builder()
                        .name("lookup")
                        .apply { description?.let(::description) }
                        .inputSchema(BetaTool.InputSchema.builder().build())
                        .build()
                )
                .runner { BetaToolResultBlockParam.Content.ofString("found") }
                .build()
        return BetaMessageCreateParams.builder()
            .model("claude-requested")
            .maxTokens(100)
            .addUserMessage("Look it up")
            .addTool(mcpTool)
            .build()
    }

    @Test
    fun toolRunner_mcpTool_emitsMcpToolSpan() {
        stubLookupLoop()

        client
            .beta()
            .messages()
            .toolRunner(mcpToolParams("Looks a record up on the server"))
            .toList()

        val tool = recorder.named("anthropic.mcp_tool_use lookup")
        assertThat(tool.kind).isEqualTo(SpanKind.INTERNAL)
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(tool.string("gen_ai.operation.name")).isEqualTo("execute_tool")
        assertThat(tool.string("gen_ai.tool.name")).isEqualTo("lookup")
        assertThat(tool.string("gen_ai.tool.type")).isEqualTo("function")
        assertThat(tool.string("gen_ai.tool.description"))
            .isEqualTo("Looks a record up on the server")
        assertThat(recorder.spans.map { it.name }).noneMatch {
            it.startsWith("anthropic.custom_tool_use")
        }
    }

    @Test
    fun toolRunner_toolWithoutADescription_recordsNone() {
        stubLookupLoop()
        for (description in listOf(null, "")) {
            resetAllScenarios()
            client.beta().messages().toolRunner(mcpToolParams(description)).toList()
        }

        val tools = recorder.spans.filter { it.name == "anthropic.mcp_tool_use lookup" }
        assertThat(tools).hasSize(2).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(attributesOf(it)).doesNotContainKey("gen_ai.tool.description")
        }
    }

    /** The model streams a call of `get_weather`, and then streams its answer. */
    private fun stubStreamedToolLoop() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(ok(STREAM_BODY).withHeader("Content-Type", "text/event-stream"))
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs("second")
                .willReturn(
                    ok(
                            sse(
                                *STREAM_EVENTS.take(5).toTypedArray(),
                                STREAM_EVENTS[9].replace("tool_use", "end_turn"),
                                STREAM_EVENTS[10],
                            )
                        )
                        .withHeader("Content-Type", "text/event-stream")
                )
        )
    }

    @Test
    fun streamingToolRunner_recordsTheToolDescriptionAndTheStreamedOutput() {
        stubStreamedToolLoop()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().toolRunner(toolParams()).streaming().forEach { stream ->
            stream.stream().forEach {}
        }

        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.OK)
        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(tool.parentSpanId).isEqualTo(loop.spanId)
        assertThat(tool.string("gen_ai.tool.description"))
            .isEqualTo("Get the weather in a given location")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"location":"Paris"}""")
        // The runner accumulates each streamed Message, and the request's span has its content.
        val calls = recorder.spans.filter { it.name == "anthropic.messages.create" }
        assertThat(calls).hasSize(2).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(it.parentSpanId).isEqualTo(loop.spanId)
            assertThat(it.string("gen_ai.input.messages")).isNotNull()
            assertThat(it.string("gen_ai.response.id")).isEqualTo("msg_1")
        }
        assertThat(calls[0].string("gen_ai.output.messages")).isEqualTo(STREAMED_OUTPUT_MESSAGES)
        assertThat(calls[0].string("anthropic.message.stop_reason")).isEqualTo("tool_use")
        assertThat(calls[1].string("gen_ai.output.messages"))
            .isEqualTo("""[{"role":"assistant","parts":[{"type":"text","content":"Hi there"}]}]""")
        assertThat(calls[1].string("anthropic.message.stop_reason")).isEqualTo("end_turn")
        // The second request sends the first Message back, as the runner accumulated it.
        assertThat(calls[1].string("gen_ai.input.messages")).contains("toolu_1", "foggy")
    }

    @Test
    fun streamingToolRunner_metadataOnly_recordsTheResponseAttributesAndNoContent() {
        stubStreamedToolLoop()

        client.beta().messages().toolRunner(toolParams()).streaming().forEach { stream ->
            stream.stream().forEach {}
        }

        val calls = recorder.spans.filter { it.name == "anthropic.messages.create" }
        assertThat(calls).hasSize(2).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(it.string("gen_ai.response.model")).isEqualTo("claude-served")
            assertThat(it.long("gen_ai.usage.output_tokens")).isEqualTo(7)
            assertThat(attributesOf(it))
                .doesNotContainKeys("gen_ai.input.messages", "gen_ai.output.messages")
        }
        assertThat(calls.map { it.string("anthropic.message.stop_reason") })
            .containsExactly("tool_use", "end_turn")
    }

    @Test
    fun streamingToolRunner_streamClosedEarly_recordsTheOutputThatArrived() {
        stubStreamedToolLoop()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().toolRunner(toolParams()).streaming().iterator().next().use {
            response ->
            val events = response.stream().iterator()
            repeat(3) { events.next() }
        }

        // The span reads what the runner's accumulator holds of the Message.
        val call = recorder.named("anthropic.messages.create")
        assertThat(call.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(call.string("gen_ai.response.id")).isEqualTo("msg_1")
        assertThat(call.string("gen_ai.input.messages")).isNotNull()
        assertThat(call.string("gen_ai.output.messages")).isEqualTo(TRUNCATED_OUTPUT_MESSAGES)
        assertThat(call.string("anthropic.message.stop_reason")).isNull()
    }

    @Test
    fun streamingToolRunner_runsTheSameLoopWhateverTheTracing() {
        // The runner's own accumulator makes the Message that the loop goes on with, and tracing
        // only reads it: without spans, with spans that are sampled out, and with spans that
        // record content, the application reads the same events and the runner sends the same
        // requests.
        stubStreamedToolLoop()
        val sampledOut =
            OpenTelemetryOptions.builder()
                .tracerProvider(SdkTracerProvider.builder().setSampler(Sampler.alwaysOff()).build())
                .traces(
                    OpenTelemetryTraces.builder()
                        .contentMode(OpenTelemetryContentMode.CONTENT)
                        .build()
                )
                .build()
        val clients =
            listOf(
                clientBuilder().openTelemetry(false).build(),
                clientBuilder().openTelemetry(sampledOut).build(),
                clientBuilder(OpenTelemetryContentMode.CONTENT).build(),
            )

        val runs =
            clients.map { client ->
                resetAllScenarios()
                resetAllRequests()
                val events = mutableListOf<String>()
                client.beta().messages().toolRunner(toolParams()).streaming().forEach { stream ->
                    stream.stream().forEach { events.add(it.toString()) }
                }
                val requests =
                    findAll(postRequestedFor(urlPathEqualTo("/v1/messages"))).map {
                        it.bodyAsString
                    }
                events to requests
            }

        val (events, requests) = runs[0]
        assertThat(events).hasSize(STREAM_EVENTS.size + 7)
        assertThat(requests).hasSize(2)
        assertThat(requests[1]).contains("toolu_1", "Paris", "foggy")
        assertThat(runs[1]).isEqualTo(runs[0])
        assertThat(runs[2]).isEqualTo(runs[0])
        // Only the last client's spans record, and its streamed requests have their output.
        assertThat(recorder.spans.filter { it.name == "anthropic.messages.create" })
            .hasSize(2)
            .allSatisfy { assertThat(it.string("gen_ai.output.messages")).isNotNull() }
    }

    @Test
    fun toolRunner_stepsCalledFromOtherContexts_runInTheToolRunnerSpan() {
        // The Messages service is a stand-in that starts a span, as an instrumented library that
        // the runner calls does, so that the span shows what is current in the runner's own code.
        val tracer = recorder.tracerProvider.get("app")
        val responses =
            ArrayDeque(
                listOf(TOOL_USE_MESSAGE_JSON, FINAL_MESSAGE_JSON).map {
                    jsonMapper().readValue(it.trimIndent(), BetaMessage::class.java)
                }
            )
        val service = mock<MessageService>()
        whenever(service.create(any<BetaMessageCreateParams>(), any())).thenAnswer {
            tracer.spanBuilder("instrumented").startSpan().end()
            responses.removeFirst()
        }
        val clientOptions =
            ClientOptions.builder().httpClient(FakeHttpClient()).openTelemetry(options()).build()
        val runner =
            BetaToolRunner(
                service,
                clientOptions,
                ToolRunnerCreateParams.builder().initialMessageParams(toolParams()).build(),
                RequestOptions.none(),
            )
        val steps = runner.iterator()
        // What is current in the application's code after each step.
        val seenByTheApplication = mutableListOf<String>()

        // The application asks for each message from another context, and one from none.
        val first = tracer.spanBuilder("first").startSpan()
        first.makeCurrent().use {
            steps.next()
            seenByTheApplication.add(Span.current().spanContext.spanId)
        }
        first.end()
        val second = tracer.spanBuilder("second").startSpan()
        second.makeCurrent().use {
            steps.next()
            seenByTheApplication.add(Span.current().spanContext.spanId)
        }
        second.end()
        assertThat(steps.hasNext()).isFalse()
        seenByTheApplication.add(Span.current().spanContext.spanId)

        val toolRunner = recorder.named("anthropic.messages.tool_runner")
        assertThat(toolRunner.status.statusCode).isEqualTo(StatusCode.OK)
        // The tool runner span started in the first step, and every later step is in it too.
        assertThat(toolRunner.parentSpanId).isEqualTo(first.spanContext.spanId)
        assertThat(recorder.spans.filter { it.name == "instrumented" }).hasSize(2).allSatisfy {
            assertThat(it.parentSpanId).isEqualTo(toolRunner.spanId)
        }
        assertThat(recorder.named("anthropic.custom_tool_use get_weather").parentSpanId)
            .isEqualTo(toolRunner.spanId)
        // Between the steps the application's own span is current, and not the tool runner span.
        assertThat(seenByTheApplication)
            .containsExactly(
                first.spanContext.spanId,
                second.spanContext.spanId,
                SpanContext.getInvalid().spanId,
            )
    }

    @Test
    fun streamingToolRunner_stepsCalledFromOtherContexts_runInTheToolRunnerSpan() {
        stubStreamedToolLoop()
        val tracer = recorder.tracerProvider.get("app")
        val seenByTheApplication = mutableListOf<String>()

        val first = tracer.spanBuilder("first").startSpan()
        val second = tracer.spanBuilder("second").startSpan()
        val steps = client.beta().messages().toolRunner(toolParams()).streaming().iterator()
        first.makeCurrent().use {
            steps.next().stream().forEach {
                seenByTheApplication.add(Span.current().spanContext.spanId)
            }
        }
        // The tool runs in this step, and the second request is sent in it.
        second.makeCurrent().use {
            steps.next().stream().forEach {
                seenByTheApplication.add(Span.current().spanContext.spanId)
            }
        }
        assertThat(steps.hasNext()).isFalse()
        first.end()
        second.end()

        val toolRunner = recorder.named("anthropic.messages.tool_runner")
        assertThat(toolRunner.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(toolRunner.parentSpanId).isEqualTo(first.spanContext.spanId)
        assertThat(recorder.spans.filter { it.name == "anthropic.messages.create" })
            .hasSize(2)
            .allSatisfy { assertThat(it.parentSpanId).isEqualTo(toolRunner.spanId) }
        assertThat(recorder.named("anthropic.custom_tool_use get_weather").parentSpanId)
            .isEqualTo(toolRunner.spanId)
        // While the application reads a streamed message, its own span is current.
        assertThat(seenByTheApplication.distinct())
            .containsExactly(first.spanContext.spanId, second.spanContext.spanId)
    }

    @Test
    fun toolRunner_failedCall_failsLoopSpan() {
        stubFor(
            post(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(429)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )

        assertThrows<RateLimitException> {
            client.beta().messages().toolRunner(toolParams()).toList()
        }

        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.ERROR)
        assertThat(loop.string("error.type")).isEqualTo("rate_limit_error")
        assertThat(recorder.named("anthropic.messages.create").parentSpanId).isEqualTo(loop.spanId)
    }

    /**
     * Runs [use] on [runner]. Not inline, so that no frame refers to the runner once this returns.
     */
    private fun abandonAfter(runner: BetaToolRunner, use: (BetaToolRunner) -> Unit) = use(runner)

    @Test
    fun toolRunner_stoppedWithItsIteratorHeld_leavesLoopSpanOpen() {
        stubToolLoop()

        // The application holds the iterator alone, which keeps the runner from being reclaimed.
        val iterator = client.beta().messages().toolRunner(toolParams()).iterator()
        iterator.next()
        collectGarbage()

        // The application may still go on, so the loop's span has not ended. Only the call's has.
        assertThat(recorder.spans.map { it.name }).containsExactly("anthropic.messages.create")
        assertThat(iterator.asSequence().toList()).hasSize(1)
        assertThat(recorder.named("anthropic.messages.tool_runner").status.statusCode)
            .isEqualTo(StatusCode.OK)
    }

    @Test
    fun toolRunner_abandoned_endsLoopSpanUnsetOnceReclaimed() {
        stubToolLoop()

        // The application reads the first message and stops.
        abandonAfter(client.beta().messages().toolRunner(toolParams())) { runner ->
            assertThat(runner.iterator().next().id()).isEqualTo("msg_1")
        }

        val loop = awaitReclaimed("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(loop.string("error.type")).isNull()
        val call = recorder.named("anthropic.messages.create")
        assertThat(call.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(call.parentSpanId).isEqualTo(loop.spanId)
        assertThat(recorder.spans.map { it.name })
            .containsExactly("anthropic.messages.create", "anthropic.messages.tool_runner")
    }

    @Test
    fun toolRunner_toolInterrupted_leavesToolSpanUnset() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "stopping_tool"))
                        .withHeader("Content-Type", "application/json")
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
        val params = toolParams().toBuilder().addTool(StoppingTool::class.java).build()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        val messages = client.beta().messages().toolRunner(params).toList()

        assertThat(messages).hasSize(2)
        val tool = recorder.named("anthropic.custom_tool_use stopping_tool")
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(tool.string("error.type")).isNull()
        // A canceled run keeps its arguments, and records no result.
        assertThat(tool.string("gen_ai.tool.call.arguments")).isNotNull()
        assertThat(tool.string("anthropic.tool_result.content")).isNull()
        // The model is told, as of any other exception.
        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))[1].bodyAsString)
            .contains("Error: stopped looking up Paris")
        assertThat(recorder.named("anthropic.messages.tool_runner").status.statusCode)
            .isEqualTo(StatusCode.OK)
    }

    @Test
    fun toolRunner_memoryToolWithoutHandler_failsTheLoop() {
        // A tool the application named `memory` is dispatched as the memory tool, which needs a
        // handler: the loop fails fast instead of sending an error result back.
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "memory"))
                        .withHeader("Content-Type", "application/json")
                )
        )
        val params =
            toolParams()
                .toBuilder()
                .addTool(
                    BetaTool.builder()
                        .name("memory")
                        .inputSchema(BetaTool.InputSchema.builder().build())
                        .build()
                )
                .build()

        assertThrows<NoSuchElementException> {
            client.beta().messages().toolRunner(params).toList()
        }

        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))).hasSize(1)
        assertThat(recorder.spans.map { it.name })
            .containsExactly("anthropic.messages.create", "anthropic.messages.tool_runner")
        assertThat(recorder.named("anthropic.messages.tool_runner").status.statusCode)
            .isEqualTo(StatusCode.ERROR)
    }

    @Test
    fun toolRunner_reclaimedAfterCompletion_keepsLoopSpanOk() {
        stubToolLoop()

        abandonAfter(client.beta().messages().toolRunner(toolParams())) { runner ->
            assertThat(runner.toList()).hasSize(2)
        }
        collectGarbage()

        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(loop.hasEnded()).isTrue()
    }

    @Test
    fun streamingToolRunner_abandoned_endsLoopAndCallSpansUnsetOnceReclaimed() {
        stubStream()

        abandonAfter(client.beta().messages().toolRunner(toolParams())) { runner ->
            // The application reads part of the first message and stops without closing it.
            runner.streaming().iterator().next().stream().findFirst()
        }

        val loop = awaitReclaimed("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(loop.string("error.type")).isNull()
        val call = awaitReclaimed("anthropic.messages.create")
        assertThat(call.status.statusCode).isEqualTo(StatusCode.UNSET)
        assertThat(call.string("error.type")).isNull()
        assertThat(call.parentSpanId).isEqualTo(loop.spanId)
        assertThat(recorder.spans.map { it.name })
            .containsExactlyInAnyOrder(
                "anthropic.messages.create",
                "anthropic.messages.tool_runner",
            )
    }

    @Test
    fun contentMode_toolRunner_recordsArgumentsAndResult() {
        stubToolLoop()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().toolRunner(toolParams()).toList()

        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"location":"Paris"}""")
        assertThat(tool.string("anthropic.tool_result.content"))
            .isEqualTo("""[{"type":"text","text":"The weather in Paris is foggy"}]""")
        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.tool.call.result")
        assertThat(attributesOf(tool)).doesNotContainKey("anthropic.content.truncated")
        assertThat(recorder.named("anthropic.messages.tool_runner").string("gen_ai.input.messages"))
            .isNull()
    }

    @Test
    fun contentMode_toolSpan_recordsTheContentBlocksOfTheResult() {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient())
                .openTelemetry(options(OpenTelemetryContentMode.CONTENT))
                .build()
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("toolu_1")
                .name("search")
                .input(BetaToolUseBlockParam.Input.builder().build())
                .build()
        val content =
            jsonMapper()
                .readTree(
                    """
                    [
                      {"type": "text", "text": "cited",
                       "citations": [
                         {"type": "char_location", "cited_text": "quote", "document_index": 0, "document_title": "t", "start_char_index": 0, "end_char_index": 5},
                         {"type": "web_search_result_location", "cited_text": "quote", "title": "t", "url": "https://example.com", "encrypted_index": "OPAQUE"}
                       ],
                       "cache_control": {"type": "ephemeral"}},
                      {"type": "search_result", "source": "https://example.com", "title": "Found",
                       "content": [{"type": "text", "text": "hit"}]},
                      {"type": "image", "source": {"type": "base64", "media_type": "image/png", "data": "PRIVATE"}}
                    ]
                    """
                )
        val results =
            listOf(
                BetaToolResultBlockParam.builder()
                    .toolUseId("toolu_1")
                    .content(JsonValue.fromJsonNode(content))
                    .build(),
                BetaToolResultBlockParam.builder().toolUseId("toolu_1").build(),
            )

        for (result in results) {
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
        }

        // Text and search result blocks with all their fields but the encrypted ones, the type
        // only of any other block, without a prefix, and an empty list for a result without
        // content.
        val (withBlocks, withoutContent) = recorder.spans
        assertThat(jsonMapper().readTree(withBlocks.string("anthropic.tool_result.content")))
            .isEqualTo(
                jsonMapper()
                    .readTree(
                        """
                        [
                          {"type": "text", "text": "cited",
                           "citations": [
                             {"type": "char_location", "cited_text": "quote", "document_index": 0, "document_title": "t", "start_char_index": 0, "end_char_index": 5},
                             {"type": "web_search_result_location", "cited_text": "quote", "title": "t", "url": "https://example.com"}
                           ],
                           "cache_control": {"type": "ephemeral"}},
                          {"type": "search_result", "source": "https://example.com", "title": "Found",
                           "content": [{"type": "text", "text": "hit"}]},
                          {"type": "image"}
                        ]
                        """
                    )
            )
        assertThat(withBlocks.attributeText()).doesNotContain("PRIVATE", "OPAQUE")
        assertThat(withoutContent.string("anthropic.tool_result.content")).isEqualTo("[]")
    }

    @Test
    fun contentMode_toolSpan_resultWithNoContent_recordsAnEmptyList() {
        val toolResult = { BetaToolResultBlockParam.builder().toolUseId("toolu_1") }
        val results =
            listOf(
                toolResult().build(),
                toolResult().content(JsonValue.from(null)).build(),
                toolResult().content(JsonValue.from(emptyList<Any>())).build(),
                toolResult().content(JsonValue.from(emptyList<Any>())).isError(true).build(),
            )

        val tools = results.map { toolSpanOf(OpenTelemetryContentMode.CONTENT, "q", it) }

        // A JSON list in every case, and never `null` or an object.
        assertThat(tools.map { it.string("anthropic.tool_result.content") }).containsOnly("[]")
        assertThat(tools).allSatisfy {
            assertThat(attributesOf(it)).doesNotContainKey("anthropic.content.truncated")
        }
    }

    /** The span of a run of a tool with [input] that the runner made [result] of. */
    private fun toolSpanOf(
        contentMode: OpenTelemetryContentMode,
        input: String,
        result: BetaToolResultBlockParam,
    ): SpanData {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient())
                .openTelemetry(options(contentMode))
                .build()
        val toolUse =
            BetaToolUseBlockParam.builder()
                .id("toolu_1")
                .name("search")
                .input(
                    BetaToolUseBlockParam.Input.builder()
                        .putAdditionalProperty("query", JsonValue.from(input))
                        .build()
                )
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
        return recorder.spans.last()
    }

    @Test
    fun contentMode_toolSpan_overTheLimit_removesResultBlocksFromTheEnd() {
        val huge = "h".repeat(CONTENT_LIMIT)
        val result =
            BetaToolResultBlockParam.builder()
                .toolUseId("toolu_1")
                .content(
                    JsonValue.from(
                        listOf("first", huge, "last").map { mapOf("type" to "text", "text" to it) }
                    )
                )
                .build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, "q", result)

        // The last block goes first, although it is small, and then the one that is too large.
        assertThat(tool.string("anthropic.tool_result.content"))
            .isEqualTo("""[{"type":"text","text":"first"}]""")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"query":"q"}""")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
        assertThat(tool.status.statusCode).isEqualTo(StatusCode.OK)
    }

    @ParameterizedTest
    @CsvSource(
        "20, []",
        "75, []",
        "76, '[{\"type\":\"text\",\"text\":\"The weather in Paris is foggy\"}]'",
    )
    fun maxContentBytes_toolSpan_boundsTheArgumentsAndTheResultTogether(
        maxContentBytes: Long,
        resultContent: String,
    ) {
        stubToolLoop()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes).build()

        client.beta().messages().toolRunner(toolParams()).toList()

        // The arguments take 20 of the bound and stay whole, because they are not over it alone.
        // The result takes 56, and its one block goes whole when the two are over the bound
        // together.
        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"location":"Paris"}""")
        assertThat(tool.string("anthropic.tool_result.content")).isEqualTo(resultContent)
        assertThat(attributesOf(tool).containsKey("anthropic.content.truncated"))
            .isEqualTo(resultContent == "[]")
    }

    @Test
    fun maxContentBytes_toolSpan_argumentsOverTheBoundAlone_areLeftOutWhole() {
        stubToolLoop()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT, maxContentBytes = 19).build()

        client.beta().messages().toolRunner(toolParams()).toList()

        // The arguments take 20, one over the bound, so the span goes without them and is
        // truncated. The result, which takes 56, then has all of the bound, and does not fit it.
        val tool = recorder.named("anthropic.custom_tool_use get_weather")
        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.tool.call.arguments")
        assertThat(tool.string("anthropic.tool_result.content")).isEqualTo("[]")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_toolSpan_stringResultOverTheLimit_isAnEmptyList() {
        val result =
            BetaToolResultBlockParam.builder()
                .toolUseId("toolu_1")
                .content("h".repeat(CONTENT_LIMIT))
                .build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, "q", result)

        assertThat(tool.string("anthropic.tool_result.content")).isEqualTo("[]")
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"query":"q"}""")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_toolSpan_argumentsOverTheLimit_areLeftOutAndTheResultIsWhole() {
        val huge = "h".repeat(CONTENT_LIMIT)
        val result =
            BetaToolResultBlockParam.builder().toolUseId("toolu_1").content("small").build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, huge, result)

        // The arguments alone are over the limit, so the span goes without them, and the result
        // has all of the limit.
        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.tool.call.arguments")
        assertThat(tool.string("anthropic.tool_result.content"))
            .isEqualTo("""[{"type":"text","text":"small"}]""")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_toolSpan_argumentsAtTheLimit_areCompleteAndEmptyTheResult() {
        // The JSON of the arguments is the limit to the character.
        val huge = "h".repeat(CONTENT_LIMIT - """{"query":""}""".length)
        val result =
            BetaToolResultBlockParam.builder().toolUseId("toolu_1").content("small").build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, huge, result)

        // The arguments are not over the limit alone, so they stay and leave the result no room.
        assertThat(tool.string("gen_ai.tool.call.arguments")).isEqualTo("""{"query":"$huge"}""")
        assertThat(tool.string("anthropic.tool_result.content")).isEqualTo("[]")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_toolSpan_measuresTheLimitInBytesOfUtf8() {
        // Three bytes each: the JSON of the arguments is the limit to the byte, in a third of it
        // in chars. With one more it is a byte over the limit.
        val atTheLimit = "€".repeat((CONTENT_LIMIT - """{"query":""}""".length) / 3)
        assertThat("""{"query":"$atTheLimit"}""".toByteArray()).hasSize(CONTENT_LIMIT)
        val result = BetaToolResultBlockParam.builder().toolUseId("toolu_1").content("é").build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, atTheLimit, result)

        assertThat(tool.string("gen_ai.tool.call.arguments"))
            .isEqualTo("""{"query":"$atTheLimit"}""")
        assertThat(tool.string("anthropic.tool_result.content")).isEqualTo("[]")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun contentMode_toolSpan_argumentsOneByteOverTheLimit_areLeftOut() {
        val over = "h" + "€".repeat((CONTENT_LIMIT - """{"query":""}""".length) / 3)
        assertThat("""{"query":"$over"}""".toByteArray()).hasSize(CONTENT_LIMIT + 1)
        val result = BetaToolResultBlockParam.builder().toolUseId("toolu_1").content("é").build()

        val tool = toolSpanOf(OpenTelemetryContentMode.CONTENT, over, result)

        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.tool.call.arguments")
        assertThat(tool.string("anthropic.tool_result.content"))
            .isEqualTo("""[{"type":"text","text":"é"}]""")
        assertThat(attributesOf(tool)).containsEntry("anthropic.content.truncated", true)
    }

    @Test
    fun metadataOnly_toolSpan_overTheLimit_recordsNoContent() {
        val result =
            BetaToolResultBlockParam.builder()
                .toolUseId("toolu_1")
                .content("h".repeat(CONTENT_LIMIT))
                .build()

        val tool = toolSpanOf(OpenTelemetryContentMode.METADATA_ONLY, "q", result)

        assertThat(attributesOf(tool))
            .doesNotContainKeys(
                "gen_ai.tool.call.arguments",
                "anthropic.tool_result.content",
                "anthropic.content.truncated",
            )
    }

    @Test
    fun contentMode_toolRunner_toolError_recordsTheErrorResult() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "broken_tool"))
                        .withHeader("Content-Type", "application/json")
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
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        client.beta().messages().toolRunner(toolParams()).toList()

        // The result that reports the error is recorded like any other: the span's `error.type`
        // says that it is an error.
        val tool = recorder.named("anthropic.custom_tool_use broken_tool")
        assertThat(tool.string("error.type")).isEqualTo("tool_error")
        assertThat(tool.string("anthropic.tool_result.content"))
            .isEqualTo("""[{"type":"text","text":"Error: BOOM Paris"}]""")
        assertThat(attributesOf(tool)).doesNotContainKey("gen_ai.tool.call.result")
    }

    @Test
    fun toolRunner_withAnOutputFormat_recordsTheOutputType() {
        val format =
            BetaJsonOutputFormat.builder()
                .schema(
                    BetaJsonOutputFormat.Schema.builder()
                        .additionalProperties(mapOf("type" to JsonValue.from("object")))
                        .build()
                )
                .build()

        // In `output_config.format`, and in the deprecated `output_format`.
        stubToolLoop()
        val inOutputConfig =
            toolParams()
                .toBuilder()
                .outputConfig(BetaOutputConfig.builder().format(format).build())
                .build()
        client.beta().messages().toolRunner(inOutputConfig).toList()
        resetAllScenarios()
        client
            .beta()
            .messages()
            .toolRunner(toolParams().toBuilder().outputFormat(format).build())
            .toList()

        val loops = recorder.spans.filter { it.name == "anthropic.messages.tool_runner" }
        assertThat(loops).hasSize(2).allSatisfy {
            assertThat(it.string("gen_ai.output.type")).isEqualTo("json")
        }
        // Each request of the loop sets it too.
        val calls = recorder.spans.filter { it.name == "anthropic.messages.create" }
        assertThat(calls).hasSize(4).allSatisfy {
            assertThat(it.string("gen_ai.output.type")).isEqualTo("json")
        }
    }

    @Test
    fun toolRunner_withAnOutputFormatThatIsNotJson_recordsNoOutputType() {
        stubToolLoop()
        val format =
            BetaJsonOutputFormat.builder()
                .schema(
                    BetaJsonOutputFormat.Schema.builder()
                        .additionalProperties(mapOf("type" to JsonValue.from("object")))
                        .build()
                )
                .type(JsonValue.from("xml_schema"))
                .build()

        client
            .beta()
            .messages()
            .toolRunner(
                toolParams()
                    .toBuilder()
                    .outputConfig(BetaOutputConfig.builder().format(format).build())
                    .build()
            )
            .toList()

        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(attributesOf(loop)).doesNotContainKey("gen_ai.output.type")
    }

    /**
     * Params whose `output_config` the application set as raw JSON, which the typed getter that the
     * spans read rejects. The request itself is sent with it as it is.
     */
    private fun paramsWithARawOutputConfig(): BetaMessageCreateParams =
        toolParams()
            .toBuilder()
            .outputConfig(
                JsonValue.from(
                    mapOf(
                        "format" to
                            mapOf("type" to "json_schema", "schema" to mapOf("type" to "object"))
                    )
                )
            )
            .build()

    @Test
    fun toolRunner_rawOutputConfig_runsTheLoopWithoutTheOutputType() {
        stubToolLoop()

        val messages = client.beta().messages().toolRunner(paramsWithARawOutputConfig()).toList()

        assertThat(messages).hasSize(2)
        val loop = recorder.named("anthropic.messages.tool_runner")
        assertThat(loop.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(loop.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(loop.string("gen_ai.output.type")).isNull()
        val sent = findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))
        assertThat(sent).hasSize(2).allSatisfy {
            assertThat(it.bodyAsString).contains(""""output_config":{"format":{""")
        }
    }

    @Test
    fun toolRunner_rawOutputConfig_withoutOpenTelemetryOptions_runsTheLoop() {
        stubToolLoop()
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .build()

        val messages = client.beta().messages().toolRunner(paramsWithARawOutputConfig()).toList()

        assertThat(messages).hasSize(2)
        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))).hasSize(2)
    }

    @Test
    fun streamingToolRunner_rawOutputConfig_runsTheLoopWithoutTheOutputType() {
        stubStream()
        val plain =
            AnthropicOkHttpClient.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .build()

        // Without OpenTelemetry options, and with a tracer provider: the first message streams.
        for (client in listOf(plain, client)) {
            abandonAfter(client.beta().messages().toolRunner(paramsWithARawOutputConfig())) { runner
                ->
                val events = runner.streaming().iterator().next().stream().count()
                assertThat(events).isEqualTo(STREAM_EVENTS.size.toLong())
            }
        }

        assertThat(findAll(postRequestedFor(urlPathEqualTo("/v1/messages")))).hasSize(2)
        val loop = awaitReclaimed("anthropic.messages.tool_runner")
        assertThat(loop.string("gen_ai.request.model")).isEqualTo("claude-requested")
        assertThat(loop.string("gen_ai.output.type")).isNull()
    }

    @Test
    fun toolRunner_spanStartedInsideATool_isAChildOfTheToolSpan() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("runner")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok(TOOL_USE_MESSAGE_JSON.trimIndent().replace("get_weather", "tracing_tool"))
                        .withHeader("Content-Type", "application/json")
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
        val params = toolParams().toBuilder().addTool(TracingTool::class.java).build()
        // The tool starts a span of its own, as an instrumented library that it calls does.
        TracingTool.onRun.set {
            recorder.tracerProvider.get("app").spanBuilder("lookup").startSpan().end()
        }

        try {
            client.beta().messages().toolRunner(params).toList()
        } finally {
            TracingTool.onRun.set(null)
        }

        val tool = recorder.named("anthropic.custom_tool_use tracing_tool")
        assertThat(recorder.named("lookup").parentSpanId).isEqualTo(tool.spanId)
        assertThat(tool.parentSpanId)
            .isEqualTo(recorder.named("anthropic.messages.tool_runner").spanId)
    }
}

@JsonClassDescription("A tool that calls an instrumented library")
internal class TracingTool : Supplier<String> {
    lateinit var location: String

    override fun get(): String {
        onRun.get()?.run()
        return "Looked up $location"
    }

    companion object {
        /** What the test does while the tool runs. */
        val onRun = AtomicReference<Runnable?>()
    }
}
