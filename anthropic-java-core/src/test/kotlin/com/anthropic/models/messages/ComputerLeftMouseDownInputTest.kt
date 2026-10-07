package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseDownInputTest {

    @Test
    fun create() {
        val computerLeftMouseDownInput = ComputerLeftMouseDownInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseDownInput = ComputerLeftMouseDownInput.builder().build()

        val roundtrippedComputerLeftMouseDownInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseDownInput),
                jacksonTypeRef<ComputerLeftMouseDownInput>(),
            )

        assertThat(roundtrippedComputerLeftMouseDownInput).isEqualTo(computerLeftMouseDownInput)
    }
}
