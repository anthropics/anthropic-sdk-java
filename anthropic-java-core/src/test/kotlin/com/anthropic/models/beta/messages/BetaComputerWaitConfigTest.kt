// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerWaitConfigTest {

    @Test
    fun create() {
        val betaComputerWaitConfig =
            BetaComputerWaitConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerWaitConfig.deferLoading()).contains(true)
        assertThat(betaComputerWaitConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerWaitConfig =
            BetaComputerWaitConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerWaitConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerWaitConfig),
                jacksonTypeRef<BetaComputerWaitConfig>(),
            )

        assertThat(roundtrippedBetaComputerWaitConfig).isEqualTo(betaComputerWaitConfig)
    }
}
