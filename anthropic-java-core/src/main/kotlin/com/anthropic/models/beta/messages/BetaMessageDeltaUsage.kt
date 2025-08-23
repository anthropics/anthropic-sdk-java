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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaMessageDeltaUsage
private constructor(
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val inputTokens: JsonField<Long>,
    private val outputTokens: JsonField<Long>,
    private val serverToolUse: JsonField<BetaServerToolUsage>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_creation_input_tokens")
        @ExcludeMissing
        cacheCreationInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaServerToolUsage> = JsonMissing.of(),
    ) : this(
        cacheCreationInputTokens,
        cacheReadInputTokens,
        inputTokens,
        outputTokens,
        serverToolUse,
        mutableMapOf(),
    )

    /**
     * The cumulative number of input tokens used to create the cache entry.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheCreationInputTokens(): Optional<Long> =
        cacheCreationInputTokens.getOptional("cache_creation_input_tokens")

    /**
     * The cumulative number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Optional<Long> =
        cacheReadInputTokens.getOptional("cache_read_input_tokens")

    /**
     * The cumulative number of input tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inputTokens(): Optional<Long> = inputTokens.getOptional("input_tokens")

    /**
     * The cumulative number of output tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /**
     * The number of server tool requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serverToolUse(): Optional<BetaServerToolUsage> =
        serverToolUse.getOptional("server_tool_use")

    /**
     * Returns the raw JSON value of [cacheCreationInputTokens].
     *
     * Unlike [cacheCreationInputTokens], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cache_creation_input_tokens")
    @ExcludeMissing
    fun _cacheCreationInputTokens(): JsonField<Long> = cacheCreationInputTokens

    /**
     * Returns the raw JSON value of [cacheReadInputTokens].
     *
     * Unlike [cacheReadInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cache_read_input_tokens")
    @ExcludeMissing
    fun _cacheReadInputTokens(): JsonField<Long> = cacheReadInputTokens

    /**
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Long> = inputTokens

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Long> = outputTokens

    /**
     * Returns the raw JSON value of [serverToolUse].
     *
     * Unlike [serverToolUse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_tool_use")
    @ExcludeMissing
    fun _serverToolUse(): JsonField<BetaServerToolUsage> = serverToolUse

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
         * Returns a mutable builder for constructing an instance of [BetaMessageDeltaUsage].
         *
         * The following fields are required:
         * ```java
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .outputTokens()
         * .serverToolUse()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageDeltaUsage]. */
    class Builder internal constructor() {

        private var cacheCreationInputTokens: JsonField<Long>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var inputTokens: JsonField<Long>? = null
        private var outputTokens: JsonField<Long>? = null
        private var serverToolUse: JsonField<BetaServerToolUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageDeltaUsage: BetaMessageDeltaUsage) = apply {
            cacheCreationInputTokens = betaMessageDeltaUsage.cacheCreationInputTokens
            cacheReadInputTokens = betaMessageDeltaUsage.cacheReadInputTokens
            inputTokens = betaMessageDeltaUsage.inputTokens
            outputTokens = betaMessageDeltaUsage.outputTokens
            serverToolUse = betaMessageDeltaUsage.serverToolUse
            additionalProperties = betaMessageDeltaUsage.additionalProperties.toMutableMap()
        }

        /** The cumulative number of input tokens used to create the cache entry. */
        fun cacheCreationInputTokens(cacheCreationInputTokens: Long?) =
            cacheCreationInputTokens(JsonField.ofNullable(cacheCreationInputTokens))

        /**
         * Alias for [Builder.cacheCreationInputTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun cacheCreationInputTokens(cacheCreationInputTokens: Long) =
            cacheCreationInputTokens(cacheCreationInputTokens as Long?)

        /**
         * Alias for calling [Builder.cacheCreationInputTokens] with
         * `cacheCreationInputTokens.orElse(null)`.
         */
        fun cacheCreationInputTokens(cacheCreationInputTokens: Optional<Long>) =
            cacheCreationInputTokens(cacheCreationInputTokens.getOrNull())

        /**
         * Sets [Builder.cacheCreationInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheCreationInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cacheCreationInputTokens(cacheCreationInputTokens: JsonField<Long>) = apply {
            this.cacheCreationInputTokens = cacheCreationInputTokens
        }

        /** The cumulative number of input tokens read from the cache. */
        fun cacheReadInputTokens(cacheReadInputTokens: Long?) =
            cacheReadInputTokens(JsonField.ofNullable(cacheReadInputTokens))

        /**
         * Alias for [Builder.cacheReadInputTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun cacheReadInputTokens(cacheReadInputTokens: Long) =
            cacheReadInputTokens(cacheReadInputTokens as Long?)

        /**
         * Alias for calling [Builder.cacheReadInputTokens] with
         * `cacheReadInputTokens.orElse(null)`.
         */
        fun cacheReadInputTokens(cacheReadInputTokens: Optional<Long>) =
            cacheReadInputTokens(cacheReadInputTokens.getOrNull())

        /**
         * Sets [Builder.cacheReadInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheReadInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cacheReadInputTokens(cacheReadInputTokens: JsonField<Long>) = apply {
            this.cacheReadInputTokens = cacheReadInputTokens
        }

        /** The cumulative number of input tokens which were used. */
        fun inputTokens(inputTokens: Long?) = inputTokens(JsonField.ofNullable(inputTokens))

        /**
         * Alias for [Builder.inputTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun inputTokens(inputTokens: Long) = inputTokens(inputTokens as Long?)

        /** Alias for calling [Builder.inputTokens] with `inputTokens.orElse(null)`. */
        fun inputTokens(inputTokens: Optional<Long>) = inputTokens(inputTokens.getOrNull())

        /**
         * Sets [Builder.inputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun inputTokens(inputTokens: JsonField<Long>) = apply { this.inputTokens = inputTokens }

        /** The cumulative number of output tokens which were used. */
        fun outputTokens(outputTokens: Long) = outputTokens(JsonField.of(outputTokens))

        /**
         * Sets [Builder.outputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outputTokens(outputTokens: JsonField<Long>) = apply { this.outputTokens = outputTokens }

        /** The number of server tool requests. */
        fun serverToolUse(serverToolUse: BetaServerToolUsage?) =
            serverToolUse(JsonField.ofNullable(serverToolUse))

        /** Alias for calling [Builder.serverToolUse] with `serverToolUse.orElse(null)`. */
        fun serverToolUse(serverToolUse: Optional<BetaServerToolUsage>) =
            serverToolUse(serverToolUse.getOrNull())

        /**
         * Sets [Builder.serverToolUse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolUse] with a well-typed [BetaServerToolUsage]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun serverToolUse(serverToolUse: JsonField<BetaServerToolUsage>) = apply {
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
         * Returns an immutable instance of [BetaMessageDeltaUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .outputTokens()
         * .serverToolUse()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMessageDeltaUsage =
            BetaMessageDeltaUsage(
                checkRequired("cacheCreationInputTokens", cacheCreationInputTokens),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("inputTokens", inputTokens),
                checkRequired("outputTokens", outputTokens),
                checkRequired("serverToolUse", serverToolUse),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMessageDeltaUsage = apply {
        if (validated) {
            return@apply
        }

        cacheCreationInputTokens()
        cacheReadInputTokens()
        inputTokens()
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
        (if (cacheCreationInputTokens.asKnown().isPresent) 1 else 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageDeltaUsage &&
            cacheCreationInputTokens == other.cacheCreationInputTokens &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inputTokens == other.inputTokens &&
            outputTokens == other.outputTokens &&
            serverToolUse == other.serverToolUse &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreationInputTokens,
            cacheReadInputTokens,
            inputTokens,
            outputTokens,
            serverToolUse,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageDeltaUsage{cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, outputTokens=$outputTokens, serverToolUse=$serverToolUse, additionalProperties=$additionalProperties}"
}
