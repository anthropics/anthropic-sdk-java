package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewCaseClosedTest {

    @Test
    fun create() {
        val betaComplianceReviewCaseClosed =
            BetaComplianceReviewCaseClosed.of(
                BetaComplianceReviewCaseCloseReason.of(
                    BetaComplianceReviewCaseCloseReason.Type.RESPONDED
                )
            )

        assertThat(betaComplianceReviewCaseClosed.reason())
            .isEqualTo(
                BetaComplianceReviewCaseCloseReason.of(
                    BetaComplianceReviewCaseCloseReason.Type.RESPONDED
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewCaseClosed =
            BetaComplianceReviewCaseClosed.of(
                BetaComplianceReviewCaseCloseReason.of(
                    BetaComplianceReviewCaseCloseReason.Type.RESPONDED
                )
            )

        val roundtrippedBetaComplianceReviewCaseClosed =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewCaseClosed),
                jacksonTypeRef<BetaComplianceReviewCaseClosed>(),
            )

        assertThat(roundtrippedBetaComplianceReviewCaseClosed)
            .isEqualTo(betaComplianceReviewCaseClosed)
    }
}
