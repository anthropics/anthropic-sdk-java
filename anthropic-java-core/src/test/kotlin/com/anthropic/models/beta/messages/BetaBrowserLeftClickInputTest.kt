package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserLeftClickInputTest {

    @Test
    fun create() {
        val betaBrowserLeftClickInput =
            BetaBrowserLeftClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserLeftClickInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserLeftClickInput.modifiers()).contains("modifiers")
        assertThat(betaBrowserLeftClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserLeftClickInput =
            BetaBrowserLeftClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserLeftClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserLeftClickInput),
                jacksonTypeRef<BetaBrowserLeftClickInput>(),
            )

        assertThat(roundtrippedBetaBrowserLeftClickInput).isEqualTo(betaBrowserLeftClickInput)
    }
}
