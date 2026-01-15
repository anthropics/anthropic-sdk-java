// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
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
                        .citedText("cited_text")
                        .documentIndex(0L)
                        .documentTitle("document_title")
                        .endCharIndex(0L)
                        .fileId("file_id")
                        .startCharIndex(0L)
                        .build()
                )
                .text("text")
                .build()

        val betaContentBlock = BetaContentBlock.ofText(text)

        assertThat(betaContentBlock.text()).contains(text)
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofText(
                BetaTextBlock.builder()
                    .addCitation(
                        BetaCitationCharLocation.builder()
                            .citedText("cited_text")
                            .documentIndex(0L)
                            .documentTitle("document_title")
                            .endCharIndex(0L)
                            .fileId("file_id")
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

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).contains(thinking)
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
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
        val redactedThinking = BetaRedactedThinkingBlock.builder().data("data").build()

        val betaContentBlock = BetaContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).contains(redactedThinking)
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofRedactedThinking(
                BetaRedactedThinkingBlock.builder().data("data").build()
            )

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
                .build()

        val betaContentBlock = BetaContentBlock.ofToolUse(toolUse)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).contains(toolUse)
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
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
                .id("srvtoolu_SQfNkl1n_JR_")
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
                .caller(BetaDirectCaller.builder().build())
                .build()

        val betaContentBlock = BetaContentBlock.ofServerToolUse(serverToolUse)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).contains(serverToolUse)
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofServerToolUse(
                BetaServerToolUseBlock.builder()
                    .id("srvtoolu_SQfNkl1n_JR_")
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name(BetaServerToolUseBlock.Name.WEB_SEARCH)
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
                    BetaWebSearchToolResultError.builder()
                        .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock = BetaContentBlock.ofWebSearchToolResult(webSearchToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).contains(webSearchToolResult)
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofWebSearchToolResult(
                BetaWebSearchToolResultBlock.builder()
                    .content(
                        BetaWebSearchToolResultError.builder()
                            .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                    BetaWebFetchToolResultErrorBlock.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock = BetaContentBlock.ofWebFetchToolResult(webFetchToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).contains(webFetchToolResult)
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofWebFetchToolResult(
                BetaWebFetchToolResultBlock.builder()
                    .content(
                        BetaWebFetchToolResultErrorBlock.builder()
                            .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                    BetaCodeExecutionToolResultError.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock = BetaContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).contains(codeExecutionToolResult)
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofCodeExecutionToolResult(
                BetaCodeExecutionToolResultBlock.builder()
                    .content(
                        BetaCodeExecutionToolResultError.builder()
                            .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                    BetaBashCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock =
            BetaContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult())
            .contains(bashCodeExecutionToolResult)
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofBashCodeExecutionToolResult(
                BetaBashCodeExecutionToolResultBlock.builder()
                    .content(
                        BetaBashCodeExecutionToolResultError.builder()
                            .errorCode(
                                BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                            )
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock =
            BetaContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult())
            .contains(textEditorCodeExecutionToolResult)
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
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
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val betaContentBlock = BetaContentBlock.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).contains(toolSearchToolResult)
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
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
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
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

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).contains(mcpToolUse)
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).isEmpty
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

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).contains(mcpToolResult)
        assertThat(betaContentBlock.containerUpload()).isEmpty
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
        val containerUpload = BetaContainerUploadBlock.builder().fileId("file_id").build()

        val betaContentBlock = BetaContentBlock.ofContainerUpload(containerUpload)

        assertThat(betaContentBlock.text()).isEmpty
        assertThat(betaContentBlock.thinking()).isEmpty
        assertThat(betaContentBlock.redactedThinking()).isEmpty
        assertThat(betaContentBlock.toolUse()).isEmpty
        assertThat(betaContentBlock.serverToolUse()).isEmpty
        assertThat(betaContentBlock.webSearchToolResult()).isEmpty
        assertThat(betaContentBlock.webFetchToolResult()).isEmpty
        assertThat(betaContentBlock.codeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(betaContentBlock.toolSearchToolResult()).isEmpty
        assertThat(betaContentBlock.mcpToolUse()).isEmpty
        assertThat(betaContentBlock.mcpToolResult()).isEmpty
        assertThat(betaContentBlock.containerUpload()).contains(containerUpload)
    }

    @Test
    fun ofContainerUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaContentBlock =
            BetaContentBlock.ofContainerUpload(
                BetaContainerUploadBlock.builder().fileId("file_id").build()
            )

        val roundtrippedBetaContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaContentBlock),
                jacksonTypeRef<BetaContentBlock>(),
            )

        assertThat(roundtrippedBetaContentBlock).isEqualTo(betaContentBlock)
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
    }
}
