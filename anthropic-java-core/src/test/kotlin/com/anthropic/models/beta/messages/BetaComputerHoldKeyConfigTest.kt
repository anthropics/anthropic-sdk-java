// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerHoldKeyConfigTest {

    @Test
    fun create() {
        val betaComputerHoldKeyConfig =
            BetaComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerHoldKeyConfig.deferLoading()).contains(true)
        assertThat(betaComputerHoldKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerHoldKeyConfig =
            BetaComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerHoldKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerHoldKeyConfig),
                jacksonTypeRef<BetaComputerHoldKeyConfig>(),
            )

        assertThat(roundtrippedBetaComputerHoldKeyConfig).isEqualTo(betaComputerHoldKeyConfig)
    }
}
