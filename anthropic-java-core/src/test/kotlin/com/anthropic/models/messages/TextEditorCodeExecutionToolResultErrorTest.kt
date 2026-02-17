// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val textEditorCodeExecutionToolResultError =
            TextEditorCodeExecutionToolResultError.builder()
                .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(textEditorCodeExecutionToolResultError.errorCode())
            .isEqualTo(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
        assertThat(textEditorCodeExecutionToolResultError.errorMessage()).contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionToolResultError =
            TextEditorCodeExecutionToolResultError.builder()
                .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedTextEditorCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionToolResultError),
                jacksonTypeRef<TextEditorCodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionToolResultError)
            .isEqualTo(textEditorCodeExecutionToolResultError)
    }
}
