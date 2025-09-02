// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionToolResultBlock =
            BetaTextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaTextEditorCodeExecutionToolResultBlock.content())
            .isEqualTo(
                BetaTextEditorCodeExecutionToolResultBlock.Content
                    .ofBetaTextEditorCodeExecutionToolResultError(
                        BetaTextEditorCodeExecutionToolResultError.builder()
                            .errorCode(
                                BetaTextEditorCodeExecutionToolResultError.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
            )
        assertThat(betaTextEditorCodeExecutionToolResultBlock.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionToolResultBlock =
            BetaTextEditorCodeExecutionToolResultBlock.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultError.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaTextEditorCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionToolResultBlock),
                jacksonTypeRef<BetaTextEditorCodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionToolResultBlock)
            .isEqualTo(betaTextEditorCodeExecutionToolResultBlock)
    }
}
