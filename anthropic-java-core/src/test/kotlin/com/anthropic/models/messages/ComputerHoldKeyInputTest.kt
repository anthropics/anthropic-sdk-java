package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerHoldKeyInputTest {

    @Test
    fun create() {
        val computerHoldKeyInput =
            ComputerHoldKeyInput.builder().duration(300L).text("text").build()

        assertThat(computerHoldKeyInput.duration()).isEqualTo(300L)
        assertThat(computerHoldKeyInput.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerHoldKeyInput =
            ComputerHoldKeyInput.builder().duration(300L).text("text").build()

        val roundtrippedComputerHoldKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerHoldKeyInput),
                jacksonTypeRef<ComputerHoldKeyInput>(),
            )

        assertThat(roundtrippedComputerHoldKeyInput).isEqualTo(computerHoldKeyInput)
    }
}
