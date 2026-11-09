package com.anthropic.models.messages

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class ThreadUnsupportedFeatureName private constructor(private val value: JsonField<String>) :
    Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val CACHE_CONTROL = ThreadUnsupportedFeatureName(JsonField.of("cache_control"))

        @JvmField
        val CONTEXT_MANAGEMENT = ThreadUnsupportedFeatureName(JsonField.of("context_management"))

        @JvmField val FALLBACKS = ThreadUnsupportedFeatureName(JsonField.of("fallbacks"))

        @JvmField val HIPAA = ThreadUnsupportedFeatureName(JsonField.of("hipaa"))

        @JvmField
        val INFERENCE_HOOKS = ThreadUnsupportedFeatureName(JsonField.of("inference_hooks"))

        @JvmField val THINKING = ThreadUnsupportedFeatureName(JsonField.of("thinking"))

        @JvmStatic
        fun of(value: String): ThreadUnsupportedFeatureName =
            // Intern known values so `==` works
            when (value) {
                "cache_control" -> CACHE_CONTROL
                "context_management" -> CONTEXT_MANAGEMENT
                "fallbacks" -> FALLBACKS
                "hipaa" -> HIPAA
                "inference_hooks" -> INFERENCE_HOOKS
                "thinking" -> THINKING
                else -> ThreadUnsupportedFeatureName(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): ThreadUnsupportedFeatureName =
            value.asString().getOrNull()?.let { of(it) } ?: ThreadUnsupportedFeatureName(value)
    }

    /** An enum containing [ThreadUnsupportedFeatureName]'s known values. */
    enum class Known {
        CACHE_CONTROL,
        CONTEXT_MANAGEMENT,
        FALLBACKS,
        HIPAA,
        INFERENCE_HOOKS,
        THINKING,
    }

    /**
     * An enum containing [ThreadUnsupportedFeatureName]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [ThreadUnsupportedFeatureName] can contain an unknown value in a couple of
     * cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        CACHE_CONTROL,
        CONTEXT_MANAGEMENT,
        FALLBACKS,
        HIPAA,
        INFERENCE_HOOKS,
        THINKING,
        /**
         * An enum member indicating that [ThreadUnsupportedFeatureName] was instantiated with an
         * unknown value.
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
            CACHE_CONTROL -> Value.CACHE_CONTROL
            CONTEXT_MANAGEMENT -> Value.CONTEXT_MANAGEMENT
            FALLBACKS -> Value.FALLBACKS
            HIPAA -> Value.HIPAA
            INFERENCE_HOOKS -> Value.INFERENCE_HOOKS
            THINKING -> Value.THINKING
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
            CACHE_CONTROL -> Known.CACHE_CONTROL
            CONTEXT_MANAGEMENT -> Known.CONTEXT_MANAGEMENT
            FALLBACKS -> Known.FALLBACKS
            HIPAA -> Known.HIPAA
            INFERENCE_HOOKS -> Known.INFERENCE_HOOKS
            THINKING -> Known.THINKING
            else ->
                throw AnthropicInvalidDataException("Unknown ThreadUnsupportedFeatureName: $value")
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
    fun validate(): ThreadUnsupportedFeatureName = apply {
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

        return other is ThreadUnsupportedFeatureName && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
