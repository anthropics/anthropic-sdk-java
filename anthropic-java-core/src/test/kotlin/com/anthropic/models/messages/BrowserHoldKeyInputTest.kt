package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserHoldKeyInputTest {

    @Test
    fun create() {
        val browserHoldKeyInput =
            BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()

        assertThat(browserHoldKeyInput.duration()).isEqualTo(0.0)
        assertThat(browserHoldKeyInput.text()).isEqualTo("text")
        assertThat(browserHoldKeyInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserHoldKeyInput =
            BrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()

        val roundtrippedBrowserHoldKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserHoldKeyInput),
                jacksonTypeRef<BrowserHoldKeyInput>(),
            )

        assertThat(roundtrippedBrowserHoldKeyInput).isEqualTo(browserHoldKeyInput)
    }
}
