package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerCursorPositionInputTest {

    @Test
    fun create() {
        val betaComputerCursorPositionInput = BetaComputerCursorPositionInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerCursorPositionInput = BetaComputerCursorPositionInput.builder().build()

        val roundtrippedBetaComputerCursorPositionInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerCursorPositionInput),
                jacksonTypeRef<BetaComputerCursorPositionInput>(),
            )

        assertThat(roundtrippedBetaComputerCursorPositionInput)
            .isEqualTo(betaComputerCursorPositionInput)
    }
}
