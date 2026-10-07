package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerTripleClickInputTest {

    @Test
    fun create() {
        val betaComputerTripleClickInput =
            BetaComputerTripleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerTripleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerTripleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerTripleClickInput = BetaComputerTripleClickInput.builder().build()

        val betaComputerTripleClickInput =
            baseBetaComputerTripleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerTripleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerTripleClickInput =
            BetaComputerTripleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerTripleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerTripleClickInput),
                jacksonTypeRef<BetaComputerTripleClickInput>(),
            )

        assertThat(roundtrippedBetaComputerTripleClickInput).isEqualTo(betaComputerTripleClickInput)
    }
}
