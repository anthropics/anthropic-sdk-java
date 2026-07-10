// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamUsageTest {

    @Test
    fun create() {
        val betaDreamUsage =
            BetaDreamUsage.builder()
                .cacheCreationInputTokens(0)
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .build()

        assertThat(betaDreamUsage.cacheCreationInputTokens()).isEqualTo(0)
        assertThat(betaDreamUsage.cacheReadInputTokens()).isEqualTo(0)
        assertThat(betaDreamUsage.inputTokens()).isEqualTo(0)
        assertThat(betaDreamUsage.outputTokens()).isEqualTo(0)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamUsage =
            BetaDreamUsage.builder()
                .cacheCreationInputTokens(0)
                .cacheReadInputTokens(0)
                .inputTokens(0)
                .outputTokens(0)
                .build()

        val roundtrippedBetaDreamUsage =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamUsage),
                jacksonTypeRef<BetaDreamUsage>(),
            )

        assertThat(roundtrippedBetaDreamUsage).isEqualTo(betaDreamUsage)
    }
}
