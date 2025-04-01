// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

class Usage
private constructor(
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val inputTokens: JsonField<Long>,
    private val outputTokens: JsonField<Long>,
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
    ) : this(
        cacheCreationInputTokens,
        cacheReadInputTokens,
        inputTokens,
        outputTokens,
        mutableMapOf(),
    )

    /**
     * The number of input tokens used to create the cache entry.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheCreationInputTokens(): Optional<Long> =
        Optional.ofNullable(cacheCreationInputTokens.getNullable("cache_creation_input_tokens"))

    /**
     * The number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Optional<Long> =
        Optional.ofNullable(cacheReadInputTokens.getNullable("cache_read_input_tokens"))

    /**
     * The number of input tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /**
     * The number of output tokens which were used.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

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
         * Returns a mutable builder for constructing an instance of [Usage].
         *
         * The following fields are required:
         * ```java
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .outputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [Usage]. */
    class Builder internal constructor() {

        private var cacheCreationInputTokens: JsonField<Long>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var inputTokens: JsonField<Long>? = null
        private var outputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(usage: Usage) = apply {
            cacheCreationInputTokens = usage.cacheCreationInputTokens
            cacheReadInputTokens = usage.cacheReadInputTokens
            inputTokens = usage.inputTokens
            outputTokens = usage.outputTokens
            additionalProperties = usage.additionalProperties.toMutableMap()
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
         * Returns an immutable instance of [Usage].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cacheCreationInputTokens()
         * .cacheReadInputTokens()
         * .inputTokens()
         * .outputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): Usage =
            Usage(
                checkRequired("cacheCreationInputTokens", cacheCreationInputTokens),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("inputTokens", inputTokens),
                checkRequired("outputTokens", outputTokens),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): Usage = apply {
        if (validated) {
            return@apply
        }

        cacheCreationInputTokens()
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
        (if (cacheCreationInputTokens.asKnown().isPresent) 1 else 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (if (inputTokens.asKnown().isPresent) 1 else 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Usage && cacheCreationInputTokens == other.cacheCreationInputTokens && cacheReadInputTokens == other.cacheReadInputTokens && inputTokens == other.inputTokens && outputTokens == other.outputTokens && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheCreationInputTokens, cacheReadInputTokens, inputTokens, outputTokens, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Usage{cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, inputTokens=$inputTokens, outputTokens=$outputTokens, additionalProperties=$additionalProperties}"
}
