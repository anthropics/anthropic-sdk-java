package com.anthropic.core.handlers

import com.anthropic.core.JsonValue
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.SseMessage
import com.anthropic.core.jsonMapper
import com.anthropic.errors.SseException
import java.io.FilterInputStream
import java.io.InputStream
import java.util.stream.Collectors.toList
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource

internal class SseHandlerTest {

    enum class TestCase(
        internal val body: String,
        internal val expectedMessages: List<SseMessage>? = null,
        internal val expectedException: Exception? = null,
    ) {
        EVENT_AND_DATA(
            buildString {
                append("event: completion\n")
                append("data: {\"foo\":true}\n")
                append("\n")
            },
            listOf(sseMessageBuilder().event("completion").data("{\"foo\":true}").build()),
        ),
        EVENT_MISSING_DATA(
            buildString {
                append("event: completion\n")
                append("\n")
            },
            listOf(sseMessageBuilder().event("completion").build()),
        ),
        MULTIPLE_EVENTS_AND_DATA(
            buildString {
                append("event: completion\n")
                append("data: {\"foo\":true}\n")
                append("\n")
                append("event: message_start\n")
                append("data: {\"bar\":false}\n")
                append("\n")
            },
            listOf(
                sseMessageBuilder().event("completion").data("{\"foo\":true}").build(),
                sseMessageBuilder().event("message_start").data("{\"bar\":false}").build(),
            ),
        ),
        MULTIPLE_EVENTS_MISSING_DATA(
            buildString {
                append("event: completion\n")
                append("\n")
                append("event: message_start\n")
                append("\n")
            },
            listOf(
                sseMessageBuilder().event("completion").build(),
                sseMessageBuilder().event("message_start").build(),
            ),
        ),
        DATA_JSON_ESCAPED_DOUBLE_NEW_LINE(
            buildString {
                append("event: completion\n")
                append("data: {\n")
                append("data: \"foo\":\n")
                append("data: true}\n")
                append("\n\n")
            },
            listOf(sseMessageBuilder().event("completion").data("{\n\"foo\":\ntrue}").build()),
        ),
        MULTIPLE_DATA_LINES(
            buildString {
                append("event: completion\n")
                append("data: {\n")
                append("data: \"foo\":\n")
                append("data: true}\n")
                append("\n\n")
            },
            listOf(sseMessageBuilder().event("completion").data("{\n\"foo\":\ntrue}").build()),
        ),
        SPECIAL_NEW_LINE_CHARACTER(
            buildString {
                append("event: completion\n")
                append("data: {\"content\":\" culpa\"}\n")
                append("\n")
                append("event: message_start\n")
                append("data: {\"content\":\" \u2028\"}\n")
                append("\n")
                append("event: completion\n")
                append("data: {\"content\":\"foo\"}\n")
                append("\n")
            },
            listOf(
                sseMessageBuilder().event("completion").data("{\"content\":\" culpa\"}").build(),
                sseMessageBuilder()
                    .event("message_start")
                    .data("{\"content\":\" \u2028\"}")
                    .build(),
                sseMessageBuilder().event("completion").data("{\"content\":\"foo\"}").build(),
            ),
        ),
        MULTI_BYTE_CHARACTER(
            buildString {
                append("event: completion\n")
                append("data: {\"content\":\"\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u0438\"}\n")
                append("\n")
            },
            listOf(
                sseMessageBuilder().event("completion").data("{\"content\":\"известни\"}").build()
            ),
        ),
        ERROR_EVENT(
            buildString {
                append("event: error\n")
                append("data: {\"errorProperty\":\"42\"}\n")
                append("\n")
            },
            expectedException =
                SseException.builder()
                    .statusCode(0)
                    .headers(Headers.builder().build())
                    .body(JsonValue.from(mapOf("errorProperty" to "42")))
                    .build(),
        ),
    }

    @ParameterizedTest
    @EnumSource
    fun handle(testCase: TestCase) {
        val response = httpResponse(testCase.body)
        var messages: List<SseMessage>? = null
        var exception: Exception? = null

        try {
            messages =
                sseHandler(jsonMapper()).handle(response).use { it.stream().collect(toList()) }
        } catch (e: Exception) {
            exception = e
        }

        if (testCase.expectedMessages != null) {
            assertThat(messages).containsExactlyElementsOf(testCase.expectedMessages)
        }
        if (testCase.expectedException != null) {
            assertThat(exception).isInstanceOf(testCase.expectedException.javaClass)
            assertThat(exception).hasMessage(testCase.expectedException.message)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["\n", "\r\n", "\r"])
    fun leadingBom_doesNotDropTheFirstNamedEvent(newline: String) {
        val wire =
            "\uFEFFevent: message_start${newline}data: {\"id\":\"msg_1\"}${newline}${newline}" +
                "event: message_stop${newline}data: {}${newline}${newline}"
        val decoded =
            sseHandler(jsonMapper()).handle(fragmentedResponse(wire)).use {
                it.stream().collect(toList())
            }
        assertThat(decoded.map { it.event }).containsExactly("message_start", "message_stop")
        assertThat(decoded[0].data).isEqualTo("{\"id\":\"msg_1\"}")
    }

    @Test
    fun leadingBom_onDataLineAndInsidePayload_areDistinguished() {
        val wire = "\uFEFFdata: {\"text\":\"x\uFEFFy\"}\nevent: completion\n\n"
        val decoded =
            sseHandler(jsonMapper()).handle(fragmentedResponse(wire)).use {
                it.stream().collect(toList())
            }
        assertThat(decoded).hasSize(1)
        assertThat(decoded[0].data).isEqualTo("{\"text\":\"x\uFEFFy\"}")
    }

    @Test
    fun leadingBom_doesNotHideServerError() {
        val wire =
            "\uFEFFevent: error\ndata: {\"error\":{\"type\":\"overloaded_error\",\"message\":\"retry\"}}\n\n"
        val error = catchThrowable {
            sseHandler(jsonMapper()).handle(fragmentedResponse(wire)).use {
                it.stream().collect(toList())
            }
        }
        assertThat(error).isInstanceOf(SseException::class.java)
    }

    @Test
    fun rawParser_recognizesBomButKeepsOriginalWireLines() {
        val firstLine = "\uFEFFevent: future_event"
        val wire = "$firstLine\ndata: {\"future\":true}\n\n"
        val messages =
            rawSseHandler(jsonMapper()).handle(fragmentedResponse(wire)).use {
                it.stream().collect(toList())
            }
        assertThat(messages).hasSize(1)
        assertThat(messages[0].event).isEqualTo("future_event")
        assertThat(messages[0].data).isEqualTo("{\"future\":true}")
        assertThat(messages[0].rawLines).containsExactly(firstLine, "data: {\"future\":true}")
    }

    @Test
    fun bomIsStrippedOnlyAtTheStartOfAStream() {
        for (prefix in listOf("\n", "\uFEFF", ": comment\n")) {
            val wire =
                "${prefix}\uFEFFevent: completion\ndata: {}\n\n" +
                    "event: message_stop\ndata: {}\n\n"
            val messages =
                sseHandler(jsonMapper()).handle(fragmentedResponse(wire)).use {
                    it.stream().collect(toList())
                }
            assertThat(messages.map { it.event }).containsExactly("message_stop")
        }
    }

    private fun fragmentedResponse(wire: String): HttpResponse =
        object : HttpResponse {
            private val input =
                object : FilterInputStream(wire.toByteArray(Charsets.UTF_8).inputStream()) {
                    override fun read(bytes: ByteArray, offset: Int, length: Int): Int =
                        super.read(bytes, offset, minOf(length, 1))
                }

            override fun statusCode(): Int = 200

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream = input

            override fun close() = input.close()
        }

    @Test
    fun cannotReuseStream() {
        val response = httpResponse("body")
        val streamResponse = sseHandler(jsonMapper()).handle(response)

        val throwable =
            streamResponse.use {
                it.stream().collect(toList())
                catchThrowable { it.stream().collect(toList()) }
            }

        assertThat(throwable).isInstanceOf(IllegalStateException::class.java)
    }
}

private fun httpResponse(body: String): HttpResponse =
    object : HttpResponse {
        override fun statusCode(): Int = 0

        override fun headers(): Headers = Headers.builder().build()

        override fun body(): InputStream = body.toByteArray().inputStream()

        override fun close() {}
    }

private fun sseMessageBuilder() = SseMessage.builder().jsonMapper(jsonMapper())
