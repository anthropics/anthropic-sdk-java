package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewTranscriptTest {

    @Test
    fun create() {
        val betaComplianceReviewTranscript =
            BetaComplianceReviewTranscript.builder()
                .itemId("item_id")
                .userProfileId("user_profile_id")
                .build()

        assertThat(betaComplianceReviewTranscript.itemId()).isEqualTo("item_id")
        assertThat(betaComplianceReviewTranscript.userProfileId()).contains("user_profile_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewTranscript =
            BetaComplianceReviewTranscript.builder()
                .itemId("item_id")
                .userProfileId("user_profile_id")
                .build()

        val roundtrippedBetaComplianceReviewTranscript =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewTranscript),
                jacksonTypeRef<BetaComplianceReviewTranscript>(),
            )

        assertThat(roundtrippedBetaComplianceReviewTranscript)
            .isEqualTo(betaComplianceReviewTranscript)
    }
}
