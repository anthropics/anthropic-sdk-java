@file:JvmName("ResponseAttributes")

package com.anthropic.internal.telemetry

import com.anthropic.models.beta.messages.BetaMessage
import com.anthropic.models.messages.Message
import io.opentelemetry.api.trace.Span
import kotlin.jvm.optionals.getOrNull

/**
 * Sets the attributes that come from [message], the parsed response of a `messages.create` API
 * call, or as much of it as a stream delivered. The ones that a stream delivers with
 * `message_delta`, its output tokens and its stop reason, are set only when [complete] is true.
 */
@JvmSynthetic
internal fun setMessageAttributes(span: Span, message: Message, complete: Boolean = true) {
    val usage = message.usage()
    val cacheCreation = usage.cacheCreation().getOrNull()
    setMessageAttributes(
        span,
        MessageFields(
            id = message.id(),
            model = message.model().asString(),
            stopReason = message.stopReason().getOrNull()?.asString(),
            inputTokens = usage.inputTokens(),
            cacheReadInputTokens = usage.cacheReadInputTokens().getOrNull(),
            cacheCreationInputTokens = usage.cacheCreationInputTokens().getOrNull(),
            ephemeral5mInputTokens = cacheCreation?.ephemeral5mInputTokens(),
            ephemeral1hInputTokens = cacheCreation?.ephemeral1hInputTokens(),
            serviceTier = usage.serviceTier().getOrNull()?.asString(),
            outputTokens = usage.outputTokens(),
            thinkingTokens = usage.outputTokensDetails().getOrNull()?.thinkingTokens(),
        ),
        complete,
    )
}

/** [setMessageAttributes] for the beta API. */
@JvmSynthetic
internal fun setMessageAttributes(span: Span, message: BetaMessage, complete: Boolean = true) {
    val usage = message.usage()
    val cacheCreation = usage.cacheCreation().getOrNull()
    val compactions =
        usage
            .iterations()
            .getOrNull()
            .orEmpty()
            .mapNotNull { it.compaction().getOrNull() }
            .ifEmpty { null }
    setMessageAttributes(
        span,
        MessageFields(
            id = message.id(),
            model = message.model().asString(),
            stopReason = message.stopReason().getOrNull()?.asString(),
            inputTokens = usage.inputTokens(),
            cacheReadInputTokens = usage.cacheReadInputTokens().getOrNull(),
            cacheCreationInputTokens = usage.cacheCreationInputTokens().getOrNull(),
            ephemeral5mInputTokens = cacheCreation?.ephemeral5mInputTokens(),
            ephemeral1hInputTokens = cacheCreation?.ephemeral1hInputTokens(),
            serviceTier = usage.serviceTier().getOrNull()?.asString(),
            outputTokens = usage.outputTokens(),
            thinkingTokens = usage.outputTokensDetails().getOrNull()?.thinkingTokens(),
            // The input tokens of a compaction are the sum that those of the Message are.
            compactionInputTokens =
                compactions?.sumOf {
                    it.inputTokens() + it.cacheReadInputTokens() + it.cacheCreationInputTokens()
                },
            compactionOutputTokens = compactions?.sumOf { it.outputTokens() },
        ),
        complete,
    )
}

/**
 * What the span of a `messages.create` API call records of a Message, as the stable and the beta
 * Message both have it.
 */
private class MessageFields(
    val id: String,
    val model: String,
    val stopReason: String?,
    val inputTokens: Long,
    val cacheReadInputTokens: Long?,
    val cacheCreationInputTokens: Long?,
    val ephemeral5mInputTokens: Long?,
    val ephemeral1hInputTokens: Long?,
    val serviceTier: String?,
    val outputTokens: Long,
    val thinkingTokens: Long?,
    /** The tokens of the compactions in the usage's iterations, which only the beta API has. */
    val compactionInputTokens: Long? = null,
    val compactionOutputTokens: Long? = null,
)

private fun setMessageAttributes(span: Span, message: MessageFields, complete: Boolean) {
    span.setAttribute(GEN_AI_RESPONSE_ID, message.id)
    span.setAttribute(GEN_AI_RESPONSE_MODEL, message.model)
    // The conventions define the input tokens as the sum of these three.
    span.setAttribute(
        GEN_AI_USAGE_INPUT_TOKENS,
        message.inputTokens +
            (message.cacheReadInputTokens ?: 0) +
            (message.cacheCreationInputTokens ?: 0),
    )
    message.cacheCreationInputTokens?.let {
        span.setAttribute(GEN_AI_USAGE_CACHE_WRITE_INPUT_TOKENS, it)
    }
    message.cacheReadInputTokens?.let {
        span.setAttribute(GEN_AI_USAGE_CACHE_READ_INPUT_TOKENS, it)
    }
    message.ephemeral5mInputTokens?.let {
        span.setAttribute(ANTHROPIC_USAGE_CACHE_CREATION_EPHEMERAL_5M_INPUT_TOKENS, it)
    }
    message.ephemeral1hInputTokens?.let {
        span.setAttribute(ANTHROPIC_USAGE_CACHE_CREATION_EPHEMERAL_1H_INPUT_TOKENS, it)
    }
    message.serviceTier?.let { span.setAttribute(ANTHROPIC_RESPONSE_SERVICE_TIER, it) }
    if (!complete) {
        return
    }
    span.setAttribute(GEN_AI_USAGE_OUTPUT_TOKENS, message.outputTokens)
    message.thinkingTokens?.let { span.setAttribute(GEN_AI_USAGE_REASONING_OUTPUT_TOKENS, it) }
    message.stopReason?.let { span.setAttribute(ANTHROPIC_MESSAGE_STOP_REASON, it) }
    message.compactionInputTokens?.let {
        span.setAttribute(ANTHROPIC_USAGE_COMPACTION_INPUT_TOKENS, it)
    }
    message.compactionOutputTokens?.let {
        span.setAttribute(ANTHROPIC_USAGE_COMPACTION_OUTPUT_TOKENS, it)
    }
}
