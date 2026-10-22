package com.anthropic.helpers

import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams
import com.anthropic.models.beta.messages.ThreadSendParams
import com.anthropic.models.beta.messages.ToolRunnerCreateParams
import com.anthropic.services.blocking.beta.MessageService
import com.anthropic.services.blocking.beta.MessageServiceImpl
import com.google.errorprone.annotations.MustBeClosed
import java.util.Optional
import java.util.function.Consumer

/**
 * A message thread (beta) keeps the conversation on the server, so each request sends only the new
 * messages instead of the whole conversation. A thread runs one turn at a time.
 *
 * Create one with [MessageService.thread], which takes what [MessageService.create] takes and sends
 * nothing until the first turn. For example:
 * ```java
 * BetaMessageThread thread = client.beta().messages().thread(
 *     ThreadCreateParams.builder()
 *         .model("claude-sonnet-5-5")
 *         .maxTokens(1024)
 *         .build());
 *
 * BetaMessage first = thread.send("Hello");
 * BetaMessage second = thread.send("And again");
 * ```
 *
 * [toolRunner] runs the tool loop over this thread. For replies in the JSON schema derived from a
 * class, see [StructuredBetaMessageThread].
 */
class BetaMessageThread
internal constructor(
    private val service: MessageService,
    @get:JvmSynthetic internal val state: MessageThreadState,
    @Volatile private var requestOptions: RequestOptions,
) {

    /** Returns a copy of the conversation: the messages that were given and the replies. */
    fun messages(): List<BetaMessageParam> = state.messages().toImmutable()

    /** Returns the ID of the reply that the next turn continues from, if it continues from one. */
    fun previousMessageId(): Optional<String> = Optional.ofNullable(state.previousMessageId())

    /** Returns the last reply of the thread, or an empty value before the first reply. */
    fun lastMessage(): Optional<BetaMessage> = Optional.ofNullable(state.lastMessage())

    /**
     * Returns the parameters for [MessageService.thread] to create a new thread with this thread's
     * fields and [messages].
     */
    fun params(): ThreadCreateParams = state.threadParams()

    /** Adds [message] to the conversation without sending it. The next turn sends it. */
    fun append(message: BetaMessageParam) = append(listOf(message))

    /** Adds the reply [message] as an assistant message. See [append]. */
    fun append(message: BetaMessage) = append(message.toParamKeepingUnknownBlocks())

    /** @see append */
    fun append(messages: List<BetaMessageParam>) = state.append(messages)

    /**
     * Sends the messages that the API does not hold yet as one turn, and returns the reply.
     *
     * @throws IllegalStateException if another turn is in progress, or if the turn would continue
     *   the thread with another `system` or `tools` than the thread was created with.
     */
    fun send(): BetaMessage = send {}

    /** Appends a user message with the given [text], and sends the turn. See [send]. */
    fun send(text: String): BetaMessage = send { it.addUserMessage(text) }

    /** Appends [message], and sends the turn. See [send]. */
    fun send(message: BetaMessageParam): BetaMessage = send { it.addMessage(message) }

    /** Appends the reply [message] as an assistant message, and sends the turn. See [send]. */
    fun send(message: BetaMessage): BetaMessage = send(message.toParamKeepingUnknownBlocks())

    /** Appends [messages], and sends the turn. See [send]. */
    fun send(messages: List<BetaMessageParam>): BetaMessage = send { builder ->
        messages.forEach { builder.addMessage(it) }
    }

    /**
     * Sends a turn as the [modifier] shapes it. See [send].
     *
     * The fields that the [modifier] sets apply to this turn only, and the messages that it adds
     * are sent with this turn. For example:
     * ```java
     * thread.send(builder -> builder.addUserMessage("In one word.").maxTokens(16));
     * ```
     */
    fun send(modifier: Consumer<ThreadSendParams.Builder>): BetaMessage =
        send(modifier, RequestOptions.none())

    /** Sends a turn like [send], with [requestOptions] for this turn only. */
    fun send(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): BetaMessage = send(state.begin(modifier), requestOptions.over(this.requestOptions))

    /**
     * Sends a turn like [send], and returns the reply as a stream of events. The turn stays in
     * progress until the stream has been read to its end or is closed, so close the stream, for
     * example with try-with-resources.
     *
     * @throws IllegalStateException if another turn is in progress, or if the turn would continue
     *   the thread with another `system` or `tools` than the thread was created with.
     */
    @MustBeClosed fun sendStreaming(): StreamResponse<BetaRawMessageStreamEvent> = sendStreaming {}

    /** Appends a user message with the given [text], and sends the turn. See [sendStreaming]. */
    @MustBeClosed
    fun sendStreaming(text: String): StreamResponse<BetaRawMessageStreamEvent> = sendStreaming {
        it.addUserMessage(text)
    }

    /** Appends [message], and sends the turn. See [sendStreaming]. */
    @MustBeClosed
    fun sendStreaming(message: BetaMessageParam): StreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming {
            it.addMessage(message)
        }

    /**
     * Appends the reply [message] as an assistant message, and sends the turn. See [sendStreaming].
     */
    @MustBeClosed
    fun sendStreaming(message: BetaMessage): StreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming(message.toParamKeepingUnknownBlocks())

    /** Appends [messages], and sends the turn. See [sendStreaming]. */
    @MustBeClosed
    fun sendStreaming(messages: List<BetaMessageParam>): StreamResponse<BetaRawMessageStreamEvent> =
        sendStreaming { builder ->
            messages.forEach { builder.addMessage(it) }
        }

    /** Sends a turn as the [modifier] shapes it, as for [send]. See [sendStreaming]. */
    @MustBeClosed
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>
    ): StreamResponse<BetaRawMessageStreamEvent> = sendStreaming(modifier, RequestOptions.none())

    /**
     * Sends a turn with [requestOptions] for this turn only, as for [send]. See [sendStreaming].
     */
    @MustBeClosed
    fun sendStreaming(
        modifier: Consumer<ThreadSendParams.Builder>,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaRawMessageStreamEvent> =
        stream(state.begin(modifier), requestOptions.over(this.requestOptions))

    /**
     * Changes the fields that the thread sends on later turns, and sends nothing. `system` and
     * `tools` cannot change once a turn has created the thread. Messages that the [modifier] adds
     * are ignored: use [append] to add messages.
     */
    fun update(modifier: Consumer<ThreadSendParams.Builder>) = state.update(modifier)

    /** Changes the thread's fields like [update], and the request options of later turns. */
    @Synchronized
    fun update(modifier: Consumer<ThreadSendParams.Builder>, requestOptions: RequestOptions) {
        state.update(modifier)
        this.requestOptions = requestOptions.over(this.requestOptions)
    }

    /**
     * Returns a new, independent thread with a copy of this thread's conversation, fields and
     * request options. Nothing is sent by this call.
     */
    fun fork(): BetaMessageThread = BetaMessageThread(service, state.fork(), requestOptions)

    /**
     * Returns a tool runner whose turns are turns of this thread, with the thread's tools. After
     * the run the thread holds the whole conversation.
     */
    fun toolRunner(): BetaToolRunner = toolRunner {}

    /**
     * Returns a tool runner like [toolRunner]. The [modifier] receives a builder that holds the
     * thread's fields and [messages], to set the runner's own options such as
     * [ToolRunnerCreateParams.Builder.maxIterations].
     *
     * @throws IllegalArgumentException if the [modifier] sets
     *   [ToolRunnerCreateParams.Builder.thread] to `false`.
     */
    fun toolRunner(modifier: Consumer<ToolRunnerCreateParams.Builder>): BetaToolRunner =
        service
            .toolRunner(
                ToolRunnerCreateParams.builder()
                    .initialMessageParams(state.params())
                    .also(modifier::accept)
                    .build(),
                requestOptions,
            )
            .onThread(this)

    private fun send(turn: Turn, requestOptions: RequestOptions): BetaMessage =
        try {
            request(turn) { service.create(it, requestOptions) }
                .also { state.recordReply(turn, it) }
        } finally {
            state.end(turn)
        }

    private fun stream(
        turn: Turn,
        requestOptions: RequestOptions,
    ): StreamResponse<BetaRawMessageStreamEvent> =
        try {
            StreamedTurn(state, turn, request(turn) { service.createStreaming(it, requestOptions) })
        } catch (e: Throwable) {
            state.end(turn)
            throw e
        }

    /**
     * Sends the requests of [turn] with [call] until the API answers one or one fails for good. The
     * caller ends the turn.
     */
    private fun <T> request(turn: Turn, call: (MessageCreateParams) -> T): T {
        while (true) {
            try {
                return call(state.plan(turn))
            } catch (e: AnthropicServiceException) {
                if (!state.recoversFrom(e)) throw e
            }
        }
    }

    internal companion object {

        /** Only a backend that is known to take no message threads says no. */
        @JvmSynthetic
        internal fun isSupportedBy(service: MessageService): Boolean =
            (service as? MessageServiceImpl)?.supportsMessageThreads() ?: true

        @JvmSynthetic
        internal fun create(
            service: MessageService,
            params: ThreadCreateParams,
            requestOptions: RequestOptions,
        ): BetaMessageThread {
            if (!isSupportedBy(service)) {
                throw threadsNotSupported()
            }

            return BetaMessageThread(
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
