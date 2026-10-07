package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerDoubleClickInputTest {

    @Test
    fun create() {
        val computerDoubleClickInput =
            ComputerDoubleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerDoubleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerDoubleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerDoubleClickInput = ComputerDoubleClickInput.builder().build()

        val computerDoubleClickInput =
            baseComputerDoubleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerDoubleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerDoubleClickInput =
            ComputerDoubleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerDoubleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerDoubleClickInput),
                jacksonTypeRef<ComputerDoubleClickInput>(),
            )

        assertThat(roundtrippedComputerDoubleClickInput).isEqualTo(computerDoubleClickInput)
    }
}
