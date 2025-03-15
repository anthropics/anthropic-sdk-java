// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
class BetaMessageTokensCount
@JsonCreator
private constructor(
    @JsonProperty("input_tokens")
    @ExcludeMissing
    private val inputTokens: JsonField<Long> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /**
     * The total number of tokens across the provided list of messages, system prompt, and tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /**
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Long> = inputTokens

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaMessageTokensCount = apply {
        if (validated) {
            return@apply
        }

        inputTokens()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaMessageTokensCount].
         *
         * The following fields are required:
         * ```java
         * .inputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageTokensCount]. */
    class Builder internal constructor() {

        private var inputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageTokensCount: BetaMessageTokensCount) = apply {
            inputTokens = betaMessageTokensCount.inputTokens
            additionalProperties = betaMessageTokensCount.additionalProperties.toMutableMap()
        }

        /**
         * The total number of tokens across the provided list of messages, system prompt, and
         * tools.
         */
        fun inputTokens(inputTokens: Long) = inputTokens(JsonField.of(inputTokens))

        /**
         * Sets [Builder.inputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun inputTokens(inputTokens: JsonField<Long>) = apply { this.inputTokens = inputTokens }

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

        fun build(): BetaMessageTokensCount =
            BetaMessageTokensCount(
                checkRequired("inputTokens", inputTokens),
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaMessageTokensCount && inputTokens == other.inputTokens && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(inputTokens, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageTokensCount{inputTokens=$inputTokens, additionalProperties=$additionalProperties}"
}
