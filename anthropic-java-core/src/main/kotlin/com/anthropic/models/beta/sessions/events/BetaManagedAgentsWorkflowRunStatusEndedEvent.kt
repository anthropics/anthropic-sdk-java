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
import kotlin.jvm.optionals.getOrNull

/** A workflow run ended. Emitted once per run, as the last of the run's `workflow_run.*` events. */
class BetaManagedAgentsWorkflowRunStatusEndedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val result: JsonField<BetaManagedAgentsWorkflowRunResult>,
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
        @JsonProperty("result")
        @ExcludeMissing
        result: JsonField<BetaManagedAgentsWorkflowRunResult> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
    ) : this(id, processedAt, result, type, workflowRunId, mutableMapOf())

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
     * How the run ended.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): BetaManagedAgentsWorkflowRunResult = result.getRequired("result")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workflow_run.status_ended")
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
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result")
    @ExcludeMissing
    fun _result(): JsonField<BetaManagedAgentsWorkflowRunResult> = result

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
         * [BetaManagedAgentsWorkflowRunStatusEndedEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .result()
         * .workflowRunId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWorkflowRunStatusEndedEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var result: JsonField<BetaManagedAgentsWorkflowRunResult>? = null
        private var type: JsonValue = JsonValue.from("workflow_run.status_ended")
        private var workflowRunId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunStatusEndedEvent:
                BetaManagedAgentsWorkflowRunStatusEndedEvent
        ) = apply {
            id = betaManagedAgentsWorkflowRunStatusEndedEvent.id
            processedAt = betaManagedAgentsWorkflowRunStatusEndedEvent.processedAt
            result = betaManagedAgentsWorkflowRunStatusEndedEvent.result
            type = betaManagedAgentsWorkflowRunStatusEndedEvent.type
            workflowRunId = betaManagedAgentsWorkflowRunStatusEndedEvent.workflowRunId
            additionalProperties =
                betaManagedAgentsWorkflowRunStatusEndedEvent.additionalProperties.toMutableMap()
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

        /** How the run ended. */
        fun result(result: BetaManagedAgentsWorkflowRunResult) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed
         * [BetaManagedAgentsWorkflowRunResult] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun result(result: JsonField<BetaManagedAgentsWorkflowRunResult>) = apply {
            this.result = result
        }

        /**
         * Alias for calling [result] with
         * `BetaManagedAgentsWorkflowRunResult.ofCompleted(completed)`.
         */
        fun result(completed: BetaManagedAgentsWorkflowRunResultCompleted) =
            result(BetaManagedAgentsWorkflowRunResult.ofCompleted(completed))

        /** Alias for calling [result] with `BetaManagedAgentsWorkflowRunResult.ofError(error)`. */
        fun result(error: BetaManagedAgentsWorkflowRunResultError) =
            result(BetaManagedAgentsWorkflowRunResult.ofError(error))

        /**
         * Alias for calling [result] with the following:
         * ```java
         * BetaManagedAgentsWorkflowRunResultError.builder()
         *     .error(error)
         *     .build()
         * ```
         */
        fun errorResult(error: BetaManagedAgentsWorkflowRunError) =
            result(BetaManagedAgentsWorkflowRunResultError.builder().error(error).build())

        /**
         * Alias for calling [errorResult] with
         * `BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)`.
         */
        fun errorResult(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            errorResult(BetaManagedAgentsWorkflowRunError.ofTimeout(timeout))

        /**
         * Alias for calling [errorResult] with the following:
         * ```java
         * BetaManagedAgentsTimeoutWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun timeoutErrorResult(message: String) =
            errorResult(BetaManagedAgentsTimeoutWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [errorResult] with
         * `BetaManagedAgentsWorkflowRunError.ofProgram(program)`.
         */
        fun errorResult(program: BetaManagedAgentsProgramWorkflowRunError) =
            errorResult(BetaManagedAgentsWorkflowRunError.ofProgram(program))

        /**
         * Alias for calling [errorResult] with the following:
         * ```java
         * BetaManagedAgentsProgramWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun programErrorResult(message: String) =
            errorResult(BetaManagedAgentsProgramWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [errorResult] with
         * `BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)`.
         */
        fun errorResult(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            errorResult(BetaManagedAgentsWorkflowRunError.ofUnknown(unknown))

        /**
         * Alias for calling [errorResult] with the following:
         * ```java
         * BetaManagedAgentsUnknownWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun unknownErrorResult(message: String) =
            errorResult(BetaManagedAgentsUnknownWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [errorResult] with
         * `BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)`.
         */
        fun errorResult(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            errorResult(BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit))

        /**
         * Alias for calling [errorResult] with the following:
         * ```java
         * BetaManagedAgentsThreadLimitWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun threadLimitErrorResult(message: String) =
            errorResult(
                BetaManagedAgentsThreadLimitWorkflowRunError.builder().message(message).build()
            )

        /**
         * Alias for calling [errorResult] with
         * `BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)`.
         */
        fun errorResult(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            errorResult(BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns))

        /**
         * Alias for calling [errorResult] with the following:
         * ```java
         * BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun maxWorkflowRunsErrorResult(message: String) =
            errorResult(
                BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder().message(message).build()
            )

        /**
         * Alias for calling [result] with `BetaManagedAgentsWorkflowRunResult.ofStopped(stopped)`.
         */
        fun result(stopped: BetaManagedAgentsWorkflowRunResultStopped) =
            result(BetaManagedAgentsWorkflowRunResult.ofStopped(stopped))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workflow_run.status_ended")
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunStatusEndedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .processedAt()
         * .result()
         * .workflowRunId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunStatusEndedEvent =
            BetaManagedAgentsWorkflowRunStatusEndedEvent(
                checkRequired("id", id),
                checkRequired("processedAt", processedAt),
                checkRequired("result", result),
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
    fun validate(): BetaManagedAgentsWorkflowRunStatusEndedEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        processedAt()
        result().validate()
        _type().let {
            if (it != JsonValue.from("workflow_run.status_ended")) {
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
            (result.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("workflow_run.status_ended")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunStatusEndedEvent &&
            id == other.id &&
            processedAt == other.processedAt &&
            result == other.result &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, processedAt, result, type, workflowRunId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunStatusEndedEvent{id=$id, processedAt=$processedAt, result=$result, type=$type, workflowRunId=$workflowRunId, additionalProperties=$additionalProperties}"
}
