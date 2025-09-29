// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMessageTokensCountTest {

    @Test
    fun create() {
        val betaMessageTokensCount =
            BetaMessageTokensCount.builder()
                .contextManagement(
                    BetaCountTokensContextManagementResponse.builder()
                        .originalInputTokens(0L)
                        .build()
                )
                .inputTokens(2095L)
                .build()

        assertThat(betaMessageTokensCount.contextManagement())
            .contains(
                BetaCountTokensContextManagementResponse.builder().originalInputTokens(0L).build()
            )
        assertThat(betaMessageTokensCount.inputTokens()).isEqualTo(2095L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageTokensCount =
            BetaMessageTokensCount.builder()
                .contextManagement(
                    BetaCountTokensContextManagementResponse.builder()
                        .originalInputTokens(0L)
                        .build()
                )
                .inputTokens(2095L)
                .build()

        val roundtrippedBetaMessageTokensCount =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageTokensCount),
                jacksonTypeRef<BetaMessageTokensCount>(),
            )

        assertThat(roundtrippedBetaMessageTokensCount).isEqualTo(betaMessageTokensCount)
    }
}
