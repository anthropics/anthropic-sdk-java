package com.anthropic.models.beta.organization.analytics.usage

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsUsageGroupBy private constructor(private val value: JsonField<String>) : Enum {

    /**
     * Returns this class instance's raw value.
     *
     * This is usually only useful if this instance was deserialized from data that doesn't match
     * any known member, and you want to know that value. For example, if the SDK is on an older
     * version than the API, then the API may respond with new members that the SDK is unaware of.
     */
    @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

    companion object {

        @JvmField val ACTOR = BetaAnalyticsUsageGroupBy(JsonField.of("actor"))

        @JvmField val ACTOR_TYPE = BetaAnalyticsUsageGroupBy(JsonField.of("actor_type"))

        @JvmField val AGENT_ID = BetaAnalyticsUsageGroupBy(JsonField.of("agent_id"))

        @JvmField val API_KEY_ID = BetaAnalyticsUsageGroupBy(JsonField.of("api_key_id"))

        @JvmField
        val CLAUDE_TAG_CATEGORY = BetaAnalyticsUsageGroupBy(JsonField.of("claude_tag_category"))

        @JvmField
        val CLAUDE_TAG_USER_ID = BetaAnalyticsUsageGroupBy(JsonField.of("claude_tag_user_id"))

        @JvmField val CONTEXT_WINDOW = BetaAnalyticsUsageGroupBy(JsonField.of("context_window"))

        @JvmField val DEPLOYMENT_ID = BetaAnalyticsUsageGroupBy(JsonField.of("deployment_id"))

        @JvmField val ENVIRONMENT_ID = BetaAnalyticsUsageGroupBy(JsonField.of("environment_id"))

        @JvmField val INFERENCE_GEO = BetaAnalyticsUsageGroupBy(JsonField.of("inference_geo"))

        @JvmField val MODEL = BetaAnalyticsUsageGroupBy(JsonField.of("model"))

        @JvmField val PRODUCT = BetaAnalyticsUsageGroupBy(JsonField.of("product"))

        @JvmField val RBAC_GROUP_ID = BetaAnalyticsUsageGroupBy(JsonField.of("rbac_group_id"))

        @JvmField val SERVICE_TIER = BetaAnalyticsUsageGroupBy(JsonField.of("service_tier"))

        @JvmField val SESSION_ID = BetaAnalyticsUsageGroupBy(JsonField.of("session_id"))

        @JvmField val SLACK_CHANNEL_ID = BetaAnalyticsUsageGroupBy(JsonField.of("slack_channel_id"))

        @JvmField val SPEED = BetaAnalyticsUsageGroupBy(JsonField.of("speed"))

        @JvmField val USER_PROFILE_ID = BetaAnalyticsUsageGroupBy(JsonField.of("user_profile_id"))

        @JvmField val WORKSPACE_ID = BetaAnalyticsUsageGroupBy(JsonField.of("workspace_id"))

        @JvmStatic
        fun of(value: String): BetaAnalyticsUsageGroupBy =
            // Intern known values so `==` works
            when (value) {
                "actor" -> ACTOR
                "actor_type" -> ACTOR_TYPE
                "agent_id" -> AGENT_ID
                "api_key_id" -> API_KEY_ID
                "claude_tag_category" -> CLAUDE_TAG_CATEGORY
                "claude_tag_user_id" -> CLAUDE_TAG_USER_ID
                "context_window" -> CONTEXT_WINDOW
                "deployment_id" -> DEPLOYMENT_ID
                "environment_id" -> ENVIRONMENT_ID
                "inference_geo" -> INFERENCE_GEO
                "model" -> MODEL
                "product" -> PRODUCT
                "rbac_group_id" -> RBAC_GROUP_ID
                "service_tier" -> SERVICE_TIER
                "session_id" -> SESSION_ID
                "slack_channel_id" -> SLACK_CHANNEL_ID
                "speed" -> SPEED
                "user_profile_id" -> USER_PROFILE_ID
                "workspace_id" -> WORKSPACE_ID
                else -> BetaAnalyticsUsageGroupBy(JsonField.of(value))
            }

        @JsonCreator
        @JvmStatic
        fun of(value: JsonField<String>): BetaAnalyticsUsageGroupBy =
            value.asString().getOrNull()?.let { of(it) } ?: BetaAnalyticsUsageGroupBy(value)
    }

    /** An enum containing [BetaAnalyticsUsageGroupBy]'s known values. */
    enum class Known {
        ACTOR,
        ACTOR_TYPE,
        AGENT_ID,
        API_KEY_ID,
        CLAUDE_TAG_CATEGORY,
        CLAUDE_TAG_USER_ID,
        CONTEXT_WINDOW,
        DEPLOYMENT_ID,
        ENVIRONMENT_ID,
        INFERENCE_GEO,
        MODEL,
        PRODUCT,
        RBAC_GROUP_ID,
        SERVICE_TIER,
        SESSION_ID,
        SLACK_CHANNEL_ID,
        SPEED,
        USER_PROFILE_ID,
        WORKSPACE_ID,
    }

    /**
     * An enum containing [BetaAnalyticsUsageGroupBy]'s known values, as well as an [_UNKNOWN]
     * member.
     *
     * An instance of [BetaAnalyticsUsageGroupBy] can contain an unknown value in a couple of cases:
     * - It was deserialized from data that doesn't match any known member. For example, if the SDK
     *   is on an older version than the API, then the API may respond with new members that the SDK
     *   is unaware of.
     * - It was constructed with an arbitrary value using the [of] method.
     */
    enum class Value {
        ACTOR,
        ACTOR_TYPE,
        AGENT_ID,
        API_KEY_ID,
        CLAUDE_TAG_CATEGORY,
        CLAUDE_TAG_USER_ID,
        CONTEXT_WINDOW,
        DEPLOYMENT_ID,
        ENVIRONMENT_ID,
        INFERENCE_GEO,
        MODEL,
        PRODUCT,
        RBAC_GROUP_ID,
        SERVICE_TIER,
        SESSION_ID,
        SLACK_CHANNEL_ID,
        SPEED,
        USER_PROFILE_ID,
        WORKSPACE_ID,
        /**
         * An enum member indicating that [BetaAnalyticsUsageGroupBy] was instantiated with an
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
            ACTOR -> Value.ACTOR
            ACTOR_TYPE -> Value.ACTOR_TYPE
            AGENT_ID -> Value.AGENT_ID
            API_KEY_ID -> Value.API_KEY_ID
            CLAUDE_TAG_CATEGORY -> Value.CLAUDE_TAG_CATEGORY
            CLAUDE_TAG_USER_ID -> Value.CLAUDE_TAG_USER_ID
            CONTEXT_WINDOW -> Value.CONTEXT_WINDOW
            DEPLOYMENT_ID -> Value.DEPLOYMENT_ID
            ENVIRONMENT_ID -> Value.ENVIRONMENT_ID
            INFERENCE_GEO -> Value.INFERENCE_GEO
            MODEL -> Value.MODEL
            PRODUCT -> Value.PRODUCT
            RBAC_GROUP_ID -> Value.RBAC_GROUP_ID
            SERVICE_TIER -> Value.SERVICE_TIER
            SESSION_ID -> Value.SESSION_ID
            SLACK_CHANNEL_ID -> Value.SLACK_CHANNEL_ID
            SPEED -> Value.SPEED
            USER_PROFILE_ID -> Value.USER_PROFILE_ID
            WORKSPACE_ID -> Value.WORKSPACE_ID
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
            ACTOR -> Known.ACTOR
            ACTOR_TYPE -> Known.ACTOR_TYPE
            AGENT_ID -> Known.AGENT_ID
            API_KEY_ID -> Known.API_KEY_ID
            CLAUDE_TAG_CATEGORY -> Known.CLAUDE_TAG_CATEGORY
            CLAUDE_TAG_USER_ID -> Known.CLAUDE_TAG_USER_ID
            CONTEXT_WINDOW -> Known.CONTEXT_WINDOW
            DEPLOYMENT_ID -> Known.DEPLOYMENT_ID
            ENVIRONMENT_ID -> Known.ENVIRONMENT_ID
            INFERENCE_GEO -> Known.INFERENCE_GEO
            MODEL -> Known.MODEL
            PRODUCT -> Known.PRODUCT
            RBAC_GROUP_ID -> Known.RBAC_GROUP_ID
            SERVICE_TIER -> Known.SERVICE_TIER
            SESSION_ID -> Known.SESSION_ID
            SLACK_CHANNEL_ID -> Known.SLACK_CHANNEL_ID
            SPEED -> Known.SPEED
            USER_PROFILE_ID -> Known.USER_PROFILE_ID
            WORKSPACE_ID -> Known.WORKSPACE_ID
            else -> throw AnthropicInvalidDataException("Unknown BetaAnalyticsUsageGroupBy: $value")
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
    fun validate(): BetaAnalyticsUsageGroupBy = apply {
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

        return other is BetaAnalyticsUsageGroupBy && value == other.value
    }

    override fun hashCode() = value.hashCode()

    override fun toString() = value.toString()
}
