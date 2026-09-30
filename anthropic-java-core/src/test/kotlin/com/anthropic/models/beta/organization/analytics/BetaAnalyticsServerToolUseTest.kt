package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsServerToolUseTest {

    @Test
    fun create() {
        val betaAnalyticsServerToolUse = BetaAnalyticsServerToolUse.of(10L)

        assertThat(betaAnalyticsServerToolUse.webSearchRequests()).isEqualTo(10L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsServerToolUse = BetaAnalyticsServerToolUse.of(10L)

        val roundtrippedBetaAnalyticsServerToolUse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsServerToolUse),
                jacksonTypeRef<BetaAnalyticsServerToolUse>(),
            )

        assertThat(roundtrippedBetaAnalyticsServerToolUse).isEqualTo(betaAnalyticsServerToolUse)
    }
}
