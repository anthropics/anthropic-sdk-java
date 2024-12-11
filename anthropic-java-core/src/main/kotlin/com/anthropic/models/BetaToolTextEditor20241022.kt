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

@JsonDeserialize(builder = BetaToolTextEditor20241022.Builder::class)
@NoAutoDetect
class BetaToolTextEditor20241022
private constructor(
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val type: JsonField<Type>,
    private val name: JsonField<Name>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    private var validated: Boolean = false

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun type(): Type = type.getRequired("type")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    fun name(): Name = name.getRequired("name")

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    @JsonProperty("name") @ExcludeMissing fun _name() = name

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    fun validate(): BetaToolTextEditor20241022 = apply {
        if (!validated) {
            cacheControl().map { it.validate() }
            type()
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
        private var name: JsonField<Name> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolTextEditor20241022: BetaToolTextEditor20241022) = apply {
            this.cacheControl = betaToolTextEditor20241022.cacheControl
            this.type = betaToolTextEditor20241022.type
            this.name = betaToolTextEditor20241022.name
            additionalProperties(betaToolTextEditor20241022.additionalProperties)
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

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in tool_use blocks.
         */
        fun name(name: Name) = name(JsonField.of(name))

        /**
         * Name of the tool.
         *
         * This is how the tool will be called by the model and in tool_use blocks.
         */
        @JsonProperty("name")
        @ExcludeMissing
        fun name(name: JsonField<Name>) = apply { this.name = name }

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

        fun build(): BetaToolTextEditor20241022 =
            BetaToolTextEditor20241022(
                cacheControl,
                type,
                name,
                additionalProperties.toImmutable(),
            )
    }

    class Name
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Name && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()

        companion object {

            @JvmField val STR_REPLACE_EDITOR = of("str_replace_editor")

            @JvmStatic fun of(value: String) = Name(JsonField.of(value))
        }

        enum class Known {
            STR_REPLACE_EDITOR,
        }

        enum class Value {
            STR_REPLACE_EDITOR,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                STR_REPLACE_EDITOR -> Value.STR_REPLACE_EDITOR
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                STR_REPLACE_EDITOR -> Known.STR_REPLACE_EDITOR
                else -> throw AnthropicInvalidDataException("Unknown Name: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
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

            @JvmField val TEXT_EDITOR_20241022 = of("text_editor_20241022")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            TEXT_EDITOR_20241022,
        }

        enum class Value {
            TEXT_EDITOR_20241022,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                TEXT_EDITOR_20241022 -> Value.TEXT_EDITOR_20241022
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                TEXT_EDITOR_20241022 -> Known.TEXT_EDITOR_20241022
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        fun asString(): String = _value().asStringOrThrow()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolTextEditor20241022 && cacheControl == other.cacheControl && type == other.type && name == other.name && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheControl, type, name, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolTextEditor20241022{cacheControl=$cacheControl, type=$type, name=$name, additionalProperties=$additionalProperties}"
}
