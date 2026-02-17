// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionViewResultBlockParamTest {

    @Test
    fun create() {
        val textEditorCodeExecutionViewResultBlockParam =
            TextEditorCodeExecutionViewResultBlockParam.builder()
                .content("content")
                .fileType(TextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        assertThat(textEditorCodeExecutionViewResultBlockParam.content()).isEqualTo("content")
        assertThat(textEditorCodeExecutionViewResultBlockParam.fileType())
            .isEqualTo(TextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
        assertThat(textEditorCodeExecutionViewResultBlockParam.numLines()).contains(0L)
        assertThat(textEditorCodeExecutionViewResultBlockParam.startLine()).contains(0L)
        assertThat(textEditorCodeExecutionViewResultBlockParam.totalLines()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionViewResultBlockParam =
            TextEditorCodeExecutionViewResultBlockParam.builder()
                .content("content")
                .fileType(TextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        val roundtrippedTextEditorCodeExecutionViewResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionViewResultBlockParam),
                jacksonTypeRef<TextEditorCodeExecutionViewResultBlockParam>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionViewResultBlockParam)
            .isEqualTo(textEditorCodeExecutionViewResultBlockParam)
    }
}
