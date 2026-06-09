// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsGitHubRepositoryResourceParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsMemoryStoreResourceParam
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsSystemMessageEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserDefineOutcomeEventParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Update Deployment */
class DeploymentUpdateParams
private constructor(
    private val deploymentId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun deploymentId(): Optional<String> = Optional.ofNullable(deploymentId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Agent to deploy. Accepts the `agent` ID string, which re-pins to the latest version, or an
     * `agent` object with both id and version specified. Omit to preserve. Cannot be cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun agent(): Optional<Agent> = body.agent()

    /**
     * Description. Omit to preserve; send empty string or null to clear.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = body.description()

    /**
     * ID of the `environment` where sessions run. Omit to preserve. Cannot be cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun environmentId(): Optional<String> = body.environmentId()

    /**
     * Initial events. Full replacement. Omit to preserve. Cannot be cleared. At least 1,
     * maximum 50.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun initialEvents(): Optional<List<BetaManagedAgentsDeploymentInitialEventParams>> =
        body.initialEvents()

    /**
     * Metadata patch. Set a key to a string to upsert it, or to null to delete it. Omit the field
     * to preserve. The stored bag is limited to 16 keys (up to 64 chars each) with values up to 512
     * chars.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun metadata(): Optional<Metadata> = body.metadata()

    /**
     * Human-readable name. Must be non-empty. Omit to preserve. Cannot be cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = body.name()

    /**
     * Session resources. Full replacement. Omit to preserve; send empty array or null to clear.
     * Maximum 500.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resources(): Optional<List<Resource>> = body.resources()

    /**
     * 5-field POSIX cron schedule. Literal wall-clock matching in the configured timezone.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun schedule(): Optional<BetaManagedAgentsScheduleParams> = body.schedule()

    /**
     * Vault IDs. Full replacement. Omit to preserve; send empty array or null to clear. Maximum 50.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun vaultIds(): Optional<List<String>> = body.vaultIds()

    /**
     * Returns the raw JSON value of [agent].
     *
     * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _agent(): JsonField<Agent> = body._agent()

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _description(): JsonField<String> = body._description()

    /**
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _environmentId(): JsonField<String> = body._environmentId()

    /**
     * Returns the raw JSON value of [initialEvents].
     *
     * Unlike [initialEvents], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _initialEvents(): JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>> =
        body._initialEvents()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    /**
     * Returns the raw JSON value of [resources].
     *
     * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _resources(): JsonField<List<Resource>> = body._resources()

    /**
     * Returns the raw JSON value of [schedule].
     *
     * Unlike [schedule], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _schedule(): JsonField<BetaManagedAgentsScheduleParams> = body._schedule()

    /**
     * Returns the raw JSON value of [vaultIds].
     *
     * Unlike [vaultIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _vaultIds(): JsonField<List<String>> = body._vaultIds()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): DeploymentUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [DeploymentUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [DeploymentUpdateParams]. */
    class Builder internal constructor() {

        private var deploymentId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(deploymentUpdateParams: DeploymentUpdateParams) = apply {
            deploymentId = deploymentUpdateParams.deploymentId
            betas = deploymentUpdateParams.betas?.toMutableList()
            body = deploymentUpdateParams.body.toBuilder()
            additionalHeaders = deploymentUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = deploymentUpdateParams.additionalQueryParams.toBuilder()
        }

        fun deploymentId(deploymentId: String?) = apply { this.deploymentId = deploymentId }

        /** Alias for calling [Builder.deploymentId] with `deploymentId.orElse(null)`. */
        fun deploymentId(deploymentId: Optional<String>) = deploymentId(deploymentId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [agent]
         * - [description]
         * - [environmentId]
         * - [initialEvents]
         * - [metadata]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Agent to deploy. Accepts the `agent` ID string, which re-pins to the latest version, or
         * an `agent` object with both id and version specified. Omit to preserve. Cannot be
         * cleared.
         */
        fun agent(agent: Agent) = apply { body.agent(agent) }

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed [Agent] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun agent(agent: JsonField<Agent>) = apply { body.agent(agent) }

        /** Alias for calling [agent] with `Agent.ofString(string)`. */
        fun agent(string: String) = apply { body.agent(string) }

        /**
         * Alias for calling [agent] with
         * `Agent.ofBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)`.
         */
        fun agent(betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams) = apply {
            body.agent(betaManagedAgentsAgentParams)
        }

        /** Description. Omit to preserve; send empty string or null to clear. */
        fun description(description: String?) = apply { body.description(description) }

        /** Alias for calling [Builder.description] with `description.orElse(null)`. */
        fun description(description: Optional<String>) = description(description.getOrNull())

        /**
         * Sets [Builder.description] to an arbitrary JSON value.
         *
         * You should usually call [Builder.description] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun description(description: JsonField<String>) = apply { body.description(description) }

        /** ID of the `environment` where sessions run. Omit to preserve. Cannot be cleared. */
        fun environmentId(environmentId: String) = apply { body.environmentId(environmentId) }

        /**
         * Sets [Builder.environmentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.environmentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun environmentId(environmentId: JsonField<String>) = apply {
            body.environmentId(environmentId)
        }

        /**
         * Initial events. Full replacement. Omit to preserve. Cannot be cleared. At least 1,
         * maximum 50.
         */
        fun initialEvents(initialEvents: List<BetaManagedAgentsDeploymentInitialEventParams>) =
            apply {
                body.initialEvents(initialEvents)
            }

        /**
         * Sets [Builder.initialEvents] to an arbitrary JSON value.
         *
         * You should usually call [Builder.initialEvents] with a well-typed
         * `List<BetaManagedAgentsDeploymentInitialEventParams>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun initialEvents(
            initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>>
        ) = apply { body.initialEvents(initialEvents) }

        /**
         * Adds a single [BetaManagedAgentsDeploymentInitialEventParams] to [initialEvents].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInitialEvent(initialEvent: BetaManagedAgentsDeploymentInitialEventParams) = apply {
            body.addInitialEvent(initialEvent)
        }

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(userMessage)`.
         */
        fun addInitialEvent(userMessage: BetaManagedAgentsUserMessageEventParams) = apply {
            body.addInitialEvent(userMessage)
        }

        /**
         * Alias for calling [addInitialEvent] with the following:
         * ```java
         * BetaManagedAgentsUserMessageEventParams.builder()
         *     .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addUserMessageInitialEvent(
            content: List<BetaManagedAgentsUserMessageEventParams.Content>
        ) = apply { body.addUserMessageInitialEvent(content) }

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(userDefineOutcome)`.
         */
        fun addInitialEvent(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) =
            apply {
                body.addInitialEvent(userDefineOutcome)
            }

        /**
         * Alias for calling [addInitialEvent] with
         * `BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(systemMessage)`.
         */
        fun addInitialEvent(systemMessage: BetaManagedAgentsSystemMessageEventParams) = apply {
            body.addInitialEvent(systemMessage)
        }

        /**
         * Alias for calling [addInitialEvent] with the following:
         * ```java
         * BetaManagedAgentsSystemMessageEventParams.builder()
         *     .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addSystemMessageInitialEvent(content: List<BetaManagedAgentsSystemContentBlock>) =
            apply {
                body.addSystemMessageInitialEvent(content)
            }

        /**
         * Metadata patch. Set a key to a string to upsert it, or to null to delete it. Omit the
         * field to preserve. The stored bag is limited to 16 keys (up to 64 chars each) with values
         * up to 512 chars.
         */
        fun metadata(metadata: Metadata?) = apply { body.metadata(metadata) }

        /** Alias for calling [Builder.metadata] with `metadata.orElse(null)`. */
        fun metadata(metadata: Optional<Metadata>) = metadata(metadata.getOrNull())

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        /** Human-readable name. Must be non-empty. Omit to preserve. Cannot be cleared. */
        fun name(name: String) = apply { body.name(name) }

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        /**
         * Session resources. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 500.
         */
        fun resources(resources: List<Resource>?) = apply { body.resources(resources) }

        /** Alias for calling [Builder.resources] with `resources.orElse(null)`. */
        fun resources(resources: Optional<List<Resource>>) = resources(resources.getOrNull())

        /**
         * Sets [Builder.resources] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resources] with a well-typed `List<Resource>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun resources(resources: JsonField<List<Resource>>) = apply { body.resources(resources) }

        /**
         * Adds a single [Resource] to [resources].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addResource(resource: Resource) = apply { body.addResource(resource) }

        /** Alias for calling [addResource] with `Resource.ofGitHubRepository(githubRepository)`. */
        fun addResource(githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams) = apply {
            body.addResource(githubRepository)
        }

        /** Alias for calling [addResource] with `Resource.ofFile(file)`. */
        fun addResource(file: BetaManagedAgentsFileResourceParams) = apply {
            body.addResource(file)
        }

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsFileResourceParams.builder()
         *     .type(BetaManagedAgentsFileResourceParams.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addFileResource(fileId: String) = apply { body.addFileResource(fileId) }

        /** Alias for calling [addResource] with `Resource.ofMemoryStore(memoryStore)`. */
        fun addResource(memoryStore: BetaManagedAgentsMemoryStoreResourceParam) = apply {
            body.addResource(memoryStore)
        }

        /**
         * Alias for calling [addResource] with the following:
         * ```java
         * BetaManagedAgentsMemoryStoreResourceParam.builder()
         *     .type(BetaManagedAgentsMemoryStoreResourceParam.Type.MEMORY_STORE)
         *     .memoryStoreId(memoryStoreId)
         *     .build()
         * ```
         */
        fun addMemoryStoreResource(memoryStoreId: String) = apply {
            body.addMemoryStoreResource(memoryStoreId)
        }

        /** 5-field POSIX cron schedule. Literal wall-clock matching in the configured timezone. */
        fun schedule(schedule: BetaManagedAgentsScheduleParams?) = apply { body.schedule(schedule) }

        /** Alias for calling [Builder.schedule] with `schedule.orElse(null)`. */
        fun schedule(schedule: Optional<BetaManagedAgentsScheduleParams>) =
            schedule(schedule.getOrNull())

        /**
         * Sets [Builder.schedule] to an arbitrary JSON value.
         *
         * You should usually call [Builder.schedule] with a well-typed
         * [BetaManagedAgentsScheduleParams] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun schedule(schedule: JsonField<BetaManagedAgentsScheduleParams>) = apply {
            body.schedule(schedule)
        }

        /**
         * Vault IDs. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 50.
         */
        fun vaultIds(vaultIds: List<String>?) = apply { body.vaultIds(vaultIds) }

        /** Alias for calling [Builder.vaultIds] with `vaultIds.orElse(null)`. */
        fun vaultIds(vaultIds: Optional<List<String>>) = vaultIds(vaultIds.getOrNull())

        /**
         * Sets [Builder.vaultIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.vaultIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun vaultIds(vaultIds: JsonField<List<String>>) = apply { body.vaultIds(vaultIds) }

        /**
         * Adds a single [String] to [vaultIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addVaultId(vaultId: String) = apply { body.addVaultId(vaultId) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [DeploymentUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): DeploymentUpdateParams =
            DeploymentUpdateParams(
                deploymentId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> deploymentId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    /**
     * Request parameters for updating a `deployment`. Omit a field to preserve its current value.
     */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val agent: JsonField<Agent>,
        private val description: JsonField<String>,
        private val environmentId: JsonField<String>,
        private val initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>>,
        private val metadata: JsonField<Metadata>,
        private val name: JsonField<String>,
        private val resources: JsonField<List<Resource>>,
        private val schedule: JsonField<BetaManagedAgentsScheduleParams>,
        private val vaultIds: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("agent") @ExcludeMissing agent: JsonField<Agent> = JsonMissing.of(),
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("environment_id")
            @ExcludeMissing
            environmentId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("initial_events")
            @ExcludeMissing
            initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>> =
                JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("resources")
            @ExcludeMissing
            resources: JsonField<List<Resource>> = JsonMissing.of(),
            @JsonProperty("schedule")
            @ExcludeMissing
            schedule: JsonField<BetaManagedAgentsScheduleParams> = JsonMissing.of(),
            @JsonProperty("vault_ids")
            @ExcludeMissing
            vaultIds: JsonField<List<String>> = JsonMissing.of(),
        ) : this(
            agent,
            description,
            environmentId,
            initialEvents,
            metadata,
            name,
            resources,
            schedule,
            vaultIds,
            mutableMapOf(),
        )

        /**
         * Agent to deploy. Accepts the `agent` ID string, which re-pins to the latest version, or
         * an `agent` object with both id and version specified. Omit to preserve. Cannot be
         * cleared.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun agent(): Optional<Agent> = agent.getOptional("agent")

        /**
         * Description. Omit to preserve; send empty string or null to clear.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun description(): Optional<String> = description.getOptional("description")

        /**
         * ID of the `environment` where sessions run. Omit to preserve. Cannot be cleared.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun environmentId(): Optional<String> = environmentId.getOptional("environment_id")

        /**
         * Initial events. Full replacement. Omit to preserve. Cannot be cleared. At least 1,
         * maximum 50.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun initialEvents(): Optional<List<BetaManagedAgentsDeploymentInitialEventParams>> =
            initialEvents.getOptional("initial_events")

        /**
         * Metadata patch. Set a key to a string to upsert it, or to null to delete it. Omit the
         * field to preserve. The stored bag is limited to 16 keys (up to 64 chars each) with values
         * up to 512 chars.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /**
         * Human-readable name. Must be non-empty. Omit to preserve. Cannot be cleared.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * Session resources. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 500.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun resources(): Optional<List<Resource>> = resources.getOptional("resources")

        /**
         * 5-field POSIX cron schedule. Literal wall-clock matching in the configured timezone.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun schedule(): Optional<BetaManagedAgentsScheduleParams> = schedule.getOptional("schedule")

        /**
         * Vault IDs. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 50.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun vaultIds(): Optional<List<String>> = vaultIds.getOptional("vault_ids")

        /**
         * Returns the raw JSON value of [agent].
         *
         * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("agent") @ExcludeMissing fun _agent(): JsonField<Agent> = agent

        /**
         * Returns the raw JSON value of [description].
         *
         * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        /**
         * Returns the raw JSON value of [environmentId].
         *
         * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("environment_id")
        @ExcludeMissing
        fun _environmentId(): JsonField<String> = environmentId

        /**
         * Returns the raw JSON value of [initialEvents].
         *
         * Unlike [initialEvents], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("initial_events")
        @ExcludeMissing
        fun _initialEvents(): JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>> =
            initialEvents

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
         * Returns the raw JSON value of [resources].
         *
         * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("resources")
        @ExcludeMissing
        fun _resources(): JsonField<List<Resource>> = resources

        /**
         * Returns the raw JSON value of [schedule].
         *
         * Unlike [schedule], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("schedule")
        @ExcludeMissing
        fun _schedule(): JsonField<BetaManagedAgentsScheduleParams> = schedule

        /**
         * Returns the raw JSON value of [vaultIds].
         *
         * Unlike [vaultIds], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("vault_ids")
        @ExcludeMissing
        fun _vaultIds(): JsonField<List<String>> = vaultIds

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var agent: JsonField<Agent> = JsonMissing.of()
            private var description: JsonField<String> = JsonMissing.of()
            private var environmentId: JsonField<String> = JsonMissing.of()
            private var initialEvents:
                JsonField<MutableList<BetaManagedAgentsDeploymentInitialEventParams>>? =
                null
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var resources: JsonField<MutableList<Resource>>? = null
            private var schedule: JsonField<BetaManagedAgentsScheduleParams> = JsonMissing.of()
            private var vaultIds: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                agent = body.agent
                description = body.description
                environmentId = body.environmentId
                initialEvents =
                    body.initialEvents.map { it.toMutableList() }.takeUnless { it.isMissing() }
                metadata = body.metadata
                name = body.name
                resources = body.resources.map { it.toMutableList() }.takeUnless { it.isMissing() }
                schedule = body.schedule
                vaultIds = body.vaultIds.map { it.toMutableList() }.takeUnless { it.isMissing() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Agent to deploy. Accepts the `agent` ID string, which re-pins to the latest version,
             * or an `agent` object with both id and version specified. Omit to preserve. Cannot be
             * cleared.
             */
            fun agent(agent: Agent) = agent(JsonField.of(agent))

            /**
             * Sets [Builder.agent] to an arbitrary JSON value.
             *
             * You should usually call [Builder.agent] with a well-typed [Agent] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun agent(agent: JsonField<Agent>) = apply { this.agent = agent }

            /** Alias for calling [agent] with `Agent.ofString(string)`. */
            fun agent(string: String) = agent(Agent.ofString(string))

            /**
             * Alias for calling [agent] with
             * `Agent.ofBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)`.
             */
            fun agent(betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams) =
                agent(Agent.ofBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams))

            /** Description. Omit to preserve; send empty string or null to clear. */
            fun description(description: String?) = description(JsonField.ofNullable(description))

            /** Alias for calling [Builder.description] with `description.orElse(null)`. */
            fun description(description: Optional<String>) = description(description.getOrNull())

            /**
             * Sets [Builder.description] to an arbitrary JSON value.
             *
             * You should usually call [Builder.description] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun description(description: JsonField<String>) = apply {
                this.description = description
            }

            /** ID of the `environment` where sessions run. Omit to preserve. Cannot be cleared. */
            fun environmentId(environmentId: String) = environmentId(JsonField.of(environmentId))

            /**
             * Sets [Builder.environmentId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.environmentId] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun environmentId(environmentId: JsonField<String>) = apply {
                this.environmentId = environmentId
            }

            /**
             * Initial events. Full replacement. Omit to preserve. Cannot be cleared. At least 1,
             * maximum 50.
             */
            fun initialEvents(initialEvents: List<BetaManagedAgentsDeploymentInitialEventParams>) =
                initialEvents(JsonField.of(initialEvents))

            /**
             * Sets [Builder.initialEvents] to an arbitrary JSON value.
             *
             * You should usually call [Builder.initialEvents] with a well-typed
             * `List<BetaManagedAgentsDeploymentInitialEventParams>` value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun initialEvents(
                initialEvents: JsonField<List<BetaManagedAgentsDeploymentInitialEventParams>>
            ) = apply { this.initialEvents = initialEvents.map { it.toMutableList() } }

            /**
             * Adds a single [BetaManagedAgentsDeploymentInitialEventParams] to [initialEvents].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addInitialEvent(initialEvent: BetaManagedAgentsDeploymentInitialEventParams) =
                apply {
                    initialEvents =
                        (initialEvents ?: JsonField.of(mutableListOf())).also {
                            checkKnown("initialEvents", it).add(initialEvent)
                        }
                }

            /**
             * Alias for calling [addInitialEvent] with
             * `BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(userMessage)`.
             */
            fun addInitialEvent(userMessage: BetaManagedAgentsUserMessageEventParams) =
                addInitialEvent(
                    BetaManagedAgentsDeploymentInitialEventParams.ofUserMessage(userMessage)
                )

            /**
             * Alias for calling [addInitialEvent] with the following:
             * ```java
             * BetaManagedAgentsUserMessageEventParams.builder()
             *     .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
             *     .content(content)
             *     .build()
             * ```
             */
            fun addUserMessageInitialEvent(
                content: List<BetaManagedAgentsUserMessageEventParams.Content>
            ) =
                addInitialEvent(
                    BetaManagedAgentsUserMessageEventParams.builder()
                        .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                        .content(content)
                        .build()
                )

            /**
             * Alias for calling [addInitialEvent] with
             * `BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(userDefineOutcome)`.
             */
            fun addInitialEvent(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) =
                addInitialEvent(
                    BetaManagedAgentsDeploymentInitialEventParams.ofUserDefineOutcome(
                        userDefineOutcome
                    )
                )

            /**
             * Alias for calling [addInitialEvent] with
             * `BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(systemMessage)`.
             */
            fun addInitialEvent(systemMessage: BetaManagedAgentsSystemMessageEventParams) =
                addInitialEvent(
                    BetaManagedAgentsDeploymentInitialEventParams.ofSystemMessage(systemMessage)
                )

            /**
             * Alias for calling [addInitialEvent] with the following:
             * ```java
             * BetaManagedAgentsSystemMessageEventParams.builder()
             *     .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
             *     .content(content)
             *     .build()
             * ```
             */
            fun addSystemMessageInitialEvent(content: List<BetaManagedAgentsSystemContentBlock>) =
                addInitialEvent(
                    BetaManagedAgentsSystemMessageEventParams.builder()
                        .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
                        .content(content)
                        .build()
                )

            /**
             * Metadata patch. Set a key to a string to upsert it, or to null to delete it. Omit the
             * field to preserve. The stored bag is limited to 16 keys (up to 64 chars each) with
             * values up to 512 chars.
             */
            fun metadata(metadata: Metadata?) = metadata(JsonField.ofNullable(metadata))

            /** Alias for calling [Builder.metadata] with `metadata.orElse(null)`. */
            fun metadata(metadata: Optional<Metadata>) = metadata(metadata.getOrNull())

            /**
             * Sets [Builder.metadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadata] with a well-typed [Metadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /** Human-readable name. Must be non-empty. Omit to preserve. Cannot be cleared. */
            fun name(name: String) = name(JsonField.of(name))

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

            /**
             * Session resources. Full replacement. Omit to preserve; send empty array or null to
             * clear. Maximum 500.
             */
            fun resources(resources: List<Resource>?) = resources(JsonField.ofNullable(resources))

            /** Alias for calling [Builder.resources] with `resources.orElse(null)`. */
            fun resources(resources: Optional<List<Resource>>) = resources(resources.getOrNull())

            /**
             * Sets [Builder.resources] to an arbitrary JSON value.
             *
             * You should usually call [Builder.resources] with a well-typed `List<Resource>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun resources(resources: JsonField<List<Resource>>) = apply {
                this.resources = resources.map { it.toMutableList() }
            }

            /**
             * Adds a single [Resource] to [resources].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addResource(resource: Resource) = apply {
                resources =
                    (resources ?: JsonField.of(mutableListOf())).also {
                        checkKnown("resources", it).add(resource)
                    }
            }

            /**
             * Alias for calling [addResource] with `Resource.ofGitHubRepository(githubRepository)`.
             */
            fun addResource(githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams) =
                addResource(Resource.ofGitHubRepository(githubRepository))

            /** Alias for calling [addResource] with `Resource.ofFile(file)`. */
            fun addResource(file: BetaManagedAgentsFileResourceParams) =
                addResource(Resource.ofFile(file))

            /**
             * Alias for calling [addResource] with the following:
             * ```java
             * BetaManagedAgentsFileResourceParams.builder()
             *     .type(BetaManagedAgentsFileResourceParams.Type.FILE)
             *     .fileId(fileId)
             *     .build()
             * ```
             */
            fun addFileResource(fileId: String) =
                addResource(
                    BetaManagedAgentsFileResourceParams.builder()
                        .type(BetaManagedAgentsFileResourceParams.Type.FILE)
                        .fileId(fileId)
                        .build()
                )

            /** Alias for calling [addResource] with `Resource.ofMemoryStore(memoryStore)`. */
            fun addResource(memoryStore: BetaManagedAgentsMemoryStoreResourceParam) =
                addResource(Resource.ofMemoryStore(memoryStore))

            /**
             * Alias for calling [addResource] with the following:
             * ```java
             * BetaManagedAgentsMemoryStoreResourceParam.builder()
             *     .type(BetaManagedAgentsMemoryStoreResourceParam.Type.MEMORY_STORE)
             *     .memoryStoreId(memoryStoreId)
             *     .build()
             * ```
             */
            fun addMemoryStoreResource(memoryStoreId: String) =
                addResource(
                    BetaManagedAgentsMemoryStoreResourceParam.builder()
                        .type(BetaManagedAgentsMemoryStoreResourceParam.Type.MEMORY_STORE)
                        .memoryStoreId(memoryStoreId)
                        .build()
                )

            /**
             * 5-field POSIX cron schedule. Literal wall-clock matching in the configured timezone.
             */
            fun schedule(schedule: BetaManagedAgentsScheduleParams?) =
                schedule(JsonField.ofNullable(schedule))

            /** Alias for calling [Builder.schedule] with `schedule.orElse(null)`. */
            fun schedule(schedule: Optional<BetaManagedAgentsScheduleParams>) =
                schedule(schedule.getOrNull())

            /**
             * Sets [Builder.schedule] to an arbitrary JSON value.
             *
             * You should usually call [Builder.schedule] with a well-typed
             * [BetaManagedAgentsScheduleParams] value instead. This method is primarily for setting
             * the field to an undocumented or not yet supported value.
             */
            fun schedule(schedule: JsonField<BetaManagedAgentsScheduleParams>) = apply {
                this.schedule = schedule
            }

            /**
             * Vault IDs. Full replacement. Omit to preserve; send empty array or null to clear.
             * Maximum 50.
             */
            fun vaultIds(vaultIds: List<String>?) = vaultIds(JsonField.ofNullable(vaultIds))

            /** Alias for calling [Builder.vaultIds] with `vaultIds.orElse(null)`. */
            fun vaultIds(vaultIds: Optional<List<String>>) = vaultIds(vaultIds.getOrNull())

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Body =
                Body(
                    agent,
                    description,
                    environmentId,
                    (initialEvents ?: JsonMissing.of()).map { it.toImmutable() },
                    metadata,
                    name,
                    (resources ?: JsonMissing.of()).map { it.toImmutable() },
                    schedule,
                    (vaultIds ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            agent().ifPresent { it.validate() }
            description()
            environmentId()
            initialEvents().ifPresent { it.forEach { it.validate() } }
            metadata().ifPresent { it.validate() }
            name()
            resources().ifPresent { it.forEach { it.validate() } }
            schedule().ifPresent { it.validate() }
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (agent.asKnown().getOrNull()?.validity() ?: 0) +
                (if (description.asKnown().isPresent) 1 else 0) +
                (if (environmentId.asKnown().isPresent) 1 else 0) +
                (initialEvents.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (resources.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (schedule.asKnown().getOrNull()?.validity() ?: 0) +
                (vaultIds.asKnown().getOrNull()?.size ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                agent == other.agent &&
                description == other.description &&
                environmentId == other.environmentId &&
                initialEvents == other.initialEvents &&
                metadata == other.metadata &&
                name == other.name &&
                resources == other.resources &&
                schedule == other.schedule &&
                vaultIds == other.vaultIds &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                agent,
                description,
                environmentId,
                initialEvents,
                metadata,
                name,
                resources,
                schedule,
                vaultIds,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{agent=$agent, description=$description, environmentId=$environmentId, initialEvents=$initialEvents, metadata=$metadata, name=$name, resources=$resources, schedule=$schedule, vaultIds=$vaultIds, additionalProperties=$additionalProperties}"
    }

    /**
     * Agent to deploy. Accepts the `agent` ID string, which re-pins to the latest version, or an
     * `agent` object with both id and version specified. Omit to preserve. Cannot be cleared.
     */
    @JsonDeserialize(using = Agent.Deserializer::class)
    @JsonSerialize(using = Agent.Serializer::class)
    class Agent
    private constructor(
        private val string: String? = null,
        private val betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        fun betaManagedAgentsAgentParams(): Optional<BetaManagedAgentsAgentParams> =
            Optional.ofNullable(betaManagedAgentsAgentParams)

        fun isString(): Boolean = string != null

        fun isBetaManagedAgentsAgentParams(): Boolean = betaManagedAgentsAgentParams != null

        fun asString(): String = string.getOrThrow("string")

        /**
         * Specification for an Agent. Provide a specific `version` or use the short-form
         * `agent="agent_id"` for the most recent version
         */
        fun asBetaManagedAgentsAgentParams(): BetaManagedAgentsAgentParams =
            betaManagedAgentsAgentParams.getOrThrow("betaManagedAgentsAgentParams")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = agent.accept(new Agent.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitString(String string) {
         *         return Optional.of(string.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                betaManagedAgentsAgentParams != null ->
                    visitor.visitBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)
                else -> visitor.unknown(_json)
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
        fun validate(): Agent = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitBetaManagedAgentsAgentParams(
                        betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
                    ) {
                        betaManagedAgentsAgentParams.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitString(string: String) = 1

                    override fun visitBetaManagedAgentsAgentParams(
                        betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
                    ) = betaManagedAgentsAgentParams.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Agent &&
                string == other.string &&
                betaManagedAgentsAgentParams == other.betaManagedAgentsAgentParams
        }

        override fun hashCode(): Int = Objects.hash(string, betaManagedAgentsAgentParams)

        override fun toString(): String =
            when {
                string != null -> "Agent{string=$string}"
                betaManagedAgentsAgentParams != null ->
                    "Agent{betaManagedAgentsAgentParams=$betaManagedAgentsAgentParams}"
                _json != null -> "Agent{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Agent")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Agent(string = string)

            /**
             * Specification for an Agent. Provide a specific `version` or use the short-form
             * `agent="agent_id"` for the most recent version
             */
            @JvmStatic
            fun ofBetaManagedAgentsAgentParams(
                betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
            ) = Agent(betaManagedAgentsAgentParams = betaManagedAgentsAgentParams)
        }

        /** An interface that defines how to map each variant of [Agent] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitString(string: String): T

            /**
             * Specification for an Agent. Provide a specific `version` or use the short-form
             * `agent="agent_id"` for the most recent version
             */
            fun visitBetaManagedAgentsAgentParams(
                betaManagedAgentsAgentParams: BetaManagedAgentsAgentParams
            ): T

            /**
             * Maps an unknown variant of [Agent] to a value of type [T].
             *
             * An instance of [Agent] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Agent: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Agent>(Agent::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Agent {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsAgentParams>())
                                ?.let { Agent(betaManagedAgentsAgentParams = it, _json = json) },
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Agent(string = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Agent(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Agent>(Agent::class) {

            override fun serialize(
                value: Agent,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaManagedAgentsAgentParams != null ->
                        generator.writeObject(value.betaManagedAgentsAgentParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Agent")
                }
            }
        }
    }

    /**
     * Metadata patch. Set a key to a string to upsert it, or to null to delete it. Omit the field
     * to preserve. The stored bag is limited to 16 keys (up to 64 chars each) with values up to 512
     * chars.
     */
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

    /** Union of resources that can be mounted into a session. */
    @JsonDeserialize(using = Resource.Deserializer::class)
    @JsonSerialize(using = Resource.Serializer::class)
    class Resource
    private constructor(
        private val githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams? = null,
        private val file: BetaManagedAgentsFileResourceParams? = null,
        private val memoryStore: BetaManagedAgentsMemoryStoreResourceParam? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Mount a GitHub repository into the session's container. */
        fun githubRepository(): Optional<BetaManagedAgentsGitHubRepositoryResourceParams> =
            Optional.ofNullable(githubRepository)

        /** Mount a file uploaded via the Files API into the session. */
        fun file(): Optional<BetaManagedAgentsFileResourceParams> = Optional.ofNullable(file)

        /** Parameters for attaching a memory store to an agent session. */
        fun memoryStore(): Optional<BetaManagedAgentsMemoryStoreResourceParam> =
            Optional.ofNullable(memoryStore)

        fun isGitHubRepository(): Boolean = githubRepository != null

        fun isFile(): Boolean = file != null

        fun isMemoryStore(): Boolean = memoryStore != null

        /** Mount a GitHub repository into the session's container. */
        fun asGitHubRepository(): BetaManagedAgentsGitHubRepositoryResourceParams =
            githubRepository.getOrThrow("githubRepository")

        /** Mount a file uploaded via the Files API into the session. */
        fun asFile(): BetaManagedAgentsFileResourceParams = file.getOrThrow("file")

        /** Parameters for attaching a memory store to an agent session. */
        fun asMemoryStore(): BetaManagedAgentsMemoryStoreResourceParam =
            memoryStore.getOrThrow("memoryStore")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = resource.accept(new Resource.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitGitHubRepository(BetaManagedAgentsGitHubRepositoryResourceParams githubRepository) {
         *         return Optional.of(githubRepository.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                githubRepository != null -> visitor.visitGitHubRepository(githubRepository)
                file != null -> visitor.visitFile(file)
                memoryStore != null -> visitor.visitMemoryStore(memoryStore)
                else -> visitor.unknown(_json)
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
        fun validate(): Resource = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitGitHubRepository(
                        githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams
                    ) {
                        githubRepository.validate()
                    }

                    override fun visitFile(file: BetaManagedAgentsFileResourceParams) {
                        file.validate()
                    }

                    override fun visitMemoryStore(
                        memoryStore: BetaManagedAgentsMemoryStoreResourceParam
                    ) {
                        memoryStore.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitGitHubRepository(
                        githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams
                    ) = githubRepository.validity()

                    override fun visitFile(file: BetaManagedAgentsFileResourceParams) =
                        file.validity()

                    override fun visitMemoryStore(
                        memoryStore: BetaManagedAgentsMemoryStoreResourceParam
                    ) = memoryStore.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Resource &&
                githubRepository == other.githubRepository &&
                file == other.file &&
                memoryStore == other.memoryStore
        }

        override fun hashCode(): Int = Objects.hash(githubRepository, file, memoryStore)

        override fun toString(): String =
            when {
                githubRepository != null -> "Resource{githubRepository=$githubRepository}"
                file != null -> "Resource{file=$file}"
                memoryStore != null -> "Resource{memoryStore=$memoryStore}"
                _json != null -> "Resource{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Resource")
            }

        companion object {

            /** Mount a GitHub repository into the session's container. */
            @JvmStatic
            fun ofGitHubRepository(
                githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams
            ) = Resource(githubRepository = githubRepository)

            /** Mount a file uploaded via the Files API into the session. */
            @JvmStatic fun ofFile(file: BetaManagedAgentsFileResourceParams) = Resource(file = file)

            /** Parameters for attaching a memory store to an agent session. */
            @JvmStatic
            fun ofMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResourceParam) =
                Resource(memoryStore = memoryStore)
        }

        /**
         * An interface that defines how to map each variant of [Resource] to a value of type [T].
         */
        interface Visitor<out T> {

            /** Mount a GitHub repository into the session's container. */
            fun visitGitHubRepository(
                githubRepository: BetaManagedAgentsGitHubRepositoryResourceParams
            ): T

            /** Mount a file uploaded via the Files API into the session. */
            fun visitFile(file: BetaManagedAgentsFileResourceParams): T

            /** Parameters for attaching a memory store to an agent session. */
            fun visitMemoryStore(memoryStore: BetaManagedAgentsMemoryStoreResourceParam): T

            /**
             * Maps an unknown variant of [Resource] to a value of type [T].
             *
             * An instance of [Resource] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Resource: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Resource>(Resource::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Resource {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "github_repository" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResourceParams>(),
                            )
                            ?.let { Resource(githubRepository = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                    "file" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsFileResourceParams>(),
                            )
                            ?.let { Resource(file = it, _json = json) } ?: Resource(_json = json)
                    }
                    "memory_store" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMemoryStoreResourceParam>(),
                            )
                            ?.let { Resource(memoryStore = it, _json = json) }
                            ?: Resource(_json = json)
                    }
                }

                return Resource(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Resource>(Resource::class) {

            override fun serialize(
                value: Resource,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.githubRepository != null -> generator.writeObject(value.githubRepository)
                    value.file != null -> generator.writeObject(value.file)
                    value.memoryStore != null -> generator.writeObject(value.memoryStore)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Resource")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is DeploymentUpdateParams &&
            deploymentId == other.deploymentId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(deploymentId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "DeploymentUpdateParams{deploymentId=$deploymentId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
