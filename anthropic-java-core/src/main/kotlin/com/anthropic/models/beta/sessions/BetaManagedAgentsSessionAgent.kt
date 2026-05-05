// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkill
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomTool
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpServerUrlDefinition
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpToolset
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfig
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsSessionThreadAgent
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

/**
 * Resolved `agent` definition for a `session`. Snapshot of the `agent` at `session` creation time.
 */
class BetaManagedAgentsSessionAgent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val description: JsonField<String>,
    private val mcpServers: JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>>,
    private val model: JsonField<BetaManagedAgentsModelConfig>,
    private val multiagent: JsonField<BetaManagedAgentsSessionMultiagentCoordinator>,
    private val name: JsonField<String>,
    private val skills: JsonField<List<Skill>>,
    private val system: JsonField<String>,
    private val tools: JsonField<List<Tool>>,
    private val type: JsonField<Type>,
    private val version: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        mcpServers: JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        model: JsonField<BetaManagedAgentsModelConfig> = JsonMissing.of(),
        @JsonProperty("multiagent")
        @ExcludeMissing
        multiagent: JsonField<BetaManagedAgentsSessionMultiagentCoordinator> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("skills") @ExcludeMissing skills: JsonField<List<Skill>> = JsonMissing.of(),
        @JsonProperty("system") @ExcludeMissing system: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("version") @ExcludeMissing version: JsonField<Int> = JsonMissing.of(),
    ) : this(
        id,
        description,
        mcpServers,
        model,
        multiagent,
        name,
        skills,
        system,
        tools,
        type,
        version,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun description(): Optional<String> = description.getOptional("description")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServers(): List<BetaManagedAgentsMcpServerUrlDefinition> =
        mcpServers.getRequired("mcp_servers")

    /**
     * Model identifier and configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): BetaManagedAgentsModelConfig = model.getRequired("model")

    /**
     * Resolved coordinator topology with full agent definitions for each roster member.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun multiagent(): Optional<BetaManagedAgentsSessionMultiagentCoordinator> =
        multiagent.getOptional("multiagent")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skills(): List<Skill> = skills.getRequired("skills")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun system(): Optional<String> = system.getOptional("system")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tools(): List<Tool> = tools.getRequired("tools")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun version(): Int = version.getRequired("version")

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
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_servers")
    @ExcludeMissing
    fun _mcpServers(): JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>> = mcpServers

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model")
    @ExcludeMissing
    fun _model(): JsonField<BetaManagedAgentsModelConfig> = model

    /**
     * Returns the raw JSON value of [multiagent].
     *
     * Unlike [multiagent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("multiagent")
    @ExcludeMissing
    fun _multiagent(): JsonField<BetaManagedAgentsSessionMultiagentCoordinator> = multiagent

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
    @JsonProperty("skills") @ExcludeMissing fun _skills(): JsonField<List<Skill>> = skills

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

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [version].
     *
     * Unlike [version], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("version") @ExcludeMissing fun _version(): JsonField<Int> = version

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
         * [BetaManagedAgentsSessionAgent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .mcpServers()
         * .model()
         * .multiagent()
         * .name()
         * .skills()
         * .system()
         * .tools()
         * .type()
         * .version()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionAgent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var description: JsonField<String>? = null
        private var mcpServers: JsonField<MutableList<BetaManagedAgentsMcpServerUrlDefinition>>? =
            null
        private var model: JsonField<BetaManagedAgentsModelConfig>? = null
        private var multiagent: JsonField<BetaManagedAgentsSessionMultiagentCoordinator>? = null
        private var name: JsonField<String>? = null
        private var skills: JsonField<MutableList<Skill>>? = null
        private var system: JsonField<String>? = null
        private var tools: JsonField<MutableList<Tool>>? = null
        private var type: JsonField<Type>? = null
        private var version: JsonField<Int>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSessionAgent: BetaManagedAgentsSessionAgent) = apply {
            id = betaManagedAgentsSessionAgent.id
            description = betaManagedAgentsSessionAgent.description
            mcpServers = betaManagedAgentsSessionAgent.mcpServers.map { it.toMutableList() }
            model = betaManagedAgentsSessionAgent.model
            multiagent = betaManagedAgentsSessionAgent.multiagent
            name = betaManagedAgentsSessionAgent.name
            skills = betaManagedAgentsSessionAgent.skills.map { it.toMutableList() }
            system = betaManagedAgentsSessionAgent.system
            tools = betaManagedAgentsSessionAgent.tools.map { it.toMutableList() }
            type = betaManagedAgentsSessionAgent.type
            version = betaManagedAgentsSessionAgent.version
            additionalProperties = betaManagedAgentsSessionAgent.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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

        fun mcpServers(mcpServers: List<BetaManagedAgentsMcpServerUrlDefinition>) =
            mcpServers(JsonField.of(mcpServers))

        /**
         * Sets [Builder.mcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServers] with a well-typed
         * `List<BetaManagedAgentsMcpServerUrlDefinition>` value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun mcpServers(mcpServers: JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>>) =
            apply {
                this.mcpServers = mcpServers.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaManagedAgentsMcpServerUrlDefinition] to [mcpServers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addMcpServer(mcpServer: BetaManagedAgentsMcpServerUrlDefinition) = apply {
            mcpServers =
                (mcpServers ?: JsonField.of(mutableListOf())).also {
                    checkKnown("mcpServers", it).add(mcpServer)
                }
        }

        /** Model identifier and configuration. */
        fun model(model: BetaManagedAgentsModelConfig) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [BetaManagedAgentsModelConfig]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun model(model: JsonField<BetaManagedAgentsModelConfig>) = apply { this.model = model }

        /** Resolved coordinator topology with full agent definitions for each roster member. */
        fun multiagent(multiagent: BetaManagedAgentsSessionMultiagentCoordinator?) =
            multiagent(JsonField.ofNullable(multiagent))

        /** Alias for calling [Builder.multiagent] with `multiagent.orElse(null)`. */
        fun multiagent(multiagent: Optional<BetaManagedAgentsSessionMultiagentCoordinator>) =
            multiagent(multiagent.getOrNull())

        /**
         * Sets [Builder.multiagent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.multiagent] with a well-typed
         * [BetaManagedAgentsSessionMultiagentCoordinator] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun multiagent(multiagent: JsonField<BetaManagedAgentsSessionMultiagentCoordinator>) =
            apply {
                this.multiagent = multiagent
            }

        /**
         * Alias for calling [multiagent] with the following:
         * ```java
         * BetaManagedAgentsSessionMultiagentCoordinator.builder()
         *     .type(BetaManagedAgentsSessionMultiagentCoordinator.Type.COORDINATOR)
         *     .agents(agents)
         *     .build()
         * ```
         */
        fun coordinatorMultiagent(agents: List<BetaManagedAgentsSessionThreadAgent>) =
            multiagent(
                BetaManagedAgentsSessionMultiagentCoordinator.builder()
                    .type(BetaManagedAgentsSessionMultiagentCoordinator.Type.COORDINATOR)
                    .agents(agents)
                    .build()
            )

        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        fun skills(skills: List<Skill>) = skills(JsonField.of(skills))

        /**
         * Sets [Builder.skills] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skills] with a well-typed `List<Skill>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skills(skills: JsonField<List<Skill>>) = apply {
            this.skills = skills.map { it.toMutableList() }
        }

        /**
         * Adds a single [Skill] to [skills].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSkill(skill: Skill) = apply {
            skills =
                (skills ?: JsonField.of(mutableListOf())).also {
                    checkKnown("skills", it).add(skill)
                }
        }

        /** Alias for calling [addSkill] with `Skill.ofAnthropic(anthropic)`. */
        fun addSkill(anthropic: BetaManagedAgentsAnthropicSkill) =
            addSkill(Skill.ofAnthropic(anthropic))

        /** Alias for calling [addSkill] with `Skill.ofCustom(custom)`. */
        fun addSkill(custom: BetaManagedAgentsCustomSkill) = addSkill(Skill.ofCustom(custom))

        fun system(system: String?) = system(JsonField.ofNullable(system))

        /** Alias for calling [Builder.system] with `system.orElse(null)`. */
        fun system(system: Optional<String>) = system(system.getOrNull())

        /**
         * Sets [Builder.system] to an arbitrary JSON value.
         *
         * You should usually call [Builder.system] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun system(system: JsonField<String>) = apply { this.system = system }

        fun tools(tools: List<Tool>) = tools(JsonField.of(tools))

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed `List<Tool>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
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
                (tools ?: JsonField.of(mutableListOf())).also { checkKnown("tools", it).add(tool) }
        }

        /** Alias for calling [addTool] with `Tool.ofAgentToolset20260401(agentToolset20260401)`. */
        fun addTool(agentToolset20260401: BetaManagedAgentsAgentToolset20260401) =
            addTool(Tool.ofAgentToolset20260401(agentToolset20260401))

        /** Alias for calling [addTool] with `Tool.ofMcpToolset(mcpToolset)`. */
        fun addTool(mcpToolset: BetaManagedAgentsMcpToolset) =
            addTool(Tool.ofMcpToolset(mcpToolset))

        /** Alias for calling [addTool] with `Tool.ofCustom(custom)`. */
        fun addTool(custom: BetaManagedAgentsCustomTool) = addTool(Tool.ofCustom(custom))

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun version(version: Int) = version(JsonField.of(version))

        /**
         * Sets [Builder.version] to an arbitrary JSON value.
         *
         * You should usually call [Builder.version] with a well-typed [Int] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun version(version: JsonField<Int>) = apply { this.version = version }

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
         * Returns an immutable instance of [BetaManagedAgentsSessionAgent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .description()
         * .mcpServers()
         * .model()
         * .multiagent()
         * .name()
         * .skills()
         * .system()
         * .tools()
         * .type()
         * .version()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionAgent =
            BetaManagedAgentsSessionAgent(
                checkRequired("id", id),
                checkRequired("description", description),
                checkRequired("mcpServers", mcpServers).map { it.toImmutable() },
                checkRequired("model", model),
                checkRequired("multiagent", multiagent),
                checkRequired("name", name),
                checkRequired("skills", skills).map { it.toImmutable() },
                checkRequired("system", system),
                checkRequired("tools", tools).map { it.toImmutable() },
                checkRequired("type", type),
                checkRequired("version", version),
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
    fun validate(): BetaManagedAgentsSessionAgent = apply {
        if (validated) {
            return@apply
        }

        id()
        description()
        mcpServers().forEach { it.validate() }
        model().validate()
        multiagent().ifPresent { it.validate() }
        name()
        skills().forEach { it.validate() }
        system()
        tools().forEach { it.validate() }
        type().validate()
        version()
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
            (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (model.asKnown().getOrNull()?.validity() ?: 0) +
            (multiagent.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (skills.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (system.asKnown().isPresent) 1 else 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (version.asKnown().isPresent) 1 else 0)

    /** Resolved skill as returned in API responses. */
    @JsonDeserialize(using = Skill.Deserializer::class)
    @JsonSerialize(using = Skill.Serializer::class)
    class Skill
    private constructor(
        private val anthropic: BetaManagedAgentsAnthropicSkill? = null,
        private val custom: BetaManagedAgentsCustomSkill? = null,
        private val _json: JsonValue? = null,
    ) {

        /** A resolved Anthropic-managed skill. */
        fun anthropic(): Optional<BetaManagedAgentsAnthropicSkill> = Optional.ofNullable(anthropic)

        /** A resolved user-created custom skill. */
        fun custom(): Optional<BetaManagedAgentsCustomSkill> = Optional.ofNullable(custom)

        fun isAnthropic(): Boolean = anthropic != null

        fun isCustom(): Boolean = custom != null

        /** A resolved Anthropic-managed skill. */
        fun asAnthropic(): BetaManagedAgentsAnthropicSkill = anthropic.getOrThrow("anthropic")

        /** A resolved user-created custom skill. */
        fun asCustom(): BetaManagedAgentsCustomSkill = custom.getOrThrow("custom")

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
         * Optional<String> result = skill.accept(new Skill.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAnthropic(BetaManagedAgentsAnthropicSkill anthropic) {
         *         return Optional.of(anthropic.toString());
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
                anthropic != null -> visitor.visitAnthropic(anthropic)
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
        fun validate(): Skill = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkill) {
                        anthropic.validate()
                    }

                    override fun visitCustom(custom: BetaManagedAgentsCustomSkill) {
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
                    override fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkill) =
                        anthropic.validity()

                    override fun visitCustom(custom: BetaManagedAgentsCustomSkill) =
                        custom.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Skill && anthropic == other.anthropic && custom == other.custom
        }

        override fun hashCode(): Int = Objects.hash(anthropic, custom)

        override fun toString(): String =
            when {
                anthropic != null -> "Skill{anthropic=$anthropic}"
                custom != null -> "Skill{custom=$custom}"
                _json != null -> "Skill{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Skill")
            }

        companion object {

            /** A resolved Anthropic-managed skill. */
            @JvmStatic
            fun ofAnthropic(anthropic: BetaManagedAgentsAnthropicSkill) =
                Skill(anthropic = anthropic)

            /** A resolved user-created custom skill. */
            @JvmStatic fun ofCustom(custom: BetaManagedAgentsCustomSkill) = Skill(custom = custom)
        }

        /** An interface that defines how to map each variant of [Skill] to a value of type [T]. */
        interface Visitor<out T> {

            /** A resolved Anthropic-managed skill. */
            fun visitAnthropic(anthropic: BetaManagedAgentsAnthropicSkill): T

            /** A resolved user-created custom skill. */
            fun visitCustom(custom: BetaManagedAgentsCustomSkill): T

            /**
             * Maps an unknown variant of [Skill] to a value of type [T].
             *
             * An instance of [Skill] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Skill: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Skill>(Skill::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Skill {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "anthropic" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsAnthropicSkill>(),
                            )
                            ?.let { Skill(anthropic = it, _json = json) } ?: Skill(_json = json)
                    }
                    "custom" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsCustomSkill>())
                            ?.let { Skill(custom = it, _json = json) } ?: Skill(_json = json)
                    }
                }

                return Skill(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Skill>(Skill::class) {

            override fun serialize(
                value: Skill,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.anthropic != null -> generator.writeObject(value.anthropic)
                    value.custom != null -> generator.writeObject(value.custom)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Skill")
                }
            }
        }
    }

    /** Union type for tool configurations returned in API responses. */
    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val agentToolset20260401: BetaManagedAgentsAgentToolset20260401? = null,
        private val mcpToolset: BetaManagedAgentsMcpToolset? = null,
        private val custom: BetaManagedAgentsCustomTool? = null,
        private val _json: JsonValue? = null,
    ) {

        fun agentToolset20260401(): Optional<BetaManagedAgentsAgentToolset20260401> =
            Optional.ofNullable(agentToolset20260401)

        fun mcpToolset(): Optional<BetaManagedAgentsMcpToolset> = Optional.ofNullable(mcpToolset)

        /** A custom tool as returned in API responses. */
        fun custom(): Optional<BetaManagedAgentsCustomTool> = Optional.ofNullable(custom)

        fun isAgentToolset20260401(): Boolean = agentToolset20260401 != null

        fun isMcpToolset(): Boolean = mcpToolset != null

        fun isCustom(): Boolean = custom != null

        fun asAgentToolset20260401(): BetaManagedAgentsAgentToolset20260401 =
            agentToolset20260401.getOrThrow("agentToolset20260401")

        fun asMcpToolset(): BetaManagedAgentsMcpToolset = mcpToolset.getOrThrow("mcpToolset")

        /** A custom tool as returned in API responses. */
        fun asCustom(): BetaManagedAgentsCustomTool = custom.getOrThrow("custom")

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
         *     public Optional<String> visitAgentToolset20260401(BetaManagedAgentsAgentToolset20260401 agentToolset20260401) {
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
                        agentToolset20260401: BetaManagedAgentsAgentToolset20260401
                    ) {
                        agentToolset20260401.validate()
                    }

                    override fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolset) {
                        mcpToolset.validate()
                    }

                    override fun visitCustom(custom: BetaManagedAgentsCustomTool) {
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
                        agentToolset20260401: BetaManagedAgentsAgentToolset20260401
                    ) = agentToolset20260401.validity()

                    override fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolset) =
                        mcpToolset.validity()

                    override fun visitCustom(custom: BetaManagedAgentsCustomTool) =
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

            @JvmStatic
            fun ofAgentToolset20260401(
                agentToolset20260401: BetaManagedAgentsAgentToolset20260401
            ) = Tool(agentToolset20260401 = agentToolset20260401)

            @JvmStatic
            fun ofMcpToolset(mcpToolset: BetaManagedAgentsMcpToolset) =
                Tool(mcpToolset = mcpToolset)

            /** A custom tool as returned in API responses. */
            @JvmStatic fun ofCustom(custom: BetaManagedAgentsCustomTool) = Tool(custom = custom)
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitAgentToolset20260401(
                agentToolset20260401: BetaManagedAgentsAgentToolset20260401
            ): T

            fun visitMcpToolset(mcpToolset: BetaManagedAgentsMcpToolset): T

            /** A custom tool as returned in API responses. */
            fun visitCustom(custom: BetaManagedAgentsCustomTool): T

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
                                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401>(),
                            )
                            ?.let { Tool(agentToolset20260401 = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "mcp_toolset" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsMcpToolset>())
                            ?.let { Tool(mcpToolset = it, _json = json) } ?: Tool(_json = json)
                    }
                    "custom" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsCustomTool>())
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

            @JvmField val AGENT = of("agent")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT
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
            AGENT,
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
                AGENT -> Value.AGENT
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
                AGENT -> Known.AGENT
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

        return other is BetaManagedAgentsSessionAgent &&
            id == other.id &&
            description == other.description &&
            mcpServers == other.mcpServers &&
            model == other.model &&
            multiagent == other.multiagent &&
            name == other.name &&
            skills == other.skills &&
            system == other.system &&
            tools == other.tools &&
            type == other.type &&
            version == other.version &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            description,
            mcpServers,
            model,
            multiagent,
            name,
            skills,
            system,
            tools,
            type,
            version,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionAgent{id=$id, description=$description, mcpServers=$mcpServers, model=$model, multiagent=$multiagent, name=$name, skills=$skills, system=$system, tools=$tools, type=$type, version=$version, additionalProperties=$additionalProperties}"
}
