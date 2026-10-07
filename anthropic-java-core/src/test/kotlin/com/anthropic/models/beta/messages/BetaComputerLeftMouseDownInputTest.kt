package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftMouseDownInputTest {

    @Test
    fun create() {
        val betaComputerLeftMouseDownInput = BetaComputerLeftMouseDownInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftMouseDownInput = BetaComputerLeftMouseDownInput.builder().build()

        val roundtrippedBetaComputerLeftMouseDownInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftMouseDownInput),
                jacksonTypeRef<BetaComputerLeftMouseDownInput>(),
            )

        assertThat(roundtrippedBetaComputerLeftMouseDownInput)
            .isEqualTo(betaComputerLeftMouseDownInput)
    }
}
