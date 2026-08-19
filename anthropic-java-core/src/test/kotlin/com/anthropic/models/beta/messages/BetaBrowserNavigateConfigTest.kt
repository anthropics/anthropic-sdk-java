// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserNavigateConfigTest {

    @Test
    fun create() {
        val betaBrowserNavigateConfig =
            BetaBrowserNavigateConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserNavigateConfig.deferLoading()).contains(true)
        assertThat(betaBrowserNavigateConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserNavigateConfig =
            BetaBrowserNavigateConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserNavigateConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserNavigateConfig),
                jacksonTypeRef<BetaBrowserNavigateConfig>(),
            )

        assertThat(roundtrippedBetaBrowserNavigateConfig).isEqualTo(betaBrowserNavigateConfig)
    }
}
