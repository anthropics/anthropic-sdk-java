package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTypeInputTest {

    @Test
    fun create() {
        val computerTypeInput = ComputerTypeInput.of("text")

        assertThat(computerTypeInput.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTypeInput = ComputerTypeInput.of("text")

        val roundtrippedComputerTypeInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTypeInput),
                jacksonTypeRef<ComputerTypeInput>(),
            )

        assertThat(roundtrippedComputerTypeInput).isEqualTo(computerTypeInput)
    }
}
