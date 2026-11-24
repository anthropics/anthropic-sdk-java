package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewEnforcementActionTest {

    @Test
    fun create() {
        val betaComplianceReviewEnforcementAction = BetaComplianceReviewEnforcementAction.of("type")

        assertThat(betaComplianceReviewEnforcementAction.type()).isEqualTo("type")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewEnforcementAction = BetaComplianceReviewEnforcementAction.of("type")

        val roundtrippedBetaComplianceReviewEnforcementAction =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewEnforcementAction),
                jacksonTypeRef<BetaComplianceReviewEnforcementAction>(),
            )

        assertThat(roundtrippedBetaComplianceReviewEnforcementAction)
            .isEqualTo(betaComplianceReviewEnforcementAction)
    }
}
