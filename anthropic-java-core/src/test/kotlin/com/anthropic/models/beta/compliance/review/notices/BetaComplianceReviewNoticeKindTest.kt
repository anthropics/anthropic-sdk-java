package com.anthropic.models.beta.compliance.review.notices

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewNoticeKindTest {

    @Test
    fun create() {
        val betaComplianceReviewNoticeKind =
            BetaComplianceReviewNoticeKind.of(
                BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED
            )

        assertThat(betaComplianceReviewNoticeKind.type())
            .isEqualTo(BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewNoticeKind =
            BetaComplianceReviewNoticeKind.of(
                BetaComplianceReviewNoticeKind.Type.RESPONSE_REQUESTED
            )

        val roundtrippedBetaComplianceReviewNoticeKind =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewNoticeKind),
                jacksonTypeRef<BetaComplianceReviewNoticeKind>(),
            )

        assertThat(roundtrippedBetaComplianceReviewNoticeKind)
            .isEqualTo(betaComplianceReviewNoticeKind)
    }
}
