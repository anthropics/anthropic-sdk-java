// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollToConfigTest {

    @Test
    fun create() {
        val betaBrowserScrollToConfig =
            BetaBrowserScrollToConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserScrollToConfig.deferLoading()).contains(true)
        assertThat(betaBrowserScrollToConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollToConfig =
            BetaBrowserScrollToConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserScrollToConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollToConfig),
                jacksonTypeRef<BetaBrowserScrollToConfig>(),
            )

        assertThat(roundtrippedBetaBrowserScrollToConfig).isEqualTo(betaBrowserScrollToConfig)
    }
}
