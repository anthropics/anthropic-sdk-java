// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.BetaMonetaryAmount
import com.anthropic.models.beta.sessions.BetaManagedAgentsCacheCreationUsage
import com.anthropic.models.beta.sessions.BetaManagedAgentsServerToolUsage
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Point-in-time snapshot of a session's cumulative usage. */
class BetaManagedAgentsSessionUsageSnapshot
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val activeSeconds: JsonField<Double>,
    private val cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage>,
    private val cacheReadInputTokens: JsonField<Int>,
    private val inputTokens: JsonField<Int>,
    private val listCost: JsonField<BetaMonetaryAmount>,
    private val outputTokens: JsonField<Int>,
    private val serverToolUse: JsonField<BetaManagedAgentsServerToolUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("active_seconds")
        @ExcludeMissing
        activeSeconds: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("cache_creation")
        @ExcludeMissing
        cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("list_cost")
        @ExcludeMissing
        listCost: JsonField<BetaMonetaryAmount> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaManagedAgentsServerToolUsage> = JsonMissing.of(),
    ) : this(
        activeSeconds,
        cacheCreation,
        cacheReadInputTokens,
        inputTokens,
        listCost,
        outputTokens,
        serverToolUse,
        mutableMapOf(),
    )

    /**
     * Cumulative time in seconds during which the session had at least one thread in running
     * status. Overlapping activity from concurrent threads is counted once. This is the duration
     * the session's runtime cost is priced on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun activeSeconds(): Optional<Double> = activeSeconds.getOptional("active_seconds")

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
     * A monetary amount in a specific currency.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun listCost(): Optional<BetaMonetaryAmount> = listCost.getOptional("list_cost")

    /**
     * Total output tokens generated across all turns.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputTokens(): Optional<Int> = outputTokens.getOptional("output_tokens")

    /**
     * Cumulative count of server-executed tool invocations, broken down by tool.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serverToolUse(): Optional<BetaManagedAgentsServerToolUsage> =
        serverToolUse.getOptional("server_tool_use")

    /**
     * Returns the raw JSON value of [activeSeconds].
     *
     * Unlike [activeSeconds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("active_seconds")
    @ExcludeMissing
    fun _activeSeconds(): JsonField<Double> = activeSeconds

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
     * Returns the raw JSON value of [listCost].
     *
     * Unlike [listCost], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("list_cost")
    @ExcludeMissing
    fun _listCost(): JsonField<BetaMonetaryAmount> = listCost

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Int> = outputTokens

    /**
     * Returns the raw JSON value of [serverToolUse].
     *
     * Unlike [serverToolUse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_tool_use")
    @ExcludeMissing
    fun _serverToolUse(): JsonField<BetaManagedAgentsServerToolUsage> = serverToolUse

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
         * [BetaManagedAgentsSessionUsageSnapshot].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionUsageSnapshot]. */
    class Builder internal constructor() {

        private var activeSeconds: JsonField<Double> = JsonMissing.of()
        private var cacheCreation: JsonField<BetaManagedAgentsCacheCreationUsage> = JsonMissing.of()
        private var cacheReadInputTokens: JsonField<Int> = JsonMissing.of()
        private var inputTokens: JsonField<Int> = JsonMissing.of()
        private var listCost: JsonField<BetaMonetaryAmount> = JsonMissing.of()
        private var outputTokens: JsonField<Int> = JsonMissing.of()
        private var serverToolUse: JsonField<BetaManagedAgentsServerToolUsage> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionUsageSnapshot: BetaManagedAgentsSessionUsageSnapshot
        ) = apply {
            activeSeconds = betaManagedAgentsSessionUsageSnapshot.activeSeconds
            cacheCreation = betaManagedAgentsSessionUsageSnapshot.cacheCreation
            cacheReadInputTokens = betaManagedAgentsSessionUsageSnapshot.cacheReadInputTokens
            inputTokens = betaManagedAgentsSessionUsageSnapshot.inputTokens
            listCost = betaManagedAgentsSessionUsageSnapshot.listCost
            outputTokens = betaManagedAgentsSessionUsageSnapshot.outputTokens
            serverToolUse = betaManagedAgentsSessionUsageSnapshot.serverToolUse
            additionalProperties =
                betaManagedAgentsSessionUsageSnapshot.additionalProperties.toMutableMap()
        }

        /**
         * Cumulative time in seconds during which the session had at least one thread in running
         * status. Overlapping activity from concurrent threads is counted once. This is the
         * duration the session's runtime cost is priced on.
         */
        fun activeSeconds(activeSeconds: Double) = activeSeconds(JsonField.of(activeSeconds))

        /**
         * Sets [Builder.activeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.activeSeconds] with a well-typed [Double] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun activeSeconds(activeSeconds: JsonField<Double>) = apply {
            this.activeSeconds = activeSeconds
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

        /** A monetary amount in a specific currency. */
        fun listCost(listCost: BetaMonetaryAmount) = listCost(JsonField.of(listCost))

        /**
         * Sets [Builder.listCost] to an arbitrary JSON value.
         *
         * You should usually call [Builder.listCost] with a well-typed [BetaMonetaryAmount] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun listCost(listCost: JsonField<BetaMonetaryAmount>) = apply { this.listCost = listCost }

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

        /** Cumulative count of server-executed tool invocations, broken down by tool. */
        fun serverToolUse(serverToolUse: BetaManagedAgentsServerToolUsage) =
            serverToolUse(JsonField.of(serverToolUse))

        /**
         * Sets [Builder.serverToolUse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolUse] with a well-typed
         * [BetaManagedAgentsServerToolUsage] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun serverToolUse(serverToolUse: JsonField<BetaManagedAgentsServerToolUsage>) = apply {
            this.serverToolUse = serverToolUse
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
         * Returns an immutable instance of [BetaManagedAgentsSessionUsageSnapshot].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSessionUsageSnapshot =
            BetaManagedAgentsSessionUsageSnapshot(
                activeSeconds,
                cacheCreation,
                cacheReadInputTokens,
                inputTokens,
                listCost,
                outputTokens,
                serverToolUse,
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
    fun validate(): BetaManagedAgentsSessionUsageSnapshot = apply {
        if (validated) {
            return@apply
        }

        activeSeconds()
        cacheCreation().ifPresent { it.validate() }
        cacheReadInputTokens()
        inputTokens()
        listCost().ifPresent { it.validate() }
        outputTokens()
        serverToolUse().ifPresent { it.validate() }
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
        (if (activeSeconds.asKnown().isPresent) 1 else 0) +
            (cacheCreation.asKnown().getOrNull()?.validity() ?: 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (listCost.asKnown().getOrNull()?.validity() ?: 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionUsageSnapshot &&
            activeSeconds == other.activeSeconds &&
            cacheCreation == other.cacheCreation &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inputTokens == other.inputTokens &&
            listCost == other.listCost &&
            outputTokens == other.outputTokens &&
            serverToolUse == other.serverToolUse &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            activeSeconds,
            cacheCreation,
            cacheReadInputTokens,
            inputTokens,
            listCost,
            outputTokens,
            serverToolUse,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionUsageSnapshot{activeSeconds=$activeSeconds, cacheCreation=$cacheCreation, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, listCost=$listCost, outputTokens=$outputTokens, serverToolUse=$serverToolUse, additionalProperties=$additionalProperties}"
}
