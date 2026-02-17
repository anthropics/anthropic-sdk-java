// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionStrReplaceResultBlockTest {

    @Test
    fun create() {
        val textEditorCodeExecutionStrReplaceResultBlock =
            TextEditorCodeExecutionStrReplaceResultBlock.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        assertThat(textEditorCodeExecutionStrReplaceResultBlock.lines().getOrNull())
            .containsExactly("string")
        assertThat(textEditorCodeExecutionStrReplaceResultBlock.newLines()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlock.newStart()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlock.oldLines()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlock.oldStart()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionStrReplaceResultBlock =
            TextEditorCodeExecutionStrReplaceResultBlock.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        val roundtrippedTextEditorCodeExecutionStrReplaceResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionStrReplaceResultBlock),
                jacksonTypeRef<TextEditorCodeExecutionStrReplaceResultBlock>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionStrReplaceResultBlock)
            .isEqualTo(textEditorCodeExecutionStrReplaceResultBlock)
    }
}
