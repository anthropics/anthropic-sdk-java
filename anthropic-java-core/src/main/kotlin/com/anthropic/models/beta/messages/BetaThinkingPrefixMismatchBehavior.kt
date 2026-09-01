// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * What happens when a thinking block in `messages` fails the conversation check: it was created in
 * a different conversation, or the messages before it have changed since. `"error"` (the default)
 * fails the request with a 400 error. `"drop_block"` removes the failing blocks and the request
 * proceeds; the model no longer sees the dropped reasoning.
 */
class BetaThinkingPrefixMismatchBehavior
@JsonCreator
private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val ERROR = of("error")

        @JvmField val DROP_BLOCK = of("drop_block")

        @JvmStatic fun of(value: String) = BetaThinkingPrefixMismatchBehavior(JsonField.of(value))

        @JvmSynthetic
        internal fun of(value: JsonField<String>): BetaThinkingPrefixMismatchBehavior =
            value.asString().getOrNull()?.let { of(it) }
                ?: BetaThinkingPrefixMismatchBehavior(value)
    }

    /** An enum containing [BetaThinkingPrefixMismatchBehavior]'s known values. */
    enum class Known {
        ERROR,
        DROP_BLOCK,
    }

    /**
     * An enum containing [BetaThinkingPrefixMismatchBehavior]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaThinkingPrefixMismatchBehavior] can contain an unknown value in a couple
     * of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        ERROR,
        DROP_BLOCK,
        /**
         * An enum member indicating that [BetaThinkingPrefixMismatchBehavior] was instantiated with
         * an unknown value.
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
            ERROR -> Value.ERROR
            DROP_BLOCK -> Value.DROP_BLOCK
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
            ERROR -> Known.ERROR
            DROP_BLOCK -> Known.DROP_BLOCK
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaThinkingPrefixMismatchBehavior: $value"
                )
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
    fun validate(): BetaThinkingPrefixMismatchBehavior = apply {
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

        return other is BetaThinkingPrefixMismatchBehavior && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
