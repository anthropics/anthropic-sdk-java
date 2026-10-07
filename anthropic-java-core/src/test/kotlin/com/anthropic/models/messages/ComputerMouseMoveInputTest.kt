package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMouseMoveInputTest {

    @Test
    fun create() {
        val computerMouseMoveInput =
            ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerMouseMoveInput.coordinate()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMouseMoveInput =
            ComputerMouseMoveInput.builder().addCoordinate(0L).addCoordinate(0L).build()

        val roundtrippedComputerMouseMoveInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMouseMoveInput),
                jacksonTypeRef<ComputerMouseMoveInput>(),
            )

        assertThat(roundtrippedComputerMouseMoveInput).isEqualTo(computerMouseMoveInput)
    }
}
