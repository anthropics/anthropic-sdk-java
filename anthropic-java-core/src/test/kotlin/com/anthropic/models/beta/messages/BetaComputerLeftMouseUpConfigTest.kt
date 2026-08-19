// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseUpConfigTest {

    @Test
    fun create() {
        val betaComputerLeftMouseUpConfig =
            BetaComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerLeftMouseUpConfig.deferLoading()).contains(true)
        assertThat(betaComputerLeftMouseUpConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseUpConfig =
            BetaComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerLeftMouseUpConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseUpConfig),
                jacksonTypeRef<BetaComputerLeftMouseUpConfig>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseUpConfig)
            .isEqualTo(betaComputerLeftMouseUpConfig)
    }
}
