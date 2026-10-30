package com.anthropic.models.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AnalyticsLinesOfCodeTest {

    @Test
    fun create() {
        val analyticsLinesOfCode =
            AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()

        assertThat(analyticsLinesOfCode.addedCount()).isEqualTo(0L)
        assertThat(analyticsLinesOfCode.removedCount()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val analyticsLinesOfCode =
            AnalyticsLinesOfCode.builder().addedCount(0L).removedCount(0L).build()

        val roundtrippedAnalyticsLinesOfCode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(analyticsLinesOfCode),
                jacksonTypeRef<AnalyticsLinesOfCode>(),
            )

        assertThat(roundtrippedAnalyticsLinesOfCode).isEqualTo(analyticsLinesOfCode)
    }
}
