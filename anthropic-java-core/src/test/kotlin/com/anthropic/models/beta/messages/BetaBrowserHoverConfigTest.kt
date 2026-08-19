// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoverConfigTest {

    @Test
    fun create() {
        val betaBrowserHoverConfig =
            BetaBrowserHoverConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserHoverConfig.deferLoading()).contains(true)
        assertThat(betaBrowserHoverConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoverConfig =
            BetaBrowserHoverConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserHoverConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoverConfig),
                jacksonTypeRef<BetaBrowserHoverConfig>(),
            )

        assertThat(roundtrippedBetaBrowserHoverConfig).isEqualTo(betaBrowserHoverConfig)
    }
}
