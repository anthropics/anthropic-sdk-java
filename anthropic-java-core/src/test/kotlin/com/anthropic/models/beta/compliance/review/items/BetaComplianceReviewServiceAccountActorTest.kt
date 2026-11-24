package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewServiceAccountActorTest {

    @Test
    fun create() {
        val betaComplianceReviewServiceAccountActor =
            BetaComplianceReviewServiceAccountActor.of("service_account_id")

        assertThat(betaComplianceReviewServiceAccountActor.serviceAccountId())
            .isEqualTo("service_account_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewServiceAccountActor =
            BetaComplianceReviewServiceAccountActor.of("service_account_id")

        val roundtrippedBetaComplianceReviewServiceAccountActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewServiceAccountActor),
                jacksonTypeRef<BetaComplianceReviewServiceAccountActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewServiceAccountActor)
            .isEqualTo(betaComplianceReviewServiceAccountActor)
    }
}
