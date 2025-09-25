// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaToolComputerUse20250124
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val displayHeightPx: JsonField<Long>,
    private val displayWidthPx: JsonField<Long>,
    private val name: JsonValue,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val displayNumber: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("display_height_px")
        @ExcludeMissing
        displayHeightPx: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("display_width_px")
        @ExcludeMissing
        displayWidthPx: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("display_number")
        @ExcludeMissing
        displayNumber: JsonField<Long> = JsonMissing.of(),
    ) : this(
        displayHeightPx,
        displayWidthPx,
        name,
        type,
        cacheControl,
        displayNumber,
        mutableMapOf(),
    )

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
     * This is how the tool will be called by the model and in `tool_use` blocks.
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
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * The X11 display number (e.g. 0, 1) for the display.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayNumber(): Optional<Long> = displayNumber.getOptional("display_number")

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

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

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

        /** Create a cache control breakpoint at this content block. */
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

        /**
         * Returns an immutable instance of [BetaToolComputerUse20250124].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .displayHeightPx()
         * .displayWidthPx()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaToolComputerUse20250124 =
            BetaToolComputerUse20250124(
                checkRequired("displayHeightPx", displayHeightPx),
                checkRequired("displayWidthPx", displayWidthPx),
                name,
                type,
                cacheControl,
                displayNumber,
                additionalProperties.toMutableMap(),
            )
    }

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

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (if (displayHeightPx.asKnown().isPresent) 1 else 0) +
            (if (displayWidthPx.asKnown().isPresent) 1 else 0) +
            name.let { if (it == JsonValue.from("computer")) 1 else 0 } +
            type.let { if (it == JsonValue.from("computer_20250124")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (displayNumber.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolComputerUse20250124 &&
            displayHeightPx == other.displayHeightPx &&
            displayWidthPx == other.displayWidthPx &&
            name == other.name &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            displayNumber == other.displayNumber &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            displayHeightPx,
            displayWidthPx,
            name,
            type,
            cacheControl,
            displayNumber,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolComputerUse20250124{displayHeightPx=$displayHeightPx, displayWidthPx=$displayWidthPx, name=$name, type=$type, cacheControl=$cacheControl, displayNumber=$displayNumber, additionalProperties=$additionalProperties}"
}
