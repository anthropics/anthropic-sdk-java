// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchToolResultBlockTest {

    @Test
    fun create() {
        val betaWebFetchToolResultBlock =
            BetaWebFetchToolResultBlock.builder()
                .content(
                    BetaWebFetchToolResultErrorBlock.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaWebFetchToolResultBlock.content())
            .isEqualTo(
                BetaWebFetchToolResultBlock.Content.ofBetaWebFetchToolResultErrorBlock(
                    BetaWebFetchToolResultErrorBlock.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(betaWebFetchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaWebFetchToolResultBlock.caller())
            .contains(
                BetaWebFetchToolResultBlock.Caller.ofDirect(BetaDirectCaller.builder().build())
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchToolResultBlock =
            BetaWebFetchToolResultBlock.builder()
                .content(
                    BetaWebFetchToolResultErrorBlock.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaWebFetchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchToolResultBlock),
                jacksonTypeRef<BetaWebFetchToolResultBlock>(),
            )

        assertThat(roundtrippedBetaWebFetchToolResultBlock).isEqualTo(betaWebFetchToolResultBlock)
    }
}
