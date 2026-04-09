// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorToolResultErrorTest {

    @Test
    fun create() {
        val betaAdvisorToolResultError =
            BetaAdvisorToolResultError.builder()
                .errorCode(BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED)
                .build()

        assertThat(betaAdvisorToolResultError.errorCode())
            .isEqualTo(BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorToolResultError =
            BetaAdvisorToolResultError.builder()
                .errorCode(BetaAdvisorToolResultError.ErrorCode.MAX_USES_EXCEEDED)
                .build()

        val roundtrippedBetaAdvisorToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorToolResultError),
                jacksonTypeRef<BetaAdvisorToolResultError>(),
            )

        assertThat(roundtrippedBetaAdvisorToolResultError).isEqualTo(betaAdvisorToolResultError)
    }
}
