package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerMiddleClickInputTest {

    @Test
    fun create() {
        val betaComputerMiddleClickInput =
            BetaComputerMiddleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerMiddleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerMiddleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerMiddleClickInput = BetaComputerMiddleClickInput.builder().build()

        val betaComputerMiddleClickInput =
            baseBetaComputerMiddleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerMiddleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerMiddleClickInput =
            BetaComputerMiddleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerMiddleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerMiddleClickInput),
                jacksonTypeRef<BetaComputerMiddleClickInput>(),
            )

        assertThat(roundtrippedBetaComputerMiddleClickInput).isEqualTo(betaComputerMiddleClickInput)
    }
}
