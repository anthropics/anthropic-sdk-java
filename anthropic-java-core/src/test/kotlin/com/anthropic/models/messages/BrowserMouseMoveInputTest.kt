package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMouseMoveInputTest {

    @Test
    fun create() {
        val browserMouseMoveInput =
            BrowserMouseMoveInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(browserMouseMoveInput.target())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserMouseMoveInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMouseMoveInput =
            BrowserMouseMoveInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserMouseMoveInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMouseMoveInput),
                jacksonTypeRef<BrowserMouseMoveInput>(),
            )

        assertThat(roundtrippedBrowserMouseMoveInput).isEqualTo(browserMouseMoveInput)
    }
}
