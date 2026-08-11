// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaMessageDeltaUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val fallbackCredit: JsonField<BetaFallbackCreditUsage>,
    private val inputTokens: JsonField<Long>,
    private val iterations: JsonField<List<Iteration>>,
    private val outputTokens: JsonField<Long>,
    private val outputTokensDetails: JsonField<BetaOutputTokensDetails>,
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
        @JsonProperty("fallback_credit")
        @ExcludeMissing
        fallbackCredit: JsonField<BetaFallbackCreditUsage> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("iterations")
        @ExcludeMissing
        iterations: JsonField<List<Iteration>> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("output_tokens_details")
        @ExcludeMissing
        outputTokensDetails: JsonField<BetaOutputTokensDetails> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaServerToolUsage> = JsonMissing.of(),
    ) : this(
        cacheCreationInputTokens,
        cacheReadInputTokens,
        fallbackCredit,
        inputTokens,
        iterations,
        outputTokens,
        outputTokensDetails,
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
     * Outcome of the ``fallback_credit_token`` presented on this request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fallbackCredit(): Optional<BetaFallbackCreditUsage> =
        fallbackCredit.getOptional("fallback_credit")

    /**
     * The cumulative number of input tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inputTokens(): Optional<Long> = inputTokens.getOptional("input_tokens")

    /**
     * Per-iteration token usage breakdown.
     *
     * Each entry represents one sampling iteration, with its own input/output token counts and
     * cache statistics. This allows you to:
     * - Determine which iterations exceeded long context thresholds (>=200k tokens)
     * - Calculate the true context window size from the last iteration
     * - Understand token accumulation across server-side tool use loops
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun iterations(): Optional<List<Iteration>> = iterations.getOptional("iterations")

    /**
     * The cumulative number of output tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /**
     * Breakdown of output tokens by category.
     *
     * `output_tokens` remains the inclusive, authoritative total used for billing. This object
     * provides a read-only decomposition for observability — for example, how many of the billed
     * output tokens were spent on internal reasoning that may have been summarized before being
     * returned to you.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputTokensDetails(): Optional<BetaOutputTokensDetails> =
        outputTokensDetails.getOptional("output_tokens_details")

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
     * Returns the raw JSON value of [fallbackCredit].
     *
     * Unlike [fallbackCredit], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("fallback_credit")
    @ExcludeMissing
    fun _fallbackCredit(): JsonField<BetaFallbackCreditUsage> = fallbackCredit

    /**
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Long> = inputTokens

    /**
     * Returns the raw JSON value of [iterations].
     *
     * Unlike [iterations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("iterations")
    @ExcludeMissing
    fun _iterations(): JsonField<List<Iteration>> = iterations

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Long> = outputTokens

    /**
     * Returns the raw JSON value of [outputTokensDetails].
     *
     * Unlike [outputTokensDetails], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("output_tokens_details")
    @ExcludeMissing
    fun _outputTokensDetails(): JsonField<BetaOutputTokensDetails> = outputTokensDetails

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
         * .fallbackCredit()
         * .inputTokens()
         * .iterations()
         * .outputTokens()
         * .outputTokensDetails()
         * .serverToolUse()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageDeltaUsage]. */
    class Builder internal constructor() {

        private var cacheCreationInputTokens: JsonField<Long>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var fallbackCredit: JsonField<BetaFallbackCreditUsage>? = null
        private var inputTokens: JsonField<Long>? = null
        private var iterations: JsonField<MutableList<Iteration>>? = null
        private var outputTokens: JsonField<Long>? = null
        private var outputTokensDetails: JsonField<BetaOutputTokensDetails>? = null
        private var serverToolUse: JsonField<BetaServerToolUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageDeltaUsage: BetaMessageDeltaUsage) = apply {
            cacheCreationInputTokens = betaMessageDeltaUsage.cacheCreationInputTokens
            cacheReadInputTokens = betaMessageDeltaUsage.cacheReadInputTokens
            fallbackCredit = betaMessageDeltaUsage.fallbackCredit
            inputTokens = betaMessageDeltaUsage.inputTokens
            iterations =
                betaMessageDeltaUsage.iterations
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            outputTokens = betaMessageDeltaUsage.outputTokens
            outputTokensDetails = betaMessageDeltaUsage.outputTokensDetails
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

        /** Outcome of the ``fallback_credit_token`` presented on this request. */
        fun fallbackCredit(fallbackCredit: BetaFallbackCreditUsage?) =
            fallbackCredit(JsonField.ofNullable(fallbackCredit))

        /** Alias for calling [Builder.fallbackCredit] with `fallbackCredit.orElse(null)`. */
        fun fallbackCredit(fallbackCredit: Optional<BetaFallbackCreditUsage>) =
            fallbackCredit(fallbackCredit.getOrNull())

        /**
         * Sets [Builder.fallbackCredit] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fallbackCredit] with a well-typed
         * [BetaFallbackCreditUsage] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun fallbackCredit(fallbackCredit: JsonField<BetaFallbackCreditUsage>) = apply {
            this.fallbackCredit = fallbackCredit
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

        /**
         * Per-iteration token usage breakdown.
         *
         * Each entry represents one sampling iteration, with its own input/output token counts and
         * cache statistics. This allows you to:
         * - Determine which iterations exceeded long context thresholds (>=200k tokens)
         * - Calculate the true context window size from the last iteration
         * - Understand token accumulation across server-side tool use loops
         */
        fun iterations(iterations: List<Iteration>?) = iterations(JsonField.ofNullable(iterations))

        /** Alias for calling [Builder.iterations] with `iterations.orElse(null)`. */
        fun iterations(iterations: Optional<List<Iteration>>) = iterations(iterations.getOrNull())

        /**
         * Sets [Builder.iterations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.iterations] with a well-typed `List<Iteration>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun iterations(iterations: JsonField<List<Iteration>>) = apply {
            this.iterations = iterations.map { it.toMutableList() }
        }

        /**
         * Adds a single [Iteration] to [iterations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addIteration(iteration: Iteration) = apply {
            iterations =
                (iterations ?: JsonField.of(mutableListOf())).also {
                    checkKnown("iterations", it).add(iteration)
                }
        }

        /** Alias for calling [addIteration] with `Iteration.ofMessage(message)`. */
        fun addIteration(message: BetaMessageIterationUsage) =
            addIteration(Iteration.ofMessage(message))

        /** Alias for calling [addIteration] with `Iteration.ofCompaction(compaction)`. */
        fun addIteration(compaction: BetaCompactionIterationUsage) =
            addIteration(Iteration.ofCompaction(compaction))

        /** Alias for calling [addIteration] with `Iteration.ofAdvisorMessage(advisorMessage)`. */
        fun addIteration(advisorMessage: BetaAdvisorMessageIterationUsage) =
            addIteration(Iteration.ofAdvisorMessage(advisorMessage))

        /** Alias for calling [addIteration] with `Iteration.ofFallbackMessage(fallbackMessage)`. */
        fun addIteration(fallbackMessage: BetaFallbackMessageIterationUsage) =
            addIteration(Iteration.ofFallbackMessage(fallbackMessage))

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

        /**
         * Breakdown of output tokens by category.
         *
         * `output_tokens` remains the inclusive, authoritative total used for billing. This object
         * provides a read-only decomposition for observability — for example, how many of the
         * billed output tokens were spent on internal reasoning that may have been summarized
         * before being returned to you.
         */
        fun outputTokensDetails(outputTokensDetails: BetaOutputTokensDetails?) =
            outputTokensDetails(JsonField.ofNullable(outputTokensDetails))

        /**
         * Alias for calling [Builder.outputTokensDetails] with `outputTokensDetails.orElse(null)`.
         */
        fun outputTokensDetails(outputTokensDetails: Optional<BetaOutputTokensDetails>) =
            outputTokensDetails(outputTokensDetails.getOrNull())

        /**
         * Sets [Builder.outputTokensDetails] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokensDetails] with a well-typed
         * [BetaOutputTokensDetails] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun outputTokensDetails(outputTokensDetails: JsonField<BetaOutputTokensDetails>) = apply {
            this.outputTokensDetails = outputTokensDetails
        }

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
         * .fallbackCredit()
         * .inputTokens()
         * .iterations()
         * .outputTokens()
         * .outputTokensDetails()
         * .serverToolUse()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMessageDeltaUsage =
            BetaMessageDeltaUsage(
                checkRequired("cacheCreationInputTokens", cacheCreationInputTokens),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("fallbackCredit", fallbackCredit),
                checkRequired("inputTokens", inputTokens),
                checkRequired("iterations", iterations).map { it.toImmutable() },
                checkRequired("outputTokens", outputTokens),
                checkRequired("outputTokensDetails", outputTokensDetails),
                checkRequired("serverToolUse", serverToolUse),
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
    fun validate(): BetaMessageDeltaUsage = apply {
        if (validated) {
            return@apply
        }

        cacheCreationInputTokens()
        cacheReadInputTokens()
        fallbackCredit().ifPresent { it.validate() }
        inputTokens()
        iterations().ifPresent { it.forEach { it.validate() } }
        outputTokens()
        outputTokensDetails().ifPresent { it.validate() }
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
            (fallbackCredit.asKnown().getOrNull()?.validity() ?: 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (iterations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (outputTokensDetails.asKnown().getOrNull()?.validity() ?: 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0)

    /** Token usage for a sampling iteration. */
    @JsonDeserialize(using = Iteration.Deserializer::class)
    @JsonSerialize(using = Iteration.Serializer::class)
    class Iteration
    private constructor(
        private val message: BetaMessageIterationUsage? = null,
        private val compaction: BetaCompactionIterationUsage? = null,
        private val advisorMessage: BetaAdvisorMessageIterationUsage? = null,
        private val fallbackMessage: BetaFallbackMessageIterationUsage? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Type =
                        Type.MESSAGE

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage): Type =
                        Type.COMPACTION

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Type = Type.ADVISOR_MESSAGE

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Type = Type.FALLBACK_MESSAGE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun cacheCreation(): Optional<BetaCacheCreation> =
            accept(
                object : Visitor<Optional<BetaCacheCreation>> {
                    override fun visitMessage(
                        message: BetaMessageIterationUsage
                    ): Optional<BetaCacheCreation> = message.cacheCreation()

                    override fun visitCompaction(
                        compaction: BetaCompactionIterationUsage
                    ): Optional<BetaCacheCreation> = compaction.cacheCreation()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Optional<BetaCacheCreation> = advisorMessage.cacheCreation()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Optional<BetaCacheCreation> = fallbackMessage.cacheCreation()
                }
            )

        fun cacheCreationInputTokens(): Long =
            accept(
                object : Visitor<Long> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Long =
                        message.cacheCreationInputTokens()

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage): Long =
                        compaction.cacheCreationInputTokens()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Long = advisorMessage.cacheCreationInputTokens()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Long = fallbackMessage.cacheCreationInputTokens()
                }
            )

        fun cacheReadInputTokens(): Long =
            accept(
                object : Visitor<Long> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Long =
                        message.cacheReadInputTokens()

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage): Long =
                        compaction.cacheReadInputTokens()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Long = advisorMessage.cacheReadInputTokens()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Long = fallbackMessage.cacheReadInputTokens()
                }
            )

        fun inputTokens(): Long =
            accept(
                object : Visitor<Long> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Long =
                        message.inputTokens()

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage): Long =
                        compaction.inputTokens()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Long = advisorMessage.inputTokens()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Long = fallbackMessage.inputTokens()
                }
            )

        fun model(): Optional<Model> =
            accept(
                object : Visitor<Optional<Model>> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Optional<Model> =
                        Optional.of(message.model())

                    override fun visitCompaction(
                        compaction: BetaCompactionIterationUsage
                    ): Optional<Model> = Optional.empty()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Optional<Model> = Optional.of(advisorMessage.model())

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Optional<Model> = Optional.of(fallbackMessage.model())
                }
            )

        fun outputTokens(): Long =
            accept(
                object : Visitor<Long> {
                    override fun visitMessage(message: BetaMessageIterationUsage): Long =
                        message.outputTokens()

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage): Long =
                        compaction.outputTokens()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ): Long = advisorMessage.outputTokens()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ): Long = fallbackMessage.outputTokens()
                }
            )

        /** Token usage for a sampling iteration. */
        fun message(): Optional<BetaMessageIterationUsage> = Optional.ofNullable(message)

        /** Token usage for a compaction iteration. */
        fun compaction(): Optional<BetaCompactionIterationUsage> = Optional.ofNullable(compaction)

        /** Token usage for an advisor sub-inference iteration. */
        fun advisorMessage(): Optional<BetaAdvisorMessageIterationUsage> =
            Optional.ofNullable(advisorMessage)

        /**
         * Token usage for the fallback-model attempt of a server-side fallback request.
         *
         * Produced in place of a `message` entry for whichever hop served the response. A declined
         * hop produces the existing `message` entry. Whether a fallback model served the response
         * is signalled by the presence of this entry in `usage.iterations`.
         */
        fun fallbackMessage(): Optional<BetaFallbackMessageIterationUsage> =
            Optional.ofNullable(fallbackMessage)

        fun isMessage(): Boolean = message != null

        fun isCompaction(): Boolean = compaction != null

        fun isAdvisorMessage(): Boolean = advisorMessage != null

        fun isFallbackMessage(): Boolean = fallbackMessage != null

        /** Token usage for a sampling iteration. */
        fun asMessage(): BetaMessageIterationUsage = message.getOrThrow("message")

        /** Token usage for a compaction iteration. */
        fun asCompaction(): BetaCompactionIterationUsage = compaction.getOrThrow("compaction")

        /** Token usage for an advisor sub-inference iteration. */
        fun asAdvisorMessage(): BetaAdvisorMessageIterationUsage =
            advisorMessage.getOrThrow("advisorMessage")

        /**
         * Token usage for the fallback-model attempt of a server-side fallback request.
         *
         * Produced in place of a `message` entry for whichever hop served the response. A declined
         * hop produces the existing `message` entry. Whether a fallback model served the response
         * is signalled by the presence of this entry in `usage.iterations`.
         */
        fun asFallbackMessage(): BetaFallbackMessageIterationUsage =
            fallbackMessage.getOrThrow("fallbackMessage")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = iteration.accept(new Iteration.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitMessage(BetaMessageIterationUsage message) {
         *         return Optional.of(message.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                message != null -> visitor.visitMessage(message)
                compaction != null -> visitor.visitCompaction(compaction)
                advisorMessage != null -> visitor.visitAdvisorMessage(advisorMessage)
                fallbackMessage != null -> visitor.visitFallbackMessage(fallbackMessage)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Iteration = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitMessage(message: BetaMessageIterationUsage) {
                        message.validate()
                    }

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage) {
                        compaction.validate()
                    }

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ) {
                        advisorMessage.validate()
                    }

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ) {
                        fallbackMessage.validate()
                    }
                }
            )
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitMessage(message: BetaMessageIterationUsage) =
                        message.validity()

                    override fun visitCompaction(compaction: BetaCompactionIterationUsage) =
                        compaction.validity()

                    override fun visitAdvisorMessage(
                        advisorMessage: BetaAdvisorMessageIterationUsage
                    ) = advisorMessage.validity()

                    override fun visitFallbackMessage(
                        fallbackMessage: BetaFallbackMessageIterationUsage
                    ) = fallbackMessage.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Iteration &&
                message == other.message &&
                compaction == other.compaction &&
                advisorMessage == other.advisorMessage &&
                fallbackMessage == other.fallbackMessage
        }

        override fun hashCode(): Int =
            Objects.hash(message, compaction, advisorMessage, fallbackMessage)

        override fun toString(): String =
            when {
                message != null -> "Iteration{message=$message}"
                compaction != null -> "Iteration{compaction=$compaction}"
                advisorMessage != null -> "Iteration{advisorMessage=$advisorMessage}"
                fallbackMessage != null -> "Iteration{fallbackMessage=$fallbackMessage}"
                _json != null -> "Iteration{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Iteration")
            }

        companion object {

            /** Token usage for a sampling iteration. */
            @JvmStatic
            fun ofMessage(message: BetaMessageIterationUsage) = Iteration(message = message)

            /** Token usage for a compaction iteration. */
            @JvmStatic
            fun ofCompaction(compaction: BetaCompactionIterationUsage) =
                Iteration(compaction = compaction)

            /** Token usage for an advisor sub-inference iteration. */
            @JvmStatic
            fun ofAdvisorMessage(advisorMessage: BetaAdvisorMessageIterationUsage) =
                Iteration(advisorMessage = advisorMessage)

            /**
             * Token usage for the fallback-model attempt of a server-side fallback request.
             *
             * Produced in place of a `message` entry for whichever hop served the response. A
             * declined hop produces the existing `message` entry. Whether a fallback model served
             * the response is signalled by the presence of this entry in `usage.iterations`.
             */
            @JvmStatic
            fun ofFallbackMessage(fallbackMessage: BetaFallbackMessageIterationUsage) =
                Iteration(fallbackMessage = fallbackMessage)
        }

        /**
         * An interface that defines how to map each variant of [Iteration] to a value of type [T].
         */
        interface Visitor<out T> {

            /** Token usage for a sampling iteration. */
            fun visitMessage(message: BetaMessageIterationUsage): T

            /** Token usage for a compaction iteration. */
            fun visitCompaction(compaction: BetaCompactionIterationUsage): T

            /** Token usage for an advisor sub-inference iteration. */
            fun visitAdvisorMessage(advisorMessage: BetaAdvisorMessageIterationUsage): T

            /**
             * Token usage for the fallback-model attempt of a server-side fallback request.
             *
             * Produced in place of a `message` entry for whichever hop served the response. A
             * declined hop produces the existing `message` entry. Whether a fallback model served
             * the response is signalled by the presence of this entry in `usage.iterations`.
             */
            fun visitFallbackMessage(fallbackMessage: BetaFallbackMessageIterationUsage): T

            /**
             * Maps an unknown variant of [Iteration] to a value of type [T].
             *
             * An instance of [Iteration] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Iteration: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Iteration>(Iteration::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Iteration {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "message" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaMessageIterationUsage>())
                            ?.let { Iteration(message = it, _json = json) }
                            ?: Iteration(_json = json)
                    }
                    "compaction" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCompactionIterationUsage>())
                            ?.let { Iteration(compaction = it, _json = json) }
                            ?: Iteration(_json = json)
                    }
                    "advisor_message" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaAdvisorMessageIterationUsage>(),
                            )
                            ?.let { Iteration(advisorMessage = it, _json = json) }
                            ?: Iteration(_json = json)
                    }
                    "fallback_message" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaFallbackMessageIterationUsage>(),
                            )
                            ?.let { Iteration(fallbackMessage = it, _json = json) }
                            ?: Iteration(_json = json)
                    }
                }

                return Iteration(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Iteration>(Iteration::class) {

            override fun serialize(
                value: Iteration,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.message != null -> generator.writeObject(value.message)
                    value.compaction != null -> generator.writeObject(value.compaction)
                    value.advisorMessage != null -> generator.writeObject(value.advisorMessage)
                    value.fallbackMessage != null -> generator.writeObject(value.fallbackMessage)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Iteration")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val MESSAGE = of("message")

                @JvmField val COMPACTION = of("compaction")

                @JvmField val ADVISOR_MESSAGE = of("advisor_message")

                @JvmField val FALLBACK_MESSAGE = of("fallback_message")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                MESSAGE,
                COMPACTION,
                ADVISOR_MESSAGE,
                FALLBACK_MESSAGE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                MESSAGE,
                COMPACTION,
                ADVISOR_MESSAGE,
                FALLBACK_MESSAGE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    MESSAGE -> Value.MESSAGE
                    COMPACTION -> Value.COMPACTION
                    ADVISOR_MESSAGE -> Value.ADVISOR_MESSAGE
                    FALLBACK_MESSAGE -> Value.FALLBACK_MESSAGE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    MESSAGE -> Known.MESSAGE
                    COMPACTION -> Known.COMPACTION
                    ADVISOR_MESSAGE -> Known.ADVISOR_MESSAGE
                    FALLBACK_MESSAGE -> Known.FALLBACK_MESSAGE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
             * Returns a score indicating how many valid values are contained in this object
             * recursively.
             *
             * Used for best match union deserialization.
             */
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageDeltaUsage &&
            cacheCreationInputTokens == other.cacheCreationInputTokens &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            fallbackCredit == other.fallbackCredit &&
            inputTokens == other.inputTokens &&
            iterations == other.iterations &&
            outputTokens == other.outputTokens &&
            outputTokensDetails == other.outputTokensDetails &&
            serverToolUse == other.serverToolUse &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreationInputTokens,
            cacheReadInputTokens,
            fallbackCredit,
            inputTokens,
            iterations,
            outputTokens,
            outputTokensDetails,
            serverToolUse,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageDeltaUsage{cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, fallbackCredit=$fallbackCredit, inputTokens=$inputTokens, iterations=$iterations, outputTokens=$outputTokens, outputTokensDetails=$outputTokensDetails, serverToolUse=$serverToolUse, additionalProperties=$additionalProperties}"
}
