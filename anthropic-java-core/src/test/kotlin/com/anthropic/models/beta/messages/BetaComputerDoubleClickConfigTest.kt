// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerDoubleClickConfigTest {

    @Test
    fun create() {
        val betaComputerDoubleClickConfig =
            BetaComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerDoubleClickConfig.deferLoading()).contains(true)
        assertThat(betaComputerDoubleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerDoubleClickConfig =
            BetaComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerDoubleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerDoubleClickConfig),
                jacksonTypeRef<BetaComputerDoubleClickConfig>(),
            )

        assertThat(roundtrippedBetaComputerDoubleClickConfig)
            .isEqualTo(betaComputerDoubleClickConfig)
    }
}
