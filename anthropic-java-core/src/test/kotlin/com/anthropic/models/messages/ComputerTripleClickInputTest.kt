package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTripleClickInputTest {

    @Test
    fun create() {
        val computerTripleClickInput =
            ComputerTripleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerTripleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerTripleClickInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerTripleClickInput = ComputerTripleClickInput.builder().build()

        val computerTripleClickInput =
            baseComputerTripleClickInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerTripleClickInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTripleClickInput =
            ComputerTripleClickInput.builder()
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerTripleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTripleClickInput),
                jacksonTypeRef<ComputerTripleClickInput>(),
            )

        assertThat(roundtrippedComputerTripleClickInput).isEqualTo(computerTripleClickInput)
    }
}
