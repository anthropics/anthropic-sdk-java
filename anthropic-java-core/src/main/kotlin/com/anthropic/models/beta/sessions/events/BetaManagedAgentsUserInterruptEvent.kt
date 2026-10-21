package com.anthropic.models.beta.sessions.events

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** An interrupt event that pauses agent execution and returns control to the user. */
class BetaManagedAgentsUserInterruptEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val type: JsonValue,
    private val processedAt: JsonField<OffsetDateTime>,
    private val sessionThreadId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("session_thread_id")
        @ExcludeMissing
        sessionThreadId: JsonField<String> = JsonMissing.of(),
    ) : this(id, type, processedAt, sessionThreadId, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("user.interrupt")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Timestamp when the interrupt was processed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun processedAt(): Optional<OffsetDateTime> = processedAt.getOptional("processed_at")

    /**
     * If absent, interrupts every non-archived thread in a multiagent session (or the primary alone
     * in a single-agent session). If present, interrupts only the named thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionThreadId(): Optional<String> = sessionThreadId.getOptional("session_thread_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [sessionThreadId].
     *
     * Unlike [sessionThreadId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_thread_id")
    @ExcludeMissing
    fun _sessionThreadId(): JsonField<String> = sessionThreadId

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
         * [BetaManagedAgentsUserInterruptEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsUserInterruptEvent] with the required
         * [id] set to the given value.
         */
        @JvmStatic fun of(id: String) = builder().id(id).build()
    }

    /** A builder for [BetaManagedAgentsUserInterruptEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("user.interrupt")
        private var processedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var sessionThreadId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsUserInterruptEvent: BetaManagedAgentsUserInterruptEvent
        ) = apply {
            id = betaManagedAgentsUserInterruptEvent.id
            type = betaManagedAgentsUserInterruptEvent.type
            processedAt = betaManagedAgentsUserInterruptEvent.processedAt
            sessionThreadId = betaManagedAgentsUserInterruptEvent.sessionThreadId
            additionalProperties =
                betaManagedAgentsUserInterruptEvent.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("user.interrupt")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Timestamp when the interrupt was processed. */
        fun processedAt(processedAt: OffsetDateTime?) =
            processedAt(JsonField.ofNullable(processedAt))

        /** Alias for calling [Builder.processedAt] with `processedAt.orElse(null)`. */
        fun processedAt(processedAt: Optional<OffsetDateTime>) =
            processedAt(processedAt.getOrNull())

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

        /**
         * If absent, interrupts every non-archived thread in a multiagent session (or the primary
         * alone in a single-agent session). If present, interrupts only the named thread.
         */
        fun sessionThreadId(sessionThreadId: String?) =
            sessionThreadId(JsonField.ofNullable(sessionThreadId))

        /** Alias for calling [Builder.sessionThreadId] with `sessionThreadId.orElse(null)`. */
        fun sessionThreadId(sessionThreadId: Optional<String>) =
            sessionThreadId(sessionThreadId.getOrNull())

        /**
         * Sets [Builder.sessionThreadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionThreadId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun sessionThreadId(sessionThreadId: JsonField<String>) = apply {
            this.sessionThreadId = sessionThreadId
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
         * Returns an immutable instance of [BetaManagedAgentsUserInterruptEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsUserInterruptEvent =
            BetaManagedAgentsUserInterruptEvent(
                checkRequired("id", id),
                type,
                processedAt,
                sessionThreadId,
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
    fun validate(): BetaManagedAgentsUserInterruptEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        _type().let {
            if (it != JsonValue.from("user.interrupt")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        processedAt()
        sessionThreadId()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("user.interrupt")) 1 else 0 } +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (if (sessionThreadId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsUserInterruptEvent &&
            id == other.id &&
            type == other.type &&
            processedAt == other.processedAt &&
            sessionThreadId == other.sessionThreadId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, type, processedAt, sessionThreadId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsUserInterruptEvent{id=$id, type=$type, processedAt=$processedAt, sessionThreadId=$sessionThreadId, additionalProperties=$additionalProperties}"
}
