package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseDownInputTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseDownInput =
            BetaBrowserLeftMouseDownInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserLeftMouseDownInput.target())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserLeftMouseDownInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseDownInput =
            BetaBrowserLeftMouseDownInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserLeftMouseDownInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseDownInput),
                jacksonTypeRef<BetaBrowserLeftMouseDownInput>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseDownInput)
            .isEqualTo(betaBrowserLeftMouseDownInput)
    }
}
