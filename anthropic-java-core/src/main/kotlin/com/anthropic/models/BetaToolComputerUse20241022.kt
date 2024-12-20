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

@JsonDeserialize(builder = BetaToolComputerUse20241022.Builder::class)
@NoAutoDetect
class BetaToolComputerUse20241022
private constructor(
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val type: JsonField<Type>,
    private val name: JsonField<Name>,
    private val displayHeightPx: JsonField<Long>,
    private val displayWidthPx: JsonField<Long>,
    private val displayNumber: JsonField<Long>,
    private val additionalProperties: Map<String, JsonValue>,
) {

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    fun type(): Type = type.getRequired("type")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    fun name(): Name = name.getRequired("name")

    /** The height of the display in pixels. */
    fun displayHeightPx(): Long = displayHeightPx.getRequired("display_height_px")

    /** The width of the display in pixels. */
    fun displayWidthPx(): Long = displayWidthPx.getRequired("display_width_px")

    /** The X11 display number (e.g. 0, 1) for the display. */
    fun displayNumber(): Optional<Long> =
        Optional.ofNullable(displayNumber.getNullable("display_number"))

    @JsonProperty("cache_control") @ExcludeMissing fun _cacheControl() = cacheControl

    @JsonProperty("type") @ExcludeMissing fun _type() = type

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    @JsonProperty("name") @ExcludeMissing fun _name() = name

    /** The height of the display in pixels. */
    @JsonProperty("display_height_px") @ExcludeMissing fun _displayHeightPx() = displayHeightPx

    /** The width of the display in pixels. */
    @JsonProperty("display_width_px") @ExcludeMissing fun _displayWidthPx() = displayWidthPx

    /** The X11 display number (e.g. 0, 1) for the display. */
    @JsonProperty("display_number") @ExcludeMissing fun _displayNumber() = displayNumber

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaToolComputerUse20241022 = apply {
        if (!validated) {
            cacheControl().map { it.validate() }
            type()
            name()
            displayHeightPx()
            displayWidthPx()
            displayNumber()
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
        private var displayHeightPx: JsonField<Long> = JsonMissing.of()
        private var displayWidthPx: JsonField<Long> = JsonMissing.of()
        private var displayNumber: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolComputerUse20241022: BetaToolComputerUse20241022) = apply {
            cacheControl = betaToolComputerUse20241022.cacheControl
            type = betaToolComputerUse20241022.type
            name = betaToolComputerUse20241022.name
            displayHeightPx = betaToolComputerUse20241022.displayHeightPx
            displayWidthPx = betaToolComputerUse20241022.displayWidthPx
            displayNumber = betaToolComputerUse20241022.displayNumber
            additionalProperties = betaToolComputerUse20241022.additionalProperties.toMutableMap()
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

        /** The height of the display in pixels. */
        fun displayHeightPx(displayHeightPx: Long) = displayHeightPx(JsonField.of(displayHeightPx))

        /** The height of the display in pixels. */
        @JsonProperty("display_height_px")
        @ExcludeMissing
        fun displayHeightPx(displayHeightPx: JsonField<Long>) = apply {
            this.displayHeightPx = displayHeightPx
        }

        /** The width of the display in pixels. */
        fun displayWidthPx(displayWidthPx: Long) = displayWidthPx(JsonField.of(displayWidthPx))

        /** The width of the display in pixels. */
        @JsonProperty("display_width_px")
        @ExcludeMissing
        fun displayWidthPx(displayWidthPx: JsonField<Long>) = apply {
            this.displayWidthPx = displayWidthPx
        }

        /** The X11 display number (e.g. 0, 1) for the display. */
        fun displayNumber(displayNumber: Long) = displayNumber(JsonField.of(displayNumber))

        /** The X11 display number (e.g. 0, 1) for the display. */
        @JsonProperty("display_number")
        @ExcludeMissing
        fun displayNumber(displayNumber: JsonField<Long>) = apply {
            this.displayNumber = displayNumber
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

        fun build(): BetaToolComputerUse20241022 =
            BetaToolComputerUse20241022(
                cacheControl,
                type,
                name,
                displayHeightPx,
                displayWidthPx,
                displayNumber,
                additionalProperties.toImmutable(),
            )
    }

    class Name
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val COMPUTER = of("computer")

            @JvmStatic fun of(value: String) = Name(JsonField.of(value))
        }

        enum class Known {
            COMPUTER,
        }

        enum class Value {
            COMPUTER,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                COMPUTER -> Value.COMPUTER
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                COMPUTER -> Known.COMPUTER
                else -> throw AnthropicInvalidDataException("Unknown Name: $value")
            }

        fun asString(): String = _value().asStringOrThrow()

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return /* spotless:off */ other is Name && value == other.value /* spotless:on */
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Type
    @JsonCreator
    private constructor(
        private val value: JsonField<String>,
    ) : Enum {

        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val COMPUTER_20241022 = of("computer_20241022")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        enum class Known {
            COMPUTER_20241022,
        }

        enum class Value {
            COMPUTER_20241022,
            _UNKNOWN,
        }

        fun value(): Value =
            when (this) {
                COMPUTER_20241022 -> Value.COMPUTER_20241022
                else -> Value._UNKNOWN
            }

        fun known(): Known =
            when (this) {
                COMPUTER_20241022 -> Known.COMPUTER_20241022
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

        return /* spotless:off */ other is BetaToolComputerUse20241022 && cacheControl == other.cacheControl && type == other.type && name == other.name && displayHeightPx == other.displayHeightPx && displayWidthPx == other.displayWidthPx && displayNumber == other.displayNumber && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(cacheControl, type, name, displayHeightPx, displayWidthPx, displayNumber, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolComputerUse20241022{cacheControl=$cacheControl, type=$type, name=$name, displayHeightPx=$displayHeightPx, displayWidthPx=$displayWidthPx, displayNumber=$displayNumber, additionalProperties=$additionalProperties}"
}
