package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsLinesOfCodeTest {

    @Test
    fun create() {
        val betaAnalyticsLinesOfCode =
            BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()

        assertThat(betaAnalyticsLinesOfCode.addedCount()).isEqualTo(0L)
        assertThat(betaAnalyticsLinesOfCode.removedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsLinesOfCode =
            BetaAnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()

        val roundtrippedBetaAnalyticsLinesOfCode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsLinesOfCode),
                jacksonTypeRef<BetaAnalyticsLinesOfCode>(),
            )

        assertThat(roundtrippedBetaAnalyticsLinesOfCode).isEqualTo(betaAnalyticsLinesOfCode)
    }
}
