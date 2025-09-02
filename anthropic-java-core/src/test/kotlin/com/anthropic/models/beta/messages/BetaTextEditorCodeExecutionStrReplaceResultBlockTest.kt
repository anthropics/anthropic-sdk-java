// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionStrReplaceResultBlockTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionStrReplaceResultBlock =
            BetaTextEditorCodeExecutionStrReplaceResultBlock.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlock.lines().getOrNull())
            .containsExactly("string")
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlock.newLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlock.newStart()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlock.oldLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlock.oldStart()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionStrReplaceResultBlock =
            BetaTextEditorCodeExecutionStrReplaceResultBlock.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        val roundtrippedBetaTextEditorCodeExecutionStrReplaceResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionStrReplaceResultBlock),
                jacksonTypeRef<BetaTextEditorCodeExecutionStrReplaceResultBlock>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionStrReplaceResultBlock)
            .isEqualTo(betaTextEditorCodeExecutionStrReplaceResultBlock)
    }
}
