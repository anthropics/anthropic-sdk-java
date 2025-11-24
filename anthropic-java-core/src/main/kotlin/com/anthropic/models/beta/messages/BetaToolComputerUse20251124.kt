// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaToolComputerUse20251124
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val displayHeightPx: JsonField<Long>,
    private val displayWidthPx: JsonField<Long>,
    private val name: JsonValue,
    private val type: JsonValue,
    private val allowedCallers: JsonField<List<AllowedCaller>>,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val deferLoading: JsonField<Boolean>,
    private val displayNumber: JsonField<Long>,
    private val enableZoom: JsonField<Boolean>,
    private val inputExamples: JsonField<List<InputExample>>,
    private val strict: JsonField<Boolean>,
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
        @JsonProperty("allowed_callers")
        @ExcludeMissing
        allowedCallers: JsonField<List<AllowedCaller>> = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("defer_loading")
        @ExcludeMissing
        deferLoading: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("display_number")
        @ExcludeMissing
        displayNumber: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("enable_zoom")
        @ExcludeMissing
        enableZoom: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("input_examples")
        @ExcludeMissing
        inputExamples: JsonField<List<InputExample>> = JsonMissing.of(),
        @JsonProperty("strict") @ExcludeMissing strict: JsonField<Boolean> = JsonMissing.of(),
    ) : this(
        displayHeightPx,
        displayWidthPx,
        name,
        type,
        allowedCallers,
        cacheControl,
        deferLoading,
        displayNumber,
        enableZoom,
        inputExamples,
        strict,
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
     * JsonValue.from("computer_20251124")
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
    fun allowedCallers(): Optional<List<AllowedCaller>> =
        allowedCallers.getOptional("allowed_callers")

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * If true, tool will not be included in initial system prompt. Only loaded when returned via
     * tool_reference from tool search.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

    /**
     * The X11 display number (e.g. 0, 1) for the display.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayNumber(): Optional<Long> = displayNumber.getOptional("display_number")

    /**
     * Whether to enable an action to take a zoomed-in screenshot of the screen.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enableZoom(): Optional<Boolean> = enableZoom.getOptional("enable_zoom")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inputExamples(): Optional<List<InputExample>> = inputExamples.getOptional("input_examples")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun strict(): Optional<Boolean> = strict.getOptional("strict")

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
     * Returns the raw JSON value of [allowedCallers].
     *
     * Unlike [allowedCallers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_callers")
    @ExcludeMissing
    fun _allowedCallers(): JsonField<List<AllowedCaller>> = allowedCallers

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [deferLoading].
     *
     * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("defer_loading")
    @ExcludeMissing
    fun _deferLoading(): JsonField<Boolean> = deferLoading

    /**
     * Returns the raw JSON value of [displayNumber].
     *
     * Unlike [displayNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_number")
    @ExcludeMissing
    fun _displayNumber(): JsonField<Long> = displayNumber

    /**
     * Returns the raw JSON value of [enableZoom].
     *
     * Unlike [enableZoom], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enable_zoom") @ExcludeMissing fun _enableZoom(): JsonField<Boolean> = enableZoom

    /**
     * Returns the raw JSON value of [inputExamples].
     *
     * Unlike [inputExamples], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("input_examples")
    @ExcludeMissing
    fun _inputExamples(): JsonField<List<InputExample>> = inputExamples

    /**
     * Returns the raw JSON value of [strict].
     *
     * Unlike [strict], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("strict") @ExcludeMissing fun _strict(): JsonField<Boolean> = strict

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
         * Returns a mutable builder for constructing an instance of [BetaToolComputerUse20251124].
         *
         * The following fields are required:
         * ```java
         * .displayHeightPx()
         * .displayWidthPx()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaToolComputerUse20251124]. */
    class Builder internal constructor() {

        private var displayHeightPx: JsonField<Long>? = null
        private var displayWidthPx: JsonField<Long>? = null
        private var name: JsonValue = JsonValue.from("computer")
        private var type: JsonValue = JsonValue.from("computer_20251124")
        private var allowedCallers: JsonField<MutableList<AllowedCaller>>? = null
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var deferLoading: JsonField<Boolean> = JsonMissing.of()
        private var displayNumber: JsonField<Long> = JsonMissing.of()
        private var enableZoom: JsonField<Boolean> = JsonMissing.of()
        private var inputExamples: JsonField<MutableList<InputExample>>? = null
        private var strict: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaToolComputerUse20251124: BetaToolComputerUse20251124) = apply {
            displayHeightPx = betaToolComputerUse20251124.displayHeightPx
            displayWidthPx = betaToolComputerUse20251124.displayWidthPx
            name = betaToolComputerUse20251124.name
            type = betaToolComputerUse20251124.type
            allowedCallers = betaToolComputerUse20251124.allowedCallers.map { it.toMutableList() }
            cacheControl = betaToolComputerUse20251124.cacheControl
            deferLoading = betaToolComputerUse20251124.deferLoading
            displayNumber = betaToolComputerUse20251124.displayNumber
            enableZoom = betaToolComputerUse20251124.enableZoom
            inputExamples = betaToolComputerUse20251124.inputExamples.map { it.toMutableList() }
            strict = betaToolComputerUse20251124.strict
            additionalProperties = betaToolComputerUse20251124.additionalProperties.toMutableMap()
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
         * JsonValue.from("computer_20251124")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun allowedCallers(allowedCallers: List<AllowedCaller>) =
            allowedCallers(JsonField.of(allowedCallers))

        /**
         * Sets [Builder.allowedCallers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedCallers] with a well-typed `List<AllowedCaller>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun allowedCallers(allowedCallers: JsonField<List<AllowedCaller>>) = apply {
            this.allowedCallers = allowedCallers.map { it.toMutableList() }
        }

        /**
         * Adds a single [AllowedCaller] to [allowedCallers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedCaller(allowedCaller: AllowedCaller) = apply {
            allowedCallers =
                (allowedCallers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedCallers", it).add(allowedCaller)
                }
        }

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

        /**
         * If true, tool will not be included in initial system prompt. Only loaded when returned
         * via tool_reference from tool search.
         */
        fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

        /**
         * Sets [Builder.deferLoading] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
            this.deferLoading = deferLoading
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

        /** Whether to enable an action to take a zoomed-in screenshot of the screen. */
        fun enableZoom(enableZoom: Boolean) = enableZoom(JsonField.of(enableZoom))

        /**
         * Sets [Builder.enableZoom] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enableZoom] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun enableZoom(enableZoom: JsonField<Boolean>) = apply { this.enableZoom = enableZoom }

        fun inputExamples(inputExamples: List<InputExample>) =
            inputExamples(JsonField.of(inputExamples))

        /**
         * Sets [Builder.inputExamples] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inputExamples] with a well-typed `List<InputExample>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun inputExamples(inputExamples: JsonField<List<InputExample>>) = apply {
            this.inputExamples = inputExamples.map { it.toMutableList() }
        }

        /**
         * Adds a single [InputExample] to [inputExamples].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInputExample(inputExample: InputExample) = apply {
            inputExamples =
                (inputExamples ?: JsonField.of(mutableListOf())).also {
                    checkKnown("inputExamples", it).add(inputExample)
                }
        }

        fun strict(strict: Boolean) = strict(JsonField.of(strict))

        /**
         * Sets [Builder.strict] to an arbitrary JSON value.
         *
         * You should usually call [Builder.strict] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun strict(strict: JsonField<Boolean>) = apply { this.strict = strict }

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
         * Returns an immutable instance of [BetaToolComputerUse20251124].
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
        fun build(): BetaToolComputerUse20251124 =
            BetaToolComputerUse20251124(
                checkRequired("displayHeightPx", displayHeightPx),
                checkRequired("displayWidthPx", displayWidthPx),
                name,
                type,
                (allowedCallers ?: JsonMissing.of()).map { it.toImmutable() },
                cacheControl,
                deferLoading,
                displayNumber,
                enableZoom,
                (inputExamples ?: JsonMissing.of()).map { it.toImmutable() },
                strict,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaToolComputerUse20251124 = apply {
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
            if (it != JsonValue.from("computer_20251124")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        allowedCallers().ifPresent { it.forEach { it.validate() } }
        cacheControl().ifPresent { it.validate() }
        deferLoading()
        displayNumber()
        enableZoom()
        inputExamples().ifPresent { it.forEach { it.validate() } }
        strict()
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
            type.let { if (it == JsonValue.from("computer_20251124")) 1 else 0 } +
            (allowedCallers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (deferLoading.asKnown().isPresent) 1 else 0) +
            (if (displayNumber.asKnown().isPresent) 1 else 0) +
            (if (enableZoom.asKnown().isPresent) 1 else 0) +
            (inputExamples.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (strict.asKnown().isPresent) 1 else 0)

    class AllowedCaller @JsonCreator private constructor(private val value: JsonField<String>) :
        Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val DIRECT = of("direct")

            @JvmField val CODE_EXECUTION_20250825 = of("code_execution_20250825")

            @JvmStatic fun of(value: String) = AllowedCaller(JsonField.of(value))
        }

        /** An enum containing [AllowedCaller]'s known values. */
        enum class Known {
            DIRECT,
            CODE_EXECUTION_20250825,
        }

        /**
         * An enum containing [AllowedCaller]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [AllowedCaller] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DIRECT,
            CODE_EXECUTION_20250825,
            /**
             * An enum member indicating that [AllowedCaller] was instantiated with an unknown
             * value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                DIRECT -> Value.DIRECT
                CODE_EXECUTION_20250825 -> Value.CODE_EXECUTION_20250825
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                DIRECT -> Known.DIRECT
                CODE_EXECUTION_20250825 -> Known.CODE_EXECUTION_20250825
                else -> throw AnthropicInvalidDataException("Unknown AllowedCaller: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): AllowedCaller = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AllowedCaller && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class InputExample
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [InputExample]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [InputExample]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(inputExample: InputExample) = apply {
                additionalProperties = inputExample.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [InputExample].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): InputExample = InputExample(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        fun validate(): InputExample = apply {
            if (validated) {
                return@apply
            }

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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is InputExample && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "InputExample{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolComputerUse20251124 &&
            displayHeightPx == other.displayHeightPx &&
            displayWidthPx == other.displayWidthPx &&
            name == other.name &&
            type == other.type &&
            allowedCallers == other.allowedCallers &&
            cacheControl == other.cacheControl &&
            deferLoading == other.deferLoading &&
            displayNumber == other.displayNumber &&
            enableZoom == other.enableZoom &&
            inputExamples == other.inputExamples &&
            strict == other.strict &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            displayHeightPx,
            displayWidthPx,
            name,
            type,
            allowedCallers,
            cacheControl,
            deferLoading,
            displayNumber,
            enableZoom,
            inputExamples,
            strict,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaToolComputerUse20251124{displayHeightPx=$displayHeightPx, displayWidthPx=$displayWidthPx, name=$name, type=$type, allowedCallers=$allowedCallers, cacheControl=$cacheControl, deferLoading=$deferLoading, displayNumber=$displayNumber, enableZoom=$enableZoom, inputExamples=$inputExamples, strict=$strict, additionalProperties=$additionalProperties}"
}
