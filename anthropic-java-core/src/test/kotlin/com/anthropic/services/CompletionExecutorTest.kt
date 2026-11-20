package com.anthropic.services

import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.AnthropicClientAsyncImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.Sleeper
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.Interceptor
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import java.io.IOException
import java.io.InputStream
import java.time.Duration
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CompletionExecutorTest {

    private val ownedThreadNames = listOf("completion", "stream-handler")
    private val stepThreadNames = CopyOnWriteArrayList<Pair<String, String>>()
    private val continuationChained = CompletableFuture<Void?>()
    private val completionExecutor = Executors.newSingleThreadExecutor { Thread(it, "completion") }

    private inner class FakeHttpClient(vararg responses: () -> HttpResponse) : HttpClient {
        private val responses = responses.iterator()

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse =
            throw UnsupportedOperationException()

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> {
            recordStep("send")
            val response = responses.next()
            val future = CompletableFuture<HttpResponse>()
            runInThread("http-client") {
                runCatching(response).fold(future::complete, future::completeExceptionally)
            }
            return future
        }

        override fun close() {}
    }

    private inner class FakeSleeper : Sleeper {
        override fun sleep(duration: Duration) = throw UnsupportedOperationException()

        override fun sleepAsync(duration: Duration): CompletableFuture<Void> {
            recordStep("sleep")
            val future = CompletableFuture<Void>()
            runInThread("sleeper") { future.complete(null) }
            return future
        }

        override fun close() {}
    }

    private val handler =
        object : AsyncStreamResponse.Handler<Any?> {
            override fun onNext(value: Any?) = recordStep("handler")

            override fun onComplete(error: Optional<Throwable>) = recordStep("handler")
        }

    @Test
    fun responseRunsInOwnedThreads() {
        val client = client(response(200, "{}"))

        awaitContinuation(
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
        )

        assertRanInOwnedThreads("send", "response body", "continuation")
    }

    @Test
    fun sendFailureRunsInOwnedThreads() {
        val client = client({ throw IOException() }).withOptions { it.maxRetries(0) }

        awaitContinuation(
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
        )

        assertRanInOwnedThreads("send", "continuation")
    }

    @Test
    fun errorResponseRunsInOwnedThreads() {
        val client = client(response(400, "{}"))

        awaitContinuation(
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
        )

        assertRanInOwnedThreads("send", "response body", "continuation")
    }

    @Test
    fun retryRunsInOwnedThreads() {
        val client = client(response(503, "{}"), response(200, "{}"))

        awaitContinuation(
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
        )

        assertRanInOwnedThreads("send", "sleep", "send", "continuation")
    }

    @Test
    fun interceptorRunsInOwnedThreads() {
        val client =
            client(response(200, "{}")).withOptions {
                it.addInterceptor(
                    Interceptor.asyncOnly { nextClient, request, requestOptions ->
                        recordStep("interceptor")
                        nextClient.executeAsync(request, requestOptions).whenComplete { _, _ ->
                            recordStep("interceptor continuation")
                        }
                    }
                )
            }

        awaitContinuation(
            client
                .messages()
                .create(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
        )

        assertRanInOwnedThreads("interceptor", "send", "interceptor continuation", "continuation")
    }

    @Test
    fun streamingRunsInOwnedThreads() {
        val client = client(response(200, ""))

        awaitContinuation(
            client
                .messages()
                .createStreaming(
                    MessageCreateParams.builder()
                        .maxTokens(1024L)
                        .addUserMessage("Hello, world")
                        .model(Model.CLAUDE_OPUS_5_5)
                        .build()
                )
                .subscribe(handler)
                .onCompleteFuture()
        )

        assertRanInOwnedThreads("send", "response body", "handler", "continuation")
    }

    @Test
    fun autoPagerRunsInOwnedThreads() {
        val client = client(response(200, "{}"))

        awaitContinuation(
            client.messages().batches().list().thenCompose {
                it.autoPager().subscribe(handler).onCompleteFuture()
            }
        )

        assertRanInOwnedThreads("send", "response body", "handler", "continuation")
    }

    private fun client(vararg responses: () -> HttpResponse): AnthropicClientAsync =
        AnthropicClientAsyncImpl(
            ClientOptions.builder()
                .httpClient(FakeHttpClient(*responses))
                .baseUrl("http://localhost")
                .completionExecutor(completionExecutor)
                .streamHandlerExecutor(
                    Executors.newCachedThreadPool { Thread(it, "stream-handler") }
                )
                .sleeper(FakeSleeper())
                .build()
        )

    private fun response(statusCode: Int, body: String): () -> HttpResponse = {
        object : HttpResponse {
            override fun statusCode(): Int = statusCode

            override fun headers(): Headers = Headers.builder().build()

            override fun body(): InputStream {
                recordStep("response body")
                return body.byteInputStream()
            }

            override fun close() {}
        }
    }

    private fun recordStep(step: String) {
        stepThreadNames += step to Thread.currentThread().name
    }

    /**
     * Runs [block], which completes a future, in a new thread. It first waits for the test and for
     * the task that [completionExecutor] is running to chain their continuations, which would
     * otherwise run in the thread that chains them.
     */
    private fun runInThread(name: String, block: () -> Unit) =
        Thread(
                {
                    continuationChained.join()
                    completionExecutor.submit {}.get()
                    block()
                },
                name,
            )
            .start()

    private fun awaitContinuation(future: CompletableFuture<*>) {
        val continuation = future.handle { _, _ -> recordStep("continuation") }
        continuationChained.complete(null)
        continuation.get(1, TimeUnit.MINUTES)
    }

    private fun assertRanInOwnedThreads(vararg steps: String) {
        assertThat(stepThreadNames.map { (step, _) -> step }).containsSubsequence(*steps)
        assertThat(stepThreadNames).allSatisfy { (step, threadName) ->
            assertThat(threadName).`as`(step).isIn(ownedThreadNames)
        }
    }
}
