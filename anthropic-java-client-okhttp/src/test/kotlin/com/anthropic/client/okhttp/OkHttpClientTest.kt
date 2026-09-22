package com.anthropic.client.okhttp

import com.anthropic.backends.Backend
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicIoException
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import java.io.BufferedReader
import java.io.DataInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.net.URI
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock

@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class OkHttpClientTest {

    private class TestBackend(
        private val baseUrl: String,
        private val onPrepareResponse: (HttpResponse) -> HttpResponse = { it },
    ) : Backend {
        override fun baseUrl(): String = baseUrl

        override fun prepareResponse(response: HttpResponse): HttpResponse =
            onPrepareResponse(response)

        override fun close() {}
    }

    private lateinit var baseUrl: String
    private lateinit var httpClient: OkHttpClient

    @BeforeEach
    fun beforeEach(wmRuntimeInfo: WireMockRuntimeInfo) {
        baseUrl = wmRuntimeInfo.httpBaseUrl
        httpClient = OkHttpClient.builder().backend(TestBackend(baseUrl)).build()
    }

    @Test
    fun executeAsync_whenFutureCancelled_cancelsUnderlyingCall() {
        stubFor(post(urlPathEqualTo("/something")).willReturn(ok()))
        val responseFuture =
            httpClient.executeAsync(
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(baseUrl)
                    .addPathSegment("something")
                    .build()
            )
        val call = httpClient.okHttpClient.dispatcher.runningCalls().single()

        responseFuture.cancel(false)

        // Should have cancelled the underlying call
        assertThat(call.isCanceled()).isTrue()
    }

    @Test
    fun close_whileAnotherThreadIsBlockedReading_unblocksTheRead() {
        withLocalHttpServer { server, client ->
            val response = client.execute(server.request("hang"))
            val body = response.body()
            DataInputStream(body).readFully(ByteArray(FIRST_CHUNK.length))
            val readResult = AtomicReference<Any?>()
            val readerDone = CountDownLatch(1)
            val reader =
                thread(isDaemon = true) {
                    readResult.set(
                        try {
                            body.read()
                        } catch (e: Throwable) {
                            e
                        }
                    )
                    readerDone.countDown()
                }
            awaitBlockedInSocketRead(reader)

            val closeStartedNanos = System.nanoTime()
            response.close()

            assertThat(System.nanoTime() - closeStartedNanos)
                .isLessThan(TimeUnit.SECONDS.toNanos(5))
            assertThat(readerDone.await(10, TimeUnit.SECONDS)).isTrue()
            assertThat(readResult.get()).isInstanceOf(IOException::class.java)
            // The canceled connection is closed rather than parked in the pool.
            assertThat(server.awaitClientDisconnect()).isTrue()
            // A second close is a no-op.
            response.close()
        }
    }

    @RepeatedTest(20)
    fun close_racingAReadThatIsStarting_neverThrowsAndTheReaderExits() {
        withLocalHttpServer { server, client ->
            val response = client.execute(server.request("hang"))
            val body = response.body()
            DataInputStream(body).readFully(ByteArray(FIRST_CHUNK.length))
            val start = CountDownLatch(1)
            val readResult = AtomicReference<Any?>()
            val reader =
                thread(isDaemon = true) {
                    start.await()
                    readResult.set(
                        try {
                            body.read()
                        } catch (e: Throwable) {
                            e
                        }
                    )
                }

            start.countDown()
            response.close()

            reader.join(TimeUnit.SECONDS.toMillis(10))
            assertThat(reader.isAlive).isFalse()
            assertThat(readResult.get()).isInstanceOf(IOException::class.java)
        }
    }

    @Test
    fun close_afterReadingTheWholeBody_reusesTheConnection() {
        withLocalHttpServer { server, client ->
            repeat(2) {
                val response = client.execute(server.request("full"))

                assertThat(response.body().readBytes().toString(Charsets.UTF_8))
                    .isEqualTo(FULL_BODY)
                response.close()
            }

            assertThat(server.acceptedConnections.get()).isEqualTo(1)
        }
    }

    @Test
    fun close_withASmallRemainderLeft_drainsItAndReusesTheConnection() {
        withLocalHttpServer { server, client ->
            val response = client.execute(server.request("large"))
            assertThat(response.body().read()).isNotEqualTo(-1)
            response.close()

            val nextResponse = client.execute(server.request("full"))

            assertThat(nextResponse.body().readBytes().toString(Charsets.UTF_8))
                .isEqualTo(FULL_BODY)
            nextResponse.close()
            assertThat(server.acceptedConnections.get()).isEqualTo(1)
        }
    }

    @Test
    fun close_beforeAnyRead_drainsTheBodyAndReusesTheConnection() {
        withLocalHttpServer { server, client ->
            val response = client.execute(server.request("large"))
            response.close()

            assertThatThrownBy { response.body().read() }.isInstanceOf(IOException::class.java)
            // A second close is a no-op.
            response.close()

            val nextResponse = client.execute(server.request("full"))

            assertThat(nextResponse.body().readBytes().toString(Charsets.UTF_8))
                .isEqualTo(FULL_BODY)
            nextResponse.close()
            assertThat(server.acceptedConnections.get()).isEqualTo(1)
        }
    }

    @Test
    fun execute_whenPrepareResponseThrows_rethrowsAndReleasesTheConnection() {
        val failure = RuntimeException("prepareResponse failed")
        withLocalHttpServer(onPrepareResponse = { throw failure }) { server, client ->
            assertThatThrownBy { client.execute(server.request("full")) }.isSameAs(failure)

            assertConnectionReleased(client)
        }
    }

    @Test
    fun execute_whenPrepareResponseThrowsIOException_wrapsItAndReleasesTheConnection() {
        val failure = IOException("prepareResponse failed")
        withLocalHttpServer(onPrepareResponse = { throw failure }) { server, client ->
            assertThatThrownBy { client.execute(server.request("full")) }
                .isInstanceOf(AnthropicIoException::class.java)
                .cause()
                .isSameAs(failure)

            assertConnectionReleased(client)
        }
    }

    @Test
    fun executeAsync_whenPrepareResponseThrows_failsTheFutureAndReleasesTheConnection() {
        val failure = RuntimeException("prepareResponse failed")
        withLocalHttpServer(onPrepareResponse = { throw failure }) { server, client ->
            val future = client.executeAsync(server.request("full"))

            assertThatThrownBy { future.get(10, TimeUnit.SECONDS) }
                .isInstanceOf(ExecutionException::class.java)
                .cause()
                .isSameAs(failure)
            assertConnectionReleased(client)
        }
    }

    @Test
    fun executeAsync_whenPrepareResponseThrowsIOException_wrapsItAndReleasesTheConnection() {
        val failure = IOException("prepareResponse failed")
        withLocalHttpServer(onPrepareResponse = { throw failure }) { server, client ->
            val future = client.executeAsync(server.request("full"))

            assertThatThrownBy { future.get(10, TimeUnit.SECONDS) }
                .isInstanceOf(ExecutionException::class.java)
                .cause()
                .isInstanceOf(AnthropicIoException::class.java)
                .cause()
                .isSameAs(failure)
            assertConnectionReleased(client)
        }
    }

    @Test
    fun executeAsync_whenCanceledWhilePreparingTheResponse_closesTheResponse() {
        val preparing = CountDownLatch(1)
        val canceled = CountDownLatch(1)
        val closed = CountDownLatch(1)
        withLocalHttpServer(
            onPrepareResponse = { response ->
                preparing.countDown()
                canceled.await(10, TimeUnit.SECONDS)
                object : HttpResponse by response {
                    override fun close() {
                        response.close()
                        closed.countDown()
                    }
                }
            }
        ) { server, client ->
            val future = client.executeAsync(server.request("full"))
            assertThat(preparing.await(10, TimeUnit.SECONDS)).isTrue()

            assertThat(future.cancel(false)).isTrue()
            canceled.countDown()

            assertThat(closed.await(10, TimeUnit.SECONDS)).isTrue()
        }
    }

    /**
     * Runs [block] with a fresh [LocalHttpServer] and a client of it, whose backend prepares
     * responses with [onPrepareResponse]. Responses come over real HTTP/1.1 connections, where
     * OkHttp reads the body and drains its remainder from the connection's socket, which a fake
     * body can't reproduce.
     */
    private fun withLocalHttpServer(
        onPrepareResponse: (HttpResponse) -> HttpResponse = { it },
        block: (server: LocalHttpServer, client: OkHttpClient) -> Unit,
    ) {
        LocalHttpServer().use { server ->
            OkHttpClient.builder()
                .backend(TestBackend(server.baseUrl, onPrepareResponse))
                .build()
                .use { client -> block(server, client) }
        }
    }

    /** Asserts the response's connection went back to the pool instead of leaking. */
    private fun assertConnectionReleased(client: OkHttpClient) {
        val connectionPool = client.okHttpClient.connectionPool
        assertThat(connectionPool.connectionCount()).isEqualTo(1)
        assertThat(connectionPool.idleConnectionCount()).isEqualTo(1)
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

    private companion object {
        const val FIRST_CHUNK = "event: message_start\n\n"
        const val FULL_BODY = "hello"
        const val LARGE_BODY_SIZE = 16 * 1024
    }

    /**
     * A keep-alive HTTP/1.1 server serving three paths:
     * - `/full`: a complete [FULL_BODY].
     * - `/large`: a [LARGE_BODY_SIZE]-byte body, larger than one OkHttp read but small enough to
     *   sit in the socket buffers, so an early close leaves a remainder to drain.
     * - `/hang`: a chunked body with one [FIRST_CHUNK] and then nothing, until the client
     *   disconnects.
     */
    private class LocalHttpServer : AutoCloseable {
        private val address = InetAddress.getLoopbackAddress()
        private val serverSocket = ServerSocket(0, 50, address)
        private val sockets = ConcurrentLinkedQueue<Socket>()
        private val clientDisconnected = CountDownLatch(1)

        val acceptedConnections = AtomicInteger()

        val baseUrl: String =
            URI("http", null, address.hostAddress, serverSocket.localPort, null, null, null)
                .toString()

        init {
            thread(isDaemon = true) {
                while (true) {
                    val socket =
                        try {
                            serverSocket.accept()
                        } catch (_: IOException) {
                            return@thread
                        }
                    acceptedConnections.incrementAndGet()
                    sockets.add(socket)
                    thread(isDaemon = true) { serve(socket) }
                }
            }
        }

        fun request(path: String): HttpRequest =
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl(baseUrl)
                .addPathSegment(path)
                .build()

        fun awaitClientDisconnect(): Boolean = clientDisconnected.await(10, TimeUnit.SECONDS)

        private fun serve(socket: Socket) {
            try {
                val input = socket.getInputStream()
                val reader = BufferedReader(InputStreamReader(input, Charsets.ISO_8859_1))
                val output = socket.getOutputStream()
                while (true) {
                    val requestLine = reader.readLine() ?: return
                    generateSequence(reader::readLine).takeWhile { it.isNotEmpty() }.count()
                    when (requestLine.split(" ")[1]) {
                        "/full" -> output.writeFixedLength(FULL_BODY)
                        "/large" -> output.writeFixedLength("a".repeat(LARGE_BODY_SIZE))
                        "/hang" -> {
                            output.write(
                                ("HTTP/1.1 200 OK\r\n" +
                                        "Content-Type: text/event-stream\r\n" +
                                        "Transfer-Encoding: chunked\r\n\r\n" +
                                        "${Integer.toHexString(FIRST_CHUNK.length)}\r\n" +
                                        "$FIRST_CHUNK\r\n")
                                    .toByteArray(Charsets.ISO_8859_1)
                            )
                            output.flush()
                            // The client sends nothing more while it reads this response, so the
                            // read only ends once the client closes the connection.
                            input.readBytes()
                            clientDisconnected.countDown()
                            return
                        }
                    }
                }
            } catch (_: IOException) {
                clientDisconnected.countDown()
            } finally {
                socket.close()
            }
        }

        private fun OutputStream.writeFixedLength(body: String) {
            write(
                "HTTP/1.1 200 OK\r\nContent-Length: ${body.length}\r\n\r\n$body"
                    .toByteArray(Charsets.ISO_8859_1)
            )
            flush()
        }

        override fun close() {
            serverSocket.close()
            sockets.forEach { it.close() }
        }
    }
}
