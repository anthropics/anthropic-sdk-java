package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickInputTest {

    @Test
    fun create() {
        val computerLeftClickInput =
            ComputerLeftClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerLeftClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerLeftClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerLeftClickInput = ComputerLeftClickInput.builder().build()

        val computerLeftClickInput =
            baseComputerLeftClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerLeftClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickInput =
            ComputerLeftClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerLeftClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickInput),
                jacksonTypeRef<ComputerLeftClickInput>(),
            )

        assertThat(roundtrippedComputerLeftClickInput).isEqualTo(computerLeftClickInput)
    }
}
