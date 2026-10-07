package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerRightClickInputTest {

    @Test
    fun create() {
        val betaComputerRightClickInput =
            BetaComputerRightClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerRightClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerRightClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerRightClickInput = BetaComputerRightClickInput.builder().build()

        val betaComputerRightClickInput =
            baseBetaComputerRightClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerRightClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerRightClickInput =
            BetaComputerRightClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerRightClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerRightClickInput),
                jacksonTypeRef<BetaComputerRightClickInput>(),
            )

        assertThat(roundtrippedBetaComputerRightClickInput).isEqualTo(betaComputerRightClickInput)
    }
}
