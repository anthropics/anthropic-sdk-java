package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerWaitInputTest {

    @Test
    fun create() {
        val computerWaitInput = ComputerWaitInput.of(300L)

        assertThat(computerWaitInput.duration()).isEqualTo(300L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerWaitInput = ComputerWaitInput.of(300L)

        val roundtrippedComputerWaitInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerWaitInput),
                jacksonTypeRef<ComputerWaitInput>(),
            )

        assertThat(roundtrippedComputerWaitInput).isEqualTo(computerWaitInput)
    }
}
