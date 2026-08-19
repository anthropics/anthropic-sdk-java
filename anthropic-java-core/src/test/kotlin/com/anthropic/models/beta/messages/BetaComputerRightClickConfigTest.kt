// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerRightClickConfigTest {

    @Test
    fun create() {
        val betaComputerRightClickConfig =
            BetaComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerRightClickConfig.deferLoading()).contains(true)
        assertThat(betaComputerRightClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerRightClickConfig =
            BetaComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerRightClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerRightClickConfig),
                jacksonTypeRef<BetaComputerRightClickConfig>(),
            )

        assertThat(roundtrippedBetaComputerRightClickConfig).isEqualTo(betaComputerRightClickConfig)
    }
}
