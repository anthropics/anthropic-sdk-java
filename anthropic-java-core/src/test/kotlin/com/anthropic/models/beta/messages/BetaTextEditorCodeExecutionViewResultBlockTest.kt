// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionViewResultBlockTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionViewResultBlock =
            BetaTextEditorCodeExecutionViewResultBlock.builder()
                .content("content")
                .fileType(BetaTextEditorCodeExecutionViewResultBlock.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        assertThat(betaTextEditorCodeExecutionViewResultBlock.content()).isEqualTo("content")
        assertThat(betaTextEditorCodeExecutionViewResultBlock.fileType())
            .isEqualTo(BetaTextEditorCodeExecutionViewResultBlock.FileType.TEXT)
        assertThat(betaTextEditorCodeExecutionViewResultBlock.numLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionViewResultBlock.startLine()).contains(0L)
        assertThat(betaTextEditorCodeExecutionViewResultBlock.totalLines()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionViewResultBlock =
            BetaTextEditorCodeExecutionViewResultBlock.builder()
                .content("content")
                .fileType(BetaTextEditorCodeExecutionViewResultBlock.FileType.TEXT)
                .numLines(0L)
                .startLine(0L)
                .totalLines(0L)
                .build()

        val roundtrippedBetaTextEditorCodeExecutionViewResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionViewResultBlock),
                jacksonTypeRef<BetaTextEditorCodeExecutionViewResultBlock>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionViewResultBlock)
            .isEqualTo(betaTextEditorCodeExecutionViewResultBlock)
    }
}
