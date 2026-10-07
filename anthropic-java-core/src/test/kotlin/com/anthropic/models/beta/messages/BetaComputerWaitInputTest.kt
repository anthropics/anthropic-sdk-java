package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerWaitInputTest {

    @Test
    fun create() {
        val betaComputerWaitInput = BetaComputerWaitInput.of(300L)

        assertThat(betaComputerWaitInput.duration()).isEqualTo(300L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerWaitInput = BetaComputerWaitInput.of(300L)

        val roundtrippedBetaComputerWaitInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerWaitInput),
                jacksonTypeRef<BetaComputerWaitInput>(),
            )

        assertThat(roundtrippedBetaComputerWaitInput).isEqualTo(betaComputerWaitInput)
    }
}
