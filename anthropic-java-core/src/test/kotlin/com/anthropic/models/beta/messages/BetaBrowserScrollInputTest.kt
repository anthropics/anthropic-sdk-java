package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollInputTest {

    @Test
    fun create() {
        val betaBrowserScrollInput =
            BetaBrowserScrollInput.builder()
                .scrollDirection(BetaBrowserScrollDirection.UP)
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .scrollAmount(1L)
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserScrollInput.scrollDirection())
            .isEqualTo(BetaBrowserScrollDirection.UP)
        assertThat(betaBrowserScrollInput.target())
            .isEqualTo(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(betaBrowserScrollInput.scrollAmount()).contains(1L)
        assertThat(betaBrowserScrollInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollInput =
            BetaBrowserScrollInput.builder()
                .scrollDirection(BetaBrowserScrollDirection.UP)
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .scrollAmount(1L)
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserScrollInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollInput),
                jacksonTypeRef<BetaBrowserScrollInput>(),
            )

        assertThat(roundtrippedBetaBrowserScrollInput).isEqualTo(betaBrowserScrollInput)
    }
}
