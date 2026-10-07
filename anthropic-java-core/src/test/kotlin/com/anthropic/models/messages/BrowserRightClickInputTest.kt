package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserRightClickInputTest {

    @Test
    fun create() {
        val browserRightClickInput =
            BrowserRightClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(browserRightClickInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserRightClickInput.modifiers()).contains("modifiers")
        assertThat(browserRightClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserRightClickInput =
            BrowserRightClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserRightClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserRightClickInput),
                jacksonTypeRef<BrowserRightClickInput>(),
            )

        assertThat(roundtrippedBrowserRightClickInput).isEqualTo(browserRightClickInput)
    }
}
