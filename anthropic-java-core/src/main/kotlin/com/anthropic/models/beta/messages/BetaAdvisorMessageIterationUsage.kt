// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Token usage for an advisor sub-inference iteration. */
class BetaAdvisorMessageIterationUsage
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cacheCreation: JsonField<BetaCacheCreation>,
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val inputTokens: JsonField<Long>,
    private val model: JsonField<Model>,
    private val outputTokens: JsonField<Long>,
    private val type: JsonValue,
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
        @JsonProperty("input_tokens")
        @ExcludeMissing
        inputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        cacheCreation,
        cacheCreationInputTokens,
        cacheReadInputTokens,
        inputTokens,
        model,
        outputTokens,
        type,
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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheCreationInputTokens(): Long =
        cacheCreationInputTokens.getRequired("cache_creation_input_tokens")

    /**
     * The number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Long = cacheReadInputTokens.getRequired("cache_read_input_tokens")

    /**
     * The number of input tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /**
     * The model that will complete your prompt.\n\nSee
     * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
     * options.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): Model = model.getRequired("model")

    /**
     * The number of output tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /**
     * Usage for an advisor sub-inference iteration
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("advisor_message")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
     * Returns the raw JSON value of [inputTokens].
     *
     * Unlike [inputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens(): JsonField<Long> = inputTokens

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Long> = outputTokens

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
         * [BetaAdvisorMessageIterationUsage].
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .model()
         * .outputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAdvisorMessageIterationUsage]. */
    class Builder internal constructor() {

        private var cacheCreation: JsonField<BetaCacheCreation>? = null
        private var cacheCreationInputTokens: JsonField<Long>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var inputTokens: JsonField<Long>? = null
        private var model: JsonField<Model>? = null
        private var outputTokens: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("advisor_message")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAdvisorMessageIterationUsage: BetaAdvisorMessageIterationUsage) =
            apply {
                cacheCreation = betaAdvisorMessageIterationUsage.cacheCreation
                cacheCreationInputTokens = betaAdvisorMessageIterationUsage.cacheCreationInputTokens
                cacheReadInputTokens = betaAdvisorMessageIterationUsage.cacheReadInputTokens
                inputTokens = betaAdvisorMessageIterationUsage.inputTokens
                model = betaAdvisorMessageIterationUsage.model
                outputTokens = betaAdvisorMessageIterationUsage.outputTokens
                type = betaAdvisorMessageIterationUsage.type
                additionalProperties =
                    betaAdvisorMessageIterationUsage.additionalProperties.toMutableMap()
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
        fun cacheCreationInputTokens(cacheCreationInputTokens: Long) =
            cacheCreationInputTokens(JsonField.of(cacheCreationInputTokens))

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
         * The model that will complete your prompt.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun model(model: Model) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { this.model = model }

        /**
         * Sets [model] to an arbitrary [String].
         *
         * You should usually call [model] with a well-typed [Model] constant instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(value: String) = model(Model.of(value))

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("advisor_message")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaAdvisorMessageIterationUsage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheCreation()
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .model()
         * .outputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAdvisorMessageIterationUsage =
            BetaAdvisorMessageIterationUsage(
                checkRequired("cacheCreation", cacheCreation),
                checkRequired("cacheCreationInputTokens", cacheCreationInputTokens),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("inputTokens", inputTokens),
                checkRequired("model", model),
                checkRequired("outputTokens", outputTokens),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaAdvisorMessageIterationUsage = apply {
        if (validated) {
            return@apply
        }

        cacheCreation().ifPresent { it.validate() }
        cacheCreationInputTokens()
        cacheReadInputTokens()
        inputTokens()
        model()
        outputTokens()
        _type().let {
            if (it != JsonValue.from("advisor_message")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("advisor_message")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAdvisorMessageIterationUsage &&
            cacheCreation == other.cacheCreation &&
            cacheCreationInputTokens == other.cacheCreationInputTokens &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            inputTokens == other.inputTokens &&
            model == other.model &&
            outputTokens == other.outputTokens &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            cacheCreation,
            cacheCreationInputTokens,
            cacheReadInputTokens,
            inputTokens,
            model,
            outputTokens,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAdvisorMessageIterationUsage{cacheCreation=$cacheCreation, cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, model=$model, outputTokens=$outputTokens, type=$type, additionalProperties=$additionalProperties}"
}
