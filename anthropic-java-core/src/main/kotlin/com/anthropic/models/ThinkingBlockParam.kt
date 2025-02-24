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
class ThinkingBlockParam
@JsonCreator
private constructor(
    @JsonProperty("signature")
    @ExcludeMissing
    private val signature: JsonField<String> = JsonMissing.of(),
    @JsonProperty("thinking")
    @ExcludeMissing
    private val thinking: JsonField<String> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    fun signature(): String = signature.getRequired("signature")

    fun thinking(): String = thinking.getRequired("thinking")

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    @JsonProperty("signature") @ExcludeMissing fun _signature(): JsonField<String> = signature

    @JsonProperty("thinking") @ExcludeMissing fun _thinking(): JsonField<String> = thinking

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): ThinkingBlockParam = apply {
        if (validated) {
            return@apply
        }

        signature()
        thinking()
        _type().let {
            if (it != JsonValue.from("thinking")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThinkingBlockParam]. */
    class Builder internal constructor() {

        private var signature: JsonField<String>? = null
        private var thinking: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("thinking")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(thinkingBlockParam: ThinkingBlockParam) = apply {
            signature = thinkingBlockParam.signature
            thinking = thinkingBlockParam.thinking
            type = thinkingBlockParam.type
            additionalProperties = thinkingBlockParam.additionalProperties.toMutableMap()
        }

        fun signature(signature: String) = signature(JsonField.of(signature))

        fun signature(signature: JsonField<String>) = apply { this.signature = signature }

        fun thinking(thinking: String) = thinking(JsonField.of(thinking))

        fun thinking(thinking: JsonField<String>) = apply { this.thinking = thinking }

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

        fun build(): ThinkingBlockParam =
            ThinkingBlockParam(
                checkRequired("signature", signature),
                checkRequired("thinking", thinking),
                type,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is ThinkingBlockParam && signature == other.signature && thinking == other.thinking && type == other.type && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(signature, thinking, type, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThinkingBlockParam{signature=$signature, thinking=$thinking, type=$type, additionalProperties=$additionalProperties}"
}
