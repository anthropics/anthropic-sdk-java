package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerDoubleClickInputTest {

    @Test
    fun create() {
        val betaComputerDoubleClickInput =
            BetaComputerDoubleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerDoubleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerDoubleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerDoubleClickInput = BetaComputerDoubleClickInput.builder().build()

        val betaComputerDoubleClickInput =
            baseBetaComputerDoubleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerDoubleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerDoubleClickInput =
            BetaComputerDoubleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerDoubleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerDoubleClickInput),
                jacksonTypeRef<BetaComputerDoubleClickInput>(),
            )

        assertThat(roundtrippedBetaComputerDoubleClickInput).isEqualTo(betaComputerDoubleClickInput)
    }
}
