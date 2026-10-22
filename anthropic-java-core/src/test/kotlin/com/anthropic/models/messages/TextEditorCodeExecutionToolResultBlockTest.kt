package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val textEditorCodeExecutionToolResultBlock =
            TextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    TextEditorCodeExecutionToolResultError.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        assertThat(textEditorCodeExecutionToolResultBlock.content())
            .isEqualTo(
                TextEditorCodeExecutionToolResultBlock.Content
                    .ofTextEditorCodeExecutionToolResultError(
                        TextEditorCodeExecutionToolResultError.builder()
                            .errorCode(
                                TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
            )
        assertThat(textEditorCodeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionToolResultBlock =
            TextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    TextEditorCodeExecutionToolResultError.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        val roundtrippedTextEditorCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionToolResultBlock),
                jacksonTypeRef<TextEditorCodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionToolResultBlock)
            .isEqualTo(textEditorCodeExecutionToolResultBlock)
    }
}
