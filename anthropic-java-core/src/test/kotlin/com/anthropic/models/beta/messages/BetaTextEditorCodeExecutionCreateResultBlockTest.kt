// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionCreateResultBlockTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionCreateResultBlock =
            BetaTextEditorCodeExecutionCreateResultBlock.builder().isFileUpdate(true).build()

        assertThat(betaTextEditorCodeExecutionCreateResultBlock.isFileUpdate()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionCreateResultBlock =
            BetaTextEditorCodeExecutionCreateResultBlock.builder().isFileUpdate(true).build()

        val roundtrippedBetaTextEditorCodeExecutionCreateResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionCreateResultBlock),
                jacksonTypeRef<BetaTextEditorCodeExecutionCreateResultBlock>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionCreateResultBlock)
            .isEqualTo(betaTextEditorCodeExecutionCreateResultBlock)
    }
}
