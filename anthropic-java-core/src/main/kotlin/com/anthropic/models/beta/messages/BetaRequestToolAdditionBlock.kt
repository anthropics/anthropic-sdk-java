package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonSchemaLocalValidation
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.toolFromClass
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Mid-conversation directive to make a tool available.
 *
 * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``. Under the
 * ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool defined earlier in
 * ``messages``, or a ``tool_definition`` object that carries an inline tool definition in
 * ``definition`` (the same object a ``tools`` entry holds). An ``mcp_toolset`` definition also
 * requires the ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point in
 * the conversation onward.
 */
class BetaRequestToolAdditionBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tool: JsonField<Tool>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool") @ExcludeMissing tool: JsonField<Tool> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(tool, type, cacheControl, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tool(): Tool = tool.getRequired("tool")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_addition")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [tool].
     *
     * Unlike [tool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool") @ExcludeMissing fun _tool(): JsonField<Tool> = tool

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * Returns a mutable builder for constructing an instance of [BetaRequestToolAdditionBlock].
         *
         * The following fields are required:
         * ```java
         * .tool()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaRequestToolAdditionBlock] with the required [tool]
         * set to the given value.
         */
        @JvmStatic fun of(tool: Tool) = builder().tool(tool).build()
    }

    /** A builder for [BetaRequestToolAdditionBlock]. */
    class Builder internal constructor() {

        private var tool: JsonField<Tool>? = null
        private var type: JsonValue = JsonValue.from("tool_addition")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRequestToolAdditionBlock: BetaRequestToolAdditionBlock) = apply {
            tool = betaRequestToolAdditionBlock.tool
            type = betaRequestToolAdditionBlock.type
            cacheControl = betaRequestToolAdditionBlock.cacheControl
            additionalProperties = betaRequestToolAdditionBlock.additionalProperties.toMutableMap()
        }

        fun tool(tool: Tool) = tool(JsonField.of(tool))

        /**
         * Sets [Builder.tool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tool] with a well-typed [Tool] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tool(tool: JsonField<Tool>) = apply { this.tool = tool }

        /** Alias for calling [tool] with `Tool.ofReference(reference)`. */
        fun tool(reference: BetaToolChangeToolReference) = tool(Tool.ofReference(reference))

        /** Alias for calling [tool] with `reference.toParam()`. */
        fun tool(reference: BetaResponseToolChangeToolReference) = tool(reference.toParam())

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun referenceTool(name: String) =
            tool(BetaToolChangeToolReference.builder().name(name).build())

        /** Alias for calling [tool] with `Tool.ofMcpToolReference(mcpToolReference)`. */
        fun tool(mcpToolReference: BetaToolChangeMcpToolReference) =
            tool(Tool.ofMcpToolReference(mcpToolReference))

        /** Alias for calling [tool] with `mcpToolReference.toParam()`. */
        fun tool(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            tool(mcpToolReference.toParam())

        /** Alias for calling [tool] with `Tool.ofMcpToolsetReference(mcpToolsetReference)`. */
        fun tool(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            tool(Tool.ofMcpToolsetReference(mcpToolsetReference))

        /** Alias for calling [tool] with `mcpToolsetReference.toParam()`. */
        fun tool(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            tool(mcpToolsetReference.toParam())

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun mcpToolsetReferenceTool(serverName: String) =
            tool(BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build())

        /** Alias for calling [tool] with `Tool.ofDefinition(definition)`. */
        fun tool(definition: BetaToolChangeToolDefinitionParam) =
            tool(Tool.ofDefinition(definition))

        /** Alias for calling [tool] with `definition.toParam()`. */
        fun tool(definition: BetaToolChangeToolDefinition) = tool(definition.toParam())

        /**
         * Alias for calling [tool] with the following:
         * ```java
         * BetaToolChangeToolDefinitionParam.builder()
         *     .definition(definition)
         *     .build()
         * ```
         */
        fun definitionTool(definition: BetaToolUnion) =
            tool(BetaToolChangeToolDefinitionParam.builder().definition(definition).build())

        /** Alias for calling [definitionTool] with `definition.toParam()`. */
        fun definitionTool(definition: BetaResponseToolUnion) = definitionTool(definition.toParam())

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)`.
         */
        fun definitionTool(betaResponseTool: BetaResponseTool) =
            definitionTool(BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)`.
         */
        fun definitionTool(toolBash20241022: BetaToolBash20241022) =
            definitionTool(BetaResponseToolUnion.ofToolBash20241022(toolBash20241022))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)`.
         */
        fun definitionTool(toolBash20250124: BetaToolBash20250124) =
            definitionTool(BetaResponseToolUnion.ofToolBash20250124(toolBash20250124))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun definitionTool(codeExecutionTool20250522: BetaCodeExecutionTool20250522) =
            definitionTool(
                BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)
            )

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun definitionTool(codeExecutionTool20250825: BetaCodeExecutionTool20250825) =
            definitionTool(
                BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)
            )

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
         */
        fun definitionTool(codeExecutionTool20260120: BetaCodeExecutionTool20260120) =
            definitionTool(
                BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)
            )

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)`.
         */
        fun definitionTool(codeExecutionTool20260521: BetaCodeExecutionTool20260521) =
            definitionTool(
                BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)
            )

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)`.
         */
        fun definitionTool(browserToolset20260801: BetaBrowserToolset20260801) =
            definitionTool(BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)`.
         */
        fun definitionTool(toolComputerUse20241022: BetaToolComputerUse20241022) =
            definitionTool(BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun definitionTool(memoryTool20250818: BetaMemoryTool20250818) =
            definitionTool(BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)`.
         */
        fun definitionTool(toolComputerUse20250124: BetaToolComputerUse20250124) =
            definitionTool(BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)`.
         */
        fun definitionTool(toolTextEditor20241022: BetaToolTextEditor20241022) =
            definitionTool(BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)`.
         */
        fun definitionTool(toolComputerUse20251124: BetaToolComputerUse20251124) =
            definitionTool(BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)`.
         */
        fun definitionTool(computerToolset20260801: BetaComputerToolset20260801) =
            definitionTool(BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)`.
         */
        fun definitionTool(toolTextEditor20250124: BetaToolTextEditor20250124) =
            definitionTool(BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)`.
         */
        fun definitionTool(toolTextEditor20250429: BetaToolTextEditor20250429) =
            definitionTool(BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)`.
         */
        fun definitionTool(toolTextEditor20250728: BetaToolTextEditor20250728) =
            definitionTool(BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun definitionTool(webSearchTool20250305: BetaWebSearchTool20250305) =
            definitionTool(BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun definitionTool(webFetchTool20250910: BetaWebFetchTool20250910) =
            definitionTool(BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
         */
        fun definitionTool(webSearchTool20260209: BetaWebSearchTool20260209) =
            definitionTool(BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
         */
        fun definitionTool(webFetchTool20260209: BetaWebFetchTool20260209) =
            definitionTool(BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)`.
         */
        fun definitionTool(webFetchTool20260309: BetaWebFetchTool20260309) =
            definitionTool(BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)`.
         */
        fun definitionTool(webSearchTool20260318: BetaWebSearchTool20260318) =
            definitionTool(BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)`.
         */
        fun definitionTool(webFetchTool20260318: BetaWebFetchTool20260318) =
            definitionTool(BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)`.
         */
        fun definitionTool(advisorTool20260301: BetaAdvisorTool20260301) =
            definitionTool(BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301))

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)`.
         */
        fun definitionTool(toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119) =
            definitionTool(
                BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)
            )

        /**
         * Alias for calling [definitionTool] with
         * `BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)`.
         */
        fun definitionTool(toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119) =
            definitionTool(
                BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)
            )

        /**
         * Alias for calling [definitionTool] with `BetaResponseToolUnion.ofMcpToolset(mcpToolset)`.
         */
        fun definitionTool(mcpToolset: BetaMcpToolset) =
            definitionTool(BetaResponseToolUnion.ofMcpToolset(mcpToolset))

        /** Alias for calling [definitionTool] with `BetaToolUnion.ofBetaTool(betaTool)`. */
        fun definitionTool(betaTool: BetaTool) = definitionTool(BetaToolUnion.ofBetaTool(betaTool))

        /**
         * Sets [tool] to a `tool_definition` holding a [BetaTool] where the JSON schema describing
         * the tool's parameters is derived from the fields of a given class. Local validation of
         * that JSON schema can be performed to check if the schema is likely to pass remote
         * validation by the AI model. By default, local validation is enabled; disable it by
         * setting [localValidation] to [JsonSchemaLocalValidation.NO].
         *
         * @see MessageCreateParams.Builder.addTool
         */
        @JvmOverloads
        fun definitionTool(
            toolParametersType: Class<*>,
            localValidation: JsonSchemaLocalValidation = JsonSchemaLocalValidation.YES,
        ) = definitionTool(toolFromClass(toolParametersType, localValidation))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_addition")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
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
         * Returns an immutable instance of [BetaRequestToolAdditionBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .tool()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRequestToolAdditionBlock =
            BetaRequestToolAdditionBlock(
                checkRequired("tool", tool),
                type,
                cacheControl,
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
    fun validate(): BetaRequestToolAdditionBlock = apply {
        if (validated) {
            return@apply
        }

        tool().validate()
        _type().let {
            if (it != JsonValue.from("tool_addition")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
        (tool.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("tool_addition")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    @JsonDeserialize(using = Tool.Deserializer::class)
    @JsonSerialize(using = Tool.Serializer::class)
    class Tool
    private constructor(
        private val reference: BetaToolChangeToolReference? = null,
        private val mcpToolReference: BetaToolChangeMcpToolReference? = null,
        private val mcpToolsetReference: BetaToolChangeMcpToolsetReference? = null,
        private val definition: BetaToolChangeToolDefinitionParam? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitReference(reference: BetaToolChangeToolReference): Type =
                        Type.TOOL_REFERENCE

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ): Type = Type.MCP_TOOL_REFERENCE

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ): Type = Type.MCP_TOOLSET_REFERENCE

                    override fun visitDefinition(
                        definition: BetaToolChangeToolDefinitionParam
                    ): Type = Type.TOOL_DEFINITION

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun name(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaToolChangeToolReference
                    ): Optional<String> = Optional.of(reference.name())

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.name())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.empty()

                    override fun visitDefinition(
                        definition: BetaToolChangeToolDefinitionParam
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("name").asKnown()
                }
            )

        fun serverName(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitReference(
                        reference: BetaToolChangeToolReference
                    ): Optional<String> = Optional.empty()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ): Optional<String> = Optional.of(mcpToolReference.serverName())

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ): Optional<String> = Optional.of(mcpToolsetReference.serverName())

                    override fun visitDefinition(
                        definition: BetaToolChangeToolDefinitionParam
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("server_name").asKnown()
                }
            )

        /**
         * Reference to a single tool, by the name the model uses to call it: a tool declared in
         * ``tools`` or defined by an earlier ``tool_addition`` block. Does not accept the composed
         * ``{server}_{name}`` form the server assigns to MCP-resolved tools; use
         * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
         */
        fun reference(): Optional<BetaToolChangeToolReference> = Optional.ofNullable(reference)

        /**
         * Reference to a single MCP tool by its server and remote name; the same
         * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
         */
        fun mcpToolReference(): Optional<BetaToolChangeMcpToolReference> =
            Optional.ofNullable(mcpToolReference)

        /** Reference to every tool in the named MCP server's toolset. */
        fun mcpToolsetReference(): Optional<BetaToolChangeMcpToolsetReference> =
            Optional.ofNullable(mcpToolsetReference)

        /**
         * A tool defined by value: `definition` is a `tools` entry (any kind `tools` accepts, an
         * MCP toolset included). An `mcp_toolset` given here also requires the
         * `mcp-client-2026-09-15` beta.
         */
        fun definition(): Optional<BetaToolChangeToolDefinitionParam> =
            Optional.ofNullable(definition)

        fun isReference(): Boolean = reference != null

        fun isMcpToolReference(): Boolean = mcpToolReference != null

        fun isMcpToolsetReference(): Boolean = mcpToolsetReference != null

        fun isDefinition(): Boolean = definition != null

        /**
         * Reference to a single tool, by the name the model uses to call it: a tool declared in
         * ``tools`` or defined by an earlier ``tool_addition`` block. Does not accept the composed
         * ``{server}_{name}`` form the server assigns to MCP-resolved tools; use
         * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
         */
        fun asReference(): BetaToolChangeToolReference = reference.getOrThrow("reference")

        /**
         * Reference to a single MCP tool by its server and remote name; the same
         * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
         */
        fun asMcpToolReference(): BetaToolChangeMcpToolReference =
            mcpToolReference.getOrThrow("mcpToolReference")

        /** Reference to every tool in the named MCP server's toolset. */
        fun asMcpToolsetReference(): BetaToolChangeMcpToolsetReference =
            mcpToolsetReference.getOrThrow("mcpToolsetReference")

        /**
         * A tool defined by value: `definition` is a `tools` entry (any kind `tools` accepts, an
         * MCP toolset included). An `mcp_toolset` given here also requires the
         * `mcp-client-2026-09-15` beta.
         */
        fun asDefinition(): BetaToolChangeToolDefinitionParam = definition.getOrThrow("definition")

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
         *     public Optional<String> visitReference(BetaToolChangeToolReference reference) {
         *         return Optional.of(reference.toString());
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
                reference != null -> visitor.visitReference(reference)
                mcpToolReference != null -> visitor.visitMcpToolReference(mcpToolReference)
                mcpToolsetReference != null -> visitor.visitMcpToolsetReference(mcpToolsetReference)
                definition != null -> visitor.visitDefinition(definition)
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
                    override fun visitReference(reference: BetaToolChangeToolReference) {
                        reference.validate()
                    }

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ) {
                        mcpToolReference.validate()
                    }

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ) {
                        mcpToolsetReference.validate()
                    }

                    override fun visitDefinition(definition: BetaToolChangeToolDefinitionParam) {
                        definition.validate()
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
                    override fun visitReference(reference: BetaToolChangeToolReference) =
                        reference.validity()

                    override fun visitMcpToolReference(
                        mcpToolReference: BetaToolChangeMcpToolReference
                    ) = mcpToolReference.validity()

                    override fun visitMcpToolsetReference(
                        mcpToolsetReference: BetaToolChangeMcpToolsetReference
                    ) = mcpToolsetReference.validity()

                    override fun visitDefinition(definition: BetaToolChangeToolDefinitionParam) =
                        definition.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tool &&
                reference == other.reference &&
                mcpToolReference == other.mcpToolReference &&
                mcpToolsetReference == other.mcpToolsetReference &&
                definition == other.definition
        }

        override fun hashCode(): Int =
            Objects.hash(reference, mcpToolReference, mcpToolsetReference, definition)

        override fun toString(): String =
            when {
                reference != null -> "Tool{reference=$reference}"
                mcpToolReference != null -> "Tool{mcpToolReference=$mcpToolReference}"
                mcpToolsetReference != null -> "Tool{mcpToolsetReference=$mcpToolsetReference}"
                definition != null -> "Tool{definition=$definition}"
                _json != null -> "Tool{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Tool")
            }

        companion object {

            /**
             * Reference to a single tool, by the name the model uses to call it: a tool declared in
             * ``tools`` or defined by an earlier ``tool_addition`` block. Does not accept the
             * composed ``{server}_{name}`` form the server assigns to MCP-resolved tools; use
             * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
             */
            @JvmStatic
            fun ofReference(reference: BetaToolChangeToolReference) = Tool(reference = reference)

            /**
             * Returns an immutable instance of [Tool] whose [ofReference] variant is built from the
             * given required [name].
             */
            @JvmStatic
            fun ofReference(name: String) = ofReference(BetaToolChangeToolReference.of(name))

            /**
             * Reference to a single MCP tool by its server and remote name; the same
             * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
             */
            @JvmStatic
            fun ofMcpToolReference(mcpToolReference: BetaToolChangeMcpToolReference) =
                Tool(mcpToolReference = mcpToolReference)

            /** Reference to every tool in the named MCP server's toolset. */
            @JvmStatic
            fun ofMcpToolsetReference(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
                Tool(mcpToolsetReference = mcpToolsetReference)

            /**
             * Returns an immutable instance of [Tool] whose [ofMcpToolsetReference] variant is
             * built from the given required [serverName].
             */
            @JvmStatic
            fun ofMcpToolsetReference(serverName: String) =
                ofMcpToolsetReference(BetaToolChangeMcpToolsetReference.of(serverName))

            /**
             * A tool defined by value: `definition` is a `tools` entry (any kind `tools` accepts,
             * an MCP toolset included). An `mcp_toolset` given here also requires the
             * `mcp-client-2026-09-15` beta.
             */
            @JvmStatic
            fun ofDefinition(definition: BetaToolChangeToolDefinitionParam) =
                Tool(definition = definition)

            /**
             * Returns an immutable instance of [Tool] whose [ofDefinition] variant is built from
             * the given required [definition].
             */
            @JvmStatic
            fun ofDefinition(definition: BetaToolUnion) =
                ofDefinition(BetaToolChangeToolDefinitionParam.of(definition))
        }

        /** An interface that defines how to map each variant of [Tool] to a value of type [T]. */
        interface Visitor<out T> {

            /**
             * Reference to a single tool, by the name the model uses to call it: a tool declared in
             * ``tools`` or defined by an earlier ``tool_addition`` block. Does not accept the
             * composed ``{server}_{name}`` form the server assigns to MCP-resolved tools; use
             * ``mcp_tool_reference`` or ``mcp_toolset_reference`` for those.
             */
            fun visitReference(reference: BetaToolChangeToolReference): T

            /**
             * Reference to a single MCP tool by its server and remote name; the same
             * ``server_name``/``name`` pair ``mcp_tool_use`` carries.
             */
            fun visitMcpToolReference(mcpToolReference: BetaToolChangeMcpToolReference): T

            /** Reference to every tool in the named MCP server's toolset. */
            fun visitMcpToolsetReference(mcpToolsetReference: BetaToolChangeMcpToolsetReference): T

            /**
             * A tool defined by value: `definition` is a `tools` entry (any kind `tools` accepts,
             * an MCP toolset included). An `mcp_toolset` given here also requires the
             * `mcp-client-2026-09-15` beta.
             */
            fun visitDefinition(definition: BetaToolChangeToolDefinitionParam): T

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
                    "tool_reference" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaToolChangeToolReference>())
                            ?.let { Tool(reference = it, _json = json) } ?: Tool(_json = json)
                    }
                    "mcp_tool_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaToolChangeMcpToolReference>(),
                            )
                            ?.let { Tool(mcpToolReference = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "mcp_toolset_reference" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaToolChangeMcpToolsetReference>(),
                            )
                            ?.let { Tool(mcpToolsetReference = it, _json = json) }
                            ?: Tool(_json = json)
                    }
                    "tool_definition" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaToolChangeToolDefinitionParam>(),
                            )
                            ?.let { Tool(definition = it, _json = json) } ?: Tool(_json = json)
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
                    value.reference != null -> generator.writeObject(value.reference)
                    value.mcpToolReference != null -> generator.writeObject(value.mcpToolReference)
                    value.mcpToolsetReference != null ->
                        generator.writeObject(value.mcpToolsetReference)
                    value.definition != null -> generator.writeObject(value.definition)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Tool")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val TOOL_REFERENCE = of("tool_reference")

                @JvmField val MCP_TOOL_REFERENCE = of("mcp_tool_reference")

                @JvmField val MCP_TOOLSET_REFERENCE = of("mcp_toolset_reference")

                @JvmField val TOOL_DEFINITION = of("tool_definition")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                TOOL_REFERENCE,
                MCP_TOOL_REFERENCE,
                MCP_TOOLSET_REFERENCE,
                TOOL_DEFINITION,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                TOOL_REFERENCE,
                MCP_TOOL_REFERENCE,
                MCP_TOOLSET_REFERENCE,
                TOOL_DEFINITION,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    TOOL_REFERENCE -> Value.TOOL_REFERENCE
                    MCP_TOOL_REFERENCE -> Value.MCP_TOOL_REFERENCE
                    MCP_TOOLSET_REFERENCE -> Value.MCP_TOOLSET_REFERENCE
                    TOOL_DEFINITION -> Value.TOOL_DEFINITION
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    TOOL_REFERENCE -> Known.TOOL_REFERENCE
                    MCP_TOOL_REFERENCE -> Known.MCP_TOOL_REFERENCE
                    MCP_TOOLSET_REFERENCE -> Known.MCP_TOOLSET_REFERENCE
                    TOOL_DEFINITION -> Known.TOOL_DEFINITION
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRequestToolAdditionBlock &&
            tool == other.tool &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(tool, type, cacheControl, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRequestToolAdditionBlock{tool=$tool, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
