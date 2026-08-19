// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserGetPageTextConfigTest {

    @Test
    fun create() {
        val betaBrowserGetPageTextConfig =
            BetaBrowserGetPageTextConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserGetPageTextConfig.deferLoading()).contains(true)
        assertThat(betaBrowserGetPageTextConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserGetPageTextConfig =
            BetaBrowserGetPageTextConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserGetPageTextConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserGetPageTextConfig),
                jacksonTypeRef<BetaBrowserGetPageTextConfig>(),
            )

        assertThat(roundtrippedBetaBrowserGetPageTextConfig).isEqualTo(betaBrowserGetPageTextConfig)
    }
}
