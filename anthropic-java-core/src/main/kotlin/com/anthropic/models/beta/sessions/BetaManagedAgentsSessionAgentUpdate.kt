// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentToolset20260401Params
import com.anthropic.models.beta.agents.BetaManagedAgentsCustomToolParams
import com.anthropic.models.beta.agents.BetaManagedAgentsMcpToolsetParams
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
 * Mid-session agent configuration update. Only `tools` and `mcp_servers` are updatable. Full
 * replacement: the provided array becomes the new value. To preserve existing entries, GET the
 * session, modify the array, and POST it back.
 */
class BetaManagedAgentsSessionAgentUpdate
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>>,
    private val tools: JsonField<List<Tool>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mcp_servers")
        @ExcludeMissing
        mcpServers: JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = JsonMissing.of(),
        @JsonProperty("tools") @ExcludeMissing tools: JsonField<List<Tool>> = JsonMissing.of(),
    ) : this(mcpServers, tools, mutableMapOf())

    /**
     * Replacement MCP server list. Full replacement: the provided array becomes the new value. Send
     * an empty array to clear; omit to preserve.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpServers(): Optional<List<BetaManagedAgentsUrlMcpServerParams>> =
        mcpServers.getOptional("mcp_servers")

    /**
     * Replacement tool list. Full replacement: the provided array becomes the new value. Send an
     * empty array to clear; omit to preserve.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tools(): Optional<List<Tool>> = tools.getOptional("tools")

    /**
     * Returns the raw JSON value of [mcpServers].
     *
     * Unlike [mcpServers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_servers")
    @ExcludeMissing
    fun _mcpServers(): JsonField<List<BetaManagedAgentsUrlMcpServerParams>> = mcpServers

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsSessionAgentUpdate].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionAgentUpdate]. */
    class Builder internal constructor() {

        private var mcpServers: JsonField<MutableList<BetaManagedAgentsUrlMcpServerParams>>? = null
        private var tools: JsonField<MutableList<Tool>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionAgentUpdate: BetaManagedAgentsSessionAgentUpdate
        ) = apply {
            mcpServers = betaManagedAgentsSessionAgentUpdate.mcpServers.map { it.toMutableList() }
            tools = betaManagedAgentsSessionAgentUpdate.tools.map { it.toMutableList() }
            additionalProperties =
                betaManagedAgentsSessionAgentUpdate.additionalProperties.toMutableMap()
        }

        /**
         * Replacement MCP server list. Full replacement: the provided array becomes the new value.
         * Send an empty array to clear; omit to preserve.
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
         * Replacement tool list. Full replacement: the provided array becomes the new value. Send
         * an empty array to clear; omit to preserve.
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
         * Returns an immutable instance of [BetaManagedAgentsSessionAgentUpdate].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSessionAgentUpdate =
            BetaManagedAgentsSessionAgentUpdate(
                (mcpServers ?: JsonMissing.of()).map { it.toImmutable() },
                (tools ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsSessionAgentUpdate = apply {
        if (validated) {
            return@apply
        }

        mcpServers().ifPresent { it.forEach { it.validate() } }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (mcpServers.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

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

        return other is BetaManagedAgentsSessionAgentUpdate &&
            mcpServers == other.mcpServers &&
            tools == other.tools &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(mcpServers, tools, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionAgentUpdate{mcpServers=$mcpServers, tools=$tools, additionalProperties=$additionalProperties}"
}
