// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCountTokensContextManagementResponseTest {

    @Test
    fun create() {
        val betaCountTokensContextManagementResponse =
            BetaCountTokensContextManagementResponse.of(0L)

        assertThat(betaCountTokensContextManagementResponse.originalInputTokens()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCountTokensContextManagementResponse =
            BetaCountTokensContextManagementResponse.of(0L)

        val roundtrippedBetaCountTokensContextManagementResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCountTokensContextManagementResponse),
                jacksonTypeRef<BetaCountTokensContextManagementResponse>(),
            )

        assertThat(roundtrippedBetaCountTokensContextManagementResponse)
            .isEqualTo(betaCountTokensContextManagementResponse)
    }
}
