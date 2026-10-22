package com.anthropic.helpers

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.InternalServerException
import com.anthropic.helpers.BetaMessageThreadTest.Companion.CREATE
import com.anthropic.helpers.BetaMessageThreadTest.Companion.PLAIN
import com.anthropic.helpers.BetaMessageThreadTest.Companion.THREADS_BETA
import com.anthropic.helpers.BetaMessageThreadTest.Companion.UNTHREADED
import com.anthropic.helpers.BetaMessageThreadTest.Companion.WEATHER_TOOL_REMOVAL
import com.anthropic.helpers.BetaMessageThreadTest.Companion.assistant
import com.anthropic.helpers.BetaMessageThreadTest.Companion.betas
import com.anthropic.helpers.BetaMessageThreadTest.Companion.blockStart
import com.anthropic.helpers.BetaMessageThreadTest.Companion.blockStop
import com.anthropic.helpers.BetaMessageThreadTest.Companion.client
import com.anthropic.helpers.BetaMessageThreadTest.Companion.continues
import com.anthropic.helpers.BetaMessageThreadTest.Companion.error as errorBody
import com.anthropic.helpers.BetaMessageThreadTest.Companion.fallbackUsage
import com.anthropic.helpers.BetaMessageThreadTest.Companion.inputDelta
import com.anthropic.helpers.BetaMessageThreadTest.Companion.json
import com.anthropic.helpers.BetaMessageThreadTest.Companion.message
import com.anthropic.helpers.BetaMessageThreadTest.Companion.ownThread
import com.anthropic.helpers.BetaMessageThreadTest.Companion.refusal
import com.anthropic.helpers.BetaMessageThreadTest.Companion.reply
import com.anthropic.helpers.BetaMessageThreadTest.Companion.sse
import com.anthropic.helpers.BetaMessageThreadTest.Companion.start
import com.anthropic.helpers.BetaMessageThreadTest.Companion.stop
import com.anthropic.helpers.BetaMessageThreadTest.Companion.textBlock
import com.anthropic.helpers.BetaMessageThreadTest.Companion.threadError
import com.anthropic.helpers.BetaMessageThreadTest.Companion.timeTool
import com.anthropic.helpers.BetaMessageThreadTest.Companion.tool
import com.anthropic.helpers.BetaMessageThreadTest.Companion.user
import com.anthropic.helpers.BetaMessageThreadTest.Companion.userParam
import com.anthropic.helpers.BetaMessageThreadTest.Companion.wire
import com.anthropic.models.beta.messages.BetaFallbackParam
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUnion
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import com.anthropic.models.beta.messages.ToolRunnerCreateParams
import com.anthropic.services.blocking.beta.MessageService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.context.Context
import io.opentelemetry.sdk.trace.ReadWriteSpan
import io.opentelemetry.sdk.trace.ReadableSpan
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.SpanProcessor
import io.opentelemetry.sdk.trace.data.SpanData
import java.time.Duration
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicInteger
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/** The tool runner on a message thread. [BetaToolRunnerTest] has the runner without one. */
internal class BetaToolRunnerThreadTest {

    /** How many times [weather] ran. */
    private val runs = AtomicInteger()

    private val weather =
        BetaRunnableTool.of(tool("get_weather")) { _ ->
            runs.incrementAndGet()
            BetaToolResultBlockParam.Content.ofString("Sunny")
        }

    /** How many times [lookup] ran. */
    private val lookups = AtomicInteger()

    private val lookup =
        BetaRunnableTool.of(tool("lookup")) { _ ->
            lookups.incrementAndGet()
            BetaToolResultBlockParam.Content.ofString("Found")
        }

    @Test
    fun `the runner starts a thread and continues it with the tool results alone`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))

        val replies = runner(http).toList()

        assertThat(replies.map { it.id() }).containsExactly("msg_a", "msg_b")
        val (create, next) = http.jsonBodies
        assertThat(create["thread"]).isEqualTo(CREATE)
        assertThat(create["messages"]).isEqualTo(json("""[$QUESTION]"""))
        assertThat(create["system"].asText()).isEqualTo("Be brief.")
        assertThat(create["tools"]).hasSize(1)
        assertThat(next["thread"]).isEqualTo(continues("msg_a"))
        assertThat(next["messages"]).isEqualTo(json("""[${result("toolu_1")}]"""))
        assertThat(next.has("system")).isFalse
        assertThat(next.has("tools")).isFalse
        for (index in 0..1) {
            assertThat(betas(http, index)).containsExactly(THREADS_BETA)
            assertThat(http.requests[index].headers.values(STAINLESS_HELPER_HEADER))
                .containsExactly("BetaToolRunner, message-thread")
        }
        assertThat(runs.get()).isEqualTo(1)
    }

    @ParameterizedTest
    @CsvSource("create,true", "create,false", "continue,true", "continue,false")
    fun `a thread value in the params is refused before a request is sent or a tool runs`(
        type: String,
        threaded: Boolean,
    ) {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"))

        val atCreation =
            assertThrows<IllegalArgumentException> {
                runner(http, thread = threaded) { thread(ownThread(type)) }
            }
        val runner = runner(http, thread = threaded)
        val inNextParams =
            assertThrows<IllegalArgumentException> {
                for (reply in runner) {
                    runner.setNextParams(
                        runner.params().toBuilder().thread(ownThread(type)).build()
                    )
                }
            }

        assertThat(atCreation).hasMessage(OWN_THREAD_REFUSAL)
        assertThat(inNextParams).hasMessage(OWN_THREAD_REFUSAL)
        assertThat(http.requests).hasSize(1)
        assertThat(runs.get()).isEqualTo(0)
    }

    @Test
    fun `a thread value in the params of a runner over a thread is refused`() {
        val http = FakeHttpClient()
        val thread = thread(http)

        val refused =
            assertThrows<IllegalArgumentException> {
                thread.toolRunner {
                    it.initialMessageParams(params { thread(ownThread("create")) })
                }
            }

        assertThat(refused).hasMessage(OWN_THREAD_REFUSAL)
    }

    @Test
    fun `a runner over a thread that is told to run without one is refused`() {
        val http = FakeHttpClient()
        val thread = thread(http)

        val refused =
            assertThrows<IllegalArgumentException> { thread.toolRunner { it.thread(false) } }

        assertThat(refused)
            .hasMessage(
                "`thread(false)` cannot be set on a tool runner that a message thread created. " +
                    "To run without a message thread, create the runner with " +
                    "`messages().toolRunner()`."
            )
        thread.toolRunner { it.thread(true) }
        assertThat(http.requests).isEmpty()
    }

    @Test
    fun `the runner sends no thread when the caller opts out`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))

        runner(http, thread = false) { addBeta("other-beta") }.toList()

        val bodies = http.jsonBodies
        for (index in 0..1) {
            assertThat(bodies[index].has("thread")).isFalse
            assertThat(bodies[index]["tools"]).hasSize(1)
            assertThat(betas(http, index)).containsExactly("other-beta")
            assertThat(http.requests[index].headers.values(STAINLESS_HELPER_HEADER))
                .containsExactly("BetaToolRunner")
        }
        assertThat(bodies[1]["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
    }

    @Test
    fun `the runner sends no thread on a client whose service takes no threads`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))

        runner(http, configure = { backend(UNTHREADED) }).toList()

        val bodies = http.jsonBodies
        for (index in 0..1) {
            assertThat(bodies[index].has("thread")).isFalse
            assertThat(betas(http, index)).isEmpty()
            assertThat(http.requests[index].headers.values(STAINLESS_HELPER_HEADER))
                .containsExactly("BetaToolRunner")
        }
        assertThat(bodies[1]["messages"]).hasSize(3)
    }

    @Test
    fun `the runner threads on a client or a service of which nothing is known`() {
        val http = FakeHttpClient(message("msg_a", "Sunny"))
        val ownService = mock<MessageService>()
        whenever(ownService.create(any<MessageCreateParams>(), any()))
            .thenReturn(jsonMapper().readValue(message("msg_b", "Sunny"), BetaMessage::class.java))
        val toolRunnerParams =
            ToolRunnerCreateParams.builder().initialMessageParams(params()).build()

        runner(http, configure = { backend(PLAIN) }).toList()
        BetaToolRunner(
                ownService,
                ClientOptions.builder().httpClient(FakeHttpClient()).build(),
                toolRunnerParams,
                RequestOptions.none(),
            )
            .toList()

        assertThat(http.jsonBodies.single()["thread"]).isEqualTo(CREATE)
        val sent = argumentCaptor<MessageCreateParams>()
        verify(ownService).create(sent.capture(), any())
        assertThat(sent.firstValue.thread().get().isCreate()).isTrue
    }

    @Test
    fun `a runner over a thread continues it, and the thread continues from the runner`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                toolUse("msg_b", "toolu_1"),
                message("msg_c", "Sunny"),
                message("msg_d", "Bye"),
            )
        val thread = thread(http)
        thread.send("Hello")
        thread.append(userParam("What is the weather in Paris?"))

        val replies = thread.toolRunner().toList()
        thread.send("Bye")

        assertThat(replies.map { it.id() }).containsExactly("msg_b", "msg_c")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"].path("previous_message_id").asText("") })
            .containsExactly("", "msg_a", "msg_b", "msg_c")
        assertThat(bodies[1]["messages"]).isEqualTo(json("""[$QUESTION]"""))
        assertThat(bodies[2]["messages"]).isEqualTo(json("""[${result("toolu_1")}]"""))
        assertThat(http.requests.map { it.headers.values(STAINLESS_HELPER_HEADER).single() })
            .containsExactly(
                "message-thread",
                "BetaToolRunner, message-thread",
                "BetaToolRunner, message-thread",
                "message-thread",
            )
        assertThat(thread.messages()).hasSize(8)
    }

    @ParameterizedTest
    @EnumSource(AppendedAt::class)
    fun `a message appended to the thread of a runner is sent, after the results of a tool call`(
        at: AppendedAt
    ) {
        val streaming = at == AppendedAt.WHILE_THE_CALL_IS_STREAMED
        val http =
            FakeHttpClient(
                if (streaming) streamedToolUse("msg_a", "toolu_1") else toolUse("msg_a", "toolu_1"),
                if (streaming) sse(start("msg_b"), *textBlock(0, "Sunny"), *stop())
                else message("msg_b", "Sunny"),
            )
        lateinit var thread: BetaMessageThread
        val appendIf = { now: AppendedAt ->
            if (now == at) thread.append(userParam("In one word."))
        }
        val tool =
            BetaRunnableTool.of(tool("get_weather")) { _ ->
                appendIf(AppendedAt.IN_THE_TOOL)
                BetaToolResultBlockParam.Content.ofString("Sunny")
            }
        thread = BetaMessageThreadTest.thread(http) { addTool(tool) }
        thread.append(userParam("What is the weather in Paris?"))

        val runner = thread.toolRunner()
        appendIf(AppendedAt.BEFORE_THE_RUN)
        if (streaming) {
            runner.streaming().forEachIndexed { index, stream ->
                if (index == 0) appendIf(AppendedAt.WHILE_THE_CALL_IS_STREAMED)
                stream.stream().forEach {}
            }
        } else {
            runner.forEachIndexed { index, _ ->
                if (index == 0) appendIf(AppendedAt.WHILE_THE_CALL_IS_HANDLED)
            }
        }

        val appended = user("In one word.")
        val sent =
            if (at == AppendedAt.BEFORE_THE_RUN)
                listOf("[$QUESTION,$appended]", "[${result("toolu_1")}]")
            else listOf("[$QUESTION]", "[${result("toolu_1")},$appended]")
        assertThat(http.jsonBodies.map { it["messages"] }).isEqualTo(sent.map { json(it) })
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(thread.messages()).hasSize(5)
    }

    /** When the caller of a runner over a thread appends a message to the thread. */
    internal enum class AppendedAt {
        BEFORE_THE_RUN,
        WHILE_THE_CALL_IS_HANDLED,
        WHILE_THE_CALL_IS_STREAMED,
        IN_THE_TOOL,
    }

    @Test
    fun `a runner over a thread does not send the unsupported request behavior of the thread`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                toolUse("msg_b", "toolu_1"),
                message("msg_c", "Sunny"),
            )
        val thread =
            BetaMessageThreadTest.thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED) {
                addTool(weather)
            }
        thread.send("Hello")
        thread.append(userParam("What is the weather in Paris?"))

        thread.toolRunner().toList()

        assertThat(http.requests).hasSize(3)
        assertThat(http.jsonBodies.flatMap { it.fieldNames().asSequence() }).noneMatch {
            it.contains("unsupported", ignoreCase = true)
        }
    }

    @Test
    fun `a runner over a thread whose stream ended early continues from the turn it sends again`() {
        val http =
            FakeHttpClient(
                sse(start("msg_a"), *textBlock(0, "Sun")),
                toolUse("msg_b", "toolu_1"),
                message("msg_c", "Sunny"),
            )
        val thread = thread(http)
        thread.sendStreaming("What is the weather in Paris?").use { it.stream().forEach {} }

        thread.toolRunner().toList()

        val (_, again, next) = http.jsonBodies
        assertThat(again["thread"]).isEqualTo(CREATE)
        assertThat(again["messages"]).isEqualTo(json("[$QUESTION]"))
        assertThat(next["thread"]).isEqualTo(continues("msg_b"))
        assertThat(next["messages"]).isEqualTo(json("[${result("toolu_1")}]"))
    }

    @Test
    fun `a runner over a fork runs the tools of the original`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))
        val fork = thread(http).fork()
        fork.append(userParam("What is the weather in Paris?"))

        fork.toolRunner().toList()

        assertThat(http.jsonBodies[0]["tools"]).hasSize(1)
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${result("toolu_1")}]"""))
    }

    @Test
    fun `a request that a thread cannot serve is sent again without one, and so is the rest`() {
        val http =
            FakeHttpClient(
                400 to threadError("thread_unsupported_request"),
                toolUse("msg_a", "toolu_1"),
                message("msg_b", "Sunny"),
            )

        runner(http).toList()

        val bodies = http.jsonBodies
        assertThat(bodies.map { it.has("thread") }).containsExactly(true, false, false)
        assertThat(bodies[2]["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
        assertThat(betas(http, 2)).isEmpty()
        assertThat(http.requests.map { it.headers.values(STAINLESS_HELPER_HEADER) })
            .containsOnly(listOf("BetaToolRunner, message-thread"))
    }

    @Test
    fun `a request that a thread cannot serve is thrown on a thread that was created to throw`() {
        val http = FakeHttpClient(400 to threadError("thread_unsupported_request"))
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))

        assertThrows<BadRequestException> { thread.toolRunner().toList() }

        assertThat(http.requests).hasSize(1)
    }

    @Test
    fun `a thread that is gone in the middle of the run is replaced by a new one`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                404 to threadError("thread_not_found"),
                message("msg_b", "Sunny"),
            )

        val replies = runner(http).toList()

        assertThat(replies.map { it.id() }).containsExactly("msg_a", "msg_b")
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["tools"]).hasSize(1)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
    }

    @Test
    fun `a thread that is gone again right after a replay ends the threading of the run`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                404 to threadError("thread_not_found"),
                toolUse("msg_b", "toolu_2"),
                404 to threadError("thread_not_found"),
                toolUse("msg_c", "toolu_3"),
                message("msg_d", "Sunny"),
            )

        val replies = runner(http).toList()

        assertThat(replies.map { it.id() }).containsExactly("msg_a", "msg_b", "msg_c", "msg_d")
        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "create", "continue", null, null)
        assertThat(http.jsonBodies[4]["messages"])
            .isEqualTo(
                json(
                    """[$QUESTION,${call("toolu_1")},${result("toolu_1")},${call("toolu_2")},""" +
                        """${result("toolu_2")}]"""
                )
            )
        assertThat(http.jsonBodies[5]["messages"]).hasSize(7)
    }

    @Test
    fun `a request of the runner that fails leaves the messages of the run on the thread`() {
        val http =
            FakeHttpClient(
                500 to errorBody("api_error"),
                toolUse("msg_a", "toolu_1"),
                500 to errorBody("api_error"),
                message("msg_b", "Sunny"),
            )
        val thread = thread(http)

        assertThrows<InternalServerException> {
            thread.toolRunner { it.initialMessageParams(params()) }.toList()
        }
        assertThat(wire(thread.messages())).isEqualTo(json("[$QUESTION]"))
        val runner = thread.toolRunner()
        assertThrows<InternalServerException> { runner.forEach { runner.addTool(lookup) } }
        thread.send()

        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("[$QUESTION]"))
        assertThat(http.jsonBodies[3]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[3]["messages"])
            .isEqualTo(json("[${result("toolu_1")},$LOOKUP_ADDED]"))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a refusal ends the run, and the thread goes on from the turns before it`(
        streaming: Boolean
    ) {
        val http =
            FakeHttpClient(
                if (streaming) streamedToolUse("msg_a", "toolu_1") else toolUse("msg_a", "toolu_1"),
                reply("msg_b", "No", streaming, "refusal"),
                message("msg_c", "Sunny"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))

        val runner = thread.toolRunner()
        val last =
            if (streaming)
                runner.streaming().map { stream ->
                    val accumulator = BetaMessageAccumulator.create()
                    stream.stream().forEach(accumulator::accumulate)
                    accumulator.message()
                }
            else runner.toList()
        assertThat(wire(runner.params().messages()))
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
        thread.send("Again")

        assertThat(last.map { it.id() }).containsExactly("msg_a", "msg_b")
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["tools"]).hasSize(1)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(
                json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")},${user("Again")}]""")
            )
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a tool call from a fallback model is answered on a new thread`(setsNextParams: Boolean) {
        val call = json(toolUse("msg_a", "toolu_1")) as ObjectNode
        call.set<JsonNode>("usage", json(fallbackUsage("claude-opus-4-5")))
        val http =
            FakeHttpClient(call.toString(), toolUse("msg_b", "toolu_2"), message("msg_c", "Sunny"))
        val fallbacks = listOf(BetaFallbackParam.builder().model("claude-opus-4-5").build())
        val runner = runner(http) { fallbacksOfFallbackParams(fallbacks) }

        for (reply in runner) {
            assertThat(runner.params().model().asString()).isEqualTo("claude-sonnet-4-5")
            assertThat(runner.params().fallbacks().get().asFallbackParams()).isEqualTo(fallbacks)
            if (setsNextParams && reply.id() == "msg_a") {
                runner.setNextParams(runner.params())
            }
        }

        val (first, create, next) = http.jsonBodies
        assertThat(create["thread"]).isEqualTo(CREATE)
        assertThat(create["tools"]).isEqualTo(first["tools"])
        assertThat(create["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
        assertThat(next["thread"]).isEqualTo(continues("msg_b"))
        assertThat(next["messages"]).isEqualTo(json("""[${result("toolu_2")}]"""))
        for (request in listOf(first, create, next)) {
            assertThat(request["model"].asText()).isEqualTo("claude-sonnet-4-5")
            assertThat(request["fallbacks"]).isEqualTo(json("""[{"model":"claude-opus-4-5"}]"""))
        }
    }

    @Test
    fun `a paused turn is resumed with no messages`() {
        val http =
            FakeHttpClient(message("msg_a", "Searching", "pause_turn"), message("msg_b", "Sunny"))

        runner(http).toList()

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"]).isEmpty()
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a message appended to the thread at a paused turn is sent when the turn is resumed`(
        streaming: Boolean
    ) {
        val http =
            if (streaming)
                FakeHttpClient(
                    sse(start("msg_a"), *textBlock(0, "Searching"), *stop("pause_turn")),
                    sse(start("msg_b"), *textBlock(0, "Sunny"), *stop()),
                )
            else
                FakeHttpClient(
                    message("msg_a", "Searching", "pause_turn"),
                    message("msg_b", "Sunny"),
                )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()
        val handle = { index: Int -> if (index == 0) thread.append(userParam("In one word.")) }

        if (streaming) {
            runner.streaming().forEachIndexed { index, stream ->
                stream.stream().forEach {}
                handle(index)
            }
        } else {
            runner.forEachIndexed { index, _ -> handle(index) }
        }

        val (_, resume) = http.jsonBodies
        val appended = user("In one word.")
        assertThat(resume["thread"]).isEqualTo(continues("msg_a"))
        assertThat(resume["messages"]).isEqualTo(json("""[$appended]"""))
        assertThat(wire(thread.messages()))
            .isEqualTo(
                json("""[$QUESTION,${assistant("Searching")},$appended,${assistant("Sunny")}]""")
            )
    }

    @Test
    fun `other messages in the params at a paused turn are sent as they are`() {
        val http =
            FakeHttpClient(message("msg_a", "Searching", "pause_turn"), message("msg_b", "Rainy"))
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                thread.append(userParam("In one word."))
                runner.setNextParams(
                    runner.params().toBuilder().messages(listOf(userParam("And in Rome?"))).build()
                )
            }
        }

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("And in Rome?")}]"""))
        assertThat(wire(thread.messages()))
            .isEqualTo(json("""[${user("And in Rome?")},${assistant("Rainy")}]"""))
    }

    @Test
    fun `a compaction before the first turn of a runner over a paused thread holds each message`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Searching", "pause_turn"),
                compaction("msg_b"),
                message("msg_c", "Sunny"),
            )
        val thread = thread(http)
        thread.send("What is the weather in Paris?")
        thread.append(userParam("In one word."))
        val runner = thread.toolRunner()
        runner.compactBeforeNextTurn()

        runner.toList()

        val (_, compaction, next) = http.jsonBodies
        assertThat(compaction.has("compaction")).isTrue
        assertThat(compaction["messages"])
            .isEqualTo(json("""[$QUESTION,${assistant("Searching")},${user("In one word.")}]"""))
        assertThat(next.has("compaction")).isFalse
        assertThat(next["messages"]).isEqualTo(json("""[$SUMMARY]"""))
    }

    @Test
    fun `the container of a reply is sent on later turns`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1", container = "container_1"),
                message("msg_b", "Sunny"),
            )

        runner(http).toList()

        assertThat(http.jsonBodies[0].has("container")).isFalse
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["container"].asText()).isEqualTo("container_1")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a run that stops at its limit leaves the tool results and the container on the thread`(
        setsNextParams: Boolean
    ) {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1", container = "container_1"),
                message("msg_b", "Rainy"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))

        val runner = thread.toolRunner { it.maxIterations(1) }
        for (reply in runner) {
            if (setsNextParams) {
                runner.setNextParams(
                    runner.params().toBuilder().addUserMessage("In one word.").build()
                )
            }
        }
        assertThat(wire(thread.messages()))
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
        thread.send("And in Lyon?")

        assertThat(runs.get()).isEqualTo(1)
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[${result("toolu_1")},${user("And in Lyon?")}]"""))
        assertThat(http.jsonBodies[1]["container"].asText()).isEqualTo("container_1")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a tool turn with a block that this SDK does not know is answered`(threaded: Boolean) {
        val newer = """{"type":"newer_block","note":"kept"}"""
        val call = """{"type":"tool_use","id":"toolu_1","name":"get_weather","input":$INPUT}"""
        val http =
            FakeHttpClient(
                withContent(message("msg_a", "", "tool_use"), "$newer,$call"),
                message("msg_b", "Sunny"),
            )

        runner(http, thread = threaded).toList()

        assertThat(runs.get()).isEqualTo(1)
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(
                if (threaded) json("""[${result("toolu_1")}]""")
                else
                    json(
                        """[$QUESTION,{"content":[$newer,$call],"role":"assistant"},""" +
                            """${result("toolu_1")}]"""
                    )
            )
    }

    @Test
    fun `messages added to the params continue the thread, after the tool results`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))
        val runner = runner(http)

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.setNextParams(
                    runner.params().toBuilder().addUserMessage("In one word.").build()
                )
            }
        }

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[${result("toolu_1")},${user("In one word.")}]"""))
        assertThat(runs.get()).isEqualTo(1)
    }

    @Test
    fun `messages that do not start with the conversation start a new thread`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))
        val runner = runner(http)

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.setNextParams(
                    runner
                        .params()
                        .toBuilder()
                        .messages(listOf(userParam("And in Lyon?"), reply.toParam()))
                        // A new thread takes a new `system`.
                        .system("Be long.")
                        .build()
                )
            }
        }

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[1]["system"].asText()).isEqualTo("Be long.")
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(
                json("""[${user("And in Lyon?")},${call("toolu_1")},${result("toolu_1")}]""")
            )
    }

    @Test
    fun `a runner without a thread sends the messages of the next params as they are`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"), message("msg_b", "Sunny"))
        val runner = runner(http, thread = false)

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.setNextParams(
                    runner.params().toBuilder().addMessage(reply).addUserMessage("Well?").build()
                )
            }
        }

        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${user("Well?")}]"""))
        assertThat(runs.get()).isEqualTo(0)
    }

    @Test
    fun `a system changed on a thread that goes on is refused before a tool runs`() {
        val http = FakeHttpClient(toolUse("msg_a", "toolu_1"))
        val runner = runner(http)

        val refused =
            assertThrows<IllegalStateException> {
                for (reply in runner) {
                    runner.setNextParams(runner.params().toBuilder().system("Be long.").build())
                }
            }

        assertThat(refused).hasMessage(refusal(listOf("system")))
        assertThat(http.requests).hasSize(1)
        assertThat(runs.get()).isEqualTo(0)
    }

    @Test
    fun `a tool that the runner adds or removes continues the thread`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                toolUse("msg_b", "toolu_2"),
                message("msg_c", "Sunny"),
            )
        val runner = runner(http)

        for (reply in runner) {
            when (reply.id()) {
                "msg_a" -> runner.addTool(BetaToolUnion.ofBetaTool(timeTool()))
                "msg_b" -> runner.removeTool("get_time")
            }
        }

        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"]["type"].asText() })
            .containsExactly("create", "continue", "continue")
        assertThat(bodies.drop(1).map { it["messages"][1]["content"][0]["type"].asText() })
            .containsExactly("tool_addition", "tool_removal")
        assertThat(bodies.drop(1)).noneMatch { it.has("tools") }
    }

    @Test
    fun `a tool that is added runs and a tool that is removed does not, on one thread`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                toolUse("msg_b", "toolu_2", name = "lookup"),
                toolUse("msg_c", "toolu_3"),
                message("msg_d", "Sunny"),
            )
        val runner = runner(http)

        val replies = runner.map { reply -> reply.also { changeTools(runner, it) }.id() }

        assertThat(replies).containsExactly("msg_a", "msg_b", "msg_c", "msg_d")
        assertThat(runs.get()).isEqualTo(1)
        assertThat(lookups.get()).isEqualTo(1)
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"] })
            .containsExactly(CREATE, continues("msg_a"), continues("msg_b"), continues("msg_c"))
        assertThat(bodies[0]["tools"].map { it["name"].asText() }).containsExactly("get_weather")
        assertThat(bodies.drop(1)).noneMatch { it.has("tools") }
        assertThat(bodies.map { it["messages"] })
            .containsExactly(
                json("[$QUESTION]"),
                json("[${result("toolu_1")},$LOOKUP_ADDED]"),
                json("[${result("toolu_2", "Found")},$WEATHER_TOOL_REMOVAL]"),
                json("[$WEATHER_NOT_FOUND]"),
            )
    }

    @Test
    fun `a runner over a thread adds and removes tools, and the thread goes on from the run`() {
        val http =
            FakeHttpClient(
                message("msg_0", "Hi"),
                toolUse("msg_a", "toolu_1"),
                toolUse("msg_b", "toolu_2", name = "lookup"),
                toolUse("msg_c", "toolu_3"),
                message("msg_d", "Sunny"),
                message("msg_e", "Bye"),
            )
        val thread = thread(http)
        thread.send("Hello")
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        runner.forEach { changeTools(runner, it) }
        thread.send("Bye")

        assertThat(lookups.get()).isEqualTo(1)
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"] })
            .containsExactly(
                CREATE,
                continues("msg_0"),
                continues("msg_a"),
                continues("msg_b"),
                continues("msg_c"),
                continues("msg_d"),
            )
        assertThat(bodies.drop(1)).noneMatch { it.has("tools") }
        assertThat(bodies.drop(1).map { it["messages"] })
            .containsExactly(
                json("[$QUESTION]"),
                json("[${result("toolu_1")},$LOOKUP_ADDED]"),
                json("[${result("toolu_2", "Found")},$WEATHER_TOOL_REMOVAL]"),
                json("[$WEATHER_NOT_FOUND]"),
                json("[${user("Bye")}]"),
            )
        assertThat(wire(thread.messages()).filter { it["role"].asText() == "system" })
            .containsExactly(json(LOOKUP_ADDED), json(WEATHER_TOOL_REMOVAL))
    }

    @Test
    fun `a compaction that a thread cannot serve costs one request and ends the threading`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                400 to threadError("thread_unsupported_request", """["context_management"]"""),
                compaction("msg_b"),
                message("msg_c", "Sunny"),
            )
        val runner = runner(http)

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.compactBeforeNextTurn()
            }
        }

        val bodies = http.jsonBodies
        assertThat(bodies.map { it.has("compaction") }).containsExactly(false, true, true, false)
        assertThat(bodies.map { it.path("thread").path("type").asText("none") })
            .containsExactly("create", "create", "none", "none")
        assertThat(bodies[2]).isEqualTo(bodies[1].deepCopy().without<ObjectNode>("thread"))
        assertThat(bodies[2]["messages"])
            .isEqualTo(json("""[$QUESTION,${call("toolu_1")},${result("toolu_1")}]"""))
        assertThat(bodies[3]["messages"]).isEqualTo(json("""[$SUMMARY]"""))
    }

    @Test
    fun `a compaction of the last turn leaves the compacted conversation in the params`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Sunny"),
                400 to threadError("thread_unsupported_request"),
                compaction("msg_b"),
            )
        val runner = runner(http)

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.compactBeforeNextTurn()
            }
        }

        assertThat(wire(runner.params().messages())).isEqualTo(json("""[$SUMMARY]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a thread goes on from the summary of a compaction of the last turn`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                if (streaming) sse(start("msg_a"), *textBlock(0, "Sunny"), *stop())
                else message("msg_a", "Sunny"),
                if (streaming)
                    sse(
                        start("msg_b"),
                        blockStart(0, """{"type":"compaction","content":"A summary."}"""),
                        blockStop(0),
                        *stop("compaction"),
                    )
                else compaction("msg_b"),
                message("msg_c", "Bye"),
            )
        val ended = EndedSpans()
        val thread =
            BetaMessageThreadTest.thread(http, configure = tracedBy(ended)) { addTool(weather) }
        thread.update({}, RequestOptions.builder().timeout(Duration.ofSeconds(42)).build())
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        if (streaming) {
            runner.streaming().forEachIndexed { index, stream ->
                stream.stream().forEach {}
                if (index == 0) runner.compactBeforeNextTurn()
            }
        } else {
            runner.forEachIndexed { index, _ -> if (index == 0) runner.compactBeforeNextTurn() }
        }

        assertThat(wire(thread.messages())).isEqualTo(json("""[$SUMMARY]"""))
        assertThat(thread.lastMessage().get().id()).isEqualTo("msg_b")
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(http.requests[1].headers.values(STAINLESS_HELPER_HEADER))
            .containsExactly("BetaToolRunner, message-thread")
        assertThat(http.requestOptions[1].timeout().get().request().seconds).isEqualTo(42L)
        val loop = ended.spans.single { it.name == "anthropic.messages.tool_runner" }
        assertThat(ended.spans.filter { it.name == "anthropic.messages.create" })
            .hasSize(2)
            .allSatisfy { assertThat(it.parentSpanId).isEqualTo(loop.spanId) }

        thread.send("Bye")

        val (_, compacting, next) = http.jsonBodies
        assertThat(compacting.has("compaction")).isTrue
        assertThat(next["thread"]).isEqualTo(CREATE)
        assertThat(next["messages"]).isEqualTo(json("""[$SUMMARY,${user("Bye")}]"""))
        assertThat(next.has("compaction")).isFalse
    }

    @Test
    fun `a thread that a compaction of the last turn unthreaded goes on from the summary`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Sunny"),
                400 to threadError("thread_unsupported_request"),
                compaction("msg_b"),
                message("msg_c", "Bye"),
            )
        val thread =
            BetaMessageThreadTest.thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED) {
                addTool(weather)
            }
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        runner.forEachIndexed { index, _ -> if (index == 0) runner.compactBeforeNextTurn() }
        thread.send("Bye")

        val next = http.jsonBodies[3]
        assertThat(next.has("thread")).isFalse
        assertThat(next["messages"]).isEqualTo(json("""[$SUMMARY,${user("Bye")}]"""))
        assertThat(next.has("compaction")).isFalse
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a message appended to the thread while the runner compacts is sent after the summary`(
        toolChange: Boolean
    ) {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                compaction("msg_b"),
                message("msg_c", "Rainy"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        for (reply in runner) {
            when (reply.id()) {
                "msg_a" -> {
                    if (toolChange) runner.addTool(lookup)
                    runner.compactBeforeNextTurn()
                }
                "msg_b" -> thread.append(userParam("And in Rome?"))
            }
        }

        assertThat(http.jsonBodies[1]["messages"]).hasSize(if (toolChange) 4 else 3)
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$SUMMARY,${user("And in Rome?")}]"""))
        assertThat(wire(thread.messages()))
            .isEqualTo(json("""[$SUMMARY,${user("And in Rome?")},${assistant("Rainy")}]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a message appended while the runner compacts the last turn stays on the thread`(
        streaming: Boolean
    ) {
        val http =
            FakeHttpClient(
                if (streaming) sse(start("msg_a"), *textBlock(0, "Sunny"), *stop())
                else message("msg_a", "Sunny"),
                if (streaming)
                    sse(
                        start("msg_b"),
                        blockStart(0, """{"type":"compaction","content":"A summary."}"""),
                        blockStop(0),
                        *stop("compaction"),
                    )
                else compaction("msg_b"),
                message("msg_c", "Rainy"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()
        val handle = { index: Int ->
            if (index == 0) runner.compactBeforeNextTurn()
            else thread.append(userParam("And in Rome?"))
        }

        if (streaming) {
            runner.streaming().forEachIndexed { index, stream ->
                stream.stream().forEach {}
                handle(index)
            }
        } else {
            runner.forEachIndexed { index, _ -> handle(index) }
        }

        assertThat(http.requests).hasSize(2)
        assertThat(wire(thread.messages()))
            .isEqualTo(json("""[$SUMMARY,${user("And in Rome?")}]"""))

        thread.send()

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$SUMMARY,${user("And in Rome?")}]"""))
    }

    @Test
    fun `a message appended to the thread before the runner compacts the last turn is compacted`() {
        val http = FakeHttpClient(message("msg_a", "Sunny"), compaction("msg_b"))
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.compactBeforeNextTurn()
                thread.append(userParam("And in Rome?"))
            }
        }

        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$QUESTION,${assistant("Sunny")},${user("And in Rome?")}]"""))
        assertThat(wire(thread.messages())).isEqualTo(json("""[$SUMMARY]"""))
    }

    @Test
    fun `a compaction response that is appended to the thread again is kept once`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                compaction("msg_b"),
                message("msg_c", "Sunny"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        for (reply in runner) {
            if (reply.id() == "msg_a") {
                runner.compactBeforeNextTurn()
            }
            thread.append(reply)
        }

        assertThat(http.jsonBodies[2]["messages"]).isEqualTo(json("""[$SUMMARY]"""))
    }

    @ParameterizedTest
    @EnumSource(NoSummary::class)
    @ResourceLock(Resources.SYSTEM_ERR)
    fun `a compaction of the last turn that gives no summary leaves the thread as it was`(
        outcome: NoSummary
    ) {
        val streaming = outcome == NoSummary.STREAM_CUT_OFF
        val http =
            FakeHttpClient(
                if (streaming) sse(start("msg_a"), *textBlock(0, "Sunny"), *stop())
                else message("msg_a", "Sunny"),
                when (outcome) {
                    NoSummary.EMPTY_RESPONSE -> compaction("msg_b", summary = null)
                    NoSummary.FAILED_REQUEST -> 400 to errorBody("invalid_request_error")
                    NoSummary.STREAM_CUT_OFF ->
                        sse(
                            start("msg_b"),
                            blockStart(0, """{"type":"compaction","content":"A summary."}"""),
                            blockStop(0),
                        )
                    NoSummary.CALLER_LEFT -> compaction("msg_b")
                },
                message("msg_c", "Bye"),
            )
        val thread =
            BetaMessageThreadTest.thread(http) {
                addTool(weather)
                stopSequences(listOf("END"))
            }
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()
        val run = {
            if (streaming) {
                runner.streaming().forEachIndexed { index, stream ->
                    stream.stream().forEach {}
                    if (index == 0) runner.compactBeforeNextTurn()
                }
            } else {
                for ((index, _) in runner.withIndex()) {
                    if (index == 0) runner.compactBeforeNextTurn()
                    else if (outcome == NoSummary.CALLER_LEFT) break
                }
            }
        }

        when (outcome) {
            NoSummary.EMPTY_RESPONSE ->
                assertThat(captureStderr(run)).contains("Compaction produced no summary")
            NoSummary.FAILED_REQUEST -> assertThrows<BadRequestException> { run() }
            NoSummary.STREAM_CUT_OFF -> assertThrows<IllegalStateException> { run() }
            NoSummary.CALLER_LEFT -> run()
        }

        assertThat(http.jsonBodies[1].has("compaction")).isTrue
        assertThat(http.jsonBodies[1].has("stop_sequences")).isFalse
        assertThat(wire(thread.messages())).isEqualTo(json("""[$QUESTION,${assistant("Sunny")}]"""))

        thread.send("Bye")

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[2]["messages"]).isEqualTo(json("""[${user("Bye")}]"""))
    }

    /** How a compaction request comes to nothing. */
    internal enum class NoSummary {
        EMPTY_RESPONSE,
        FAILED_REQUEST,
        STREAM_CUT_OFF,
        CALLER_LEFT,
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun `a compaction in the run that gives no summary lets the run continue the thread`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                compaction("msg_b", summary = null),
                message("msg_c", "Sunny"),
            )
        val runner = runner(http)

        captureStderr {
            runner.forEachIndexed { index, _ -> if (index == 0) runner.compactBeforeNextTurn() }
        }

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[2]["messages"]).isEqualTo(json("""[${result("toolu_1")}]"""))
    }

    @Test
    @ResourceLock(Resources.SYSTEM_ERR)
    fun `a message appended to the thread during a compaction that gives no summary is sent`() {
        val http =
            FakeHttpClient(
                toolUse("msg_a", "toolu_1"),
                compaction("msg_b", summary = null),
                message("msg_c", "Rainy"),
            )
        val thread = thread(http)
        thread.append(userParam("What is the weather in Paris?"))
        val runner = thread.toolRunner()

        captureStderr {
            for (reply in runner) {
                when (reply.id()) {
                    "msg_a" -> runner.compactBeforeNextTurn()
                    "msg_b" -> thread.append(userParam("And in Rome?"))
                }
            }
        }

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[${result("toolu_1")},${user("And in Rome?")}]"""))
    }

    @Test
    fun `the streaming runner streams every turn on the thread`() {
        val http =
            FakeHttpClient(
                streamedToolUse("msg_a", "toolu_1"),
                sse(start("msg_b"), *textBlock(0, "Sunny"), *stop()),
            )

        val turns =
            runner(
                    http,
                    requestOptions =
                        RequestOptions.builder().timeout(Duration.ofSeconds(42)).build(),
                    params = { putAdditionalHeader("x-caller", "yes") },
                )
                .streaming()
                .map { it.stream().forEach {} }
                .size

        assertThat(turns).isEqualTo(2)
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["stream"].asBoolean() }).containsExactly(true, true)
        assertThat(bodies[0]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[1]["messages"]).isEqualTo(json("""[${result("toolu_1")}]"""))
        assertThat(http.issued.map { it.closed.get() }).containsExactly(true, true)
        assertThat(http.requests.map { it.headers.values("x-caller") })
            .containsExactly(listOf("yes"), listOf("yes"))
        assertThat(http.requestOptions.map { it.timeout().get().request().seconds })
            .containsExactly(42L, 42L)
        // Each turn hands the API call the accumulator that the runner reads the reply from.
        assertThat(http.requestOptions.map { it.streamAccumulator!!.message().id() })
            .containsExactly("msg_a", "msg_b")
    }

    @ParameterizedTest
    @CsvSource(
        "true, IN_THE_REPLY",
        "true, BEFORE_THE_REPLY",
        "true, CLOSED_BY_THE_CALLER",
        "false, IN_THE_REPLY",
        "false, BEFORE_THE_REPLY",
        "false, CLOSED_BY_THE_CALLER",
    )
    fun `a stream that ends before the whole reply fails the run, on a thread as without one`(
        threaded: Boolean,
        end: StreamEnd,
    ) {
        val http =
            FakeHttpClient(
                when (end) {
                    StreamEnd.IN_THE_REPLY -> sse(start("msg_a"), *textBlock(0, "Sun"))
                    StreamEnd.BEFORE_THE_REPLY -> ""
                    StreamEnd.CLOSED_BY_THE_CALLER ->
                        sse(start("msg_a"), *textBlock(0, "Sun"), *stop())
                },
                message("msg_b", "Sunny"),
            )
        val ended = EndedSpans()
        val traced = tracedBy(ended)
        val thread = BetaMessageThreadTest.thread(http, configure = traced) { addTool(weather) }
        thread.append(userParam("What is the weather in Paris?"))
        val runner =
            if (threaded) thread.toolRunner() else runner(http, thread = false, configure = traced)
        val streams = runner.streaming().iterator()

        if (end == StreamEnd.CLOSED_BY_THE_CALLER) {
            streams.next().use { it.stream().limit(4).forEach {} }
        } else {
            streams.next().stream().forEach {}
        }
        val error = assertThrows<IllegalStateException> { streams.hasNext() }

        assertThat(error).hasMessage("'message_stop' event not yet received.")
        assertThat(http.requests).hasSize(1)
        assertThat(runs).hasValue(0)
        assertThat(ended.spans.single { it.name == "anthropic.messages.tool_runner" }.status)
            .extracting { it.statusCode }
            .isEqualTo(StatusCode.ERROR)
        if (threaded) {
            val kept =
                if (end == StreamEnd.BEFORE_THE_REPLY) QUESTION
                else """$QUESTION,${assistant("Sun")}"""
            assertThat(wire(thread.messages())).isEqualTo(json("[$kept]"))

            thread.send("And in Rome?")

            assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
            assertThat(http.jsonBodies[1]["messages"])
                .isEqualTo(json("""[$kept,${user("And in Rome?")}]"""))
        }
    }

    /** How the stream of a reply ends before its `message_stop` event is read. */
    internal enum class StreamEnd {
        IN_THE_REPLY,
        BEFORE_THE_REPLY,
        CLOSED_BY_THE_CALLER,
    }

    private fun tracedBy(ended: EndedSpans): ClientOptions.Builder.() -> Unit = {
        openTelemetry(
            OpenTelemetryOptions.builder()
                .tracerProvider(SdkTracerProvider.builder().addSpanProcessor(ended).build())
                .build()
        )
    }

    private class EndedSpans : SpanProcessor {
        val spans = CopyOnWriteArrayList<SpanData>()

        override fun onStart(parentContext: Context, span: ReadWriteSpan) {}

        override fun isStartRequired(): Boolean = false

        override fun onEnd(span: ReadableSpan) {
            spans.add(span.toSpanData())
        }

        override fun isEndRequired(): Boolean = true
    }

    private fun params(configure: MessageCreateParams.Builder.() -> Unit = {}) =
        MessageCreateParams.builder()
            .model("claude-sonnet-4-5")
            .maxTokens(1024)
            .system("Be brief.")
            .addTool(weather)
            .addUserMessage("What is the weather in Paris?")
            .apply(configure)
            .build()

    private fun runner(
        http: FakeHttpClient,
        thread: Boolean = true,
        requestOptions: RequestOptions = RequestOptions.none(),
        configure: ClientOptions.Builder.() -> Unit = {},
        params: MessageCreateParams.Builder.() -> Unit = {},
    ): BetaToolRunner =
        client(http, configure)
            .beta()
            .messages()
            .toolRunner(
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(params(params))
                    .maxIterations(5)
                    .thread(thread)
                    .build(),
                requestOptions,
            )

    private fun thread(http: FakeHttpClient): BetaMessageThread =
        BetaMessageThreadTest.thread(http) { addTool(weather) }

    /** Adds [lookup] while the first reply is handled, and removes [weather] during the second. */
    private fun changeTools(runner: BetaToolRunner, reply: BetaMessage) {
        when (reply.id()) {
            "msg_a" -> runner.addTool(lookup)
            "msg_b" -> runner.removeTool(weather)
        }
    }

    private companion object {
        const val INPUT = """{"city":"Paris"}"""

        const val OWN_THREAD_REFUSAL =
            "`thread` cannot be set in the params of a tool runner. The runner sets it for each " +
                "request itself. To run without a message thread, set `thread(false)` on the " +
                "`ToolRunnerCreateParams`."

        val QUESTION = user("What is the weather in Paris?")

        const val SUMMARY =
            """{"content":[{"content":"A summary.","type":"compaction"}],"role":"assistant"}"""

        fun call(id: String): String =
            """{"content":[{"id":"$id","input":$INPUT,"name":"get_weather",""" +
                """"type":"tool_use"}],"role":"assistant"}"""

        fun result(id: String, content: String = "Sunny"): String =
            """{"content":[{"content":"$content","tool_use_id":"$id",""" +
                """"type":"tool_result"}],"role":"user"}"""

        const val LOOKUP_ADDED =
            """{"content":[{"tool":{"definition":{"input_schema":{"type":"object"},""" +
                """"name":"lookup"},"type":"tool_definition"},"type":"tool_addition"}],""" +
                """"role":"system"}"""

        const val WEATHER_NOT_FOUND =
            """{"content":[{"content":"Error: Tool 'get_weather' not found","is_error":true,""" +
                """"tool_use_id":"toolu_3","type":"tool_result"}],"role":"user"}"""

        fun toolUse(
            id: String,
            call: String,
            container: String? = null,
            name: String = "get_weather",
        ): String =
            withContent(
                message(id, "", "tool_use", container),
                """{"type":"tool_use","id":"$call","name":"$name","input":$INPUT}""",
            )

        fun streamedToolUse(id: String, call: String): String =
            sse(
                start(id),
                blockStart(
                    0,
                    """{"type":"tool_use","id":"$call","name":"get_weather","input":{}}""",
                ),
                inputDelta(0, """{\"city\":\"Paris\"}"""),
                blockStop(0),
                *stop("tool_use"),
            )

        fun compaction(id: String, summary: String? = "A summary."): String =
            withContent(
                message(id, "", "compaction"),
                """{"type":"compaction","content":${summary?.let { "\"$it\"" } ?: "null"}}""",
            )

        private fun withContent(message: String, block: String): String =
            (json(message) as ObjectNode).set<JsonNode>("content", json("[$block]")).toString()
    }
}
