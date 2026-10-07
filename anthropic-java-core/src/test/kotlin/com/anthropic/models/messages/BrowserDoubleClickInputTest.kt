package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserDoubleClickInputTest {

    @Test
    fun create() {
        val browserDoubleClickInput =
            BrowserDoubleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(browserDoubleClickInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserDoubleClickInput.modifiers()).contains("modifiers")
        assertThat(browserDoubleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserDoubleClickInput =
            BrowserDoubleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserDoubleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserDoubleClickInput),
                jacksonTypeRef<BrowserDoubleClickInput>(),
            )

        assertThat(roundtrippedBrowserDoubleClickInput).isEqualTo(browserDoubleClickInput)
    }
}
