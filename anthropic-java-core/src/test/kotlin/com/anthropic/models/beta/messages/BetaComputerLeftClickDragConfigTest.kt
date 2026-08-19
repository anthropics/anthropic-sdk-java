// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickDragConfigTest {

    @Test
    fun create() {
        val betaComputerLeftClickDragConfig =
            BetaComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaComputerLeftClickDragConfig.deferLoading()).contains(true)
        assertThat(betaComputerLeftClickDragConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickDragConfig =
            BetaComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaComputerLeftClickDragConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickDragConfig),
                jacksonTypeRef<BetaComputerLeftClickDragConfig>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickDragConfig)
            .isEqualTo(betaComputerLeftClickDragConfig)
    }
}
