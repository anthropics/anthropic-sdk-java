package com.anthropic.models.beta.sessions

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
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("event")
        @ExcludeMissing
        event: JsonField<BetaManagedAgentsStartEventPreview> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
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
     * Expected to always return the following:
     * ```java
     * JsonValue.from("event_start")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [event].
     *
     * Unlike [event], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("event")
    @ExcludeMissing
    fun _event(): JsonField<BetaManagedAgentsStartEventPreview> = event

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
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsStartEvent] with the required [event]
         * set to the given value.
         */
        @JvmStatic
        fun of(event: BetaManagedAgentsStartEventPreview) = builder().event(event).build()

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsStartEventPreview.ofAgentMessage(agentMessage)`.
         */
        @JvmStatic
        fun of(agentMessage: BetaManagedAgentsAgentMessagePreview) =
            of(BetaManagedAgentsStartEventPreview.ofAgentMessage(agentMessage))

        /**
         * Alias for calling [of] with
         * `BetaManagedAgentsStartEventPreview.ofAgentThinking(agentThinking)`.
         */
        @JvmStatic
        fun of(agentThinking: BetaManagedAgentsAgentThinkingPreview) =
            of(BetaManagedAgentsStartEventPreview.ofAgentThinking(agentThinking))
    }

    /** A builder for [BetaManagedAgentsStartEvent]. */
    class Builder internal constructor() {

        private var event: JsonField<BetaManagedAgentsStartEventPreview>? = null
        private var type: JsonValue = JsonValue.from("event_start")
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
         *     .id(id)
         *     .build()
         * ```
         */
        fun agentMessageEvent(id: String) =
            event(BetaManagedAgentsAgentMessagePreview.builder().id(id).build())

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
         *     .id(id)
         *     .build()
         * ```
         */
        fun agentThinkingEvent(id: String) =
            event(BetaManagedAgentsAgentThinkingPreview.builder().id(id).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("event_start")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsStartEvent =
            BetaManagedAgentsStartEvent(
                checkRequired("event", event),
                type,
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
        _type().let {
            if (it != JsonValue.from("event_start")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
            type.let { if (it == JsonValue.from("event_start")) 1 else 0 }

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
