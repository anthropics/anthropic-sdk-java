package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * What the spans that the API exports to a telemetry destination contain.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#choose-a-content-mode)
 * to learn what the spans contain in each content mode.
 */
class BetaTelemetryDestinationContentMode
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

        /**
         * The spans contain identifiers, timing, status, token counts, and similar metadata. They
         * don't contain prompt text, completion text, or tool inputs and outputs.
         */
        @JvmField
        val METADATA_ONLY = BetaTelemetryDestinationContentMode(JsonField.of("metadata_only"))

        /**
         * The spans contain the same metadata as `metadata_only`, plus message and tool content.
         *
         * An organization with a data-handling restriction, such as under HIPAA, can't choose this
         * mode. The API exports only metadata for such an organization, even to a destination that
         * already has `content`.
         */
        @JvmField val CONTENT = BetaTelemetryDestinationContentMode(JsonField.of("content"))

        @JvmStatic
        fun of(value: String): BetaTelemetryDestinationContentMode =
            // Intern known values so `==` works
            when (value) {
                "metadata_only" -> METADATA_ONLY
                "content" -> CONTENT
                else -> BetaTelemetryDestinationContentMode(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaTelemetryDestinationContentMode =
            value.asString().getOrNull()?.let { of(it) }
                ?: BetaTelemetryDestinationContentMode(value)
    }

    /** An enum containing [BetaTelemetryDestinationContentMode]'s known values. */
    enum class Known {
        /**
         * The spans contain identifiers, timing, status, token counts, and similar metadata. They
         * don't contain prompt text, completion text, or tool inputs and outputs.
         */
        METADATA_ONLY,
        /**
         * The spans contain the same metadata as `metadata_only`, plus message and tool content.
         *
         * An organization with a data-handling restriction, such as under HIPAA, can't choose this
         * mode. The API exports only metadata for such an organization, even to a destination that
         * already has `content`.
         */
        CONTENT,
    }

    /**
     * An enum containing [BetaTelemetryDestinationContentMode]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaTelemetryDestinationContentMode] can contain an unknown value in a couple
     * of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /**
         * The spans contain identifiers, timing, status, token counts, and similar metadata. They
         * don't contain prompt text, completion text, or tool inputs and outputs.
         */
        METADATA_ONLY,
        /**
         * The spans contain the same metadata as `metadata_only`, plus message and tool content.
         *
         * An organization with a data-handling restriction, such as under HIPAA, can't choose this
         * mode. The API exports only metadata for such an organization, even to a destination that
         * already has `content`.
         */
        CONTENT,
        /**
         * An enum member indicating that [BetaTelemetryDestinationContentMode] was instantiated
         * with an unknown value.
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
            METADATA_ONLY -> Value.METADATA_ONLY
            CONTENT -> Value.CONTENT
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
            METADATA_ONLY -> Known.METADATA_ONLY
            CONTENT -> Known.CONTENT
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaTelemetryDestinationContentMode: $value"
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
    fun validate(): BetaTelemetryDestinationContentMode = apply {
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

        return other is BetaTelemetryDestinationContentMode && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
