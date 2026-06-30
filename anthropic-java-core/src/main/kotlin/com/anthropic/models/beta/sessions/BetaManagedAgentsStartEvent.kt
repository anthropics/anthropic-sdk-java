// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.Enum
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
 * Opens a preview of a buffered event. Carries the previewed event's type and id only. Followed by
 * zero or more event_delta events with the same event id, normally concluded by the buffered event
 * carrying that id. If the producing model request ends without that event (an error or interrupt
 * mid-stream), its terminal span.model_request_end closes the preview. Only sent on stream
 * connections that opt in via event_deltas; never appears in event history.
 */
class BetaManagedAgentsStartEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val event: JsonField<BetaManagedAgentsStartEventPreview>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("event")
        @ExcludeMissing
        event: JsonField<BetaManagedAgentsStartEventPreview> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(event, type, mutableMapOf())

    /**
     * The previewed event's type and id. The event type determines which delta types the preview's
     * event_delta events carry: agent.message events stream content_delta fragments; agent.thinking
     * previews are start-only — no deltas follow, and the buffered agent.thinking with the same id
     * concludes them.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun event(): BetaManagedAgentsStartEventPreview = event.getRequired("event")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [event].
     *
     * Unlike [event], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("event")
    @ExcludeMissing
    fun _event(): JsonField<BetaManagedAgentsStartEventPreview> = event

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsStartEvent].
         *
         * The following fields are required:
         * ```java
         * .event()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsStartEvent]. */
    class Builder internal constructor() {

        private var event: JsonField<BetaManagedAgentsStartEventPreview>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsStartEvent: BetaManagedAgentsStartEvent) = apply {
            event = betaManagedAgentsStartEvent.event
            type = betaManagedAgentsStartEvent.type
            additionalProperties = betaManagedAgentsStartEvent.additionalProperties.toMutableMap()
        }

        /**
         * The previewed event's type and id. The event type determines which delta types the
         * preview's event_delta events carry: agent.message events stream content_delta fragments;
         * agent.thinking previews are start-only — no deltas follow, and the buffered
         * agent.thinking with the same id concludes them.
         */
        fun event(event: BetaManagedAgentsStartEventPreview) = event(JsonField.of(event))

        /**
         * Sets [Builder.event] to an arbitrary JSON value.
         *
         * You should usually call [Builder.event] with a well-typed
         * [BetaManagedAgentsStartEventPreview] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun event(event: JsonField<BetaManagedAgentsStartEventPreview>) = apply {
            this.event = event
        }

        /**
         * Alias for calling [event] with
         * `BetaManagedAgentsStartEventPreview.ofAgentMessage(agentMessage)`.
         */
        fun event(agentMessage: BetaManagedAgentsAgentMessagePreview) =
            event(BetaManagedAgentsStartEventPreview.ofAgentMessage(agentMessage))

        /**
         * Alias for calling [event] with the following:
         * ```java
         * BetaManagedAgentsAgentMessagePreview.builder()
         *     .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
         *     .id(id)
         *     .build()
         * ```
         */
        fun agentMessageEvent(id: String) =
            event(
                BetaManagedAgentsAgentMessagePreview.builder()
                    .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                    .id(id)
                    .build()
            )

        /**
         * Alias for calling [event] with
         * `BetaManagedAgentsStartEventPreview.ofAgentThinking(agentThinking)`.
         */
        fun event(agentThinking: BetaManagedAgentsAgentThinkingPreview) =
            event(BetaManagedAgentsStartEventPreview.ofAgentThinking(agentThinking))

        /**
         * Alias for calling [event] with the following:
         * ```java
         * BetaManagedAgentsAgentThinkingPreview.builder()
         *     .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
         *     .id(id)
         *     .build()
         * ```
         */
        fun agentThinkingEvent(id: String) =
            event(
                BetaManagedAgentsAgentThinkingPreview.builder()
                    .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                    .id(id)
                    .build()
            )

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsStartEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .event()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent(
                checkRequired("event", event),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsStartEvent = apply {
        if (validated) {
            return@apply
        }

        event().validate()
        type().validate()
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
        (event.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val EVENT_START = of("event_start")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            EVENT_START
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            EVENT_START,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                EVENT_START -> Value.EVENT_START
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
                EVENT_START -> Known.EVENT_START
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsStartEvent &&
            event == other.event &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(event, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsStartEvent{event=$event, type=$type, additionalProperties=$additionalProperties}"
}
