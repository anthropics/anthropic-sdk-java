// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionStrReplaceResultBlockParamTest {

    @Test
    fun create() {
        val textEditorCodeExecutionStrReplaceResultBlockParam =
            TextEditorCodeExecutionStrReplaceResultBlockParam.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        assertThat(textEditorCodeExecutionStrReplaceResultBlockParam.lines().getOrNull())
            .containsExactly("string")
        assertThat(textEditorCodeExecutionStrReplaceResultBlockParam.newLines()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlockParam.newStart()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlockParam.oldLines()).contains(0L)
        assertThat(textEditorCodeExecutionStrReplaceResultBlockParam.oldStart()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionStrReplaceResultBlockParam =
            TextEditorCodeExecutionStrReplaceResultBlockParam.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        val roundtrippedTextEditorCodeExecutionStrReplaceResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionStrReplaceResultBlockParam),
                jacksonTypeRef<TextEditorCodeExecutionStrReplaceResultBlockParam>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionStrReplaceResultBlockParam)
            .isEqualTo(textEditorCodeExecutionStrReplaceResultBlockParam)
    }
}
