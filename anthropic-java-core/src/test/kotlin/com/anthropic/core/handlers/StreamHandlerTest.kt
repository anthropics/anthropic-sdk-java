package com.anthropic.core.handlers

import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicIoException
import java.io.IOException
import java.io.InputStream
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

internal class StreamHandlerTest {

    @Test
    fun streamHandler_splitsStreamOnNewlines() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines = runBlocking { streamResponse.stream().toList() }

        assertThat(lines).containsExactly("a", "bb", "ccc", "dddd")
    }

    @Test
    fun streamHandler_whenClosedEarly_stopsYielding() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines = runBlocking {
            streamResponse
                .stream()
                .onEach {
                    if (it == "bb") {
                        streamResponse.close()
                    }
                }
                .toList()
        }

        assertThat(lines).containsExactly("a", "bb")
    }

    @Test
    fun streamHandler_whenReaderThrowsIOException_wrapsException() {
        val handler = streamHandler<String> { _, lines -> lines.forEach {} }
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n".byteInputStream().throwing()))

        val e = assertThrows<AnthropicIoException> { runBlocking { streamResponse.stream().collect {} } }
        assertThat(e).hasMessage("Stream failed")
        assertThat(e).hasCauseInstanceOf(IOException::class.java)
    }

    @Test
    fun streamHandler_whenBlockThrowsIOException_doesNotWrapException() {
        val ioException = IOException("BOOM!")
        val handler =
            streamHandler<String> { _, lines ->
                lines.forEachIndexed { index, _ ->
                    if (index == 2) {
                        throw ioException
                    }
                }
            }
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n".byteInputStream()))

        val e = assertThrows<IOException> { runBlocking { streamResponse.stream().collect {} } }
        assertThat(e).isSameAs(ioException)
    }

    private fun httpResponse(body: InputStream): HttpResponse =
        object : HttpResponse {

            override fun statusCode(): Int = 0

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream = body

            override fun close() {}
        }

    private fun InputStream.throwing(): InputStream =
        object : InputStream() {

            override fun read(): Int {
                val byte = this@throwing.read()
                if (byte == -1) {
                    throw IOException("BOOM!")
                }
                return byte
            }
        }
}
