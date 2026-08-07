// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionCreateResultBlockParamTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionCreateResultBlockParam =
            BetaTextEditorCodeExecutionCreateResultBlockParam.of(true)

        assertThat(betaTextEditorCodeExecutionCreateResultBlockParam.isFileUpdate()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionCreateResultBlockParam =
            BetaTextEditorCodeExecutionCreateResultBlockParam.of(true)

        val roundtrippedBetaTextEditorCodeExecutionCreateResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionCreateResultBlockParam),
                jacksonTypeRef<BetaTextEditorCodeExecutionCreateResultBlockParam>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionCreateResultBlockParam)
            .isEqualTo(betaTextEditorCodeExecutionCreateResultBlockParam)
    }
}
