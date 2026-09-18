package com.anthropic.core.handlers

import com.anthropic.backends.Backend
import com.anthropic.client.okhttp.OkHttpClient
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.SseMessage
import com.anthropic.core.http.toAsync
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicIoException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URI
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread
import kotlin.streams.asSequence
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows

internal class StreamHandlerTest {

    @Test
    fun streamHandler_splitsStreamOnNewlines() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines = streamResponse.stream().asSequence().toList()

        assertThat(lines).containsExactly("a", "bb", "ccc", "dddd")
    }

    @Test
    fun streamHandler_whenClosedEarly_stopsYielding() {
        val handler = streamHandler { _, lines -> yieldAll(lines) }
        val streamResponse = handler.handle(httpResponse("a\nbb\nccc\ndddd".byteInputStream()))

        val lines =
            streamResponse
                .stream()
                .asSequence()
                .onEach {
                    if (it == "bb") {
                        streamResponse.close()
                    }
                }
                .toList()

        assertThat(lines).containsExactly("a", "bb")
    }

    @Test
    fun streamHandler_whenClosedFromAnotherThreadWhileReading_endsStreamWithoutError() {
        withHangingSseResponse { server, response, executor ->
            val streamResponse = sseHandler(jsonMapper()).handle(response)
            val firstEvent = CountDownLatch(1)
            val streamError = AtomicReference<Optional<Throwable>>()
            val reader =
                thread(isDaemon = true) {
                    streamError.set(
                        try {
                            streamResponse.stream().forEach { firstEvent.countDown() }
                            Optional.empty()
                        } catch (e: Throwable) {
                            Optional.of(e)
                        }
                    )
                }
            assertThat(firstEvent.await(10, TimeUnit.SECONDS)).isTrue()
            awaitBlockedInSocketRead(reader)

            CompletableFuture.runAsync(streamResponse::close, executor).get(10, TimeUnit.SECONDS)

            reader.join(TimeUnit.SECONDS.toMillis(10))
            assertThat(reader.isAlive).isFalse()
            assertThat(streamError.get()).isEmpty()
            assertThat(server.awaitClientDisconnect()).isTrue()
        }
    }

    @Test
    fun streamHandler_whenAsyncResponseClosedFromAnotherThreadWhileReading_completesWithoutError() {
        withHangingSseResponse { server, response, executor ->
            val asyncStreamResponse =
                CompletableFuture.completedFuture(sseHandler(jsonMapper()).handle(response))
                    .toAsync(executor)
            val handlerThread = AtomicReference<Thread>()
            val firstEvent = CountDownLatch(1)
            val completion = CompletableFuture<Optional<Throwable>>()
            asyncStreamResponse.subscribe(
                object : AsyncStreamResponse.Handler<SseMessage> {
                    override fun onNext(value: SseMessage) {
                        handlerThread.set(Thread.currentThread())
                        firstEvent.countDown()
                    }

                    override fun onComplete(error: Optional<Throwable>) {
                        completion.complete(error)
                    }
                }
            )
            assertThat(firstEvent.await(10, TimeUnit.SECONDS)).isTrue()
            awaitBlockedInSocketRead(handlerThread.get())

            CompletableFuture.runAsync(asyncStreamResponse::close, executor)
                .get(10, TimeUnit.SECONDS)

            assertThat(completion.get(10, TimeUnit.SECONDS)).isEmpty()
            assertThat(asyncStreamResponse.onCompleteFuture()).isCompleted()
            assertThat(server.awaitClientDisconnect()).isTrue()
        }
    }

    @Test
    fun streamHandler_whenReaderThrowsIOException_wrapsException() {
        val handler = streamHandler<String> { _, lines -> lines.forEach {} }
        val streamResponse = handler.handle(httpResponse("a\nb\nc\n".byteInputStream().throwing()))

        val e = assertThrows<AnthropicIoException> { streamResponse.stream().forEach {} }
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

        val e = assertThrows<IOException> { streamResponse.stream().forEach {} }
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

    /**
     * Runs [block] with a response of a fresh [HangingSseServer], and an executor for work off the
     * test thread. The response is received over a real HTTP/1.1 connection, so a close from
     * another thread can land while a reader is blocked on the socket, which a fake body can't
     * reproduce.
     */
    private fun withHangingSseResponse(
        block: (server: HangingSseServer, response: HttpResponse, executor: Executor) -> Unit
    ) {
        HangingSseServer().use { server ->
            OkHttpClient.builder().backend(TestBackend(server.baseUrl)).build().use { httpClient ->
                val response =
                    httpClient.execute(
                        HttpRequest.builder()
                            .method(HttpMethod.GET)
                            .baseUrl(server.baseUrl)
                            .addPathSegment("stream")
                            .build()
                    )
                val executor = Executors.newCachedThreadPool()
                try {
                    block(server, response, executor)
                } finally {
                    executor.shutdownNow()
                }
            }
        }
    }

    /**
     * Waits until [thread] is blocked reading the socket, so a close lands mid-read rather than
     * before it.
     */
    private fun awaitBlockedInSocketRead(thread: Thread) {
        val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(10)
        while (thread.stackTrace.none { it.className == "okio.InputStreamSource" }) {
            check(System.nanoTime() < deadline) { "Reader never blocked in a socket read" }
            Thread.sleep(5)
        }
    }

    private class TestBackend(private val baseUrl: String) : Backend {
        override fun baseUrl(): String = baseUrl

        override fun close() {}
    }

    /**
     * Serves one SSE event over chunked HTTP/1.1 and then nothing, until the client disconnects.
     */
    private class HangingSseServer : AutoCloseable {
        private val address = InetAddress.getLoopbackAddress()
        private val serverSocket = ServerSocket(0, 50, address)
        private val socket = AtomicReference<Socket>()
        private val clientDisconnected = CountDownLatch(1)

        val baseUrl: String =
            URI("http", null, address.hostAddress, serverSocket.localPort, null, null, null)
                .toString()

        init {
            thread(isDaemon = true) {
                try {
                    serverSocket.accept().use { socket ->
                        this.socket.set(socket)
                        val input = socket.getInputStream()
                        val reader = BufferedReader(InputStreamReader(input, Charsets.ISO_8859_1))
                        generateSequence(reader::readLine).takeWhile { it.isNotEmpty() }.count()
                        val event = "event: message_start\ndata: {}\n\n"
                        socket
                            .getOutputStream()
                            .apply {
                                write(
                                    ("HTTP/1.1 200 OK\r\n" +
                                            "Content-Type: text/event-stream\r\n" +
                                            "Transfer-Encoding: chunked\r\n\r\n" +
                                            "${Integer.toHexString(event.length)}\r\n" +
                                            "$event\r\n")
                                        .toByteArray(Charsets.ISO_8859_1)
                                )
                            }
                            .flush()
                        // The client sends nothing more while it reads this response, so the read
                        // only ends once the client closes the connection.
                        input.readBytes()
                    }
                } catch (_: IOException) {} finally {
                    clientDisconnected.countDown()
                }
            }
        }

        fun awaitClientDisconnect(): Boolean = clientDisconnected.await(10, TimeUnit.SECONDS)

        override fun close() {
            serverSocket.close()
            socket.get()?.close()
        }
    }
}
