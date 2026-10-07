package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickDragInputTest {

    @Test
    fun create() {
        val browserLeftClickDragInput =
            BrowserLeftClickDragInput.builder()
                .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(browserLeftClickDragInput.from())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserLeftClickDragInput.target())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserLeftClickDragInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickDragInput =
            BrowserLeftClickDragInput.builder()
                .from(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserLeftClickDragInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickDragInput),
                jacksonTypeRef<BrowserLeftClickDragInput>(),
            )

        assertThat(roundtrippedBrowserLeftClickDragInput).isEqualTo(browserLeftClickDragInput)
    }
}
