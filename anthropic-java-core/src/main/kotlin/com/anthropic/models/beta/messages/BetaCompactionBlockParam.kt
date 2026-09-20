package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A compaction block containing summary of previous context.
 *
 * Users should round-trip these blocks from responses to subsequent requests to maintain context
 * across compaction boundaries.
 *
 * When content is None, the block represents a failed compaction. The server treats these as
 * no-ops. Empty string content is not allowed.
 */
class BetaCompactionBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val content: JsonField<String>,
    private val encryptedContent: JsonField<String>,
    private val signature: JsonField<String>,
    private val toolChanges: JsonField<List<ToolChange>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("content") @ExcludeMissing content: JsonField<String> = JsonMissing.of(),
        @JsonProperty("encrypted_content")
        @ExcludeMissing
        encryptedContent: JsonField<String> = JsonMissing.of(),
        @JsonProperty("signature") @ExcludeMissing signature: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tool_changes")
        @ExcludeMissing
        toolChanges: JsonField<List<ToolChange>> = JsonMissing.of(),
    ) : this(type, cacheControl, content, encryptedContent, signature, toolChanges, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compaction")
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
     * Summary of previously compacted content, or null if compaction failed
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun content(): Optional<String> = content.getOptional("content")

    /**
     * Opaque metadata from prior compaction, to be round-tripped verbatim
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun encryptedContent(): Optional<String> = encryptedContent.getOptional("encrypted_content")

    /**
     * The block's signature as returned, to be sent back verbatim
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun signature(): Optional<String> = signature.getOptional("signature")

    /**
     * The tool changes of the compacted range, as the server returned them on this block: the
     * `tool_addition` and `tool_removal` entries that take the request's `tools` to the tool set in
     * effect at the end of the range. Send them back unchanged with the block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun toolChanges(): Optional<List<ToolChange>> = toolChanges.getOptional("tool_changes")

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<String> = content

    /**
     * Returns the raw JSON value of [encryptedContent].
     *
     * Unlike [encryptedContent], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("encrypted_content")
    @ExcludeMissing
    fun _encryptedContent(): JsonField<String> = encryptedContent

    /**
     * Returns the raw JSON value of [signature].
     *
     * Unlike [signature], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signature") @ExcludeMissing fun _signature(): JsonField<String> = signature

    /**
     * Returns the raw JSON value of [toolChanges].
     *
     * Unlike [toolChanges], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_changes")
    @ExcludeMissing
    fun _toolChanges(): JsonField<List<ToolChange>> = toolChanges

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

        /** Returns a mutable builder for constructing an instance of [BetaCompactionBlockParam]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCompactionBlockParam]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("compaction")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var content: JsonField<String> = JsonMissing.of()
        private var encryptedContent: JsonField<String> = JsonMissing.of()
        private var signature: JsonField<String> = JsonMissing.of()
        private var toolChanges: JsonField<MutableList<ToolChange>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCompactionBlockParam: BetaCompactionBlockParam) = apply {
            type = betaCompactionBlockParam.type
            cacheControl = betaCompactionBlockParam.cacheControl
            content = betaCompactionBlockParam.content
            encryptedContent = betaCompactionBlockParam.encryptedContent
            signature = betaCompactionBlockParam.signature
            toolChanges =
                betaCompactionBlockParam.toolChanges
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaCompactionBlockParam.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compaction")
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

        /** Summary of previously compacted content, or null if compaction failed */
        fun content(content: String?) = content(JsonField.ofNullable(content))

        /** Alias for calling [Builder.content] with `content.orElse(null)`. */
        fun content(content: Optional<String>) = content(content.getOrNull())

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<String>) = apply { this.content = content }

        /** Opaque metadata from prior compaction, to be round-tripped verbatim */
        fun encryptedContent(encryptedContent: String?) =
            encryptedContent(JsonField.ofNullable(encryptedContent))

        /** Alias for calling [Builder.encryptedContent] with `encryptedContent.orElse(null)`. */
        fun encryptedContent(encryptedContent: Optional<String>) =
            encryptedContent(encryptedContent.getOrNull())

        /**
         * Sets [Builder.encryptedContent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.encryptedContent] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun encryptedContent(encryptedContent: JsonField<String>) = apply {
            this.encryptedContent = encryptedContent
        }

        /** The block's signature as returned, to be sent back verbatim */
        fun signature(signature: String?) = signature(JsonField.ofNullable(signature))

        /** Alias for calling [Builder.signature] with `signature.orElse(null)`. */
        fun signature(signature: Optional<String>) = signature(signature.getOrNull())

        /**
         * Sets [Builder.signature] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signature] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signature(signature: JsonField<String>) = apply { this.signature = signature }

        /**
         * The tool changes of the compacted range, as the server returned them on this block: the
         * `tool_addition` and `tool_removal` entries that take the request's `tools` to the tool
         * set in effect at the end of the range. Send them back unchanged with the block.
         */
        fun toolChanges(toolChanges: List<ToolChange>?) =
            toolChanges(JsonField.ofNullable(toolChanges))

        /** Alias for calling [Builder.toolChanges] with `toolChanges.orElse(null)`. */
        fun toolChanges(toolChanges: Optional<List<ToolChange>>) =
            toolChanges(toolChanges.getOrNull())

        /**
         * Sets [Builder.toolChanges] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolChanges] with a well-typed `List<ToolChange>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun toolChanges(toolChanges: JsonField<List<ToolChange>>) = apply {
            this.toolChanges = toolChanges.map { it.toMutableList() }
        }

        /**
         * Adds a single [ToolChange] to [toolChanges].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addToolChange(toolChange: ToolChange) = apply {
            toolChanges =
                (toolChanges ?: JsonField.of(mutableListOf())).also {
                    checkKnown("toolChanges", it).add(toolChange)
                }
        }

        /** Alias for calling [addToolChange] with `ToolChange.ofAddition(addition)`. */
        fun addToolChange(addition: BetaRequestToolAdditionBlock) =
            addToolChange(ToolChange.ofAddition(addition))

        /** Alias for calling [addToolChange] with `addition.toParam()`. */
        fun addToolChange(addition: BetaResponseToolAdditionBlock) =
            addToolChange(addition.toParam())

        /**
         * Alias for calling [addToolChange] with the following:
         * ```java
         * BetaRequestToolAdditionBlock.builder()
         *     .tool(tool)
         *     .build()
         * ```
         */
        fun addAdditionToolChange(tool: BetaRequestToolAdditionBlock.Tool) =
            addToolChange(BetaRequestToolAdditionBlock.builder().tool(tool).build())

        /**
         * Alias for calling [addAdditionToolChange] with
         * `BetaRequestToolAdditionBlock.Tool.ofReference(reference)`.
         */
        fun addAdditionToolChange(reference: BetaToolChangeToolReference) =
            addAdditionToolChange(BetaRequestToolAdditionBlock.Tool.ofReference(reference))

        /** Alias for calling [addAdditionToolChange] with `reference.toParam()`. */
        fun addAdditionToolChange(reference: BetaResponseToolChangeToolReference) =
            addAdditionToolChange(reference.toParam())

        /**
         * Alias for calling [addAdditionToolChange] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun addReferenceAdditionToolChange(name: String) =
            addAdditionToolChange(BetaToolChangeToolReference.builder().name(name).build())

        /**
         * Alias for calling [addAdditionToolChange] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        fun addAdditionToolChange(mcpToolReference: BetaToolChangeMcpToolReference) =
            addAdditionToolChange(
                BetaRequestToolAdditionBlock.Tool.ofMcpToolReference(mcpToolReference)
            )

        /** Alias for calling [addAdditionToolChange] with `mcpToolReference.toParam()`. */
        fun addAdditionToolChange(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            addAdditionToolChange(mcpToolReference.toParam())

        /**
         * Alias for calling [addAdditionToolChange] with
         * `BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        fun addAdditionToolChange(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            addAdditionToolChange(
                BetaRequestToolAdditionBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /** Alias for calling [addAdditionToolChange] with `mcpToolsetReference.toParam()`. */
        fun addAdditionToolChange(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            addAdditionToolChange(mcpToolsetReference.toParam())

        /**
         * Alias for calling [addAdditionToolChange] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun addMcpToolsetReferenceAdditionToolChange(serverName: String) =
            addAdditionToolChange(
                BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build()
            )

        /**
         * Alias for calling [addAdditionToolChange] with
         * `BetaRequestToolAdditionBlock.Tool.ofDefinition(definition)`.
         */
        fun addAdditionToolChange(definition: BetaToolChangeToolDefinitionParam) =
            addAdditionToolChange(BetaRequestToolAdditionBlock.Tool.ofDefinition(definition))

        /** Alias for calling [addAdditionToolChange] with `definition.toParam()`. */
        fun addAdditionToolChange(definition: BetaToolChangeToolDefinition) =
            addAdditionToolChange(definition.toParam())

        /**
         * Alias for calling [addAdditionToolChange] with the following:
         * ```java
         * BetaToolChangeToolDefinitionParam.builder()
         *     .definition(definition)
         *     .build()
         * ```
         */
        fun addDefinitionAdditionToolChange(definition: BetaToolUnion) =
            addAdditionToolChange(
                BetaToolChangeToolDefinitionParam.builder().definition(definition).build()
            )

        /** Alias for calling [addDefinitionAdditionToolChange] with `definition.toParam()`. */
        fun addDefinitionAdditionToolChange(definition: BetaResponseToolUnion) =
            addDefinitionAdditionToolChange(definition.toParam())

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)`.
         */
        fun addDefinitionAdditionToolChange(betaResponseTool: BetaResponseTool) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofBetaResponseTool(betaResponseTool)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)`.
         */
        fun addDefinitionAdditionToolChange(toolBash20241022: BetaToolBash20241022) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolBash20241022(toolBash20241022)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)`.
         */
        fun addDefinitionAdditionToolChange(toolBash20250124: BetaToolBash20250124) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolBash20250124(toolBash20250124)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)`.
         */
        fun addDefinitionAdditionToolChange(
            codeExecutionTool20250522: BetaCodeExecutionTool20250522
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofCodeExecutionTool20250522(codeExecutionTool20250522)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)`.
         */
        fun addDefinitionAdditionToolChange(
            codeExecutionTool20250825: BetaCodeExecutionTool20250825
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofCodeExecutionTool20250825(codeExecutionTool20250825)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)`.
         */
        fun addDefinitionAdditionToolChange(
            codeExecutionTool20260120: BetaCodeExecutionTool20260120
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofCodeExecutionTool20260120(codeExecutionTool20260120)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)`.
         */
        fun addDefinitionAdditionToolChange(
            codeExecutionTool20260521: BetaCodeExecutionTool20260521
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofCodeExecutionTool20260521(codeExecutionTool20260521)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)`.
         */
        fun addDefinitionAdditionToolChange(browserToolset20260801: BetaBrowserToolset20260801) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofBrowserToolset20260801(browserToolset20260801)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)`.
         */
        fun addDefinitionAdditionToolChange(toolComputerUse20241022: BetaToolComputerUse20241022) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolComputerUse20241022(toolComputerUse20241022)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)`.
         */
        fun addDefinitionAdditionToolChange(memoryTool20250818: BetaMemoryTool20250818) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofMemoryTool20250818(memoryTool20250818)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)`.
         */
        fun addDefinitionAdditionToolChange(toolComputerUse20250124: BetaToolComputerUse20250124) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolComputerUse20250124(toolComputerUse20250124)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)`.
         */
        fun addDefinitionAdditionToolChange(toolTextEditor20241022: BetaToolTextEditor20241022) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolTextEditor20241022(toolTextEditor20241022)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)`.
         */
        fun addDefinitionAdditionToolChange(toolComputerUse20251124: BetaToolComputerUse20251124) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolComputerUse20251124(toolComputerUse20251124)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)`.
         */
        fun addDefinitionAdditionToolChange(computerToolset20260801: BetaComputerToolset20260801) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofComputerToolset20260801(computerToolset20260801)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)`.
         */
        fun addDefinitionAdditionToolChange(toolTextEditor20250124: BetaToolTextEditor20250124) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolTextEditor20250124(toolTextEditor20250124)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)`.
         */
        fun addDefinitionAdditionToolChange(toolTextEditor20250429: BetaToolTextEditor20250429) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolTextEditor20250429(toolTextEditor20250429)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)`.
         */
        fun addDefinitionAdditionToolChange(toolTextEditor20250728: BetaToolTextEditor20250728) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolTextEditor20250728(toolTextEditor20250728)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)`.
         */
        fun addDefinitionAdditionToolChange(webSearchTool20250305: BetaWebSearchTool20250305) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebSearchTool20250305(webSearchTool20250305)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)`.
         */
        fun addDefinitionAdditionToolChange(webFetchTool20250910: BetaWebFetchTool20250910) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebFetchTool20250910(webFetchTool20250910)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)`.
         */
        fun addDefinitionAdditionToolChange(webSearchTool20260209: BetaWebSearchTool20260209) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebSearchTool20260209(webSearchTool20260209)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)`.
         */
        fun addDefinitionAdditionToolChange(webFetchTool20260209: BetaWebFetchTool20260209) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebFetchTool20260209(webFetchTool20260209)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)`.
         */
        fun addDefinitionAdditionToolChange(webFetchTool20260309: BetaWebFetchTool20260309) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebFetchTool20260309(webFetchTool20260309)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)`.
         */
        fun addDefinitionAdditionToolChange(webSearchTool20260318: BetaWebSearchTool20260318) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebSearchTool20260318(webSearchTool20260318)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)`.
         */
        fun addDefinitionAdditionToolChange(webFetchTool20260318: BetaWebFetchTool20260318) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofWebFetchTool20260318(webFetchTool20260318)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)`.
         */
        fun addDefinitionAdditionToolChange(advisorTool20260301: BetaAdvisorTool20260301) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofAdvisorTool20260301(advisorTool20260301)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)`.
         */
        fun addDefinitionAdditionToolChange(
            toolSearchToolBm25_20251119: BetaToolSearchToolBm25_20251119
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolSearchToolBm25_20251119(toolSearchToolBm25_20251119)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)`.
         */
        fun addDefinitionAdditionToolChange(
            toolSearchToolRegex20251119: BetaToolSearchToolRegex20251119
        ) =
            addDefinitionAdditionToolChange(
                BetaResponseToolUnion.ofToolSearchToolRegex20251119(toolSearchToolRegex20251119)
            )

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaResponseToolUnion.ofMcpToolset(mcpToolset)`.
         */
        fun addDefinitionAdditionToolChange(mcpToolset: BetaMcpToolset) =
            addDefinitionAdditionToolChange(BetaResponseToolUnion.ofMcpToolset(mcpToolset))

        /**
         * Alias for calling [addDefinitionAdditionToolChange] with
         * `BetaToolUnion.ofBetaTool(betaTool)`.
         */
        fun addDefinitionAdditionToolChange(betaTool: BetaTool) =
            addDefinitionAdditionToolChange(BetaToolUnion.ofBetaTool(betaTool))

        /** Alias for calling [addToolChange] with `ToolChange.ofRemoval(removal)`. */
        fun addToolChange(removal: BetaRequestToolRemovalBlock) =
            addToolChange(ToolChange.ofRemoval(removal))

        /** Alias for calling [addToolChange] with `removal.toParam()`. */
        fun addToolChange(removal: BetaResponseToolRemovalBlock) = addToolChange(removal.toParam())

        /**
         * Alias for calling [addToolChange] with the following:
         * ```java
         * BetaRequestToolRemovalBlock.builder()
         *     .tool(tool)
         *     .build()
         * ```
         */
        fun addRemovalToolChange(tool: BetaRequestToolRemovalBlock.Tool) =
            addToolChange(BetaRequestToolRemovalBlock.builder().tool(tool).build())

        /**
         * Alias for calling [addRemovalToolChange] with
         * `BetaRequestToolRemovalBlock.Tool.ofReference(reference)`.
         */
        fun addRemovalToolChange(reference: BetaToolChangeToolReference) =
            addRemovalToolChange(BetaRequestToolRemovalBlock.Tool.ofReference(reference))

        /** Alias for calling [addRemovalToolChange] with `reference.toParam()`. */
        fun addRemovalToolChange(reference: BetaResponseToolChangeToolReference) =
            addRemovalToolChange(reference.toParam())

        /**
         * Alias for calling [addRemovalToolChange] with the following:
         * ```java
         * BetaToolChangeToolReference.builder()
         *     .name(name)
         *     .build()
         * ```
         */
        fun addReferenceRemovalToolChange(name: String) =
            addRemovalToolChange(BetaToolChangeToolReference.builder().name(name).build())

        /**
         * Alias for calling [addRemovalToolChange] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)`.
         */
        fun addRemovalToolChange(mcpToolReference: BetaToolChangeMcpToolReference) =
            addRemovalToolChange(
                BetaRequestToolRemovalBlock.Tool.ofMcpToolReference(mcpToolReference)
            )

        /** Alias for calling [addRemovalToolChange] with `mcpToolReference.toParam()`. */
        fun addRemovalToolChange(mcpToolReference: BetaResponseToolChangeMcpToolReference) =
            addRemovalToolChange(mcpToolReference.toParam())

        /**
         * Alias for calling [addRemovalToolChange] with
         * `BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)`.
         */
        fun addRemovalToolChange(mcpToolsetReference: BetaToolChangeMcpToolsetReference) =
            addRemovalToolChange(
                BetaRequestToolRemovalBlock.Tool.ofMcpToolsetReference(mcpToolsetReference)
            )

        /** Alias for calling [addRemovalToolChange] with `mcpToolsetReference.toParam()`. */
        fun addRemovalToolChange(mcpToolsetReference: BetaResponseToolChangeMcpToolsetReference) =
            addRemovalToolChange(mcpToolsetReference.toParam())

        /**
         * Alias for calling [addRemovalToolChange] with the following:
         * ```java
         * BetaToolChangeMcpToolsetReference.builder()
         *     .serverName(serverName)
         *     .build()
         * ```
         */
        fun addMcpToolsetReferenceRemovalToolChange(serverName: String) =
            addRemovalToolChange(
                BetaToolChangeMcpToolsetReference.builder().serverName(serverName).build()
            )

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
         * Returns an immutable instance of [BetaCompactionBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaCompactionBlockParam =
            BetaCompactionBlockParam(
                type,
                cacheControl,
                content,
                encryptedContent,
                signature,
                (toolChanges ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaCompactionBlockParam = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("compaction")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        content()
        encryptedContent()
        signature()
        toolChanges().ifPresent { it.forEach { it.validate() } }
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
        type.let { if (it == JsonValue.from("compaction")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (if (content.asKnown().isPresent) 1 else 0) +
            (if (encryptedContent.asKnown().isPresent) 1 else 0) +
            (if (signature.asKnown().isPresent) 1 else 0) +
            (toolChanges.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    @JsonDeserialize(using = ToolChange.Deserializer::class)
    @JsonSerialize(using = ToolChange.Serializer::class)
    class ToolChange
    private constructor(
        private val addition: BetaRequestToolAdditionBlock? = null,
        private val removal: BetaRequestToolRemovalBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitAddition(addition: BetaRequestToolAdditionBlock): Type =
                        Type.TOOL_ADDITION

                    override fun visitRemoval(removal: BetaRequestToolRemovalBlock): Type =
                        Type.TOOL_REMOVAL

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun cacheControl(): Optional<BetaCacheControlEphemeral> =
            accept(
                object : Visitor<Optional<BetaCacheControlEphemeral>> {
                    override fun visitAddition(
                        addition: BetaRequestToolAdditionBlock
                    ): Optional<BetaCacheControlEphemeral> = addition.cacheControl()

                    override fun visitRemoval(
                        removal: BetaRequestToolRemovalBlock
                    ): Optional<BetaCacheControlEphemeral> = removal.cacheControl()

                    override fun unknown(json: JsonValue?): Optional<BetaCacheControlEphemeral> =
                        json.getProperty<BetaCacheControlEphemeral>("cache_control").asKnown()
                }
            )

        /**
         * Mid-conversation directive to make a tool available.
         *
         * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``.
         * Under the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool
         * defined earlier in ``messages``, or a ``tool_definition`` object that carries an inline
         * tool definition in ``definition`` (the same object a ``tools`` entry holds). An
         * ``mcp_toolset`` definition also requires the ``mcp-client-2026-09-15`` beta. The tool is
         * offered to the model from this point in the conversation onward.
         */
        fun addition(): Optional<BetaRequestToolAdditionBlock> = Optional.ofNullable(addition)

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
         * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model from
         * this point in the conversation onward.
         */
        fun removal(): Optional<BetaRequestToolRemovalBlock> = Optional.ofNullable(removal)

        fun isAddition(): Boolean = addition != null

        fun isRemoval(): Boolean = removal != null

        /**
         * Mid-conversation directive to make a tool available.
         *
         * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's ``tools``.
         * Under the ``inline-tools-2026-09-15`` beta it may instead be a reference to a tool
         * defined earlier in ``messages``, or a ``tool_definition`` object that carries an inline
         * tool definition in ``definition`` (the same object a ``tools`` entry holds). An
         * ``mcp_toolset`` definition also requires the ``mcp-client-2026-09-15`` beta. The tool is
         * offered to the model from this point in the conversation onward.
         */
        fun asAddition(): BetaRequestToolAdditionBlock = addition.getOrThrow("addition")

        /**
         * Mid-conversation directive to withdraw a tool.
         *
         * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
         * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model from
         * this point in the conversation onward.
         */
        fun asRemoval(): BetaRequestToolRemovalBlock = removal.getOrThrow("removal")

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
         * Optional<String> result = toolChange.accept(new ToolChange.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitAddition(BetaRequestToolAdditionBlock addition) {
         *         return Optional.of(addition.toString());
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
                addition != null -> visitor.visitAddition(addition)
                removal != null -> visitor.visitRemoval(removal)
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
        fun validate(): ToolChange = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitAddition(addition: BetaRequestToolAdditionBlock) {
                        addition.validate()
                    }

                    override fun visitRemoval(removal: BetaRequestToolRemovalBlock) {
                        removal.validate()
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
                    override fun visitAddition(addition: BetaRequestToolAdditionBlock) =
                        addition.validity()

                    override fun visitRemoval(removal: BetaRequestToolRemovalBlock) =
                        removal.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ToolChange && addition == other.addition && removal == other.removal
        }

        override fun hashCode(): Int = Objects.hash(addition, removal)

        override fun toString(): String =
            when {
                addition != null -> "ToolChange{addition=$addition}"
                removal != null -> "ToolChange{removal=$removal}"
                _json != null -> "ToolChange{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ToolChange")
            }

        companion object {

            /**
             * Mid-conversation directive to make a tool available.
             *
             * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
             * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a reference
             * to a tool defined earlier in ``messages``, or a ``tool_definition`` object that
             * carries an inline tool definition in ``definition`` (the same object a ``tools``
             * entry holds). An ``mcp_toolset`` definition also requires the
             * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point in
             * the conversation onward.
             */
            @JvmStatic
            fun ofAddition(addition: BetaRequestToolAdditionBlock) = ToolChange(addition = addition)

            /**
             * Returns an immutable instance of [ToolChange] whose [ofAddition] variant is built
             * from the given required [tool].
             */
            @JvmStatic
            fun ofAddition(tool: BetaRequestToolAdditionBlock.Tool) =
                ofAddition(BetaRequestToolAdditionBlock.of(tool))

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
             * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model
             * from this point in the conversation onward.
             */
            @JvmStatic
            fun ofRemoval(removal: BetaRequestToolRemovalBlock) = ToolChange(removal = removal)

            /**
             * Returns an immutable instance of [ToolChange] whose [ofRemoval] variant is built from
             * the given required [tool].
             */
            @JvmStatic
            fun ofRemoval(tool: BetaRequestToolRemovalBlock.Tool) =
                ofRemoval(BetaRequestToolRemovalBlock.of(tool))
        }

        /**
         * An interface that defines how to map each variant of [ToolChange] to a value of type [T].
         */
        interface Visitor<out T> {

            /**
             * Mid-conversation directive to make a tool available.
             *
             * ``tool`` is a reference to a tool (or MCP toolset) declared in the request's
             * ``tools``. Under the ``inline-tools-2026-09-15`` beta it may instead be a reference
             * to a tool defined earlier in ``messages``, or a ``tool_definition`` object that
             * carries an inline tool definition in ``definition`` (the same object a ``tools``
             * entry holds). An ``mcp_toolset`` definition also requires the
             * ``mcp-client-2026-09-15`` beta. The tool is offered to the model from this point in
             * the conversation onward.
             */
            fun visitAddition(addition: BetaRequestToolAdditionBlock): T

            /**
             * Mid-conversation directive to withdraw a tool.
             *
             * ``tool`` references a tool (or MCP toolset) by name: one declared in the request's
             * ``tools`` or defined earlier in ``messages``. It is no longer offered to the model
             * from this point in the conversation onward.
             */
            fun visitRemoval(removal: BetaRequestToolRemovalBlock): T

            /**
             * Maps an unknown variant of [ToolChange] to a value of type [T].
             *
             * An instance of [ToolChange] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ToolChange: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ToolChange>(ToolChange::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ToolChange {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "tool_addition" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaRequestToolAdditionBlock>())
                            ?.let { ToolChange(addition = it, _json = json) }
                            ?: ToolChange(_json = json)
                    }
                    "tool_removal" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaRequestToolRemovalBlock>())
                            ?.let { ToolChange(removal = it, _json = json) }
                            ?: ToolChange(_json = json)
                    }
                }

                return ToolChange(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<ToolChange>(ToolChange::class) {

            override fun serialize(
                value: ToolChange,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.addition != null -> generator.writeObject(value.addition)
                    value.removal != null -> generator.writeObject(value.removal)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ToolChange")
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

                @JvmField val TOOL_ADDITION = of("tool_addition")

                @JvmField val TOOL_REMOVAL = of("tool_removal")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                TOOL_ADDITION,
                TOOL_REMOVAL,
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
                TOOL_ADDITION,
                TOOL_REMOVAL,
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
                    TOOL_ADDITION -> Value.TOOL_ADDITION
                    TOOL_REMOVAL -> Value.TOOL_REMOVAL
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
                    TOOL_ADDITION -> Known.TOOL_ADDITION
                    TOOL_REMOVAL -> Known.TOOL_REMOVAL
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

        return other is BetaCompactionBlockParam &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            content == other.content &&
            encryptedContent == other.encryptedContent &&
            signature == other.signature &&
            toolChanges == other.toolChanges &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            type,
            cacheControl,
            content,
            encryptedContent,
            signature,
            toolChanges,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCompactionBlockParam{type=$type, cacheControl=$cacheControl, content=$content, encryptedContent=$encryptedContent, signature=$signature, toolChanges=$toolChanges, additionalProperties=$additionalProperties}"
}
