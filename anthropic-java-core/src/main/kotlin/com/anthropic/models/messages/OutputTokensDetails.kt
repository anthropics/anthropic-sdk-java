// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects

class OutputTokensDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val thinkingTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("thinking_tokens")
        @ExcludeMissing
        thinkingTokens: JsonField<Long> = JsonMissing.of()
    ) : this(thinkingTokens, mutableMapOf())

    /**
     * Number of output tokens the model generated as internal reasoning, including the
     * thinking-block delimiter tokens.
     *
     * Reflects the raw reasoning the model produced, not the (possibly shorter) summarized thinking
     * text returned in the response body. Computed by re-tokenizing the raw reasoning text, so it
     * may differ from the model's exact generation count by a small number of tokens. Always ≤
     * `output_tokens`; `output_tokens - thinking_tokens` approximates the non-reasoning output.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinkingTokens(): Long = thinkingTokens.getRequired("thinking_tokens")

    /**
     * Returns the raw JSON value of [thinkingTokens].
     *
     * Unlike [thinkingTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("thinking_tokens")
    @ExcludeMissing
    fun _thinkingTokens(): JsonField<Long> = thinkingTokens

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [OutputTokensDetails].
         *
         * The following fields are required:
         * ```java
         * .thinkingTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OutputTokensDetails]. */
    class Builder internal constructor() {

        private var thinkingTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(outputTokensDetails: OutputTokensDetails) = apply {
            thinkingTokens = outputTokensDetails.thinkingTokens
            additionalProperties = outputTokensDetails.additionalProperties.toMutableMap()
        }

        /**
         * Number of output tokens the model generated as internal reasoning, including the
         * thinking-block delimiter tokens.
         *
         * Reflects the raw reasoning the model produced, not the (possibly shorter) summarized
         * thinking text returned in the response body. Computed by re-tokenizing the raw reasoning
         * text, so it may differ from the model's exact generation count by a small number of
         * tokens. Always ≤ `output_tokens`; `output_tokens - thinking_tokens` approximates the
         * non-reasoning output.
         */
        fun thinkingTokens(thinkingTokens: Long) = thinkingTokens(JsonField.of(thinkingTokens))

        /**
         * Sets [Builder.thinkingTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinkingTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun thinkingTokens(thinkingTokens: JsonField<Long>) = apply {
            this.thinkingTokens = thinkingTokens
        }

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

        /**
         * Returns an immutable instance of [OutputTokensDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .thinkingTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): OutputTokensDetails =
            OutputTokensDetails(
                checkRequired("thinkingTokens", thinkingTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): OutputTokensDetails = apply {
        if (validated) {
            return@apply
        }

        thinkingTokens()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = (if (thinkingTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OutputTokensDetails &&
            thinkingTokens == other.thinkingTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(thinkingTokens, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "OutputTokensDetails{thinkingTokens=$thinkingTokens, additionalProperties=$additionalProperties}"
}
