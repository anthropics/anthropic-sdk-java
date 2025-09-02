// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionToolResultError =
            BetaTextEditorCodeExecutionToolResultError.builder()
                .errorCode(BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(betaTextEditorCodeExecutionToolResultError.errorCode())
            .isEqualTo(BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
        assertThat(betaTextEditorCodeExecutionToolResultError.errorMessage())
            .contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionToolResultError =
            BetaTextEditorCodeExecutionToolResultError.builder()
                .errorCode(BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedBetaTextEditorCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionToolResultError),
                jacksonTypeRef<BetaTextEditorCodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionToolResultError)
            .isEqualTo(betaTextEditorCodeExecutionToolResultError)
    }
}
