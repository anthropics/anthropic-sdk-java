// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsFileResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsGitHubRepositoryResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsMemoryStoreResource
import com.anthropic.models.beta.sessions.resources.BetaManagedAgentsSessionResource
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A Managed Agents `session`. */
class BetaManagedAgentsSession
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agent: JsonField<BetaManagedAgentsSessionAgent>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val environmentId: JsonField<String>,
    private val metadata: JsonField<Metadata>,
    private val outcomeEvaluations: JsonField<List<BetaManagedAgentsOutcomeEvaluationResource>>,
    private val resources: JsonField<List<BetaManagedAgentsSessionResource>>,
    private val stats: JsonField<BetaManagedAgentsSessionStats>,
    private val status: JsonField<Status>,
    private val title: JsonField<String>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val usage: JsonField<BetaManagedAgentsSessionUsage>,
    private val vaultIds: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent")
        @ExcludeMissing
        agent: JsonField<BetaManagedAgentsSessionAgent> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("environment_id")
        @ExcludeMissing
        environmentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("outcome_evaluations")
        @ExcludeMissing
        outcomeEvaluations: JsonField<List<BetaManagedAgentsOutcomeEvaluationResource>> =
            JsonMissing.of(),
        @JsonProperty("resources")
        @ExcludeMissing
        resources: JsonField<List<BetaManagedAgentsSessionResource>> = JsonMissing.of(),
        @JsonProperty("stats")
        @ExcludeMissing
        stats: JsonField<BetaManagedAgentsSessionStats> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("usage")
        @ExcludeMissing
        usage: JsonField<BetaManagedAgentsSessionUsage> = JsonMissing.of(),
        @JsonProperty("vault_ids")
        @ExcludeMissing
        vaultIds: JsonField<List<String>> = JsonMissing.of(),
    ) : this(
        id,
        agent,
        archivedAt,
        createdAt,
        environmentId,
        metadata,
        outcomeEvaluations,
        resources,
        stats,
        status,
        title,
        type,
        updatedAt,
        usage,
        vaultIds,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Resolved `agent` definition for a `session`. Snapshot of the `agent` at `session` creation
     * time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): BetaManagedAgentsSessionAgent = agent.getRequired("agent")

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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun environmentId(): String = environmentId.getRequired("environment_id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

    /**
     * Per-outcome evaluation state. One entry per define_outcome event sent to the session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outcomeEvaluations(): List<BetaManagedAgentsOutcomeEvaluationResource> =
        outcomeEvaluations.getRequired("outcome_evaluations")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun resources(): List<BetaManagedAgentsSessionResource> = resources.getRequired("resources")

    /**
     * Timing statistics for a session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun stats(): BetaManagedAgentsSessionStats = stats.getRequired("stats")

    /**
     * SessionStatus enum
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = title.getOptional("title")

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
     * Cumulative token usage for a session across all turns.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun usage(): BetaManagedAgentsSessionUsage = usage.getRequired("usage")

    /**
     * Vault IDs attached to the session at creation. Empty when no vaults were supplied.
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
    fun _agent(): JsonField<BetaManagedAgentsSessionAgent> = agent

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
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("environment_id")
    @ExcludeMissing
    fun _environmentId(): JsonField<String> = environmentId

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    /**
     * Returns the raw JSON value of [outcomeEvaluations].
     *
     * Unlike [outcomeEvaluations], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("outcome_evaluations")
    @ExcludeMissing
    fun _outcomeEvaluations(): JsonField<List<BetaManagedAgentsOutcomeEvaluationResource>> =
        outcomeEvaluations

    /**
     * Returns the raw JSON value of [resources].
     *
     * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resources")
    @ExcludeMissing
    fun _resources(): JsonField<List<BetaManagedAgentsSessionResource>> = resources

    /**
     * Returns the raw JSON value of [stats].
     *
     * Unlike [stats], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stats")
    @ExcludeMissing
    fun _stats(): JsonField<BetaManagedAgentsSessionStats> = stats

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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
     * Returns the raw JSON value of [usage].
     *
     * Unlike [usage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("usage")
    @ExcludeMissing
    fun _usage(): JsonField<BetaManagedAgentsSessionUsage> = usage

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsSession].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .environmentId()
         * .metadata()
         * .outcomeEvaluations()
         * .resources()
         * .stats()
         * .status()
         * .title()
         * .type()
         * .updatedAt()
         * .usage()
         * .vaultIds()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSession]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agent: JsonField<BetaManagedAgentsSessionAgent>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var environmentId: JsonField<String>? = null
        private var metadata: JsonField<Metadata>? = null
        private var outcomeEvaluations:
            JsonField<MutableList<BetaManagedAgentsOutcomeEvaluationResource>>? =
            null
        private var resources: JsonField<MutableList<BetaManagedAgentsSessionResource>>? = null
        private var stats: JsonField<BetaManagedAgentsSessionStats>? = null
        private var status: JsonField<Status>? = null
        private var title: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var usage: JsonField<BetaManagedAgentsSessionUsage>? = null
        private var vaultIds: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSession: BetaManagedAgentsSession) = apply {
            id = betaManagedAgentsSession.id
            agent = betaManagedAgentsSession.agent
            archivedAt = betaManagedAgentsSession.archivedAt
            createdAt = betaManagedAgentsSession.createdAt
            environmentId = betaManagedAgentsSession.environmentId
            metadata = betaManagedAgentsSession.metadata
            outcomeEvaluations =
                betaManagedAgentsSession.outcomeEvaluations.map { it.toMutableList() }
            resources = betaManagedAgentsSession.resources.map { it.toMutableList() }
            stats = betaManagedAgentsSession.stats
            status = betaManagedAgentsSession.status
            title = betaManagedAgentsSession.title
            type = betaManagedAgentsSession.type
            updatedAt = betaManagedAgentsSession.updatedAt
            usage = betaManagedAgentsSession.usage
            vaultIds = betaManagedAgentsSession.vaultIds.map { it.toMutableList() }
            additionalProperties = betaManagedAgentsSession.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Resolved `agent` definition for a `session`. Snapshot of the `agent` at `session`
         * creation time.
         */
        fun agent(agent: BetaManagedAgentsSessionAgent) = agent(JsonField.of(agent))

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed [BetaManagedAgentsSessionAgent]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun agent(agent: JsonField<BetaManagedAgentsSessionAgent>) = apply { this.agent = agent }

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

        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

        /** Per-outcome evaluation state. One entry per define_outcome event sent to the session. */
        fun outcomeEvaluations(
            outcomeEvaluations: List<BetaManagedAgentsOutcomeEvaluationResource>
        ) = outcomeEvaluations(JsonField.of(outcomeEvaluations))

        /**
         * Sets [Builder.outcomeEvaluations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outcomeEvaluations] with a well-typed
         * `List<BetaManagedAgentsOutcomeEvaluationResource>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun outcomeEvaluations(
            outcomeEvaluations: JsonField<List<BetaManagedAgentsOutcomeEvaluationResource>>
        ) = apply { this.outcomeEvaluations = outcomeEvaluations.map { it.toMutableList() } }

        /**
         * Adds a single [BetaManagedAgentsOutcomeEvaluationResource] to [outcomeEvaluations].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addOutcomeEvaluation(outcomeEvaluation: BetaManagedAgentsOutcomeEvaluationResource) =
            apply {
                outcomeEvaluations =
                    (outcomeEvaluations ?: JsonField.of(mutableListOf())).also {
                        checkKnown("outcomeEvaluations", it).add(outcomeEvaluation)
                    }
            }

        fun resources(resources: List<BetaManagedAgentsSessionResource>) =
            resources(JsonField.of(resources))

        /**
         * Sets [Builder.resources] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resources] with a well-typed
         * `List<BetaManagedAgentsSessionResource>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun resources(resources: JsonField<List<BetaManagedAgentsSessionResource>>) = apply {
            this.resources = resources.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsSessionResource] to [resources].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResource(resource: BetaManagedAgentsSessionResource) = apply {
            resources =
                (resources ?: JsonField.of(mutableListOf())).also {
                    checkKnown("resources", it).add(resource)
                }
        }

        /**
         * Alias for calling [addResource] with
         * `BetaManagedAgentsSessionResource.ofGitHubRepository(githubRepository)`.
         */
        fun addResource(githubRepository: BetaManagedAgentsGitHubRepositoryResource) =
            addResource(BetaManagedAgentsSessionResource.ofGitHubRepository(githubRepository))

        /** Alias for calling [addResource] with `BetaManagedAgentsSessionResource.ofFile(file)`. */
        fun addResource(file: BetaManagedAgentsFileResource) =
            addResource(BetaManagedAgentsSessionResource.ofFile(file))

        /**
         * Alias for calling [addResource] with
         * `BetaManagedAgentsSessionResource.ofMemoryStore(memoryStore)`.
         */
        fun addResource(memoryStore: BetaManagedAgentsMemoryStoreResource) =
            addResource(BetaManagedAgentsSessionResource.ofMemoryStore(memoryStore))

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsMemoryStoreResource.builder()
         *     .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreResource(memoryStoreId: String) =
            addResource(
                BetaManagedAgentsMemoryStoreResource.builder()
                    .type(BetaManagedAgentsMemoryStoreResource.Type.MEMORY_STORE)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )

        /** Timing statistics for a session. */
        fun stats(stats: BetaManagedAgentsSessionStats) = stats(JsonField.of(stats))

        /**
         * Sets [Builder.stats] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stats] with a well-typed [BetaManagedAgentsSessionStats]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun stats(stats: JsonField<BetaManagedAgentsSessionStats>) = apply { this.stats = stats }

        /** SessionStatus enum */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

        fun title(title: String?) = title(JsonField.ofNullable(title))

        /** Alias for calling [Builder.title] with `title.orElse(null)`. */
        fun title(title: Optional<String>) = title(title.getOrNull())

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { this.title = title }

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

        /** Cumulative token usage for a session across all turns. */
        fun usage(usage: BetaManagedAgentsSessionUsage) = usage(JsonField.of(usage))

        /**
         * Sets [Builder.usage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.usage] with a well-typed [BetaManagedAgentsSessionUsage]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun usage(usage: JsonField<BetaManagedAgentsSessionUsage>) = apply { this.usage = usage }

        /** Vault IDs attached to the session at creation. Empty when no vaults were supplied. */
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
         * Returns an immutable instance of [BetaManagedAgentsSession].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .archivedAt()
         * .createdAt()
         * .environmentId()
         * .metadata()
         * .outcomeEvaluations()
         * .resources()
         * .stats()
         * .status()
         * .title()
         * .type()
         * .updatedAt()
         * .usage()
         * .vaultIds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSession =
            BetaManagedAgentsSession(
                checkRequired("id", id),
                checkRequired("agent", agent),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("environmentId", environmentId),
                checkRequired("metadata", metadata),
                checkRequired("outcomeEvaluations", outcomeEvaluations).map { it.toImmutable() },
                checkRequired("resources", resources).map { it.toImmutable() },
                checkRequired("stats", stats),
                checkRequired("status", status),
                checkRequired("title", title),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                checkRequired("usage", usage),
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
    fun validate(): BetaManagedAgentsSession = apply {
        if (validated) {
            return@apply
        }

        id()
        agent().validate()
        archivedAt()
        createdAt()
        environmentId()
        metadata().validate()
        outcomeEvaluations().forEach { it.validate() }
        resources().forEach { it.validate() }
        stats().validate()
        status().validate()
        title()
        type().validate()
        updatedAt()
        usage().validate()
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
            (if (environmentId.asKnown().isPresent) 1 else 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (outcomeEvaluations.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (resources.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (stats.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (usage.asKnown().getOrNull()?.validity() ?: 0) +
            (vaultIds.asKnown().getOrNull()?.size ?: 0)

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

    /** SessionStatus enum */
    class Status @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val RESCHEDULING = of("rescheduling")

            @JvmField val RUNNING = of("running")

            @JvmField val IDLE = of("idle")

            @JvmField val TERMINATED = of("terminated")

            @JvmStatic fun of(value: String) = Status(JsonField.of(value))
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            RESCHEDULING,
            RUNNING,
            IDLE,
            TERMINATED,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            RESCHEDULING,
            RUNNING,
            IDLE,
            TERMINATED,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
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
                RESCHEDULING -> Value.RESCHEDULING
                RUNNING -> Value.RUNNING
                IDLE -> Value.IDLE
                TERMINATED -> Value.TERMINATED
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
                RESCHEDULING -> Known.RESCHEDULING
                RUNNING -> Known.RUNNING
                IDLE -> Known.IDLE
                TERMINATED -> Known.TERMINATED
                else -> throw AnthropicInvalidDataException("Unknown Status: $value")
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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
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

            @JvmField val SESSION = of("session")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION
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
            SESSION,
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
                SESSION -> Value.SESSION
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
                SESSION -> Known.SESSION
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

        return other is BetaManagedAgentsSession &&
            id == other.id &&
            agent == other.agent &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            environmentId == other.environmentId &&
            metadata == other.metadata &&
            outcomeEvaluations == other.outcomeEvaluations &&
            resources == other.resources &&
            stats == other.stats &&
            status == other.status &&
            title == other.title &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            usage == other.usage &&
            vaultIds == other.vaultIds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agent,
            archivedAt,
            createdAt,
            environmentId,
            metadata,
            outcomeEvaluations,
            resources,
            stats,
            status,
            title,
            type,
            updatedAt,
            usage,
            vaultIds,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSession{id=$id, agent=$agent, archivedAt=$archivedAt, createdAt=$createdAt, environmentId=$environmentId, metadata=$metadata, outcomeEvaluations=$outcomeEvaluations, resources=$resources, stats=$stats, status=$status, title=$title, type=$type, updatedAt=$updatedAt, usage=$usage, vaultIds=$vaultIds, additionalProperties=$additionalProperties}"
}
