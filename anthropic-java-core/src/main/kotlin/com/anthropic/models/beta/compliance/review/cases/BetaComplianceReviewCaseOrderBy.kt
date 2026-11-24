package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/** The field that a list of review cases is sorted by: `created_at` or `updated_at`. */
class BetaComplianceReviewCaseOrderBy private constructor(private val value: JsonField<String>) :
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

        /** Sort by when Anthropic sent the case. */
        @JvmField val CREATED_AT = BetaComplianceReviewCaseOrderBy(JsonField.of("created_at"))

        /** Sort by when the case last changed. */
        @JvmField val UPDATED_AT = BetaComplianceReviewCaseOrderBy(JsonField.of("updated_at"))

        @JvmStatic
        fun of(value: String): BetaComplianceReviewCaseOrderBy =
            // Intern known values so `==` works
            when (value) {
                "created_at" -> CREATED_AT
                "updated_at" -> UPDATED_AT
                else -> BetaComplianceReviewCaseOrderBy(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaComplianceReviewCaseOrderBy =
            value.asString().getOrNull()?.let { of(it) } ?: BetaComplianceReviewCaseOrderBy(value)
    }

    /** An enum containing [BetaComplianceReviewCaseOrderBy]'s known values. */
    enum class Known {
        /** Sort by when Anthropic sent the case. */
        CREATED_AT,
        /** Sort by when the case last changed. */
        UPDATED_AT,
    }

    /**
     * An enum containing [BetaComplianceReviewCaseOrderBy]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaComplianceReviewCaseOrderBy] can contain an unknown value in a couple of
     * cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        /** Sort by when Anthropic sent the case. */
        CREATED_AT,
        /** Sort by when the case last changed. */
        UPDATED_AT,
        /**
         * An enum member indicating that [BetaComplianceReviewCaseOrderBy] was instantiated with an
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
            CREATED_AT -> Value.CREATED_AT
            UPDATED_AT -> Value.UPDATED_AT
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
            CREATED_AT -> Known.CREATED_AT
            UPDATED_AT -> Known.UPDATED_AT
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewCaseOrderBy: $value"
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
    fun validate(): BetaComplianceReviewCaseOrderBy = apply {
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

        return other is BetaComplianceReviewCaseOrderBy && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
