// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
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
 * A deployment is a configured instance of an agent — it binds the agent to everything needed to
 * run it autonomously: an environment, credentials, initial events, and an optional schedule.
 */
class BetaManagedAgentsDeployment
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agent: JsonField<BetaManagedAgentsAgentReference>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val description: JsonField<String>,
    private val environmentId: JsonField<String>,
    private val initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEvent>>,
    private val metadata: JsonField<Metadata>,
    private val name: JsonField<String>,
    private val pausedReason: JsonField<BetaManagedAgentsDeploymentPausedReason>,
    private val resources: JsonField<List<BetaManagedAgentsSessionResourceConfig>>,
    private val schedule: JsonField<BetaManagedAgentsSchedule>,
    private val status: JsonField<BetaManagedAgentsDeploymentStatus>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val vaultIds: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent")
        @ExcludeMissing
        agent: JsonField<BetaManagedAgentsAgentReference> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("initial_events")
        @ExcludeMissing
        initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEvent>> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("paused_reason")
        @ExcludeMissing
        pausedReason: JsonField<BetaManagedAgentsDeploymentPausedReason> = JsonMissing.of(),
        @JsonProperty("resources")
        @ExcludeMissing
        resources: JsonField<List<BetaManagedAgentsSessionResourceConfig>> = JsonMissing.of(),
        @JsonProperty("schedule")
        @ExcludeMissing
        schedule: JsonField<BetaManagedAgentsSchedule> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaManagedAgentsDeploymentStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("vault_ids")
        @ExcludeMissing
        vaultIds: JsonField<List<String>> = JsonMissing.of(),
    ) : this(
        id,
        agent,
        archivedAt,
        createdAt,
        description,
        environmentId,
        initialEvents,
        metadata,
        name,
        pausedReason,
        resources,
        schedule,
        status,
        type,
        updatedAt,
        vaultIds,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this deployment.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A resolved agent reference with a concrete version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): BetaManagedAgentsAgentReference = agent.getRequired("agent")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Description of what the deployment does.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * ID of the `environment` where sessions run.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun environmentId(): String = environmentId.getRequired("environment_id")

    /**
     * Events sent to each session immediately after creation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun initialEvents(): List<BetaManagedAgentsDeploymentInitialEvent> =
        initialEvents.getRequired("initial_events")

    /**
     * Arbitrary key-value metadata. Maximum 16 pairs.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

    /**
     * Human-readable name.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Why a deployment is paused. Non-null exactly when `status` is `paused`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pausedReason(): Optional<BetaManagedAgentsDeploymentPausedReason> =
        pausedReason.getOptional("paused_reason")

    /**
     * Resources attached to sessions created from this deployment. Echoes the input minus
     * write-only credentials.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun resources(): List<BetaManagedAgentsSessionResourceConfig> =
        resources.getRequired("resources")

    /**
     * 5-field POSIX cron schedule with computed runtime timestamps.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun schedule(): Optional<BetaManagedAgentsSchedule> = schedule.getOptional("schedule")

    /**
     * Lifecycle status of a deployment.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaManagedAgentsDeploymentStatus = status.getRequired("status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Vault IDs supplying stored credentials for sessions created from this deployment.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun vaultIds(): List<String> = vaultIds.getRequired("vault_ids")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agent].
     *
     * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent")
    @ExcludeMissing
    fun _agent(): JsonField<BetaManagedAgentsAgentReference> = agent

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("description") @ExcludeMissing fun _description(): JsonField<String> = description

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<String> = environmentId

    /**
     * Returns the raw JSON value of [initialEvents].
     *
     * Unlike [initialEvents], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("initial_events")
    @ExcludeMissing
    fun _initialEvents(): JsonField<List<BetaManagedAgentsDeploymentInitialEvent>> = initialEvents

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [pausedReason].
     *
     * Unlike [pausedReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("paused_reason")
    @ExcludeMissing
    fun _pausedReason(): JsonField<BetaManagedAgentsDeploymentPausedReason> = pausedReason

    /**
     * Returns the raw JSON value of [resources].
     *
     * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resources")
    @ExcludeMissing
    fun _resources(): JsonField<List<BetaManagedAgentsSessionResourceConfig>> = resources

    /**
     * Returns the raw JSON value of [schedule].
     *
     * Unlike [schedule], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("schedule")
    @ExcludeMissing
    fun _schedule(): JsonField<BetaManagedAgentsSchedule> = schedule

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<BetaManagedAgentsDeploymentStatus> = status

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [vaultIds].
     *
     * Unlike [vaultIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("vault_ids") @ExcludeMissing fun _vaultIds(): JsonField<List<String>> = vaultIds

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsDeployment].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .description()
         * .environmentId()
         * .initialEvents()
         * .metadata()
         * .name()
         * .pausedReason()
         * .resources()
         * .schedule()
         * .status()
         * .type()
         * .updatedAt()
         * .vaultIds()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsDeployment]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agent: JsonField<BetaManagedAgentsAgentReference>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var description: JsonField<String>? = null
        private var environmentId: JsonField<String>? = null
        private var initialEvents:
            JsonField<MutableList<BetaManagedAgentsDeploymentInitialEvent>>? =
            null
        private var metadata: JsonField<Metadata>? = null
        private var name: JsonField<String>? = null
        private var pausedReason: JsonField<BetaManagedAgentsDeploymentPausedReason>? = null
        private var resources: JsonField<MutableList<BetaManagedAgentsSessionResourceConfig>>? =
            null
        private var schedule: JsonField<BetaManagedAgentsSchedule>? = null
        private var status: JsonField<BetaManagedAgentsDeploymentStatus>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var vaultIds: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsDeployment: BetaManagedAgentsDeployment) = apply {
            id = betaManagedAgentsDeployment.id
            agent = betaManagedAgentsDeployment.agent
            archivedAt = betaManagedAgentsDeployment.archivedAt
            createdAt = betaManagedAgentsDeployment.createdAt
            description = betaManagedAgentsDeployment.description
            environmentId = betaManagedAgentsDeployment.environmentId
            initialEvents =
                betaManagedAgentsDeployment.initialEvents
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            metadata = betaManagedAgentsDeployment.metadata
            name = betaManagedAgentsDeployment.name
            pausedReason = betaManagedAgentsDeployment.pausedReason
            resources =
                betaManagedAgentsDeployment.resources
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            schedule = betaManagedAgentsDeployment.schedule
            status = betaManagedAgentsDeployment.status
            type = betaManagedAgentsDeployment.type
            updatedAt = betaManagedAgentsDeployment.updatedAt
            vaultIds =
                betaManagedAgentsDeployment.vaultIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaManagedAgentsDeployment.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this deployment. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A resolved agent reference with a concrete version. */
        fun agent(agent: BetaManagedAgentsAgentReference) = agent(JsonField.of(agent))

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed
         * [BetaManagedAgentsAgentReference] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun agent(agent: JsonField<BetaManagedAgentsAgentReference>) = apply { this.agent = agent }

        /** A timestamp in RFC 3339 format */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Description of what the deployment does. */
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

        /** ID of the `environment` where sessions run. */
        fun environmentId(environmentId: String) = environmentId(JsonField.of(environmentId))

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun environmentId(environmentId: JsonField<String>) = apply {
            this.environmentId = environmentId
        }

        /** Events sent to each session immediately after creation. */
        fun initialEvents(initialEvents: List<BetaManagedAgentsDeploymentInitialEvent>) =
            initialEvents(JsonField.of(initialEvents))

        /**
         * Sets [Builder.initialEvents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.initialEvents] with a well-typed
         * `List<BetaManagedAgentsDeploymentInitialEvent>` value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun initialEvents(initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEvent>>) =
            apply {
                this.initialEvents = initialEvents.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaManagedAgentsDeploymentInitialEvent] to [initialEvents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInitialEvent(initialEvent: BetaManagedAgentsDeploymentInitialEvent) = apply {
            initialEvents =
                (initialEvents ?: JsonField.of(mutableListOf())).also {
                    checkKnown("initialEvents", it).add(initialEvent)
                }
        }

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(userMessage)`.
         */
        fun addInitialEvent(userMessage: BetaManagedAgentsDeploymentUserMessageEvent) =
            addInitialEvent(BetaManagedAgentsDeploymentInitialEvent.ofUserMessage(userMessage))

        /**
         * Alias for calling [addInitialEvent] with the following:
         * ```java
         * BetaManagedAgentsDeploymentUserMessageEvent.builder()
         *     .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addUserMessageInitialEvent(
            content: List<BetaManagedAgentsDeploymentUserMessageEvent.Content>
        ) =
            addInitialEvent(
                BetaManagedAgentsDeploymentUserMessageEvent.builder()
                    .type(BetaManagedAgentsDeploymentUserMessageEvent.Type.USER_MESSAGE)
                    .content(content)
                    .build()
            )

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEvent.ofUserDefineOutcome(userDefineOutcome)`.
         */
        fun addInitialEvent(userDefineOutcome: BetaManagedAgentsDeploymentUserDefineOutcomeEvent) =
            addInitialEvent(
                BetaManagedAgentsDeploymentInitialEvent.ofUserDefineOutcome(userDefineOutcome)
            )

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEvent.ofSystemMessage(systemMessage)`.
         */
        fun addInitialEvent(systemMessage: BetaManagedAgentsDeploymentSystemMessageEvent) =
            addInitialEvent(BetaManagedAgentsDeploymentInitialEvent.ofSystemMessage(systemMessage))

        /**
         * Alias for calling [addInitialEvent] with the following:
         * ```java
         * BetaManagedAgentsDeploymentSystemMessageEvent.builder()
         *     .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addSystemMessageInitialEvent(content: List<BetaManagedAgentsSystemContentBlock>) =
            addInitialEvent(
                BetaManagedAgentsDeploymentSystemMessageEvent.builder()
                    .type(BetaManagedAgentsDeploymentSystemMessageEvent.Type.SYSTEM_MESSAGE)
                    .content(content)
                    .build()
            )

        /** Arbitrary key-value metadata. Maximum 16 pairs. */
        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

        /** Human-readable name. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** Why a deployment is paused. Non-null exactly when `status` is `paused`. */
        fun pausedReason(pausedReason: BetaManagedAgentsDeploymentPausedReason?) =
            pausedReason(JsonField.ofNullable(pausedReason))

        /** Alias for calling [Builder.pausedReason] with `pausedReason.orElse(null)`. */
        fun pausedReason(pausedReason: Optional<BetaManagedAgentsDeploymentPausedReason>) =
            pausedReason(pausedReason.getOrNull())

        /**
         * Sets [Builder.pausedReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pausedReason] with a well-typed
         * [BetaManagedAgentsDeploymentPausedReason] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun pausedReason(pausedReason: JsonField<BetaManagedAgentsDeploymentPausedReason>) = apply {
            this.pausedReason = pausedReason
        }

        /**
         * Alias for calling [pausedReason] with
         * `BetaManagedAgentsDeploymentPausedReason.ofManual(manual)`.
         */
        fun pausedReason(manual: BetaManagedAgentsManualDeploymentPausedReason) =
            pausedReason(BetaManagedAgentsDeploymentPausedReason.ofManual(manual))

        /**
         * Alias for calling [pausedReason] with
         * `BetaManagedAgentsDeploymentPausedReason.ofError(error)`.
         */
        fun pausedReason(error: BetaManagedAgentsErrorDeploymentPausedReason) =
            pausedReason(BetaManagedAgentsDeploymentPausedReason.ofError(error))

        /**
         * Alias for calling [pausedReason] with the following:
         * ```java
         * BetaManagedAgentsErrorDeploymentPausedReason.builder()
         *     .type(BetaManagedAgentsErrorDeploymentPausedReason.Type.ERROR)
         *     .error(error)
         *     .build()
         * ```
         */
        fun errorPausedReason(error: BetaManagedAgentsDeploymentPausedReasonError) =
            pausedReason(
                BetaManagedAgentsErrorDeploymentPausedReason.builder()
                    .type(BetaManagedAgentsErrorDeploymentPausedReason.Type.ERROR)
                    .error(error)
                    .build()
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)`.
         */
        fun errorPausedReason(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                    environmentArchived
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)`.
         */
        fun errorPausedReason(
            agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)`.
         */
        fun errorPausedReason(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                    environmentNotFound
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)`.
         */
        fun errorPausedReason(
            vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)`.
         */
        fun errorPausedReason(
            fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(sessionResourceNotFound)`.
         */
        fun errorPausedReason(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                    sessionResourceNotFound
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)`.
         */
        fun errorPausedReason(
            workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(organizationDisabled)`.
         */
        fun errorPausedReason(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                    organizationDisabled
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)`.
         */
        fun errorPausedReason(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                    memoryStoreArchived
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)`.
         */
        fun errorPausedReason(
            skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)`.
         */
        fun errorPausedReason(
            vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)`.
         */
        fun errorPausedReason(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError) =
            errorPausedReason(BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown))

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)`.
         */
        fun errorPausedReason(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported
                )
            )

        /**
         * Alias for calling [errorPausedReason] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)`.
         */
        fun errorPausedReason(
            mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError
        ) =
            errorPausedReason(
                BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)
            )

        /**
         * Resources attached to sessions created from this deployment. Echoes the input minus
         * write-only credentials.
         */
        fun resources(resources: List<BetaManagedAgentsSessionResourceConfig>) =
            resources(JsonField.of(resources))

        /**
         * Sets [Builder.resources] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resources] with a well-typed
         * `List<BetaManagedAgentsSessionResourceConfig>` value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun resources(resources: JsonField<List<BetaManagedAgentsSessionResourceConfig>>) = apply {
            this.resources = resources.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsSessionResourceConfig] to [resources].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResource(resource: BetaManagedAgentsSessionResourceConfig) = apply {
            resources =
                (resources ?: JsonField.of(mutableListOf())).also {
                    checkKnown("resources", it).add(resource)
                }
        }

        /**
         * Alias for calling [addResource] with
         * `BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(githubRepository)`.
         */
        fun addResource(githubRepository: BetaManagedAgentsGitHubRepositoryResourceConfig) =
            addResource(BetaManagedAgentsSessionResourceConfig.ofGitHubRepository(githubRepository))

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
         *     .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
         *     .url(url)
         *     .build()
         * ```
         */
        fun addGitHubRepositoryResource(url: String) =
            addResource(
                BetaManagedAgentsGitHubRepositoryResourceConfig.builder()
                    .type(BetaManagedAgentsGitHubRepositoryResourceConfig.Type.GITHUB_REPOSITORY)
                    .url(url)
                    .build()
            )

        /**
         * Alias for calling [addResource] with
         * `BetaManagedAgentsSessionResourceConfig.ofFile(file)`.
         */
        fun addResource(file: BetaManagedAgentsFileResourceConfig) =
            addResource(BetaManagedAgentsSessionResourceConfig.ofFile(file))

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsFileResourceConfig.builder()
         *     .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addFileResource(fileId: String) =
            addResource(
                BetaManagedAgentsFileResourceConfig.builder()
                    .type(BetaManagedAgentsFileResourceConfig.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

        /**
         * Alias for calling [addResource] with
         * `BetaManagedAgentsSessionResourceConfig.ofMemoryStore(memoryStore)`.
         */
        fun addResource(memoryStore: BetaManagedAgentsMemoryStoreResourceConfig) =
            addResource(BetaManagedAgentsSessionResourceConfig.ofMemoryStore(memoryStore))

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsMemoryStoreResourceConfig.builder()
         *     .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreResource(memoryStoreId: String) =
            addResource(
                BetaManagedAgentsMemoryStoreResourceConfig.builder()
                    .type(BetaManagedAgentsMemoryStoreResourceConfig.Type.MEMORY_STORE)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )

        /** 5-field POSIX cron schedule with computed runtime timestamps. */
        fun schedule(schedule: BetaManagedAgentsSchedule?) =
            schedule(JsonField.ofNullable(schedule))

        /** Alias for calling [Builder.schedule] with `schedule.orElse(null)`. */
        fun schedule(schedule: Optional<BetaManagedAgentsSchedule>) = schedule(schedule.getOrNull())

        /**
         * Sets [Builder.schedule] to an arbitrary JSON value.
         *
         * You should usually call [Builder.schedule] with a well-typed [BetaManagedAgentsSchedule]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun schedule(schedule: JsonField<BetaManagedAgentsSchedule>) = apply {
            this.schedule = schedule
        }

        /** Lifecycle status of a deployment. */
        fun status(status: BetaManagedAgentsDeploymentStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed
         * [BetaManagedAgentsDeploymentStatus] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<BetaManagedAgentsDeploymentStatus>) = apply {
            this.status = status
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Vault IDs supplying stored credentials for sessions created from this deployment. */
        fun vaultIds(vaultIds: List<String>) = vaultIds(JsonField.of(vaultIds))

        /**
         * Sets [Builder.vaultIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.vaultIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun vaultIds(vaultIds: JsonField<List<String>>) = apply {
            this.vaultIds = vaultIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [vaultIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addVaultId(vaultId: String) = apply {
            vaultIds =
                (vaultIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("vaultIds", it).add(vaultId)
                }
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
         * Returns an immutable instance of [BetaManagedAgentsDeployment].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .description()
         * .environmentId()
         * .initialEvents()
         * .metadata()
         * .name()
         * .pausedReason()
         * .resources()
         * .schedule()
         * .status()
         * .type()
         * .updatedAt()
         * .vaultIds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsDeployment =
            BetaManagedAgentsDeployment(
                checkRequired("id", id),
                checkRequired("agent", agent),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("description", description),
                checkRequired("environmentId", environmentId),
                checkRequired("initialEvents", initialEvents).map { it.toImmutable() },
                checkRequired("metadata", metadata),
                checkRequired("name", name),
                checkRequired("pausedReason", pausedReason),
                checkRequired("resources", resources).map { it.toImmutable() },
                checkRequired("schedule", schedule),
                checkRequired("status", status),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                checkRequired("vaultIds", vaultIds).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsDeployment = apply {
        if (validated) {
            return@apply
        }

        id()
        agent().validate()
        archivedAt()
        createdAt()
        description()
        environmentId()
        initialEvents().forEach { it.validate() }
        metadata().validate()
        name()
        pausedReason().ifPresent { it.validate() }
        resources().forEach { it.validate() }
        schedule().ifPresent { it.validate() }
        status().validate()
        type().validate()
        updatedAt()
        vaultIds()
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
            (agent.asKnown().getOrNull()?.validity() ?: 0) +
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (if (environmentId.asKnown().isPresent) 1 else 0) +
            (initialEvents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (pausedReason.asKnown().getOrNull()?.validity() ?: 0) +
            (resources.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (schedule.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (vaultIds.asKnown().getOrNull()?.size ?: 0)

    /** Arbitrary key-value metadata. Maximum 16 pairs. */
    class Metadata
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                additionalProperties = metadata.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Metadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Metadata = Metadata(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Metadata = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val DEPLOYMENT = of("deployment")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            DEPLOYMENT
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DEPLOYMENT,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                DEPLOYMENT -> Value.DEPLOYMENT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                DEPLOYMENT -> Known.DEPLOYMENT
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeployment &&
            id == other.id &&
            agent == other.agent &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            description == other.description &&
            environmentId == other.environmentId &&
            initialEvents == other.initialEvents &&
            metadata == other.metadata &&
            name == other.name &&
            pausedReason == other.pausedReason &&
            resources == other.resources &&
            schedule == other.schedule &&
            status == other.status &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            vaultIds == other.vaultIds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agent,
            archivedAt,
            createdAt,
            description,
            environmentId,
            initialEvents,
            metadata,
            name,
            pausedReason,
            resources,
            schedule,
            status,
            type,
            updatedAt,
            vaultIds,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsDeployment{id=$id, agent=$agent, archivedAt=$archivedAt, createdAt=$createdAt, description=$description, environmentId=$environmentId, initialEvents=$initialEvents, metadata=$metadata, name=$name, pausedReason=$pausedReason, resources=$resources, schedule=$schedule, status=$status, type=$type, updatedAt=$updatedAt, vaultIds=$vaultIds, additionalProperties=$additionalProperties}"
}
