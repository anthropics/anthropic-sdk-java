package com.anthropic.helpers

import com.anthropic.core.*
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.outputTypeFromJson
import com.anthropic.core.toJsonString
import com.anthropic.models.beta.messages.*
import com.anthropic.services.blocking.beta.MessageService
import java.util.Optional
import java.util.concurrent.atomic.AtomicBoolean
import java.util.stream.Stream
import kotlin.jvm.optionals.asSequence
import kotlin.jvm.optionals.getOrNull

/**
 * Makes one request, yields it in the form the caller iterates over, and returns the assistant
 * message once the caller has moved on.
 */
private typealias Send<T> = suspend SequenceScope<T>.(MessageCreateParams) -> BetaMessage

/**
 * A [BetaToolRunner] handles the automatic conversation loop between the assistant and tools.
 *
 * It's an [Iterable] that yields either [BetaMessage] objects by default or [StreamResponse]
 * objects when calling [streaming].
 */
class BetaToolRunner
internal constructor(
    private val messageService: MessageService,
    private val params: ToolRunnerCreateParams,
    private val requestOptions: RequestOptions,
) : Iterable<BetaMessage> {

    private var consumed = AtomicBoolean()
    private var currentParams = params.initialMessageParams
    private var nextParams: MessageCreateParams? = null
    private var lastToolResponse: BetaMessageParam? = null

    // How compaction moves through the runner:
    //   1. `compactBeforeNextTurn()` sets `compaction` to `Scheduled`. Nothing is sent.
    //   2. Before each model request, `runLoop()` asks `compactionToSend()`. If one is scheduled
    //      and the last turn wasn't paused, `compact()` sends the compaction request instead: the
    //      current params plus `compaction`, without `context_management`.
    //   3. While that request is out, `compaction` is `InFlight`: `setNextParams()` refuses
    //      different messages, and further `compactBeforeNextTurn()` calls are ignored. A request
    //      that throws puts it back to `Idle`; nothing is retried.
    //   4. The response is yielded, then `compact()` makes it the whole history (or keeps the
    //      history and warns if there was no summary), and the loop carries on.
    //   5. If the run is ending, `compactionToSendAfterFinalTurn()` decides whether 2-4 happen
    //      once more before stopping: not when the last turn was cut off with tool calls never run.
    // From 1 to 4, `compactBeforeNextTurn()` and `setNextParams()` refuse a compaction edit.
    private var compaction: Compaction = Compaction.Idle

    private sealed interface Compaction {
        object Idle : Compaction

        class Scheduled(val config: BetaCompactionConfig) : Compaction

        object InFlight : Compaction
    }

    init {
        rejectCompactionParam(params.initialMessageParams)
    }

    override fun iterator(): Iterator<BetaMessage> = runLoop { requestParams ->
        messageService.create(requestParams, requestOptions).also { yield(it) }
    }

    /** Returns an [Iterable] that yields streamed assistant messages instead of buffered ones. */
    fun streaming(): Iterable<StreamResponse<BetaRawMessageStreamEvent>> =
        object : Iterable<StreamResponse<BetaRawMessageStreamEvent>> {

            override fun iterator(): Iterator<StreamResponse<BetaRawMessageStreamEvent>> =
                runLoop { requestParams ->
                    val accumulator = BetaMessageAccumulator.create()
                    val streamResponse =
                        object : StreamResponse<BetaRawMessageStreamEvent> {

                            private val delegate =
                                messageService.createStreaming(requestParams, requestOptions)

                            override fun stream(): Stream<BetaRawMessageStreamEvent> =
                                delegate.stream().peek(accumulator::accumulate)

                            override fun close() = delegate.close()
                        }
                    streamResponse.use { yield(it) }

                    accumulator.message()
                }
        }

    /** The conversation loop, which [iterator] and [streaming] share. */
    private fun <T> runLoop(send: Send<T>): Iterator<T> {
        if (consumed.getAndSet(true)) {
            throw IllegalStateException("Cannot iterate the same `BetaToolRunner` twice")
        }

        return iterator {
            var paramsBuilder = params.initialMessageParams.toBuilderWithToolRunnerHeader()
            val maxIterations = params.maxIterations().orElse(Long.MAX_VALUE)
            var iteration = 0L
            var turnPaused = false

            while (iteration < maxIterations) {
                currentParams = paramsBuilder.build()
                val compactionConfig = compactionToSend(turnPaused)
                if (compactionConfig != null) {
                    paramsBuilder = compact(send, compactionConfig)
                    continue
                }
                iteration++

                val message = send(currentParams)
                val nextStep = determineNextStepFromStopReason(message.stopReason())
                turnPaused = nextStep == NextStep.RESUME

                val nextParams = nextParams
                if (nextParams != null) {
                    paramsBuilder = nextParams.toBuilderWithToolRunnerHeader()
                    this@BetaToolRunner.nextParams = null
                    continue
                }

                val toolResponse =
                    if (nextStep == NextStep.RUN_TOOLS) generateToolResponse(message.toParam())
                    else null
                if (
                    nextStep == NextStep.STOP ||
                        (nextStep == NextStep.RUN_TOOLS && toolResponse == null)
                ) {
                    val finalCompactionConfig = compactionToSendAfterFinalTurn(message)
                    if (finalCompactionConfig != null) {
                        currentParams =
                            paramsBuilder
                                .addMessage(message.toParamKeepingUnknownBlocks())
                                .adoptContainer(message)
                                .build()
                        currentParams = compact(send, finalCompactionConfig).build()
                    }
                    break
                }
                paramsBuilder.addMessage(message).adoptContainer(message)
                toolResponse?.let { paramsBuilder.addMessage(it) }
            }
        }
    }

    /** Returns the current params being used by [BetaToolRunner]. */
    fun params(): MessageCreateParams = currentParams

    /** Sets the parameters for the next API call, invalidating any cached tool response. */
    fun setNextParams(nextParams: MessageCreateParams) {
        rejectCompactionParam(nextParams)
        if (compaction !is Compaction.Idle) {
            checkCanCompact(nextParams)
        }
        check(
            compaction !is Compaction.InFlight ||
                nextParams._messages() == currentParams._messages()
        ) {
            "The messages can't be changed while the conversation is being compacted, because " +
                "the compaction response replaces them. Make the change on the next iteration."
        }
        lastToolResponse = null
        this.nextParams = nextParams
    }

    /**
     * Compacts the conversation before the model's next turn.
     *
     * This only schedules the compaction. Once the current turn has finished, including any tool
     * calls, the runner requests a summary and replaces the message history with the compaction
     * response the API returns. That response is yielded like any other message, with a stop reason
     * of [BetaStopReason.COMPACTION], and the runner then carries on. If the current turn is the
     * last one, the runner compacts and then stops.
     *
     * Takes the same [BetaCompactionConfig] as [MessageService.create] does in its params, e.g. to
     * give your own summarization instructions. Calling this again before the compaction runs
     * replaces the pending one. Requires the `compact-2026-09-04` beta.
     *
     * For example, to compact once the conversation grows past 100,000 input tokens:
     * ```java
     * for (BetaMessage message : toolRunner) {
     *   if (message.usage().inputTokens() > 100_000) {
     *     toolRunner.compactBeforeNextTurn();
     *   }
     * }
     * ```
     *
     * @throws IllegalStateException if the context management config has a compaction edit.
     */
    fun compactBeforeNextTurn() = compactBeforeNextTurn(BetaCompactionConfig.builder().build())

    /** @see compactBeforeNextTurn */
    fun compactBeforeNextTurn(compaction: BetaCompactionConfig) {
        checkCanCompact(nextParams ?: currentParams)
        when (this.compaction) {
            // There is nothing new to summarize while the compaction response is being handled.
            Compaction.InFlight -> return
            Compaction.Idle,
            is Compaction.Scheduled -> this.compaction = Compaction.Scheduled(compaction)
        }
    }

    /**
     * Get the tool response for the last message from the assistant.
     *
     * Avoids redundant tool executions by caching results.
     *
     * @returns A [BetaMessageParam] containing tool results, or an empty optional if no tools need
     *   to be executed.
     */
    fun lastToolResponse(): Optional<BetaMessageParam> {
        if (lastToolResponse != null) {
            return Optional.ofNullable(lastToolResponse)
        }

        val lastMessage = currentParams.messages().lastOrNull() ?: return Optional.empty()
        return Optional.ofNullable(generateToolResponse(lastMessage))
    }

    private fun rejectCompactionParam(params: MessageCreateParams) {
        val compaction = params._compaction()
        require(compaction.isMissing() || compaction.isNull()) {
            "`compaction` cannot be set on a tool runner: every request in the loop would " +
                "compact again. Call `compactBeforeNextTurn()` on the tool runner when the " +
                "conversation should be compacted instead."
        }
    }

    private fun checkCanCompact(params: MessageCreateParams) {
        // The compaction request is sent without `context_management`, so the API can't reject this
        // combination there: it would run and bill the compaction, then reject the next request,
        // where the compaction response and the compaction edit meet.
        val edits =
            params._contextManagement().asKnown().getOrNull()?._edits()?.asKnown()?.getOrNull()
        check(
            edits.orEmpty().none {
                it.type()._value().asString().getOrNull()?.startsWith("compact_") == true
            }
        ) {
            "`compactBeforeNextTurn()` can't be used while `contextManagement` has a compaction " +
                "edit, because the API doesn't accept a compaction block together with one. " +
                "Remove the edit first."
        }
    }

    // The API can't compact a conversation that ends mid-turn, so a paused turn is resumed first.
    private fun compactionToSend(turnPaused: Boolean): BetaCompactionConfig? =
        (compaction as? Compaction.Scheduled)?.config?.takeUnless { turnPaused }

    /**
     * Sends [config] as a compaction request of its own, yields the response like any other, and
     * returns the params to carry on with.
     */
    private suspend fun <T> SequenceScope<T>.compact(
        send: Send<T>,
        config: BetaCompactionConfig,
    ): MessageCreateParams.Builder {
        val request =
            currentParams
                .toBuilder()
                .compaction(config)
                // The API refuses `compaction` alongside `context_management`. Later requests
                // keep it.
                .contextManagement(JsonMissing.of())
                .build()

        compaction = Compaction.InFlight
        val message =
            try {
                send(request)
            } finally {
                compaction = Compaction.Idle
            }

        val paramsBuilder = (nextParams ?: currentParams).toBuilderWithToolRunnerHeader()
        nextParams = null
        val hasSummary =
            message.content().any {
                !it.compaction().getOrNull()?.content()?.getOrNull().isNullOrEmpty()
            }
        if (!hasSummary) {
            warn("Compaction produced no summary; keeping the conversation as it is.")
            return paramsBuilder
        }
        lastToolResponse = null
        // The response has to be sent back as it came, first, replacing the messages it summarizes.
        return paramsBuilder.messages(listOf(message.toParamKeepingUnknownBlocks()))
    }

    private fun compactionToSendAfterFinalTurn(message: BetaMessage): BetaCompactionConfig? {
        val scheduled = compaction as? Compaction.Scheduled ?: return null
        // A turn that was cut short can end with tool calls that are never run, and the API can't
        // compact a conversation whose last turn has an unanswered tool call.
        if (message.content().any { it.isToolUse() }) {
            val stopReason = message.stopReason().map { " (stop_reason=$it)" }.orElse("")
            warn(
                "The pending compaction was skipped because the last turn$stopReason ended with " +
                    "tool calls that were not run. Call `compactBeforeNextTurn()` again if you " +
                    "continue the conversation."
            )
            compaction = Compaction.Idle
            return null
        }
        return scheduled.config
    }

    /**
     * [BetaMessage.toParam], except that a block of a type newer than this SDK goes back as the raw
     * JSON it came as, where [BetaContentBlock.toParam] throws.
     */
    private fun BetaMessage.toParamKeepingUnknownBlocks(): BetaMessageParam =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.ASSISTANT)
            .contentOfBetaContentBlockParams(
                content().map { block ->
                    block
                        ._json()
                        .getOrNull()
                        ?.takeIf { block.type().value() == BetaContentBlock.Type.Value._UNKNOWN }
                        ?.convert(BetaContentBlockParam::class.java) ?: block.toParam()
                }
            )
            .build()

    private fun warn(message: String) {
        System.err.println("WARNING: `BetaToolRunner`: $message")
    }

    private fun MessageCreateParams.toBuilderWithToolRunnerHeader(): MessageCreateParams.Builder =
        toBuilder()
            .replaceAdditionalHeaders(
                STAINLESS_HELPER_HEADER,
                mergedStainlessHelperValue(
                    _additionalHeaders(),
                    StainlessHelperHeaderValue.BETA_TOOL_RUNNER,
                ),
            )

    /** What the loop does with a turn once it has been yielded, decided by its stop reason. */
    private enum class NextStep {
        /**
         * Answer the turn's client tool calls and continue; with none to answer, the loop is done.
         */
        RUN_TOOLS,
        /**
         * The turn isn't finished: sending it back unchanged, without answering any tool calls,
         * lets the server continue it.
         */
        RESUME,
        /**
         * The turn is final. Its tool calls are not executed: they belong to a conversation that
         * has ended, so running them would fire side effects the caller never confirmed and produce
         * tool_results that cannot be coherently replayed.
         */
        STOP,
    }

    /**
     * Sorts every stop reason into a [NextStep]. The `when` has no `else`, so a newly generated
     * [BetaStopReason.Value] member fails to compile here until it is classified.
     */
    private fun determineNextStepFromStopReason(stopReason: Optional<BetaStopReason>): NextStep {
        val value = stopReason.getOrNull()?.value() ?: return NextStep.STOP
        return when (value) {
            BetaStopReason.Value.TOOL_USE -> NextStep.RUN_TOOLS
            BetaStopReason.Value.PAUSE_TURN,
            // pause_after_compaction hands the turn back before the model answers; sending it
            // back unchanged continues it.
            BetaStopReason.Value.COMPACTION -> NextStep.RESUME
            BetaStopReason.Value.END_TURN,
            BetaStopReason.Value.MAX_TOKENS,
            BetaStopReason.Value.STOP_SEQUENCE,
            BetaStopReason.Value.REFUSAL,
            BetaStopReason.Value.MODEL_CONTEXT_WINDOW_EXCEEDED,
            // A stop reason newer than this SDK stops the loop rather than throwing.
            BetaStopReason.Value._UNKNOWN -> NextStep.STOP
        }
    }

    /**
     * Carries the container the last turn ran in onto the next request: container-bound server
     * tools reject a follow-up that omits it, so its id is forwarded unless the caller pinned a
     * container themselves (a pinned [BetaContainerParams] without an id has the id filled in).
     */
    private fun MessageCreateParams.Builder.adoptContainer(message: BetaMessage) = apply {
        val id = message._container().asKnown().getOrNull()?._id()?.asString()?.getOrNull()
        if (id.isNullOrEmpty()) {
            return@apply
        }
        // The builder has no accessors, but `currentParams` was built from it this iteration.
        val pinned = currentParams._container()
        if (pinned.isMissing() || pinned.isNull()) {
            container(id)
        } else {
            pinned
                .asKnown()
                .getOrNull()
                ?.betaContainerParams()
                ?.getOrNull()
                ?.takeIf { it._id().isMissing() || it._id().isNull() }
                ?.let { container(it.toBuilder().id(id).build()) }
        }
    }

    private fun generateToolResponse(lastMessage: BetaMessageParam): BetaMessageParam? {
        if (lastMessage.roleString() != "assistant") {
            return null
        }

        val contentBlockParams =
            lastMessage.content().betaContentBlockParams().getOrNull() ?: return null
        // Tool calls before the last fallback block belong to the attempt that refused; the
        // fallback interceptor strips them from replayed history, so answering them would orphan
        // the tool_result.
        val lastSeam = contentBlockParams.indexOfLast { it.isFallback() }
        val toolUseBlockParams =
            contentBlockParams.drop(lastSeam + 1).flatMap { it.toolUse().asSequence() }
        if (toolUseBlockParams.isEmpty()) {
            return null
        }

        val toolsByName = currentParams.runnableTools().associateBy { it.name() }
        val availableToolNames = availableToolNames()
        return BetaMessageParam.builder()
            .role(BetaMessageParam.Role.USER)
            .contentOfBetaContentBlockParams(
                toolUseBlockParams.map { toolUse ->
                    BetaContentBlockParam.ofToolResult(
                        generateToolUseResult(toolUse, toolsByName, availableToolNames)
                    )
                }
            )
            .build()
            .also { lastToolResponse = it }
    }

    /**
     * Returns the names of the tools that are currently available to the assistant.
     *
     * Starts from every runnable tool's name and folds the `tool_removal`/`tool_addition` blocks of
     * the `"system"` messages, in request order. A removed tool can still receive a `tool_use` from
     * the model, so dispatch checks membership in this set and routes a removed name down the same
     * not-found path as a tool that was never declared.
     */
    private fun availableToolNames(): MutableSet<String> {
        val available = currentParams.runnableTools().map { it.name() }.toMutableSet()
        // The assistant message being answered is either the last message in the history or hasn't
        // been added to it yet, so every `"system"` message here precedes it.
        for (message in currentParams.messages().filter { it.isSystem() }) {
            // A `"system"` message whose content is a plain string carries no blocks.
            val content =
                message._content().asKnown().getOrNull()?.betaContentBlockParams()?.getOrNull()
                    ?: continue
            for (block in content) {
                applyToolChange(block, available)
            }
        }
        return available
    }

    /**
     * A message in the history may carry `role` either as a known [BetaMessageParam.Role] or as a
     * raw JSON string (e.g. one set via [JsonValue]), so the throwing [BetaMessageParam.role]
     * accessor can't be used on the history. Both shapes are read back as their non-throwing string
     * form instead.
     */
    private fun BetaMessageParam.roleString(): String? =
        (_role().asKnown().getOrNull()?._value() ?: _role()).asString().getOrNull()

    private fun BetaMessageParam.isSystem(): Boolean = roleString() == "system"

    private fun applyToolChange(block: BetaContentBlockParam, available: MutableSet<String>) {
        when {
            block.isToolRemoval() ->
                block.asToolRemoval().tool().referencedToolName()?.let(available::remove)
            block.isToolAddition() ->
                block.asToolAddition().tool().referencedToolName()?.let(available::add)
        }
    }

    private fun BetaRequestToolRemovalBlock.Tool.referencedToolName(): String? =
        when {
            isReference() -> asReference().name()
            // MCP references are executed server-side, so they don't affect runnable tools.
            else -> null // unknown reference types are ignored for forward compatibility
        }

    private fun BetaRequestToolAdditionBlock.Tool.referencedToolName(): String? =
        when {
            isReference() -> asReference().name()
            // MCP references are executed server-side, so they don't affect runnable tools.
            else -> null // unknown reference types are ignored for forward compatibility
        }

    private fun generateToolUseResult(
        toolUse: BetaToolUseBlockParam,
        toolsByName: Map<String, BetaRunnableTool>,
        availableToolNames: Set<String>,
    ): BetaToolResultBlockParam =
        when (toolUse.name()) {
            // Memory tool commands have the same type (`"tool_use"`) as other tool use blocks, but
            // the tool name is always `"memory"`.
            "memory" -> generateMemoryToolUseResult(toolUse)
            else -> generateGenericToolUseResult(toolUse, toolsByName, availableToolNames)
        }

    private fun generateGenericToolUseResult(
        toolUse: BetaToolUseBlockParam,
        toolsByName: Map<String, BetaRunnableTool>,
        availableToolNames: Set<String>,
    ): BetaToolResultBlockParam {
        val tool =
            toolsByName[toolUse.name()].takeIf { toolUse.name() in availableToolNames }
                ?: return BetaToolResultBlockParam.builder()
                    .toolUseId(toolUse.id())
                    .content("Error: Tool '${toolUse.name()}' not found")
                    .isError(true)
                    .build()
        val content =
            try {
                tool.run(toolUse._input())
            } catch (e: Exception) {
                return BetaToolResultBlockParam.builder()
                    .toolUseId(toolUse.id())
                    .content("Error: ${e.message}")
                    .isError(true)
                    .build()
            }

        return BetaToolResultBlockParam.builder().toolUseId(toolUse.id()).content(content).build()
    }

    /**
     * Generates a tool use result for a memory tool command. There can be only one memory tool and
     * its name must be `"memory"`.
     */
    private fun generateMemoryToolUseResult(
        toolUse: BetaToolUseBlockParam
    ): BetaToolResultBlockParam {
        val command =
            outputTypeFromJson(
                toJsonString(toolUse._input()),
                BetaMemoryTool20250818Command::class.java,
            )
        // Allow an exception if this is not set. `ToolRunnerCreateParams.Builder` should ensure
        // that it is present if the request declared a memory tool.
        val handler = params.betaMemoryToolHandler().get()
        val memoryToolOutput =
            try {
                command.accept(
                    object : BetaMemoryTool20250818Command.Visitor<String> {
                        override fun visitView(view: BetaMemoryTool20250818ViewCommand): String =
                            handler.view(view.path(), view.viewRange())

                        override fun visitCreate(
                            create: BetaMemoryTool20250818CreateCommand
                        ): String = handler.create(create.path(), create.fileText())

                        override fun visitStrReplace(
                            strReplace: BetaMemoryTool20250818StrReplaceCommand
                        ): String =
                            handler.strReplace(
                                strReplace.path(),
                                strReplace.oldStr(),
                                strReplace.newStr(),
                            )

                        override fun visitInsert(
                            insert: BetaMemoryTool20250818InsertCommand
                        ): String =
                            handler.insert(insert.path(), insert.insertLine(), insert.insertText())

                        override fun visitDelete(
                            delete: BetaMemoryTool20250818DeleteCommand
                        ): String = handler.delete(delete.path())

                        override fun visitRename(
                            rename: BetaMemoryTool20250818RenameCommand
                        ): String = handler.rename(rename.oldPath(), rename.newPath())
                    }
                )
            } catch (e: Exception) {
                return BetaToolResultBlockParam.builder()
                    .toolUseId(toolUse.id())
                    .content("Error: ${e.message}")
                    .isError(true)
                    .build()
            }

        return BetaToolResultBlockParam.builder()
            .toolUseId(toolUse.id())
            .content(memoryToolOutput)
            .build()
    }
}
