// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val betaCodeExecutionToolResultBlock =
            BetaCodeExecutionToolResultBlock.builder()
                .content(
                    BetaCodeExecutionToolResultError.of(
                        BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaCodeExecutionToolResultBlock.content())
            .isEqualTo(
                BetaCodeExecutionToolResultBlockContent.ofError(
                    BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                )
            )
        assertThat(betaCodeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlock =
            BetaCodeExecutionToolResultBlock.builder()
                .content(
                    BetaCodeExecutionToolResultError.of(
                        BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlock),
                jacksonTypeRef<BetaCodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlock)
            .isEqualTo(betaCodeExecutionToolResultBlock)
    }
}
