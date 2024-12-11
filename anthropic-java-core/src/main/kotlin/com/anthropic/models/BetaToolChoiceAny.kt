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

/** The model will use any available tools. */
@JsonDeserialize(builder = BetaToolChoiceAny.Builder::class)
@NoAutoDetect
class BetaToolChoiceAny
private constructor(
    private val type: JsonField<Type>,
    private val disableParallelToolUse: JsonField<Boolean>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun type(): Type = type.getRequired("type")

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
     */
    fun disableParallelToolUse(): Optional<Boolean> =
        Optional.ofNullable(disableParallelToolUse.getNullable("disable_parallel_tool_use"))

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
     */
    @JsonProperty("disable_parallel_tool_use")
    @ExcludeMissing
    fun _disableParallelToolUse() = disableParallelToolUse

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaToolChoiceAny = apply {
        if (!validated) {
            type()
            disableParallelToolUse()
            validated = true
        }
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var type: JsonField<Type> = JsonMissing.of()
        private var disableParallelToolUse: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChoiceAny: BetaToolChoiceAny) = apply {
            this.type = betaToolChoiceAny.type
            this.disableParallelToolUse = betaToolChoiceAny.disableParallelToolUse
            additionalProperties(betaToolChoiceAny.additionalProperties)
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /**
         * Whether to disable parallel tool use.
         *
         * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
         */
        fun disableParallelToolUse(disableParallelToolUse: Boolean) =
            disableParallelToolUse(JsonField.of(disableParallelToolUse))

        /**
         * Whether to disable parallel tool use.
         *
         * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
         */
        @JsonProperty("disable_parallel_tool_use")
        @ExcludeMissing
        fun disableParallelToolUse(disableParallelToolUse: JsonField<Boolean>) = apply {
            this.disableParallelToolUse = disableParallelToolUse
        }

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

        fun build(): BetaToolChoiceAny =
            BetaToolChoiceAny(
                type,
                disableParallelToolUse,
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

            @JvmField val ANY = of("any")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            ANY,
        }

        enum class Value {
            ANY,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                ANY -> Value.ANY
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                ANY -> Known.ANY
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolChoiceAny && type == other.type && disableParallelToolUse == other.disableParallelToolUse && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, disableParallelToolUse, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChoiceAny{type=$type, disableParallelToolUse=$disableParallelToolUse, additionalProperties=$additionalProperties}"
}
