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

/** The model will use the specified tool with `tool_choice.name`. */
@JsonDeserialize(builder = BetaToolChoiceTool.Builder::class)
@NoAutoDetect
class BetaToolChoiceTool
private constructor(
    private val type: JsonField<Type>,
    private val name: JsonField<String>,
    private val disableParallelToolUse: JsonField<Boolean>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    fun type(): Type = type.getRequired("type")

    /** The name of the tool to use. */
    fun name(): String = name.getRequired("name")

    /**
     * Whether to disable parallel tool use.
     *
     * Defaults to `false`. If set to `true`, the model will output exactly one tool use.
     */
    fun disableParallelToolUse(): Optional<Boolean> =
        Optional.ofNullable(disableParallelToolUse.getNullable("disable_parallel_tool_use"))

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /** The name of the tool to use. */
    @JsonProperty("name") @ExcludeMissing fun _name() = name

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

    private var validated: Boolean = false

    fun validate(): BetaToolChoiceTool = apply {
        if (!validated) {
            type()
            name()
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
        private var name: JsonField<String> = JsonMissing.of()
        private var disableParallelToolUse: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolChoiceTool: BetaToolChoiceTool) = apply {
            type = betaToolChoiceTool.type
            name = betaToolChoiceTool.name
            disableParallelToolUse = betaToolChoiceTool.disableParallelToolUse
            additionalProperties = betaToolChoiceTool.additionalProperties.toMutableMap()
        }

        fun type(type: Type) = type(JsonField.of(type))

        @JsonProperty("type")
        @ExcludeMissing
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** The name of the tool to use. */
        fun name(name: String) = name(JsonField.of(name))

        /** The name of the tool to use. */
        @JsonProperty("name")
        @ExcludeMissing
        fun name(name: JsonField<String>) = apply { this.name = name }

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

        fun build(): BetaToolChoiceTool =
            BetaToolChoiceTool(
                type,
                name,
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

        companion object {

            @JvmField val TOOL = of("tool")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            TOOL,
        }

        enum class Value {
            TOOL,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TOOL -> Value.TOOL
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TOOL -> Known.TOOL
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

        return /* spotless:off */ other is BetaToolChoiceTool && type == other.type && name == other.name && disableParallelToolUse == other.disableParallelToolUse && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(type, name, disableParallelToolUse, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolChoiceTool{type=$type, name=$name, disableParallelToolUse=$disableParallelToolUse, additionalProperties=$additionalProperties}"
}
