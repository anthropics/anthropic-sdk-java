package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTripleClickInputTest {

    @Test
    fun create() {
        val betaBrowserTripleClickInput =
            BetaBrowserTripleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserTripleClickInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserTripleClickInput.modifiers()).contains("modifiers")
        assertThat(betaBrowserTripleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTripleClickInput =
            BetaBrowserTripleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserTripleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTripleClickInput),
                jacksonTypeRef<BetaBrowserTripleClickInput>(),
            )

        assertThat(roundtrippedBetaBrowserTripleClickInput).isEqualTo(betaBrowserTripleClickInput)
    }
}
