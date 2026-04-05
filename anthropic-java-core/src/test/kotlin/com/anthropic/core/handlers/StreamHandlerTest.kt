package kotlinx.kmp.util.core.handlers

import kotlinx.kmp.util.core.http.Headers
import kotlinx.kmp.util.core.http.HttpResponse
import kotlinx.kmp.util.core.errors.AnthropicIoException
import java.io.IOException
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okio.Buffer
import okio.BufferedSource
import okio.Source
import okio.Timeout
import okio.buffer
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

internal class StreamHandlerTest {

    @Test
    fun streamHandler_splitsStreamOnNewlines() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd"))

        val lines = runBlocking { streamResponse.stream().toList() }

        assertThat(lines).containsExactly("a", "bb", "ccc", "dddd")
    }

    @Test
    fun streamHandler_whenClosedEarly_stopsYielding() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd"))

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
        val streamResponse = handler.handle(httpResponseThrowing("a\nb\nc\n"))

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
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n"))

        val e = assertThrows<IOException> { runBlocking { streamResponse.stream().collect {} } }
        assertThat(e).isSameAs(ioException)
    }

    private fun httpResponse(body: String): HttpResponse =
        object : HttpResponse {
            override fun statusCode(): Int = 0
            override fun headers(): Headers = Headers.builder().build()
            override fun body(): BufferedSource = Buffer().writeUtf8(body)
            override fun close() {}
        }

    /** Creates an HttpResponse whose body throws IOException at EOF. */
    private fun httpResponseThrowing(body: String): HttpResponse {
        val bytes = body.toByteArray()
        val throwingSource = object : Source {
            private var offset = 0
            override fun read(sink: Buffer, byteCount: Long): Long {
                if (offset >= bytes.size) throw IOException("BOOM!")
                val toRead = minOf(byteCount.toInt(), bytes.size - offset)
                sink.write(bytes, offset, toRead)
                offset += toRead
                return toRead.toLong()
            }
            override fun timeout() = Timeout.NONE
            override fun close() {}
        }
        return object : HttpResponse {
            override fun statusCode(): Int = 0
            override fun headers(): Headers = Headers.builder().build()
            override fun body(): BufferedSource = throwingSource.buffer()
            override fun close() {}
        }
    }
}
