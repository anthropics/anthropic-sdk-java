// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickConfigTest {

    @Test
    fun create() {
        val betaComputerLeftClickConfig =
            BetaComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerLeftClickConfig.deferLoading()).contains(true)
        assertThat(betaComputerLeftClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickConfig =
            BetaComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerLeftClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickConfig),
                jacksonTypeRef<BetaComputerLeftClickConfig>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickConfig).isEqualTo(betaComputerLeftClickConfig)
    }
}
