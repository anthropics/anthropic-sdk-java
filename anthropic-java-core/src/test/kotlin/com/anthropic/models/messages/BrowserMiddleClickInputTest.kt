package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserMiddleClickInputTest {

    @Test
    fun create() {
        val browserMiddleClickInput =
            BrowserMiddleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(browserMiddleClickInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserMiddleClickInput.modifiers()).contains("modifiers")
        assertThat(browserMiddleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserMiddleClickInput =
            BrowserMiddleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserMiddleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserMiddleClickInput),
                jacksonTypeRef<BrowserMiddleClickInput>(),
            )

        assertThat(roundtrippedBrowserMiddleClickInput).isEqualTo(browserMiddleClickInput)
    }
}
