// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseDownConfigTest {

    @Test
    fun create() {
        val betaComputerLeftMouseDownConfig =
            BetaComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerLeftMouseDownConfig.deferLoading()).contains(true)
        assertThat(betaComputerLeftMouseDownConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseDownConfig =
            BetaComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerLeftMouseDownConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseDownConfig),
                jacksonTypeRef<BetaComputerLeftMouseDownConfig>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseDownConfig)
            .isEqualTo(betaComputerLeftMouseDownConfig)
    }
}
