// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects
import java.util.Optional

@JsonDeserialize(builder = BetaUsage.Builder::class)
@NoAutoDetect
class BetaUsage
private constructor(
    private val inputTokens: JsonField<Long>,
    private val cacheCreationInputTokens: JsonField<Long>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val outputTokens: JsonField<Long>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    /** The number of input tokens which were used. */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /** The number of input tokens used to create the cache entry. */
    fun cacheCreationInputTokens(): Optional<Long> =
        Optional.ofNullable(cacheCreationInputTokens.getNullable("cache_creation_input_tokens"))

    /** The number of input tokens read from the cache. */
    fun cacheReadInputTokens(): Optional<Long> =
        Optional.ofNullable(cacheReadInputTokens.getNullable("cache_read_input_tokens"))

    /** The number of output tokens which were used. */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /** The number of input tokens which were used. */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens() = inputTokens

    /** The number of input tokens used to create the cache entry. */
    @JsonProperty("cache_creation_input_tokens")
    @ExcludeMissing
    fun _cacheCreationInputTokens() = cacheCreationInputTokens

    /** The number of input tokens read from the cache. */
    @JsonProperty("cache_read_input_tokens")
    @ExcludeMissing
    fun _cacheReadInputTokens() = cacheReadInputTokens

    /** The number of output tokens which were used. */
    @JsonProperty("output_tokens") @ExcludeMissing fun _outputTokens() = outputTokens

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaUsage = apply {
        if (!validated) {
            inputTokens()
            cacheCreationInputTokens()
            cacheReadInputTokens()
            outputTokens()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var inputTokens: JsonField<Long> = JsonMissing.of()
        private var cacheCreationInputTokens: JsonField<Long> = JsonMissing.of()
        private var cacheReadInputTokens: JsonField<Long> = JsonMissing.of()
        private var outputTokens: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaUsage: BetaUsage) = apply {
            inputTokens = betaUsage.inputTokens
            cacheCreationInputTokens = betaUsage.cacheCreationInputTokens
            cacheReadInputTokens = betaUsage.cacheReadInputTokens
            outputTokens = betaUsage.outputTokens
            additionalProperties = betaUsage.additionalProperties.toMutableMap()
        }

        /** The number of input tokens which were used. */
        fun inputTokens(inputTokens: Long) = inputTokens(JsonField.of(inputTokens))

        /** The number of input tokens which were used. */
        @JsonProperty("input_tokens")
        @ExcludeMissing
        fun inputTokens(inputTokens: JsonField<Long>) = apply { this.inputTokens = inputTokens }

        /** The number of input tokens used to create the cache entry. */
        fun cacheCreationInputTokens(cacheCreationInputTokens: Long) =
            cacheCreationInputTokens(JsonField.of(cacheCreationInputTokens))

        /** The number of input tokens used to create the cache entry. */
        @JsonProperty("cache_creation_input_tokens")
        @ExcludeMissing
        fun cacheCreationInputTokens(cacheCreationInputTokens: JsonField<Long>) = apply {
            this.cacheCreationInputTokens = cacheCreationInputTokens
        }

        /** The number of input tokens read from the cache. */
        fun cacheReadInputTokens(cacheReadInputTokens: Long) =
            cacheReadInputTokens(JsonField.of(cacheReadInputTokens))

        /** The number of input tokens read from the cache. */
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        fun cacheReadInputTokens(cacheReadInputTokens: JsonField<Long>) = apply {
            this.cacheReadInputTokens = cacheReadInputTokens
        }

        /** The number of output tokens which were used. */
        fun outputTokens(outputTokens: Long) = outputTokens(JsonField.of(outputTokens))

        /** The number of output tokens which were used. */
        @JsonProperty("output_tokens")
        @ExcludeMissing
        fun outputTokens(outputTokens: JsonField<Long>) = apply { this.outputTokens = outputTokens }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        @JsonAnySetter
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

        fun build(): BetaUsage =
            BetaUsage(
                inputTokens,
                cacheCreationInputTokens,
                cacheReadInputTokens,
                outputTokens,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaUsage && inputTokens == other.inputTokens && cacheCreationInputTokens == other.cacheCreationInputTokens && cacheReadInputTokens == other.cacheReadInputTokens && outputTokens == other.outputTokens && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(inputTokens, cacheCreationInputTokens, cacheReadInputTokens, outputTokens, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaUsage{inputTokens=$inputTokens, cacheCreationInputTokens=$cacheCreationInputTokens, cacheReadInputTokens=$cacheReadInputTokens, outputTokens=$outputTokens, additionalProperties=$additionalProperties}"
}
