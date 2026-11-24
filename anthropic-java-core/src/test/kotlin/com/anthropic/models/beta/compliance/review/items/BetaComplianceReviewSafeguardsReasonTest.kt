package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewSafeguardsReasonTest {

    @Test
    fun create() {
        val betaComplianceReviewSafeguardsReason =
            BetaComplianceReviewSafeguardsReason.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewSafeguardsReason =
            BetaComplianceReviewSafeguardsReason.builder().build()

        val roundtrippedBetaComplianceReviewSafeguardsReason =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewSafeguardsReason),
                jacksonTypeRef<BetaComplianceReviewSafeguardsReason>(),
            )

        assertThat(roundtrippedBetaComplianceReviewSafeguardsReason)
            .isEqualTo(betaComplianceReviewSafeguardsReason)
    }
}
