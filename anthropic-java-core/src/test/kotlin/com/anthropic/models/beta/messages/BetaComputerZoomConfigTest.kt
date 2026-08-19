// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerZoomConfigTest {

    @Test
    fun create() {
        val betaComputerZoomConfig =
            BetaComputerZoomConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerZoomConfig.deferLoading()).contains(true)
        assertThat(betaComputerZoomConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerZoomConfig =
            BetaComputerZoomConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerZoomConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerZoomConfig),
                jacksonTypeRef<BetaComputerZoomConfig>(),
            )

        assertThat(roundtrippedBetaComputerZoomConfig).isEqualTo(betaComputerZoomConfig)
    }
}
