// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional

/** Prompt-cache creation token usage broken down by cache lifetime. */
class BetaManagedAgentsCacheCreationUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val ephemeral1hInputTokens: JsonField<Int>,
    private val ephemeral5mInputTokens: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("ephemeral_1h_input_tokens")
        @ExcludeMissing
        ephemeral1hInputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("ephemeral_5m_input_tokens")
        @ExcludeMissing
        ephemeral5mInputTokens: JsonField<Int> = JsonMissing.of(),
    ) : this(ephemeral1hInputTokens, ephemeral5mInputTokens, mutableMapOf())

    /**
     * Tokens used to create 1-hour ephemeral cache entries.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun ephemeral1hInputTokens(): Optional<Int> =
        ephemeral1hInputTokens.getOptional("ephemeral_1h_input_tokens")

    /**
     * Tokens used to create 5-minute ephemeral cache entries.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun ephemeral5mInputTokens(): Optional<Int> =
        ephemeral5mInputTokens.getOptional("ephemeral_5m_input_tokens")

    /**
     * Returns the raw JSON value of [ephemeral1hInputTokens].
     *
     * Unlike [ephemeral1hInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("ephemeral_1h_input_tokens")
    @ExcludeMissing
    fun _ephemeral1hInputTokens(): JsonField<Int> = ephemeral1hInputTokens

    /**
     * Returns the raw JSON value of [ephemeral5mInputTokens].
     *
     * Unlike [ephemeral5mInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("ephemeral_5m_input_tokens")
    @ExcludeMissing
    fun _ephemeral5mInputTokens(): JsonField<Int> = ephemeral5mInputTokens

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
         * [BetaManagedAgentsCacheCreationUsage].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsCacheCreationUsage]. */
    class Builder internal constructor() {

        private var ephemeral1hInputTokens: JsonField<Int> = JsonMissing.of()
        private var ephemeral5mInputTokens: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsCacheCreationUsage: BetaManagedAgentsCacheCreationUsage
        ) = apply {
            ephemeral1hInputTokens = betaManagedAgentsCacheCreationUsage.ephemeral1hInputTokens
            ephemeral5mInputTokens = betaManagedAgentsCacheCreationUsage.ephemeral5mInputTokens
            additionalProperties =
                betaManagedAgentsCacheCreationUsage.additionalProperties.toMutableMap()
        }

        /** Tokens used to create 1-hour ephemeral cache entries. */
        fun ephemeral1hInputTokens(ephemeral1hInputTokens: Int) =
            ephemeral1hInputTokens(JsonField.of(ephemeral1hInputTokens))

        /**
         * Sets [Builder.ephemeral1hInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ephemeral1hInputTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun ephemeral1hInputTokens(ephemeral1hInputTokens: JsonField<Int>) = apply {
            this.ephemeral1hInputTokens = ephemeral1hInputTokens
        }

        /** Tokens used to create 5-minute ephemeral cache entries. */
        fun ephemeral5mInputTokens(ephemeral5mInputTokens: Int) =
            ephemeral5mInputTokens(JsonField.of(ephemeral5mInputTokens))

        /**
         * Sets [Builder.ephemeral5mInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ephemeral5mInputTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun ephemeral5mInputTokens(ephemeral5mInputTokens: JsonField<Int>) = apply {
            this.ephemeral5mInputTokens = ephemeral5mInputTokens
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
         * Returns an immutable instance of [BetaManagedAgentsCacheCreationUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsCacheCreationUsage =
            BetaManagedAgentsCacheCreationUsage(
                ephemeral1hInputTokens,
                ephemeral5mInputTokens,
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
    fun validate(): BetaManagedAgentsCacheCreationUsage = apply {
        if (validated) {
            return@apply
        }

        ephemeral1hInputTokens()
        ephemeral5mInputTokens()
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
    internal fun validity(): Int =
        (if (ephemeral1hInputTokens.asKnown().isPresent) 1 else 0) +
            (if (ephemeral5mInputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsCacheCreationUsage &&
            ephemeral1hInputTokens == other.ephemeral1hInputTokens &&
            ephemeral5mInputTokens == other.ephemeral5mInputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(ephemeral1hInputTokens, ephemeral5mInputTokens, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsCacheCreationUsage{ephemeral1hInputTokens=$ephemeral1hInputTokens, ephemeral5mInputTokens=$ephemeral5mInputTokens, additionalProperties=$additionalProperties}"
}
