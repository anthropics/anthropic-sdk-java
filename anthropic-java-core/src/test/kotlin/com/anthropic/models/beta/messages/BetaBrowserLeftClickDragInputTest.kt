package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickDragInputTest {

    @Test
    fun create() {
        val betaBrowserLeftClickDragInput =
            BetaBrowserLeftClickDragInput.builder()
                .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserLeftClickDragInput.from())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserLeftClickDragInput.target())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserLeftClickDragInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickDragInput =
            BetaBrowserLeftClickDragInput.builder()
                .from(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserLeftClickDragInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickDragInput),
                jacksonTypeRef<BetaBrowserLeftClickDragInput>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickDragInput)
            .isEqualTo(betaBrowserLeftClickDragInput)
    }
}
