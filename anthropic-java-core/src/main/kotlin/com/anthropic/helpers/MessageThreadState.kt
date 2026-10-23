package com.anthropic.helpers

import com.anthropic.core.JsonArray
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonObject
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.StreamResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicServiceException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.messages.BetaContentBlock
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.beta.messages.BetaMessageContentParam
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaRawMessageStreamEvent
import com.anthropic.models.beta.messages.BetaStopReason
import com.anthropic.models.beta.messages.BetaThreadContinueParams
import com.anthropic.models.beta.messages.BetaThreadCreateParams
import com.anthropic.models.beta.messages.BetaToolUnion
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams
import com.anthropic.models.beta.messages.ThreadCreateParams.UnsupportedRequestBehavior
import com.anthropic.models.beta.messages.ThreadSendParams
import java.util.function.Consumer
import java.util.stream.Stream
import kotlin.jvm.optionals.getOrNull

/** What the next request of a thread says about the thread. */
internal sealed interface Link {

    /**
     * The request starts a new thread with the whole history. It comes [afterNotFound] when it
     * sends again a request whose thread the API did not find.
     */
    class Create(val afterNotFound: Boolean = false) : Link

    /**
     * The request continues the thread from the reply [previousMessageId]. The API holds the first
     * [held] messages of the history and the [system] and [tools] of the thread, so the request
     * leaves them out. It comes [afterNotFound] when it is the first to continue such a thread.
     */
    class Continue(
        val previousMessageId: String,
        val held: Int,
        private val system: JsonField<MessageCreateParams.System>,
        private val tools: JsonField<List<BetaToolUnion>>,
        val afterNotFound: Boolean,
    ) : Link {

        /** The API cannot see a change of what the request leaves out, so it is refused here. */
        fun checkUnchanged(fields: MessageCreateParams) {
            val changed =
                listOfNotNull(
                    "`system`".takeIf { fields._system() != system },
                    "`tools`".takeIf { fields._tools() != tools },
                )
            check(changed.isEmpty()) {
                "Cannot change ${changed.joinToString(" and ")} on a message thread: the API " +
                    "keeps the `system` and `tools` that the thread was created with. To change " +
                    "them, start a new thread from `params()` or `fork()`." +
                    if ("`tools`" in changed)
                        " To add or remove tools on this thread, send `tool_addition` or " +
                            "`tool_removal` blocks in a `system` message."
                    else ""
            }
        }
    }

    /** The request names no thread and sends the whole history. */
    object Unthreaded : Link
}

/** One message of a thread's history. */
internal sealed class Entry(val param: BetaMessageParam) {

    class Given(param: BetaMessageParam) : Entry(param)

    class Reply(val message: BetaMessage) : Entry(message.toParamKeepingUnknownBlocks())

    /** The part of a reply that arrived before its stream ended. */
    class PartialReply(val message: BetaMessage) : Entry(message.toParamKeepingUnknownBlocks())
}

/**
 * A turn in progress: the fields of its requests, the messages appended since it began, and the
 * first [kept] messages of the history, which are all that stay if it ends before a reply starts.
 */
internal class Turn(val fields: MessageCreateParams, val kept: Int) {
    val appended = mutableListOf<BetaMessageParam>()
    var replyStarted = false
}

/**
 * A message thread as the client sees it: the history, the fields of the requests, and how the next
 * request links to the thread. It sends nothing: it plans each request and records each reply.
 */
internal class MessageThreadState(
    params: MessageCreateParams,
    private val unsupportedRequestBehavior: UnsupportedRequestBehavior,
) {

    private var fields = fieldsOf(params)
    private val history = mutableListOf<Entry>()
    private var link: Link = Link.Create()
    private var turn: Turn? = null

    init {
        stage(messagesOf(params))
    }

    @Synchronized
    fun messages(): List<BetaMessageParam> = history.map { it.param } + turn?.appended.orEmpty()

    @Synchronized fun previousMessageId(): String? = (link as? Link.Continue)?.previousMessageId

    @Synchronized
    fun lastMessage(): BetaMessage? =
        history.asReversed().firstNotNullOfOrNull {
            when (it) {
                is Entry.Given -> null
                is Entry.Reply -> it.message
                is Entry.PartialReply -> it.message
            }
        }

    /** The thread's fields, with the whole history as the messages. */
    @Synchronized
    fun params(): MessageCreateParams = fields.toBuilder().messages(messages()).build()

    /** What creates a thread with the same fields, history and unsupported request behavior. */
    @Synchronized
    fun threadParams(): ThreadCreateParams =
        ThreadCreateParams.builder()
            .wrap(params().toBuilder())
            .unsupportedRequestBehavior(unsupportedRequestBehavior)
            .build()

    /** Adds [messages] to the history, after the reply of the turn in progress if there is one. */
    @Synchronized
    fun append(messages: List<BetaMessageParam>) {
        turn?.appended?.addAll(messages) ?: stage(messages)
    }

    /**
     * Adds [results], the results of the tool calls of the last reply, right after that reply,
     * ahead of the messages appended since.
     */
    @Synchronized
    fun answerLastReply(results: BetaMessageParam) {
        history.add(history.indexOfLast { it is Entry.Reply } + 1, Entry.Given(results))
    }

    /** Takes the thread's fields from what [edit] makes of [params], and keeps the history. */
    @Synchronized
    fun update(edit: Consumer<ThreadSendParams.Builder>) {
        fields = fieldsOf(edited(edit))
    }

    /** A thread with the same fields and history, whose first request continues no thread. */
    @Synchronized
    fun fork(): MessageThreadState {
        val fork = MessageThreadState(fields, unsupportedRequestBehavior)
        fork.history.addAll(history)
        fork.stage(turn?.appended.orEmpty())
        if (link == Link.Unthreaded) {
            fork.link = Link.Unthreaded
        }
        return fork
    }

    /** Has the next request send the whole history instead of continuing the thread. */
    private fun startNewThread() {
        if (link != Link.Unthreaded) {
            link = Link.Create()
        }
    }

    /**
     * Puts the last reply of [fork], the reply to a compaction request, in place of the first
     * [compacted] messages of the history, and keeps the messages appended since. The next request
     * starts a new thread, or names none if [fork] had to give up threading.
     */
    @Synchronized
    fun compactTo(fork: MessageThreadState, compacted: Int) {
        val appended = history.drop(compacted).map { it.param }
        history.clear()
        history.add(fork.history.last())
        stage(appended)
        link = if (fork.link == Link.Unthreaded) Link.Unthreaded else Link.Create()
    }

    /**
     * Makes [messages] the history, and returns how many of them it started with already. A history
     * that does not start with the messages that the API holds starts a new thread.
     */
    @Synchronized
    fun replaceMessages(messages: List<BetaMessageParam>): Int {
        check(turn == null) {
            "Another turn is in progress on this message thread. Wait for it to end, or close " +
                "its stream."
        }

        val shared =
            history.zip(messages).takeWhile { (entry, message) -> entry.param == message }.size
        if (shared < ((link as? Link.Continue)?.held ?: 0)) {
            link = Link.Create()
        }
        history.subList(shared, history.size).clear()
        stage(messages.drop(shared))
        return shared
    }

    /**
     * Begins a turn that sends what [edit] makes of [params]: its fields, for this turn only, and
     * its messages, which become the history as in [replaceMessages].
     */
    @Synchronized
    fun begin(edit: Consumer<ThreadSendParams.Builder>): Turn {
        val edited = edited(edit)
        val fields = fieldsOf(edited)
        val shared = replaceMessages(messagesOf(edited))
        // With nothing new to send, the turn whose stream ended early is sent again.
        if (history.lastOrNull() is Entry.PartialReply) {
            history.removeAt(history.lastIndex)
        }
        return Turn(fields, minOf(shared, history.size)).also { turn = it }
    }

    /** What [edit] makes of [params]. */
    private fun edited(edit: Consumer<ThreadSendParams.Builder>): MessageCreateParams =
        ThreadSendParams.builder()
            .wrap(params().toBuilder())
            .also(edit::accept)
            .build()
            .messageParams

    /** Throws what [plan] throws for the fields of [params] while the thread can be continued. */
    @Synchronized
    fun checkUnchanged(params: MessageCreateParams) {
        (link as? Link.Continue)?.checkUnchanged(params)
    }

    /**
     * Ends [turn], however it went. A turn that no reply started takes back the messages that it
     * added to the history, so the same call sends it again.
     */
    @Synchronized
    fun end(turn: Turn) {
        if (this.turn !== turn) {
            return
        }
        this.turn = null
        if (!turn.replyStarted) {
            history.subList(turn.kept, history.size).clear()
        }
        stage(turn.appended)
    }

    /** A message that repeats the reply before it is left out, so a reply can be passed back. */
    private fun stage(messages: List<BetaMessageParam>) {
        for (message in messages) {
            val last = history.lastOrNull()
            if (last == null || last is Entry.Given || last.param != message) {
                history.add(Entry.Given(message))
            }
        }
    }

    /**
     * The next request of [turn]. Planning changes nothing, so a request that fails leaves the
     * thread as it was.
     */
    @Synchronized
    fun plan(turn: Turn): MessageCreateParams {
        val link = link
        val builder =
            turn.fields
                .toBuilder()
                .messages(
                    if (link is Link.Continue) history.drop(link.held).map { it.param }
                    else wholeHistory()
                )
                .replaceAdditionalHeaders(
                    STAINLESS_HELPER_HEADER,
                    mergedStainlessHelperValue(
                        turn.fields._additionalHeaders(),
                        StainlessHelperHeaderValue.MESSAGE_THREAD,
                    ),
                )
        when (link) {
            is Link.Create -> builder.thread(BetaThreadCreateParams.builder().build())
            is Link.Continue -> {
                link.checkUnchanged(turn.fields)
                builder
                    .thread(BetaThreadContinueParams.of(link.previousMessageId))
                    .system(JsonMissing.of())
                    .tools(JsonMissing.of())
            }
            Link.Unthreaded -> return builder.build()
        }
        if (
            AnthropicBeta.MESSAGE_THREADS_2026_08_12 !in turn.fields.betas().getOrNull().orEmpty()
        ) {
            builder.addBeta(AnthropicBeta.MESSAGE_THREADS_2026_08_12)
        }
        return builder.build()
    }

    /**
     * The history as a request that continues no thread sends it. The messages up to the last reply
     * were sent before, and go without the cache breakpoints that were set for those requests.
     */
    private fun wholeHistory(): List<BetaMessageParam> {
        val recorded = history.indexOfLast { it !is Entry.Given } + 1
        return history.mapIndexed { index, entry ->
            if (index < recorded) entry.param.withoutCacheControl() else entry.param
        }
    }

    /**
     * Ends [turn] in the same step, so that whoever sees the reply can begin the next turn. A
     * refused turn is not recorded, but the API may hold it, so the next request continues no
     * thread.
     */
    @Synchronized
    fun recordReply(turn: Turn, reply: BetaMessage) {
        if (reply.isRefusal()) {
            startNewThread()
            end(turn)
            return
        }

        turn.replyStarted = true
        history.add(Entry.Reply(reply))
        if (link != Link.Unthreaded) {
            val system = turn.fields._system()
            val tools = turn.fields._tools()
            val afterNotFound = (link as? Link.Create)?.afterNotFound == true
            // The API holds no turn that a fallback model wrote.
            link =
                if (reply.isFromFallbackModel()) Link.Create()
                else Link.Continue(reply.id(), history.size, system, tools, afterNotFound)
            // The thread keeps what the turn that created it gave.
            fields = fields.toBuilder().system(system).tools(tools).build()
        }
        adoptContainer(reply)
        end(turn)
    }

    /**
     * Records the part of a reply that arrived before its stream ended. The API holds either the
     * whole reply or none of it, so the next request continues no thread. A refusal is not
     * recorded, as in [recordReply].
     */
    @Synchronized
    fun recordPartialReply(turn: Turn, reply: BetaMessage) {
        if (!reply.isRefusal()) {
            turn.replyStarted = true
            if (reply.content().isNotEmpty()) {
                history.add(Entry.PartialReply(reply))
            }
        }
        startNewThread()
        end(turn)
    }

    /**
     * Container-bound server tools reject a request that leaves out the container the turn before
     * it ran in, so its id is sent on later turns unless the caller named one.
     */
    private fun adoptContainer(reply: BetaMessage) {
        val id = reply.container().getOrNull()?.id() ?: return
        val container = fields.container().getOrNull()
        if (container == null) {
            fields = fields.toBuilder().container(id).build()
        } else if (container.betaContainerParams().getOrNull()?.id()?.isPresent == false) {
            val named = container.asBetaContainerParams().toBuilder().id(id).build()
            fields = fields.toBuilder().container(named).build()
        }
    }

    /**
     * Whether the request that failed with [error] should be planned and sent again, because the
     * API rejected its thread in a way that the state has now adjusted for.
     */
    @Synchronized
    fun recoversFrom(error: AnthropicServiceException): Boolean {
        val code = error.body().field("error")?.field("details")?.field("error_code")?.asString()
        val continued = link as? Link.Continue
        val continuesUnthreaded =
            unsupportedRequestBehavior == UnsupportedRequestBehavior.CONTINUE_UNTHREADED
        link =
            when (code?.getOrNull()) {
                "thread_not_found" ->
                    // The API may be unable to store a thread that is gone right after its start.
                    continued?.let {
                        if (it.afterNotFound && continuesUnthreaded) Link.Unthreaded
                        else Link.Create(afterNotFound = true)
                    }
                "thread_already_continued",
                "thread_continue_in_flight" -> continued?.let { Link.Create() }
                "thread_unsupported_request" ->
                    Link.Unthreaded.takeIf { link != Link.Unthreaded && continuesUnthreaded }
                else -> null
            } ?: return false
        return true
    }

    private companion object {

        private fun JsonValue.field(name: String): JsonValue? = asObject().getOrNull()?.get(name)

        private fun BetaMessage.isRefusal(): Boolean =
            stopReason().getOrNull() == BetaStopReason.REFUSAL

        private fun BetaMessage.isFromFallbackModel(): Boolean =
            usage().iterations().getOrNull().orEmpty().any { it.isFallbackMessage() }

        private fun BetaMessageParam.withoutCacheControl(): BetaMessageParam {
            val json = JsonValue.from(this)
            val stripped = json.withoutCacheControl()
            return stripped.takeIf { it != json }?.convert(BetaMessageParam::class.java) ?: this
        }

        /** The keys that lead from a message to its blocks. Any other value is sent as given. */
        private val BLOCK_KEYS = setOf("content", "source", "tool", "definition", "tool_references")

        private fun JsonValue.withoutCacheControl(): JsonValue =
            if (this !is JsonObject) this
            else
                JsonObject.of(
                    (values - "cache_control").mapValues { (name, value) ->
                        when {
                            name !in BLOCK_KEYS -> value
                            value is JsonArray ->
                                JsonArray.of(value.values.map { it.withoutCacheControl() })
                            else -> value.withoutCacheControl()
                        }
                    }
                )

        private fun messagesOf(params: MessageCreateParams): List<BetaMessageParam> =
            params._messages().asKnown().getOrNull().orEmpty()

        /** [params] without the messages and the `thread`, which the state sets on each request. */
        private fun fieldsOf(params: MessageCreateParams): MessageCreateParams =
            params.toBuilder().messages(emptyList()).thread(JsonMissing.of()).build()
    }
}

/**
 * The stream of [turn]. It records the reply when the `message_stop` event is read, and the part of
 * the reply that arrived when it is closed before that. Either ends the turn.
 */
internal class StreamedTurn(
    private val state: MessageThreadState,
    private val turn: Turn,
    private val response: StreamResponse<BetaRawMessageStreamEvent>,
) : StreamResponse<BetaRawMessageStreamEvent> {

    private val accumulator = BetaMessageAccumulator.create()
    private var ended = false

    override fun stream(): Stream<BetaRawMessageStreamEvent> = response.stream().peek(::accumulate)

    @Synchronized
    private fun accumulate(event: BetaRawMessageStreamEvent) {
        if (ended) {
            return
        }
        accumulator.accumulate(event)
        if (event.isMessageStop()) {
            ended = true
            try {
                state.recordReply(turn, accumulator.message())
            } finally {
                state.end(turn)
            }
        }
    }

    @Synchronized
    override fun close() {
        response.close()
        if (!ended) {
            ended = true
            try {
                accumulator.sendablePartialMessage()?.let { state.recordPartialReply(turn, it) }
            } finally {
                state.end(turn)
            }
        }
    }
}

/**
 * These options, with those of [thread] where these set none. A timeout replaces the whole timeout
 * of [thread], where [RequestOptions.applyDefaults] keeps the phases that it leaves unset.
 */
@JvmSynthetic
internal fun RequestOptions.over(thread: RequestOptions): RequestOptions =
    applyDefaults(thread).toBuilder().also { timeout().getOrNull()?.let(it::timeout) }.build()

@JvmSynthetic
internal fun threadsNotSupported() =
    AnthropicException(
        "Message threads are not available on this client, because the service that its backend " +
            "sends to does not take them. Send the whole conversation with " +
            "`messages().create()` instead."
    )

/**
 * [BetaMessage.toParam], except that a block of a type newer than this SDK goes back as the raw
 * JSON it came as, where [BetaContentBlock.toParam] throws.
 */
@JvmSynthetic
internal fun BetaMessage.toParamKeepingUnknownBlocks(): BetaMessageParam =
    BetaMessageParam.ofAssistant(
        BetaMessageContentParam.ofContentBlockParams(
            content().map { block ->
                block
                    ._json()
                    .getOrNull()
                    ?.takeIf { block.type().value() == BetaContentBlock.Type.Value._UNKNOWN }
                    ?.convert(BetaContentBlockParam::class.java) ?: block.toParam()
            }
        )
    )
