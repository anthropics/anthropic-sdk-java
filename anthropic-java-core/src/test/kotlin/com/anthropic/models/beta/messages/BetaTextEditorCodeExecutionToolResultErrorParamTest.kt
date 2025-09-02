// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionToolResultErrorParam =
            BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                .errorCode(
                    BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                )
                .errorMessage("error_message")
                .build()

        assertThat(betaTextEditorCodeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
        assertThat(betaTextEditorCodeExecutionToolResultErrorParam.errorMessage())
            .contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionToolResultErrorParam =
            BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                .errorCode(
                    BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                )
                .errorMessage("error_message")
                .build()

        val roundtrippedBetaTextEditorCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionToolResultErrorParam),
                jacksonTypeRef<BetaTextEditorCodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionToolResultErrorParam)
            .isEqualTo(betaTextEditorCodeExecutionToolResultErrorParam)
    }
}
