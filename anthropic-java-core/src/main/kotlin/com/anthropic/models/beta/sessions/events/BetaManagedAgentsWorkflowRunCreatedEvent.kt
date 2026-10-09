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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A workflow run was created. A workflow run is background work that the session's agent starts.
 * Emitted once per run, before the run's other `workflow_run.*` events.
 */
class BetaManagedAgentsWorkflowRunCreatedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val description: JsonField<String>,
    private val name: JsonField<String>,
    private val phases: JsonField<List<BetaManagedAgentsWorkflowRunPhase>>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val workflowRunId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("phases")
        @ExcludeMissing
        phases: JsonField<List<BetaManagedAgentsWorkflowRunPhase>> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workflow_run_id")
        @ExcludeMissing
        workflowRunId: JsonField<String> = JsonMissing.of(),
    ) : this(id, description, name, phases, processedAt, type, workflowRunId, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Description that the agent gave the run, passed on as written, or `null` if it gave none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * Name that the agent gave the run, passed on as written, or a name that the server assigned.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * The phases that the run's plan declares, in the plan's order. Can be empty.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun phases(): List<BetaManagedAgentsWorkflowRunPhase> = phases.getRequired("phases")

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
     * JsonValue.from("workflow_run.created")
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
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [phases].
     *
     * Unlike [phases], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("phases")
    @ExcludeMissing
    fun _phases(): JsonField<List<BetaManagedAgentsWorkflowRunPhase>> = phases

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
         * [BetaManagedAgentsWorkflowRunCreatedEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .name()
         * .phases()
         * .processedAt()
         * .workflowRunId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsWorkflowRunCreatedEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var description: JsonField<String>? = null
        private var name: JsonField<String>? = null
        private var phases: JsonField<MutableList<BetaManagedAgentsWorkflowRunPhase>>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("workflow_run.created")
        private var workflowRunId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWorkflowRunCreatedEvent: BetaManagedAgentsWorkflowRunCreatedEvent
        ) = apply {
            id = betaManagedAgentsWorkflowRunCreatedEvent.id
            description = betaManagedAgentsWorkflowRunCreatedEvent.description
            name = betaManagedAgentsWorkflowRunCreatedEvent.name
            phases =
                betaManagedAgentsWorkflowRunCreatedEvent.phases
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            processedAt = betaManagedAgentsWorkflowRunCreatedEvent.processedAt
            type = betaManagedAgentsWorkflowRunCreatedEvent.type
            workflowRunId = betaManagedAgentsWorkflowRunCreatedEvent.workflowRunId
            additionalProperties =
                betaManagedAgentsWorkflowRunCreatedEvent.additionalProperties.toMutableMap()
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
         * Description that the agent gave the run, passed on as written, or `null` if it gave none.
         */
        fun description(description: String?) = description(JsonField.ofNullable(description))

        /** Alias for calling [Builder.description] with `description.orElse(null)`. */
        fun description(description: Optional<String>) = description(description.getOrNull())

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { this.description = description }

        /**
         * Name that the agent gave the run, passed on as written, or a name that the server
         * assigned.
         */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The phases that the run's plan declares, in the plan's order. Can be empty. */
        fun phases(phases: List<BetaManagedAgentsWorkflowRunPhase>) = phases(JsonField.of(phases))

        /**
         * Sets [Builder.phases] to an arbitrary JSON value.
         *
         * You should usually call [Builder.phases] with a well-typed
         * `List<BetaManagedAgentsWorkflowRunPhase>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun phases(phases: JsonField<List<BetaManagedAgentsWorkflowRunPhase>>) = apply {
            this.phases = phases.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsWorkflowRunPhase] to [phases].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPhase(phase: BetaManagedAgentsWorkflowRunPhase) = apply {
            phases =
                (phases ?: JsonField.of(mutableListOf())).also {
                    checkKnown("phases", it).add(phase)
                }
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
         * JsonValue.from("workflow_run.created")
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
         * Returns an immutable instance of [BetaManagedAgentsWorkflowRunCreatedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .name()
         * .phases()
         * .processedAt()
         * .workflowRunId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsWorkflowRunCreatedEvent =
            BetaManagedAgentsWorkflowRunCreatedEvent(
                checkRequired("id", id),
                checkRequired("description", description),
                checkRequired("name", name),
                checkRequired("phases", phases).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsWorkflowRunCreatedEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        description()
        name()
        phases().forEach { it.validate() }
        processedAt()
        _type().let {
            if (it != JsonValue.from("workflow_run.created")) {
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
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (phases.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("workflow_run.created")) 1 else 0 } +
            (if (workflowRunId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsWorkflowRunCreatedEvent &&
            id == other.id &&
            description == other.description &&
            name == other.name &&
            phases == other.phases &&
            processedAt == other.processedAt &&
            type == other.type &&
            workflowRunId == other.workflowRunId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            description,
            name,
            phases,
            processedAt,
            type,
            workflowRunId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWorkflowRunCreatedEvent{id=$id, description=$description, name=$name, phases=$phases, processedAt=$processedAt, type=$type, workflowRunId=$workflowRunId, additionalProperties=$additionalProperties}"
}
