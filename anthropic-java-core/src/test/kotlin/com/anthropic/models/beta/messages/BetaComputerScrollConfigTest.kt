// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScrollConfigTest {

    @Test
    fun create() {
        val betaComputerScrollConfig =
            BetaComputerScrollConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerScrollConfig.deferLoading()).contains(true)
        assertThat(betaComputerScrollConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScrollConfig =
            BetaComputerScrollConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerScrollConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScrollConfig),
                jacksonTypeRef<BetaComputerScrollConfig>(),
            )

        assertThat(roundtrippedBetaComputerScrollConfig).isEqualTo(betaComputerScrollConfig)
    }
}
