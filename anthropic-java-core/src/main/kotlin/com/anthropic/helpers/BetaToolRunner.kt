package com.anthropic.helpers

import com.anthropic.core.*
import com.anthropic.core.http.StreamResponse
import com.anthropic.core.outputTypeFromJson
import com.anthropic.core.toJsonString
import com.anthropic.core.toolFromClass
import com.anthropic.models.beta.messages.*
import com.anthropic.services.blocking.beta.MessageService
import java.util.Optional
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Supplier
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
            var paramsBuilder = params.initialMessageParams.toBuilder()

            for (iteration in 0.until(params.maxIterations().orElse(Long.MAX_VALUE))) {
                currentParams = paramsBuilder.build()
                val message = messageService.create(currentParams, requestOptions)
                yield(message)

                val nextParams = nextParams
                if (nextParams == null) {
                    paramsBuilder
                        .addMessage(message)
                        .addMessage(generateToolResponse(message.toParam()) ?: break)
                } else {
                    paramsBuilder = nextParams.toBuilder()
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
                    var paramsBuilder = params.initialMessageParams.toBuilder()

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
                            paramsBuilder
                                .addMessage(message)
                                .addMessage(generateToolResponse(message.toParam()) ?: break)
                        } else {
                            paramsBuilder = nextParams.toBuilder()
                            this@BetaToolRunner.nextParams = null
                        }
                    }
                }
            }
        }

    /** Returns the current params being used by [BetaToolRunner]. */
    fun params(): MessageCreateParams = currentParams

    /** Sets the parameters for the next API call. */
    fun setNextParams(nextParams: MessageCreateParams) {
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

    private fun generateToolResponse(lastMessage: BetaMessageParam): BetaMessageParam? {
        if (lastMessage._role() != JsonValue.from("assistant")) {
            return null
        }

        val contentBlockParams =
            lastMessage.content().betaContentBlockParams().getOrNull() ?: return null
        val toolUseBlockParams = contentBlockParams.flatMap { it.toolUse().asSequence() }
        if (toolUseBlockParams.isEmpty()) {
            return null
        }

        val toolsByName =
            currentParams
                .toolParametersTypes()
                .asSequence()
                .map { RunnableTool(it) }
                .groupBy { it.name() }
        return BetaMessageParam.builder()
            .role(BetaMessageParam.Role.USER)
            .contentOfBetaContentBlockParams(
                toolUseBlockParams.map { toolUse ->
                    BetaContentBlockParam.ofToolResult(generateToolUseResult(toolUse, toolsByName))
                }
            )
            .build()
            .also { lastToolResponse = it }
    }

    private fun generateToolUseResult(
        toolUse: BetaToolUseBlockParam,
        toolsByName: Map<String, List<RunnableTool>>,
    ): BetaToolResultBlockParam {
        val tool =
            toolsByName[toolUse.name()]?.firstOrNull()
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
}

private data class RunnableTool(private val parametersType: Class<*>) {

    private val tool = toolFromClass(parametersType)

    fun name() = tool.name()

    fun run(input: JsonField<*>): BetaToolResultBlockParam.Content {
        val parsed = outputTypeFromJson(toJsonString(input), parametersType)
        if (parsed !is Supplier<*>) {
            throw IllegalStateException("Cannot run non-`Supplier` tool")
        }

        return when (val output = parsed.get()) {
            is String -> BetaToolResultBlockParam.Content.ofString(output)
            is BetaToolResultBlockParam.Content -> output
            else ->
                throw IllegalStateException(
                    "Expected tool to return `String` or `BetaToolResultBlockParam.Content`"
                )
        }
    }
}
