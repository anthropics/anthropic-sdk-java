package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(contentBlock)).isEqualTo(mapOf("text" to Optional.of(text)))
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

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("thinking" to Optional.of(thinking)))
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

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("redactedThinking" to Optional.of(redactedThinking)))
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

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("toolUse" to Optional.of(toolUse)))
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
                .id("srvtoolu_c")
                .caller(DirectCaller.builder().build())
                .input(JsonValue.from(mapOf<String, Any>()))
                .name(ServerToolUseBlock.Name.WEB_SEARCH)
                .build()

        val contentBlock = ContentBlock.ofServerToolUse(serverToolUse)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("serverToolUse" to Optional.of(serverToolUse)))
    }

    @Test
    fun ofServerToolUseRoundtrip() {
        val jsonMapper = jsonMapper()
        val contentBlock =
            ContentBlock.ofServerToolUse(
                ServerToolUseBlock.builder()
                    .id("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock = ContentBlock.ofWebSearchToolResult(webSearchToolResult)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("webSearchToolResult" to Optional.of(webSearchToolResult)))
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
                    .toolUseId("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock = ContentBlock.ofWebFetchToolResult(webFetchToolResult)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("webFetchToolResult" to Optional.of(webFetchToolResult)))
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
                    .toolUseId("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock = ContentBlock.ofCodeExecutionToolResult(codeExecutionToolResult)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("codeExecutionToolResult" to Optional.of(codeExecutionToolResult)))
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
                    .toolUseId("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock = ContentBlock.ofBashCodeExecutionToolResult(bashCodeExecutionToolResult)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(
                mapOf("bashCodeExecutionToolResult" to Optional.of(bashCodeExecutionToolResult))
            )
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
                    .toolUseId("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock =
            ContentBlock.ofTextEditorCodeExecutionToolResult(textEditorCodeExecutionToolResult)

        assertThat(presentVariants(contentBlock))
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
                    .toolUseId("srvtoolu_c")
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
                .toolUseId("srvtoolu_c")
                .build()

        val contentBlock = ContentBlock.ofToolSearchToolResult(toolSearchToolResult)

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("toolSearchToolResult" to Optional.of(toolSearchToolResult)))
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
                    .toolUseId("srvtoolu_c")
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

        assertThat(presentVariants(contentBlock))
            .isEqualTo(mapOf("containerUpload" to Optional.of(containerUpload)))
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
                            "tool_use_id" to "srvtoolu_c",
                        )
                    ),
                    jacksonTypeRef<ContentBlock>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { contentBlock.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(contentBlock.id()).contains("id")
        assertThat(contentBlock.toolUseId()).contains("srvtoolu_c")

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

    private fun presentVariants(contentBlock: ContentBlock): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "text" to contentBlock.text(),
                "thinking" to contentBlock.thinking(),
                "redactedThinking" to contentBlock.redactedThinking(),
                "toolUse" to contentBlock.toolUse(),
                "serverToolUse" to contentBlock.serverToolUse(),
                "webSearchToolResult" to contentBlock.webSearchToolResult(),
                "webFetchToolResult" to contentBlock.webFetchToolResult(),
                "codeExecutionToolResult" to contentBlock.codeExecutionToolResult(),
                "bashCodeExecutionToolResult" to contentBlock.bashCodeExecutionToolResult(),
                "textEditorCodeExecutionToolResult" to
                    contentBlock.textEditorCodeExecutionToolResult(),
                "toolSearchToolResult" to contentBlock.toolSearchToolResult(),
                "containerUpload" to contentBlock.containerUpload(),
            )
            .filterValues { it.isPresent }
}
