package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickDragInputTest {

    @Test
    fun create() {
        val betaComputerLeftClickDragInput =
            BetaComputerLeftClickDragInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .addStartCoordinate(0L)
                .addStartCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerLeftClickDragInput.coordinate()).containsExactly(0L, 0L)
        assertThat(betaComputerLeftClickDragInput.startCoordinate()).containsExactly(0L, 0L)
        assertThat(betaComputerLeftClickDragInput.text()).contains("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickDragInput =
            BetaComputerLeftClickDragInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .addStartCoordinate(0L)
                .addStartCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerLeftClickDragInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickDragInput),
                jacksonTypeRef<BetaComputerLeftClickDragInput>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickDragInput)
            .isEqualTo(betaComputerLeftClickDragInput)
    }
}
