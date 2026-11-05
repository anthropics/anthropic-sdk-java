@file:JvmName("ResponseAttributes")

package com.anthropic.internal.telemetry

import com.anthropic.core.JsonField
import com.anthropic.core.JsonValue
import com.anthropic.core.opentelemetry.ApiCall
import com.anthropic.core.opentelemetry.integerOrNull
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.Span
import kotlin.jvm.optionals.getOrNull

/**
 * Sets the input tokens of [usage] and, when [complete], the tokens of the compactions in its
 * iterations: a stream delivers the iterations with `message_delta`.
 */
@JvmSynthetic
internal fun setUsageAttributes(span: Span, usage: JsonField<*>, complete: Boolean = true) {
    // By name, so that a property that the API no longer has reads as not set. This converts the
    // whole usage, which is small, and only for a span that records.
    val json = usage.asKnown().getOrNull()?.let { JsonValue.from(it) }
    if (json.at("input_tokens")?.isNull() == false) {
        json.inputTokens()?.let { span.setAttribute(GEN_AI_USAGE_INPUT_TOKENS, it) }
    }
    if (!complete) {
        return
    }
    val compactions =
        json.at("iterations")?.asArray()?.getOrNull().orEmpty().filter {
            it.at("type")?.asString()?.getOrNull() == "compaction"
        }
    if (compactions.isEmpty()) {
        return
    }
    compactions
        .sumOrNull { it.inputTokens() }
        ?.let { span.setAttribute(ANTHROPIC_USAGE_COMPACTION_INPUT_TOKENS, it) }
    compactions
        .sumOrNull { it.tokens("output_tokens") }
        ?.let { span.setAttribute(ANTHROPIC_USAGE_COMPACTION_OUTPUT_TOKENS, it) }
}

/**
 * The input tokens of this usage, or of this iteration of one. The conventions define them as the
 * sum of these three.
 */
private fun JsonValue?.inputTokens(): Long? =
    tokens("input_tokens", "cache_read_input_tokens", "cache_creation_input_tokens")

/**
 * The sum of the properties [names] of this object, each a count of tokens. One that is not set
 * counts no tokens. One that is set to anything but a count leaves no sum that is right: `null`.
 */
private fun JsonValue?.tokens(vararg names: String): Long? =
    names.asList().sumOrNull { name ->
        val count = at(name)
        if (count == null || count.isNull()) 0
        else count.asNumber().getOrNull()?.let { integerOrNull(it) }
    }

/** The sum of what [count] gives for each element, or `null` when it gives `null` for one. */
private inline fun <T> List<T>.sumOrNull(count: (T) -> Long?): Long? = sumOf {
    count(it) ?: return null
}

/** The JSON of the property [name] of this object, or `null` when it is not set. */
private fun JsonValue?.at(name: String): JsonValue? = this?.asObject()?.getOrNull()?.get(name)

/**
 * The attributes that [call] takes from [message], as much of a Message as its stream delivered.
 * Until `message_delta` arrived ([complete]), they are the [MESSAGE_START_ATTRIBUTES] only.
 */
@JvmSynthetic
internal fun streamedMessageAttributes(call: ApiCall, message: Any, complete: Boolean): Attributes {
    val attributes = call.responseAttributes(message)
    return if (complete) attributes
    else attributes.toBuilder().removeIf { it.key !in MESSAGE_START_ATTRIBUTES }.build()
}

/**
 * The response attributes of `messages.create` whose values `message_start` already has for good.
 * For any other, the streamed Message can have a placeholder until `message_delta` arrives.
 */
@get:JvmSynthetic
internal val MESSAGE_START_ATTRIBUTES: Set<String> =
    setOf(
        "gen_ai.response.id",
        "gen_ai.response.model",
        "gen_ai.usage.cache_write.input_tokens",
        "gen_ai.usage.cache_read.input_tokens",
        "anthropic.usage.cache_creation.ephemeral_5m_input_tokens",
        "anthropic.usage.cache_creation.ephemeral_1h_input_tokens",
        "anthropic.response.service_tier",
    )
