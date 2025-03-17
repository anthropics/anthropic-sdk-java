// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
import kotlin.jvm.optionals.getOrNull

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

    /**
     * The height of the display in pixels.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayHeightPx(): Long = displayHeightPx.getRequired("display_height_px")

    /**
     * The width of the display in pixels.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayWidthPx(): Long = displayWidthPx.getRequired("display_width_px")

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in tool_use blocks.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("computer")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("computer_20250124")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        Optional.ofNullable(cacheControl.getNullable("cache_control"))

    /**
     * The X11 display number (e.g. 0, 1) for the display.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayNumber(): Optional<Long> =
        Optional.ofNullable(displayNumber.getNullable("display_number"))

    /**
     * Returns the raw JSON value of [displayHeightPx].
     *
     * Unlike [displayHeightPx], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_height_px")
    @ExcludeMissing
    fun _displayHeightPx(): JsonField<Long> = displayHeightPx

    /**
     * Returns the raw JSON value of [displayWidthPx].
     *
     * Unlike [displayWidthPx], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_width_px")
    @ExcludeMissing
    fun _displayWidthPx(): JsonField<Long> = displayWidthPx

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [displayNumber].
     *
     * Unlike [displayNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
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

        /**
         * Returns a mutable builder for constructing an instance of [BetaToolComputerUse20250124].
         *
         * The following fields are required:
         * ```java
         * .displayHeightPx()
         * .displayWidthPx()
         * ```
         */
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

        /**
         * Sets [Builder.displayHeightPx] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayHeightPx] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayHeightPx(displayHeightPx: JsonField<Long>) = apply {
            this.displayHeightPx = displayHeightPx
        }

        /** The width of the display in pixels. */
        fun displayWidthPx(displayWidthPx: Long) = displayWidthPx(JsonField.of(displayWidthPx))

        /**
         * Sets [Builder.displayWidthPx] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayWidthPx] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayWidthPx(displayWidthPx: JsonField<Long>) = apply {
            this.displayWidthPx = displayWidthPx
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("computer")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("computer_20250124")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /** The X11 display number (e.g. 0, 1) for the display. */
        fun displayNumber(displayNumber: Long?) = displayNumber(JsonField.ofNullable(displayNumber))

        /**
         * Alias for [Builder.displayNumber].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun displayNumber(displayNumber: Long) = displayNumber(displayNumber as Long?)

        /** Alias for calling [Builder.displayNumber] with `displayNumber.orElse(null)`. */
        fun displayNumber(displayNumber: Optional<Long>) = displayNumber(displayNumber.getOrNull())

        /**
         * Sets [Builder.displayNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayNumber] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
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
