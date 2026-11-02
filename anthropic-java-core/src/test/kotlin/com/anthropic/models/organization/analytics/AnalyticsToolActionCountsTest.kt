package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsToolActionCountsTest {

    @Test
    fun create() {
        val analyticsToolActionCounts =
            AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()

        assertThat(analyticsToolActionCounts.acceptedCount()).isEqualTo(0L)
        assertThat(analyticsToolActionCounts.rejectedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsToolActionCounts =
            AnalyticsToolActionCounts.builder().acceptedCount(0L).rejectedCount(0L).build()

        val roundtrippedAnalyticsToolActionCounts =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsToolActionCounts),
                jacksonTypeRef<AnalyticsToolActionCounts>(),
            )

        assertThat(roundtrippedAnalyticsToolActionCounts).isEqualTo(analyticsToolActionCounts)
    }
}
