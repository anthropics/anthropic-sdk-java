package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewCaseCloseReasonTest {

    @Test
    fun create() {
        val betaComplianceReviewCaseCloseReason =
            BetaComplianceReviewCaseCloseReason.of(
                BetaComplianceReviewCaseCloseReason.Type.RESPONDED
            )

        assertThat(betaComplianceReviewCaseCloseReason.type())
            .isEqualTo(BetaComplianceReviewCaseCloseReason.Type.RESPONDED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewCaseCloseReason =
            BetaComplianceReviewCaseCloseReason.of(
                BetaComplianceReviewCaseCloseReason.Type.RESPONDED
            )

        val roundtrippedBetaComplianceReviewCaseCloseReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewCaseCloseReason),
                jacksonTypeRef<BetaComplianceReviewCaseCloseReason>(),
            )

        assertThat(roundtrippedBetaComplianceReviewCaseCloseReason)
            .isEqualTo(betaComplianceReviewCaseCloseReason)
    }
}
