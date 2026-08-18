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

    override fun iterator(): Iterator<BetaMessage> {
        if (consumed.getAndSet(true)) {
            throw IllegalStateException("Cannot iterate the same `BetaToolRunner` twice")
        }

        return iterator {
            var paramsBuilder = params.initialMessageParams.toBuilderWithToolRunnerHeader()

            for (iteration in 0.until(params.maxIterations().orElse(Long.MAX_VALUE))) {
                currentParams = paramsBuilder.build()
                val message = messageService.create(currentParams, requestOptions)
                yield(message)

                val nextParams = nextParams
                if (nextParams == null) {
                    // A refusal-terminated turn is terminal: its tool calls belong to a dead
                    // conversation, so executing them would fire side effects the caller never
                    // confirmed and produce tool_results that cannot be coherently replayed.
                    if (message.stopReason().getOrNull() == BetaStopReason.REFUSAL) {
                        break
                    }
                    paramsBuilder
                        .addMessage(message)
                        .adoptContainer(message)
                        .addMessage(generateToolResponse(message.toParam()) ?: break)
                } else {
                    paramsBuilder = nextParams.toBuilderWithToolRunnerHeader()
                    this@BetaToolRunner.nextParams = null
                }
            }
        }
    }

    /** Returns an [Iterable] that yields streamed assistant messages instead of buffered ones. */
    fun streaming(): Iterable<StreamResponse<BetaRawMessageStreamEvent>> =
        object : Iterable<StreamResponse<BetaRawMessageStreamEvent>> {

            override fun iterator(): Iterator<StreamResponse<BetaRawMessageStreamEvent>> {
                if (consumed.getAndSet(true)) {
                    throw IllegalStateException("Cannot iterate the same `BetaToolRunner` twice")
                }

                return iterator {
                    var paramsBuilder = params.initialMessageParams.toBuilderWithToolRunnerHeader()

                    for (iteration in 0.until(params.maxIterations().orElse(Long.MAX_VALUE))) {
                        currentParams = paramsBuilder.build()

                        val accumulator = BetaMessageAccumulator.create()
                        val streamResponse =
                            object : StreamResponse<BetaRawMessageStreamEvent> {

                                private val delegate =
                                    messageService.createStreaming(currentParams, requestOptions)

                                override fun stream(): Stream<BetaRawMessageStreamEvent> =
                                    delegate.stream().peek(accumulator::accumulate)

                                override fun close() = delegate.close()
                            }
                        streamResponse.use { yield(it) }

                        val message = accumulator.message()
                        val nextParams = nextParams
                        if (nextParams == null) {
                            // A refusal-terminated turn is terminal: its tool calls belong to a
                            // dead conversation, so executing them would fire side effects the
                            // caller never confirmed and produce tool_results that cannot be
                            // coherently replayed.
                            if (message.stopReason().getOrNull() == BetaStopReason.REFUSAL) {
                                break
                            }
                            paramsBuilder
                                .addMessage(message)
                                .adoptContainer(message)
                                .addMessage(generateToolResponse(message.toParam()) ?: break)
                        } else {
                            paramsBuilder = nextParams.toBuilderWithToolRunnerHeader()
                            this@BetaToolRunner.nextParams = null
                        }
                    }
                }
            }
        }

    /** Returns the current params being used by [BetaToolRunner]. */
    fun params(): MessageCreateParams = currentParams

    /** Sets the parameters for the next API call, invalidating any cached tool response. */
    fun setNextParams(nextParams: MessageCreateParams) {
        lastToolResponse = null
        this.nextParams = nextParams
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

    private fun MessageCreateParams.toBuilderWithToolRunnerHeader(): MessageCreateParams.Builder =
        toBuilder()
            .replaceAdditionalHeaders(
                STAINLESS_HELPER_HEADER,
                mergedStainlessHelperValue(
                    _additionalHeaders(),
                    StainlessHelperHeaderValue.BETA_TOOL_RUNNER,
                ),
            )

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
        toolsByName: Map<String, RunnableTool>,
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
        toolsByName: Map<String, RunnableTool>,
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
