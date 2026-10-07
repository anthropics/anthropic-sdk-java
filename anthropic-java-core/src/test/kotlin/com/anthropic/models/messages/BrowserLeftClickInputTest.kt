package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserLeftClickInputTest {

    @Test
    fun create() {
        val browserLeftClickInput =
            BrowserLeftClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(browserLeftClickInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserLeftClickInput.modifiers()).contains("modifiers")
        assertThat(browserLeftClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserLeftClickInput =
            BrowserLeftClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserLeftClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserLeftClickInput),
                jacksonTypeRef<BrowserLeftClickInput>(),
            )

        assertThat(roundtrippedBrowserLeftClickInput).isEqualTo(browserLeftClickInput)
    }
}
