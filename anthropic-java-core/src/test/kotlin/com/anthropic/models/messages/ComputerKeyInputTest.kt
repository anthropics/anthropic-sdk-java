package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerKeyInputTest {

    @Test
    fun create() {
        val computerKeyInput = ComputerKeyInput.builder().text("text").repeat(1L).build()

        assertThat(computerKeyInput.text()).isEqualTo("text")
        assertThat(computerKeyInput.repeat()).contains(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerKeyInput = ComputerKeyInput.builder().text("text").repeat(1L).build()

        val roundtrippedComputerKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerKeyInput),
                jacksonTypeRef<ComputerKeyInput>(),
            )

        assertThat(roundtrippedComputerKeyInput).isEqualTo(computerKeyInput)
    }
}
