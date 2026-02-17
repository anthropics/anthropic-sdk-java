// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val textEditorCodeExecutionToolResultErrorParam =
            TextEditorCodeExecutionToolResultErrorParam.builder()
                .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(textEditorCodeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
        assertThat(textEditorCodeExecutionToolResultErrorParam.errorMessage())
            .contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionToolResultErrorParam =
            TextEditorCodeExecutionToolResultErrorParam.builder()
                .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedTextEditorCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionToolResultErrorParam),
                jacksonTypeRef<TextEditorCodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionToolResultErrorParam)
            .isEqualTo(textEditorCodeExecutionToolResultErrorParam)
    }
}
