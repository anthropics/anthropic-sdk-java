// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

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

/** Update Agent */
class AgentUpdateParams
private constructor(
    private val agentId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun agentId(): Optional<String> = Optional.ofNullable(agentId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The agent's current version, used to prevent concurrent overwrites. Obtain this value from a
     * create or retrieve response. The request fails if this does not match the server's current
     * version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun version(): Int = body.version()

    /**
     * Description. Up to 2048 characters. Omit to preserve; send empty string or null to clear.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = body.description()

    /**
     * MCP servers. Full replacement. Omit to preserve; send empty array or null to clear. Names
     * must be unique. Maximum 20.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpServers(): Optional<List<BetaManagedAgentsUrlMcpServerParams>> = body.mcpServers()

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
     * Model identifier. Accepts the
     * [model string](https://platform.claude.com/docs/en/about-claude/models/overview#latest-models-comparison),
     * e.g. `claude-opus-4-6`, or a `model_config` object for additional configuration control. Omit
     * to preserve. Cannot be cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<Model> = body.model()

    /**
     * Human-readable name. 1-256 characters. Omit to preserve. Cannot be cleared.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = body.name()

    /**
     * Skills. Full replacement. Omit to preserve; send empty array or null to clear. Maximum 20.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun skills(): Optional<List<BetaManagedAgentsSkillParams>> = body.skills()

    /**
     * System prompt. Up to 100,000 characters. Omit to preserve; send empty string or null to
     * clear.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun system(): Optional<String> = body.system()

    /**
     * Tool configurations available to the agent. Full replacement. Omit to preserve; send empty
     * array or null to clear. Maximum of 128 tools across all toolsets allowed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tools(): Optional<List<Tool>> = body.tools()

    /**
     * Returns the raw JSON value of [version].
     *
     * Unlike [version], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _version(): JsonField<Int> = body._version()

    /**
     * Returns the raw JSON value of [description].
     *
     * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _description(): JsonField<String> = body._description()

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _mcpServers(): JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = body._mcpServers()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _model(): JsonField<Model> = body._model()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    /**
     * Returns the raw JSON value of [skills].
     *
     * Unlike [skills], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _skills(): JsonField<List<BetaManagedAgentsSkillParams>> = body._skills()

    /**
     * Returns the raw JSON value of [system].
     *
     * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _system(): JsonField<String> = body._system()

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _tools(): JsonField<List<Tool>> = body._tools()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [AgentUpdateParams].
         *
         * The following fields are required:
         * ```java
         * .version()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [AgentUpdateParams]. */
    class Builder internal constructor() {

        private var agentId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(agentUpdateParams: AgentUpdateParams) = apply {
            agentId = agentUpdateParams.agentId
            betas = agentUpdateParams.betas?.toMutableList()
            body = agentUpdateParams.body.toBuilder()
            additionalHeaders = agentUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = agentUpdateParams.additionalQueryParams.toBuilder()
        }

        fun agentId(agentId: String?) = apply { this.agentId = agentId }

        /** Alias for calling [Builder.agentId] with `agentId.orElse(null)`. */
        fun agentId(agentId: Optional<String>) = agentId(agentId.getOrNull())

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
         * - [version]
         * - [description]
         * - [mcpServers]
         * - [metadata]
         * - [model]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The agent's current version, used to prevent concurrent overwrites. Obtain this value
         * from a create or retrieve response. The request fails if this does not match the server's
         * current version.
         */
        fun version(version: Int) = apply { body.version(version) }

        /**
         * Sets [Builder.version] to an arbitrary JSON value.
         *
         * You should usually call [Builder.version] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun version(version: JsonField<Int>) = apply { body.version(version) }

        /**
         * Description. Up to 2048 characters. Omit to preserve; send empty string or null to clear.
         */
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

        /**
         * MCP servers. Full replacement. Omit to preserve; send empty array or null to clear. Names
         * must be unique. Maximum 20.
         */
        fun mcpServers(mcpServers: List<BetaManagedAgentsUrlMcpServerParams>?) = apply {
            body.mcpServers(mcpServers)
        }

        /** Alias for calling [Builder.mcpServers] with `mcpServers.orElse(null)`. */
        fun mcpServers(mcpServers: Optional<List<BetaManagedAgentsUrlMcpServerParams>>) =
            mcpServers(mcpServers.getOrNull())

        /**
         * Sets [Builder.mcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServers] with a well-typed
         * `List<BetaManagedAgentsUrlMcpServerParams>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun mcpServers(mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>) = apply {
            body.mcpServers(mcpServers)
        }

        /**
         * Adds a single [BetaManagedAgentsUrlMcpServerParams] to [mcpServers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMcpServer(mcpServer: BetaManagedAgentsUrlMcpServerParams) = apply {
            body.addMcpServer(mcpServer)
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

        /**
         * Model identifier. Accepts the
         * [model string](https://platform.claude.com/docs/en/about-claude/models/overview#latest-models-comparison),
         * e.g. `claude-opus-4-6`, or a `model_config` object for additional configuration control.
         * Omit to preserve. Cannot be cleared.
         */
        fun model(model: Model) = apply { body.model(model) }

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<Model>) = apply { body.model(model) }

        /** Alias for calling [model] with `Model.ofBetaManagedAgents(betaManagedAgents)`. */
        fun model(betaManagedAgents: BetaManagedAgentsModel) = apply {
            body.model(betaManagedAgents)
        }

        /**
         * Alias for calling [model] with
         * `Model.ofBetaManagedAgentsModelConfigParams(betaManagedAgentsModelConfigParams)`.
         */
        fun model(betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams) = apply {
            body.model(betaManagedAgentsModelConfigParams)
        }

        /** Human-readable name. 1-256 characters. Omit to preserve. Cannot be cleared. */
        fun name(name: String) = apply { body.name(name) }

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        /**
         * Skills. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 20.
         */
        fun skills(skills: List<BetaManagedAgentsSkillParams>?) = apply { body.skills(skills) }

        /** Alias for calling [Builder.skills] with `skills.orElse(null)`. */
        fun skills(skills: Optional<List<BetaManagedAgentsSkillParams>>) =
            skills(skills.getOrNull())

        /**
         * Sets [Builder.skills] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skills] with a well-typed
         * `List<BetaManagedAgentsSkillParams>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun skills(skills: JsonField<List<BetaManagedAgentsSkillParams>>) = apply {
            body.skills(skills)
        }

        /**
         * Adds a single [BetaManagedAgentsSkillParams] to [skills].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSkill(skill: BetaManagedAgentsSkillParams) = apply { body.addSkill(skill) }

        /**
         * Alias for calling [addSkill] with `BetaManagedAgentsSkillParams.ofAnthropic(anthropic)`.
         */
        fun addSkill(anthropic: BetaManagedAgentsAnthropicSkillParams) = apply {
            body.addSkill(anthropic)
        }

        /**
         * Alias for calling [addSkill] with the following:
         * ```java
         * BetaManagedAgentsAnthropicSkillParams.builder()
         *     .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
         *     .skillId(skillId)
         *     .build()
         * ```
         */
        fun addAnthropicSkill(skillId: String) = apply { body.addAnthropicSkill(skillId) }

        /** Alias for calling [addSkill] with `BetaManagedAgentsSkillParams.ofCustom(custom)`. */
        fun addSkill(custom: BetaManagedAgentsCustomSkillParams) = apply { body.addSkill(custom) }

        /**
         * Alias for calling [addSkill] with the following:
         * ```java
         * BetaManagedAgentsCustomSkillParams.builder()
         *     .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
         *     .skillId(skillId)
         *     .build()
         * ```
         */
        fun addCustomSkill(skillId: String) = apply { body.addCustomSkill(skillId) }

        /**
         * System prompt. Up to 100,000 characters. Omit to preserve; send empty string or null to
         * clear.
         */
        fun system(system: String?) = apply { body.system(system) }

        /** Alias for calling [Builder.system] with `system.orElse(null)`. */
        fun system(system: Optional<String>) = system(system.getOrNull())

        /**
         * Sets [Builder.system] to an arbitrary JSON value.
         *
         * You should usually call [Builder.system] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun system(system: JsonField<String>) = apply { body.system(system) }

        /**
         * Tool configurations available to the agent. Full replacement. Omit to preserve; send
         * empty array or null to clear. Maximum of 128 tools across all toolsets allowed.
         */
        fun tools(tools: List<Tool>?) = apply { body.tools(tools) }

        /** Alias for calling [Builder.tools] with `tools.orElse(null)`. */
        fun tools(tools: Optional<List<Tool>>) = tools(tools.getOrNull())

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun tools(tools: JsonField<List<Tool>>) = apply { body.tools(tools) }

        /**
         * Adds a single [Tool] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: Tool) = apply { body.addTool(tool) }

        /** Alias for calling [addTool] with `Tool.ofAgentToolset20260401(agentToolset20260401)`. */
        fun addTool(agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params) = apply {
            body.addTool(agentToolset20260401)
        }

        /** Alias for calling [addTool] with `Tool.ofMcpToolset(mcpToolset)`. */
        fun addTool(mcpToolset: BetaManagedAgentsMcpToolsetParams) = apply {
            body.addTool(mcpToolset)
        }

        /**
         * Alias for calling [addTool] with the following:
         * ```java
         * BetaManagedAgentsMcpToolsetParams.builder()
         *     .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
         *     .mcpServerName(mcpServerName)
         *     .build()
         * ```
         */
        fun addMcpToolsetTool(mcpServerName: String) = apply {
            body.addMcpToolsetTool(mcpServerName)
        }

        /** Alias for calling [addTool] with `Tool.ofCustom(custom)`. */
        fun addTool(custom: BetaManagedAgentsCustomToolParams) = apply { body.addTool(custom) }

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
         * Returns an immutable instance of [AgentUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .version()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): AgentUpdateParams =
            AgentUpdateParams(
                agentId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> agentId ?: ""
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

    /** Request parameters for updating an `agent`. Omit a field to preserve its current value. */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val version: JsonField<Int>,
        private val description: JsonField<String>,
        private val mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>,
        private val metadata: JsonField<Metadata>,
        private val model: JsonField<Model>,
        private val name: JsonField<String>,
        private val skills: JsonField<List<BetaManagedAgentsSkillParams>>,
        private val system: JsonField<String>,
        private val tools: JsonField<List<Tool>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("version") @ExcludeMissing version: JsonField<Int> = JsonMissing.of(),
            @JsonProperty("description")
            @ExcludeMissing
            description: JsonField<String> = JsonMissing.of(),
            @JsonProperty("mcp_servers")
            @ExcludeMissing
            mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
            @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
            @JsonProperty("skills")
            @ExcludeMissing
            skills: JsonField<List<BetaManagedAgentsSkillParams>> = JsonMissing.of(),
            @JsonProperty("system") @ExcludeMissing system: JsonField<String> = JsonMissing.of(),
            @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        ) : this(
            version,
            description,
            mcpServers,
            metadata,
            model,
            name,
            skills,
            system,
            tools,
            mutableMapOf(),
        )

        /**
         * The agent's current version, used to prevent concurrent overwrites. Obtain this value
         * from a create or retrieve response. The request fails if this does not match the server's
         * current version.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun version(): Int = version.getRequired("version")

        /**
         * Description. Up to 2048 characters. Omit to preserve; send empty string or null to clear.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun description(): Optional<String> = description.getOptional("description")

        /**
         * MCP servers. Full replacement. Omit to preserve; send empty array or null to clear. Names
         * must be unique. Maximum 20.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun mcpServers(): Optional<List<BetaManagedAgentsUrlMcpServerParams>> =
            mcpServers.getOptional("mcp_servers")

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
         * Model identifier. Accepts the
         * [model string](https://platform.claude.com/docs/en/about-claude/models/overview#latest-models-comparison),
         * e.g. `claude-opus-4-6`, or a `model_config` object for additional configuration control.
         * Omit to preserve. Cannot be cleared.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun model(): Optional<Model> = model.getOptional("model")

        /**
         * Human-readable name. 1-256 characters. Omit to preserve. Cannot be cleared.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * Skills. Full replacement. Omit to preserve; send empty array or null to clear.
         * Maximum 20.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun skills(): Optional<List<BetaManagedAgentsSkillParams>> = skills.getOptional("skills")

        /**
         * System prompt. Up to 100,000 characters. Omit to preserve; send empty string or null to
         * clear.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun system(): Optional<String> = system.getOptional("system")

        /**
         * Tool configurations available to the agent. Full replacement. Omit to preserve; send
         * empty array or null to clear. Maximum of 128 tools across all toolsets allowed.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun tools(): Optional<List<Tool>> = tools.getOptional("tools")

        /**
         * Returns the raw JSON value of [version].
         *
         * Unlike [version], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("version") @ExcludeMissing fun _version(): JsonField<Int> = version

        /**
         * Returns the raw JSON value of [description].
         *
         * Unlike [description], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("description")
        @ExcludeMissing
        fun _description(): JsonField<String> = description

        /**
         * Returns the raw JSON value of [mcpServers].
         *
         * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        fun _mcpServers(): JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = mcpServers

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        /**
         * Returns the raw JSON value of [model].
         *
         * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

        /**
         * Returns the raw JSON value of [name].
         *
         * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

        /**
         * Returns the raw JSON value of [skills].
         *
         * Unlike [skills], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("skills")
        @ExcludeMissing
        fun _skills(): JsonField<List<BetaManagedAgentsSkillParams>> = skills

        /**
         * Returns the raw JSON value of [system].
         *
         * Unlike [system], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("system") @ExcludeMissing fun _system(): JsonField<String> = system

        /**
         * Returns the raw JSON value of [tools].
         *
         * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("tools") @ExcludeMissing fun _tools(): JsonField<List<Tool>> = tools

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
             * .version()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var version: JsonField<Int>? = null
            private var description: JsonField<String> = JsonMissing.of()
            private var mcpServers: JsonField<MutableList<BetaManagedAgentsUrlMcpServerParams>>? =
                null
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var model: JsonField<Model> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var skills: JsonField<MutableList<BetaManagedAgentsSkillParams>>? = null
            private var system: JsonField<String> = JsonMissing.of()
            private var tools: JsonField<MutableList<Tool>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                version = body.version
                description = body.description
                mcpServers = body.mcpServers.map { it.toMutableList() }
                metadata = body.metadata
                model = body.model
                name = body.name
                skills = body.skills.map { it.toMutableList() }
                system = body.system
                tools = body.tools.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The agent's current version, used to prevent concurrent overwrites. Obtain this value
             * from a create or retrieve response. The request fails if this does not match the
             * server's current version.
             */
            fun version(version: Int) = version(JsonField.of(version))

            /**
             * Sets [Builder.version] to an arbitrary JSON value.
             *
             * You should usually call [Builder.version] with a well-typed [Int] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun version(version: JsonField<Int>) = apply { this.version = version }

            /**
             * Description. Up to 2048 characters. Omit to preserve; send empty string or null to
             * clear.
             */
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

            /**
             * MCP servers. Full replacement. Omit to preserve; send empty array or null to clear.
             * Names must be unique. Maximum 20.
             */
            fun mcpServers(mcpServers: List<BetaManagedAgentsUrlMcpServerParams>?) =
                mcpServers(JsonField.ofNullable(mcpServers))

            /** Alias for calling [Builder.mcpServers] with `mcpServers.orElse(null)`. */
            fun mcpServers(mcpServers: Optional<List<BetaManagedAgentsUrlMcpServerParams>>) =
                mcpServers(mcpServers.getOrNull())

            /**
             * Sets [Builder.mcpServers] to an arbitrary JSON value.
             *
             * You should usually call [Builder.mcpServers] with a well-typed
             * `List<BetaManagedAgentsUrlMcpServerParams>` value instead. This method is primarily
             * for setting the field to an undocumented or not yet supported value.
             */
            fun mcpServers(mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>) =
                apply {
                    this.mcpServers = mcpServers.map { it.toMutableList() }
                }

            /**
             * Adds a single [BetaManagedAgentsUrlMcpServerParams] to [mcpServers].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addMcpServer(mcpServer: BetaManagedAgentsUrlMcpServerParams) = apply {
                mcpServers =
                    (mcpServers ?: JsonField.of(mutableListOf())).also {
                        checkKnown("mcpServers", it).add(mcpServer)
                    }
            }

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

            /**
             * Model identifier. Accepts the
             * [model string](https://platform.claude.com/docs/en/about-claude/models/overview#latest-models-comparison),
             * e.g. `claude-opus-4-6`, or a `model_config` object for additional configuration
             * control. Omit to preserve. Cannot be cleared.
             */
            fun model(model: Model) = model(JsonField.of(model))

            /**
             * Sets [Builder.model] to an arbitrary JSON value.
             *
             * You should usually call [Builder.model] with a well-typed [Model] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun model(model: JsonField<Model>) = apply { this.model = model }

            /** Alias for calling [model] with `Model.ofBetaManagedAgents(betaManagedAgents)`. */
            fun model(betaManagedAgents: BetaManagedAgentsModel) =
                model(Model.ofBetaManagedAgents(betaManagedAgents))

            /**
             * Alias for calling [model] with
             * `Model.ofBetaManagedAgentsModelConfigParams(betaManagedAgentsModelConfigParams)`.
             */
            fun model(betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams) =
                model(
                    Model.ofBetaManagedAgentsModelConfigParams(betaManagedAgentsModelConfigParams)
                )

            /** Human-readable name. 1-256 characters. Omit to preserve. Cannot be cleared. */
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
             * Skills. Full replacement. Omit to preserve; send empty array or null to clear.
             * Maximum 20.
             */
            fun skills(skills: List<BetaManagedAgentsSkillParams>?) =
                skills(JsonField.ofNullable(skills))

            /** Alias for calling [Builder.skills] with `skills.orElse(null)`. */
            fun skills(skills: Optional<List<BetaManagedAgentsSkillParams>>) =
                skills(skills.getOrNull())

            /**
             * Sets [Builder.skills] to an arbitrary JSON value.
             *
             * You should usually call [Builder.skills] with a well-typed
             * `List<BetaManagedAgentsSkillParams>` value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun skills(skills: JsonField<List<BetaManagedAgentsSkillParams>>) = apply {
                this.skills = skills.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaManagedAgentsSkillParams] to [skills].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addSkill(skill: BetaManagedAgentsSkillParams) = apply {
                skills =
                    (skills ?: JsonField.of(mutableListOf())).also {
                        checkKnown("skills", it).add(skill)
                    }
            }

            /**
             * Alias for calling [addSkill] with
             * `BetaManagedAgentsSkillParams.ofAnthropic(anthropic)`.
             */
            fun addSkill(anthropic: BetaManagedAgentsAnthropicSkillParams) =
                addSkill(BetaManagedAgentsSkillParams.ofAnthropic(anthropic))

            /**
             * Alias for calling [addSkill] with the following:
             * ```java
             * BetaManagedAgentsAnthropicSkillParams.builder()
             *     .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
             *     .skillId(skillId)
             *     .build()
             * ```
             */
            fun addAnthropicSkill(skillId: String) =
                addSkill(
                    BetaManagedAgentsAnthropicSkillParams.builder()
                        .type(BetaManagedAgentsAnthropicSkillParams.Type.ANTHROPIC)
                        .skillId(skillId)
                        .build()
                )

            /**
             * Alias for calling [addSkill] with `BetaManagedAgentsSkillParams.ofCustom(custom)`.
             */
            fun addSkill(custom: BetaManagedAgentsCustomSkillParams) =
                addSkill(BetaManagedAgentsSkillParams.ofCustom(custom))

            /**
             * Alias for calling [addSkill] with the following:
             * ```java
             * BetaManagedAgentsCustomSkillParams.builder()
             *     .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
             *     .skillId(skillId)
             *     .build()
             * ```
             */
            fun addCustomSkill(skillId: String) =
                addSkill(
                    BetaManagedAgentsCustomSkillParams.builder()
                        .type(BetaManagedAgentsCustomSkillParams.Type.CUSTOM)
                        .skillId(skillId)
                        .build()
                )

            /**
             * System prompt. Up to 100,000 characters. Omit to preserve; send empty string or null
             * to clear.
             */
            fun system(system: String?) = system(JsonField.ofNullable(system))

            /** Alias for calling [Builder.system] with `system.orElse(null)`. */
            fun system(system: Optional<String>) = system(system.getOrNull())

            /**
             * Sets [Builder.system] to an arbitrary JSON value.
             *
             * You should usually call [Builder.system] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun system(system: JsonField<String>) = apply { this.system = system }

            /**
             * Tool configurations available to the agent. Full replacement. Omit to preserve; send
             * empty array or null to clear. Maximum of 128 tools across all toolsets allowed.
             */
            fun tools(tools: List<Tool>?) = tools(JsonField.ofNullable(tools))

            /** Alias for calling [Builder.tools] with `tools.orElse(null)`. */
            fun tools(tools: Optional<List<Tool>>) = tools(tools.getOrNull())

            /**
             * Sets [Builder.tools] to an arbitrary JSON value.
             *
             * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun tools(tools: JsonField<List<Tool>>) = apply {
                this.tools = tools.map { it.toMutableList() }
            }

            /**
             * Adds a single [Tool] to [tools].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addTool(tool: Tool) = apply {
                tools =
                    (tools ?: JsonField.of(mutableListOf())).also {
                        checkKnown("tools", it).add(tool)
                    }
            }

            /**
             * Alias for calling [addTool] with `Tool.ofAgentToolset20260401(agentToolset20260401)`.
             */
            fun addTool(agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params) =
                addTool(Tool.ofAgentToolset20260401(agentToolset20260401))

            /** Alias for calling [addTool] with `Tool.ofMcpToolset(mcpToolset)`. */
            fun addTool(mcpToolset: BetaManagedAgentsMcpToolsetParams) =
                addTool(Tool.ofMcpToolset(mcpToolset))

            /**
             * Alias for calling [addTool] with the following:
             * ```java
             * BetaManagedAgentsMcpToolsetParams.builder()
             *     .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
             *     .mcpServerName(mcpServerName)
             *     .build()
             * ```
             */
            fun addMcpToolsetTool(mcpServerName: String) =
                addTool(
                    BetaManagedAgentsMcpToolsetParams.builder()
                        .type(BetaManagedAgentsMcpToolsetParams.Type.MCP_TOOLSET)
                        .mcpServerName(mcpServerName)
                        .build()
                )

            /** Alias for calling [addTool] with `Tool.ofCustom(custom)`. */
            fun addTool(custom: BetaManagedAgentsCustomToolParams) = addTool(Tool.ofCustom(custom))

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
             * .version()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("version", version),
                    description,
                    (mcpServers ?: JsonMissing.of()).map { it.toImmutable() },
                    metadata,
                    model,
                    name,
                    (skills ?: JsonMissing.of()).map { it.toImmutable() },
                    system,
                    (tools ?: JsonMissing.of()).map { it.toImmutable() },
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

            version()
            description()
            mcpServers().ifPresent { it.forEach { it.validate() } }
            metadata().ifPresent { it.validate() }
            model().ifPresent { it.validate() }
            name()
            skills().ifPresent { it.forEach { it.validate() } }
            system()
            tools().ifPresent { it.forEach { it.validate() } }
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
            (if (version.asKnown().isPresent) 1 else 0) +
                (if (description.asKnown().isPresent) 1 else 0) +
                (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0) +
                (model.asKnown().getOrNull()?.validity() ?: 0) +
                (if (name.asKnown().isPresent) 1 else 0) +
                (skills.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (if (system.asKnown().isPresent) 1 else 0) +
                (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                version == other.version &&
                description == other.description &&
                mcpServers == other.mcpServers &&
                metadata == other.metadata &&
                model == other.model &&
                name == other.name &&
                skills == other.skills &&
                system == other.system &&
                tools == other.tools &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                version,
                description,
                mcpServers,
                metadata,
                model,
                name,
                skills,
                system,
                tools,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{version=$version, description=$description, mcpServers=$mcpServers, metadata=$metadata, model=$model, name=$name, skills=$skills, system=$system, tools=$tools, additionalProperties=$additionalProperties}"
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

    /**
     * Model identifier. Accepts the
     * [model string](https://platform.claude.com/docs/en/about-claude/models/overview#latest-models-comparison),
     * e.g. `claude-opus-4-6`, or a `model_config` object for additional configuration control. Omit
     * to preserve. Cannot be cleared.
     */
    @JsonDeserialize(using = Model.Deserializer::class)
    @JsonSerialize(using = Model.Serializer::class)
    class Model
    private constructor(
        private val betaManagedAgents: BetaManagedAgentsModel? = null,
        private val betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * The model that will power your agent.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun betaManagedAgents(): Optional<BetaManagedAgentsModel> =
            Optional.ofNullable(betaManagedAgents)

        /** An object that defines additional configuration control over model use */
        fun betaManagedAgentsModelConfigParams(): Optional<BetaManagedAgentsModelConfigParams> =
            Optional.ofNullable(betaManagedAgentsModelConfigParams)

        fun isBetaManagedAgents(): Boolean = betaManagedAgents != null

        fun isBetaManagedAgentsModelConfigParams(): Boolean =
            betaManagedAgentsModelConfigParams != null

        /**
         * The model that will power your agent.\n\nSee
         * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details and
         * options.
         */
        fun asBetaManagedAgents(): BetaManagedAgentsModel =
            betaManagedAgents.getOrThrow("betaManagedAgents")

        /** An object that defines additional configuration control over model use */
        fun asBetaManagedAgentsModelConfigParams(): BetaManagedAgentsModelConfigParams =
            betaManagedAgentsModelConfigParams.getOrThrow("betaManagedAgentsModelConfigParams")

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
         * Optional<String> result = model.accept(new Model.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitBetaManagedAgents(BetaManagedAgentsModel betaManagedAgents) {
         *         return Optional.of(betaManagedAgents.toString());
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
                betaManagedAgents != null -> visitor.visitBetaManagedAgents(betaManagedAgents)
                betaManagedAgentsModelConfigParams != null ->
                    visitor.visitBetaManagedAgentsModelConfigParams(
                        betaManagedAgentsModelConfigParams
                    )
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
        fun validate(): Model = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBetaManagedAgents(
                        betaManagedAgents: BetaManagedAgentsModel
                    ) {}

                    override fun visitBetaManagedAgentsModelConfigParams(
                        betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams
                    ) {
                        betaManagedAgentsModelConfigParams.validate()
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
                    override fun visitBetaManagedAgents(betaManagedAgents: BetaManagedAgentsModel) =
                        1

                    override fun visitBetaManagedAgentsModelConfigParams(
                        betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams
                    ) = betaManagedAgentsModelConfigParams.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Model &&
                betaManagedAgents == other.betaManagedAgents &&
                betaManagedAgentsModelConfigParams == other.betaManagedAgentsModelConfigParams
        }

        override fun hashCode(): Int =
            Objects.hash(betaManagedAgents, betaManagedAgentsModelConfigParams)

        override fun toString(): String =
            when {
                betaManagedAgents != null -> "Model{betaManagedAgents=$betaManagedAgents}"
                betaManagedAgentsModelConfigParams != null ->
                    "Model{betaManagedAgentsModelConfigParams=$betaManagedAgentsModelConfigParams}"
                _json != null -> "Model{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Model")
            }

        companion object {

            /**
             * The model that will power your agent.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            @JvmStatic
            fun ofBetaManagedAgents(betaManagedAgents: BetaManagedAgentsModel) =
                Model(betaManagedAgents = betaManagedAgents)

            /** An object that defines additional configuration control over model use */
            @JvmStatic
            fun ofBetaManagedAgentsModelConfigParams(
                betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams
            ) = Model(betaManagedAgentsModelConfigParams = betaManagedAgentsModelConfigParams)
        }

        /** An interface that defines how to map each variant of [Model] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * The model that will power your agent.\n\nSee
             * [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
             * and options.
             */
            fun visitBetaManagedAgents(betaManagedAgents: BetaManagedAgentsModel): T

            /** An object that defines additional configuration control over model use */
            fun visitBetaManagedAgentsModelConfigParams(
                betaManagedAgentsModelConfigParams: BetaManagedAgentsModelConfigParams
            ): T

            /**
             * Maps an unknown variant of [Model] to a value of type [T].
             *
             * An instance of [Model] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Model: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Model>(Model::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Model {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsModel>())?.let {
                                Model(betaManagedAgents = it, _json = json)
                            },
                            tryDeserialize(
                                    node,
                                    jacksonTypeRef<BetaManagedAgentsModelConfigParams>(),
                                )
                                ?.let {
                                    Model(betaManagedAgentsModelConfigParams = it, _json = json)
                                },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Model(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Model>(Model::class) {

            override fun serialize(
                value: Model,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.betaManagedAgents != null ->
                        generator.writeObject(value.betaManagedAgents)
                    value.betaManagedAgentsModelConfigParams != null ->
                        generator.writeObject(value.betaManagedAgentsModelConfigParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Model")
                }
            }
        }
    }

    /** Union type for tool configurations in the tools array. */
    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params? = null,
        private val mcpToolset: BetaManagedAgentsMcpToolsetParams? = null,
        private val custom: BetaManagedAgentsCustomToolParams? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * Configuration for built-in agent tools. Use this to enable or disable groups of tools
         * available to the agent.
         */
        fun agentToolset20260401(): Optional<BetaManagedAgentsAgentToolset20260401Params> =
            Optional.ofNullable(agentToolset20260401)

        /** Configuration for tools from an MCP server defined in `mcp_servers`. */
        fun mcpToolset(): Optional<BetaManagedAgentsMcpToolsetParams> =
            Optional.ofNullable(mcpToolset)

        /**
         * A custom tool that is executed by the API client rather than the agent. When the agent
         * calls this tool, an `agent.custom_tool_use` event is emitted and the session goes idle,
         * waiting for the client to provide the result via a `user.custom_tool_result` event.
         */
        fun custom(): Optional<BetaManagedAgentsCustomToolParams> = Optional.ofNullable(custom)

        fun isAgentToolset20260401(): Boolean = agentToolset20260401 != null

        fun isMcpToolset(): Boolean = mcpToolset != null

        fun isCustom(): Boolean = custom != null

        /**
         * Configuration for built-in agent tools. Use this to enable or disable groups of tools
         * available to the agent.
         */
        fun asAgentToolset20260401(): BetaManagedAgentsAgentToolset20260401Params =
            agentToolset20260401.getOrThrow("agentToolset20260401")

        /** Configuration for tools from an MCP server defined in `mcp_servers`. */
        fun asMcpToolset(): BetaManagedAgentsMcpToolsetParams = mcpToolset.getOrThrow("mcpToolset")

        /**
         * A custom tool that is executed by the API client rather than the agent. When the agent
         * calls this tool, an `agent.custom_tool_use` event is emitted and the session goes idle,
         * waiting for the client to provide the result via a `user.custom_tool_result` event.
         */
        fun asCustom(): BetaManagedAgentsCustomToolParams = custom.getOrThrow("custom")

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
         * Optional<String> result = tool.accept(new Tool.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAgentToolset20260401(BetaManagedAgentsAgentToolset20260401Params agentToolset20260401) {
         *         return Optional.of(agentToolset20260401.toString());
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
                agentToolset20260401 != null ->
                    visitor.visitAgentToolset20260401(agentToolset20260401)
                mcpToolset != null -> visitor.visitMcpToolset(mcpToolset)
                custom != null -> visitor.visitCustom(custom)
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
        fun validate(): Tool = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAgentToolset20260401(
                        agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params
                    ) {
                        agentToolset20260401.validate()
                    }

                    override fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolsetParams) {
                        mcpToolset.validate()
                    }

                    override fun visitCustom(custom: BetaManagedAgentsCustomToolParams) {
                        custom.validate()
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
                    override fun visitAgentToolset20260401(
                        agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params
                    ) = agentToolset20260401.validity()

                    override fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolsetParams) =
                        mcpToolset.validity()

                    override fun visitCustom(custom: BetaManagedAgentsCustomToolParams) =
                        custom.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tool &&
                agentToolset20260401 == other.agentToolset20260401 &&
                mcpToolset == other.mcpToolset &&
                custom == other.custom
        }

        override fun hashCode(): Int = Objects.hash(agentToolset20260401, mcpToolset, custom)

        override fun toString(): String =
            when {
                agentToolset20260401 != null -> "Tool{agentToolset20260401=$agentToolset20260401}"
                mcpToolset != null -> "Tool{mcpToolset=$mcpToolset}"
                custom != null -> "Tool{custom=$custom}"
                _json != null -> "Tool{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Tool")
            }

        companion object {

            /**
             * Configuration for built-in agent tools. Use this to enable or disable groups of tools
             * available to the agent.
             */
            @JvmStatic
            fun ofAgentToolset20260401(
                agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params
            ) = Tool(agentToolset20260401 = agentToolset20260401)

            /** Configuration for tools from an MCP server defined in `mcp_servers`. */
            @JvmStatic
            fun ofMcpToolset(mcpToolset: BetaManagedAgentsMcpToolsetParams) =
                Tool(mcpToolset = mcpToolset)

            /**
             * A custom tool that is executed by the API client rather than the agent. When the
             * agent calls this tool, an `agent.custom_tool_use` event is emitted and the session
             * goes idle, waiting for the client to provide the result via a
             * `user.custom_tool_result` event.
             */
            @JvmStatic
            fun ofCustom(custom: BetaManagedAgentsCustomToolParams) = Tool(custom = custom)
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * Configuration for built-in agent tools. Use this to enable or disable groups of tools
             * available to the agent.
             */
            fun visitAgentToolset20260401(
                agentToolset20260401: BetaManagedAgentsAgentToolset20260401Params
            ): T

            /** Configuration for tools from an MCP server defined in `mcp_servers`. */
            fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolsetParams): T

            /**
             * A custom tool that is executed by the API client rather than the agent. When the
             * agent calls this tool, an `agent.custom_tool_use` event is emitted and the session
             * goes idle, waiting for the client to provide the result via a
             * `user.custom_tool_result` event.
             */
            fun visitCustom(custom: BetaManagedAgentsCustomToolParams): T

            /**
             * Maps an unknown variant of [Tool] to a value of type [T].
             *
             * An instance of [Tool] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Tool: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Tool>(Tool::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Tool {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "agent_toolset_20260401" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401Params>(),
                            )
                            ?.let { Tool(agentToolset20260401 = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "mcp_toolset" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMcpToolsetParams>(),
                            )
                            ?.let { Tool(mcpToolset = it, _json = json) } ?: Tool(_json = json)
                    }
                    "custom" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsCustomToolParams>(),
                            )
                            ?.let { Tool(custom = it, _json = json) } ?: Tool(_json = json)
                    }
                }

                return Tool(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Tool>(Tool::class) {

            override fun serialize(
                value: Tool,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.agentToolset20260401 != null ->
                        generator.writeObject(value.agentToolset20260401)
                    value.mcpToolset != null -> generator.writeObject(value.mcpToolset)
                    value.custom != null -> generator.writeObject(value.custom)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Tool")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AgentUpdateParams &&
            agentId == other.agentId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(agentId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "AgentUpdateParams{agentId=$agentId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
