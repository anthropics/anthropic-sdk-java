// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerCursorPositionConfigTest {

    @Test
    fun create() {
        val betaComputerCursorPositionConfig =
            BetaComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerCursorPositionConfig.deferLoading()).contains(true)
        assertThat(betaComputerCursorPositionConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerCursorPositionConfig =
            BetaComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerCursorPositionConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerCursorPositionConfig),
                jacksonTypeRef<BetaComputerCursorPositionConfig>(),
            )

        assertThat(roundtrippedBetaComputerCursorPositionConfig)
            .isEqualTo(betaComputerCursorPositionConfig)
    }
}
