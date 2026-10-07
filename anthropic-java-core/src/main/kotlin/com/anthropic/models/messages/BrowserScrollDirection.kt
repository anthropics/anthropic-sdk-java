package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BrowserScrollDirection private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val UP = BrowserScrollDirection(JsonField.of("up"))

        @JvmField val DOWN = BrowserScrollDirection(JsonField.of("down"))

        @JvmField val LEFT = BrowserScrollDirection(JsonField.of("left"))

        @JvmField val RIGHT = BrowserScrollDirection(JsonField.of("right"))

        @JvmStatic
        fun of(value: String): BrowserScrollDirection =
            // Intern known values so `==` works
            when (value) {
                "up" -> UP
                "down" -> DOWN
                "left" -> LEFT
                "right" -> RIGHT
                else -> BrowserScrollDirection(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BrowserScrollDirection =
            value.asString().getOrNull()?.let { of(it) } ?: BrowserScrollDirection(value)
    }

    /** An enum containing [BrowserScrollDirection]'s known values. */
    enum class Known {
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }

    /**
     * An enum containing [BrowserScrollDirection]'s known values, as well as an [_UNKNOWN] member.
     *
     * An instance of [BrowserScrollDirection] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        /**
         * An enum member indicating that [BrowserScrollDirection] was instantiated with an unknown
         * value.
         */
        _UNKNOWN,
    }

    /**
     * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN] if
     * the class was instantiated with an unknown value.
     *
     * Use the [known] method instead if you're certain the value is always known or if you want to
     * throw for the unknown case.
     */
    fun value(): Value =
        when (this) {
            UP -> Value.UP
            DOWN -> Value.DOWN
            LEFT -> Value.LEFT
            RIGHT -> Value.RIGHT
            else -> Value._UNKNOWN
        }

    /**
     * Returns an enum member corresponding to this class instance's value.
     *
     * Use the [value] method instead if you're uncertain the value is always known and don't want
     * to throw for the unknown case.
     *
     * @throws AnthropicInvalidDataException if this class instance's value is a not a known member.
     */
    fun known(): Known =
        when (this) {
            UP -> Known.UP
            DOWN -> Known.DOWN
            LEFT -> Known.LEFT
            RIGHT -> Known.RIGHT
            else -> throw AnthropicInvalidDataException("Unknown BrowserScrollDirection: $value")
        }

    /**
     * Returns this class instance's primitive wire representation.
     *
     * This differs from the [toString] method because that method is primarily for debugging and
     * generally doesn't throw.
     *
     * @throws AnthropicInvalidDataException if this class instance's value does not have the
     *   expected primitive type.
     */
    fun asString(): String =
        _value().asString().orElseThrow { AnthropicInvalidDataException("Value is not a String") }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BrowserScrollDirection = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserScrollDirection && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
