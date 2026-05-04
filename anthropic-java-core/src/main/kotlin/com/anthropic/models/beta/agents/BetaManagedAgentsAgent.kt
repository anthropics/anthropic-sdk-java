// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A Managed Agents `agent`. */
class BetaManagedAgentsAgent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val description: JsonField<String>,
    private val mcpServers: JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>>,
    private val metadata: JsonField<Metadata>,
    private val model: JsonField<BetaManagedAgentsModelConfig>,
    private val name: JsonField<String>,
    private val skills: JsonField<List<Skill>>,
    private val system: JsonField<String>,
    private val tools: JsonField<List<Tool>>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val version: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("description")
        @ExcludeMissing
        description: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        mcpServers: JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("model")
        @ExcludeMissing
        model: JsonField<BetaManagedAgentsModelConfig> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("skills") @ExcludeMissing skills: JsonField<List<Skill>> = JsonMissing.of(),
        @JsonProperty("system") @ExcludeMissing system: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("version") @ExcludeMissing version: JsonField<Int> = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        createdAt,
        description,
        mcpServers,
        metadata,
        model,
        name,
        skills,
        system,
        tools,
        type,
        updatedAt,
        version,
        mutableMapOf(),
    )

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

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
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

    /**
     * Model identifier and configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun model(): BetaManagedAgentsModelConfig = model.getRequired("model")

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
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * The agent's current version. Starts at 1 and increments when the agent is modified.
     *
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
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_servers")
    @ExcludeMissing
    fun _mcpServers(): JsonField<List<BetaManagedAgentsMcpServerUrlDefinition>> = mcpServers

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
    @JsonProperty("model")
    @ExcludeMissing
    fun _model(): JsonField<BetaManagedAgentsModelConfig> = model

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
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsAgent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .description()
         * .mcpServers()
         * .metadata()
         * .model()
         * .name()
         * .skills()
         * .system()
         * .tools()
         * .type()
         * .updatedAt()
         * .version()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var description: JsonField<String>? = null
        private var mcpServers: JsonField<MutableList<BetaManagedAgentsMcpServerUrlDefinition>>? =
            null
        private var metadata: JsonField<Metadata>? = null
        private var model: JsonField<BetaManagedAgentsModelConfig>? = null
        private var name: JsonField<String>? = null
        private var skills: JsonField<MutableList<Skill>>? = null
        private var system: JsonField<String>? = null
        private var tools: JsonField<MutableList<Tool>>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var version: JsonField<Int>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsAgent: BetaManagedAgentsAgent) = apply {
            id = betaManagedAgentsAgent.id
            archivedAt = betaManagedAgentsAgent.archivedAt
            createdAt = betaManagedAgentsAgent.createdAt
            description = betaManagedAgentsAgent.description
            mcpServers = betaManagedAgentsAgent.mcpServers.map { it.toMutableList() }
            metadata = betaManagedAgentsAgent.metadata
            model = betaManagedAgentsAgent.model
            name = betaManagedAgentsAgent.name
            skills = betaManagedAgentsAgent.skills.map { it.toMutableList() }
            system = betaManagedAgentsAgent.system
            tools = betaManagedAgentsAgent.tools.map { it.toMutableList() }
            type = betaManagedAgentsAgent.type
            updatedAt = betaManagedAgentsAgent.updatedAt
            version = betaManagedAgentsAgent.version
            additionalProperties = betaManagedAgentsAgent.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

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

        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

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

        /** The agent's current version. Starts at 1 and increments when the agent is modified. */
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
         * Returns an immutable instance of [BetaManagedAgentsAgent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .createdAt()
         * .description()
         * .mcpServers()
         * .metadata()
         * .model()
         * .name()
         * .skills()
         * .system()
         * .tools()
         * .type()
         * .updatedAt()
         * .version()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgent =
            BetaManagedAgentsAgent(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("createdAt", createdAt),
                checkRequired("description", description),
                checkRequired("mcpServers", mcpServers).map { it.toImmutable() },
                checkRequired("metadata", metadata),
                checkRequired("model", model),
                checkRequired("name", name),
                checkRequired("skills", skills).map { it.toImmutable() },
                checkRequired("system", system),
                checkRequired("tools", tools).map { it.toImmutable() },
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): BetaManagedAgentsAgent = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        createdAt()
        description()
        mcpServers().forEach { it.validate() }
        metadata().validate()
        model().validate()
        name()
        skills().forEach { it.validate() }
        system()
        tools().forEach { it.validate() }
        type().validate()
        updatedAt()
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
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (description.asKnown().isPresent) 1 else 0) +
            (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (model.asKnown().getOrNull()?.validity() ?: 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (skills.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (system.asKnown().isPresent) 1 else 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (version.asKnown().isPresent) 1 else 0)

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

        return other is BetaManagedAgentsAgent &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            createdAt == other.createdAt &&
            description == other.description &&
            mcpServers == other.mcpServers &&
            metadata == other.metadata &&
            model == other.model &&
            name == other.name &&
            skills == other.skills &&
            system == other.system &&
            tools == other.tools &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            version == other.version &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            createdAt,
            description,
            mcpServers,
            metadata,
            model,
            name,
            skills,
            system,
            tools,
            type,
            updatedAt,
            version,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgent{id=$id, archivedAt=$archivedAt, createdAt=$createdAt, description=$description, mcpServers=$mcpServers, metadata=$metadata, model=$model, name=$name, skills=$skills, system=$system, tools=$tools, type=$type, updatedAt=$updatedAt, version=$version, additionalProperties=$additionalProperties}"
}
