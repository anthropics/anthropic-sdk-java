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
import java.util.Optional

@NoAutoDetect
class BetaToolComputerUse20250124
@JsonCreator
private constructor(
    @JsonProperty("display_height_px")
    @ExcludeMissing
    private val displayHeightPx: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("display_width_px")
    @ExcludeMissing
    private val displayWidthPx: JsonField<Long> = JsonMissing.of(),
    @JsonProperty("name") @ExcludeMissing private val name: JsonValue = JsonMissing.of(),
    @JsonProperty("type") @ExcludeMissing private val type: JsonValue = JsonMissing.of(),
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
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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

    fun validate(): BetaToolComputerUse20250124 = apply {
        if (validated) {
            return@apply
        }

        displayHeightPx()
        displayWidthPx()
        _name().let {
            if (it != JsonValue.from("computer")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("computer_20250124")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        displayNumber()
        validated = true
    }

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolComputerUse20250124]. */
    class Builder internal constructor() {

        private var displayHeightPx: JsonField<Long>? = null
        private var displayWidthPx: JsonField<Long>? = null
        private var name: JsonValue = JsonValue.from("computer")
        private var type: JsonValue = JsonValue.from("computer_20250124")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var displayNumber: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolComputerUse20250124: BetaToolComputerUse20250124) = apply {
            displayHeightPx = betaToolComputerUse20250124.displayHeightPx
            displayWidthPx = betaToolComputerUse20250124.displayWidthPx
            name = betaToolComputerUse20250124.name
            type = betaToolComputerUse20250124.type
            cacheControl = betaToolComputerUse20250124.cacheControl
            displayNumber = betaToolComputerUse20250124.displayNumber
            additionalProperties = betaToolComputerUse20250124.additionalProperties.toMutableMap()
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
        fun name(name: JsonValue) = apply { this.name = name }

        fun type(type: JsonValue) = apply { this.type = type }

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

        fun build(): BetaToolComputerUse20250124 =
            BetaToolComputerUse20250124(
                checkRequired("displayHeightPx", displayHeightPx),
                checkRequired("displayWidthPx", displayWidthPx),
                name,
                type,
                cacheControl,
                displayNumber,
                additionalProperties.toImmutable(),
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaToolComputerUse20250124 && displayHeightPx == other.displayHeightPx && displayWidthPx == other.displayWidthPx && name == other.name && type == other.type && cacheControl == other.cacheControl && displayNumber == other.displayNumber && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(displayHeightPx, displayWidthPx, name, type, cacheControl, displayNumber, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolComputerUse20250124{displayHeightPx=$displayHeightPx, displayWidthPx=$displayWidthPx, name=$name, type=$type, cacheControl=$cacheControl, displayNumber=$displayNumber, additionalProperties=$additionalProperties}"
}
