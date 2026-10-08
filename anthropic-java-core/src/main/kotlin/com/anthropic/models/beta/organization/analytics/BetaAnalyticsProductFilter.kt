package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

/**
 * Publicly documented product surfaces. `claude-tag` is Claude Tag, the Claude product in Slack.
 * `chat_cowork_unified` is Chat and Cowork unified, Cowork's features inside claude.ai chat: chat
 * and Cowork usage by a member who has it turned on is reported under this value instead of `chat`
 * or `cowork`. It is accepted as a filter only on deployments that offer Chat and Cowork unified.
 */
class BetaAnalyticsProductFilter private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val CHAT = BetaAnalyticsProductFilter(JsonField.of("chat"))

        @JvmField
        val CHAT_COWORK_UNIFIED = BetaAnalyticsProductFilter(JsonField.of("chat_cowork_unified"))

        @JvmField val CLAUDE_TAG = BetaAnalyticsProductFilter(JsonField.of("claude-tag"))

        @JvmField val CLAUDE_CODE = BetaAnalyticsProductFilter(JsonField.of("claude_code"))

        @JvmField val CLAUDE_DESIGN = BetaAnalyticsProductFilter(JsonField.of("claude_design"))

        @JvmField
        val CLAUDE_IN_CHROME = BetaAnalyticsProductFilter(JsonField.of("claude_in_chrome"))

        @JvmField val COWORK = BetaAnalyticsProductFilter(JsonField.of("cowork"))

        @JvmField val OFFICE_AGENT = BetaAnalyticsProductFilter(JsonField.of("office_agent"))

        @JvmStatic
        fun of(value: String): BetaAnalyticsProductFilter =
            // Intern known values so `==` works
            when (value) {
                "chat" -> CHAT
                "chat_cowork_unified" -> CHAT_COWORK_UNIFIED
                "claude-tag" -> CLAUDE_TAG
                "claude_code" -> CLAUDE_CODE
                "claude_design" -> CLAUDE_DESIGN
                "claude_in_chrome" -> CLAUDE_IN_CHROME
                "cowork" -> COWORK
                "office_agent" -> OFFICE_AGENT
                else -> BetaAnalyticsProductFilter(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaAnalyticsProductFilter =
            value.asString().getOrNull()?.let { of(it) } ?: BetaAnalyticsProductFilter(value)
    }

    /** An enum containing [BetaAnalyticsProductFilter]'s known values. */
    enum class Known {
        CHAT,
        CHAT_COWORK_UNIFIED,
        CLAUDE_TAG,
        CLAUDE_CODE,
        CLAUDE_DESIGN,
        CLAUDE_IN_CHROME,
        COWORK,
        OFFICE_AGENT,
    }

    /**
     * An enum containing [BetaAnalyticsProductFilter]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaAnalyticsProductFilter] can contain an unknown value in a couple of
     * cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        CHAT,
        CHAT_COWORK_UNIFIED,
        CLAUDE_TAG,
        CLAUDE_CODE,
        CLAUDE_DESIGN,
        CLAUDE_IN_CHROME,
        COWORK,
        OFFICE_AGENT,
        /**
         * An enum member indicating that [BetaAnalyticsProductFilter] was instantiated with an
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
            CHAT -> Value.CHAT
            CHAT_COWORK_UNIFIED -> Value.CHAT_COWORK_UNIFIED
            CLAUDE_TAG -> Value.CLAUDE_TAG
            CLAUDE_CODE -> Value.CLAUDE_CODE
            CLAUDE_DESIGN -> Value.CLAUDE_DESIGN
            CLAUDE_IN_CHROME -> Value.CLAUDE_IN_CHROME
            COWORK -> Value.COWORK
            OFFICE_AGENT -> Value.OFFICE_AGENT
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
            CHAT -> Known.CHAT
            CHAT_COWORK_UNIFIED -> Known.CHAT_COWORK_UNIFIED
            CLAUDE_TAG -> Known.CLAUDE_TAG
            CLAUDE_CODE -> Known.CLAUDE_CODE
            CLAUDE_DESIGN -> Known.CLAUDE_DESIGN
            CLAUDE_IN_CHROME -> Known.CLAUDE_IN_CHROME
            COWORK -> Known.COWORK
            OFFICE_AGENT -> Known.OFFICE_AGENT
            else ->
                throw AnthropicInvalidDataException("Unknown BetaAnalyticsProductFilter: $value")
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
    fun validate(): BetaAnalyticsProductFilter = apply {
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

        return other is BetaAnalyticsProductFilter && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
