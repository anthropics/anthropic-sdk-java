// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserSwitchTabConfigTest {

    @Test
    fun create() {
        val betaBrowserSwitchTabConfig =
            BetaBrowserSwitchTabConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserSwitchTabConfig.deferLoading()).contains(true)
        assertThat(betaBrowserSwitchTabConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserSwitchTabConfig =
            BetaBrowserSwitchTabConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserSwitchTabConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserSwitchTabConfig),
                jacksonTypeRef<BetaBrowserSwitchTabConfig>(),
            )

        assertThat(roundtrippedBetaBrowserSwitchTabConfig).isEqualTo(betaBrowserSwitchTabConfig)
    }
}
