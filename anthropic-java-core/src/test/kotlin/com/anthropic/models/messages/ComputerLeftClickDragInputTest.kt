package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickDragInputTest {

    @Test
    fun create() {
        val computerLeftClickDragInput =
            ComputerLeftClickDragInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .addStartCoordinate(0L)
                .addStartCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerLeftClickDragInput.coordinate()).containsExactly(0L, 0L)
        assertThat(computerLeftClickDragInput.startCoordinate()).containsExactly(0L, 0L)
        assertThat(computerLeftClickDragInput.text()).contains("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickDragInput =
            ComputerLeftClickDragInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .addStartCoordinate(0L)
                .addStartCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerLeftClickDragInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickDragInput),
                jacksonTypeRef<ComputerLeftClickDragInput>(),
            )

        assertThat(roundtrippedComputerLeftClickDragInput).isEqualTo(computerLeftClickDragInput)
    }
}
