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
        val redactedThinking = RedactedThinkingBlock.of("data")

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
        val contentBlock = ContentBlock.ofRedactedThinking(RedactedThinkingBlock.of("data"))

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
                .toolsetName("toolset_name")
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
                    .toolsetName("toolset_name")
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
                    WebSearchToolResultError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
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
                        WebSearchToolResultError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
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
                    WebFetchToolResultErrorBlock.of(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
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
                        WebFetchToolResultErrorBlock.of(
                            WebFetchToolResultErrorCode.INVALID_TOOL_INPUT
                        )
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
                    CodeExecutionToolResultError.of(
                        CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
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
                        CodeExecutionToolResultError.of(
                            CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
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
                    BashCodeExecutionToolResultError.of(
                        BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
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
                        BashCodeExecutionToolResultError.of(
                            BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                        )
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
        val containerUpload = ContainerUploadBlock.of("file_id")

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
        val contentBlock = ContentBlock.ofContainerUpload(ContainerUploadBlock.of("file_id"))

        val roundtrippedContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(contentBlock),
                jacksonTypeRef<ContentBlock>(),
            )

        assertThat(roundtrippedContentBlock).isEqualTo(contentBlock)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val contentBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to "id",
                            "tool_use_id" to "srvtoolu_SQfNkl1n_JR_",
                        )
                    ),
                    jacksonTypeRef<ContentBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { contentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(contentBlock.id()).contains("id")
        assertThat(contentBlock.toolUseId()).contains("srvtoolu_SQfNkl1n_JR_")

        val mismatchedContentBlock =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to listOf("invalid"),
                            "tool_use_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<ContentBlock>(),
                )

        assertThat(mismatchedContentBlock.id()).isEmpty
        assertThat(mismatchedContentBlock.toolUseId()).isEmpty
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

        assertThat(contentBlock.id()).isEmpty
        assertThat(contentBlock.toolUseId()).isEmpty
    }
}
