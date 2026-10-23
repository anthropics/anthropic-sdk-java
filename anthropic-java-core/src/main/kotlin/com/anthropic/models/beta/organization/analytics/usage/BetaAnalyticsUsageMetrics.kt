package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServerToolUse
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The token usage of one row. */
class BetaAnalyticsUsageMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreation: JsonField<BetaCacheCreation>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val outputTokens: JsonField<Long>,
    private val requests: JsonField<Long>,
    private val serverToolUse: JsonField<BetaAnalyticsServerToolUse>,
    private val totalTokens: JsonField<Long>,
    private val uncachedInputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_creation")
        @ExcludeMissing
        cacheCreation: JsonField<BetaCacheCreation> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("requests") @ExcludeMissing requests: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaAnalyticsServerToolUse> = JsonMissing.of(),
        @JsonProperty("total_tokens")
        @ExcludeMissing
        totalTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("uncached_input_tokens")
        @ExcludeMissing
        uncachedInputTokens: JsonField<Long> = JsonMissing.of(),
    ) : this(
        cacheCreation,
        cacheReadInputTokens,
        outputTokens,
        requests,
        serverToolUse,
        totalTokens,
        uncachedInputTokens,
        mutableMapOf(),
    )

    /**
     * The number of input tokens for cache creation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheCreation(): BetaCacheCreation = cacheCreation.getRequired("cache_creation")

    /**
     * The number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Long = cacheReadInputTokens.getRequired("cache_read_input_tokens")

    /**
     * The number of output tokens generated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /**
     * Number of API requests in this row's scope. For sandbox / code-execution events, this counts
     * execution spans rather than HTTP requests (these rows surface with `product: null`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requests(): Optional<Long> = requests.getOptional("requests")

    /**
     * Server-side tool usage metrics.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serverToolUse(): BetaAnalyticsServerToolUse = serverToolUse.getRequired("server_tool_use")

    /**
     * Total token count across all token types. This is the value the default `order_by`
     * (`total_tokens`) sorts on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun totalTokens(): Long = totalTokens.getRequired("total_tokens")

    /**
     * The number of uncached input tokens processed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun uncachedInputTokens(): Long = uncachedInputTokens.getRequired("uncached_input_tokens")

    /**
     * Returns the raw JSON value of [cacheCreation].
     *
     * Unlike [cacheCreation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_creation")
    @ExcludeMissing
    fun _cacheCreation(): JsonField<BetaCacheCreation> = cacheCreation

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
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Long> = outputTokens

    /**
     * Returns the raw JSON value of [requests].
     *
     * Unlike [requests], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("requests") @ExcludeMissing fun _requests(): JsonField<Long> = requests

    /**
     * Returns the raw JSON value of [serverToolUse].
     *
     * Unlike [serverToolUse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_tool_use")
    @ExcludeMissing
    fun _serverToolUse(): JsonField<BetaAnalyticsServerToolUse> = serverToolUse

    /**
     * Returns the raw JSON value of [totalTokens].
     *
     * Unlike [totalTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total_tokens") @ExcludeMissing fun _totalTokens(): JsonField<Long> = totalTokens

    /**
     * Returns the raw JSON value of [uncachedInputTokens].
     *
     * Unlike [uncachedInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("uncached_input_tokens")
    @ExcludeMissing
    fun _uncachedInputTokens(): JsonField<Long> = uncachedInputTokens

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsUsageMetrics].
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheReadInputTokens()
         * .outputTokens()
         * .requests()
         * .serverToolUse()
         * .totalTokens()
         * .uncachedInputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsUsageMetrics]. */
    class Builder internal constructor() {

        private var cacheCreation: JsonField<BetaCacheCreation>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var outputTokens: JsonField<Long>? = null
        private var requests: JsonField<Long>? = null
        private var serverToolUse: JsonField<BetaAnalyticsServerToolUse>? = null
        private var totalTokens: JsonField<Long>? = null
        private var uncachedInputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsUsageMetrics: BetaAnalyticsUsageMetrics) = apply {
            cacheCreation = betaAnalyticsUsageMetrics.cacheCreation
            cacheReadInputTokens = betaAnalyticsUsageMetrics.cacheReadInputTokens
            outputTokens = betaAnalyticsUsageMetrics.outputTokens
            requests = betaAnalyticsUsageMetrics.requests
            serverToolUse = betaAnalyticsUsageMetrics.serverToolUse
            totalTokens = betaAnalyticsUsageMetrics.totalTokens
            uncachedInputTokens = betaAnalyticsUsageMetrics.uncachedInputTokens
            additionalProperties = betaAnalyticsUsageMetrics.additionalProperties.toMutableMap()
        }

        /** The number of input tokens for cache creation. */
        fun cacheCreation(cacheCreation: BetaCacheCreation) =
            cacheCreation(JsonField.of(cacheCreation))

        /**
         * Sets [Builder.cacheCreation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheCreation] with a well-typed [BetaCacheCreation]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheCreation(cacheCreation: JsonField<BetaCacheCreation>) = apply {
            this.cacheCreation = cacheCreation
        }

        /** The number of input tokens read from the cache. */
        fun cacheReadInputTokens(cacheReadInputTokens: Long) =
            cacheReadInputTokens(JsonField.of(cacheReadInputTokens))

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

        /** The number of output tokens generated. */
        fun outputTokens(outputTokens: Long) = outputTokens(JsonField.of(outputTokens))

        /**
         * Sets [Builder.outputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outputTokens(outputTokens: JsonField<Long>) = apply { this.outputTokens = outputTokens }

        /**
         * Number of API requests in this row's scope. For sandbox / code-execution events, this
         * counts execution spans rather than HTTP requests (these rows surface with `product:
         * null`).
         */
        fun requests(requests: Long?) = requests(JsonField.ofNullable(requests))

        /**
         * Alias for [Builder.requests].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun requests(requests: Long) = requests(requests as Long?)

        /** Alias for calling [Builder.requests] with `requests.orElse(null)`. */
        fun requests(requests: Optional<Long>) = requests(requests.getOrNull())

        /**
         * Sets [Builder.requests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requests] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun requests(requests: JsonField<Long>) = apply { this.requests = requests }

        /** Server-side tool usage metrics. */
        fun serverToolUse(serverToolUse: BetaAnalyticsServerToolUse) =
            serverToolUse(JsonField.of(serverToolUse))

        /**
         * Sets [Builder.serverToolUse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolUse] with a well-typed
         * [BetaAnalyticsServerToolUse] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun serverToolUse(serverToolUse: JsonField<BetaAnalyticsServerToolUse>) = apply {
            this.serverToolUse = serverToolUse
        }

        /**
         * Total token count across all token types. This is the value the default `order_by`
         * (`total_tokens`) sorts on.
         */
        fun totalTokens(totalTokens: Long) = totalTokens(JsonField.of(totalTokens))

        /**
         * Sets [Builder.totalTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun totalTokens(totalTokens: JsonField<Long>) = apply { this.totalTokens = totalTokens }

        /** The number of uncached input tokens processed. */
        fun uncachedInputTokens(uncachedInputTokens: Long) =
            uncachedInputTokens(JsonField.of(uncachedInputTokens))

        /**
         * Sets [Builder.uncachedInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.uncachedInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun uncachedInputTokens(uncachedInputTokens: JsonField<Long>) = apply {
            this.uncachedInputTokens = uncachedInputTokens
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
         * Returns an immutable instance of [BetaAnalyticsUsageMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheReadInputTokens()
         * .outputTokens()
         * .requests()
         * .serverToolUse()
         * .totalTokens()
         * .uncachedInputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsUsageMetrics =
            BetaAnalyticsUsageMetrics(
                checkRequired("cacheCreation", cacheCreation),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("outputTokens", outputTokens),
                checkRequired("requests", requests),
                checkRequired("serverToolUse", serverToolUse),
                checkRequired("totalTokens", totalTokens),
                checkRequired("uncachedInputTokens", uncachedInputTokens),
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
    fun validate(): BetaAnalyticsUsageMetrics = apply {
        if (validated) {
            return@apply
        }

        cacheCreation().validate()
        cacheReadInputTokens()
        outputTokens()
        requests()
        serverToolUse().validate()
        totalTokens()
        uncachedInputTokens()
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
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (if (requests.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0) +
            (if (totalTokens.asKnown().isPresent) 1 else 0) +
            (if (uncachedInputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsUsageMetrics &&
            cacheCreation == other.cacheCreation &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            outputTokens == other.outputTokens &&
            requests == other.requests &&
            serverToolUse == other.serverToolUse &&
            totalTokens == other.totalTokens &&
            uncachedInputTokens == other.uncachedInputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreation,
            cacheReadInputTokens,
            outputTokens,
            requests,
            serverToolUse,
            totalTokens,
            uncachedInputTokens,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsUsageMetrics{cacheCreation=$cacheCreation, cacheReadInputTokens=$cacheReadInputTokens, outputTokens=$outputTokens, requests=$requests, serverToolUse=$serverToolUse, totalTokens=$totalTokens, uncachedInputTokens=$uncachedInputTokens, additionalProperties=$additionalProperties}"
}
