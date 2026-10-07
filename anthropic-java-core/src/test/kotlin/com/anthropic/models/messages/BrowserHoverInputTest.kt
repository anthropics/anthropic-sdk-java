package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoverInputTest {

    @Test
    fun create() {
        val browserHoverInput =
            BrowserHoverInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        assertThat(browserHoverInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserHoverInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoverInput =
            BrowserHoverInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserHoverInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoverInput),
                jacksonTypeRef<BrowserHoverInput>(),
            )

        assertThat(roundtrippedBrowserHoverInput).isEqualTo(browserHoverInput)
    }
}
