package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaComputerScrollInputTest {

    @Test
    fun create() {
        val betaComputerScrollInput =
            BetaComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(BetaComputerScrollDirection.UP)
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(betaComputerScrollInput.scrollAmount()).isEqualTo(0L)
        assertThat(betaComputerScrollInput.scrollDirection())
            .isEqualTo(BetaComputerScrollDirection.UP)
        assertThat(betaComputerScrollInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(betaComputerScrollInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaComputerScrollInput =
            BetaComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(BetaComputerScrollDirection.UP)
                .build()

        val betaComputerScrollInput =
            baseBetaComputerScrollInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(betaComputerScrollInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaComputerScrollInput =
            BetaComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(BetaComputerScrollDirection.UP)
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedBetaComputerScrollInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComputerScrollInput),
                jacksonTypeRef<BetaComputerScrollInput>(),
            )

        assertThat(roundtrippedBetaComputerScrollInput).isEqualTo(betaComputerScrollInput)
    }
}
