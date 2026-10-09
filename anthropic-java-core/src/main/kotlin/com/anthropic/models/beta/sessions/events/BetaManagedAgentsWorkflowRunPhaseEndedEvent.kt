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

/**
 * A workflow run's plan left a phase, or the run's end closed it. Emitted once for every
 * `workflow_run.phase_started` event, before the run's `workflow_run.status_ended` event. The event
 * does not say whether the plan finished the phase's work, or why it left.
 */
class BetaManagedAgentsWorkflowRunPhaseEndedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val phaseStartedId: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val workflowRunId: JsonField<String>,
    private val workflowRunPhaseId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("phase_started_id")
        @ExcludeMissing
        phaseStartedId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workflow_run_phase_id")
        @ExcludeMissing
        workflowRunPhaseId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        phaseStartedId,
        processedAt,
        type,
        workflowRunId,
        workflowRunPhaseId,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Identifier of the `workflow_run.phase_started` event that opened the phase.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun phaseStartedId(): String = phaseStartedId.getRequired("phase_started_id")

    /**
     * Timestamp when this event was processed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workflow_run.phase_ended")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Identifier of the run. The same value is on all of the run's `workflow_run.*` events.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workflowRunId(): String = workflowRunId.getRequired("workflow_run_id")

    /**
     * Identifier of the phase, as in `phases` on the run's `workflow_run.created` event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workflowRunPhaseId(): String = workflowRunPhaseId.getRequired("workflow_run_phase_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [phaseStartedId].
     *
     * Unlike [phaseStartedId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("phase_started_id")
    @ExcludeMissing
    fun _phaseStartedId(): JsonField<String> = phaseStartedId

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [workflowRunId].
     *
     * Unlike [workflowRunId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workflow_run_id")
    @ExcludeMissing
    fun _workflowRunId(): JsonField<String> = workflowRunId

    /**
     * Returns the raw JSON value of [workflowRunPhaseId].
     *
     * Unlike [workflowRunPhaseId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("workflow_run_phase_id")
    @ExcludeMissing
    fun _workflowRunPhaseId(): JsonField<String> = workflowRunPhaseId

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
         * [BetaManagedAgentsWorkflowRunPhaseEndedEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .phaseStartedId()
         * .processedAt()
         * .workflowRunId()
         * .workflowRunPhaseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWorkflowRunPhaseEndedEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var phaseStartedId: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("workflow_run.phase_ended")
        private var workflowRunId: JsonField<String>? = null
        private var workflowRunPhaseId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunPhaseEndedEvent: BetaManagedAgentsWorkflowRunPhaseEndedEvent
        ) = apply {
            id = betaManagedAgentsWorkflowRunPhaseEndedEvent.id
            phaseStartedId = betaManagedAgentsWorkflowRunPhaseEndedEvent.phaseStartedId
            processedAt = betaManagedAgentsWorkflowRunPhaseEndedEvent.processedAt
            type = betaManagedAgentsWorkflowRunPhaseEndedEvent.type
            workflowRunId = betaManagedAgentsWorkflowRunPhaseEndedEvent.workflowRunId
            workflowRunPhaseId = betaManagedAgentsWorkflowRunPhaseEndedEvent.workflowRunPhaseId
            additionalProperties =
                betaManagedAgentsWorkflowRunPhaseEndedEvent.additionalProperties.toMutableMap()
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

        /** Identifier of the `workflow_run.phase_started` event that opened the phase. */
        fun phaseStartedId(phaseStartedId: String) = phaseStartedId(JsonField.of(phaseStartedId))

        /**
         * Sets [Builder.phaseStartedId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.phaseStartedId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun phaseStartedId(phaseStartedId: JsonField<String>) = apply {
            this.phaseStartedId = phaseStartedId
        }

        /** Timestamp when this event was processed. */
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workflow_run.phase_ended")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Identifier of the run. The same value is on all of the run's `workflow_run.*` events. */
        fun workflowRunId(workflowRunId: String) = workflowRunId(JsonField.of(workflowRunId))

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

        /** Identifier of the phase, as in `phases` on the run's `workflow_run.created` event. */
        fun workflowRunPhaseId(workflowRunPhaseId: String) =
            workflowRunPhaseId(JsonField.of(workflowRunPhaseId))

        /**
         * Sets [Builder.workflowRunPhaseId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workflowRunPhaseId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun workflowRunPhaseId(workflowRunPhaseId: JsonField<String>) = apply {
            this.workflowRunPhaseId = workflowRunPhaseId
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunPhaseEndedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .phaseStartedId()
         * .processedAt()
         * .workflowRunId()
         * .workflowRunPhaseId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunPhaseEndedEvent =
            BetaManagedAgentsWorkflowRunPhaseEndedEvent(
                checkRequired("id", id),
                checkRequired("phaseStartedId", phaseStartedId),
                checkRequired("processedAt", processedAt),
                type,
                checkRequired("workflowRunId", workflowRunId),
                checkRequired("workflowRunPhaseId", workflowRunPhaseId),
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
    fun validate(): BetaManagedAgentsWorkflowRunPhaseEndedEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        phaseStartedId()
        processedAt()
        _type().let {
            if (it != JsonValue.from("workflow_run.phase_ended")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workflowRunId()
        workflowRunPhaseId()
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
            (if (phaseStartedId.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("workflow_run.phase_ended")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0) +
            (if (workflowRunPhaseId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunPhaseEndedEvent &&
            id == other.id &&
            phaseStartedId == other.phaseStartedId &&
            processedAt == other.processedAt &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            workflowRunPhaseId == other.workflowRunPhaseId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            phaseStartedId,
            processedAt,
            type,
            workflowRunId,
            workflowRunPhaseId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunPhaseEndedEvent{id=$id, phaseStartedId=$phaseStartedId, processedAt=$processedAt, type=$type, workflowRunId=$workflowRunId, workflowRunPhaseId=$workflowRunPhaseId, additionalProperties=$additionalProperties}"
}
