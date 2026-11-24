package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BetaComplianceReviewPolicyCategory private constructor(private val value: JsonField<String>) :
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

        @JvmField val CYBER = BetaComplianceReviewPolicyCategory(JsonField.of("cyber"))

        @JvmField val BIO = BetaComplianceReviewPolicyCategory(JsonField.of("bio"))

        @JvmField val CHEM = BetaComplianceReviewPolicyCategory(JsonField.of("chem"))

        @JvmField val RADNUC = BetaComplianceReviewPolicyCategory(JsonField.of("radnuc"))

        @JvmField val CGW = BetaComplianceReviewPolicyCategory(JsonField.of("cgw"))

        @JvmField val MGW = BetaComplianceReviewPolicyCategory(JsonField.of("mgw"))

        @JvmField val IO = BetaComplianceReviewPolicyCategory(JsonField.of("io"))

        @JvmField val FRAUD = BetaComplianceReviewPolicyCategory(JsonField.of("fraud"))

        @JvmField
        val VIOLENT_EXTREMISM =
            BetaComplianceReviewPolicyCategory(JsonField.of("violent_extremism"))

        @JvmField
        val SURVEILLANCE = BetaComplianceReviewPolicyCategory(JsonField.of("surveillance"))

        @JvmField
        val HUMAN_EXPLOITATION =
            BetaComplianceReviewPolicyCategory(JsonField.of("human_exploitation"))

        @JvmField val SELF_HARM = BetaComplianceReviewPolicyCategory(JsonField.of("self_harm"))

        @JvmField
        val ADULT_CONTENT = BetaComplianceReviewPolicyCategory(JsonField.of("adult_content"))

        @JvmField
        val REASONING_EXTRACTION =
            BetaComplianceReviewPolicyCategory(JsonField.of("reasoning_extraction"))

        @JvmField
        val FRONTIER_LLM = BetaComplianceReviewPolicyCategory(JsonField.of("frontier_llm"))

        @JvmField
        val COMPROMISED_KEY = BetaComplianceReviewPolicyCategory(JsonField.of("compromised_key"))

        @JvmField val BENIGN = BetaComplianceReviewPolicyCategory(JsonField.of("benign"))

        @JvmField val OTHER = BetaComplianceReviewPolicyCategory(JsonField.of("other"))

        @JvmField
        val UNDETERMINED = BetaComplianceReviewPolicyCategory(JsonField.of("undetermined"))

        @JvmStatic
        fun of(value: String): BetaComplianceReviewPolicyCategory =
            // Intern known values so `==` works
            when (value) {
                "cyber" -> CYBER
                "bio" -> BIO
                "chem" -> CHEM
                "radnuc" -> RADNUC
                "cgw" -> CGW
                "mgw" -> MGW
                "io" -> IO
                "fraud" -> FRAUD
                "violent_extremism" -> VIOLENT_EXTREMISM
                "surveillance" -> SURVEILLANCE
                "human_exploitation" -> HUMAN_EXPLOITATION
                "self_harm" -> SELF_HARM
                "adult_content" -> ADULT_CONTENT
                "reasoning_extraction" -> REASONING_EXTRACTION
                "frontier_llm" -> FRONTIER_LLM
                "compromised_key" -> COMPROMISED_KEY
                "benign" -> BENIGN
                "other" -> OTHER
                "undetermined" -> UNDETERMINED
                else -> BetaComplianceReviewPolicyCategory(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaComplianceReviewPolicyCategory =
            value.asString().getOrNull()?.let { of(it) }
                ?: BetaComplianceReviewPolicyCategory(value)
    }

    /** An enum containing [BetaComplianceReviewPolicyCategory]'s known values. */
    enum class Known {
        CYBER,
        BIO,
        CHEM,
        RADNUC,
        CGW,
        MGW,
        IO,
        FRAUD,
        VIOLENT_EXTREMISM,
        SURVEILLANCE,
        HUMAN_EXPLOITATION,
        SELF_HARM,
        ADULT_CONTENT,
        REASONING_EXTRACTION,
        FRONTIER_LLM,
        COMPROMISED_KEY,
        BENIGN,
        OTHER,
        UNDETERMINED,
    }

    /**
     * An enum containing [BetaComplianceReviewPolicyCategory]'s known values, as well as an
     * [_UNKNOWN] member.
     *
     * An instance of [BetaComplianceReviewPolicyCategory] can contain an unknown value in a couple
     * of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        CYBER,
        BIO,
        CHEM,
        RADNUC,
        CGW,
        MGW,
        IO,
        FRAUD,
        VIOLENT_EXTREMISM,
        SURVEILLANCE,
        HUMAN_EXPLOITATION,
        SELF_HARM,
        ADULT_CONTENT,
        REASONING_EXTRACTION,
        FRONTIER_LLM,
        COMPROMISED_KEY,
        BENIGN,
        OTHER,
        UNDETERMINED,
        /**
         * An enum member indicating that [BetaComplianceReviewPolicyCategory] was instantiated with
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
            CYBER -> Value.CYBER
            BIO -> Value.BIO
            CHEM -> Value.CHEM
            RADNUC -> Value.RADNUC
            CGW -> Value.CGW
            MGW -> Value.MGW
            IO -> Value.IO
            FRAUD -> Value.FRAUD
            VIOLENT_EXTREMISM -> Value.VIOLENT_EXTREMISM
            SURVEILLANCE -> Value.SURVEILLANCE
            HUMAN_EXPLOITATION -> Value.HUMAN_EXPLOITATION
            SELF_HARM -> Value.SELF_HARM
            ADULT_CONTENT -> Value.ADULT_CONTENT
            REASONING_EXTRACTION -> Value.REASONING_EXTRACTION
            FRONTIER_LLM -> Value.FRONTIER_LLM
            COMPROMISED_KEY -> Value.COMPROMISED_KEY
            BENIGN -> Value.BENIGN
            OTHER -> Value.OTHER
            UNDETERMINED -> Value.UNDETERMINED
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
            CYBER -> Known.CYBER
            BIO -> Known.BIO
            CHEM -> Known.CHEM
            RADNUC -> Known.RADNUC
            CGW -> Known.CGW
            MGW -> Known.MGW
            IO -> Known.IO
            FRAUD -> Known.FRAUD
            VIOLENT_EXTREMISM -> Known.VIOLENT_EXTREMISM
            SURVEILLANCE -> Known.SURVEILLANCE
            HUMAN_EXPLOITATION -> Known.HUMAN_EXPLOITATION
            SELF_HARM -> Known.SELF_HARM
            ADULT_CONTENT -> Known.ADULT_CONTENT
            REASONING_EXTRACTION -> Known.REASONING_EXTRACTION
            FRONTIER_LLM -> Known.FRONTIER_LLM
            COMPROMISED_KEY -> Known.COMPROMISED_KEY
            BENIGN -> Known.BENIGN
            OTHER -> Known.OTHER
            UNDETERMINED -> Known.UNDETERMINED
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewPolicyCategory: $value"
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
    fun validate(): BetaComplianceReviewPolicyCategory = apply {
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

        return other is BetaComplianceReviewPolicyCategory && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
