// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaThinkingConfigAdaptive
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val display: JsonField<Display>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("display") @ExcludeMissing display: JsonField<Display> = JsonMissing.of(),
    ) : this(type, display, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("adaptive")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Controls how thinking content appears in the response. When set to `summarized`, thinking is
     * returned normally. When set to `omitted`, thinking content is redacted but a signature is
     * returned for multi-turn continuity. Defaults to `summarized`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun display(): Optional<Display> = display.getOptional("display")

    /**
     * Returns the raw JSON value of [display].
     *
     * Unlike [display], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display") @ExcludeMissing fun _display(): JsonField<Display> = display

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
         * Returns a mutable builder for constructing an instance of [BetaThinkingConfigAdaptive].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaThinkingConfigAdaptive]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("adaptive")
        private var display: JsonField<Display> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaThinkingConfigAdaptive: BetaThinkingConfigAdaptive) = apply {
            type = betaThinkingConfigAdaptive.type
            display = betaThinkingConfigAdaptive.display
            additionalProperties = betaThinkingConfigAdaptive.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("adaptive")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Controls how thinking content appears in the response. When set to `summarized`, thinking
         * is returned normally. When set to `omitted`, thinking content is redacted but a signature
         * is returned for multi-turn continuity. Defaults to `summarized`.
         */
        fun display(display: Display?) = display(JsonField.ofNullable(display))

        /** Alias for calling [Builder.display] with `display.orElse(null)`. */
        fun display(display: Optional<Display>) = display(display.getOrNull())

        /**
         * Sets [Builder.display] to an arbitrary JSON value.
         *
         * You should usually call [Builder.display] with a well-typed [Display] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun display(display: JsonField<Display>) = apply { this.display = display }

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
         * Returns an immutable instance of [BetaThinkingConfigAdaptive].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaThinkingConfigAdaptive =
            BetaThinkingConfigAdaptive(type, display, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaThinkingConfigAdaptive = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("adaptive")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        display().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("adaptive")) 1 else 0 } +
            (display.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Controls how thinking content appears in the response. When set to `summarized`, thinking is
     * returned normally. When set to `omitted`, thinking content is redacted but a signature is
     * returned for multi-turn continuity. Defaults to `summarized`.
     */
    class Display @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val SUMMARIZED = of("summarized")

            @JvmField val OMITTED = of("omitted")

            @JvmStatic fun of(value: String) = Display(JsonField.of(value))
        }

        /** An enum containing [Display]'s known values. */
        enum class Known {
            SUMMARIZED,
            OMITTED,
        }

        /**
         * An enum containing [Display]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Display] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            SUMMARIZED,
            OMITTED,
            /** An enum member indicating that [Display] was instantiated with an unknown value. */
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
                SUMMARIZED -> Value.SUMMARIZED
                OMITTED -> Value.OMITTED
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
                SUMMARIZED -> Known.SUMMARIZED
                OMITTED -> Known.OMITTED
                else -> throw AnthropicInvalidDataException("Unknown Display: $value")
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Display = apply {
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

            return other is Display && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaThinkingConfigAdaptive &&
            type == other.type &&
            display == other.display &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, display, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaThinkingConfigAdaptive{type=$type, display=$display, additionalProperties=$additionalProperties}"
}
