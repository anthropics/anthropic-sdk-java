// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserZoomConfigTest {

    @Test
    fun create() {
        val betaBrowserZoomConfig =
            BetaBrowserZoomConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserZoomConfig.deferLoading()).contains(true)
        assertThat(betaBrowserZoomConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserZoomConfig =
            BetaBrowserZoomConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserZoomConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserZoomConfig),
                jacksonTypeRef<BetaBrowserZoomConfig>(),
            )

        assertThat(roundtrippedBetaBrowserZoomConfig).isEqualTo(betaBrowserZoomConfig)
    }
}
