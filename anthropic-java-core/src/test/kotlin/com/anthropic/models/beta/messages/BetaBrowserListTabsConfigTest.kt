// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserListTabsConfigTest {

    @Test
    fun create() {
        val betaBrowserListTabsConfig =
            BetaBrowserListTabsConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserListTabsConfig.deferLoading()).contains(true)
        assertThat(betaBrowserListTabsConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserListTabsConfig =
            BetaBrowserListTabsConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserListTabsConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserListTabsConfig),
                jacksonTypeRef<BetaBrowserListTabsConfig>(),
            )

        assertThat(roundtrippedBetaBrowserListTabsConfig).isEqualTo(betaBrowserListTabsConfig)
    }
}
