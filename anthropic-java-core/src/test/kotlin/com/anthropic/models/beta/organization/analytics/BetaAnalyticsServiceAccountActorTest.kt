package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsServiceAccountActorTest {

    @Test
    fun create() {
        val betaAnalyticsServiceAccountActor =
            BetaAnalyticsServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        assertThat(betaAnalyticsServiceAccountActor.serviceAccountId())
            .isEqualTo("svac_01Hk3R9TWxq7CfQak00OiVw4")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsServiceAccountActor =
            BetaAnalyticsServiceAccountActor.of("svac_01Hk3R9TWxq7CfQak00OiVw4")

        val roundtrippedBetaAnalyticsServiceAccountActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsServiceAccountActor),
                jacksonTypeRef<BetaAnalyticsServiceAccountActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsServiceAccountActor)
            .isEqualTo(betaAnalyticsServiceAccountActor)
    }
}
