package com.anthropic.helpers

import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.Timeout
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.AnthropicIoException
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.InternalServerException
import com.anthropic.helpers.BetaMessageThreadTest.City
import com.anthropic.helpers.BetaMessageThreadTest.Companion.CITY_FORMAT
import com.anthropic.helpers.BetaMessageThreadTest.Companion.CREATE
import com.anthropic.helpers.BetaMessageThreadTest.Companion.HELLO
import com.anthropic.helpers.BetaMessageThreadTest.Companion.THREADS_BETA
import com.anthropic.helpers.BetaMessageThreadTest.Companion.UNTHREADED
import com.anthropic.helpers.BetaMessageThreadTest.Companion.WEATHER_TOOL_REMOVAL
import com.anthropic.helpers.BetaMessageThreadTest.Companion.assertFollows
import com.anthropic.helpers.BetaMessageThreadTest.Companion.assistant
import com.anthropic.helpers.BetaMessageThreadTest.Companion.betas
import com.anthropic.helpers.BetaMessageThreadTest.Companion.city
import com.anthropic.helpers.BetaMessageThreadTest.Companion.cityOf
import com.anthropic.helpers.BetaMessageThreadTest.Companion.continues
import com.anthropic.helpers.BetaMessageThreadTest.Companion.delta
import com.anthropic.helpers.BetaMessageThreadTest.Companion.error as errorBody
import com.anthropic.helpers.BetaMessageThreadTest.Companion.fallbackUsage
import com.anthropic.helpers.BetaMessageThreadTest.Companion.json
import com.anthropic.helpers.BetaMessageThreadTest.Companion.marked
import com.anthropic.helpers.BetaMessageThreadTest.Companion.message
import com.anthropic.helpers.BetaMessageThreadTest.Companion.refusal
import com.anthropic.helpers.BetaMessageThreadTest.Companion.reply
import com.anthropic.helpers.BetaMessageThreadTest.Companion.sse
import com.anthropic.helpers.BetaMessageThreadTest.Companion.start
import com.anthropic.helpers.BetaMessageThreadTest.Companion.stop
import com.anthropic.helpers.BetaMessageThreadTest.Companion.textBlock
import com.anthropic.helpers.BetaMessageThreadTest.Companion.threadError
import com.anthropic.helpers.BetaMessageThreadTest.Companion.unmarked
import com.anthropic.helpers.BetaMessageThreadTest.Companion.unsupported
import com.anthropic.helpers.BetaMessageThreadTest.Companion.user
import com.anthropic.helpers.BetaMessageThreadTest.Companion.userParam
import com.anthropic.helpers.BetaMessageThreadTest.Companion.weatherTool
import com.anthropic.helpers.BetaMessageThreadTest.Companion.weatherToolRemoval
import com.anthropic.helpers.BetaMessageThreadTest.Companion.wire
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaOutputConfig
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import com.anthropic.services.async.beta.MessageServiceAsync
import java.time.Duration
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.CountDownLatch
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTimeoutPreemptively
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

/** Every test that takes `streaming` sends its turns as futures and as streams. */
internal class BetaMessageThreadAsyncTest {

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `the first turn creates a thread and the next one continues it`(streaming: Boolean) {
        val http =
            FakeHttpClient(reply("msg_a", "Hi", streaming), reply("msg_b", "More", streaming))
        val thread =
            thread(http) {
                system("Be brief.")
                addUserMessage("Hello")
            }
        assertThat(http.requests).isEmpty()

        thread.turn(streaming)
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        thread.append(userParam("One"))
        thread.turn(streaming, "Two")

        val (create, next) = http.jsonBodies
        assertThat(create["thread"]).isEqualTo(CREATE)
        assertThat(create["messages"]).isEqualTo(json("""[$HELLO]"""))
        assertThat(create["system"].asText()).isEqualTo("Be brief.")
        assertThat(next["thread"]).isEqualTo(continues("msg_a"))
        assertThat(next["messages"]).isEqualTo(json("""[${user("One")},${user("Two")}]"""))
        assertThat(next.has("system")).isFalse
        for (index in 0..1) {
            assertThat(http.jsonBodies[index].path("stream").asBoolean()).isEqualTo(streaming)
            assertThat(betas(http, index)).containsExactly(THREADS_BETA)
        }
        assertThat(thread.previousMessageId()).hasValue("msg_b")
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_b")
        assertThat(wire(thread.messages()))
            .isEqualTo(
                json(
                    """[$HELLO,${assistant("Hi")},${user("One")},${user("Two")},""" +
                        """${assistant("More")}]"""
                )
            )
        assertThat(wire(thread.params().initialMessageParams.messages()))
            .isEqualTo(wire(thread.messages()))
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
        val first = thread.send("Hello").join()

        thread.append(first)
        val second = thread.send(first).join()
        thread.sendStreaming(second).drain()
        val seeded = thread(http)
        seeded.append(userParam("Hello"))
        seeded.append(first)
        seeded.send("Again").join()
        val prefilled = thread(http) { addUserMessage("Hello") }
        prefilled.sendStreaming(first).drain()

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
        thread.send("Hello").join()

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
        thread.send("Again").join()
        thread.sendStreaming("Bye").drain()
        thread.fork().send("On the fork").join()

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

        thread.send({ it.addUserMessage("Hello") }, turnOptions).join()
        thread.sendStreaming({ it.addUserMessage("Again") }, turnOptions).drain()
        thread.send("Bye").join()

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

        thread.send({ it.addUserMessage("Hello") }, laterOptions).join()
        thread.sendStreaming({ it.addUserMessage("Again") }, laterOptions).drain()
        thread.send("Bye").join()
        thread.update({}, laterOptions)
        thread.send("Bye again").join()

        assertThat(http.requestOptions.map { it.timeout().get() })
            .containsExactly(later, later, timeout, later)
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
                message("msg_e", "Mine"),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)

        thread.send("Hello").join()
        thread.sendStreaming("Again").drain()
        thread.send("Bye").join()
        thread.sendStreaming("Bye again").drain()
        thread.update { it.putAdditionalHeader(STAINLESS_HELPER_HEADER, "mine") }
        thread.send("Mine").join()

        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "continue", "create", null, null, null)
        assertThat(http.requests.map { it.headers.values(STAINLESS_HELPER_HEADER).single() })
            .containsExactly(*Array(6) { "message-thread" }, "mine, message-thread")
    }

    @Test
    fun `the reply is recorded before the future of its turn completes`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "More"))
        val thread = thread(http)

        val second = thread.send("Hello").thenCompose { thread.send("Again") }.join()

        assertThat(second.id()).isEqualTo("msg_b")
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a turn that fails before its reply starts takes its messages back`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                400 to errorBody("invalid_request_error"),
                reply("msg_b", "More", streaming),
                500 to errorBody("api_error"),
                Failure(AnthropicIoException("Connection reset")),
                reply("msg_c", "Bye", streaming),
            )
        val thread = thread(http)
        thread.turn(streaming, "Hello")

        assertThatThrownBy { thread.turn(streaming, "Bad") }
            .hasCauseInstanceOf(BadRequestException::class.java)
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.turn(streaming, "Good")
        assertThatThrownBy { thread.turn(streaming, "Again") }
            .hasCauseInstanceOf(InternalServerException::class.java)
        assertThatThrownBy { thread.turn(streaming, "Again") }
            .hasCauseInstanceOf(AnthropicIoException::class.java)
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
                500 to errorBody("api_error"),
                reply("msg_b", "More", streaming),
            )
        val thread = thread(http)
        thread.turn(streaming, "Hello")

        thread.append(userParam("Appended"))
        assertThatThrownBy { thread.turn(streaming, "Sent") }
            .hasCauseInstanceOf(InternalServerException::class.java)
        thread.turn(streaming)

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[2]["messages"]).isEqualTo(json("""[${user("Appended")}]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a refused turn is not kept, and the next turn starts a new thread`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                reply("msg_b", "No", streaming, "refusal"),
                reply("msg_c", "Hi again", streaming),
            )
        val thread = thread(http) { system("Be brief.") }
        thread.turn(streaming, "Hello")

        thread.append(userParam("Appended"))
        thread.turn(streaming, "Refused")
        assertThat(thread.previousMessageId()).isEmpty
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        thread.turn(streaming, "Again")

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Appended")},${user("Again")}]"""))
        assertThat(bodies[2]["system"]).isEqualTo(bodies[0]["system"])
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
        thread.send("Hello").join()

        thread.sendStreaming("Refused").drain()
        assertThat(thread.lastMessage().getOrNull()?.id()).isEqualTo("msg_a")
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("Again").join()

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
                message("msg_c", "More"),
            )
        val thread = thread(http)
        thread.send("Hello").join()

        // The executor is asked to read the stream once the response has arrived, and never does.
        val arrived = CountDownLatch(1)
        val stream = thread.sendStreaming("Closed").subscribe({}) { arrived.countDown() }
        arrived.await()
        stream.close()
        thread.sendStreaming("Cut off").drain()
        assertThat(wire(thread.messages())).isEqualTo(json("""[$HELLO,${assistant("Hi")}]"""))
        thread.send("Again").join()

        assertThat(http.jsonBodies[3]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[3]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
    }

    @Test
    fun `a reply that cannot be recorded ends its turn`() {
        val http =
            FakeHttpClient(
                """{"id":"msg_a","type":"message","role":"assistant"}""",
                message("msg_b", "Hi"),
            )
        val thread = thread(http)

        assertThatThrownBy { thread.send("Hello").join() }
            .hasCauseInstanceOf(AnthropicInvalidDataException::class.java)
        thread.send().join()

        assertThat(http.jsonBodies[1]).isEqualTo(http.jsonBodies[0])
        assertThat(thread.previousMessageId()).hasValue("msg_b")
    }

    @Test
    fun `a streamed reply that cannot be recorded ends its turn`() {
        val withoutId = start("msg_a").replace(""""id":"msg_a",""", "")
        val http =
            FakeHttpClient(sse(withoutId, *textBlock(0, "Hi"), *stop()), message("msg_b", "More"))
        val thread = thread(http)

        assertThatThrownBy { thread.sendStreaming("Hello").drain() }
            .hasCauseInstanceOf(AnthropicInvalidDataException::class.java)
        thread.send("Again").join()

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(thread.previousMessageId()).hasValue("msg_b")
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a continue rejected with a replay code is sent again as a create`(streaming: Boolean) {
        for (code in
            listOf("thread_not_found", "thread_already_continued", "thread_continue_in_flight")) {
            val http =
                FakeHttpClient(
                    reply("msg_a", "Hi", streaming),
                    (if (code == "thread_not_found") 404 else 409) to threadError(code),
                    reply("msg_b", "More", streaming),
                )
            val thread = thread(http) { system("Be brief.") }
            thread.turn(streaming, "Hello")

            thread.turn(streaming, "Again")

            val bodies = http.jsonBodies
            assertThat(bodies[1]["thread"]["type"].asText()).isEqualTo("continue")
            assertThat(bodies[2]["thread"]).isEqualTo(CREATE)
            assertThat(bodies[2]["messages"])
                .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
            assertThat(bodies[2]["system"].asText()).isEqualTo("Be brief.")
            assertThat(thread.previousMessageId()).hasValue("msg_b")
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a replay sends the recorded messages without their cache breakpoints`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                404 to threadError("thread_not_found"),
                reply("msg_b", "Hi again", streaming),
            )
        val thread = thread(http)
        val sent = listOf(marked("Hello"), marked("Again"))
        thread.turn(streaming, listOf(sent[0]))

        thread.turn(streaming, listOf(sent[1]))

        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"])
            .isEqualTo(json("""[${unmarked("Hello")},${assistant("Hi")},${wire(sent[1])}]"""))
        assertThat(thread.messages().filter { it.isUser() }).isEqualTo(sent)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a reply from a fallback model starts a new thread and changes no field`(
        streaming: Boolean
    ) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming, usage = fallbackUsage("claude-opus-4-5")),
                reply("msg_b", "Hi again", streaming),
                reply("msg_c", "Bye", streaming),
            )
        val thread = thread(http) { fallbacksDefault() }
        val given = thread.params()

        thread.turn(streaming, "Hello")
        assertThat(thread.params().toBuilder().messages(emptyList()).build()).isEqualTo(given)
        thread.turn(streaming, "Again")
        thread.turn(streaming, "Bye")

        val (first, create, next) = http.jsonBodies
        assertThat(create["thread"]).isEqualTo(CREATE)
        assertThat(create["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
        assertThat(next["thread"]).isEqualTo(continues("msg_b"))
        for (request in listOf(first, create, next)) {
            assertThat(request["model"].asText()).isEqualTo("claude-sonnet-4-5")
            assertThat(request["fallbacks"].asText()).isEqualTo("default")
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a thread that is gone again right after a replay stops threading when asked to`(
        streaming: Boolean
    ) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                404 to threadError("thread_not_found"),
                reply("msg_b", "Hi again", streaming),
                404 to threadError("thread_not_found"),
                reply("msg_c", "Bye", streaming),
                reply("msg_d", "Gone", streaming),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)

        listOf("Hello", "Again", "Bye", "Gone").forEach { thread.turn(streaming, it) }

        assertThat(http.jsonBodies.map { it["thread"]?.get("type")?.asText() })
            .containsExactly("create", "continue", "create", "continue", null, null)
        assertThat(http.jsonBodies[4]["messages"]).hasSize(5)
        assertThat(http.jsonBodies[5]["messages"]).hasSize(7)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `an unsupported request continues without a thread when asked to`(streaming: Boolean) {
        val http =
            FakeHttpClient(
                reply("msg_a", "Hi", streaming),
                400 to threadError("thread_unsupported_request"),
                reply("msg_b", "More", streaming),
                reply("msg_c", "Bye", streaming),
            )
        val thread = thread(http, UnsupportedRequestBehavior.CONTINUE_UNTHREADED)
        thread.turn(streaming, "Hello")

        thread.turn(streaming, "Again")
        thread.turn(streaming, "Bye")

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]["type"].asText()).isEqualTo("continue")
        for (index in 2..3) {
            assertThat(bodies[index].has("thread")).isFalse
            assertThat(betas(http, index)).isEmpty()
        }
        assertThat(bodies[2]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("Again")}]"""))
        assertThat(bodies[3]["messages"]).hasSize(5)
        assertThat(thread.previousMessageId()).isEmpty
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a changed system is refused by the call, and takes the turn back`(streaming: Boolean) {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "More"))
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello").join()

        thread.update { it.system("Be long.") }
        val refused = assertThrows<IllegalStateException> { thread.turn(streaming, "Again") }

        assertThat(refused).hasMessage(refusal(listOf("system")))
        assertThat(http.requests).hasSize(1)
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        thread.update { it.system("Be brief.") }
        thread.send("More").join()
        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("More")}]"""))
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a system message that removes a tool goes out on the turn that continues the thread`(
        streaming: Boolean
    ) {
        val http =
            FakeHttpClient(reply("msg_a", "Hi", streaming), reply("msg_b", "More", streaming))
        val thread = thread(http) { addTool(weatherTool()) }
        thread.turn(streaming, "Hello")

        thread.append(weatherToolRemoval())
        thread.turn(streaming, "Again")

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"])
            .isEqualTo(json("""[$WEATHER_TOOL_REMOVAL,${user("Again")}]"""))
        assertThat(http.jsonBodies[1].has("tools")).isFalse
        assertThat(wire(thread.messages())[2]).isEqualTo(json(WEATHER_TOOL_REMOVAL))
    }

    @Test
    fun `a fork leaves the original as it was`() {
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                message("msg_b", "Forked"),
                message("msg_c", "Original"),
            )
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello").join()

        val fork = thread.fork()
        assertThat(fork.previousMessageId()).isEmpty
        assertThat(wire(fork.messages())).isEqualTo(wire(thread.messages()))
        fork.send("On the fork").join()
        assertThat(thread.previousMessageId()).hasValue("msg_a")
        thread.send("On the original").join()

        val bodies = http.jsonBodies
        assertThat(bodies[1]["thread"]).isEqualTo(CREATE)
        assertThat(bodies[1]["system"].asText()).isEqualTo("Be brief.")
        assertThat(bodies[1]["messages"])
            .isEqualTo(json("""[$HELLO,${assistant("Hi")},${user("On the fork")}]"""))
        assertThat(bodies[2]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(bodies[2]["messages"]).isEqualTo(json("""[${user("On the original")}]"""))
    }

    @Test
    fun `the params rebuild a thread that starts with a create`() {
        val http = FakeHttpClient(message("msg_a", "Hi"), message("msg_b", "Hi again"))
        val thread = thread(http) { system("Be brief.") }
        thread.send("Hello").join()
        thread.append(userParam("Again"))

        client(http).beta().messages().thread(thread.params()).send().join()

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
        val outcomes = listOf(rebuilt, thread.fork()).map { outcome(it.send("Hello")) { id() } }

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
        val outcomes = listOf(rebuilt, thread.fork()).map { outcome(it.send("Hello")) { id() } }

        assertFollows(behavior, outcomes, http)
        assertThat(thread.params().unsupportedRequestBehavior()).isEqualTo(behavior)
        assertThat(rebuilt.params().unsupportedRequestBehavior()).isEqualTo(behavior)
    }

    @ParameterizedTest
    @ValueSource(booleans = [false, true])
    fun `a stream that ends or is closed early records the part of the reply that arrived`(
        closed: Boolean
    ) {
        val whole = arrayOf(start("msg_b"), *textBlock(0, "One"), *textBlock(1, "Two"), *stop())
        val http =
            FakeHttpClient(
                message("msg_a", "Hi"),
                if (closed) sse(*whole) else sse(*whole.take(4).toTypedArray()),
                message("msg_c", "With the part"),
                sse(*whole.take(4).toTypedArray()),
                message("msg_d", "Again"),
            )
        val thread = thread(http)
        thread.send("Hello").join()

        val stream = thread.sendStreaming("Again")
        stream
            .subscribe { if (closed && it.isContentBlockStop()) stream.close() }
            .onCompleteFuture()
            .join()

        assertThat(thread.previousMessageId()).isEmpty
        assertThat(thread.lastMessage().get().id()).isEqualTo("msg_b")
        assertThat(thread.lastMessage().get().stopReason()).isEmpty
        val history = """$HELLO,${assistant("Hi")},${user("Again")},${assistant("One")}"""
        assertThat(wire(thread.messages())).isEqualTo(json("[$history]"))

        // A message after the part sends the conversation with the part.
        thread.send("More").join()
        assertThat(http.jsonBodies[2]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[2]["messages"]).isEqualTo(json("""[$history,${user("More")}]"""))

        // No message after the part sends the turn again, in place of the part.
        thread.sendStreaming("Next").drain()
        thread.send().join()
        assertThat(http.jsonBodies[4]["thread"]).isEqualTo(CREATE)
        assertThat(http.jsonBodies[4]["messages"].last()).isEqualTo(json(user("Next")))
        assertThat(thread.previousMessageId()).hasValue("msg_d")
    }

    @Test
    fun `a turn is refused while a future or a stream is open, and stages nothing`() {
        val release = CountDownLatch(1)
        val http =
            FakeHttpClient(
                {
                    release.await()
                    message("msg_a", "Hi")
                },
                sse(start("msg_b"), *textBlock(0, "More"), *stop()),
                message("msg_c", "Bye"),
            )
        val thread = thread(http)

        val first = thread.send("Hello")
        assertThrows<IllegalStateException> { thread.send("Too early") }
        assertThrows<IllegalStateException> { thread.sendStreaming("Too early") }
        release.countDown()
        first.join()
        val stream = thread.sendStreaming("Again")
        assertThrows<IllegalStateException> { thread.send("Too early") }
        assertThrows<IllegalStateException> { thread.sendStreaming("Too early") }
        stream.drain()
        thread.send("Bye").join()

        assertThat(http.jsonBodies.map { it["messages"] })
            .containsExactly(
                json("""[$HELLO]"""),
                json("""[${user("Again")}]"""),
                json("""[${user("Bye")}]"""),
            )
    }

    @Test
    fun `a future that the caller cancels leaves the turn to end when its response arrives`() {
        val release = CountDownLatch(1)
        val http =
            FakeHttpClient(
                {
                    release.await()
                    message("msg_a", "Hi")
                },
                message("msg_b", "More"),
            )
        val thread = thread(http)

        thread.send("Hello").cancel(true)
        assertThrows<IllegalStateException> { thread.send("Too early") }
        release.countDown()
        assertTimeoutPreemptively(Duration.ofSeconds(30)) {
            while (!thread.previousMessageId().isPresent) {
                Thread.sleep(10)
            }
        }
        thread.send("Again").join()

        assertThat(http.jsonBodies[1]["thread"]).isEqualTo(continues("msg_a"))
        assertThat(http.jsonBodies[1]["messages"]).isEqualTo(json("""[${user("Again")}]"""))
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
            structured(http) {
                outputConfig(BetaOutputConfig.builder().effort(BetaOutputConfig.Effort.LOW).build())
            }

        val first = thread.send { it.addUserMessage("Go").maxTokens(16) }.join()
        val second = thread.send("Again").join()
        thread.sendStreaming("Bye").drain()
        val streamed = thread.lastMessage().get()
        thread.update { it.maxTokens(2048) }
        thread.append(userParam("Go on"))
        val replayed = thread.send().join()

        assertThat(listOf(first, second, streamed, replayed).map { cityOf(it) })
            .containsExactly("Paris", "Rome", "Oslo", "Bern")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"]["type"].asText() })
            .containsExactly("create", "continue", "continue", "continue", "create")
        assertThat(bodies.map { it["output_config"] })
            .containsOnly(json("""{"effort":"low","format":$CITY_FORMAT}"""))
        assertThat(bodies.indices.map { betas(http, it) }).containsOnly(listOf(THREADS_BETA))
        assertThat(thread.previousMessageId()).hasValue("msg_d")
        assertThat(thread.messages()).hasSize(8)
    }

    @Test
    fun `the params and a fork of a thread of a class keep the schema`() {
        val http =
            FakeHttpClient(
                message("msg_a", city("Paris")),
                message("msg_b", city("Rome")),
                message("msg_c", city("Oslo")),
                message("msg_d", city("Bern")),
            )
        val thread = structured(http)
        thread.send("Go").join()

        val params = thread.params()
        val messages = client(http).beta().messages()
        messages.thread(params.rawParams).send("Again").join()
        val structured = messages.thread(params).send("Again").join()
        val fork = thread.fork()
        assertThat(cityOf(fork.lastMessage().get())).isEqualTo("Paris")
        val forked = fork.send("Again").join()

        assertThat(listOf(structured, forked).map { cityOf(it) }).containsExactly("Oslo", "Bern")
        val bodies = http.jsonBodies
        assertThat(bodies.map { it["thread"] }).containsOnly(CREATE)
        assertThat(bodies.map { it["output_config"] })
            .containsOnly(json("""{"format":$CITY_FORMAT}"""))
        assertThat(bodies.drop(2)).containsOnly(bodies[1])
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
    fun `a thread is refused on a client whose service takes no threads, and on no other`() {
        val http = FakeHttpClient()
        val params = ThreadCreateParams.builder().model("claude-sonnet-4-5").maxTokens(1024).build()
        val ownService = mock<MessageServiceAsync>()
        whenever(ownService.thread(any<ThreadCreateParams>())).thenCallRealMethod()
        whenever(ownService.thread(any<ThreadCreateParams>(), any())).thenCallRealMethod()
        whenever(ownService.create(any<MessageCreateParams>(), any()))
            .thenReturn(answer(message("msg_a", "Hi")))

        assertThrows<AnthropicException> {
            client(http) { backend(UNTHREADED) }.beta().messages().thread(params)
        }
        ownService.thread(params).send("Hello").join()

        assertThat(http.requests).isEmpty()
        val sent = argumentCaptor<MessageCreateParams>()
        verify(ownService).create(sent.capture(), any())
        assertThat(sent.firstValue.thread().get().isCreate()).isTrue
    }

    private fun thread(
        http: FakeHttpClient,
        behavior: UnsupportedRequestBehavior = UnsupportedRequestBehavior.ERROR,
        params: ThreadCreateParams.Builder.() -> Unit = {},
    ): BetaMessageThreadAsync =
        client(http)
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

    private fun structured(
        http: FakeHttpClient,
        params: ThreadCreateParams.Builder.() -> Unit = {},
    ): StructuredBetaMessageThreadAsync<City> =
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

    private fun client(
        http: FakeHttpClient,
        configure: ClientOptions.Builder.() -> Unit = {},
    ): AnthropicClientAsyncImpl =
        AnthropicClientAsyncImpl(
            ClientOptions.builder()
                .httpClient(http)
                .baseUrl("https://api.example.test")
                .maxRetries(0)
                .apply(configure)
                .build()
        )

    /** What [read] makes of the reply of [turn], or the error that the turn failed with. */
    private fun <T> outcome(turn: CompletableFuture<T>, read: T.() -> String): Result<String> =
        runCatching { turn.join().read() }.recoverCatching { throw it.cause ?: it }

    private fun answer(body: String): CompletableFuture<BetaMessage> =
        CompletableFuture.completedFuture(jsonMapper().readValue(body, BetaMessage::class.java))

    /** Sends one turn, with a user message of [text] if given, and waits for it to end. */
    private fun BetaMessageThreadAsync.turn(streaming: Boolean, text: String? = null) =
        turn(streaming, listOfNotNull(text?.let { userParam(it) }))

    private fun BetaMessageThreadAsync.turn(streaming: Boolean, messages: List<BetaMessageParam>) {
        if (streaming) sendStreaming(messages).drain() else send(messages).join()
    }

    private fun AsyncStreamResponse<BetaRawMessageStreamEvent>.drain():
        List<BetaRawMessageStreamEvent> {
        val events = CopyOnWriteArrayList<BetaRawMessageStreamEvent>()
        subscribe { events.add(it) }.onCompleteFuture().join()
        return events
    }
}
