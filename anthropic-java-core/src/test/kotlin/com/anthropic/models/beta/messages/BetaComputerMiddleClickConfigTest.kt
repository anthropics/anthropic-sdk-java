// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerMiddleClickConfigTest {

    @Test
    fun create() {
        val betaComputerMiddleClickConfig =
            BetaComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerMiddleClickConfig.deferLoading()).contains(true)
        assertThat(betaComputerMiddleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerMiddleClickConfig =
            BetaComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerMiddleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerMiddleClickConfig),
                jacksonTypeRef<BetaComputerMiddleClickConfig>(),
            )

        assertThat(roundtrippedBetaComputerMiddleClickConfig)
            .isEqualTo(betaComputerMiddleClickConfig)
    }
}
