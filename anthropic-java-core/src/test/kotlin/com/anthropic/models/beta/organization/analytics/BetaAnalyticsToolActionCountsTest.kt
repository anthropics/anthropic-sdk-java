package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsToolActionCountsTest {

    @Test
    fun create() {
        val betaAnalyticsToolActionCounts =
            BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()

        assertThat(betaAnalyticsToolActionCounts.acceptedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsToolActionCounts.rejectedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsToolActionCounts =
            BetaAnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()

        val roundtrippedBetaAnalyticsToolActionCounts =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsToolActionCounts),
                jacksonTypeRef<BetaAnalyticsToolActionCounts>(),
            )

        assertThat(roundtrippedBetaAnalyticsToolActionCounts)
            .isEqualTo(betaAnalyticsToolActionCounts)
    }
}
