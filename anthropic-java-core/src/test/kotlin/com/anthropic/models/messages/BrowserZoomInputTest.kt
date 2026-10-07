package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserZoomInputTest {

    @Test
    fun create() {
        val browserZoomInput =
            BrowserZoomInput.builder().region(listOf(0L, 0L, 0L, 0L)).tabId("tab_id").build()

        assertThat(browserZoomInput.region()).containsExactly(0L, 0L, 0L, 0L)
        assertThat(browserZoomInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserZoomInput =
            BrowserZoomInput.builder().region(listOf(0L, 0L, 0L, 0L)).tabId("tab_id").build()

        val roundtrippedBrowserZoomInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserZoomInput),
                jacksonTypeRef<BrowserZoomInput>(),
            )

        assertThat(roundtrippedBrowserZoomInput).isEqualTo(browserZoomInput)
    }
}
