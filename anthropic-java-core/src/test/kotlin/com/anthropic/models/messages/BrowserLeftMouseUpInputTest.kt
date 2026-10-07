package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftMouseUpInputTest {

    @Test
    fun create() {
        val browserLeftMouseUpInput =
            BrowserLeftMouseUpInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(browserLeftMouseUpInput.target())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserLeftMouseUpInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftMouseUpInput =
            BrowserLeftMouseUpInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserLeftMouseUpInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftMouseUpInput),
                jacksonTypeRef<BrowserLeftMouseUpInput>(),
            )

        assertThat(roundtrippedBrowserLeftMouseUpInput).isEqualTo(browserLeftMouseUpInput)
    }
}
