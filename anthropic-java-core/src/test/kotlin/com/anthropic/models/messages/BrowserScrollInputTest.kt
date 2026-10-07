package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollInputTest {

    @Test
    fun create() {
        val browserScrollInput =
            BrowserScrollInput.builder()
                .scrollDirection(BrowserScrollDirection.UP)
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .scrollAmount(1L)
                .tabId("tab_id")
                .build()

        assertThat(browserScrollInput.scrollDirection()).isEqualTo(BrowserScrollDirection.UP)
        assertThat(browserScrollInput.target())
            .isEqualTo(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
        assertThat(browserScrollInput.scrollAmount()).contains(1L)
        assertThat(browserScrollInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollInput =
            BrowserScrollInput.builder()
                .scrollDirection(BrowserScrollDirection.UP)
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .scrollAmount(1L)
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserScrollInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollInput),
                jacksonTypeRef<BrowserScrollInput>(),
            )

        assertThat(roundtrippedBrowserScrollInput).isEqualTo(browserScrollInput)
    }
}
