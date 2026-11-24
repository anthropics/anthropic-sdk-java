package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComplianceReviewApiActorTest {

    @Test
    fun create() {
        val betaComplianceReviewApiActor = BetaComplianceReviewApiActor.of("api_key_id")

        assertThat(betaComplianceReviewApiActor.apiKeyId()).isEqualTo("api_key_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceReviewApiActor = BetaComplianceReviewApiActor.of("api_key_id")

        val roundtrippedBetaComplianceReviewApiActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceReviewApiActor),
                jacksonTypeRef<BetaComplianceReviewApiActor>(),
            )

        assertThat(roundtrippedBetaComplianceReviewApiActor).isEqualTo(betaComplianceReviewApiActor)
    }
}
