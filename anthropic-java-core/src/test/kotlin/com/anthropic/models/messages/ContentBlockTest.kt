// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ContentBlockTest {

    @Test
    fun ofText() {
        val text =
            TextBlock.builder()
                .addCitation(
                    CitationCharLocation.builder()
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

        val contentBlock = ContentBlock.ofText(text)

        assertThat(contentBlock.text()).contains(text)
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofTextRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofText(
                TextBlock.builder()
                    .addCitation(
                        CitationCharLocation.builder()
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

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofThinking() {
        val thinking = ThinkingBlock.builder().signature("signature").thinking("thinking").build()

        val contentBlock = ContentBlock.ofThinking(thinking)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).contains(thinking)
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofThinking(
                ThinkingBlock.builder().signature("signature").thinking("thinking").build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofRedactedThinking() {
        val redactedThinking = RedactedThinkingBlock.builder().data("data").build()

        val contentBlock = ContentBlock.ofRedactedThinking(redactedThinking)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).contains(redactedThinking)
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofRedactedThinkingRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofRedactedThinking(RedactedThinkingBlock.builder().data("data").build())

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofToolUse() {
        val toolUse =
            ToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf<String, Any>()))
                .name("x")
                .build()

        val contentBlock = ContentBlock.ofToolUse(toolUse)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).contains(toolUse)
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofToolUse(
                ToolUseBlock.builder()
                    .id("id")
                    .caller(DirectCaller.builder().build())
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name("x")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofServerToolUse() {
        val serverToolUse =
            ServerToolUseBlock.builder()
                .id("srvtoolu_SQfNkl1n_JR_")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(ServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        val contentBlock = ContentBlock.ofServerToolUse(serverToolUse)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).contains(serverToolUse)
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofServerToolUse(
                ServerToolUseBlock.builder()
                    .id("srvtoolu_SQfNkl1n_JR_")
                    .caller(DirectCaller.builder().build())
                    .input(JsonValue.from(mapOf<String, Any>()))
                    .name(ServerToolUseBlock.Name.WEB_SEARCH)
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofWebSearchToolResult() {
        val webSearchToolResult =
            WebSearchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebSearchToolResultError.builder()
                        .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock = ContentBlock.ofWebSearchToolResult(webSearchToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).contains(webSearchToolResult)
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofWebSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofWebSearchToolResult(
                WebSearchToolResultBlock.builder()
                    .caller(DirectCaller.builder().build())
                    .content(
                        WebSearchToolResultError.builder()
                            .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofWebFetchToolResult() {
        val webFetchToolResult =
            WebFetchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebFetchToolResultErrorBlock.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock = ContentBlock.ofWebFetchToolResult(webFetchToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).contains(webFetchToolResult)
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofWebFetchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofWebFetchToolResult(
                WebFetchToolResultBlock.builder()
                    .caller(DirectCaller.builder().build())
                    .content(
                        WebFetchToolResultErrorBlock.builder()
                            .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofCodeExecutionToolResult() {
        val codeExecutionToolResult =
            CodeExecutionToolResultBlock.builder()
                .content(
                    CodeExecutionToolResultError.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock = ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).contains(codeExecutionToolResult)
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofCodeExecutionToolResult(
                CodeExecutionToolResultBlock.builder()
                    .content(
                        CodeExecutionToolResultError.builder()
                            .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofBashCodeExecutionToolResult() {
        val bashCodeExecutionToolResult =
            BashCodeExecutionToolResultBlock.builder()
                .content(
                    BashCodeExecutionToolResultError.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock = ContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).contains(bashCodeExecutionToolResult)
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofBashCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofBashCodeExecutionToolResult(
                BashCodeExecutionToolResultBlock.builder()
                    .content(
                        BashCodeExecutionToolResultError.builder()
                            .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofTextEditorCodeExecutionToolResult() {
        val textEditorCodeExecutionToolResult =
            TextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    TextEditorCodeExecutionToolResultError.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock =
            ContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult())
            .contains(textEditorCodeExecutionToolResult)
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofTextEditorCodeExecutionToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofTextEditorCodeExecutionToolResult(
                TextEditorCodeExecutionToolResultBlock.builder()
                    .content(
                        TextEditorCodeExecutionToolResultError.builder()
                            .errorCode(
                                TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofToolSearchToolResult() {
        val toolSearchToolResult =
            ToolSearchToolResultBlock.builder()
                .content(
                    ToolSearchToolResultError.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val contentBlock = ContentBlock.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).contains(toolSearchToolResult)
        assertThat(contentBlock.containerUpload()).isEmpty
    }

    @Test
    fun ofToolSearchToolResultRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofToolSearchToolResult(
                ToolSearchToolResultBlock.builder()
                    .content(
                        ToolSearchToolResultError.builder()
                            .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                            .errorMessage("error_message")
                            .build()
                    )
                    .toolUseId("srvtoolu_SQfNkl1n_JR_")
                    .build()
            )

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun ofContainerUpload() {
        val containerUpload = ContainerUploadBlock.builder().fileId("file_id").build()

        val contentBlock = ContentBlock.ofContainerUpload(containerUpload)

        assertThat(contentBlock.text()).isEmpty
        assertThat(contentBlock.thinking()).isEmpty
        assertThat(contentBlock.redactedThinking()).isEmpty
        assertThat(contentBlock.toolUse()).isEmpty
        assertThat(contentBlock.serverToolUse()).isEmpty
        assertThat(contentBlock.webSearchToolResult()).isEmpty
        assertThat(contentBlock.webFetchToolResult()).isEmpty
        assertThat(contentBlock.codeExecutionToolResult()).isEmpty
        assertThat(contentBlock.bashCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.textEditorCodeExecutionToolResult()).isEmpty
        assertThat(contentBlock.toolSearchToolResult()).isEmpty
        assertThat(contentBlock.containerUpload()).contains(containerUpload)
    }

    @Test
    fun ofContainerUploadRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofContainerUpload(ContainerUploadBlock.builder().fileId("file_id").build())

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
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
        val contentBlock = jsonMapper().convertValue(testCase.value, jacksonTypeRef<ContentBlock>())

        val e = assertThrows<AnthropicInvalidDataException> { contentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
