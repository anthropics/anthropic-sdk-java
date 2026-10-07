package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseDownInputTest {

    @Test
    fun create() {
        val browserLeftMouseDownInput =
            BrowserLeftMouseDownInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(browserLeftMouseDownInput.target())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserLeftMouseDownInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseDownInput =
            BrowserLeftMouseDownInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserLeftMouseDownInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseDownInput),
                jacksonTypeRef<BrowserLeftMouseDownInput>(),
            )

        assertThat(roundtrippedBrowserLeftMouseDownInput).isEqualTo(browserLeftMouseDownInput)
    }
}
