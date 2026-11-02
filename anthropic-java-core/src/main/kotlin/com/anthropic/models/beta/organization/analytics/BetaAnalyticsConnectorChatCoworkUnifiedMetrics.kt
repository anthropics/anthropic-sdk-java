package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A connector's use in Chat and Cowork unified on one day: `chat` holds its use in chat
 * conversations and `sessions` its use in Cowork sessions.
 */
class BetaAnalyticsConnectorChatCoworkUnifiedMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val chat: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics>,
    private val sessions: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("chat")
        @ExcludeMissing
        chat: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics> = JsonMissing.of(),
        @JsonProperty("sessions")
        @ExcludeMissing
        sessions: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics> =
            JsonMissing.of(),
    ) : this(chat, sessions, mutableMapOf())

    /**
     * A connector's use in chat conversations recorded while members had Chat and Cowork unified
     * turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun chat(): BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics = chat.getRequired("chat")

    /**
     * A connector's use in Cowork sessions recorded while members had Chat and Cowork unified
     * turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sessions(): BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics =
        sessions.getRequired("sessions")

    /**
     * Returns the raw JSON value of [chat].
     *
     * Unlike [chat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("chat")
    @ExcludeMissing
    fun _chat(): JsonField<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics> = chat

    /**
     * Returns the raw JSON value of [sessions].
     *
     * Unlike [sessions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sessions")
    @ExcludeMissing
    fun _sessions(): JsonField<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics> = sessions

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
         * Returns a mutable builder for constructing an instance of
         * [BetaAnalyticsConnectorChatCoworkUnifiedMetrics].
         *
         * The following fields are required:
         * ```java
         * .chat()
         * .sessions()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsConnectorChatCoworkUnifiedMetrics]. */
    class Builder internal constructor() {

        private var chat: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics>? = null
        private var sessions: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics>? =
            null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsConnectorChatCoworkUnifiedMetrics:
                BetaAnalyticsConnectorChatCoworkUnifiedMetrics
        ) = apply {
            chat = betaAnalyticsConnectorChatCoworkUnifiedMetrics.chat
            sessions = betaAnalyticsConnectorChatCoworkUnifiedMetrics.sessions
            additionalProperties =
                betaAnalyticsConnectorChatCoworkUnifiedMetrics.additionalProperties.toMutableMap()
        }

        /**
         * A connector's use in chat conversations recorded while members had Chat and Cowork
         * unified turned on.
         */
        fun chat(chat: BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics) =
            chat(JsonField.of(chat))

        /**
         * Sets [Builder.chat] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chat] with a well-typed
         * [BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun chat(chat: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedChatMetrics>) = apply {
            this.chat = chat
        }

        /**
         * A connector's use in Cowork sessions recorded while members had Chat and Cowork unified
         * turned on.
         */
        fun sessions(sessions: BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics) =
            sessions(JsonField.of(sessions))

        /**
         * Sets [Builder.sessions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessions] with a well-typed
         * [BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics] value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun sessions(sessions: JsonField<BetaAnalyticsConnectorChatCoworkUnifiedSessionsMetrics>) =
            apply {
                this.sessions = sessions
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
         * Returns an immutable instance of [BetaAnalyticsConnectorChatCoworkUnifiedMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .chat()
         * .sessions()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsConnectorChatCoworkUnifiedMetrics =
            BetaAnalyticsConnectorChatCoworkUnifiedMetrics(
                checkRequired("chat", chat),
                checkRequired("sessions", sessions),
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaAnalyticsConnectorChatCoworkUnifiedMetrics = apply {
        if (validated) {
            return@apply
        }

        chat().validate()
        sessions().validate()
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
        (chat.asKnown().getOrNull()?.validity() ?: 0) +
            (sessions.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsConnectorChatCoworkUnifiedMetrics &&
            chat == other.chat &&
            sessions == other.sessions &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(chat, sessions, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsConnectorChatCoworkUnifiedMetrics{chat=$chat, sessions=$sessions, additionalProperties=$additionalProperties}"
}
