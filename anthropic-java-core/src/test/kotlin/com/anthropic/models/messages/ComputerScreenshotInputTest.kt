package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerScreenshotInputTest {

    @Test
    fun create() {
        val computerScreenshotInput = ComputerScreenshotInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerScreenshotInput = ComputerScreenshotInput.builder().build()

        val roundtrippedComputerScreenshotInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerScreenshotInput),
                jacksonTypeRef<ComputerScreenshotInput>(),
            )

        assertThat(roundtrippedComputerScreenshotInput).isEqualTo(computerScreenshotInput)
    }
}
