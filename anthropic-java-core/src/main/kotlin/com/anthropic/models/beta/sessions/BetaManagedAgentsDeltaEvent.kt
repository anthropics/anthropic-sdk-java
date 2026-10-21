package com.anthropic.models.beta.sessions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * An incremental update to an event that is still being streamed. Deltas are best-effort and may
 * stop early; when the buffered event with id == event_id is produced it carries the complete
 * content. A model request that ends early (an error or interrupt) produces no buffered event — its
 * terminal span.model_request_end closes the preview. Only sent on stream connections that opt in
 * via event_deltas; never appears in event history.
 */
class BetaManagedAgentsDeltaEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val delta: JsonField<BetaManagedAgentsDeltaContent>,
    private val eventId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("delta")
        @ExcludeMissing
        delta: JsonField<BetaManagedAgentsDeltaContent> = JsonMissing.of(),
        @JsonProperty("event_id") @ExcludeMissing eventId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(delta, eventId, type, mutableMapOf())

    /**
     * One fragment of the previewed event. The delta type is named for the previewed event's field
     * it streams into: agent.message events stream content_delta fragments, each a partial element
     * of the content array.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun delta(): BetaManagedAgentsDeltaContent = delta.getRequired("delta")

    /**
     * The id of the event being previewed. Matches event.id on the corresponding event_start and
     * the buffered event that reconciles the preview.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun eventId(): String = eventId.getRequired("event_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("event_delta")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [delta].
     *
     * Unlike [delta], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("delta")
    @ExcludeMissing
    fun _delta(): JsonField<BetaManagedAgentsDeltaContent> = delta

    /**
     * Returns the raw JSON value of [eventId].
     *
     * Unlike [eventId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("event_id") @ExcludeMissing fun _eventId(): JsonField<String> = eventId

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsDeltaEvent].
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .eventId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsDeltaEvent]. */
    class Builder internal constructor() {

        private var delta: JsonField<BetaManagedAgentsDeltaContent>? = null
        private var eventId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("event_delta")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsDeltaEvent: BetaManagedAgentsDeltaEvent) = apply {
            delta = betaManagedAgentsDeltaEvent.delta
            eventId = betaManagedAgentsDeltaEvent.eventId
            type = betaManagedAgentsDeltaEvent.type
            additionalProperties = betaManagedAgentsDeltaEvent.additionalProperties.toMutableMap()
        }

        /**
         * One fragment of the previewed event. The delta type is named for the previewed event's
         * field it streams into: agent.message events stream content_delta fragments, each a
         * partial element of the content array.
         */
        fun delta(delta: BetaManagedAgentsDeltaContent) = delta(JsonField.of(delta))

        /**
         * Sets [Builder.delta] to an arbitrary JSON value.
         *
         * You should usually call [Builder.delta] with a well-typed [BetaManagedAgentsDeltaContent]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun delta(delta: JsonField<BetaManagedAgentsDeltaContent>) = apply { this.delta = delta }

        /**
         * Alias for calling [delta] with the following:
         * ```java
         * BetaManagedAgentsDeltaContent.builder()
         *     .content(content)
         *     .build()
         * ```
         */
        fun contentDeltaDelta(content: BetaManagedAgentsTextBlock) =
            delta(BetaManagedAgentsDeltaContent.builder().content(content).build())

        /**
         * The id of the event being previewed. Matches event.id on the corresponding event_start
         * and the buffered event that reconciles the preview.
         */
        fun eventId(eventId: String) = eventId(JsonField.of(eventId))

        /**
         * Sets [Builder.eventId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.eventId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun eventId(eventId: JsonField<String>) = apply { this.eventId = eventId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("event_delta")
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
         * Returns an immutable instance of [BetaManagedAgentsDeltaEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .delta()
         * .eventId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsDeltaEvent =
            BetaManagedAgentsDeltaEvent(
                checkRequired("delta", delta),
                checkRequired("eventId", eventId),
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
    fun validate(): BetaManagedAgentsDeltaEvent = apply {
        if (validated) {
            return@apply
        }

        delta().validate()
        eventId()
        _type().let {
            if (it != JsonValue.from("event_delta")) {
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
        (delta.asKnown().getOrNull()?.validity() ?: 0) +
            (if (eventId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("event_delta")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeltaEvent &&
            delta == other.delta &&
            eventId == other.eventId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(delta, eventId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsDeltaEvent{delta=$delta, eventId=$eventId, type=$type, additionalProperties=$additionalProperties}"
}
