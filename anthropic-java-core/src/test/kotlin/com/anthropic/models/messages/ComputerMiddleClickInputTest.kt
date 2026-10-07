package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMiddleClickInputTest {

    @Test
    fun create() {
        val computerMiddleClickInput =
            ComputerMiddleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerMiddleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerMiddleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerMiddleClickInput = ComputerMiddleClickInput.builder().build()

        val computerMiddleClickInput =
            baseComputerMiddleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerMiddleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMiddleClickInput =
            ComputerMiddleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerMiddleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMiddleClickInput),
                jacksonTypeRef<ComputerMiddleClickInput>(),
            )

        assertThat(roundtrippedComputerMiddleClickInput).isEqualTo(computerMiddleClickInput)
    }
}
