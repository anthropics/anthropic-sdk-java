// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
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

class BetaMessageDeltaUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val inputTokens: JsonField<Long>,
    private val iterations: JsonField<List<BetaIterationsUsageItems>>,
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
        @JsonProperty("iterations")
        @ExcludeMissing
        iterations: JsonField<List<BetaIterationsUsageItems>> = JsonMissing.of(),
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
        iterations,
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
         * .iterations()
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
        private var iterations: JsonField<MutableList<BetaIterationsUsageItems>>? = null
        private var outputTokens: JsonField<Long>? = null
        private var serverToolUse: JsonField<BetaServerToolUsage>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageDeltaUsage: BetaMessageDeltaUsage) = apply {
            cacheCreationInputTokens = betaMessageDeltaUsage.cacheCreationInputTokens
            cacheReadInputTokens = betaMessageDeltaUsage.cacheReadInputTokens
            inputTokens = betaMessageDeltaUsage.inputTokens
            iterations = betaMessageDeltaUsage.iterations.map { it.toMutableList() }
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
         * .iterations()
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
                checkRequired("iterations", iterations).map { it.toImmutable() },
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
        iterations().ifPresent { it.forEach { it.validate() } }
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
            (iterations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0)

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageDeltaUsage &&
            cacheCreationInputTokens == other.cacheCreationInputTokens &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inputTokens == other.inputTokens &&
            iterations == other.iterations &&
            outputTokens == other.outputTokens &&
            serverToolUse == other.serverToolUse &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreationInputTokens,
            cacheReadInputTokens,
            inputTokens,
            iterations,
            outputTokens,
            serverToolUse,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageDeltaUsage{cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, iterations=$iterations, outputTokens=$outputTokens, serverToolUse=$serverToolUse, additionalProperties=$additionalProperties}"
}
