// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import com.anthropic.core.Enum
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
import java.util.Optional

@NoAutoDetect
class BetaToolComputerUse20241022
@JsonCreator
private constructor(
    @JsonProperty("display_height_px")
    @ExcludeMissing
    private val displayHeightPx: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("display_width_px")
    @ExcludeMissing
    private val displayWidthPx: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("name") @ExcludeMissing private val name: JsonField<Name> = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonField<Type> = JsonMissing.of(),
    @JsonProperty("cache_control")
    @ExcludeMissing
    private val cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    @JsonProperty("display_number")
    @ExcludeMissing
    private val displayNumber: JsonField<Long> = JsonMissing.of(),
    @JsonAnySetter private val additionalProperties: Map<String, JsonValue> = immutableEmptyMap(),
) {

    /** The height of the display in pixels. */
    fun displayHeightPx(): Long = displayHeightPx.getRequired("display_height_px")

    /** The width of the display in pixels. */
    fun displayWidthPx(): Long = displayWidthPx.getRequired("display_width_px")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    fun name(): Name = name.getRequired("name")

    fun type(): Type = type.getRequired("type")

    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /** The X11 display number (e.g. 0, 1) for the display. */
    fun displayNumber(): Optional<Long> =
        Optional.ofNullable(displayNumber.getNullable("display_number"))

    /** The height of the display in pixels. */
    @JsonProperty("display_height_px")
    @ExcludeMissing
    fun _displayHeightPx(): JsonField<Long> = displayHeightPx

    /** The width of the display in pixels. */
    @JsonProperty("display_width_px")
    @ExcludeMissing
    fun _displayWidthPx(): JsonField<Long> = displayWidthPx

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<Name> = name

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /** The X11 display number (e.g. 0, 1) for the display. */
    @JsonProperty("display_number")
    @ExcludeMissing
    fun _displayNumber(): JsonField<Long> = displayNumber

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

    private var validated: Boolean = false

    fun validate(): BetaToolComputerUse20241022 = apply {
        if (validated) {
            return@apply
        }

        displayHeightPx()
        displayWidthPx()
        name()
        type()
        cacheControl().ifPresent { it.validate() }
        displayNumber()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    class Builder {

        private var displayHeightPx: JsonField<Long>? = null
        private var displayWidthPx: JsonField<Long>? = null
        private var name: JsonField<Name>? = null
        private var type: JsonField<Type>? = null
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var displayNumber: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolComputerUse20241022: BetaToolComputerUse20241022) = apply {
            displayHeightPx = betaToolComputerUse20241022.displayHeightPx
            displayWidthPx = betaToolComputerUse20241022.displayWidthPx
            name = betaToolComputerUse20241022.name
            type = betaToolComputerUse20241022.type
            cacheControl = betaToolComputerUse20241022.cacheControl
            displayNumber = betaToolComputerUse20241022.displayNumber
            additionalProperties = betaToolComputerUse20241022.additionalProperties.toMutableMap()
        }

        /** The height of the display in pixels. */
        fun displayHeightPx(displayHeightPx: Long) = displayHeightPx(JsonField.of(displayHeightPx))

        /** The height of the display in pixels. */
        fun displayHeightPx(displayHeightPx: JsonField<Long>) = apply {
            this.displayHeightPx = displayHeightPx
        }

        /** The width of the display in pixels. */
        fun displayWidthPx(displayWidthPx: Long) = displayWidthPx(JsonField.of(displayWidthPx))

        /** The width of the display in pixels. */
        fun displayWidthPx(displayWidthPx: JsonField<Long>) = apply {
            this.displayWidthPx = displayWidthPx
        }

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
        fun name(name: JsonField<Name>) = apply { this.name = name }

        fun type(type: Type) = type(JsonField.of(type))

        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.orElse(null))

        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /** The X11 display number (e.g. 0, 1) for the display. */
        fun displayNumber(displayNumber: Long?) = displayNumber(JsonField.ofNullable(displayNumber))

        /** The X11 display number (e.g. 0, 1) for the display. */
        fun displayNumber(displayNumber: Long) = displayNumber(displayNumber as Long?)

        /** The X11 display number (e.g. 0, 1) for the display. */
        @Suppress("USELESS_CAST") // See https://youtrack.jetbrains.com/issue/KT-74228
        fun displayNumber(displayNumber: Optional<Long>) =
            displayNumber(displayNumber.orElse(null) as Long?)

        /** The X11 display number (e.g. 0, 1) for the display. */
        fun displayNumber(displayNumber: JsonField<Long>) = apply {
            this.displayNumber = displayNumber
        }

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

        fun build(): BetaToolComputerUse20241022 =
            BetaToolComputerUse20241022(
                checkRequired("displayHeightPx", displayHeightPx),
                checkRequired("displayWidthPx", displayWidthPx),
                checkRequired("name", name),
                checkRequired("type", type),
                cacheControl,
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

        return /* spotless:off */ other is BetaToolComputerUse20241022 && displayHeightPx == other.displayHeightPx && displayWidthPx == other.displayWidthPx && name == other.name && type == other.type && cacheControl == other.cacheControl && displayNumber == other.displayNumber && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(displayHeightPx, displayWidthPx, name, type, cacheControl, displayNumber, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolComputerUse20241022{displayHeightPx=$displayHeightPx, displayWidthPx=$displayWidthPx, name=$name, type=$type, cacheControl=$cacheControl, displayNumber=$displayNumber, additionalProperties=$additionalProperties}"
}
