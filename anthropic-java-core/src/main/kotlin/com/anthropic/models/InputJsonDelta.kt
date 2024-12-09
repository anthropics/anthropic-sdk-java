// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.NoAutoDetect
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.Objects

@JsonDeserialize(builder = InputJsonDelta.Builder::class)
@NoAutoDetect
class InputJsonDelta
private constructor(
    private val type: JsonField<Type>,
    private val partialJson: JsonField<String>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    fun partialJson(): String = partialJson.getRequired("partial_json")

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("partial_json") @ExcludeMissing fun _partialJson() = partialJson

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): InputJsonDelta = apply {
        if (!validated) {
            type()
            partialJson()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var partialJson: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(inputJsonDelta: InputJsonDelta) = apply {
            this.type = inputJsonDelta.type
            this.partialJson = inputJsonDelta.partialJson
            additionalProperties(inputJsonDelta.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun partialJson(partialJson: String) = partialJson(JsonField.of(partialJson))

        @JsonProperty("partial_json")
        @ExcludeMissing
        fun partialJson(partialJson: JsonField<String>) = apply { this.partialJson = partialJson }

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

        fun build(): InputJsonDelta =
            InputJsonDelta(
                type,
                partialJson,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val INPUT_JSON_DELTA = Type(JsonField.of("input_json_delta"))

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            INPUT_JSON_DELTA,
        }

        enum class Value {
            INPUT_JSON_DELTA,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                INPUT_JSON_DELTA -> Value.INPUT_JSON_DELTA
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                INPUT_JSON_DELTA -> Known.INPUT_JSON_DELTA
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is InputJsonDelta && type == other.type && partialJson == other.partialJson && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, partialJson, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "InputJsonDelta{type=$type, partialJson=$partialJson, additionalProperties=$additionalProperties}"
}
