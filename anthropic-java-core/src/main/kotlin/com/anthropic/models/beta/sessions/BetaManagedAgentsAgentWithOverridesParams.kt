// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsAnthropicSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomToolParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpToolsetParams
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.agents.BetaManagedAgentsModelConfigParams
import com.anthropic.models.beta.agents.BetaManagedAgentsSkillParams
import com.anthropic.models.beta.agents.BetaManagedAgentsUrlMcpServerParams
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
 * Reference to an `agent` plus optional configuration overrides. Each provided field replaces the
 * agent's value for the caller's use; the agent resource is unchanged.
 */
class BetaManagedAgentsAgentWithOverridesParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val type: JsonField<Type>,
    private val mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>,
    private val model: JsonField<Model>,
    private val skills: JsonField<List<BetaManagedAgentsSkillParams>>,
    private val system: JsonField<String>,
    private val tools: JsonField<List<Tool>>,
    private val version: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<Model> = JsonMissing.of(),
        @JsonProperty("skills")
        @ExcludeMissing
        skills: JsonField<List<BetaManagedAgentsSkillParams>> = JsonMissing.of(),
        @JsonProperty("system") @ExcludeMissing system: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        @JsonProperty("version") @ExcludeMissing version: JsonField<Int> = JsonMissing.of(),
    ) : this(id, type, mcpServers, model, skills, system, tools, version, mutableMapOf())

    /**
     * The `agent` ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Replacement MCP server list. Full replacement: the provided array becomes the MCP servers.
     * Send an empty array to clear; omit to preserve the agent's servers.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpServers(): Optional<List<BetaManagedAgentsUrlMcpServerParams>> =
        mcpServers.getOptional("mcp_servers")

    /**
     * Replacement model. Accepts the model string, e.g. `claude-opus-4-6`, or a `model_config`
     * object. Omit to use the agent's model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<Model> = model.getOptional("model")

    /**
     * Replacement skill list. Full replacement: the provided array becomes the skills. Send an
     * empty array to clear; omit to preserve the agent's skills.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun skills(): Optional<List<BetaManagedAgentsSkillParams>> = skills.getOptional("skills")

    /**
     * Replacement system prompt. Up to 100,000 characters. Set to null to clear the agent's system
     * prompt; omit to preserve it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun system(): Optional<String> = system.getOptional("system")

    /**
     * Replacement tool list. Full replacement: the provided array becomes the tool configuration.
     * Send an empty array to clear; omit to preserve the agent's tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tools(): Optional<List<Tool>> = tools.getOptional("tools")

    /**
     * The specific `agent` version to use. Omit to use the latest version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun version(): Optional<Int> = version.getOptional("version")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_servers")
    @ExcludeMissing
    fun _mcpServers(): JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = mcpServers

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<Model> = model

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
         * [BetaManagedAgentsAgentWithOverridesParams].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentWithOverridesParams]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var mcpServers: JsonField<MutableList<BetaManagedAgentsUrlMcpServerParams>>? = null
        private var model: JsonField<Model> = JsonMissing.of()
        private var skills: JsonField<MutableList<BetaManagedAgentsSkillParams>>? = null
        private var system: JsonField<String> = JsonMissing.of()
        private var tools: JsonField<MutableList<Tool>>? = null
        private var version: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentWithOverridesParams: BetaManagedAgentsAgentWithOverridesParams
        ) = apply {
            id = betaManagedAgentsAgentWithOverridesParams.id
            type = betaManagedAgentsAgentWithOverridesParams.type
            mcpServers =
                betaManagedAgentsAgentWithOverridesParams.mcpServers
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            model = betaManagedAgentsAgentWithOverridesParams.model
            skills =
                betaManagedAgentsAgentWithOverridesParams.skills
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            system = betaManagedAgentsAgentWithOverridesParams.system
            tools =
                betaManagedAgentsAgentWithOverridesParams.tools
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            version = betaManagedAgentsAgentWithOverridesParams.version
            additionalProperties =
                betaManagedAgentsAgentWithOverridesParams.additionalProperties.toMutableMap()
        }

        /** The `agent` ID. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /**
         * Replacement MCP server list. Full replacement: the provided array becomes the MCP
         * servers. Send an empty array to clear; omit to preserve the agent's servers.
         */
        fun mcpServers(mcpServers: List<BetaManagedAgentsUrlMcpServerParams>) =
            mcpServers(JsonField.of(mcpServers))

        /**
         * Sets [Builder.mcpServers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServers] with a well-typed
         * `List<BetaManagedAgentsUrlMcpServerParams>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun mcpServers(mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>) = apply {
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
         * Replacement model. Accepts the model string, e.g. `claude-opus-4-6`, or a `model_config`
         * object. Omit to use the agent's model.
         */
        fun model(model: Model) = model(JsonField.of(model))

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [Model] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
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
            model(Model.ofBetaManagedAgentsModelConfigParams(betaManagedAgentsModelConfigParams))

        /**
         * Replacement skill list. Full replacement: the provided array becomes the skills. Send an
         * empty array to clear; omit to preserve the agent's skills.
         */
        fun skills(skills: List<BetaManagedAgentsSkillParams>) = skills(JsonField.of(skills))

        /**
         * Sets [Builder.skills] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skills] with a well-typed
         * `List<BetaManagedAgentsSkillParams>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
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
         * Alias for calling [addSkill] with `BetaManagedAgentsSkillParams.ofAnthropic(anthropic)`.
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

        /** Alias for calling [addSkill] with `BetaManagedAgentsSkillParams.ofCustom(custom)`. */
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
         * Replacement system prompt. Up to 100,000 characters. Set to null to clear the agent's
         * system prompt; omit to preserve it.
         */
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

        /**
         * Replacement tool list. Full replacement: the provided array becomes the tool
         * configuration. Send an empty array to clear; omit to preserve the agent's tools.
         */
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

        /** The specific `agent` version to use. Omit to use the latest version. */
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
         * Returns an immutable instance of [BetaManagedAgentsAgentWithOverridesParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentWithOverridesParams =
            BetaManagedAgentsAgentWithOverridesParams(
                checkRequired("id", id),
                checkRequired("type", type),
                (mcpServers ?: JsonMissing.of()).map { it.toImmutable() },
                model,
                (skills ?: JsonMissing.of()).map { it.toImmutable() },
                system,
                (tools ?: JsonMissing.of()).map { it.toImmutable() },
                version,
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
    fun validate(): BetaManagedAgentsAgentWithOverridesParams = apply {
        if (validated) {
            return@apply
        }

        id()
        type().validate()
        mcpServers().ifPresent { it.forEach { it.validate() } }
        model().ifPresent { it.validate() }
        skills().ifPresent { it.forEach { it.validate() } }
        system()
        tools().ifPresent { it.forEach { it.validate() } }
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
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (model.asKnown().getOrNull()?.validity() ?: 0) +
            (skills.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (system.asKnown().isPresent) 1 else 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (version.asKnown().isPresent) 1 else 0)

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

            @JvmField val AGENT_WITH_OVERRIDES = of("agent_with_overrides")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT_WITH_OVERRIDES
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
            AGENT_WITH_OVERRIDES,
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
                AGENT_WITH_OVERRIDES -> Value.AGENT_WITH_OVERRIDES
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
                AGENT_WITH_OVERRIDES -> Known.AGENT_WITH_OVERRIDES
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

    /**
     * Replacement model. Accepts the model string, e.g. `claude-opus-4-6`, or a `model_config`
     * object. Omit to use the agent's model.
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
         * The model that will power your agent.
         *
         * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
         * and options.
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
         * The model that will power your agent.
         *
         * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional details
         * and options.
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
             * The model that will power your agent.
             *
             * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional
             * details and options.
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
             * The model that will power your agent.
             *
             * See [models](https://docs.anthropic.com/en/docs/models-overview) for additional
             * details and options.
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

        return other is BetaManagedAgentsAgentWithOverridesParams &&
            id == other.id &&
            type == other.type &&
            mcpServers == other.mcpServers &&
            model == other.model &&
            skills == other.skills &&
            system == other.system &&
            tools == other.tools &&
            version == other.version &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            type,
            mcpServers,
            model,
            skills,
            system,
            tools,
            version,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentWithOverridesParams{id=$id, type=$type, mcpServers=$mcpServers, model=$model, skills=$skills, system=$system, tools=$tools, version=$version, additionalProperties=$additionalProperties}"
}
