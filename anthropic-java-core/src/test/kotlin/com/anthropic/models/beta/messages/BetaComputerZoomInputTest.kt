package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerZoomInputTest {

    @Test
    fun create() {
        val betaComputerZoomInput = BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L))

        assertThat(betaComputerZoomInput.region()).containsExactly(0L, 0L, 0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerZoomInput = BetaComputerZoomInput.of(listOf(0L, 0L, 0L, 0L))

        val roundtrippedBetaComputerZoomInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerZoomInput),
                jacksonTypeRef<BetaComputerZoomInput>(),
            )

        assertThat(roundtrippedBetaComputerZoomInput).isEqualTo(betaComputerZoomInput)
    }
}
