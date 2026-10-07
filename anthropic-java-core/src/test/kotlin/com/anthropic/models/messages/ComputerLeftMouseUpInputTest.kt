package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseUpInputTest {

    @Test
    fun create() {
        val computerLeftMouseUpInput = ComputerLeftMouseUpInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseUpInput = ComputerLeftMouseUpInput.builder().build()

        val roundtrippedComputerLeftMouseUpInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseUpInput),
                jacksonTypeRef<ComputerLeftMouseUpInput>(),
            )

        assertThat(roundtrippedComputerLeftMouseUpInput).isEqualTo(computerLeftMouseUpInput)
    }
}
