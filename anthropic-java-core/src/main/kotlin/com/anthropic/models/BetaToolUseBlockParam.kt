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
import java.util.Optional

@JsonDeserialize(builder = BetaToolUseBlockParam.Builder::class)
@NoAutoDetect
class BetaToolUseBlockParam
private constructor(
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val type: JsonField<Type>,
    private val id: JsonField<String>,
    private val name: JsonField<String>,
    private val input: JsonValue,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun type(): Type = type.getRequired("type")

    fun id(): String = id.getRequired("id")

    fun name(): String = name.getRequired("name")

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    @JsonProperty("id") @ExcludeMissing fun _id() = id

    @JsonProperty("name") @ExcludeMissing fun _name() = name

    @JsonProperty("input") @ExcludeMissing fun _input() = input

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaToolUseBlockParam = apply {
        if (!validated) {
            cacheControl().map { it.validate() }
            type()
            id()
            name()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var type: JsonField<Type> = JsonMissing.of()
        private var id: JsonField<String> = JsonMissing.of()
        private var name: JsonField<String> = JsonMissing.of()
        private var input: JsonValue = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolUseBlockParam: BetaToolUseBlockParam) = apply {
            this.cacheControl = betaToolUseBlockParam.cacheControl
            this.type = betaToolUseBlockParam.type
            this.id = betaToolUseBlockParam.id
            this.name = betaToolUseBlockParam.name
            this.input = betaToolUseBlockParam.input
            additionalProperties(betaToolUseBlockParam.additionalProperties)
        }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral) =
            cacheControl(JsonField.of(cacheControl))

        @JsonProperty("cache_control")
        @ExcludeMissing
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun id(id: String) = id(JsonField.of(id))

        @JsonProperty("id") @ExcludeMissing fun id(id: JsonField<String>) = apply { this.id = id }

        fun name(name: String) = name(JsonField.of(name))

        @JsonProperty("name")
        @ExcludeMissing
        fun name(name: JsonField<String>) = apply { this.name = name }

        @JsonProperty("input")
        @ExcludeMissing
        fun input(input: JsonValue) = apply { this.input = input }

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

        fun build(): BetaToolUseBlockParam =
            BetaToolUseBlockParam(
                cacheControl,
                type,
                id,
                name,
                input,
                additionalProperties.toImmutable(),
            )
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TOOL_USE = of("tool_use")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            TOOL_USE,
        }

        enum class Value {
            TOOL_USE,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TOOL_USE -> Value.TOOL_USE
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TOOL_USE -> Known.TOOL_USE
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Type && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolUseBlockParam && cacheControl == other.cacheControl && type == other.type && id == other.id && name == other.name && input == other.input && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheControl, type, id, name, input, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolUseBlockParam{cacheControl=$cacheControl, type=$type, id=$id, name=$name, input=$input, additionalProperties=$additionalProperties}"
}
