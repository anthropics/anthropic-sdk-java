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

class BetaCacheCreation
private constructor(
    private val ephemeral1hInputTokens: JsonField<Long>,
    private val ephemeral5mInputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("ephemeral_1h_input_tokens")
        @ExcludeMissing
        ephemeral1hInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("ephemeral_5m_input_tokens")
        @ExcludeMissing
        ephemeral5mInputTokens: JsonField<Long> = JsonMissing.of(),
    ) : this(ephemeral1hInputTokens, ephemeral5mInputTokens, mutableMapOf())

    /**
     * The number of input tokens used to create the 1 hour cache entry.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun ephemeral1hInputTokens(): Long =
        ephemeral1hInputTokens.getRequired("ephemeral_1h_input_tokens")

    /**
     * The number of input tokens used to create the 5 minute cache entry.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun ephemeral5mInputTokens(): Long =
        ephemeral5mInputTokens.getRequired("ephemeral_5m_input_tokens")

    /**
     * Returns the raw JSON value of [ephemeral1hInputTokens].
     *
     * Unlike [ephemeral1hInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("ephemeral_1h_input_tokens")
    @ExcludeMissing
    fun _ephemeral1hInputTokens(): JsonField<Long> = ephemeral1hInputTokens

    /**
     * Returns the raw JSON value of [ephemeral5mInputTokens].
     *
     * Unlike [ephemeral5mInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("ephemeral_5m_input_tokens")
    @ExcludeMissing
    fun _ephemeral5mInputTokens(): JsonField<Long> = ephemeral5mInputTokens

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
         * Returns a mutable builder for constructing an instance of [BetaCacheCreation].
         *
         * The following fields are required:
         * ```java
         * .ephemeral1hInputTokens()
         * .ephemeral5mInputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCacheCreation]. */
    class Builder internal constructor() {

        private var ephemeral1hInputTokens: JsonField<Long>? = null
        private var ephemeral5mInputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCacheCreation: BetaCacheCreation) = apply {
            ephemeral1hInputTokens = betaCacheCreation.ephemeral1hInputTokens
            ephemeral5mInputTokens = betaCacheCreation.ephemeral5mInputTokens
            additionalProperties = betaCacheCreation.additionalProperties.toMutableMap()
        }

        /** The number of input tokens used to create the 1 hour cache entry. */
        fun ephemeral1hInputTokens(ephemeral1hInputTokens: Long) =
            ephemeral1hInputTokens(JsonField.of(ephemeral1hInputTokens))

        /**
         * Sets [Builder.ephemeral1hInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ephemeral1hInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun ephemeral1hInputTokens(ephemeral1hInputTokens: JsonField<Long>) = apply {
            this.ephemeral1hInputTokens = ephemeral1hInputTokens
        }

        /** The number of input tokens used to create the 5 minute cache entry. */
        fun ephemeral5mInputTokens(ephemeral5mInputTokens: Long) =
            ephemeral5mInputTokens(JsonField.of(ephemeral5mInputTokens))

        /**
         * Sets [Builder.ephemeral5mInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ephemeral5mInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun ephemeral5mInputTokens(ephemeral5mInputTokens: JsonField<Long>) = apply {
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
         * Returns an immutable instance of [BetaCacheCreation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .ephemeral1hInputTokens()
         * .ephemeral5mInputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCacheCreation =
            BetaCacheCreation(
                checkRequired("ephemeral1hInputTokens", ephemeral1hInputTokens),
                checkRequired("ephemeral5mInputTokens", ephemeral5mInputTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCacheCreation = apply {
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

        return other is BetaCacheCreation &&
            ephemeral1hInputTokens == other.ephemeral1hInputTokens &&
            ephemeral5mInputTokens == other.ephemeral5mInputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(ephemeral1hInputTokens, ephemeral5mInputTokens, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCacheCreation{ephemeral1hInputTokens=$ephemeral1hInputTokens, ephemeral5mInputTokens=$ephemeral5mInputTokens, additionalProperties=$additionalProperties}"
}
