@file:JvmName("ToolRunnerSpans")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.opentelemetry.ERROR_TYPE
import com.anthropic.core.opentelemetry.isCancellation
import com.anthropic.core.opentelemetry.tracer
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams as BetaMessageCreateParams
import com.fasterxml.jackson.databind.JsonNode
import io.opentelemetry.api.trace.Span
import io.opentelemetry.api.trace.SpanBuilder
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.context.Context
import kotlin.jvm.optionals.getOrNull

/**
 * Starts the `anthropic.messages.tool_runner` span of one loop of the Messages tool runner, whose
 * first request is for [model] and has [params], as a child of the current span. `null` when the
 * client emits no spans. The runner sets its status and ends it.
 *
 * Whether [params] set a JSON output format is read here, for a client that emits spans only. The
 * typed getters throw when the application set the field as raw JSON; that is logged, the span goes
 * without `gen_ai.output.type`, and the runner runs.
 */
@JvmSynthetic
internal fun startToolRunnerSpan(
    clientOptions: ClientOptions,
    model: String?,
    params: BetaMessageCreateParams,
    conversationId: String?,
): Span? {
    val span =
        tracer(clientOptions)
            ?.spanBuilder("anthropic.messages.tool_runner")
            ?.setSpanKind(SpanKind.INTERNAL)
            ?.setAttribute(GEN_AI_OPERATION_NAME, GenAiOperationNameValues.INVOKE_AGENT)
            ?.setAttribute(GEN_AI_PROVIDER_NAME, clientOptions.openTelemetryProviderName)
            ?: return null
    model?.let { span.setAttribute(GEN_AI_REQUEST_MODEL, it) }
    try {
        if (params.setsJsonOutputFormat()) {
            span.setAttribute(GEN_AI_OUTPUT_TYPE, GenAiOutputTypeValues.JSON)
        }
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the tool runner's params", e)
    }
    conversationId?.let { span.setAttribute(GEN_AI_CONVERSATION_ID, it) }
    return startOrNull(clientOptions, span)
}

/** The span of a tool run, named by who defined the tool. */
internal enum class ToolUseSpan(val spanName: String) {
    /** A tool that the application defined. */
    CUSTOM("anthropic.custom_tool_use"),

    /** A tool that an MCP helper made. */
    MCP("anthropic.mcp_tool_use"),
}

/** How a tool run ended. */
internal sealed class ToolRunOutcome {

    /** The tool returned, and the runner sends [result] back to the model. */
    class Succeeded(val result: BetaToolResultBlockParam) : ToolRunOutcome()

    /**
     * The tool failed. The runner sends [result], an error result, back to the model; it is `null`
     * when the run threw out of the runner, which then sends nothing.
     */
    class Failed(val result: BetaToolResultBlockParam?) : ToolRunOutcome()

    /**
     * The application canceled the run, see [isCancellation]. That is not a failure of the tool.
     */
    object Canceled : ToolRunOutcome()
}

/**
 * The outcome of a tool run that the runner made [result] of, or that threw out of the runner when
 * [result] is `null`. [error] is what the run threw, also when [result] reports it.
 */
@JvmSynthetic
internal fun toolRunOutcome(result: BetaToolResultBlockParam?, error: Throwable?): ToolRunOutcome =
    when {
        result != null && result._isError().asBoolean().getOrNull() != true ->
            ToolRunOutcome.Succeeded(result)
        error != null && isCancellation(error) -> ToolRunOutcome.Canceled
        else -> ToolRunOutcome.Failed(result)
    }

/**
 * Starts the span of one run of the tool that [toolUse] calls, as a child of the span in
 * [parentOtelContext]. The span's name is that of [kind] with the tool's name after a space, or
 * alone when the tool has none. The span has the tool's [description] whether or not the client
 * records content. Returns `null` when the client emits no spans.
 */
@JvmSynthetic
internal fun startToolUseSpan(
    clientOptions: ClientOptions,
    kind: ToolUseSpan,
    toolUse: BetaToolUseBlockParam,
    description: String?,
    conversationId: String?,
    parentOtelContext: Context,
): Span? {
    val tracer = tracer(clientOptions) ?: return null
    val name = toolUse._name().asString().getOrNull().orEmpty()
    return tracer
        .spanBuilder(if (name.isEmpty()) kind.spanName else "${kind.spanName} $name")
        .setSpanKind(SpanKind.INTERNAL)
        .setParent(parentOtelContext)
        .setAttribute(GEN_AI_OPERATION_NAME, GenAiOperationNameValues.EXECUTE_TOOL)
        .setAttribute(GEN_AI_PROVIDER_NAME, clientOptions.openTelemetryProviderName)
        .setAttribute(GEN_AI_TOOL_NAME, name)
        .setAttribute(GEN_AI_TOOL_CALL_ID, toolUse._id().asString().getOrNull().orEmpty())
        .setAttribute(GEN_AI_TOOL_TYPE, GenAiToolTypeValues.FUNCTION)
        .apply {
            if (!description.isNullOrEmpty()) {
                setAttribute(GEN_AI_TOOL_DESCRIPTION, description)
            }
            conversationId?.let { setAttribute(GEN_AI_CONVERSATION_ID, it) }
            toolUse._toolsetName().asString().getOrNull()?.let {
                setAttribute(ANTHROPIC_TOOLSET_NAME, it)
            }
        }
        .let { startOrNull(clientOptions, it) }
}

/**
 * Starts the span of [builder]. Starting it runs the application's sampler and span processors,
 * whose failure is not the runner's: it is logged, and the runner runs as if tracing were off.
 */
private fun startOrNull(clientOptions: ClientOptions, builder: SpanBuilder): Span? =
    try {
        builder.startSpan()
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not start a span of the tool runner", e)
        null
    }

/**
 * Ends [span], the span of the run of the tool that [toolUse] calls, with [outcome]: `OK` for a
 * success, `ERROR` with `tool_error` for a failure, and no status for a canceled run, as for a
 * canceled API call. In content mode the span first gets the tool call's content
 * ([setToolCallContent]).
 */
@JvmSynthetic
internal fun endToolUseSpan(
    clientOptions: ClientOptions,
    span: Span,
    toolUse: BetaToolUseBlockParam,
    outcome: ToolRunOutcome,
) {
    if (recordsContent(clientOptions) && span.isRecording) {
        setToolCallContent(clientOptions, span, toolUse, outcome)
    }
    // Ending the span runs the application's span processors, whose failure is not the tool's.
    try {
        when (outcome) {
            is ToolRunOutcome.Succeeded -> span.setStatus(StatusCode.OK)
            is ToolRunOutcome.Canceled -> {}
            is ToolRunOutcome.Failed -> {
                span.setStatus(StatusCode.ERROR)
                span.setAttribute(ERROR_TYPE, "tool_error")
            }
        }
        span.end()
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not end the span of the tool run", e)
    }
}

/**
 * Sets the tool's input, and the content blocks of the result that is sent back to the model, for
 * an error result too: whether the result is an error is the span's `error.type`. Together the two
 * stay within the [contentLimit], in bytes of UTF-8, where removing can bring them there. No bytes
 * are counted when [MAX_UTF8_BYTES_PER_CHAR] for each char of the two fits. Otherwise, first, an
 * input that alone is over the limit is left out whole, and the result then has all of it. Then the
 * result's content blocks go, the last one first, until the list is empty. When anything was
 * removed, the span has `anthropic.content.truncated`. A failure to record is logged and does not
 * fail the tool run.
 */
private fun setToolCallContent(
    clientOptions: ClientOptions,
    span: Span,
    toolUse: BetaToolUseBlockParam,
    outcome: ToolRunOutcome,
) {
    try {
        val mapper = clientOptions.jsonMapper
        val arguments = mapper.writeValueAsString(toolUse._input())
        val result =
            when (outcome) {
                is ToolRunOutcome.Succeeded -> outcome.result
                is ToolRunOutcome.Failed -> outcome.result
                is ToolRunOutcome.Canceled -> null
            }
        val blocks =
            result?.let {
                toolResultContent(mapper.valueToTree<JsonNode>(it).get("content")).map { block ->
                    mapper.writeValueAsString(block)
                }
            }
        val limit = contentLimit(clientOptions)
        val chars = arguments.length + "[]".length + blocks.orEmpty().sumOf { it.length + 1L }
        val fitsByLength = MAX_UTF8_BYTES_PER_CHAR * chars <= limit
        val argumentsSize = if (fitsByLength) 0 else utf8Length(arguments)
        val keepsArguments = argumentsSize <= limit
        if (keepsArguments) {
            span.setAttribute(GEN_AI_TOOL_CALL_ARGUMENTS, arguments)
        } else {
            span.setAttribute(ANTHROPIC_CONTENT_TRUNCATED, true)
        }
        if (blocks == null) {
            return
        }
        val room = if (keepsArguments) limit - argumentsSize else limit
        val content =
            if (fitsByLength) Reduction(blocks.joinToString(",", "[", "]"), false)
            else truncateTrailing(blocks, room, "[", "]")
        span.setAttribute(ANTHROPIC_TOOL_RESULT_CONTENT, content.json)
        if (content.reduced) {
            span.setAttribute(ANTHROPIC_CONTENT_TRUNCATED, true)
        }
    } catch (e: Exception) {
        logTelemetryFailure(clientOptions, "Could not record the tool run on its span", e)
    }
}
