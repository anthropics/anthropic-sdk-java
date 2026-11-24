package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaContentBlockTest {

    @Test
    fun ofText() {
        val text =
            BetaTextBlock.builder()
                .addCitation(
                    BetaCitationCharLocation.builder()
                        .citedText("The grass is green. The sky is blue.")
                        .documentIndex(0L)
                        .documentTitle("My Document")
                        .endCharIndex(0L)
                        .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val betaContentBlock = BetaContentBlock.ofText(text)

        assertThat(presentVariants(betaContentBlock)).isEqualTo(mapOf("text" to Optional.of(text)))
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofText(
                BetaTextBlock.builder()
                    .addCitation(
                        BetaCitationCharLocation.builder()
                            .citedText("The grass is green. The sky is blue.")
                            .documentIndex(0L)
                            .documentTitle("My Document")
                            .endCharIndex(0L)
                            .fileId("file_011CNha8iCJcU1wXNR6q4V8w")
                            .startCharIndex(0L)
                            .build()
                    )
                    .text("text")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofThinking() {
        val thinking =
            BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val betaContentBlock = BetaContentBlock.ofThinking(thinking)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofThinking(
                BetaThinkingBlock.builder().signature("signature").thinking("thinking").build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = BetaRedactedThinkingBlock.of("data")

        val betaContentBlock = BetaContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("redactedThinking" to Optional.of(redactedThinking)))
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofRedactedThinking(BetaRedactedThinkingBlock.of("data"))

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            BetaToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .caller(BetaDirectCaller.builder().build())
                .toolsetName("toolset_name")
                .build()

        val betaContentBlock = BetaContentBlock.ofToolUse(toolUse)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("toolUse" to Optional.of(toolUse)))
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofToolUse(
                BetaToolUseBlock.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("x")
                    .caller(BetaDirectCaller.builder().build())
                    .toolsetName("toolset_name")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofServerToolUse() {
        val serverToolUse =
            BetaServerToolUseBlock.builder()
                .id("srvtoolu_c")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlock.Name.ADVISOR)
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlock = BetaContentBlock.ofServerToolUse(serverToolUse)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("serverToolUse" to Optional.of(serverToolUse)))
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofServerToolUse(
                BetaServerToolUseBlock.builder()
                    .id("srvtoolu_c")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name(BetaServerToolUseBlock.Name.ADVISOR)
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofWebSearchToolResult() {
        val webSearchToolResult =
            BetaWebSearchToolResultBlock.builder()
                .content(
                    BetaWebSearchToolResultError.of(
                        BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlock = BetaContentBlock.ofWebSearchToolResult(webSearchToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("webSearchToolResult" to Optional.of(webSearchToolResult)))
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofWebSearchToolResult(
                BetaWebSearchToolResultBlock.builder()
                    .content(
                        BetaWebSearchToolResultError.of(
                            BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofWebFetchToolResult() {
        val webFetchToolResult =
            BetaWebFetchToolResultBlock.builder()
                .content(
                    BetaWebFetchToolResultErrorBlock.of(
                        BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlock = BetaContentBlock.ofWebFetchToolResult(webFetchToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("webFetchToolResult" to Optional.of(webFetchToolResult)))
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofWebFetchToolResult(
                BetaWebFetchToolResultBlock.builder()
                    .content(
                        BetaWebFetchToolResultErrorBlock.of(
                            BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .caller(BetaDirectCaller.builder().build())
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofAdvisorToolResult() {
        val advisorToolResult =
            BetaAdvisorToolResultBlock.builder()
                .content(
                    BetaAdvisorToolResultError.of(
                        BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        val betaContentBlock = BetaContentBlock.ofAdvisorToolResult(advisorToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("advisorToolResult" to Optional.of(advisorToolResult)))
    }

    @Test
    fun ofAdvisorToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofAdvisorToolResult(
                BetaAdvisorToolResultBlock.builder()
                    .content(
                        BetaAdvisorToolResultError.of(
                            BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofCodeExecutionToolResult() {
        val codeExecutionToolResult =
            BetaCodeExecutionToolResultBlock.builder()
                .content(
                    BetaCodeExecutionToolResultError.of(
                        BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        val betaContentBlock = BetaContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("codeExecutionToolResult" to Optional.of(codeExecutionToolResult)))
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofCodeExecutionToolResult(
                BetaCodeExecutionToolResultBlock.builder()
                    .content(
                        BetaCodeExecutionToolResultError.of(
                            BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofBashCodeExecutionToolResult() {
        val bashCodeExecutionToolResult =
            BetaBashCodeExecutionToolResultBlock.builder()
                .content(
                    BetaBashCodeExecutionToolResultError.of(
                        BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        val betaContentBlock =
            BetaContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(
                mapOf("bashCodeExecutionToolResult" to Optional.of(bashCodeExecutionToolResult))
            )
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofBashCodeExecutionToolResult(
                BetaBashCodeExecutionToolResultBlock.builder()
                    .content(
                        BetaBashCodeExecutionToolResultError.of(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                    )
                    .toolUseId("srvtoolu_c")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofTextEditorCodeExecutionToolResult() {
        val textEditorCodeExecutionToolResult =
            BetaTextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        val betaContentBlock =
            BetaContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(
                mapOf(
                    "textEditorCodeExecutionToolResult" to
                        Optional.of(textEditorCodeExecutionToolResult)
                )
            )
    }

    @Test
    fun ofTextEditorCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofTextEditorCodeExecutionToolResult(
                BetaTextEditorCodeExecutionToolResultBlock.builder()
                    .content(
                        BetaTextEditorCodeExecutionToolResultError.builder()
                            .errorCode(
                                BetaTextEditorCodeExecutionToolResultError.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofToolSearchToolResult() {
        val toolSearchToolResult =
            BetaToolSearchToolResultBlock.builder()
                .content(
                    BetaToolSearchToolResultError.builder()
                        .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        val betaContentBlock = BetaContentBlock.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("toolSearchToolResult" to Optional.of(toolSearchToolResult)))
    }

    @Test
    fun ofToolSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofToolSearchToolResult(
                BetaToolSearchToolResultBlock.builder()
                    .content(
                        BetaToolSearchToolResultError.builder()
                            .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_c")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofMcpToolUse() {
        val mcpToolUse =
            BetaMcpToolUseBlock.builder()
                .id("id")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("name")
                .serverName("server_name")
                .build()

        val betaContentBlock = BetaContentBlock.ofMcpToolUse(mcpToolUse)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("mcpToolUse" to Optional.of(mcpToolUse)))
    }

    @Test
    fun ofMcpToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofMcpToolUse(
                BetaMcpToolUseBlock.builder()
                    .id("id")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("name")
                    .serverName("server_name")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofMcpToolResult() {
        val mcpToolResult =
            BetaMcpToolResultBlock.builder()
                .content("string")
                .isError(true)
                .toolUseId("tool_use_id")
                .build()

        val betaContentBlock = BetaContentBlock.ofMcpToolResult(mcpToolResult)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("mcpToolResult" to Optional.of(mcpToolResult)))
    }

    @Test
    fun ofMcpToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofMcpToolResult(
                BetaMcpToolResultBlock.builder()
                    .content("string")
                    .isError(true)
                    .toolUseId("tool_use_id")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofContainerUpload() {
        val containerUpload = BetaContainerUploadBlock.of("file_id")

        val betaContentBlock = BetaContentBlock.ofContainerUpload(containerUpload)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("containerUpload" to Optional.of(containerUpload)))
    }

    @Test
    fun ofContainerUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofContainerUpload(BetaContainerUploadBlock.of("file_id"))

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofCompaction() {
        val compaction =
            BetaCompactionBlock.builder()
                .content("content")
                .encryptedContent("encrypted_content")
                .signature("signature")
                .addReferenceAdditionToolChange("name")
                .build()

        val betaContentBlock = BetaContentBlock.ofCompaction(compaction)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("compaction" to Optional.of(compaction)))
    }

    @Test
    fun ofCompactionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofCompaction(
                BetaCompactionBlock.builder()
                    .content("content")
                    .encryptedContent("encrypted_content")
                    .signature("signature")
                    .addReferenceAdditionToolChange("name")
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofFallback() {
        val fallback =
            BetaFallbackBlock.builder()
                .from(BetaFallbackInfo.of(Model.CLAUDE_FABLE_5_2))
                .to(BetaFallbackInfo.of(Model.CLAUDE_FABLE_5_2))
                .trigger(BetaFallbackRefusalTrigger.of(BetaFallbackRefusalTrigger.Category.CYBER))
                .build()

        val betaContentBlock = BetaContentBlock.ofFallback(fallback)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("fallback" to Optional.of(fallback)))
    }

    @Test
    fun ofFallbackRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofFallback(
                BetaFallbackBlock.builder()
                    .from(BetaFallbackInfo.of(Model.CLAUDE_FABLE_5_2))
                    .to(BetaFallbackInfo.of(Model.CLAUDE_FABLE_5_2))
                    .trigger(
                        BetaFallbackRefusalTrigger.of(BetaFallbackRefusalTrigger.Category.CYBER)
                    )
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun ofMcpToolListing() {
        val mcpToolListing =
            BetaMcpToolListingBlock.builder()
                .mcpServerName("mcp_server_name")
                .addTool(
                    BetaMcpTool.builder()
                        .inputSchema(
                            BetaMcpTool.InputSchema.builder()
                                .putAdditionalProperty("foo", JsonValue.from("bar"))
                                .build()
                        )
                        .name("name")
                        .description("description")
                        .build()
                )
                .build()

        val betaContentBlock = BetaContentBlock.ofMcpToolListing(mcpToolListing)

        assertThat(presentVariants(betaContentBlock))
            .isEqualTo(mapOf("mcpToolListing" to Optional.of(mcpToolListing)))
    }

    @Test
    fun ofMcpToolListingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofMcpToolListing(
                BetaMcpToolListingBlock.builder()
                    .mcpServerName("mcp_server_name")
                    .addTool(
                        BetaMcpTool.builder()
                            .inputSchema(
                                BetaMcpTool.InputSchema.builder()
                                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                                    .build()
                            )
                            .name("name")
                            .description("description")
                            .build()
                    )
                    .build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaContentBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "signature" to "signature",
                            "id" to "id",
                            "tool_use_id" to "srvtoolu_c",
                        )
                    ),
                    jacksonTypeRef<BetaContentBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaContentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaContentBlock.signature()).contains("signature")
        assertThat(betaContentBlock.id()).contains("id")
        assertThat(betaContentBlock.toolUseId()).contains("srvtoolu_c")

        val mismatchedBetaContentBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "signature" to listOf("invalid"),
                            "id" to listOf("invalid"),
                            "tool_use_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaContentBlock>(),
                )

        assertThat(mismatchedBetaContentBlock.signature()).isEmpty
        assertThat(mismatchedBetaContentBlock.id()).isEmpty
        assertThat(mismatchedBetaContentBlock.toolUseId()).isEmpty
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaContentBlock =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaContentBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { betaContentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaContentBlock.signature()).isEmpty
        assertThat(betaContentBlock.id()).isEmpty
        assertThat(betaContentBlock.toolUseId()).isEmpty
    }

    private fun presentVariants(betaContentBlock: BetaContentBlock): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to betaContentBlock.text(),
                "thinking" to betaContentBlock.thinking(),
                "redactedThinking" to betaContentBlock.redactedThinking(),
                "toolUse" to betaContentBlock.toolUse(),
                "serverToolUse" to betaContentBlock.serverToolUse(),
                "webSearchToolResult" to betaContentBlock.webSearchToolResult(),
                "webFetchToolResult" to betaContentBlock.webFetchToolResult(),
                "advisorToolResult" to betaContentBlock.advisorToolResult(),
                "codeExecutionToolResult" to betaContentBlock.codeExecutionToolResult(),
                "bashCodeExecutionToolResult" to betaContentBlock.bashCodeExecutionToolResult(),
                "textEditorCodeExecutionToolResult" to
                    betaContentBlock.textEditorCodeExecutionToolResult(),
                "toolSearchToolResult" to betaContentBlock.toolSearchToolResult(),
                "mcpToolUse" to betaContentBlock.mcpToolUse(),
                "mcpToolResult" to betaContentBlock.mcpToolResult(),
                "containerUpload" to betaContentBlock.containerUpload(),
                "compaction" to betaContentBlock.compaction(),
                "fallback" to betaContentBlock.fallback(),
                "mcpToolListing" to betaContentBlock.mcpToolListing(),
            )
            .filterValues { it.isPresent }
}
