// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerTripleClickConfigTest {

    @Test
    fun create() {
        val betaComputerTripleClickConfig =
            BetaComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerTripleClickConfig.deferLoading()).contains(true)
        assertThat(betaComputerTripleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerTripleClickConfig =
            BetaComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerTripleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerTripleClickConfig),
                jacksonTypeRef<BetaComputerTripleClickConfig>(),
            )

        assertThat(roundtrippedBetaComputerTripleClickConfig)
            .isEqualTo(betaComputerTripleClickConfig)
    }
}
