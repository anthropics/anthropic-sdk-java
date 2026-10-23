package com.anthropic.helpers

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.Timeout
import com.anthropic.core.http.Headers
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.AnthropicIoException
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.NotFoundException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaFallbackParam
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaMessageThreadParam
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.messages.BetaRequestToolRemovalBlock
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaSystemMessageParam.Content.SystemMessageContentBlock
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaThreadContinueParams
import com.anthropic.models.beta.messages.BetaThreadCreateParams
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.StructuredMessage
import com.anthropic.models.beta.messages.ThreadCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import com.anthropic.models.beta.messages.ThreadSendParams
import com.anthropic.services.blocking.beta.MessageService
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.time.Duration
import java.util.stream.Collectors
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

internal class BetaMessageThreadTest {

    @Test
    fun `the first turn creates a thread and the next one continues it`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread =
            thread(http) {
                system("Be brief.")
                addTool(weatherTool())
            }

        val first = thread.send("Hello")
        val second = thread.send("Again")

        assertThat(first.id()).isEqualTo("msg_a")
        assertThat(second.id()).isEqualTo("msg_b")
        val (create, next) = http.jsonBodies
        assertThat(create["thread"]).isEqualTo(CREATE)
        assertThat(create["messages"]).isEqualTo(json("""[$HELLO]"""))
        assertThat(create["system"]).isEqualTo(json(""""Be brief.""""))
        assertThat(create["tools"]).hasSize(1)
        assertThat(create.has("stream")).isFalse
        assertThat(next["thread"]).isEqualTo(continues("msg_a"))
        assertThat(next["messages"]).isEqualTo(json("""[${user("Again")}]"""))
        assertThat(next.has("system")).isFalse
        assertThat(next.has("tools")).isFalse
        assertThat(next["model"]).isEqualTo(create["model"])
        assertThat(next["max_tokens"]).isEqualTo(create["max_tokens"])
        assertThat(betas(http, 0)).containsExactly(THREADS_BETA)
        assertThat(betas(http, 1)).containsExactly(THREADS_BETA)
        assertThat(thread.previousMessageId()).hasValue("msg_b")
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_b")
        assertThat(wire(thread.messages()))
            .isEqualTo(
                json("""[$HELLO,${assistant("Hi")},${user("Again")},${assistant("Hi again")}]""")
            )
    }

    @Test
    fun `a send with nothing staged goes out and the API answers`() {
        val http =
            FakeHttpClient(
                400 to error("invalid_request_error"),
                message("msg_a", "Hi"),
                message("msg_b", "More"),
                sse(start("msg_c"), *textBlock(0, "Even more"), *stop()),
                message("msg_d", "Forked"),
            )
        val thread = thread(http)

        assertThrows<BadRequestException> { thread.send() }
        thread.send("Hello")
        thread.send()
        thread.sendStreaming().use { it.stream().forEach {} }
        thread.fork().send()

        val bodies = http.jsonBodies
        assertThat(bodies[0]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[0]["messages"]).isEqualTo(json("[]"))
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("[]"))
        assertThat(bodies[3]["thread"]).isEqualTo(continues("msg_b"))
        assertThat(bodies[3]["messages"]).isEqualTo(json("[]"))
        // A thread with no reply to continue from sends the conversation that it holds.
        assertThat(bodies[4]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[4]["messages"]).isEqualTo(wire(thread.messages()))
    }

    @Test
    fun `staged messages go out unmerged in one request, after the seed`() {
        val http = FakeHttpClient(message("msg_a", "Hi"))
        val thread = thread(http) { addUserMessage("Hello") }

        thread.append(userParam("One"))
        thread.append(listOf(userParam("Two")))
        thread.send(listOf(userParam("Three"), userParam("Four")))

        assertThat(http.jsonBodies.single()["messages"])
            .isEqualTo(
                json("""[$HELLO,${user("One")},${user("Two")},${user("Three")},${user("Four")}]""")
            )
    }

    @Test
    fun `staging the reply of the last turn is a no-op`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = thread(http)

        val reply = thread.send("Hello")
        thread.append(reply.toParam())
        thread.append(
            BetaMessageParam.ofAssistantOfContentBlockParams(reply.content().map { it.toParam() })
        )
        thread.send("Again")

        assertThat(thread.messages()).hasSize(4)
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
    }

    @Test
    fun `a reply object is taken as a message, and the reply of the last turn adds nothing`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
                sse(start("msg_c"), *textBlock(0, "Bye"), *stop()),
                message("msg_d", "Hi"),
                sse(start("msg_e"), *textBlock(0, " there"), *stop()),
            )
        val thread = thread(http)
        val first = thread.send("Hello")

        thread.append(first)
        val second = thread.send(first)
        thread.sendStreaming(second).use { it.stream().forEach {} }
        val seeded = thread(http)
        seeded.append(userParam("Hello"))
        seeded.append(first)
        seeded.send("Again")
        val prefilled = thread(http) { addUserMessage("Hello") }
        prefilled.sendStreaming(first).use { it.stream().forEach {} }

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[1]["messages"]).isEqualTo(json("[]"))
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_b"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("[]"))
        assertThat(thread.messages()).hasSize(4)
        assertThat(bodies[3]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
        assertThat(bodies[4]["messages"]).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
    }

    @Test
    fun `each kind of message resolves to its overload from Java`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi"),
                message("msg_c", "Hi"),
                message("msg_d", "Hi"),
                message("msg_e", "Hi"),
            )

        MessageThreadJavaCalls.sendEachKind(thread(http), userParam("Param"))

        assertThat(http.jsonBodies.map { it["messages"] })
            .containsExactly(
                json("""[${user("Text")}]"""),
                json("[]"),
                json("""[${user("Param")}]"""),
                json("""[${user("Param")}]"""),
                json("""[${user("Appended")},${user("Param")},${user("Modifier")}]"""),
            )
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a turn that fails before its reply starts takes its messages back`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                400 to error("invalid_request_error"),
                reply("msg_b", "Hi again", streaming),
                529 to error("overloaded_error"),
                Failure(AnthropicIoException("Connection reset")),
                reply("msg_c", "Bye", streaming),
            )
        val thread = thread(http)
        thread.turn(streaming, "Hello")

        assertThrows<BadRequestException> { thread.turn(streaming, "Bad") }
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.turn(streaming, "Good")
        assertThrows<AnthropicServiceException> { thread.turn(streaming, "Again") }
        assertThrows<AnthropicIoException> { thread.turn(streaming, "Again") }
        thread.turn(streaming, "Again")

        val bodies = http.jsonBodies
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("""[${user("Good")}]"""))
        assertThat(bodies[4]).isEqualTo(bodies[3])
        assertThat(bodies[5]).isEqualTo(bodies[3])
        assertThat(bodies[5]["thread"]).isEqualTo(continues("msg_b"))
        assertThat(bodies[5]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a turn that fails leaves the messages that were appended before it`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                500 to error("api_error"),
                400 to error("invalid_request_error"),
                reply("msg_b", "Hi again", streaming),
            )
        val thread = thread(http)
        thread.turn(streaming, "Hello")

        thread.append(userParam("Appended"))
        assertThrows<AnthropicServiceException> { thread.turn(streaming, "Sent") }
        assertThrows<BadRequestException> { thread.turn(streaming) }
        thread.turn(streaming)

        val bodies = http.jsonBodies
        assertThat(bodies[1]["messages"])
            .isEqualTo(json("""[${user("Appended")},${user("Sent")}]"""))
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("""[${user("Appended")}]"""))
        assertThat(bodies[3]).isEqualTo(bodies[2])
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a refused turn is not kept, and the next turn starts a new thread`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                reply("msg_b", "No", streaming, "refusal"),
                reply("msg_c", "Hi again", streaming),
                reply("msg_d", "Bye", streaming),
            )
        val thread = pinned(http)
        thread.turn(streaming, "Hello")

        thread.turn(streaming, "Refused")
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.turn(streaming, "Again")
        thread.turn(streaming, "Bye")

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
        assertThat(bodies[2]["system"]).isEqualTo(bodies[0]["system"])
        assertThat(bodies[2]["tools"]).isEqualTo(bodies[0]["tools"])
        assertThat(bodies[3]["thread"]).isEqualTo(continues("msg_c"))
    }

    @Test
    fun `a refusal whose stream ends before its last event is not kept`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "No"), delta("refusal")),
                message("msg_c", "Hi again"),
            )
        val thread = thread(http)
        thread.send("Hello")

        thread.sendStreaming("Refused").use { it.stream().forEach {} }
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("Again")

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
    }

    @Test
    fun `a refusal is returned as it came`() {
        val http =
            FakeHttpClient(
                message("msg_a", "No", "refusal"),
                sse(start("msg_b"), *textBlock(0, "No"), *stop("refusal")),
            )
        val thread = thread(http)

        val reply = thread.send("Hello")
        val events = thread.sendStreaming("Hello").use { it.stream().collect(Collectors.toList()) }

        assertThat(reply)
            .isEqualTo(MAPPER.readValue(message("msg_a", "No", "refusal"), reply.javaClass))
        assertThat(events).hasSize(6)
        assertThat(events[4].asMessageDelta().delta().stopReason()).hasValue(BetaStopReason.REFUSAL)
        assertThat(events.last().isMessageStop()).isTrue
    }

    @Test
    fun `a refused first turn leaves the messages of creation, and none of its own fields`() {
        val http =
            FakeHttpClient(message("msg_a", "No", "refusal", "container_1"), message("msg_b", "Hi"))
        val thread = thread(http) { addUserMessage("Hello") }

        thread.send { it.addUserMessage("Refused").system("Be brief.").addTool(weatherTool()) }
        assertThat(thread.lastMessage()).isEmpty
        thread.send("Again")

        val (refused, next) = http.jsonBodies
        assertThat(refused["messages"]).isEqualTo(json("""[$HELLO,${user("Refused")}]"""))
        assertThat(refused.has("system")).isTrue
        assertThat(next["thread"]).isEqualTo(CREATE)
        assertThat(next["messages"]).isEqualTo(json("""[$HELLO,${user("Again")}]"""))
        assertThat(next.has("system")).isFalse
        assertThat(next.has("tools")).isFalse
        assertThat(next.has("container")).isFalse
    }

    @Test
    fun `a refused turn leaves the messages that were appended before it`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "No", "refusal"),
                message("msg_c", "Hi again"),
            )
        val thread = thread(http)
        thread.send("Hello")

        thread.append(userParam("Appended"))
        thread.send("Refused")
        thread.send("Again")

        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Appended")},${user("Again")}]"""))
    }

    @Test
    fun `a refused turn is not kept on a thread that goes on without a thread`() {
        val http =
            FakeHttpClient(
                400 to threadError("thread_unsupported_request"),
                message("msg_a", "Hi"),
                message("msg_b", "No", "refusal"),
                message("msg_c", "Hi again"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        thread.send("Hello")

        thread.send("Refused")
        thread.send("Again")

        assertThat(http.jsonBodies[3].has("thread")).isFalse
        assertThat(http.jsonBodies[3]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
    }

    @Test
    fun `a replay sends the recorded messages without their cache breakpoints`() {
        val http =
            FakeHttpClient(
                (1..5).map { message("msg_$it", "Hi") } +
                    (404 to threadError("thread_not_found")) +
                    message("msg_6", "Hi")
            )
        val thread = thread(http)
        val sent = (1..6).map { marked("Turn $it") }
        sent.take(5).forEach { thread.send(it) }

        thread.send(sent[5])

        val bodies = http.jsonBodies
        assertThat(bodies[5]["thread"]).isEqualTo(continues("msg_5"))
        assertThat(bodies[5]["messages"]).isEqualTo(wire(listOf(sent[5])))
        assertThat(bodies[6]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[6]["messages"]).hasSize(11)
        assertThat(bodies[6]["messages"][0]).isEqualTo(json(unmarked("Turn 1")))
        assertThat(bodies[6]["messages"].last()).isEqualTo(wire(sent).last())
        assertThat(bodies[6]["messages"].findValues("cache_control")).hasSize(1)
        // The conversation keeps them.
        assertThat(thread.messages().filter { it.isUser() }).isEqualTo(sent)
        assertThat(wire(sent).findValues("cache_control")).hasSize(6)
    }

    @Test
    fun `the first request sends the cache breakpoints of its messages as they are`() {
        val http = FakeHttpClient(message("msg_a", "Hi"))
        val seed = listOf(marked("One"), BetaMessageParam.ofAssistant("Two"), marked("Three"))
        val thread = thread(http) { messages(seed) }

        thread.append(marked("Four"))
        thread.send(marked("Five"))

        assertThat(http.jsonBodies.single()["messages"])
            .isEqualTo(wire(seed + marked("Four") + marked("Five")))
    }

    @Test
    fun `a turn that is sent again after its stream ended early keeps its cache breakpoint`() {
        val http =
            FakeHttpClient(message("msg_a", "Hi"), sse(start("msg_b")), message("msg_c", "Again"))
        val thread = thread(http)
        thread.send(marked("Hello"))
        thread.sendStreaming(marked("Again")).use { it.stream().forEach {} }

        thread.send()

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(
                json("""[${unmarked("Hello")},${assistant("Hi")},${wire(marked("Again"))}]""")
            )
    }

    @Test
    fun `a turn whose stream ended before any content is sent as given with the next one`() {
        val http =
            FakeHttpClient(message("msg_a", "Hi"), sse(start("msg_b")), message("msg_c", "Again"))
        val thread = thread(http)
        val cutOff =
            BetaMessageParam.ofUserOfContentBlockParams(
                listOf("One", "Two").flatMap {
                    marked(it).asUser().content().asContentBlockParams()
                }
            )
        thread.send(marked("Hello"))
        thread.sendStreaming(cutOff).use { it.stream().forEach {} }

        thread.send(marked("Again"))

        assertThat(wire(cutOff).findValues("cache_control")).hasSize(2)
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(
                json(
                    """[${unmarked("Hello")},${assistant("Hi")},${wire(cutOff)},""" +
                        """${wire(marked("Again"))}]"""
                )
            )
    }

    @Test
    fun `a replay drops the cache breakpoints of nested blocks, and leaves free-form values`() {
        val marker = """"cache_control":{"type":"ephemeral"}"""
        val call = """{"type":"tool_use","id":"toolu_1","name":"get_weather","input":{$marker}}"""
        val definition =
            """{"name":"get_time","input_examples":[{"cache_control":"no-store"}],""" +
                """"input_schema":{"type":"object","properties":{"cache_control":{}}}MARKER}"""
        val addition =
            """{"type":"tool_addition","tool":{"type":"tool_definition",""" +
                """"definition":$definition}MARKER}"""
        val changes = addition.replace("MARKER", ",$marker")
        val reference = """{"type":"tool_reference","tool_name":"get_weather"MARKER}"""
        val search =
            """{"type":"server_tool_use","id":"srvtoolu_1","name":"tool_search_tool_regex",""" +
                """"input":{"pattern":"weather"}},{"type":"tool_search_tool_result",""" +
                """"tool_use_id":"srvtoolu_1","content":{"tool_references":[$reference],""" +
                """"type":"tool_search_tool_search_result"}}"""
        val summary =
            """{"role":"assistant","content":[{"type":"compaction","content":"A summary.",""" +
                """"tool_changes":[$changes]MARKER},$search]}"""
        val text = """{"type":"text","text":"Sunny"MARKER}"""
        val document = """{"type":"document","source":{"type":"content","content":[$text]}MARKER}"""
        val result =
            """{"role":"user","content":[{"type":"tool_result","tool_use_id":"toolu_1",""" +
                """"content":[$text,$document]MARKER}]}"""
        val added = """{"role":"system","content":[$addition]}"""
        val given = listOf(summary, result, added).map { it.replace("MARKER", ",$marker") }
        val (givenSummary, givenResult, givenAdded) =
            given.map { MAPPER.readValue(it, BetaMessageParam::class.java) }
        val toolUse = (json(message("msg_a", "", "tool_use")) as ObjectNode)
        toolUse.set<JsonNode>("content", json("[$call]"))
        val http =
            FakeHttpClient(toolUse.toString(), message("msg_b", "Sunny"), message("msg_c", "Bye"))
        val thread = thread(http) { addTool(weatherTool()).messages(listOf(givenSummary)) }
        thread.send("Hello")
        thread.send(listOf(givenResult, givenAdded))

        thread.fork().send("Bye")

        val (first, second, replay) = http.jsonBodies.map { it["messages"] }
        assertThat(first + second)
            .isEqualTo(json("[${given[0]},$HELLO,${given[1]},${given[2]}]").toList())
        val expected =
            """[$summary,$HELLO,{"role":"assistant","content":[$call]},$result,$added,""" +
                """${assistant("Sunny")},${user("Bye")}]"""
        assertThat(replay).isEqualTo(json(expected.replace("MARKER", "")))
    }

    @Test
    fun `a replay leaves the cache breakpoints outside the messages`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                404 to threadError("thread_not_found"),
                message("msg_b", "Hi again"),
            )
        val thread =
            thread(http) {
                cacheControl(EPHEMERAL)
                systemOfBetaTextBlockParams(
                    listOf(
                        BetaTextBlockParam.builder()
                            .text("Be brief.")
                            .cacheControl(EPHEMERAL)
                            .build()
                    )
                )
                addTool(weatherTool().toBuilder().cacheControl(EPHEMERAL).build())
            }
        thread.send(marked("Hello"))

        thread.send("Again")

        val (create, _, replay) = http.jsonBodies
        assertThat(replay["messages"].findValues("cache_control")).isEmpty()
        for (field in listOf("cache_control", "system", "tools")) {
            assertThat(replay[field].findValues("type")).isNotEmpty
            assertThat(replay[field]).isEqualTo(create[field])
        }
        assertThat(replay.findValues("cache_control")).hasSize(3)
    }

    @Test
    fun `a request without a thread sends the recorded messages without cache breakpoints`() {
        val http =
            FakeHttpClient(
                400 to threadError("thread_unsupported_request"),
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)

        thread.send(marked("Hello"))
        thread.send(marked("Again"))

        val bodies = http.jsonBodies
        assertThat(bodies[1]["messages"]).isEqualTo(wire(listOf(marked("Hello"))))
        assertThat(bodies[2].has("thread")).isFalse
        assertThat(bodies[2]["messages"])
            .isEqualTo(
                json("""[${unmarked("Hello")},${assistant("Hi")},${wire(marked("Again"))}]""")
            )
    }

    @Test
    fun `the first request of a fork sends the recorded messages without cache breakpoints`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = thread(http)
        thread.send(marked("Hello"))
        thread.append(marked("Appended"))

        thread.fork().send(marked("Again"))

        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(
                json(
                    """[${unmarked("Hello")},${assistant("Hi")},${wire(marked("Appended"))},""" +
                        """${wire(marked("Again"))}]"""
                )
            )
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a reply from a fallback model starts a new thread and changes no field`(
        streaming: Boolean
    ) {
        val fallback = fallbackUsage("claude-opus-4-5")
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming, usage = fallback),
                reply("msg_b", "Hi again", streaming, usage = fallback),
                reply("msg_c", "More", streaming),
                reply("msg_d", "Bye", streaming),
            )
        val thread = withFallbacks(http)
        val given = thread.params()

        thread.turn(streaming, "Hello")
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        assertThat(thread.params().toBuilder().messages(emptyList()).build()).isEqualTo(given)
        listOf("Again", "More", "Bye").forEach { thread.turn(streaming, it) }

        val history =
            listOf(HELLO, assistant("Hi"), user("Again"), assistant("Hi again"), user("More"))
        assertThat(http.jsonBodies.map { it["thread"] })
            .containsExactly(CREATE, CREATE, CREATE, continues("msg_c"))
        assertThat(http.jsonBodies.map { it["messages"] })
            .containsExactly(
                json("[$HELLO]"),
                json("${history.take(3)}"),
                json("$history"),
                json("[${user("Bye")}]"),
            )
        val (first, second, third, fourth) =
            http.jsonBodies.map { it.remove(listOf("thread", "messages")) }
        assertThat(first["model"].asText()).isEqualTo("claude-sonnet-4-5")
        assertThat(first["fallbacks"]).isEqualTo(json("""[{"model":"claude-opus-4-5"}]"""))
        assertThat(first["system"].asText()).isEqualTo("Be brief.")
        assertThat(listOf(second, third)).containsOnly(first)
        assertThat(fourth).isEqualTo(first.remove(listOf("system")))
        assertThat((1..3).map { betas(http, it) }).containsOnly(betas(http, 0))
    }

    @Test
    fun `a refusal from a fallback model is not recorded`() {
        val http =
            FakeHttpClient(
                message("msg_a", "No", "refusal", usage = fallbackUsage("claude-opus-4-5")),
                message("msg_b", "Hi"),
            )
        val thread = withFallbacks(http)

        thread.send("Refused")
        thread.send("Hello")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("[$HELLO]"))
        assertThat(http.jsonBodies[1]["model"]).isEqualTo(http.jsonBodies[0]["model"])
        assertThat(http.jsonBodies[1]["fallbacks"]).isEqualTo(http.jsonBodies[0]["fallbacks"])
    }

    @Test
    fun `a reply from the model that was asked continues the thread`() {
        val usage = fallbackUsage("claude-sonnet-4-5").replace("fallback_message", "message")
        val http = FakeHttpClient(message("msg_a", "Hi", usage = usage), message("msg_b", "More"))
        val thread = withFallbacks(http)

        thread.send("Hello")
        thread.send("Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["model"]).isEqualTo(http.jsonBodies[0]["model"])
        assertThat(http.jsonBodies[1]["fallbacks"]).isEqualTo(http.jsonBodies[0]["fallbacks"])
    }

    @Test
    fun `a reply from a fallback model changes nothing on a thread that goes on without one`() {
        val http =
            FakeHttpClient(
                400 to threadError("thread_unsupported_request"),
                message("msg_a", "Hi", usage = fallbackUsage("claude-opus-4-5")),
                message("msg_b", "More"),
            )
        val thread = withFallbacks(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)

        thread.send("Hello")
        thread.send("Again")

        assertThat(http.jsonBodies[2].has("thread")).isFalse
        assertThat(http.jsonBodies[2]["model"]).isEqualTo(http.jsonBodies[0]["model"])
        assertThat(http.jsonBodies[2]["fallbacks"]).isEqualTo(http.jsonBodies[0]["fallbacks"])
        assertThat(thread.params().model().asString()).isEqualTo("claude-sonnet-4-5")
    }

    @Test
    fun `a replay code on a create is thrown after one request`() {
        val http = FakeHttpClient(404 to threadError("thread_not_found"))
        val thread = thread(http)

        assertThrows<NotFoundException> { thread.send("Hello") }

        assertThat(http.requests).hasSize(1)
        assertThat(thread.messages()).isEmpty()
    }

    @Test
    fun `an error is recovered by its code and never by its message`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                404 to error("not_found_error", "thread_not_found: no such thread"),
                400 to error("invalid_request_error", "thread_unsupported_request"),
            )
        val thread =
            thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED) { system("Be brief.") }
        thread.send("Hello")

        assertThrows<NotFoundException> { thread.send("Again") }
        assertThrows<BadRequestException> { thread.send() }

        assertThat(http.requests).hasSize(3)
        assertThat(thread.previousMessageId()).hasValue("msg_a")
    }

    @Test
    fun `an unsupported request is thrown by default`() {
        val http =
            FakeHttpClient(400 to threadError("thread_unsupported_request"), message("msg_a", "Hi"))
        val thread = thread(http)

        assertThrows<BadRequestException> { thread.send("Hello") }
        thread.send("Hello")

        assertThat(http.jsonBodies[1]).isEqualTo(http.jsonBodies[0])
    }

    @Test
    fun `a replay that is rejected as unsupported goes on without a thread in the same call`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                404 to threadError("thread_not_found"),
                400 to threadError("thread_unsupported_request"),
                message("msg_b", "Hi again"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        thread.send("Hello")

        thread.send("Again")

        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "create", null)
    }

    @ParameterizedTest
    @EnumSource(UnsupportedRequestBehavior::class)
    fun `a thread that is gone again right after a replay stops threading only when asked to`(
        behavior: UnsupportedRequestBehavior
    ) {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                404 to threadError("thread_not_found"),
                message("msg_b", "Hi again"),
                404 to threadError("thread_not_found"),
                message("msg_c", "Bye"),
                message("msg_d", "Gone"),
            )
        val thread = thread(http, behavior) { system("Be brief.") }
        thread.send("Hello")
        thread.send("Again")

        thread.send("Bye")
        thread.send("Gone")

        val bodies = http.jsonBodies
        val after =
            when (behavior) {
                UnsupportedRequestBehavior.ERROR -> listOf("create", "continue")
                UnsupportedRequestBehavior.CONTINUE_UNTHREADED -> listOf(null, null)
            }
        assertThat(bodies.map { it["thread"]?.get("type")?.asText() })
            .isEqualTo(listOf("create", "continue", "create", "continue") + after)
        assertThat(bodies[4]["messages"])
            .isEqualTo(
                json(
                    """[$HELLO,${assistant("Hi")},${user("Again")},${assistant("Hi again")},""" +
                        """${user("Bye")}]"""
                )
            )
        assertThat(bodies[4]["system"]).isEqualTo(bodies[0]["system"])
    }

    @Test
    fun `a turn that continues the thread after a replay lets the next replay thread again`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                404 to threadError("thread_not_found"),
                message("msg_b", "Hi again"),
                message("msg_c", "More"),
                404 to threadError("thread_not_found"),
                message("msg_d", "Bye"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        listOf("Hello", "Again", "More").forEach { thread.send(it) }

        thread.send("Bye")

        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "create", "continue", "continue", "create")
        assertThat(thread.previousMessageId()).hasValue("msg_d")
    }

    @ParameterizedTest
    @ValueSource(strings = ["thread_already_continued", "thread_continue_in_flight"])
    fun `only a thread that is not found counts towards the end of threading`(code: String) {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                409 to threadError(code),
                message("msg_b", "Hi again"),
                404 to threadError("thread_not_found"),
                message("msg_c", "Bye"),
                409 to threadError(code),
                message("msg_d", "Gone"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        thread.send("Hello")

        listOf("Again", "Bye", "Gone").forEach { thread.send(it) }

        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly(
                "create",
                "continue",
                "create",
                "continue",
                "create",
                "continue",
                "create",
            )
    }

    @Test
    fun `a changed system or tools is refused before any request, and takes the turn back`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = pinned(http)
        thread.send("Hello")

        thread.update { it.system("Be long.") }
        val system = assertThrows<IllegalStateException> { thread.send("Again") }
        thread.update { it.system("Be brief.") }
        val tools =
            assertThrows<IllegalStateException> { thread.sendStreaming { it.addTool(timeTool()) } }
        val both =
            assertThrows<IllegalStateException> {
                thread.send { it.system("Be long.").addTool(timeTool()) }
            }

        assertThat(system).hasMessage(refusal(listOf("system")))
        assertThat(tools).hasMessage(refusal(listOf("tools")))
        assertThat(both).hasMessage(refusal(listOf("system", "tools")))
        assertThat(http.requests).hasSize(1)
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("More")
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("More")}]"""))
    }

    @Test
    fun `a system message that removes a tool goes out on the turn that continues the thread`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = pinned(http)
        thread.send("Hello")

        thread.append(weatherToolRemoval())
        thread.send("Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$WEATHER_TOOL_REMOVAL,${user("Again")}]"""))
        assertThat(http.jsonBodies[1].has("tools")).isFalse
        assertThat(wire(thread.messages())[2]).isEqualTo(json(WEATHER_TOOL_REMOVAL))
    }

    @Test
    fun `a system and tools equal to those of the thread continue it`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = pinned(http)
        thread.send("Hello")

        thread.update { it.system("Be brief.").tools(emptyList()).addTool(weatherTool()) }
        thread.send("Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1].has("system")).isFalse
        assertThat(http.jsonBodies[1].has("tools")).isFalse
    }

    @Test
    fun `a thread keeps the system that the turn that created it gave`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = pinned(http)

        thread.send { it.addUserMessage("Hello").system("Be long.") }
        thread.send("Again")

        assertThat(http.jsonBodies[0]["system"].asText()).isEqualTo("Be long.")
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(thread.params().initialMessageParams.system().get().asString())
            .isEqualTo("Be long.")
    }

    @Test
    fun `a fork takes a changed system and tools`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val fork = pinned(http).also { it.send("Hello") }.fork()

        fork.update { it.system("Be long.").addTool(timeTool()) }
        fork.send("Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[1]["system"].asText()).isEqualTo("Be long.")
        assertThat(http.jsonBodies[1]["tools"]).hasSize(2)
    }

    @Test
    fun `a field of one turn beats an update, which beats creation`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
                message("msg_c", "Bye"),
            )
        val thread = thread(http) { putAdditionalHeader("x-thread", "thread") }

        thread.update {
            it.maxTokens(2048)
            it.putAdditionalHeader("x-update", "update")
        }
        assertThat(http.requests).isEmpty()
        thread.send {
            it.addUserMessage("Hello").maxTokens(512).putAdditionalHeader("x-turn", "turn")
        }
        thread.send("Again")

        val bodies = http.jsonBodies
        assertThat(bodies.map { it["max_tokens"].asInt() }).containsExactly(512, 2048)
        assertThat(http.requests[0].headers.values("x-thread")).containsExactly("thread")
        assertThat(http.requests[0].headers.values("x-update")).containsExactly("update")
        assertThat(http.requests[0].headers.values("x-turn")).containsExactly("turn")
        assertThat(http.requests[1].headers.values("x-thread")).containsExactly("thread")
        assertThat(http.requests[1].headers.values("x-update")).containsExactly("update")
        assertThat(http.requests[1].headers.values("x-turn")).isEmpty()
        assertThat(thread.params().initialMessageParams.maxTokens()).isEqualTo(2048)
    }

    @Test
    fun `the request options of an update lie over those of the thread on later turns`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
                sse(start("msg_c"), *textBlock(0, "Bye"), *stop()),
                message("msg_d", "Forked"),
            )
        val fallbackState = BetaFallbackState.create()
        val thread =
            client(http)
                .beta()
                .messages()
                .thread(
                    ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build(),
                    RequestOptions.builder()
                        .timeout(Duration.ofSeconds(5))
                        .fallbackState(fallbackState)
                        .build(),
                )
        thread.send("Hello")

        thread.update(
            { it.maxTokens(2048) },
            RequestOptions.builder().timeout(Duration.ofSeconds(7)).build(),
        )
        assertThrows<IllegalStateException> {
            thread.update(
                { throw IllegalStateException("No.") },
                RequestOptions.builder().timeout(Duration.ofSeconds(9)).build(),
            )
        }
        thread.send("Again")
        thread.sendStreaming("Bye").use { it.stream().forEach {} }
        thread.fork().send("On the fork")

        assertThat(http.jsonBodies.map { it["max_tokens"].asInt() })
            .containsExactly(1024, 2048, 2048, 2048)
        assertThat(http.requestOptions.map { it.timeout().get().request().seconds })
            .containsExactly(5L, 7L, 7L, 7L)
        assertThat(http.requestOptions.map { it.fallbackState().get() })
            .containsOnly(fallbackState)
            .hasSize(4)
    }

    @Test
    fun `the request options of a turn lie over those of the thread for that turn only`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "Hi again"), *stop()),
                message("msg_c", "Bye"),
            )
        val fallbackState = BetaFallbackState.create()
        val thread =
            client(http)
                .beta()
                .messages()
                .thread(
                    ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build(),
                    RequestOptions.builder()
                        .timeout(Duration.ofSeconds(5))
                        .fallbackState(fallbackState)
                        .build(),
                )
        val turnOptions = RequestOptions.builder().timeout(Duration.ofSeconds(7)).build()

        thread.send({ it.addUserMessage("Hello") }, turnOptions)
        thread.sendStreaming({ it.addUserMessage("Again") }, turnOptions).use {
            it.stream().forEach {}
        }
        thread.send("Bye")

        assertThat(http.requestOptions.map { it.timeout().get().request().seconds })
            .containsExactly(7L, 7L, 5L)
        assertThat(http.requestOptions.map { it.fallbackState().get() })
            .containsOnly(fallbackState)
            .hasSize(3)
    }

    @Test
    fun `a later timeout replaces the whole timeout of the thread`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "Hi again"), *stop()),
                message("msg_c", "Bye"),
                message("msg_d", "Bye again"),
            )
        val timeout =
            Timeout.builder().connect(Duration.ofSeconds(1)).read(Duration.ofSeconds(2)).build()
        val later = Timeout.builder().request(Duration.ofSeconds(7)).build()
        val laterOptions = RequestOptions.builder().timeout(later).build()
        val thread =
            client(http)
                .beta()
                .messages()
                .thread(
                    ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build(),
                    RequestOptions.builder().timeout(timeout).build(),
                )

        thread.send({ it.addUserMessage("Hello") }, laterOptions)
        thread.sendStreaming({ it.addUserMessage("Again") }, laterOptions).use {
            it.stream().forEach {}
        }
        thread.send("Bye")
        thread.update({}, laterOptions)
        thread.send("Bye again")

        assertThat(http.requestOptions.map { it.timeout().get() })
            .containsExactly(later, later, timeout, later)
    }

    @Test
    fun `a header or a query param is replaced or removed by the builder's calls for that`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
                message("msg_c", "Bye"),
                message("msg_d", "Bye again"),
            )
        val thread =
            thread(http) {
                putAdditionalHeader("x-replaced", "thread")
                putAdditionalHeader("x-removed", "thread")
                putAdditionalHeader("x-put", "thread")
                putAdditionalQueryParam("replaced", "thread")
                putAdditionalQueryParam("removed", "thread")
            }

        thread.send {
            it.addUserMessage("Hello")
                .replaceAdditionalHeaders("x-replaced", "turn")
                .removeAdditionalHeaders("x-removed")
                .putAdditionalHeader("x-put", "turn")
                .replaceAdditionalQueryParams("replaced", "turn")
                .removeAdditionalQueryParams("removed")
        }
        thread.send("Again")
        thread.update {
            it.replaceAdditionalHeaders("x-replaced", "update")
                .removeAdditionalHeaders("x-removed")
                .replaceAdditionalQueryParams("replaced", "update")
                .removeAdditionalQueryParams("removed")
        }
        thread.send("Bye")
        thread.send("Bye again")

        assertThat(http.requests.map { it.headers.values("x-replaced") })
            .containsExactly(listOf("turn"), listOf("thread"), listOf("update"), listOf("update"))
        assertThat(http.requests.map { it.headers.values("x-removed") })
            .containsExactly(emptyList(), listOf("thread"), emptyList(), emptyList())
        // A `put` adds a value to those of the name.
        assertThat(http.requests[0].headers.values("x-put")).containsExactly("thread", "turn")
        assertThat(http.requests.map { it.queryParams.values("replaced") })
            .containsExactly(listOf("turn"), listOf("thread"), listOf("update"), listOf("update"))
        assertThat(http.requests.map { it.queryParams.values("removed") })
            .containsExactly(emptyList(), listOf("thread"), emptyList(), emptyList())
    }

    @Test
    fun `a modifier adds messages, and can replace neither the conversation nor the thread`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = thread(http)
        thread.send("Hello")

        thread.send { assertThat(wire(it.addUserMessage("Again").build().messages())).hasSize(3) }

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
        assertThat(ThreadSendParams.Builder::class.java.methods.map { it.name })
            .`as`("Delete from `ThreadSendParams.Builder` what replaces the messages or the thread")
            .contains("addMessage", "addUserMessage", "maxTokens")
            .doesNotContain("messages", "thread", "continueThread", "body")
    }

    @Test
    fun `a modifier cannot set the output config or format, which creation takes`() {
        assertThat(ThreadSendParams.Builder::class.java.methods.map { it.name })
            .`as`("Delete from `ThreadSendParams.Builder` what sets the output config or format")
            .doesNotContain("outputConfig", "outputFormat")
        assertThat(ThreadCreateParams.Builder::class.java.methods.map { it.name })
            .contains("outputConfig", "outputFormat")
    }

    @Test
    fun `the threads beta joins the caller's betas once`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi"))

        thread(http) { addBeta("other-beta") }.send("Hello")
        thread(http) {
                addBeta("other-beta")
                addBeta(THREADS_BETA)
            }
            .send("Hello")
        for (index in 0..1) {
            assertThat(betas(http, index)).containsExactlyInAnyOrder("other-beta", THREADS_BETA)
        }
    }

    @Test
    fun `every request of a thread names the thread in the helper header`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "Hi again"), *stop()),
                404 to threadError("thread_not_found"),
                400 to threadError("thread_unsupported_request"),
                message("msg_c", "Bye"),
                sse(start("msg_d"), *textBlock(0, "Bye again"), *stop()),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)

        thread.send("Hello")
        thread.sendStreaming("Again").use { it.stream().forEach {} }
        thread.send("Bye")
        thread.sendStreaming("Bye again").use { it.stream().forEach {} }

        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "continue", "create", null, null)
        assertThat(http.requests.map { it.headers.values(STAINLESS_HELPER_HEADER) })
            .containsOnly(listOf("message-thread"))
    }

    @Test
    fun `the thread's helper tag joins the caller's, and no header given later drops it`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Hi again"),
                message("msg_c", "Bye"),
                message("msg_d", "Bye again"),
            )
        val thread = thread(http) { putAdditionalHeader("X-Stainless-Helper", "mine") }

        thread.send("Hello")
        thread.send { it.addUserMessage("Again").removeAdditionalHeaders(STAINLESS_HELPER_HEADER) }
        assertThat(helperHeader(thread.params())).containsExactly("mine")
        thread.update { it.additionalHeaders(Headers.builder().build()) }
        thread.send("Bye")
        assertThat(helperHeader(thread.params())).isEmpty()
        thread.update { it.putAdditionalHeader(STAINLESS_HELPER_HEADER, "message-thread, other") }
        thread.send("Bye again")

        assertThat(http.requests.map { it.headers.values(STAINLESS_HELPER_HEADER) })
            .containsExactly(
                listOf("mine, message-thread"),
                listOf("message-thread"),
                listOf("message-thread"),
                listOf("message-thread, other"),
            )
    }

    @Test
    fun `the container of a reply is sent on later turns`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi", container = "container_1"),
                message("msg_b", "Hi again", container = "container_2"),
                message("msg_c", "Bye"),
            )
        val thread = thread(http)

        thread.send("Hello")
        thread.send("Again")
        thread.send("Bye")

        val bodies = http.jsonBodies
        assertThat(bodies[0].has("container")).isFalse
        assertThat(bodies[1]["container"].asText()).isEqualTo("container_1")
        // The thread named a container by then, and what the caller or the thread named is kept.
        assertThat(bodies[2]["container"].asText()).isEqualTo("container_1")
    }

    @Test
    fun `a reply with a stop reason or a block this SDK does not know is recorded`() {
        val http =
            FakeHttpClient(
                """
                {"id":"msg_a","type":"message","role":"assistant","model":"claude-sonnet-4-5",
                 "content":[{"type":"text","text":"Hi","citations":null},
                            {"type":"brand_new_block","value":1}],
                 "stop_reason":"brand_new_reason","stop_sequence":null,"usage":$USAGE}
                """
                    .trimIndent(),
                message("msg_b", "Hi again"),
            )
        val thread = thread(http)

        thread.append(thread.send("Hello"))
        thread.send("Again")

        assertThat(thread.messages()).hasSize(4)
        assertThat(wire(thread.messages())[1]["content"][1])
            .isEqualTo(json("""{"type":"brand_new_block","value":1}"""))
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
    }

    @Test
    fun `a fork starts a new thread and leaves the original as it was`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Forked"),
                message("msg_c", "Original"),
            )
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello")

        val fork = thread.fork()
        assertThat(http.requests).hasSize(1)
        assertThat(fork.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        assertThat(fork.previousMessageId()).isEmpty
        assertThat(wire(fork.messages())).isEqualTo(wire(thread.messages()))
        fork.send("On the fork")
        thread.send("On the original")

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[1]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("On the fork")}]"""))
        assertThat(bodies[1]["system"]).isEqualTo(json(""""Be brief.""""))
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("""[${user("On the original")}]"""))
        assertThat(thread.messages()).hasSize(4)
        assertThat(fork.messages()).hasSize(4)
    }

    @Test
    fun `the params rebuild a thread that starts with a create`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello")
        thread.append(userParam("Again"))

        val params = thread.params()
        client(http).beta().messages().thread(params).send()

        assertThat(params.initialMessageParams.thread()).isEmpty
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[1]["system"]).isEqualTo(http.jsonBodies[0]["system"])
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
    }

    @ParameterizedTest
    @EnumSource(UnsupportedRequestBehavior::class)
    fun `the params and a fork keep the unsupported request behavior, which is never sent`(
        behavior: UnsupportedRequestBehavior
    ) {
        val http =
            FakeHttpClient(List(2) { unsupported(behavior, message("msg_a", "Hi")) }.flatten())
        val thread = thread(http, behavior)

        val rebuilt = client(http).beta().messages().thread(thread.params())
        val outcomes = listOf(rebuilt, thread.fork()).map { runCatching { it.send("Hello").id() } }

        assertFollows(behavior, outcomes, http)
        assertThat(thread.params().unsupportedRequestBehavior()).isEqualTo(behavior)
        assertThat(rebuilt.params().unsupportedRequestBehavior()).isEqualTo(behavior)
    }

    @ParameterizedTest
    @EnumSource(UnsupportedRequestBehavior::class)
    fun `the params and a fork of a thread of a class keep the unsupported request behavior`(
        behavior: UnsupportedRequestBehavior
    ) {
        val http =
            FakeHttpClient(
                List(2) { unsupported(behavior, message("msg_a", city("Paris"))) }.flatten()
            )
        val thread = structured(http) { unsupportedRequestBehavior(behavior) }

        val rebuilt = client(http).beta().messages().thread(thread.params())
        val outcomes = listOf(rebuilt, thread.fork()).map { runCatching { it.send("Hello").id() } }

        assertFollows(behavior, outcomes, http)
        assertThat(thread.params().unsupportedRequestBehavior()).isEqualTo(behavior)
        assertThat(rebuilt.params().unsupportedRequestBehavior()).isEqualTo(behavior)
    }

    @Test
    fun `a thread of a class sends its schema on every turn and returns structured replies`() {
        val http =
            FakeHttpClient(
                message("msg_a", city("Paris")),
                message("msg_b", city("Rome")),
                sse(start("msg_c"), *textBlock(0, city("Oslo")), *stop()),
                404 to threadError("thread_not_found"),
                message("msg_d", city("Bern")),
            )
        val thread =
            structured(http) { outputConfig(BetaOutputConfig.builder().effort(EFFORT_LOW).build()) }

        val first = thread.send { it.addUserMessage("Go").maxTokens(16) }
        val second = thread.send("Again")
        thread.sendStreaming("Bye").use { it.stream().forEach {} }
        val streamed = thread.lastMessage().get()
        thread.update { it.maxTokens(2048) }
        thread.append(userParam("Go on"))
        val replayed = thread.send()

        assertThat(listOf(first, second, streamed, replayed).map { cityOf(it) })
            .containsExactly("Paris", "Rome", "Oslo", "Bern")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"]["type"].asText() })
            .containsExactly("create", "continue", "continue", "continue", "create")
        assertThat(bodies.map { it["output_config"] })
            .containsOnly(json("""{"effort":"low","format":$CITY_FORMAT}"""))
        assertThat(bodies).noneMatch { it.has("output_format") }
        assertThat(bodies.indices.map { betas(http, it) }).containsOnly(listOf(THREADS_BETA))
        assertThat(thread.previousMessageId()).hasValue("msg_d")
        assertThat(thread.messages()).hasSize(8)
    }

    @Test
    fun `the params of a thread of a class create a thread with or without the class`() {
        val http =
            FakeHttpClient(
                message("msg_a", city("Paris")),
                message("msg_b", city("Rome")),
                message("msg_c", city("Oslo")),
            )
        val thread = structured(http)
        thread.send("Go")

        val params = thread.params()
        val messages = client(http).beta().messages()
        messages.thread(params.rawParams).send("Again")
        val structured = messages.thread(params).send("Again")

        assertThat(cityOf(structured)).isEqualTo("Oslo")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"] }).containsOnly(CREATE)
        assertThat(bodies.map { it["output_config"] })
            .containsOnly(json("""{"format":$CITY_FORMAT}"""))
        assertThat(bodies[2]).isEqualTo(bodies[1])
        assertThat(bodies.indices.map { betas(http, it) }).containsOnly(listOf(THREADS_BETA))
    }

    @Test
    fun `a fork of a thread of a class and a tool runner over it keep the schema`() {
        val http =
            FakeHttpClient(
                message("msg_a", city("Paris")),
                message("msg_b", city("Rome")),
                message("msg_c", city("Oslo")),
            )
        val thread = structured(http)
        thread.send("Go")

        val fork = thread.fork()
        assertThat(cityOf(fork.lastMessage().get())).isEqualTo("Paris")
        assertThat(cityOf(fork.send("On the fork"))).isEqualTo("Rome")
        thread.append(userParam("On the original"))
        thread.toolRunner().toList()

        assertThat(cityOf(thread.lastMessage().get())).isEqualTo("Oslo")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"]["type"].asText() })
            .containsExactly("create", "create", "continue")
        assertThat(bodies.map { it["output_config"] })
            .containsOnly(json("""{"format":$CITY_FORMAT}"""))
    }

    @Test
    fun `a thread of a class is created and used from Java`() {
        val http = FakeHttpClient(message("msg_a", city("Paris")), message("msg_b", city("Rome")))

        val replies =
            MessageThreadJavaCalls.sendStructured(client(http).beta().messages(), City::class.java)

        assertThat(replies.map { cityOf(it) }).containsExactly("Paris", "Rome")
        assertThat(http.jsonBodies.map { it["output_config"] })
            .containsExactly(
                json("""{"format":$CITY_FORMAT}"""),
                json("""{"effort":"low","format":$CITY_FORMAT}"""),
            )
    }

    @Test
    fun `a thread is refused on a client whose service takes no threads`() {
        val http = FakeHttpClient()
        val messages = client(http) { backend(UNTHREADED) }.beta().messages()

        assertThrows<AnthropicException> {
            messages.thread(
                ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()
            )
        }

        assertThat(http.requests).isEmpty()
        // The answer survives a copy of the client's options.
        assertThrows<AnthropicException> {
            messages
                .withOptions { it.maxRetries(1) }
                .thread(
                    ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()
                )
        }
    }

    @Test
    fun `a client or a service of which nothing is known may thread`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi"))
        val params = ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()
        val ownService = mock<MessageService>()
        whenever(ownService.thread(any<ThreadCreateParams>())).thenCallRealMethod()
        whenever(ownService.thread(any<ThreadCreateParams>(), any())).thenCallRealMethod()
        whenever(ownService.create(any<MessageCreateParams>(), any()))
            .thenReturn(jsonMapper().readValue(message("msg_c", "Hi"), BetaMessage::class.java))

        client(http).beta().messages().thread(params).send("Hello")
        client(http) { backend(PLAIN) }.beta().messages().thread(params).send("Hello")
        ownService.thread(params).send("Hello")

        for (body in http.jsonBodies) {
            assertThat(body["thread"]).isEqualTo(CREATE)
        }
        val sent = argumentCaptor<MessageCreateParams>()
        verify(ownService).create(sent.capture(), any())
        assertThat(sent.firstValue.thread().get().isCreate()).isTrue
    }

    @Test
    fun `a streamed turn creates a thread and the next one continues it`() {
        val http =
            FakeHttpClient(
                sse(start("msg_a"), *textBlock(0, "Hi"), *stop()),
                sse(start("msg_b"), *textBlock(0, "Hi again"), *stop()),
            )
        val thread = thread(http) { system("Be brief.") }

        val first = thread.sendStreaming("Hello").use { it.stream().collect(Collectors.toList()) }
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        thread.sendStreaming(userParam("Again")).use { it.stream().forEach {} }

        assertThat(first).hasSize(6)
        assertThat(first.first().isMessageStart()).isTrue
        assertThat(first.last().isMessageStop()).isTrue
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["stream"].asBoolean() }).containsExactly(true, true)
        assertThat(bodies[0]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[1]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
        assertThat(bodies[1].has("system")).isFalse
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_b")
        assertThat(wire(thread.messages()))
            .isEqualTo(
                json("""[$HELLO,${assistant("Hi")},${user("Again")},${assistant("Hi again")}]""")
            )
        assertThat(http.issued.map { it.closed.get() }).containsExactly(true, true)
    }

    @Test
    fun `a stream that ends early records the part of the reply that arrived`() {
        val cutOff = sse(start("msg_b"), *textBlock(0, "One"), *openTextBlock(1, "Tw"))
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                cutOff,
                message("msg_c", "With the part"),
                cutOff,
                message("msg_d", "Again"),
            )
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello")

        thread.sendStreaming("Again").use { it.stream().forEach {} }

        val part =
            """{"content":[{"text":"One","type":"text"},{"text":"Tw","type":"text"}],"role":"assistant"}"""
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(wire(thread.messages()))
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")},$part]"""))

        // A message after the part sends the conversation with the part.
        thread.send("More")
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(
                json("""[$HELLO,${assistant("Hi")},${user("Again")},$part,${user("More")}]""")
            )
        assertThat(http.jsonBodies[2]["system"]).isEqualTo(json(""""Be brief.""""))

        // No message after the part sends the turn again, in place of the part.
        thread.sendStreaming("Next").use { it.stream().forEach {} }
        thread.send()
        assertThat(http.jsonBodies[4]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[4]["messages"]).hasSize(7)
        assertThat(http.jsonBodies[4]["messages"].last()).isEqualTo(json(user("Next")))
        assertThat(wire(thread.messages()).last()).isEqualTo(json(assistant("Again")))
        assertThat(thread.previousMessageId()).hasValue("msg_d")
    }

    @Test
    fun `a partial reply is sent as it was recorded, with the calls that nothing answers`() {
        val toolUse = """{"type":"tool_use","id":"toolu_1","name":"get_weather","input":{}}"""
        val search =
            """{"type":"server_tool_use","id":"srvtoolu_1","name":"web_search","input":{}}"""
        val cutOff =
            sse(
                start("msg_a"),
                *textBlock(0, "Let me check."),
                blockStart(1, toolUse),
                inputDelta(1, """{\"city\":\"Paris\"}"""),
                blockStop(1),
                blockStart(2, search),
                inputDelta(2, """{\"query\":\"Paris\"}"""),
                blockStop(2),
                blockStart(3, toolUse.replace("toolu_1", "toolu_2")),
                inputDelta(3, """{\"ci"""),
            )
        val http = FakeHttpClient(cutOff, message("msg_b", "Hi"))
        val thread = thread(http) { addTool(weatherTool()) }

        thread.sendStreaming("Hello").use { it.stream().forEach {} }
        val recorded = wire(thread.messages())[1]
        thread.send("Never mind")

        // The calls that finished are recorded, and the call that did not is never kept.
        assertThat(recorded["content"].map { it["type"].asText() })
            .containsExactly("text", "tool_use", "server_tool_use")
        assertThat(recorded["content"][1]["input"]).isEqualTo(json("""{"city":"Paris"}"""))
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$HELLO,$recorded,${user("Never mind")}]"""))
    }

    @Test
    fun `a stream that ends before any content records no reply`() {
        val http =
            FakeHttpClient(message("msg_a", "Hi"), sse(start("msg_b")), message("msg_c", "Again"))
        val thread = thread(http)
        thread.send("Hello")

        thread.sendStreaming("Again").use { it.stream().forEach {} }
        assertThat(thread.messages()).hasSize(3)
        assertThat(thread.previousMessageId()).isEmpty
        thread.send()

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
    }

    @Test
    fun `a stream that is closed or ends before its reply starts takes its messages back`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "Never read"), *stop()),
                sse("""{"type":"ping"}"""),
                message("msg_c", "Hi again"),
            )
        val thread = thread(http)
        thread.send("Hello")

        thread.sendStreaming("Closed").close()
        thread.sendStreaming("Cut off").use { it.stream().forEach {} }
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("Again")

        assertThat(http.jsonBodies[3]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[3]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a turn that fails after a partial reply leaves the partial reply`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                sse(start("msg_b"), *textBlock(0, "One")),
                400 to error("invalid_request_error"),
                message("msg_c", "Again"),
            )
        val thread = thread(http)
        thread.send("Hello")
        thread.sendStreaming("Again").use { it.stream().forEach {} }
        val turn = """$HELLO,${assistant("Hi")},${user("Again")}"""

        assertThrows<BadRequestException> { thread.turn(streaming, "New") }
        assertThat(wire(thread.messages())).isEqualTo(json("""[$turn,${assistant("One")}]"""))
        thread.send()

        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[$turn,${assistant("One")},${user("New")}]"""))
        assertThat(http.jsonBodies[3]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[3]["messages"]).isEqualTo(json("[$turn]"))
    }

    @Test
    fun `a stream the caller closes early is partial and releases the response`() {
        val http =
            FakeHttpClient(
                sse(start("msg_a"), *textBlock(0, "Hi"), *textBlock(1, "there"), *stop()),
                message("msg_b", "Again"),
            )
        val thread = thread(http)

        thread.sendStreaming("Hello").use { stream ->
            stream.stream().filter { it.isContentBlockStop() }.findFirst()
        }

        assertThat(http.issued.single().closed.get()).isTrue
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("Again")
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
    }

    @Test
    fun `a whole reply is recorded when its last event is delivered`() {
        val http =
            FakeHttpClient(
                sse(start("msg_a"), *textBlock(0, "Hi"), *stop()),
                message("msg_b", "Again"),
            )
        val thread = thread(http)

        val stream = thread.sendStreaming("Hello")
        stream.stream().forEach {}
        // The turn has ended, so the thread takes the next one before the stream is closed.
        thread.send("Again")
        stream.close()
        stream.close()

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(thread.previousMessageId()).hasValue("msg_b")
        assertThat(thread.messages()).hasSize(4)
        assertThat(http.issued.first().closed.get()).isTrue
    }

    @Test
    fun `a turn is refused while a stream is open and stages nothing`() {
        val http =
            FakeHttpClient(
                sse(start("msg_a"), *textBlock(0, "Hi"), *stop()),
                message("msg_b", "Again"),
            )
        val thread = thread(http)

        thread.sendStreaming("Hello").use { stream ->
            assertThrows<IllegalStateException> { thread.send("Too early") }
            assertThrows<IllegalStateException> { thread.sendStreaming("Too early") }
            thread.append(userParam("After"))
            assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${user("After")}]"""))
            stream.stream().forEach {}
        }
        thread.send()

        assertThat(http.requests).hasSize(2)
        assertThat(wire(thread.messages()).map { it["role"].asText() })
            .containsExactly("user", "assistant", "user", "assistant")
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("After")}]"""))
    }

    @Test
    fun `a streamed reply that cannot be recorded ends its turn`() {
        val withoutId = start("msg_a").replace(""""id":"msg_a",""", "")
        val http =
            FakeHttpClient(sse(withoutId, *textBlock(0, "Hi"), *stop()), message("msg_b", "More"))
        val thread = thread(http)

        assertThrows<AnthropicInvalidDataException> {
            thread.sendStreaming("Hello").use { it.stream().forEach {} }
        }
        thread.send("Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(thread.previousMessageId()).hasValue("msg_b")
    }

    @Test
    fun `a stream request that fails is made again, and a rejected continue is replayed`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                500 to error("api_error"),
                404 to threadError("thread_not_found"),
                sse(start("msg_b"), *textBlock(0, "Hi again"), *stop()),
            )
        val thread = thread(http)
        thread.send("Hello")

        assertThrows<AnthropicServiceException> { thread.sendStreaming("Again") }
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        thread.sendStreaming("Again").use { it.stream().forEach {} }

        val bodies = http.jsonBodies
        assertThat(bodies[2]).isEqualTo(bodies[1])
        assertThat(bodies[3]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[3]["stream"].asBoolean()).isTrue
        assertThat(bodies[3]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
        assertThat(thread.previousMessageId()).hasValue("msg_b")
    }

    @Test
    fun `the last message is the part of the reply that arrived when the stream ended early`() {
        val cutOff = sse(start("msg_b"), *textBlock(0, "One"), *openTextBlock(1, "Tw"))
        val http = FakeHttpClient(message("msg_a", "Hi"), cutOff)
        val thread = thread(http)
        thread.send("Hello")

        thread.sendStreaming("Again").use { it.stream().forEach {} }

        val last = thread.lastMessage().get()
        assertThat(last.id()).isEqualTo("msg_b")
        assertThat(last.content().map { it.asText().text() }).containsExactly("One", "Tw")
        assertThat(last.stopReason()).isEmpty
        assertThat(thread.previousMessageId()).isEmpty
    }

    class City {
        @JvmField var city: String = ""
    }

    internal companion object {
        const val THREADS_BETA = "message-threads-2026-08-12"

        const val USAGE = """{"input_tokens":1,"output_tokens":1}"""

        val HELLO = user("Hello")

        private val EFFORT_LOW = BetaOutputConfig.Effort.LOW

        private val MAPPER = jsonMapper()

        /** A backend that says nothing about message threads, as one written before them. */
        val PLAIN =
            object : Backend {
                override fun baseUrl(): String = "https://api.example.test"

                override fun close() {}
            }

        /** A backend whose service takes no message threads. */
        val UNTHREADED =
            object : Backend {
                override fun baseUrl(): String = "https://api.example.test"

                override fun supportsMessageThreads(): Boolean = false

                override fun close() {}
            }

        fun client(
            http: FakeHttpClient,
            configure: ClientOptions.Builder.() -> Unit = {},
        ): AnthropicClientImpl =
            AnthropicClientImpl(
                ClientOptions.builder()
                    .httpClient(http)
                    .baseUrl("https://api.example.test")
                    .maxRetries(0)
                    .apply(configure)
                    .build()
            )

        fun thread(
            http: FakeHttpClient,
            behavior: UnsupportedRequestBehavior = UnsupportedRequestBehavior.ERROR,
            configure: ClientOptions.Builder.() -> Unit = {},
            params: ThreadCreateParams.Builder.() -> Unit = {},
        ): BetaMessageThread =
            client(http, configure)
                .beta()
                .messages()
                .thread(
                    ThreadCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .maxTokens(1024)
                        .unsupportedRequestBehavior(behavior)
                        .apply(params)
                        .build()
                )

        /** A thread whose replies are instances of [City]. */
        fun structured(
            http: FakeHttpClient,
            params: ThreadCreateParams.Builder.() -> Unit = {},
        ): StructuredBetaMessageThread<City> =
            client(http)
                .beta()
                .messages()
                .thread(
                    ThreadCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .maxTokens(1024)
                        .apply(params)
                        .outputConfig(City::class.java)
                        .build()
                )

        /** The text of a reply that names the city, for the inside of a JSON string. */
        fun city(name: String) = """{\"city\":\"$name\"}"""

        fun cityOf(message: StructuredMessage<City>): String =
            message.content().single().text().get().text().city

        /** The output format that the message params derive from [City]. */
        val CITY_FORMAT: JsonNode =
            MAPPER.valueToTree<JsonNode>(
                    MessageCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .maxTokens(1024)
                        .messages(emptyList())
                        .outputConfig(City::class.java)
                        .build()
                        ._outputConfig()
                )["format"]

        /** A thread with a `system` and a tool. */
        fun pinned(
            http: FakeHttpClient,
            behavior: UnsupportedRequestBehavior = UnsupportedRequestBehavior.ERROR,
        ): BetaMessageThread = thread(http, behavior) { system("Be brief.").addTool(weatherTool()) }

        /** A thread that asks the API to fall back to another model. */
        fun withFallbacks(
            http: FakeHttpClient,
            behavior: UnsupportedRequestBehavior = UnsupportedRequestBehavior.ERROR,
        ): BetaMessageThread =
            thread(http, behavior) {
                system("Be brief.")
                addBeta(AnthropicBeta.SERVER_SIDE_FALLBACK_2026_07_01)
                fallbacksOfFallbackParams(
                    listOf(BetaFallbackParam.builder().model("claude-opus-4-5").build())
                )
            }

        /** The usage of a reply that [model] wrote after the model that was asked gave up. */
        fun fallbackUsage(model: String): String {
            val tokens =
                """"input_tokens":1,"output_tokens":1,"cache_creation_input_tokens":0,""" +
                    """"cache_read_input_tokens":0,"cache_creation":null"""
            return """{"input_tokens":1,"output_tokens":1,"iterations":[""" +
                """{"type":"fallback_message","model":"$model",$tokens},""" +
                """{"type":"message","model":"claude-sonnet-4-5",$tokens}]}"""
        }

        fun weatherTool() = tool("get_weather")

        fun timeTool() = tool("get_time")

        /** A `system` message that takes [weatherTool] away from the model. */
        fun weatherToolRemoval(): BetaMessageParam =
            BetaMessageParam.ofSystemOfBlocks(
                listOf(
                    SystemMessageContentBlock.ofToolRemoval(
                        BetaRequestToolRemovalBlock.builder().referenceTool("get_weather").build()
                    )
                )
            )

        const val WEATHER_TOOL_REMOVAL =
            """{"content":[{"tool":{"name":"get_weather","type":"tool_reference"},""" +
                """"type":"tool_removal"}],"role":"system"}"""

        fun tool(name: String) =
            BetaTool.builder()
                .name(name)
                .inputSchema(BetaTool.InputSchema.builder().build())
                .build()

        /** The message of the error that refuses a change of [fields]. */
        fun refusal(fields: List<String>): String =
            "Cannot change ${fields.joinToString(" and ") { "`$it`" }} on a message thread: the " +
                "API keeps the `system` and `tools` that the thread was created with. To " +
                "change them, start a new thread from `params()` or `fork()`." +
                if ("tools" in fields)
                    " To add or remove tools on this thread, send `tool_addition` or " +
                        "`tool_removal` blocks in a `system` message."
                else ""

        fun json(text: String): JsonNode = MAPPER.readTree(text)

        fun helperHeader(params: ThreadCreateParams): List<String> =
            params.initialMessageParams._additionalHeaders().values(STAINLESS_HELPER_HEADER)

        /** A `thread` value of the given [type], as a caller gives one. */
        fun ownThread(type: String): BetaMessageThreadParam =
            if (type == "create")
                BetaMessageThreadParam.ofCreate(BetaThreadCreateParams.builder().build())
            else BetaMessageThreadParam.ofContinue(BetaThreadContinueParams.of("msg_z"))

        /** The `thread` of a request that starts a thread. */
        val CREATE: JsonNode = json("""{"type":"create"}""")

        /** The `thread` of a request that continues from the reply with [id]. */
        fun continues(id: String): JsonNode =
            json("""{"type":"continue","previous_message_id":"$id"}""")

        fun wire(messages: List<BetaMessageParam>): JsonNode = MAPPER.valueToTree(messages)

        fun wire(message: BetaMessageParam): JsonNode = MAPPER.valueToTree(message)

        fun user(text: String) = """{"content":"$text","role":"user"}"""

        fun userParam(text: String): BetaMessageParam = BetaMessageParam.ofUser(text)

        private val EPHEMERAL = BetaCacheControlEphemeral.builder().build()

        /** A user message of one text block that has a cache breakpoint. */
        fun marked(text: String): BetaMessageParam =
            BetaMessageParam.ofUserOfContentBlockParams(
                listOf(
                    BetaContentBlockParam.ofText(
                        BetaTextBlockParam.builder().text(text).cacheControl(EPHEMERAL).build()
                    )
                )
            )

        /** What [marked] gives, without the cache breakpoint. */
        fun unmarked(text: String) =
            """{"content":[{"text":"$text","type":"text"}],"role":"user"}"""

        fun assistant(text: String) =
            """{"content":[{"text":"$text","type":"text"}],"role":"assistant"}"""

        /** The tokens of the `anthropic-beta` header of the request at [index]. */
        fun betas(http: FakeHttpClient, index: Int): List<String> =
            http.requests[index].headers.values("anthropic-beta").flatMap { value ->
                value.split(',').map { it.trim() }
            }

        fun message(
            id: String,
            text: String,
            stopReason: String = "end_turn",
            container: String? = null,
            usage: String = USAGE,
        ): String {
            val containerField =
                container
                    ?.let { ""","container":{"id":"$it","expires_at":"2026-01-01T00:00:00Z"}""" }
                    .orEmpty()
            return """{"id":"$id","type":"message","role":"assistant",""" +
                """"model":"claude-sonnet-4-5","content":[{"type":"text","text":"$text"}],""" +
                """"stop_reason":"$stopReason","stop_sequence":null,"usage":$usage""" +
                """$containerField}"""
        }

        /** A whole reply, as the body of a response or as the events of a stream. */
        fun reply(
            id: String,
            text: String,
            streaming: Boolean,
            stopReason: String = "end_turn",
            usage: String = USAGE,
        ): String =
            if (streaming) sse(start(id), *textBlock(0, text), *stop(stopReason, usage))
            else message(id, text, stopReason, usage = usage)

        /** Sends one turn, with a user message of [text] if given, and reads its whole reply. */
        private fun BetaMessageThread.turn(streaming: Boolean, text: String? = null) {
            val messages = listOfNotNull(text?.let { userParam(it) })
            if (streaming) sendStreaming(messages).use { it.stream().forEach {} }
            else send(messages)
        }

        fun sse(vararg events: String): String =
            events.joinToString("") { event ->
                "event: ${MAPPER.readTree(event)["type"].asText()}\ndata: $event\n\n"
            }

        fun start(id: String) =
            """{"type":"message_start","message":{"id":"$id","type":"message",""" +
                """"role":"assistant","model":"claude-sonnet-4-5","content":[],""" +
                """"stop_reason":null,"stop_sequence":null,"usage":$USAGE}}"""

        fun blockStart(index: Int, block: String) =
            """{"type":"content_block_start","index":$index,"content_block":$block}"""

        fun textDelta(index: Int, text: String) =
            """{"type":"content_block_delta","index":$index,""" +
                """"delta":{"type":"text_delta","text":"$text"}}"""

        fun inputDelta(index: Int, partialJson: String) =
            """{"type":"content_block_delta","index":$index,""" +
                """"delta":{"type":"input_json_delta","partial_json":"$partialJson"}}"""

        fun blockStop(index: Int) = """{"type":"content_block_stop","index":$index}"""

        /** The events of a text block whose stream ends before the block does. */
        fun openTextBlock(index: Int, text: String) =
            arrayOf(blockStart(index, """{"type":"text","text":""}"""), textDelta(index, text))

        fun textBlock(index: Int, text: String) =
            arrayOf(*openTextBlock(index, text), blockStop(index))

        fun delta(stopReason: String, usage: String = """{"output_tokens":1}""") =
            """{"type":"message_delta","delta":{"stop_reason":"$stopReason",""" +
                """"stop_sequence":null},"usage":$usage}"""

        fun stop(stopReason: String = "end_turn", usage: String = """{"output_tokens":1}""") =
            arrayOf(delta(stopReason, usage), """{"type":"message_stop"}""")

        fun error(type: String, message: String = "No."): String =
            """{"type":"error","error":{"type":"$type","message":"$message"}}"""

        /**
         * The answers to the requests of one turn that the API cannot serve on a thread, on a
         * thread with [behavior]. The [reply] answers the request without a thread.
         */
        fun unsupported(behavior: UnsupportedRequestBehavior, reply: String): List<Any> =
            listOfNotNull(
                400 to threadError("thread_unsupported_request"),
                reply.takeIf { behavior == UnsupportedRequestBehavior.CONTINUE_UNTHREADED },
            )

        /**
         * Asserts that each of the [outcomes], of one turn on a thread of its own that was answered
         * with [unsupported], is what [behavior] asks for, and that [http] never sent [behavior].
         */
        fun assertFollows(
            behavior: UnsupportedRequestBehavior,
            outcomes: List<Result<String>>,
            http: FakeHttpClient,
        ) {
            val threads = http.jsonBodies.map { it["thread"]?.get("type")?.asText() }
            when (behavior) {
                UnsupportedRequestBehavior.ERROR -> {
                    assertThat(outcomes.map { it.exceptionOrNull() }).allSatisfy {
                        assertThat(it).isInstanceOf(BadRequestException::class.java)
                    }
                    assertThat(threads).containsExactly("create", "create")
                }
                UnsupportedRequestBehavior.CONTINUE_UNTHREADED -> {
                    assertThat(outcomes.map { it.getOrThrow() }).containsExactly("msg_a", "msg_a")
                    assertThat(threads).containsExactly("create", null, "create", null)
                }
            }
            assertThat(http.jsonBodies.flatMap { it.fieldNames().asSequence() }).noneMatch {
                it.contains("unsupported", ignoreCase = true)
            }
        }

        fun threadError(code: String, unsupported: String? = null): String {
            val values = unsupported?.let { ""","unsupported":$it""" }.orEmpty()
            return """{"type":"error","error":{"type":"invalid_request_error",""" +
                """"message":"No.","details":{"error_code":"$code"$values}}}"""
        }
    }
}
