package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScreenshotInputTest {

    @Test
    fun create() {
        val betaComputerScreenshotInput = BetaComputerScreenshotInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScreenshotInput = BetaComputerScreenshotInput.builder().build()

        val roundtrippedBetaComputerScreenshotInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScreenshotInput),
                jacksonTypeRef<BetaComputerScreenshotInput>(),
            )

        assertThat(roundtrippedBetaComputerScreenshotInput).isEqualTo(betaComputerScreenshotInput)
    }
}
