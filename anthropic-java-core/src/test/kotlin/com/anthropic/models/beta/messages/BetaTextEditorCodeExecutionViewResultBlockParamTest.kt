// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionViewResultBlockParamTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionViewResultBlockParam =
            BetaTextEditorCodeExecutionViewResultBlockParam.builder()
                .content("content")
                .fileType(BetaTextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        assertThat(betaTextEditorCodeExecutionViewResultBlockParam.content()).isEqualTo("content")
        assertThat(betaTextEditorCodeExecutionViewResultBlockParam.fileType())
            .isEqualTo(BetaTextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
        assertThat(betaTextEditorCodeExecutionViewResultBlockParam.numLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionViewResultBlockParam.startLine()).contains(0L)
        assertThat(betaTextEditorCodeExecutionViewResultBlockParam.totalLines()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionViewResultBlockParam =
            BetaTextEditorCodeExecutionViewResultBlockParam.builder()
                .content("content")
                .fileType(BetaTextEditorCodeExecutionViewResultBlockParam.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        val roundtrippedBetaTextEditorCodeExecutionViewResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionViewResultBlockParam),
                jacksonTypeRef<BetaTextEditorCodeExecutionViewResultBlockParam>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionViewResultBlockParam)
            .isEqualTo(betaTextEditorCodeExecutionViewResultBlockParam)
    }
}
