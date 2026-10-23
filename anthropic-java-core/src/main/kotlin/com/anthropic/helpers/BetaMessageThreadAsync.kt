package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.http.toAsync
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams
import com.anthropic.models.beta.messages.ThreadSendParams
import com.anthropic.services.async.beta.MessageServiceAsync
import com.anthropic.services.async.beta.MessageServiceAsyncImpl
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionException
import java.util.concurrent.Executor
import java.util.concurrent.ForkJoinPool
import java.util.function.Consumer
import java.util.stream.Stream
import kotlin.streams.asStream

/**
 * A message thread on an asynchronous client. It does what [BetaMessageThread] does, and returns
 * futures and asynchronous streams. A turn is in progress until its future has completed or its
 * stream has ended.
 *
 * Create one with [MessageServiceAsync.thread]. For example:
 * ```java
 * BetaMessageThreadAsync thread = client.beta().messages().thread(
 *     ThreadCreateParams.builder()
 *         .model("claude-sonnet-5-5")
 *         .maxTokens(1024)
 *         .build());
 *
 * CompletableFuture<BetaMessage> second =
 *     thread.send("Hello").thenCompose(first -> thread.send("And again"));
 * ```
 *
 * For replies in the JSON schema derived from a class, see [StructuredBetaMessageThreadAsync].
 */
class BetaMessageThreadAsync
internal constructor(
    private val service: MessageServiceAsync,
    private val state: MessageThreadState,
    @Volatile private var requestOptions: RequestOptions,
) {

    /** @see BetaMessageThread.messages */
    fun messages(): List<BetaMessageParam> = state.messages().toImmutable()

    /** @see BetaMessageThread.previousMessageId */
    fun previousMessageId(): Optional<String> = Optional.ofNullable(state.previousMessageId())

    /** @see BetaMessageThread.lastMessage */
    fun lastMessage(): Optional<BetaMessage> = Optional.ofNullable(state.lastMessage())

    /**
     * Returns the parameters for [MessageServiceAsync.thread] to create a new thread with this
     * thread's fields and [messages].
     */
    fun params(): ThreadCreateParams = state.threadParams()

    /** @see BetaMessageThread.append */
    fun append(message: BetaMessageParam) = append(listOf(message))

    /** @see BetaMessageThread.append */
    fun append(message: BetaMessage) = append(message.toParamKeepingUnknownBlocks())

    /** @see BetaMessageThread.append */
    fun append(messages: List<BetaMessageParam>) = state.append(messages)

    /** @see BetaMessageThread.send */
    fun send(): CompletableFuture<BetaMessage> = send {}

    /** @see BetaMessageThread.send */
    fun send(text: String): CompletableFuture<BetaMessage> = send { it.addUserMessage(text) }

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessageParam): CompletableFuture<BetaMessage> = send {
        it.addMessage(message)
    }

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessage): CompletableFuture<BetaMessage> =
        send(message.toParamKeepingUnknownBlocks())

    /** @see BetaMessageThread.send */
    fun send(messages: List<BetaMessageParam>): CompletableFuture<BetaMessage> = send { builder ->
        messages.forEach { builder.addMessage(it) }
    }

    /** @see BetaMessageThread.send */
    fun send(modifier: Consumer<ThreadSendParams.Builder>): CompletableFuture<BetaMessage> =
        send(modifier, RequestOptions.none())

    /** @see BetaMessageThread.send */
    fun send(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaMessage> =
        send(state.begin(modifier), requestOptions.over(this.requestOptions))

    /**
     * Sends a turn like [send], and returns the reply as a stream of events. The turn stays in
     * progress until the stream has ended or is closed.
     */
    fun sendStreaming(): AsyncStreamResponse<BetaRawMessageStreamEvent> = sendStreaming {}

    /** @see sendStreaming */
    fun sendStreaming(text: String): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming {
            it.addUserMessage(text)
        }

    /** @see sendStreaming */
    fun sendStreaming(message: BetaMessageParam): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming {
            it.addMessage(message)
        }

    /** @see sendStreaming */
    fun sendStreaming(message: BetaMessage): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming(message.toParamKeepingUnknownBlocks())

    /** @see sendStreaming */
    fun sendStreaming(
        messages: List<BetaMessageParam>
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> = sendStreaming { builder ->
        messages.forEach { builder.addMessage(it) }
    }

    /** @see sendStreaming */
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming(modifier, RequestOptions.none())

    /** @see sendStreaming */
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        stream(state.begin(modifier), requestOptions.over(this.requestOptions))

    /** @see BetaMessageThread.update */
    fun update(modifier: Consumer<ThreadSendParams.Builder>) = state.update(modifier)

    /** @see BetaMessageThread.update */
    @Synchronized
    fun update(modifier: Consumer<ThreadSendParams.Builder>, requestOptions: RequestOptions) {
        state.update(modifier)
        this.requestOptions = requestOptions.over(this.requestOptions)
    }

    /** @see BetaMessageThread.fork */
    fun fork(): BetaMessageThreadAsync =
        BetaMessageThreadAsync(service, state.fork(), requestOptions)

    private fun send(turn: Turn, requestOptions: RequestOptions): CompletableFuture<BetaMessage> =
        request(turn, { service.create(it, requestOptions) }) { reply ->
            state.recordReply(turn, reply)
            reply
        }

    private fun stream(
        turn: Turn,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        request<_, StreamResponse<BetaRawMessageStreamEvent>>(
                turn,
                { service.withRawResponse().createStreaming(it, requestOptions) },
            ) { response ->
                val streamed = StreamedTurn(state, turn, response.parse())
                object : StreamResponse<BetaRawMessageStreamEvent> by streamed {
                    // The future of an asynchronous stream completes before the stream is closed.
                    // This ends the turn of a stream that ran out before that.
                    override fun stream(): Stream<BetaRawMessageStreamEvent> =
                        sequence { streamed.use { yieldAll(it.stream().iterator()) } }.asStream()
                }
            }
            .toAsync(streamHandlerExecutor())

    /**
     * Sends the requests of [turn] with [call] until the API answers one, which [answered] gets, or
     * one fails for good. That failure, or one of [answered], ends the turn, in a stage that the
     * caller cannot reach, so also when the caller cancels the returned future.
     */
    private fun <T, R> request(
        turn: Turn,
        call: (MessageCreateParams) -> CompletableFuture<T>,
        answered: (T) -> R,
    ): CompletableFuture<R> {
        val response =
            try {
                call(state.plan(turn))
            } catch (e: Throwable) {
                state.end(turn)
                throw e
            }

        return response
            .handle { value, thrown ->
                val error = (thrown as? CompletionException)?.cause ?: thrown
                when {
                    error == null ->
                        try {
                            CompletableFuture.completedFuture(answered(value))
                        } catch (e: Throwable) {
                            state.end(turn)
                            throw e
                        }
                    error is AnthropicServiceException && state.recoversFrom(error) ->
                        request(turn, call, answered)
                    else -> {
                        state.end(turn)
                        CompletableFuture<R>().apply { completeExceptionally(error) }
                    }
                }
            }
            .thenCompose { it }
    }

    private fun streamHandlerExecutor(): Executor =
        (service as? MessageServiceAsyncImpl)?.clientOptions()?.streamHandlerExecutor
            ?: ForkJoinPool.commonPool()

    internal companion object {

        @JvmSynthetic
        internal fun create(
            service: MessageServiceAsync,
            params: ThreadCreateParams,
            requestOptions: RequestOptions,
        ): BetaMessageThreadAsync {
            val supported =
                (service as? MessageServiceAsyncImpl)?.clientOptions()?.supportsMessageThreads()
            if (supported == false) {
                throw threadsNotSupported()
            }

            return BetaMessageThreadAsync(
                service,
                MessageThreadState(
                    params.initialMessageParams,
                    params.unsupportedRequestBehavior(),
                ),
                requestOptions,
            )
        }
    }
}
