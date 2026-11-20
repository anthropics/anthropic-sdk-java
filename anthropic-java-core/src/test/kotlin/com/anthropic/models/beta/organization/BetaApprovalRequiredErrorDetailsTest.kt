package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaApprovalRequiredErrorDetailsTest {

    @Test
    fun create() {
        val betaApprovalRequiredErrorDetails =
            BetaApprovalRequiredErrorDetails.of("inference_data_privacy.human_review")

        assertThat(betaApprovalRequiredErrorDetails.field())
            .isEqualTo("inference_data_privacy.human_review")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaApprovalRequiredErrorDetails =
            BetaApprovalRequiredErrorDetails.of("inference_data_privacy.human_review")

        val roundtrippedBetaApprovalRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaApprovalRequiredErrorDetails),
                jacksonTypeRef<BetaApprovalRequiredErrorDetails>(),
            )

        assertThat(roundtrippedBetaApprovalRequiredErrorDetails)
            .isEqualTo(betaApprovalRequiredErrorDetails)
    }
}
