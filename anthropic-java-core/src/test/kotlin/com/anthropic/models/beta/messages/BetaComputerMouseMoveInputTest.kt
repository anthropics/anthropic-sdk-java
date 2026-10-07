package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerMouseMoveInputTest {

    @Test
    fun create() {
        val betaComputerMouseMoveInput =
            BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerMouseMoveInput.coordinate()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerMouseMoveInput =
            BetaComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()

        val roundtrippedBetaComputerMouseMoveInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerMouseMoveInput),
                jacksonTypeRef<BetaComputerMouseMoveInput>(),
            )

        assertThat(roundtrippedBetaComputerMouseMoveInput).isEqualTo(betaComputerMouseMoveInput)
    }
}
