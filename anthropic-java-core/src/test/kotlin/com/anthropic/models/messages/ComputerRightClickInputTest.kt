package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerRightClickInputTest {

    @Test
    fun create() {
        val computerRightClickInput =
            ComputerRightClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerRightClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerRightClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerRightClickInput = ComputerRightClickInput.builder().build()

        val computerRightClickInput =
            baseComputerRightClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerRightClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerRightClickInput =
            ComputerRightClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerRightClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerRightClickInput),
                jacksonTypeRef<ComputerRightClickInput>(),
            )

        assertThat(roundtrippedComputerRightClickInput).isEqualTo(computerRightClickInput)
    }
}
