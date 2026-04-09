// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorToolResultErrorParamTest {

    @Test
    fun create() {
        val betaAdvisorToolResultErrorParam =
            BetaAdvisorToolResultErrorParam.builder()
                .errorCode(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
                .build()

        assertThat(betaAdvisorToolResultErrorParam.errorCode())
            .isEqualTo(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorToolResultErrorParam =
            BetaAdvisorToolResultErrorParam.builder()
                .errorCode(BetaAdvisorToolResultErrorParam.ErrorCode.MAX_USES_EXCEEDED)
                .build()

        val roundtrippedBetaAdvisorToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorToolResultErrorParam),
                jacksonTypeRef<BetaAdvisorToolResultErrorParam>(),
            )

        assertThat(roundtrippedBetaAdvisorToolResultErrorParam)
            .isEqualTo(betaAdvisorToolResultErrorParam)
    }
}
