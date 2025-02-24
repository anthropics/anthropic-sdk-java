// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.checkRequired
import com.anthropic.core.immutableEmptyMap
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Objects

@NoAutoDetect
class RedactedThinkingBlock
@JsonCreator
private constructor(
    @JsonProperty("data") @ExcludeMissing private val data: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun data(): String = data.getRequired("data")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<String> = data

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun toParam(): RedactedThinkingBlockParam =
        RedactedThinkingBlockParam.builder().data(_data()).build()

    private var validated: Boolean = false

    fun validate(): RedactedThinkingBlock = apply {
        if (validated) {
            return@apply
        }

        data()
        _type().let {
            if (it != JsonValue.from("redacted_thinking")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [RedactedThinkingBlock]. */
    class Builder internal constructor() {

        private var data: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("redacted_thinking")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(redactedThinkingBlock: RedactedThinkingBlock) = apply {
            data = redactedThinkingBlock.data
            type = redactedThinkingBlock.type
            additionalProperties = redactedThinkingBlock.additionalProperties.toMutableMap()
        }

        fun data(data: String) = data(JsonField.of(data))

        fun data(data: JsonField<String>) = apply { this.data = data }

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

        fun build(): RedactedThinkingBlock =
            RedactedThinkingBlock(
                checkRequired("data", data),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is RedactedThinkingBlock && data == other.data && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(data, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "RedactedThinkingBlock{data=$data, type=$type, additionalProperties=$additionalProperties}"
}
