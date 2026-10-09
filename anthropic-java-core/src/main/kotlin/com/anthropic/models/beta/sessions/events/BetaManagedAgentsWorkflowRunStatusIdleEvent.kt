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
 * A workflow run is idle. Emitted each time the run goes idle, whatever the cause. If the run ends
 * while idle, no `workflow_run.status_running` comes between this event and its
 * `workflow_run.status_ended`.
 */
class BetaManagedAgentsWorkflowRunStatusIdleEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val workflowRunId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
    ) : this(id, processedAt, type, workflowRunId, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

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
     * JsonValue.from("workflow_run.status_idle")
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
         * [BetaManagedAgentsWorkflowRunStatusIdleEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .workflowRunId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWorkflowRunStatusIdleEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("workflow_run.status_idle")
        private var workflowRunId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunStatusIdleEvent: BetaManagedAgentsWorkflowRunStatusIdleEvent
        ) = apply {
            id = betaManagedAgentsWorkflowRunStatusIdleEvent.id
            processedAt = betaManagedAgentsWorkflowRunStatusIdleEvent.processedAt
            type = betaManagedAgentsWorkflowRunStatusIdleEvent.type
            workflowRunId = betaManagedAgentsWorkflowRunStatusIdleEvent.workflowRunId
            additionalProperties =
                betaManagedAgentsWorkflowRunStatusIdleEvent.additionalProperties.toMutableMap()
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
         * JsonValue.from("workflow_run.status_idle")
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunStatusIdleEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .workflowRunId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunStatusIdleEvent =
            BetaManagedAgentsWorkflowRunStatusIdleEvent(
                checkRequired("id", id),
                checkRequired("processedAt", processedAt),
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
    fun validate(): BetaManagedAgentsWorkflowRunStatusIdleEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        processedAt()
        _type().let {
            if (it != JsonValue.from("workflow_run.status_idle")) {
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
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("workflow_run.status_idle")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunStatusIdleEvent &&
            id == other.id &&
            processedAt == other.processedAt &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, processedAt, type, workflowRunId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunStatusIdleEvent{id=$id, processedAt=$processedAt, type=$type, workflowRunId=$workflowRunId, additionalProperties=$additionalProperties}"
}
