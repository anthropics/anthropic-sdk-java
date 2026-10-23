package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.AsyncStreamResponse
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.StructuredMessage
import com.anthropic.models.beta.messages.StructuredThreadCreateParams
import com.anthropic.models.beta.messages.ThreadSendParams
import com.anthropic.services.async.beta.MessageServiceAsync
import java.util.Optional
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

/**
 * A [BetaMessageThreadAsync] whose replies are in the JSON schema derived from a class. It does
 * what [StructuredBetaMessageThread] does, and returns futures and asynchronous streams.
 *
 * Create one with [MessageServiceAsync.thread]. For example:
 * ```java
 * StructuredBetaMessageThreadAsync<Forecast> thread = client.beta().messages().thread(
 *     ThreadCreateParams.builder()
 *         .model("claude-sonnet-5-5")
 *         .maxTokens(1024)
 *         .outputConfig(Forecast.class)
 *         .build());
 *
 * CompletableFuture<StructuredMessage<Forecast>> tomorrow =
 *     thread.send("Today?").thenCompose(today -> thread.send("Tomorrow?"));
 * ```
 *
 * @param T The class that the text of the replies is converted to.
 */
class StructuredBetaMessageThreadAsync<T : Any>
internal constructor(
    private val outputType: Class<T>,
    private val delegate: BetaMessageThreadAsync,
) {

    /** @see BetaMessageThread.messages */
    fun messages(): List<BetaMessageParam> = delegate.messages()

    /** @see BetaMessageThread.previousMessageId */
    fun previousMessageId(): Optional<String> = delegate.previousMessageId()

    /** @see BetaMessageThread.lastMessage */
    fun lastMessage(): Optional<StructuredMessage<T>> = delegate.lastMessage().map(::structured)

    /** Returns what [BetaMessageThread.params] returns, with the class. */
    fun params(): StructuredThreadCreateParams<T> =
        StructuredThreadCreateParams(outputType, delegate.params())

    /** @see BetaMessageThread.append */
    fun append(message: BetaMessageParam) = delegate.append(message)

    /** @see BetaMessageThread.append */
    fun append(message: BetaMessage) = delegate.append(message)

    /** @see BetaMessageThread.append */
    fun append(messages: List<BetaMessageParam>) = delegate.append(messages)

    /** @see BetaMessageThread.send */
    fun send(): CompletableFuture<StructuredMessage<T>> = delegate.send().structured()

    /** @see BetaMessageThread.send */
    fun send(text: String): CompletableFuture<StructuredMessage<T>> =
        delegate.send(text).structured()

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessageParam): CompletableFuture<StructuredMessage<T>> =
        delegate.send(message).structured()

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessage): CompletableFuture<StructuredMessage<T>> =
        delegate.send(message).structured()

    /** @see BetaMessageThread.send */
    fun send(messages: List<BetaMessageParam>): CompletableFuture<StructuredMessage<T>> =
        delegate.send(messages).structured()

    /** @see BetaMessageThread.send */
    fun send(
        modifier: Consumer<ThreadSendParams.Builder>
    ): CompletableFuture<StructuredMessage<T>> = delegate.send(modifier).structured()

    /** @see BetaMessageThread.send */
    fun send(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): CompletableFuture<StructuredMessage<T>> =
        delegate.send(modifier, requestOptions).structured()

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(): AsyncStreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming()

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(text: String): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(text)

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(message: BetaMessageParam): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(message)

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(message: BetaMessage): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(message)

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(
        messages: List<BetaMessageParam>
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming(messages)

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming(modifier)

    /** @see BetaMessageThreadAsync.sendStreaming */
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): AsyncStreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(modifier, requestOptions)

    /** @see BetaMessageThread.update */
    fun update(modifier: Consumer<ThreadSendParams.Builder>) = delegate.update(modifier)

    /** @see BetaMessageThread.update */
    fun update(modifier: Consumer<ThreadSendParams.Builder>, requestOptions: RequestOptions) =
        delegate.update(modifier, requestOptions)

    /** @see BetaMessageThread.fork */
    fun fork(): StructuredBetaMessageThreadAsync<T> =
        StructuredBetaMessageThreadAsync(outputType, delegate.fork())

    private fun structured(message: BetaMessage) = StructuredMessage(outputType, message)

    // Not a user callback; the conversion only wraps the reply.
    @Suppress("ForbiddenMethodCall")
    private fun CompletableFuture<BetaMessage>.structured() = thenApply(::structured)
}
