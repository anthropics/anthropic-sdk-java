package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewStoredCopyUnavailableTest {

    @Test
    fun create() {
        val betaComplianceReviewStoredCopyUnavailable =
            BetaComplianceReviewStoredCopyUnavailable.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewStoredCopyUnavailable =
            BetaComplianceReviewStoredCopyUnavailable.builder().build()

        val roundtrippedBetaComplianceReviewStoredCopyUnavailable =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewStoredCopyUnavailable),
                jacksonTypeRef<BetaComplianceReviewStoredCopyUnavailable>(),
            )

        assertThat(roundtrippedBetaComplianceReviewStoredCopyUnavailable)
            .isEqualTo(betaComplianceReviewStoredCopyUnavailable)
    }
}
