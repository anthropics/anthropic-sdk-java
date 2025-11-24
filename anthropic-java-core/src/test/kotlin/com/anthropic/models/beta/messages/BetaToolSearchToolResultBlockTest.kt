// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolResultBlockTest {

    @Test
    fun create() {
        val betaToolSearchToolResultBlock =
            BetaToolSearchToolResultBlock.builder()
                .content(
                    BetaToolSearchToolResultError.builder()
                        .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaToolSearchToolResultBlock.content())
            .isEqualTo(
                BetaToolSearchToolResultBlock.Content.ofBetaToolSearchToolResultError(
                    BetaToolSearchToolResultError.builder()
                        .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
            )
        assertThat(betaToolSearchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolResultBlock =
            BetaToolSearchToolResultBlock.builder()
                .content(
                    BetaToolSearchToolResultError.builder()
                        .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaToolSearchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolResultBlock),
                jacksonTypeRef<BetaToolSearchToolResultBlock>(),
            )

        assertThat(roundtrippedBetaToolSearchToolResultBlock)
            .isEqualTo(betaToolSearchToolResultBlock)
    }
}
