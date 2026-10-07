package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerLeftClickInputTest {

    @Test
    fun create() {
        val betaComputerLeftClickInput =
            BetaComputerLeftClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerLeftClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerLeftClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerLeftClickInput = BetaComputerLeftClickInput.builder().build()

        val betaComputerLeftClickInput =
            baseBetaComputerLeftClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerLeftClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerLeftClickInput =
            BetaComputerLeftClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerLeftClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerLeftClickInput),
                jacksonTypeRef<BetaComputerLeftClickInput>(),
            )

        assertThat(roundtrippedBetaComputerLeftClickInput).isEqualTo(betaComputerLeftClickInput)
    }
}
