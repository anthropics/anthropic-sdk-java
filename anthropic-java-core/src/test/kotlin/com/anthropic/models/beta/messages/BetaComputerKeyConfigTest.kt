// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerKeyConfigTest {

    @Test
    fun create() {
        val betaComputerKeyConfig =
            BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerKeyConfig.deferLoading()).contains(true)
        assertThat(betaComputerKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerKeyConfig =
            BetaComputerKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerKeyConfig),
                jacksonTypeRef<BetaComputerKeyConfig>(),
            )

        assertThat(roundtrippedBetaComputerKeyConfig).isEqualTo(betaComputerKeyConfig)
    }
}
