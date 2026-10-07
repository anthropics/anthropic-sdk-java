package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseUpInputTest {

    @Test
    fun create() {
        val betaComputerLeftMouseUpInput = BetaComputerLeftMouseUpInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseUpInput = BetaComputerLeftMouseUpInput.builder().build()

        val roundtrippedBetaComputerLeftMouseUpInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseUpInput),
                jacksonTypeRef<BetaComputerLeftMouseUpInput>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseUpInput).isEqualTo(betaComputerLeftMouseUpInput)
    }
}
