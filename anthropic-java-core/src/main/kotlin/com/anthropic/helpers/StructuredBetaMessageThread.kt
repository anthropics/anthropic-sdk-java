package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.StructuredMessage
import com.anthropic.models.beta.messages.StructuredThreadCreateParams
import com.anthropic.models.beta.messages.ThreadSendParams
import com.anthropic.models.beta.messages.ToolRunnerCreateParams
import com.anthropic.services.blocking.beta.MessageService
import com.google.errorprone.annotations.MustBeClosed
import java.util.Optional
import java.util.function.Consumer

/**
 * A [BetaMessageThread] whose replies are in the JSON schema derived from a class. [send] and
 * [lastMessage] return the reply with its text converted to instances of the class.
 *
 * Create one with [MessageService.thread]. For example:
 * ```java
 * StructuredBetaMessageThread<Forecast> thread = client.beta().messages().thread(
 *     ThreadCreateParams.builder()
 *         .model("claude-sonnet-5-5")
 *         .maxTokens(1024)
 *         .outputConfig(Forecast.class)
 *         .build());
 *
 * StructuredMessage<Forecast> today = thread.send("Today?");
 * StructuredMessage<Forecast> tomorrow = thread.send("Tomorrow?");
 * ```
 *
 * @param T The class that the text of the replies is converted to.
 */
class StructuredBetaMessageThread<T : Any>
internal constructor(private val outputType: Class<T>, private val delegate: BetaMessageThread) {

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
    fun send(): StructuredMessage<T> = structured(delegate.send())

    /** @see BetaMessageThread.send */
    fun send(text: String): StructuredMessage<T> = structured(delegate.send(text))

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessageParam): StructuredMessage<T> = structured(delegate.send(message))

    /** @see BetaMessageThread.send */
    fun send(message: BetaMessage): StructuredMessage<T> = structured(delegate.send(message))

    /** @see BetaMessageThread.send */
    fun send(messages: List<BetaMessageParam>): StructuredMessage<T> =
        structured(delegate.send(messages))

    /** @see BetaMessageThread.send */
    fun send(modifier: Consumer<ThreadSendParams.Builder>): StructuredMessage<T> =
        structured(delegate.send(modifier))

    /** @see BetaMessageThread.send */
    fun send(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): StructuredMessage<T> = structured(delegate.send(modifier, requestOptions))

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(): StreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming()

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(text: String): StreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(text)

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(message: BetaMessageParam): StreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(message)

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(message: BetaMessage): StreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(message)

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(messages: List<BetaMessageParam>): StreamResponse<BetaRawMessageStreamEvent> =
        delegate.sendStreaming(messages)

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>
    ): StreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming(modifier)

    /** @see BetaMessageThread.sendStreaming */
    @MustBeClosed
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaRawMessageStreamEvent> = delegate.sendStreaming(modifier, requestOptions)

    /** @see BetaMessageThread.update */
    fun update(modifier: Consumer<ThreadSendParams.Builder>) = delegate.update(modifier)

    /** @see BetaMessageThread.update */
    fun update(modifier: Consumer<ThreadSendParams.Builder>, requestOptions: RequestOptions) =
        delegate.update(modifier, requestOptions)

    /** @see BetaMessageThread.fork */
    fun fork(): StructuredBetaMessageThread<T> =
        StructuredBetaMessageThread(outputType, delegate.fork())

    /** @see BetaMessageThread.toolRunner */
    fun toolRunner(): BetaToolRunner = delegate.toolRunner()

    /** @see BetaMessageThread.toolRunner */
    fun toolRunner(modifier: Consumer<ToolRunnerCreateParams.Builder>): BetaToolRunner =
        delegate.toolRunner(modifier)

    private fun structured(message: BetaMessage) = StructuredMessage(outputType, message)
}
