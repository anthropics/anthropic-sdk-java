package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserRightClickInputTest {

    @Test
    fun create() {
        val betaBrowserRightClickInput =
            BetaBrowserRightClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserRightClickInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserRightClickInput.modifiers()).contains("modifiers")
        assertThat(betaBrowserRightClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserRightClickInput =
            BetaBrowserRightClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserRightClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserRightClickInput),
                jacksonTypeRef<BetaBrowserRightClickInput>(),
            )

        assertThat(roundtrippedBetaBrowserRightClickInput).isEqualTo(betaBrowserRightClickInput)
    }
}
