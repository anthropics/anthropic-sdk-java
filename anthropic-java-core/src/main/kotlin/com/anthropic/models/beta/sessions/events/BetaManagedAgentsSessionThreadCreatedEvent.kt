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

/**
 * Emitted when a child thread is created. Written to the parent thread's output stream so clients
 * observing the session see child creation.
 */
class BetaManagedAgentsSessionThreadCreatedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agentName: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val sessionThreadId: JsonField<String>,
    private val type: JsonValue,
    private val workflowRunId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent_name") @ExcludeMissing agentName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("session_thread_id")
        @ExcludeMissing
        sessionThreadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
    ) : this(id, agentName, processedAt, sessionThreadId, type, workflowRunId, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Name of the callable agent the thread runs.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agentName(): String = agentName.getRequired("agent_name")

    /**
     * Timestamp when the thread was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Public `sthr_` ID of the newly created thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sessionThreadId(): String = sessionThreadId.getRequired("session_thread_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("session.thread_created")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Identifier of the workflow run that created the thread, or `null` for any other thread.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workflowRunId(): Optional<String> = workflowRunId.getOptional("workflow_run_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agentName].
     *
     * Unlike [agentName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent_name") @ExcludeMissing fun _agentName(): JsonField<String> = agentName

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

    /**
     * Returns the raw JSON value of [workflowRunId].
     *
     * Unlike [workflowRunId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workflow_run_id")
    @ExcludeMissing
    fun _workflowRunId(): JsonField<String> = workflowRunId

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
         * [BetaManagedAgentsSessionThreadCreatedEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agentName()
         * .processedAt()
         * .sessionThreadId()
         * .workflowRunId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionThreadCreatedEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agentName: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var sessionThreadId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("session.thread_created")
        private var workflowRunId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionThreadCreatedEvent: BetaManagedAgentsSessionThreadCreatedEvent
        ) = apply {
            id = betaManagedAgentsSessionThreadCreatedEvent.id
            agentName = betaManagedAgentsSessionThreadCreatedEvent.agentName
            processedAt = betaManagedAgentsSessionThreadCreatedEvent.processedAt
            sessionThreadId = betaManagedAgentsSessionThreadCreatedEvent.sessionThreadId
            type = betaManagedAgentsSessionThreadCreatedEvent.type
            workflowRunId = betaManagedAgentsSessionThreadCreatedEvent.workflowRunId
            additionalProperties =
                betaManagedAgentsSessionThreadCreatedEvent.additionalProperties.toMutableMap()
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

        /** Name of the callable agent the thread runs. */
        fun agentName(agentName: String) = agentName(JsonField.of(agentName))

        /**
         * Sets [Builder.agentName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agentName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun agentName(agentName: JsonField<String>) = apply { this.agentName = agentName }

        /** Timestamp when the thread was created. */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

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

        /** Public `sthr_` ID of the newly created thread. */
        fun sessionThreadId(sessionThreadId: String) =
            sessionThreadId(JsonField.of(sessionThreadId))

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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("session.thread_created")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Identifier of the workflow run that created the thread, or `null` for any other thread.
         */
        fun workflowRunId(workflowRunId: String?) =
            workflowRunId(JsonField.ofNullable(workflowRunId))

        /** Alias for calling [Builder.workflowRunId] with `workflowRunId.orElse(null)`. */
        fun workflowRunId(workflowRunId: Optional<String>) =
            workflowRunId(workflowRunId.getOrNull())

        /**
         * Sets [Builder.workflowRunId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workflowRunId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workflowRunId(workflowRunId: JsonField<String>) = apply {
            this.workflowRunId = workflowRunId
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
         * Returns an immutable instance of [BetaManagedAgentsSessionThreadCreatedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agentName()
         * .processedAt()
         * .sessionThreadId()
         * .workflowRunId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionThreadCreatedEvent =
            BetaManagedAgentsSessionThreadCreatedEvent(
                checkRequired("id", id),
                checkRequired("agentName", agentName),
                checkRequired("processedAt", processedAt),
                checkRequired("sessionThreadId", sessionThreadId),
                type,
                checkRequired("workflowRunId", workflowRunId),
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
    fun validate(): BetaManagedAgentsSessionThreadCreatedEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        agentName()
        processedAt()
        sessionThreadId()
        _type().let {
            if (it != JsonValue.from("session.thread_created")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workflowRunId()
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
            (if (agentName.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (if (sessionThreadId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("session.thread_created")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionThreadCreatedEvent &&
            id == other.id &&
            agentName == other.agentName &&
            processedAt == other.processedAt &&
            sessionThreadId == other.sessionThreadId &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agentName,
            processedAt,
            sessionThreadId,
            type,
            workflowRunId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionThreadCreatedEvent{id=$id, agentName=$agentName, processedAt=$processedAt, sessionThreadId=$sessionThreadId, type=$type, workflowRunId=$workflowRunId, additionalProperties=$additionalProperties}"
}
