// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
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

class BetaUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreation: JsonField<BetaCacheCreation>,
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val inferenceGeo: JsonField<String>,
    private val inputTokens: JsonField<Long>,
    private val iterations: JsonField<List<BetaIterationsUsageItems>>,
    private val outputTokens: JsonField<Long>,
    private val serverToolUse: JsonField<BetaServerToolUsage>,
    private val serviceTier: JsonField<ServiceTier>,
    private val speed: JsonField<Speed>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cache_creation")
        @ExcludeMissing
        cacheCreation: JsonField<BetaCacheCreation> = JsonMissing.of(),
        @JsonProperty("cache_creation_input_tokens")
        @ExcludeMissing
        cacheCreationInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("inference_geo")
        @ExcludeMissing
        inferenceGeo: JsonField<String> = JsonMissing.of(),
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("iterations")
        @ExcludeMissing
        iterations: JsonField<List<BetaIterationsUsageItems>> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaServerToolUsage> = JsonMissing.of(),
        @JsonProperty("service_tier")
        @ExcludeMissing
        serviceTier: JsonField<ServiceTier> = JsonMissing.of(),
        @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
    ) : this(
        cacheCreation,
        cacheCreationInputTokens,
        cacheReadInputTokens,
        inferenceGeo,
        inputTokens,
        iterations,
        outputTokens,
        serverToolUse,
        serviceTier,
        speed,
        mutableMapOf(),
    )

    /**
     * Breakdown of cached tokens by TTL
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheCreation(): Optional<BetaCacheCreation> = cacheCreation.getOptional("cache_creation")

    /**
     * The number of input tokens used to create the cache entry.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheCreationInputTokens(): Optional<Long> =
        cacheCreationInputTokens.getOptional("cache_creation_input_tokens")

    /**
     * The number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Optional<Long> =
        cacheReadInputTokens.getOptional("cache_read_input_tokens")

    /**
     * The geographic region where inference was performed for this request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inferenceGeo(): Optional<String> = inferenceGeo.getOptional("inference_geo")

    /**
     * The number of input tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

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
    fun iterations(): Optional<List<BetaIterationsUsageItems>> =
        iterations.getOptional("iterations")

    /**
     * The number of output tokens which were used.
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
     * If the request used the priority, standard, or batch tier.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun serviceTier(): Optional<ServiceTier> = serviceTier.getOptional("service_tier")

    /**
     * The inference speed mode used for this request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun speed(): Optional<Speed> = speed.getOptional("speed")

    /**
     * Returns the raw JSON value of [cacheCreation].
     *
     * Unlike [cacheCreation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_creation")
    @ExcludeMissing
    fun _cacheCreation(): JsonField<BetaCacheCreation> = cacheCreation

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
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inference_geo")
    @ExcludeMissing
    fun _inferenceGeo(): JsonField<String> = inferenceGeo

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
    fun _iterations(): JsonField<List<BetaIterationsUsageItems>> = iterations

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

    /**
     * Returns the raw JSON value of [serviceTier].
     *
     * Unlike [serviceTier], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("service_tier")
    @ExcludeMissing
    fun _serviceTier(): JsonField<ServiceTier> = serviceTier

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<Speed> = speed

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
         * Returns a mutable builder for constructing an instance of [BetaUsage].
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inferenceGeo()
         * .inputTokens()
         * .iterations()
         * .outputTokens()
         * .serverToolUse()
         * .serviceTier()
         * .speed()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaUsage]. */
    class Builder internal constructor() {

        private var cacheCreation: JsonField<BetaCacheCreation>? = null
        private var cacheCreationInputTokens: JsonField<Long>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var inferenceGeo: JsonField<String>? = null
        private var inputTokens: JsonField<Long>? = null
        private var iterations: JsonField<MutableList<BetaIterationsUsageItems>>? = null
        private var outputTokens: JsonField<Long>? = null
        private var serverToolUse: JsonField<BetaServerToolUsage>? = null
        private var serviceTier: JsonField<ServiceTier>? = null
        private var speed: JsonField<Speed>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaUsage: BetaUsage) = apply {
            cacheCreation = betaUsage.cacheCreation
            cacheCreationInputTokens = betaUsage.cacheCreationInputTokens
            cacheReadInputTokens = betaUsage.cacheReadInputTokens
            inferenceGeo = betaUsage.inferenceGeo
            inputTokens = betaUsage.inputTokens
            iterations = betaUsage.iterations.map { it.toMutableList() }
            outputTokens = betaUsage.outputTokens
            serverToolUse = betaUsage.serverToolUse
            serviceTier = betaUsage.serviceTier
            speed = betaUsage.speed
            additionalProperties = betaUsage.additionalProperties.toMutableMap()
        }

        /** Breakdown of cached tokens by TTL */
        fun cacheCreation(cacheCreation: BetaCacheCreation?) =
            cacheCreation(JsonField.ofNullable(cacheCreation))

        /** Alias for calling [Builder.cacheCreation] with `cacheCreation.orElse(null)`. */
        fun cacheCreation(cacheCreation: Optional<BetaCacheCreation>) =
            cacheCreation(cacheCreation.getOrNull())

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

        /** The number of input tokens used to create the cache entry. */
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

        /** The number of input tokens read from the cache. */
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

        /** The geographic region where inference was performed for this request. */
        fun inferenceGeo(inferenceGeo: String?) = inferenceGeo(JsonField.ofNullable(inferenceGeo))

        /** Alias for calling [Builder.inferenceGeo] with `inferenceGeo.orElse(null)`. */
        fun inferenceGeo(inferenceGeo: Optional<String>) = inferenceGeo(inferenceGeo.getOrNull())

        /**
         * Sets [Builder.inferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inferenceGeo] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun inferenceGeo(inferenceGeo: JsonField<String>) = apply {
            this.inferenceGeo = inferenceGeo
        }

        /** The number of input tokens which were used. */
        fun inputTokens(inputTokens: Long) = inputTokens(JsonField.of(inputTokens))

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
        fun iterations(iterations: List<BetaIterationsUsageItems>?) =
            iterations(JsonField.ofNullable(iterations))

        /** Alias for calling [Builder.iterations] with `iterations.orElse(null)`. */
        fun iterations(iterations: Optional<List<BetaIterationsUsageItems>>) =
            iterations(iterations.getOrNull())

        /**
         * Sets [Builder.iterations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.iterations] with a well-typed
         * `List<BetaIterationsUsageItems>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun iterations(iterations: JsonField<List<BetaIterationsUsageItems>>) = apply {
            this.iterations = iterations.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaIterationsUsageItems] to [iterations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addIteration(iteration: BetaIterationsUsageItems) = apply {
            iterations =
                (iterations ?: JsonField.of(mutableListOf())).also {
                    checkKnown("iterations", it).add(iteration)
                }
        }

        /**
         * Alias for calling [addIteration] with
         * `BetaIterationsUsageItems.ofMessageIterationUsage(messageIterationUsage)`.
         */
        fun addIteration(messageIterationUsage: BetaMessageIterationUsage) =
            addIteration(BetaIterationsUsageItems.ofMessageIterationUsage(messageIterationUsage))

        /**
         * Alias for calling [addIteration] with
         * `BetaIterationsUsageItems.ofCompactionIterationUsage(compactionIterationUsage)`.
         */
        fun addIteration(compactionIterationUsage: BetaCompactionIterationUsage) =
            addIteration(
                BetaIterationsUsageItems.ofCompactionIterationUsage(compactionIterationUsage)
            )

        /** The number of output tokens which were used. */
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

        /** If the request used the priority, standard, or batch tier. */
        fun serviceTier(serviceTier: ServiceTier?) = serviceTier(JsonField.ofNullable(serviceTier))

        /** Alias for calling [Builder.serviceTier] with `serviceTier.orElse(null)`. */
        fun serviceTier(serviceTier: Optional<ServiceTier>) = serviceTier(serviceTier.getOrNull())

        /**
         * Sets [Builder.serviceTier] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceTier] with a well-typed [ServiceTier] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceTier(serviceTier: JsonField<ServiceTier>) = apply {
            this.serviceTier = serviceTier
        }

        /** The inference speed mode used for this request. */
        fun speed(speed: Speed?) = speed(JsonField.ofNullable(speed))

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<Speed>) = speed(speed.getOrNull())

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [Speed] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun speed(speed: JsonField<Speed>) = apply { this.speed = speed }

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
         * Returns an immutable instance of [BetaUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inferenceGeo()
         * .inputTokens()
         * .iterations()
         * .outputTokens()
         * .serverToolUse()
         * .serviceTier()
         * .speed()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaUsage =
            BetaUsage(
                checkRequired("cacheCreation", cacheCreation),
                checkRequired("cacheCreationInputTokens", cacheCreationInputTokens),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("inferenceGeo", inferenceGeo),
                checkRequired("inputTokens", inputTokens),
                checkRequired("iterations", iterations).map { it.toImmutable() },
                checkRequired("outputTokens", outputTokens),
                checkRequired("serverToolUse", serverToolUse),
                checkRequired("serviceTier", serviceTier),
                checkRequired("speed", speed),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaUsage = apply {
        if (validated) {
            return@apply
        }

        cacheCreation().ifPresent { it.validate() }
        cacheCreationInputTokens()
        cacheReadInputTokens()
        inferenceGeo()
        inputTokens()
        iterations().ifPresent { it.forEach { it.validate() } }
        outputTokens()
        serverToolUse().ifPresent { it.validate() }
        serviceTier().ifPresent { it.validate() }
        speed().ifPresent { it.validate() }
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
            (if (cacheCreationInputTokens.asKnown().isPresent) 1 else 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (if (inferenceGeo.asKnown().isPresent) 1 else 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (iterations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0) +
            (serviceTier.asKnown().getOrNull()?.validity() ?: 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0)

    /** Token usage for a sampling iteration. */
    @JsonDeserialize(using = BetaIterationsUsageItems.Deserializer::class)
    @JsonSerialize(using = BetaIterationsUsageItems.Serializer::class)
    class BetaIterationsUsageItems
    private constructor(
        private val messageIterationUsage: BetaMessageIterationUsage? = null,
        private val compactionIterationUsage: BetaCompactionIterationUsage? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Token usage for a sampling iteration. */
        fun messageIterationUsage(): Optional<BetaMessageIterationUsage> =
            Optional.ofNullable(messageIterationUsage)

        /** Token usage for a compaction iteration. */
        fun compactionIterationUsage(): Optional<BetaCompactionIterationUsage> =
            Optional.ofNullable(compactionIterationUsage)

        fun isMessageIterationUsage(): Boolean = messageIterationUsage != null

        fun isCompactionIterationUsage(): Boolean = compactionIterationUsage != null

        /** Token usage for a sampling iteration. */
        fun asMessageIterationUsage(): BetaMessageIterationUsage =
            messageIterationUsage.getOrThrow("messageIterationUsage")

        /** Token usage for a compaction iteration. */
        fun asCompactionIterationUsage(): BetaCompactionIterationUsage =
            compactionIterationUsage.getOrThrow("compactionIterationUsage")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                messageIterationUsage != null ->
                    visitor.visitMessageIterationUsage(messageIterationUsage)
                compactionIterationUsage != null ->
                    visitor.visitCompactionIterationUsage(compactionIterationUsage)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): BetaIterationsUsageItems = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitMessageIterationUsage(
                        messageIterationUsage: BetaMessageIterationUsage
                    ) {
                        messageIterationUsage.validate()
                    }

                    override fun visitCompactionIterationUsage(
                        compactionIterationUsage: BetaCompactionIterationUsage
                    ) {
                        compactionIterationUsage.validate()
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
                    override fun visitMessageIterationUsage(
                        messageIterationUsage: BetaMessageIterationUsage
                    ) = messageIterationUsage.validity()

                    override fun visitCompactionIterationUsage(
                        compactionIterationUsage: BetaCompactionIterationUsage
                    ) = compactionIterationUsage.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is BetaIterationsUsageItems &&
                messageIterationUsage == other.messageIterationUsage &&
                compactionIterationUsage == other.compactionIterationUsage
        }

        override fun hashCode(): Int = Objects.hash(messageIterationUsage, compactionIterationUsage)

        override fun toString(): String =
            when {
                messageIterationUsage != null ->
                    "BetaIterationsUsageItems{messageIterationUsage=$messageIterationUsage}"
                compactionIterationUsage != null ->
                    "BetaIterationsUsageItems{compactionIterationUsage=$compactionIterationUsage}"
                _json != null -> "BetaIterationsUsageItems{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid BetaIterationsUsageItems")
            }

        companion object {

            /** Token usage for a sampling iteration. */
            @JvmStatic
            fun ofMessageIterationUsage(messageIterationUsage: BetaMessageIterationUsage) =
                BetaIterationsUsageItems(messageIterationUsage = messageIterationUsage)

            /** Token usage for a compaction iteration. */
            @JvmStatic
            fun ofCompactionIterationUsage(compactionIterationUsage: BetaCompactionIterationUsage) =
                BetaIterationsUsageItems(compactionIterationUsage = compactionIterationUsage)
        }

        /**
         * An interface that defines how to map each variant of [BetaIterationsUsageItems] to a
         * value of type [T].
         */
        interface Visitor<out T> {

            /** Token usage for a sampling iteration. */
            fun visitMessageIterationUsage(messageIterationUsage: BetaMessageIterationUsage): T

            /** Token usage for a compaction iteration. */
            fun visitCompactionIterationUsage(
                compactionIterationUsage: BetaCompactionIterationUsage
            ): T

            /**
             * Maps an unknown variant of [BetaIterationsUsageItems] to a value of type [T].
             *
             * An instance of [BetaIterationsUsageItems] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown BetaIterationsUsageItems: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<BetaIterationsUsageItems>(BetaIterationsUsageItems::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): BetaIterationsUsageItems {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaMessageIterationUsage>())?.let {
                                BetaIterationsUsageItems(messageIterationUsage = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<BetaCompactionIterationUsage>())
                                ?.let {
                                    BetaIterationsUsageItems(
                                        compactionIterationUsage = it,
                                        _json = json,
                                    )
                                },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> BetaIterationsUsageItems(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer :
            BaseSerializer<BetaIterationsUsageItems>(BetaIterationsUsageItems::class) {

            override fun serialize(
                value: BetaIterationsUsageItems,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.messageIterationUsage != null ->
                        generator.writeObject(value.messageIterationUsage)
                    value.compactionIterationUsage != null ->
                        generator.writeObject(value.compactionIterationUsage)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid BetaIterationsUsageItems")
                }
            }
        }
    }

    /** If the request used the priority, standard, or batch tier. */
    class ServiceTier @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val STANDARD = of("standard")

            @JvmField val PRIORITY = of("priority")

            @JvmField val BATCH = of("batch")

            @JvmStatic fun of(value: String) = ServiceTier(JsonField.of(value))
        }

        /** An enum containing [ServiceTier]'s known values. */
        enum class Known {
            STANDARD,
            PRIORITY,
            BATCH,
        }

        /**
         * An enum containing [ServiceTier]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ServiceTier] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STANDARD,
            PRIORITY,
            BATCH,
            /**
             * An enum member indicating that [ServiceTier] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                STANDARD -> Value.STANDARD
                PRIORITY -> Value.PRIORITY
                BATCH -> Value.BATCH
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                STANDARD -> Known.STANDARD
                PRIORITY -> Known.PRIORITY
                BATCH -> Known.BATCH
                else -> throw AnthropicInvalidDataException("Unknown ServiceTier: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): ServiceTier = apply {
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

            return other is ServiceTier && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** The inference speed mode used for this request. */
    class Speed @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val STANDARD = of("standard")

            @JvmField val FAST = of("fast")

            @JvmStatic fun of(value: String) = Speed(JsonField.of(value))
        }

        /** An enum containing [Speed]'s known values. */
        enum class Known {
            STANDARD,
            FAST,
        }

        /**
         * An enum containing [Speed]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Speed] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            STANDARD,
            FAST,
            /** An enum member indicating that [Speed] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                STANDARD -> Value.STANDARD
                FAST -> Value.FAST
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                STANDARD -> Known.STANDARD
                FAST -> Known.FAST
                else -> throw AnthropicInvalidDataException("Unknown Speed: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Speed = apply {
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

            return other is Speed && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaUsage &&
            cacheCreation == other.cacheCreation &&
            cacheCreationInputTokens == other.cacheCreationInputTokens &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inferenceGeo == other.inferenceGeo &&
            inputTokens == other.inputTokens &&
            iterations == other.iterations &&
            outputTokens == other.outputTokens &&
            serverToolUse == other.serverToolUse &&
            serviceTier == other.serviceTier &&
            speed == other.speed &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreation,
            cacheCreationInputTokens,
            cacheReadInputTokens,
            inferenceGeo,
            inputTokens,
            iterations,
            outputTokens,
            serverToolUse,
            serviceTier,
            speed,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaUsage{cacheCreation=$cacheCreation, cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, inferenceGeo=$inferenceGeo, inputTokens=$inputTokens, iterations=$iterations, outputTokens=$outputTokens, serverToolUse=$serverToolUse, serviceTier=$serviceTier, speed=$speed, additionalProperties=$additionalProperties}"
}
