// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionStrReplaceResultBlockParamTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionStrReplaceResultBlockParam =
            BetaTextEditorCodeExecutionStrReplaceResultBlockParam.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlockParam.lines().getOrNull())
            .containsExactly("string")
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlockParam.newLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlockParam.newStart()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlockParam.oldLines()).contains(0L)
        assertThat(betaTextEditorCodeExecutionStrReplaceResultBlockParam.oldStart()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionStrReplaceResultBlockParam =
            BetaTextEditorCodeExecutionStrReplaceResultBlockParam.builder()
                .addLine("string")
                .newLines(0L)
                .newStart(0L)
                .oldLines(0L)
                .oldStart(0L)
                .build()

        val roundtrippedBetaTextEditorCodeExecutionStrReplaceResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(
                    betaTextEditorCodeExecutionStrReplaceResultBlockParam
                ),
                jacksonTypeRef<BetaTextEditorCodeExecutionStrReplaceResultBlockParam>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionStrReplaceResultBlockParam)
            .isEqualTo(betaTextEditorCodeExecutionStrReplaceResultBlockParam)
    }
}
