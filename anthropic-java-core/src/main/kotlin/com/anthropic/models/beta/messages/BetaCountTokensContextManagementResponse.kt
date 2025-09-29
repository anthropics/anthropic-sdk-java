// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaCountTokensContextManagementResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val originalInputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("original_input_tokens")
        @ExcludeMissing
        originalInputTokens: JsonField<Long> = JsonMissing.of()
    ) : this(originalInputTokens, mutableMapOf())

    /**
     * The original token count before context management was applied
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun originalInputTokens(): Long = originalInputTokens.getRequired("original_input_tokens")

    /**
     * Returns the raw JSON value of [originalInputTokens].
     *
     * Unlike [originalInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("original_input_tokens")
    @ExcludeMissing
    fun _originalInputTokens(): JsonField<Long> = originalInputTokens

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
         * Returns a mutable builder for constructing an instance of
         * [BetaCountTokensContextManagementResponse].
         *
         * The following fields are required:
         * ```java
         * .originalInputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCountTokensContextManagementResponse]. */
    class Builder internal constructor() {

        private var originalInputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaCountTokensContextManagementResponse: BetaCountTokensContextManagementResponse
        ) = apply {
            originalInputTokens = betaCountTokensContextManagementResponse.originalInputTokens
            additionalProperties =
                betaCountTokensContextManagementResponse.additionalProperties.toMutableMap()
        }

        /** The original token count before context management was applied */
        fun originalInputTokens(originalInputTokens: Long) =
            originalInputTokens(JsonField.of(originalInputTokens))

        /**
         * Sets [Builder.originalInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.originalInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun originalInputTokens(originalInputTokens: JsonField<Long>) = apply {
            this.originalInputTokens = originalInputTokens
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
         * Returns an immutable instance of [BetaCountTokensContextManagementResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .originalInputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCountTokensContextManagementResponse =
            BetaCountTokensContextManagementResponse(
                checkRequired("originalInputTokens", originalInputTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCountTokensContextManagementResponse = apply {
        if (validated) {
            return@apply
        }

        originalInputTokens()
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
    @JvmSynthetic
    internal fun validity(): Int = (if (originalInputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCountTokensContextManagementResponse &&
            originalInputTokens == other.originalInputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(originalInputTokens, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCountTokensContextManagementResponse{originalInputTokens=$originalInputTokens, additionalProperties=$additionalProperties}"
}
