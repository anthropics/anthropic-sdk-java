// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Cumulative token usage for a session thread across all turns. */
class BetaManagedAgentsSessionThreadUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage>,
    private val cacheReadInputTokens: JsonField<Int>,
    private val inputTokens: JsonField<Int>,
    private val outputTokens: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_creation")
        @ExcludeMissing
        cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Int> = JsonMissing.of(),
    ) : this(cacheCreation, cacheReadInputTokens, inputTokens, outputTokens, mutableMapOf())

    /**
     * Prompt-cache creation token usage broken down by cache lifetime.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheCreation(): Optional<BetaManagedAgentsCacheCreationUsage> =
        cacheCreation.getOptional("cache_creation")

    /**
     * Total tokens read from prompt cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Optional<Int> =
        cacheReadInputTokens.getOptional("cache_read_input_tokens")

    /**
     * Total input tokens consumed across all turns.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inputTokens(): Optional<Int> = inputTokens.getOptional("input_tokens")

    /**
     * Total output tokens generated across all turns.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputTokens(): Optional<Int> = outputTokens.getOptional("output_tokens")

    /**
     * Returns the raw JSON value of [cacheCreation].
     *
     * Unlike [cacheCreation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_creation")
    @ExcludeMissing
    fun _cacheCreation(): JsonField<BetaManagedAgentsCacheCreationUsage> = cacheCreation

    /**
     * Returns the raw JSON value of [cacheReadInputTokens].
     *
     * Unlike [cacheReadInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cache_read_input_tokens")
    @ExcludeMissing
    fun _cacheReadInputTokens(): JsonField<Int> = cacheReadInputTokens

    /**
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Int> = inputTokens

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Int> = outputTokens

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
         * [BetaManagedAgentsSessionThreadUsage].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionThreadUsage]. */
    class Builder internal constructor() {

        private var cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage> = JsonMissing.of()
        private var cacheReadInputTokens: JsonField<Int> = JsonMissing.of()
        private var inputTokens: JsonField<Int> = JsonMissing.of()
        private var outputTokens: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionThreadUsage: BetaManagedAgentsSessionThreadUsage
        ) = apply {
            cacheCreation = betaManagedAgentsSessionThreadUsage.cacheCreation
            cacheReadInputTokens = betaManagedAgentsSessionThreadUsage.cacheReadInputTokens
            inputTokens = betaManagedAgentsSessionThreadUsage.inputTokens
            outputTokens = betaManagedAgentsSessionThreadUsage.outputTokens
            additionalProperties =
                betaManagedAgentsSessionThreadUsage.additionalProperties.toMutableMap()
        }

        /** Prompt-cache creation token usage broken down by cache lifetime. */
        fun cacheCreation(cacheCreation: BetaManagedAgentsCacheCreationUsage) =
            cacheCreation(JsonField.of(cacheCreation))

        /**
         * Sets [Builder.cacheCreation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheCreation] with a well-typed
         * [BetaManagedAgentsCacheCreationUsage] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun cacheCreation(cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage>) = apply {
            this.cacheCreation = cacheCreation
        }

        /** Total tokens read from prompt cache. */
        fun cacheReadInputTokens(cacheReadInputTokens: Int) =
            cacheReadInputTokens(JsonField.of(cacheReadInputTokens))

        /**
         * Sets [Builder.cacheReadInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheReadInputTokens] with a well-typed [Int] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cacheReadInputTokens(cacheReadInputTokens: JsonField<Int>) = apply {
            this.cacheReadInputTokens = cacheReadInputTokens
        }

        /** Total input tokens consumed across all turns. */
        fun inputTokens(inputTokens: Int) = inputTokens(JsonField.of(inputTokens))

        /**
         * Sets [Builder.inputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTokens] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun inputTokens(inputTokens: JsonField<Int>) = apply { this.inputTokens = inputTokens }

        /** Total output tokens generated across all turns. */
        fun outputTokens(outputTokens: Int) = outputTokens(JsonField.of(outputTokens))

        /**
         * Sets [Builder.outputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokens] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outputTokens(outputTokens: JsonField<Int>) = apply { this.outputTokens = outputTokens }

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
         * Returns an immutable instance of [BetaManagedAgentsSessionThreadUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSessionThreadUsage =
            BetaManagedAgentsSessionThreadUsage(
                cacheCreation,
                cacheReadInputTokens,
                inputTokens,
                outputTokens,
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
    fun validate(): BetaManagedAgentsSessionThreadUsage = apply {
        if (validated) {
            return@apply
        }

        cacheCreation().ifPresent { it.validate() }
        cacheReadInputTokens()
        inputTokens()
        outputTokens()
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
        (cacheCreation.asKnown().getOrNull()?.validity() ?: 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionThreadUsage &&
            cacheCreation == other.cacheCreation &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inputTokens == other.inputTokens &&
            outputTokens == other.outputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreation,
            cacheReadInputTokens,
            inputTokens,
            outputTokens,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionThreadUsage{cacheCreation=$cacheCreation, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, outputTokens=$outputTokens, additionalProperties=$additionalProperties}"
}
