// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchToolResultBlockTest {

    @Test
    fun create() {
        val betaWebSearchToolResultBlock =
            BetaWebSearchToolResultBlock.builder()
                .content(
                    BetaWebSearchToolResultError.builder()
                        .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaWebSearchToolResultBlock.content())
            .isEqualTo(
                BetaWebSearchToolResultBlockContent.ofError(
                    BetaWebSearchToolResultError.builder()
                        .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(betaWebSearchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultBlock =
            BetaWebSearchToolResultBlock.builder()
                .content(
                    BetaWebSearchToolResultError.builder()
                        .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaWebSearchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultBlock),
                jacksonTypeRef<BetaWebSearchToolResultBlock>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultBlock).isEqualTo(betaWebSearchToolResultBlock)
    }
}
