// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
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

/** Create Session */
class SessionCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Agent identifier. Accepts the `agent` ID string, which pins the latest version for the
     * session, or an `agent` object with both id and version specified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): Agent = body.agent()

    /**
     * ID of the `environment` defining the container configuration for this session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun environmentId(): String = body.environmentId()

    /**
     * Arbitrary key-value metadata attached to the session. Maximum 16 pairs, keys up to 64 chars,
     * values up to 512 chars.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun metadata(): Optional<Metadata> = body.metadata()

    /**
     * Resources (e.g. repositories, files) to mount into the session's container.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resources(): Optional<List<Resource>> = body.resources()

    /**
     * Human-readable session title.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun title(): Optional<String> = body.title()

    /**
     * Vault IDs for stored credentials the agent can use during the session.
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
     * Returns the raw JSON value of [environmentId].
     *
     * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _environmentId(): JsonField<String> = body._environmentId()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    /**
     * Returns the raw JSON value of [resources].
     *
     * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _resources(): JsonField<List<Resource>> = body._resources()

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _title(): JsonField<String> = body._title()

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

        /**
         * Returns a mutable builder for constructing an instance of [SessionCreateParams].
         *
         * The following fields are required:
         * ```java
         * .agent()
         * .environmentId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SessionCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(sessionCreateParams: SessionCreateParams) = apply {
            betas = sessionCreateParams.betas?.toMutableList()
            body = sessionCreateParams.body.toBuilder()
            additionalHeaders = sessionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = sessionCreateParams.additionalQueryParams.toBuilder()
        }

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
         * - [environmentId]
         * - [metadata]
         * - [resources]
         * - [title]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Agent identifier. Accepts the `agent` ID string, which pins the latest version for the
         * session, or an `agent` object with both id and version specified.
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

        /** ID of the `environment` defining the container configuration for this session. */
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
         * Arbitrary key-value metadata attached to the session. Maximum 16 pairs, keys up to 64
         * chars, values up to 512 chars.
         */
        fun metadata(metadata: Metadata) = apply { body.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        /** Resources (e.g. repositories, files) to mount into the session's container. */
        fun resources(resources: List<Resource>) = apply { body.resources(resources) }

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

        /** Human-readable session title. */
        fun title(title: String?) = apply { body.title(title) }

        /** Alias for calling [Builder.title] with `title.orElse(null)`. */
        fun title(title: Optional<String>) = title(title.getOrNull())

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { body.title(title) }

        /** Vault IDs for stored credentials the agent can use during the session. */
        fun vaultIds(vaultIds: List<String>) = apply { body.vaultIds(vaultIds) }

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
         * Returns an immutable instance of [SessionCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .agent()
         * .environmentId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SessionCreateParams =
            SessionCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    /** Request parameters for creating a `session`. */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val agent: JsonField<Agent>,
        private val environmentId: JsonField<String>,
        private val metadata: JsonField<Metadata>,
        private val resources: JsonField<List<Resource>>,
        private val title: JsonField<String>,
        private val vaultIds: JsonField<List<String>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("agent") @ExcludeMissing agent: JsonField<Agent> = JsonMissing.of(),
            @JsonProperty("environment_id")
            @ExcludeMissing
            environmentId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("resources")
            @ExcludeMissing
            resources: JsonField<List<Resource>> = JsonMissing.of(),
            @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
            @JsonProperty("vault_ids")
            @ExcludeMissing
            vaultIds: JsonField<List<String>> = JsonMissing.of(),
        ) : this(agent, environmentId, metadata, resources, title, vaultIds, mutableMapOf())

        /**
         * Agent identifier. Accepts the `agent` ID string, which pins the latest version for the
         * session, or an `agent` object with both id and version specified.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun agent(): Agent = agent.getRequired("agent")

        /**
         * ID of the `environment` defining the container configuration for this session.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun environmentId(): String = environmentId.getRequired("environment_id")

        /**
         * Arbitrary key-value metadata attached to the session. Maximum 16 pairs, keys up to 64
         * chars, values up to 512 chars.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /**
         * Resources (e.g. repositories, files) to mount into the session's container.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun resources(): Optional<List<Resource>> = resources.getOptional("resources")

        /**
         * Human-readable session title.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun title(): Optional<String> = title.getOptional("title")

        /**
         * Vault IDs for stored credentials the agent can use during the session.
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
         * Returns the raw JSON value of [environmentId].
         *
         * Unlike [environmentId], this method doesn't throw if the JSON field has an unexpected
         * type.
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
         * Returns the raw JSON value of [resources].
         *
         * Unlike [resources], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("resources")
        @ExcludeMissing
        fun _resources(): JsonField<List<Resource>> = resources

        /**
         * Returns the raw JSON value of [title].
         *
         * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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

            /**
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .agent()
             * .environmentId()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var agent: JsonField<Agent>? = null
            private var environmentId: JsonField<String>? = null
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var resources: JsonField<MutableList<Resource>>? = null
            private var title: JsonField<String> = JsonMissing.of()
            private var vaultIds: JsonField<MutableList<String>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                agent = body.agent
                environmentId = body.environmentId
                metadata = body.metadata
                resources = body.resources.map { it.toMutableList() }
                title = body.title
                vaultIds = body.vaultIds.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Agent identifier. Accepts the `agent` ID string, which pins the latest version for
             * the session, or an `agent` object with both id and version specified.
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

            /** ID of the `environment` defining the container configuration for this session. */
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
             * Arbitrary key-value metadata attached to the session. Maximum 16 pairs, keys up to 64
             * chars, values up to 512 chars.
             */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /**
             * Sets [Builder.metadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadata] with a well-typed [Metadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            /** Resources (e.g. repositories, files) to mount into the session's container. */
            fun resources(resources: List<Resource>) = resources(JsonField.of(resources))

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

            /** Human-readable session title. */
            fun title(title: String?) = title(JsonField.ofNullable(title))

            /** Alias for calling [Builder.title] with `title.orElse(null)`. */
            fun title(title: Optional<String>) = title(title.getOrNull())

            /**
             * Sets [Builder.title] to an arbitrary JSON value.
             *
             * You should usually call [Builder.title] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun title(title: JsonField<String>) = apply { this.title = title }

            /** Vault IDs for stored credentials the agent can use during the session. */
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .agent()
             * .environmentId()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("agent", agent),
                    checkRequired("environmentId", environmentId),
                    metadata,
                    (resources ?: JsonMissing.of()).map { it.toImmutable() },
                    title,
                    (vaultIds ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            agent().validate()
            environmentId()
            metadata().ifPresent { it.validate() }
            resources().ifPresent { it.forEach { it.validate() } }
            title()
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
                (if (environmentId.asKnown().isPresent) 1 else 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                (resources.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (if (title.asKnown().isPresent) 1 else 0) +
                (vaultIds.asKnown().getOrNull()?.size ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                agent == other.agent &&
                environmentId == other.environmentId &&
                metadata == other.metadata &&
                resources == other.resources &&
                title == other.title &&
                vaultIds == other.vaultIds &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                agent,
                environmentId,
                metadata,
                resources,
                title,
                vaultIds,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{agent=$agent, environmentId=$environmentId, metadata=$metadata, resources=$resources, title=$title, vaultIds=$vaultIds, additionalProperties=$additionalProperties}"
    }

    /**
     * Agent identifier. Accepts the `agent` ID string, which pins the latest version for the
     * session, or an `agent` object with both id and version specified.
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

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                string != null -> visitor.visitString(string)
                betaManagedAgentsAgentParams != null ->
                    visitor.visitBetaManagedAgentsAgentParams(betaManagedAgentsAgentParams)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

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
     * Arbitrary key-value metadata attached to the session. Maximum 16 pairs, keys up to 64 chars,
     * values up to 512 chars.
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
        private val _json: JsonValue? = null,
    ) {

        /** Mount a GitHub repository into the session's container. */
        fun githubRepository(): Optional<BetaManagedAgentsGitHubRepositoryResourceParams> =
            Optional.ofNullable(githubRepository)

        /** Mount a file uploaded via the Files API into the session. */
        fun file(): Optional<BetaManagedAgentsFileResourceParams> = Optional.ofNullable(file)

        fun isGitHubRepository(): Boolean = githubRepository != null

        fun isFile(): Boolean = file != null

        /** Mount a GitHub repository into the session's container. */
        fun asGitHubRepository(): BetaManagedAgentsGitHubRepositoryResourceParams =
            githubRepository.getOrThrow("githubRepository")

        /** Mount a file uploaded via the Files API into the session. */
        fun asFile(): BetaManagedAgentsFileResourceParams = file.getOrThrow("file")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                githubRepository != null -> visitor.visitGitHubRepository(githubRepository)
                file != null -> visitor.visitFile(file)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

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

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Resource &&
                githubRepository == other.githubRepository &&
                file == other.file
        }

        override fun hashCode(): Int = Objects.hash(githubRepository, file)

        override fun toString(): String =
            when {
                githubRepository != null -> "Resource{githubRepository=$githubRepository}"
                file != null -> "Resource{file=$file}"
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

        return other is SessionCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "SessionCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
