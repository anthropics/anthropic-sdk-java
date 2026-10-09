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
 * A workflow run met an error, or an error kept a run from being created. A run that ends with a
 * `result.type` of `error` emits this event before its `workflow_run.status_ended`, with the same
 * `error`.
 */
class BetaManagedAgentsWorkflowRunErrorEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val error: JsonField<BetaManagedAgentsWorkflowRunError>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val workflowRunId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error")
        @ExcludeMissing
        error: JsonField<BetaManagedAgentsWorkflowRunError> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
    ) : this(id, error, processedAt, type, workflowRunId, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Why the run did not finish, or was not created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): BetaManagedAgentsWorkflowRunError = error.getRequired("error")

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
     * JsonValue.from("workflow_run.error")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Identifier of the run that met the error, or `null` when the error kept a run from being
     * created.
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
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error")
    @ExcludeMissing
    fun _error(): JsonField<BetaManagedAgentsWorkflowRunError> = error

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
         * [BetaManagedAgentsWorkflowRunErrorEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .error()
         * .processedAt()
         * .workflowRunId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWorkflowRunErrorEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var error: JsonField<BetaManagedAgentsWorkflowRunError>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("workflow_run.error")
        private var workflowRunId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunErrorEvent: BetaManagedAgentsWorkflowRunErrorEvent
        ) = apply {
            id = betaManagedAgentsWorkflowRunErrorEvent.id
            error = betaManagedAgentsWorkflowRunErrorEvent.error
            processedAt = betaManagedAgentsWorkflowRunErrorEvent.processedAt
            type = betaManagedAgentsWorkflowRunErrorEvent.type
            workflowRunId = betaManagedAgentsWorkflowRunErrorEvent.workflowRunId
            additionalProperties =
                betaManagedAgentsWorkflowRunErrorEvent.additionalProperties.toMutableMap()
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

        /** Why the run did not finish, or was not created. */
        fun error(error: BetaManagedAgentsWorkflowRunError) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed
         * [BetaManagedAgentsWorkflowRunError] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<BetaManagedAgentsWorkflowRunError>) = apply {
            this.error = error
        }

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofTimeout(timeout)`.
         */
        fun error(timeout: BetaManagedAgentsTimeoutWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofTimeout(timeout))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsTimeoutWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun timeoutError(message: String) =
            error(BetaManagedAgentsTimeoutWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofProgram(program)`.
         */
        fun error(program: BetaManagedAgentsProgramWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofProgram(program))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsProgramWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun programError(message: String) =
            error(BetaManagedAgentsProgramWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with `BetaManagedAgentsWorkflowRunError.ofUnknown(unknown)`.
         */
        fun error(unknown: BetaManagedAgentsUnknownWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofUnknown(unknown))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsUnknownWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun unknownError(message: String) =
            error(BetaManagedAgentsUnknownWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit)`.
         */
        fun error(threadLimit: BetaManagedAgentsThreadLimitWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofThreadLimit(threadLimit))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsThreadLimitWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun threadLimitError(message: String) =
            error(BetaManagedAgentsThreadLimitWorkflowRunError.builder().message(message).build())

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns)`.
         */
        fun error(maxWorkflowRuns: BetaManagedAgentsMaxWorkflowRunsWorkflowRunError) =
            error(BetaManagedAgentsWorkflowRunError.ofMaxWorkflowRuns(maxWorkflowRuns))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder()
         *     .message(message)
         *     .build()
         * ```
         */
        fun maxWorkflowRunsError(message: String) =
            error(
                BetaManagedAgentsMaxWorkflowRunsWorkflowRunError.builder().message(message).build()
            )

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
         * JsonValue.from("workflow_run.error")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Identifier of the run that met the error, or `null` when the error kept a run from being
         * created.
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunErrorEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .error()
         * .processedAt()
         * .workflowRunId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunErrorEvent =
            BetaManagedAgentsWorkflowRunErrorEvent(
                checkRequired("id", id),
                checkRequired("error", error),
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
    fun validate(): BetaManagedAgentsWorkflowRunErrorEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        error().validate()
        processedAt()
        _type().let {
            if (it != JsonValue.from("workflow_run.error")) {
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
            (error.asKnown().getOrNull()?.validity() ?: 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("workflow_run.error")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunErrorEvent &&
            id == other.id &&
            error == other.error &&
            processedAt == other.processedAt &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, error, processedAt, type, workflowRunId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunErrorEvent{id=$id, error=$error, processedAt=$processedAt, type=$type, workflowRunId=$workflowRunId, additionalProperties=$additionalProperties}"
}
