package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserMiddleClickInputTest {

    @Test
    fun create() {
        val betaBrowserMiddleClickInput =
            BetaBrowserMiddleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserMiddleClickInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserMiddleClickInput.modifiers()).contains("modifiers")
        assertThat(betaBrowserMiddleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserMiddleClickInput =
            BetaBrowserMiddleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserMiddleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserMiddleClickInput),
                jacksonTypeRef<BetaBrowserMiddleClickInput>(),
            )

        assertThat(roundtrippedBetaBrowserMiddleClickInput).isEqualTo(betaBrowserMiddleClickInput)
    }
}
