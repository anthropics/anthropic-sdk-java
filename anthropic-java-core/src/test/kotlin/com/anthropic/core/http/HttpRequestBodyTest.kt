package com.anthropic.core.http

import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.JsonNode
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class HttpRequestBodyTest {

    @Test
    fun jsonFactory_serializesValue() {
        val body = HttpRequestBody.ofJson(mapOf("hello" to "world"))

        assertThat(body.contentType()).isEqualTo("application/json")
        assertThat(body.repeatable()).isTrue()
        assertThat(writeToString(body)).isEqualTo("""{"hello":"world"}""")
    }

    @Test
    fun json_parsesBody() {
        val body = FakeHttpRequestBody("""{"hello":"world"}""", repeatable = true)

        val json = body.json(JsonNode::class.java)

        assertThat(json.path("hello").asText()).isEqualTo("world")
    }

    @Test
    fun json_whenBodyIsNotJson_throws() {
        val body = FakeHttpRequestBody("not json", repeatable = true)

        assertThatThrownBy { body.json(JsonNode::class.java) }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
    }

    @Test
    fun buffered_whenRepeatable_returnsSameBody() {
        val body = FakeHttpRequestBody("hello", repeatable = true)

        assertThat(body.buffered()).isSameAs(body)
    }

    @Test
    fun buffered_isWrittenRepeatedly() {
        val body = FakeHttpRequestBody("hello", repeatable = false)

        val buffered = body.buffered()

        assertThat(buffered.repeatable()).isTrue()
        assertThat(writeToString(buffered)).isEqualTo("hello")
        assertThat(writeToString(buffered)).isEqualTo("hello")
    }

    @Test
    fun buffered_writesBodyLazilyAndOnce() {
        val body = FakeHttpRequestBody("hello", repeatable = false)

        val buffered = body.buffered()

        assertThat(body.writeToCalls).isEqualTo(0)
        writeToString(buffered)
        writeToString(buffered)
        assertThat(body.writeToCalls).isEqualTo(1)
    }

    @Test
    fun buffered_whenContentLengthIsKnown_returnsContentLength() {
        val body = FakeHttpRequestBody("hello", repeatable = false, length = 5L)

        val buffered = body.buffered()

        assertThat(buffered.contentLength()).isEqualTo(5L)
        assertThat(body.writeToCalls).isEqualTo(0)
    }

    @Test
    fun buffered_whenContentLengthIsUnknown_returnsBufferedLength() {
        val body = FakeHttpRequestBody("hello", repeatable = false, length = -1L)

        assertThat(body.buffered().contentLength()).isEqualTo(5L)
    }

    @Test
    fun buffered_closeClosesOriginalBody() {
        val body = FakeHttpRequestBody("hello", repeatable = false)

        body.buffered().close()

        assertThat(body.closed).isTrue()
    }

    private fun writeToString(body: HttpRequestBody): String {
        val outputStream = ByteArrayOutputStream()
        body.writeTo(outputStream)
        return outputStream.toByteArray().decodeToString()
    }

    private class FakeHttpRequestBody(
        private val bodyText: String,
        private val repeatable: Boolean,
        private val length: Long = bodyText.length.toLong(),
    ) : HttpRequestBody {
        var writeToCalls = 0
        var closed = false

        override fun writeTo(outputStream: OutputStream) {
            writeToCalls++
            outputStream.write(bodyText.toByteArray())
        }

        override fun contentType(): String = "application/json"

        override fun contentLength(): Long = length

        override fun repeatable(): Boolean = repeatable

        override fun close() {
            closed = true
        }
    }
}
