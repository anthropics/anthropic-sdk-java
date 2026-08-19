// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadNetworkConfigTest {

    @Test
    fun create() {
        val betaBrowserReadNetworkConfig =
            BetaBrowserReadNetworkConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserReadNetworkConfig.deferLoading()).contains(true)
        assertThat(betaBrowserReadNetworkConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadNetworkConfig =
            BetaBrowserReadNetworkConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserReadNetworkConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadNetworkConfig),
                jacksonTypeRef<BetaBrowserReadNetworkConfig>(),
            )

        assertThat(roundtrippedBetaBrowserReadNetworkConfig).isEqualTo(betaBrowserReadNetworkConfig)
    }
}
