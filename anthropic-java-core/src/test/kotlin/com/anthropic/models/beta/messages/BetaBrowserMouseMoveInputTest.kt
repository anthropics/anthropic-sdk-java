package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserMouseMoveInputTest {

    @Test
    fun create() {
        val betaBrowserMouseMoveInput =
            BetaBrowserMouseMoveInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserMouseMoveInput.target())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserMouseMoveInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserMouseMoveInput =
            BetaBrowserMouseMoveInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserMouseMoveInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserMouseMoveInput),
                jacksonTypeRef<BetaBrowserMouseMoveInput>(),
            )

        assertThat(roundtrippedBetaBrowserMouseMoveInput).isEqualTo(betaBrowserMouseMoveInput)
    }
}
