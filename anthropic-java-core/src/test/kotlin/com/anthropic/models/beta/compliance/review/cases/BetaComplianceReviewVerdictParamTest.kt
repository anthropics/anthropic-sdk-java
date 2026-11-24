package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewVerdictParamTest {

    @Test
    fun create() {
        val betaComplianceReviewVerdictParam =
            BetaComplianceReviewVerdictParam.builder()
                .determination(BetaComplianceReviewDetermination.VIOLATION)
                .itemId("item_id")
                .policyCategory(BetaComplianceReviewViolatedPolicyCategory.CYBER)
                .build()

        assertThat(betaComplianceReviewVerdictParam.determination())
            .isEqualTo(BetaComplianceReviewDetermination.VIOLATION)
        assertThat(betaComplianceReviewVerdictParam.itemId()).isEqualTo("item_id")
        assertThat(betaComplianceReviewVerdictParam.policyCategory())
            .contains(BetaComplianceReviewViolatedPolicyCategory.CYBER)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewVerdictParam =
            BetaComplianceReviewVerdictParam.builder()
                .determination(BetaComplianceReviewDetermination.VIOLATION)
                .itemId("item_id")
                .policyCategory(BetaComplianceReviewViolatedPolicyCategory.CYBER)
                .build()

        val roundtrippedBetaComplianceReviewVerdictParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewVerdictParam),
                jacksonTypeRef<BetaComplianceReviewVerdictParam>(),
            )

        assertThat(roundtrippedBetaComplianceReviewVerdictParam)
            .isEqualTo(betaComplianceReviewVerdictParam)
    }
}
