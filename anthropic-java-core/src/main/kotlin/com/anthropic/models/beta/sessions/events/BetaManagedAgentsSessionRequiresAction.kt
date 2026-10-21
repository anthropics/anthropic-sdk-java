package com.anthropic.models.beta.sessions.events

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * The agent is idle waiting on one or more blocking user-input events (tool confirmation, custom
 * tool result, etc.). Resolving all of them transitions the session back to running.
 */
class BetaManagedAgentsSessionRequiresAction
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val eventIds: JsonField<List<String>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("event_ids")
        @ExcludeMissing
        eventIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(eventIds, type, mutableMapOf())

    /**
     * The ids of events the agent is blocked on. Resolving fewer than all re-emits
     * `session.status_idle` with the remainder.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun eventIds(): List<String> = eventIds.getRequired("event_ids")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("requires_action")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [eventIds].
     *
     * Unlike [eventIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("event_ids") @ExcludeMissing fun _eventIds(): JsonField<List<String>> = eventIds

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
         * [BetaManagedAgentsSessionRequiresAction].
         *
         * The following fields are required:
         * ```java
         * .eventIds()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsSessionRequiresAction] with the
         * required [eventIds] set to the given value.
         */
        @JvmStatic fun of(eventIds: List<String>) = builder().eventIds(eventIds).build()
    }

    /** A builder for [BetaManagedAgentsSessionRequiresAction]. */
    class Builder internal constructor() {

        private var eventIds: JsonField<MutableList<String>>? = null
        private var type: JsonValue = JsonValue.from("requires_action")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionRequiresAction: BetaManagedAgentsSessionRequiresAction
        ) = apply {
            eventIds =
                betaManagedAgentsSessionRequiresAction.eventIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsSessionRequiresAction.type
            additionalProperties =
                betaManagedAgentsSessionRequiresAction.additionalProperties.toMutableMap()
        }

        /**
         * The ids of events the agent is blocked on. Resolving fewer than all re-emits
         * `session.status_idle` with the remainder.
         */
        fun eventIds(eventIds: List<String>) = eventIds(JsonField.of(eventIds))

        /**
         * Sets [Builder.eventIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.eventIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun eventIds(eventIds: JsonField<List<String>>) = apply {
            this.eventIds = eventIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [eventIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEventId(eventId: String) = apply {
            eventIds =
                (eventIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("eventIds", it).add(eventId)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("requires_action")
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
         * Returns an immutable instance of [BetaManagedAgentsSessionRequiresAction].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .eventIds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionRequiresAction =
            BetaManagedAgentsSessionRequiresAction(
                checkRequired("eventIds", eventIds).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsSessionRequiresAction = apply {
        if (validated) {
            return@apply
        }

        eventIds()
        _type().let {
            if (it != JsonValue.from("requires_action")) {
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
        (eventIds.asKnown().getOrNull()?.size ?: 0) +
            type.let { if (it == JsonValue.from("requires_action")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionRequiresAction &&
            eventIds == other.eventIds &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(eventIds, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionRequiresAction{eventIds=$eventIds, type=$type, additionalProperties=$additionalProperties}"
}
