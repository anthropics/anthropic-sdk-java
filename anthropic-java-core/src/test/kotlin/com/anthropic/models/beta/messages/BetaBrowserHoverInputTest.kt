package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoverInputTest {

    @Test
    fun create() {
        val betaBrowserHoverInput =
            BetaBrowserHoverInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserHoverInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserHoverInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoverInput =
            BetaBrowserHoverInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserHoverInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoverInput),
                jacksonTypeRef<BetaBrowserHoverInput>(),
            )

        assertThat(roundtrippedBetaBrowserHoverInput).isEqualTo(betaBrowserHoverInput)
    }
}
