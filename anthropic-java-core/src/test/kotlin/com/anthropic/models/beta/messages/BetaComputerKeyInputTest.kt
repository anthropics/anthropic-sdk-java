package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerKeyInputTest {

    @Test
    fun create() {
        val betaComputerKeyInput = BetaComputerKeyInput.builder().text("text").repeat(1L).build()

        assertThat(betaComputerKeyInput.text()).isEqualTo("text")
        assertThat(betaComputerKeyInput.repeat()).contains(1L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerKeyInput = BetaComputerKeyInput.builder().text("text").repeat(1L).build()

        val roundtrippedBetaComputerKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerKeyInput),
                jacksonTypeRef<BetaComputerKeyInput>(),
            )

        assertThat(roundtrippedBetaComputerKeyInput).isEqualTo(betaComputerKeyInput)
    }
}
