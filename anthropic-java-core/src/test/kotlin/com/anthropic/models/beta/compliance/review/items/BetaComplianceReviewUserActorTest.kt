package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewUserActorTest {

    @Test
    fun create() {
        val betaComplianceReviewUserActor =
            BetaComplianceReviewUserActor.builder()
                .emailAddress("email_address")
                .userId("user_id")
                .build()

        assertThat(betaComplianceReviewUserActor.emailAddress()).contains("email_address")
        assertThat(betaComplianceReviewUserActor.userId()).isEqualTo("user_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewUserActor =
            BetaComplianceReviewUserActor.builder()
                .emailAddress("email_address")
                .userId("user_id")
                .build()

        val roundtrippedBetaComplianceReviewUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewUserActor),
                jacksonTypeRef<BetaComplianceReviewUserActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewUserActor)
            .isEqualTo(betaComplianceReviewUserActor)
    }
}
