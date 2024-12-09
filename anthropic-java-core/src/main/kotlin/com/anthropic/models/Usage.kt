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

@JsonDeserialize(builder = Usage.Builder::class)
@NoAutoDetect
class Usage
private constructor(
    private val inputTokens: JsonField<Long>,
    private val outputTokens: JsonField<Long>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    /** The number of input tokens which were used. */
    fun inputTokens(): Long = inputTokens.getRequired("input_tokens")

    /** The number of output tokens which were used. */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /** The number of input tokens which were used. */
    @JsonProperty("input_tokens") @ExcludeMissing fun _inputTokens() = inputTokens

    /** The number of output tokens which were used. */
    @JsonProperty("output_tokens") @ExcludeMissing fun _outputTokens() = outputTokens

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): Usage = apply {
        if (!validated) {
            inputTokens()
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
        private var outputTokens: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(usage: Usage) = apply {
            this.inputTokens = usage.inputTokens
            this.outputTokens = usage.outputTokens
            additionalProperties(usage.additionalProperties)
        }

        /** The number of input tokens which were used. */
        fun inputTokens(inputTokens: Long) = inputTokens(JsonField.of(inputTokens))

        /** The number of input tokens which were used. */
        @JsonProperty("input_tokens")
        @ExcludeMissing
        fun inputTokens(inputTokens: JsonField<Long>) = apply { this.inputTokens = inputTokens }

        /** The number of output tokens which were used. */
        fun outputTokens(outputTokens: Long) = outputTokens(JsonField.of(outputTokens))

        /** The number of output tokens which were used. */
        @JsonProperty("output_tokens")
        @ExcludeMissing
        fun outputTokens(outputTokens: JsonField<Long>) = apply { this.outputTokens = outputTokens }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            this.additionalProperties.putAll(additionalProperties)
        }

        @JsonAnySetter
        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            this.additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun build(): Usage =
            Usage(
                inputTokens,
                outputTokens,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is Usage && inputTokens == other.inputTokens && outputTokens == other.outputTokens && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(inputTokens, outputTokens, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "Usage{inputTokens=$inputTokens, outputTokens=$outputTokens, additionalProperties=$additionalProperties}"
}
