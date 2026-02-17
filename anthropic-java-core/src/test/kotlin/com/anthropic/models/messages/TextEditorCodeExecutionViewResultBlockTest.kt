// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionViewResultBlockTest {

    @Test
    fun create() {
        val textEditorCodeExecutionViewResultBlock =
            TextEditorCodeExecutionViewResultBlock.builder()
                .content("content")
                .fileType(TextEditorCodeExecutionViewResultBlock.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        assertThat(textEditorCodeExecutionViewResultBlock.content()).isEqualTo("content")
        assertThat(textEditorCodeExecutionViewResultBlock.fileType())
            .isEqualTo(TextEditorCodeExecutionViewResultBlock.FileType.TEXT)
        assertThat(textEditorCodeExecutionViewResultBlock.numLines()).contains(0L)
        assertThat(textEditorCodeExecutionViewResultBlock.startLine()).contains(0L)
        assertThat(textEditorCodeExecutionViewResultBlock.totalLines()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionViewResultBlock =
            TextEditorCodeExecutionViewResultBlock.builder()
                .content("content")
                .fileType(TextEditorCodeExecutionViewResultBlock.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        val roundtrippedTextEditorCodeExecutionViewResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionViewResultBlock),
                jacksonTypeRef<TextEditorCodeExecutionViewResultBlock>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionViewResultBlock)
            .isEqualTo(textEditorCodeExecutionViewResultBlock)
    }
}
