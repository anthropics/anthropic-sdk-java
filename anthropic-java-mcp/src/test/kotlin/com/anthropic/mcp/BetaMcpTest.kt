package com.anthropic.mcp

import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.helpers.McpBetaTool
import com.anthropic.models.beta.messages.BetaCacheControlEphemeral
import com.anthropic.models.beta.messages.BetaMessageParam
import com.anthropic.models.beta.messages.MessageCreateParams
import com.anthropic.models.messages.Model
import io.modelcontextprotocol.client.McpSyncClient
import io.modelcontextprotocol.spec.McpSchema
import java.util.Base64
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

internal class BetaMcpTest {

    private val client: McpSyncClient = mock()

    private fun baseBuilder(): MessageCreateParams.Builder =
        MessageCreateParams.builder()
            .model(Model.CLAUDE_SONNET_4_5)
            .maxTokens(1000)
            .addUserMessage("hello")

    // --- mcpContent ---

    @Test
    fun mcpContent_text_convertsToTextBlock() {
        val result = BetaMcp.mcpContent(McpSchema.TextContent("hello world"))

        assertThat(result.text()).isPresent
        assertThat(result.text().get().text()).isEqualTo("hello world")
    }

    @Test
    fun mcpContent_textWithCacheControl_appliesCacheControl() {
        val result =
            BetaMcp.mcpContent(
                McpSchema.TextContent("hello"),
                BetaCacheControlEphemeral.builder().build(),
            )

        assertThat(result.text()).isPresent
        assertThat(result.text().get().cacheControl()).isPresent
    }

    @Test
    fun mcpContent_supportedImage_convertsToImageBlock() {
        val data = Base64.getEncoder().encodeToString("fake-png".toByteArray())

        val result = BetaMcp.mcpContent(McpSchema.ImageContent(null, data, "image/png"))

        assertThat(result.image()).isPresent
        assertThat(result.image().get().source().base64().get().data()).isEqualTo(data)
    }

    @Test
    fun mcpContent_unknownImageMimeType_passesThrough() {
        val result = BetaMcp.mcpContent(McpSchema.ImageContent(null, "data", "image/bmp"))

        assertThat(result.image()).isPresent
        assertThat(result.image().get().source().base64().get().mediaType().toString())
            .isEqualTo("image/bmp")
    }

    @Test
    fun mcpContent_audioContent_throws() {
        assertThatThrownBy {
                BetaMcp.mcpContent(McpSchema.AudioContent(null, "data", "audio/mpeg"))
            }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageContaining("audio")
    }

    @Test
    fun mcpContent_embeddedTextResource_convertsToDocumentBlock() {
        val resource = McpSchema.TextResourceContents("file:///doc.txt", "text/plain", "hello")
        val embedded = McpSchema.EmbeddedResource(null, resource)

        val result = BetaMcp.mcpContent(embedded)

        assertThat(result.document()).isPresent
    }

    @Test
    fun mcpContent_embeddedPdfBlobResource_convertsToDocumentBlock() {
        val pdfBase64 = Base64.getEncoder().encodeToString("%PDF-".toByteArray())
        val resource =
            McpSchema.BlobResourceContents("file:///doc.pdf", "application/pdf", pdfBase64)
        val embedded = McpSchema.EmbeddedResource(null, resource)

        val result = BetaMcp.mcpContent(embedded)

        assertThat(result.document()).isPresent
    }

    @Test
    fun mcpContent_embeddedImageBlobResource_convertsToImageBlock() {
        val imageBase64 = Base64.getEncoder().encodeToString("fake-png".toByteArray())
        val resource = McpSchema.BlobResourceContents("file:///img.png", "image/png", imageBase64)
        val embedded = McpSchema.EmbeddedResource(null, resource)

        val result = BetaMcp.mcpContent(embedded)

        assertThat(result.image()).isPresent
    }

    // --- mcpMessage ---

    @Test
    fun mcpMessage_userRole_convertsToUserMessage() {
        val message = McpSchema.PromptMessage(McpSchema.Role.USER, McpSchema.TextContent("hi"))

        val result = BetaMcp.mcpMessage(message)

        assertThat(result.role()).isEqualTo(BetaMessageParam.Role.USER)
    }

    @Test
    fun mcpMessage_assistantRole_convertsToAssistantMessage() {
        val message =
            McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, McpSchema.TextContent("reply"))

        val result = BetaMcp.mcpMessage(message)

        assertThat(result.role()).isEqualTo(BetaMessageParam.Role.ASSISTANT)
    }

    // --- mcpMessages ---

    @Test
    fun mcpMessages_convertsAllMessages() {
        val messages =
            listOf(
                McpSchema.PromptMessage(McpSchema.Role.USER, McpSchema.TextContent("hello")),
                McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, McpSchema.TextContent("reply")),
            )

        val results = BetaMcp.mcpMessages(messages)

        assertThat(results).hasSize(2)
        assertThat(results[0].role()).isEqualTo(BetaMessageParam.Role.USER)
        assertThat(results[1].role()).isEqualTo(BetaMessageParam.Role.ASSISTANT)
    }

    // --- mcpResourceContents ---

    @Test
    fun mcpResourceContents_textResource_convertsToDocumentBlock() {
        val resource = McpSchema.TextResourceContents("file:///doc.txt", "text/plain", "content")

        val blocks = BetaMcp.mcpResourceContents(McpSchema.ReadResourceResult(listOf(resource)))

        assertThat(blocks).hasSize(1)
        assertThat(blocks[0].document()).isPresent
    }

    @Test
    fun mcpResourceContents_multipleResources_convertsAll() {
        val text = McpSchema.TextResourceContents("file:///doc.txt", "text/plain", "hello")
        val pdf =
            McpSchema.BlobResourceContents(
                "file:///doc.pdf",
                "application/pdf",
                Base64.getEncoder().encodeToString("%PDF-".toByteArray()),
            )

        val blocks = BetaMcp.mcpResourceContents(McpSchema.ReadResourceResult(listOf(text, pdf)))

        assertThat(blocks).hasSize(2)
        assertThat(blocks[0].document()).isPresent
        assertThat(blocks[1].document()).isPresent
    }

    @Test
    fun mcpResourceContents_emptyContents_returnsEmptyList() {
        val blocks = BetaMcp.mcpResourceContents(McpSchema.ReadResourceResult(emptyList()))

        assertThat(blocks).isEmpty()
    }

    @Test
    fun mcpResourceContents_unsupportedMimeType_throws() {
        val resource = McpSchema.TextResourceContents("file:///audio.mp3", "audio/mpeg", "data")

        assertThatThrownBy {
                BetaMcp.mcpResourceContents(McpSchema.ReadResourceResult(listOf(resource)))
            }
            .isInstanceOf(AnthropicInvalidDataException::class.java)
    }

    // --- mcpResourceFiles ---

    @Test
    fun mcpResourceFiles_blobResource_returnsBytesAndFilename() {
        val bytes = "hello".toByteArray()
        val base64 = Base64.getEncoder().encodeToString(bytes)
        val resource =
            McpSchema.BlobResourceContents("file:///data.bin", "application/octet-stream", base64)

        val files = BetaMcp.mcpResourceFiles(McpSchema.ReadResourceResult(listOf(resource)))

        assertThat(files).hasSize(1)
        assertThat(files[0].filename).isEqualTo("data.bin")
        assertThat(files[0].content).isEqualTo(bytes)
        assertThat(files[0].mimeType).isEqualTo("application/octet-stream")
    }

    @Test
    fun mcpResourceFiles_textResource_returnsUtf8Bytes() {
        val resource = McpSchema.TextResourceContents("file:///doc.txt", "text/plain", "hello")

        val files = BetaMcp.mcpResourceFiles(McpSchema.ReadResourceResult(listOf(resource)))

        assertThat(files).hasSize(1)
        assertThat(files[0].filename).isEqualTo("doc.txt")
        assertThat(String(files[0].content)).isEqualTo("hello")
    }

    @Test
    fun mcpResourceFiles_multipleResources_convertsAll() {
        val text = McpSchema.TextResourceContents("file:///a.txt", "text/plain", "hello")
        val blob =
            McpSchema.BlobResourceContents(
                "file:///b.bin",
                "application/octet-stream",
                Base64.getEncoder().encodeToString("world".toByteArray()),
            )

        val files = BetaMcp.mcpResourceFiles(McpSchema.ReadResourceResult(listOf(text, blob)))

        assertThat(files).hasSize(2)
        assertThat(files[0].filename).isEqualTo("a.txt")
        assertThat(files[1].filename).isEqualTo("b.bin")
    }

    @Test
    fun mcpResourceFiles_emptyContents_returnsEmptyList() {
        val files = BetaMcp.mcpResourceFiles(McpSchema.ReadResourceResult(emptyList()))

        assertThat(files).isEmpty()
    }

    // --- mcpTool ---

    @Test
    fun mcpTool_returnsToolWithCorrectName() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tool = McpSchema.Tool("my_tool", null, "Does something", schema, null, null, null)

        val result = BetaMcp.mcpTool(tool, client)

        assertThat(result.name).isEqualTo("my_tool")
    }

    @Test
    fun mcpTool_definitionMatchesMcpTool() {
        val schema = McpSchema.JsonSchema("object", null, listOf("param1"), null, null, null)
        val tool = McpSchema.Tool("tool_req", null, "desc", schema, null, null, null)

        val result = BetaMcp.mcpTool(tool, client)

        assertThat(result.definition.name()).isEqualTo("tool_req")
        assertThat(result.definition.description()).isPresent
        assertThat(result.definition.inputSchema().required())
            .isPresent
            .satisfies({ req -> assertThat(req.get()).contains("param1") })
    }

    @Test
    fun mcpTool_runnerIsNonNull() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tool = McpSchema.Tool("t", null, null, schema, null, null, null)

        val result = BetaMcp.mcpTool(tool, client)

        assertThat(result.runner).isNotNull
    }

    // --- mcpTools ---

    @Test
    fun mcpTools_convertsAllTools() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tools =
            listOf(
                McpSchema.Tool("tool_a", null, null, schema, null, null, null),
                McpSchema.Tool("tool_b", null, null, schema, null, null, null),
            )

        val result = BetaMcp.mcpTools(tools, client)

        assertThat(result).hasSize(2)
        assertThat(result[0].name).isEqualTo("tool_a")
        assertThat(result[1].name).isEqualTo("tool_b")
    }

    @Test
    fun mcpTools_canBeWiredIntoBuilder() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tools =
            listOf(
                McpSchema.Tool("tool_a", null, null, schema, null, null, null),
                McpSchema.Tool("tool_b", null, null, schema, null, null, null),
            )

        val betaTools: List<McpBetaTool> = BetaMcp.mcpTools(tools, client)
        val params = baseBuilder().addTools(betaTools).build()

        assertThat(params.tools()).isPresent
        assertThat(params.tools().get()).hasSizeGreaterThanOrEqualTo(2)
    }

    // --- addMcpTool ---

    @Test
    fun addMcpTool_registersToolDefinitionInBuilder() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tool = McpSchema.Tool("my_tool", null, "Does something", schema, null, null, null)

        val params = BetaMcp.addMcpTool(baseBuilder(), tool, client).build()

        assertThat(params.tools()).isPresent
        assertThat(params.tools().get()).isNotEmpty
        val hasMyTool =
            params.tools().get().any { t ->
                t.betaTool().map { it.name() == "my_tool" }.orElse(false)
            }
        assertThat(hasMyTool).isTrue
    }

    @Test
    fun addMcpTool_schemaWithRequired_preservesRequired() {
        val schema = McpSchema.JsonSchema("object", null, listOf("param1"), null, null, null)
        val tool = McpSchema.Tool("tool_req", null, null, schema, null, null, null)

        val params = BetaMcp.addMcpTool(baseBuilder(), tool, client).build()

        assertThat(params.tools()).isPresent
        params
            .tools()
            .get()
            .firstOrNull { t -> t.betaTool().map { it.name() == "tool_req" }.orElse(false) }
            ?.betaTool()
            ?.ifPresent { bt ->
                assertThat(bt.inputSchema().required())
                    .isPresent
                    .satisfies({ req -> assertThat(req.get()).contains("param1") })
            }
    }

    // --- addMcpTools ---

    @Test
    fun addMcpTools_registersAllTools() {
        val schema = McpSchema.JsonSchema("object", null, null, null, null, null)
        val tools =
            listOf(
                McpSchema.Tool("tool_a", null, null, schema, null, null, null),
                McpSchema.Tool("tool_b", null, null, schema, null, null, null),
            )

        val params = BetaMcp.addMcpTools(baseBuilder(), tools, client).build()

        assertThat(params.tools()).isPresent
        assertThat(params.tools().get()).hasSizeGreaterThanOrEqualTo(2)
    }
}
