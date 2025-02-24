// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class ThinkingConfigEnabled
@JsonCreator
private constructor(
    @JsonProperty("budget_tokens")
    @ExcludeMissing
    private val budgetTokens: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * Determines how many tokens Claude can use for its internal reasoning process. Larger budgets
     * can enable more thorough analysis for complex problems, improving response quality.
     *
     * Must be ≥1024 and less than `max_tokens`.
     *
     * See
     * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
     * for details.
     */
    fun budgetTokens(): Long = budgetTokens.getRequired("budget_tokens")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Determines how many tokens Claude can use for its internal reasoning process. Larger budgets
     * can enable more thorough analysis for complex problems, improving response quality.
     *
     * Must be ≥1024 and less than `max_tokens`.
     *
     * See
     * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
     * for details.
     */
    @JsonProperty("budget_tokens")
    @ExcludeMissing
    fun _budgetTokens(): JsonField<Long> = budgetTokens

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ThinkingConfigEnabled = apply {
        if (validated) {
            return@apply
        }

        budgetTokens()
        _type().let {
            if (it != JsonValue.from("enabled")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThinkingConfigEnabled]. */
    class Builder internal constructor() {

        private var budgetTokens: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("enabled")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(thinkingConfigEnabled: ThinkingConfigEnabled) = apply {
            budgetTokens = thinkingConfigEnabled.budgetTokens
            type = thinkingConfigEnabled.type
            additionalProperties = thinkingConfigEnabled.additionalProperties.toMutableMap()
        }

        /**
         * Determines how many tokens Claude can use for its internal reasoning process. Larger
         * budgets can enable more thorough analysis for complex problems, improving response
         * quality.
         *
         * Must be ≥1024 and less than `max_tokens`.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun budgetTokens(budgetTokens: Long) = budgetTokens(JsonField.of(budgetTokens))

        /**
         * Determines how many tokens Claude can use for its internal reasoning process. Larger
         * budgets can enable more thorough analysis for complex problems, improving response
         * quality.
         *
         * Must be ≥1024 and less than `max_tokens`.
         *
         * See
         * [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
         * for details.
         */
        fun budgetTokens(budgetTokens: JsonField<Long>) = apply { this.budgetTokens = budgetTokens }

        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        fun build(): ThinkingConfigEnabled =
            ThinkingConfigEnabled(
                checkRequired("budgetTokens", budgetTokens),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ThinkingConfigEnabled && budgetTokens == other.budgetTokens && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(budgetTokens, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThinkingConfigEnabled{budgetTokens=$budgetTokens, type=$type, additionalProperties=$additionalProperties}"
}
