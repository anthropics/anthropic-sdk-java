package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerTypeInputTest {

    @Test
    fun create() {
        val betaComputerTypeInput = BetaComputerTypeInput.of("text")

        assertThat(betaComputerTypeInput.text()).isEqualTo("text")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerTypeInput = BetaComputerTypeInput.of("text")

        val roundtrippedBetaComputerTypeInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerTypeInput),
                jacksonTypeRef<BetaComputerTypeInput>(),
            )

        assertThat(roundtrippedBetaComputerTypeInput).isEqualTo(betaComputerTypeInput)
    }
}
