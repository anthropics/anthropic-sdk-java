package com.anthropic.mcp

import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.helpers.McpBetaTool
import com.anthropic.models.beta.messages.BetaAdvisorToolResultBlockParam
import com.anthropic.models.beta.messages.BetaBase64ImageSource
import com.anthropic.models.beta.messages.BetaBase64PdfSource
import com.anthropic.models.beta.messages.BetaBashCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaCompactionBlockParam
import com.anthropic.models.beta.messages.BetaContainerUploadBlockParam
import com.anthropic.models.beta.messages.BetaContentBlockParam
import com.anthropic.models.beta.messages.BetaImageBlockParam
import com.anthropic.models.beta.messages.BetaMcpToolUseBlockParam
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.BetaPlainTextSource
import com.anthropic.models.beta.messages.BetaRedactedThinkingBlockParam
import com.anthropic.models.beta.messages.BetaRequestDocumentBlock
import com.anthropic.models.beta.messages.BetaRequestMcpToolResultBlockParam
import com.anthropic.models.beta.messages.BetaSearchResultBlockParam
import com.anthropic.models.beta.messages.BetaServerToolUseBlockParam
import com.anthropic.models.beta.messages.BetaTextBlockParam
import com.anthropic.models.beta.messages.BetaTextEditorCodeExecutionToolResultBlockParam
import com.anthropic.models.beta.messages.BetaThinkingBlockParam
import com.anthropic.models.beta.messages.BetaTool
import com.anthropic.models.beta.messages.BetaToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolSearchToolResultBlockParam
import com.anthropic.models.beta.messages.BetaToolUseBlockParam
import com.anthropic.models.beta.messages.BetaWebFetchToolResultBlockParam
import com.anthropic.models.beta.messages.BetaWebSearchToolResultBlockParam
import com.anthropic.models.beta.messages.MessageCreateParams
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import java.net.URI
import java.nio.charset.StandardCharsets
import java.util.Base64

/** Helper methods for converting MCP SDK types into Anthropic API types. */
object BetaMcp {

    private val SUPPORTED_IMAGE_MIME_TYPES =
        setOf("image/jpeg", "image/png", "image/gif", "image/webp")

    private val OBJECT_MAPPER = ObjectMapper()

    private val MAP_TYPE_REF = object : TypeReference<Map<String, Any?>>() {}

    /**
     * Converts an MCP content item into an Anthropic content block.
     *
     * @throws AnthropicInvalidDataException for audio content, resource links, or unsupported MIME
     *   types
     */
    @JvmStatic
    @JvmOverloads
    fun mcpContent(
        content: McpSchema.Content,
        cacheControl: BetaCacheControlEphemeral? = null,
    ): BetaContentBlockParam =
        when (content) {
            is McpSchema.TextContent ->
                BetaContentBlockParam.ofText(
                    BetaTextBlockParam.builder()
                        .text(content.text())
                        .cacheControl(cacheControl)
                        .build()
                )
            is McpSchema.ImageContent ->
                BetaContentBlockParam.ofImage(
                    BetaImageBlockParam.builder()
                        .source(
                            BetaBase64ImageSource.builder()
                                .data(content.data())
                                .mediaType(BetaBase64ImageSource.MediaType.of(content.mimeType()))
                                .build()
                        )
                        .cacheControl(cacheControl)
                        .build()
                )
            is McpSchema.EmbeddedResource ->
                resourceContentsToContentBlock(content.resource(), cacheControl)
            is McpSchema.AudioContent ->
                throw AnthropicInvalidDataException(
                    "MCP audio content is not supported by the Anthropic API"
                )
            is McpSchema.ResourceLink ->
                throw AnthropicInvalidDataException(
                    "MCP resource links must be resolved by the MCP client before conversion"
                )
            else ->
                throw AnthropicInvalidDataException(
                    "Unsupported MCP content type: ${content.type()}"
                )
        }

    /**
     * Converts an MCP prompt message into an Anthropic message parameter.
     *
     * @throws AnthropicInvalidDataException for unsupported content types
     */
    @JvmStatic
    @JvmOverloads
    fun mcpMessage(
        message: McpSchema.PromptMessage,
        cacheControl: BetaCacheControlEphemeral? = null,
    ): BetaMessageParam =
        BetaMessageParam.builder()
            .role(BetaMessageParam.Role.of(message.role().name.lowercase()))
            .contentOfBetaContentBlockParams(listOf(mcpContent(message.content(), cacheControl)))
            .build()

    /**
     * Converts a list of MCP prompt messages into Anthropic message parameters.
     *
     * @throws AnthropicInvalidDataException for unsupported content types
     */
    @JvmStatic
    @JvmOverloads
    fun mcpMessages(
        messages: List<McpSchema.PromptMessage>,
        cacheControl: BetaCacheControlEphemeral? = null,
    ): List<BetaMessageParam> = messages.map { mcpMessage(it, cacheControl) }

    /**
     * Converts each item of an MCP resource read result into an Anthropic content block.
     *
     * @throws AnthropicInvalidDataException if any item has an unsupported MIME type
     */
    @JvmStatic
    @JvmOverloads
    fun mcpResourceContents(
        result: McpSchema.ReadResourceResult,
        cacheControl: BetaCacheControlEphemeral? = null,
    ): List<BetaContentBlockParam> =
        result.contents().map { resourceContentsToContentBlock(it, cacheControl) }

    /**
     * Converts each item of an MCP resource read result into a file object suitable for the files
     * upload API.
     */
    @JvmStatic
    fun mcpResourceFiles(result: McpSchema.ReadResourceResult): List<McpResourceFile> =
        result.contents().map {
            McpResourceFile.builder()
                .filename(extractFilename(it.uri()))
                .content(resourceContentsToBytes(it))
                .mimeType(it.mimeType())
                .build()
        }

    /**
     * Converts an MCP tool into an [McpBetaTool] for use with
     * [MessageCreateParams.Builder.addTool].
     */
    @JvmStatic
    fun mcpTool(tool: McpSchema.Tool, client: McpSyncClient): McpBetaTool {
        val toolName = tool.name()
        return McpBetaTool.builder()
            .name(toolName)
            .definition(betaToolFromMcp(tool))
            .runner { jsonInput ->
                val arguments: Map<String, Any?> =
                    try {
                        OBJECT_MAPPER.readValue(jsonInput, MAP_TYPE_REF)
                    } catch (e: Exception) {
                        throw AnthropicInvalidDataException(
                            "Failed to parse JSON tool input for tool '$toolName'",
                            e,
                        )
                    }
                convertCallToolResult(
                    client.callTool(McpSchema.CallToolRequest(toolName, arguments))
                )
            }
            .build()
    }

    /**
     * Converts a list of MCP tools into [McpBetaTool] objects for use with
     * [MessageCreateParams.Builder.addTools].
     */
    @JvmStatic
    fun mcpTools(tools: List<McpSchema.Tool>, client: McpSyncClient): List<McpBetaTool> =
        tools.map { mcpTool(it, client) }

    /** Registers an MCP tool with the message builder, wiring it to the given client. */
    @JvmStatic
    fun addMcpTool(
        builder: MessageCreateParams.Builder,
        tool: McpSchema.Tool,
        client: McpSyncClient,
    ): MessageCreateParams.Builder = builder.addTool(mcpTool(tool, client))

    /** Registers multiple MCP tools with the message builder, wiring each to the given client. */
    @JvmStatic
    fun addMcpTools(
        builder: MessageCreateParams.Builder,
        tools: List<McpSchema.Tool>,
        client: McpSyncClient,
    ): MessageCreateParams.Builder = builder.addTools(mcpTools(tools, client))

    private fun resourceContentsToContentBlock(
        item: McpSchema.ResourceContents,
        cacheControl: BetaCacheControlEphemeral?,
    ): BetaContentBlockParam {
        val mimeType = item.mimeType()
        return when {
            mimeType in SUPPORTED_IMAGE_MIME_TYPES -> {
                val blob =
                    (item as? McpSchema.BlobResourceContents)?.blob()
                        ?: throw AnthropicInvalidDataException("Image resource must have blob data")
                BetaContentBlockParam.ofImage(
                    BetaImageBlockParam.builder()
                        .source(
                            BetaBase64ImageSource.builder()
                                .data(blob)
                                .mediaType(BetaBase64ImageSource.MediaType.of(mimeType))
                                .build()
                        )
                        .cacheControl(cacheControl)
                        .build()
                )
            }
            mimeType == "application/pdf" -> {
                val blob =
                    (item as? McpSchema.BlobResourceContents)?.blob()
                        ?: throw AnthropicInvalidDataException("PDF resource must have blob data")
                BetaContentBlockParam.ofDocument(
                    BetaRequestDocumentBlock.builder()
                        .source(BetaBase64PdfSource.builder().data(blob).build())
                        .cacheControl(cacheControl)
                        .build()
                )
            }
            mimeType == null || mimeType.startsWith("text/") ->
                BetaContentBlockParam.ofDocument(
                    BetaRequestDocumentBlock.builder()
                        .source(
                            BetaPlainTextSource.builder()
                                .data(textFromResourceContents(item))
                                .build()
                        )
                        .cacheControl(cacheControl)
                        .build()
                )
            else -> throw AnthropicInvalidDataException("Unsupported resource MIME type: $mimeType")
        }
    }

    private fun textFromResourceContents(item: McpSchema.ResourceContents): String =
        when (item) {
            is McpSchema.TextResourceContents -> item.text()
            is McpSchema.BlobResourceContents ->
                String(Base64.getDecoder().decode(item.blob()), StandardCharsets.UTF_8)
            else -> throw AnthropicInvalidDataException("Unsupported resource contents type")
        }

    private fun resourceContentsToBytes(item: McpSchema.ResourceContents): ByteArray =
        when (item) {
            is McpSchema.BlobResourceContents -> Base64.getDecoder().decode(item.blob())
            is McpSchema.TextResourceContents -> item.text().toByteArray(StandardCharsets.UTF_8)
            else -> throw AnthropicInvalidDataException("Unsupported resource contents type")
        }

    private fun convertCallToolResult(
        result: McpSchema.CallToolResult
    ): BetaToolResultBlockParam.Content {
        if (result.isError() == true) {
            val errorText =
                result.content().joinToString("\n") {
                    when (it) {
                        is McpSchema.TextContent -> it.text()
                        else -> it.toString()
                    }
                }
            throw AnthropicException(errorText)
        }
        if (result.content().isEmpty() && result.structuredContent() != null) {
            return try {
                BetaToolResultBlockParam.Content.ofString(
                    OBJECT_MAPPER.writeValueAsString(result.structuredContent())
                )
            } catch (e: Exception) {
                throw AnthropicInvalidDataException("Failed to serialize structuredContent", e)
            }
        }
        return BetaToolResultBlockParam.Content.ofBlocks(
            result.content().map { contentParamToBlock(mcpContent(it)) }
        )
    }

    private fun contentParamToBlock(
        block: BetaContentBlockParam
    ): BetaToolResultBlockParam.Content.Block =
        block.accept(
            object : BetaContentBlockParam.Visitor<BetaToolResultBlockParam.Content.Block> {
                override fun visitText(text: BetaTextBlockParam) =
                    BetaToolResultBlockParam.Content.Block.ofText(text)

                override fun visitImage(image: BetaImageBlockParam) =
                    BetaToolResultBlockParam.Content.Block.ofImage(image)

                override fun visitDocument(document: BetaRequestDocumentBlock) =
                    BetaToolResultBlockParam.Content.Block.ofDocument(document)

                override fun visitSearchResult(searchResult: BetaSearchResultBlockParam) =
                    unsupported("search_result")

                override fun visitThinking(thinking: BetaThinkingBlockParam) =
                    unsupported("thinking")

                override fun visitRedactedThinking(
                    redactedThinking: BetaRedactedThinkingBlockParam
                ) = unsupported("redacted_thinking")

                override fun visitToolUse(toolUse: BetaToolUseBlockParam) = unsupported("tool_use")

                override fun visitToolResult(toolResult: BetaToolResultBlockParam) =
                    unsupported("tool_result")

                override fun visitServerToolUse(serverToolUse: BetaServerToolUseBlockParam) =
                    unsupported("server_tool_use")

                override fun visitWebSearchToolResult(
                    webSearchToolResult: BetaWebSearchToolResultBlockParam
                ) = unsupported("web_search_tool_result")

                override fun visitWebFetchToolResult(
                    webFetchToolResult: BetaWebFetchToolResultBlockParam
                ) = unsupported("web_fetch_tool_result")

                override fun visitAdvisorToolResult(
                    advisorToolResult: BetaAdvisorToolResultBlockParam
                ) = unsupported("advisor_tool_result")

                override fun visitCodeExecutionToolResult(
                    codeExecutionToolResult: BetaCodeExecutionToolResultBlockParam
                ) = unsupported("code_execution_tool_result")

                override fun visitBashCodeExecutionToolResult(
                    bashCodeExecutionToolResult: BetaBashCodeExecutionToolResultBlockParam
                ) = unsupported("bash_code_execution_tool_result")

                override fun visitTextEditorCodeExecutionToolResult(
                    textEditorCodeExecutionToolResult:
                        BetaTextEditorCodeExecutionToolResultBlockParam
                ) = unsupported("text_editor_code_execution_tool_result")

                override fun visitToolSearchToolResult(
                    toolSearchToolResult: BetaToolSearchToolResultBlockParam
                ) = unsupported("tool_search_tool_result")

                override fun visitMcpToolUse(mcpToolUse: BetaMcpToolUseBlockParam) =
                    unsupported("mcp_tool_use")

                override fun visitMcpToolResult(mcpToolResult: BetaRequestMcpToolResultBlockParam) =
                    unsupported("mcp_tool_result")

                override fun visitContainerUpload(containerUpload: BetaContainerUploadBlockParam) =
                    unsupported("container_upload")

                override fun visitCompaction(compaction: BetaCompactionBlockParam) =
                    unsupported("compaction")

                private fun unsupported(type: String): BetaToolResultBlockParam.Content.Block =
                    throw AnthropicInvalidDataException(
                        "Unsupported content block type for tool result: $type"
                    )
            }
        )

    private fun betaToolFromMcp(tool: McpSchema.Tool): BetaTool {
        val builder =
            BetaTool.builder().name(tool.name()).inputSchema(betaInputSchema(tool.inputSchema()))
        tool.description()?.let { builder.description(it) }
        return builder.build()
    }

    private fun betaInputSchema(schema: McpSchema.JsonSchema): BetaTool.InputSchema {
        val builder = BetaTool.InputSchema.builder()
        schema.required()?.let { builder.required(it) }
        schema.properties()?.let { builder.putAdditionalProperty("properties", JsonValue.from(it)) }
        schema.defs()?.let { builder.putAdditionalProperty("\$defs", JsonValue.from(it)) }
        schema.definitions()?.let {
            builder.putAdditionalProperty("definitions", JsonValue.from(it))
        }
        return builder.build()
    }

    private fun extractFilename(uri: String?): String? {
        if (uri == null) return null
        return try {
            val path = URI.create(uri).path?.takeIf { it.isNotEmpty() } ?: return null
            val lastSlash = path.lastIndexOf('/')
            val filename = if (lastSlash >= 0) path.substring(lastSlash + 1) else path
            filename.takeIf { it.isNotEmpty() }
        } catch (e: Exception) {
            null
        }
    }
}
