package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerScrollInputTest {

    @Test
    fun create() {
        val computerScrollInput =
            ComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(ComputerScrollDirection.UP)
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        assertThat(computerScrollInput.scrollAmount()).isEqualTo(0L)
        assertThat(computerScrollInput.scrollDirection()).isEqualTo(ComputerScrollDirection.UP)
        assertThat(computerScrollInput.coordinate().getOrNull()).containsExactly(0L, 0L)
        assertThat(computerScrollInput.text()).contains("text")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseComputerScrollInput =
            ComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(ComputerScrollDirection.UP)
                .build()

        val computerScrollInput =
            baseComputerScrollInput.toBuilder().addCoordinate(0L).addCoordinate(0L).build()

        assertThat(computerScrollInput.coordinate().getOrNull()).containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerScrollInput =
            ComputerScrollInput.builder()
                .scrollAmount(0L)
                .scrollDirection(ComputerScrollDirection.UP)
                .addCoordinate(0L)
                .addCoordinate(0L)
                .text("text")
                .build()

        val roundtrippedComputerScrollInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerScrollInput),
                jacksonTypeRef<ComputerScrollInput>(),
            )

        assertThat(roundtrippedComputerScrollInput).isEqualTo(computerScrollInput)
    }
}
