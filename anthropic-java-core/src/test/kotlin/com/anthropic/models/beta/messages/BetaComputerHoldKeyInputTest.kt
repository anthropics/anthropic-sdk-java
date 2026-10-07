package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerHoldKeyInputTest {

    @Test
    fun create() {
        val betaComputerHoldKeyInput =
            BetaComputerHoldKeyInput.builder().duration(300L).text("text").build()

        assertThat(betaComputerHoldKeyInput.duration()).isEqualTo(300L)
        assertThat(betaComputerHoldKeyInput.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerHoldKeyInput =
            BetaComputerHoldKeyInput.builder().duration(300L).text("text").build()

        val roundtrippedBetaComputerHoldKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerHoldKeyInput),
                jacksonTypeRef<BetaComputerHoldKeyInput>(),
            )

        assertThat(roundtrippedBetaComputerHoldKeyInput).isEqualTo(betaComputerHoldKeyInput)
    }
}
