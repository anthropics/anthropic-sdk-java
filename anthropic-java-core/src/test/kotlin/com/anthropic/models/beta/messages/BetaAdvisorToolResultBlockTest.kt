// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorToolResultBlockTest {

    @Test
    fun create() {
        val betaAdvisorToolResultBlock =
            BetaAdvisorToolResultBlock.builder()
                .content(
                    BetaAdvisorToolResultError.of(
                        BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(betaAdvisorToolResultBlock.content())
            .isEqualTo(
                BetaAdvisorToolResultBlock.Content.ofBetaAdvisorToolResultError(
                    BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED
                )
            )
        assertThat(betaAdvisorToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorToolResultBlock =
            BetaAdvisorToolResultBlock.builder()
                .content(
                    BetaAdvisorToolResultError.of(
                        BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED
                    )
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedBetaAdvisorToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorToolResultBlock),
                jacksonTypeRef<BetaAdvisorToolResultBlock>(),
            )

        assertThat(roundtrippedBetaAdvisorToolResultBlock).isEqualTo(betaAdvisorToolResultBlock)
    }
}
