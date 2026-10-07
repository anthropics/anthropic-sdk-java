package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScreenshotInputTest {

    @Test
    fun create() {
        val browserScreenshotInput = BrowserScreenshotInput.builder().tabId("tab_id").build()

        assertThat(browserScreenshotInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScreenshotInput = BrowserScreenshotInput.builder().tabId("tab_id").build()

        val roundtrippedBrowserScreenshotInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScreenshotInput),
                jacksonTypeRef<BrowserScreenshotInput>(),
            )

        assertThat(roundtrippedBrowserScreenshotInput).isEqualTo(browserScreenshotInput)
    }
}
