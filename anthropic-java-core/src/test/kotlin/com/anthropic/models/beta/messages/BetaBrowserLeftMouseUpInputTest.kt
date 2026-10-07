package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftMouseUpInputTest {

    @Test
    fun create() {
        val betaBrowserLeftMouseUpInput =
            BetaBrowserLeftMouseUpInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserLeftMouseUpInput.target())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserLeftMouseUpInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftMouseUpInput =
            BetaBrowserLeftMouseUpInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserLeftMouseUpInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftMouseUpInput),
                jacksonTypeRef<BetaBrowserLeftMouseUpInput>(),
            )

        assertThat(roundtrippedBetaBrowserLeftMouseUpInput).isEqualTo(betaBrowserLeftMouseUpInput)
    }
}
