package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTypeInputTest {

    @Test
    fun create() {
        val browserTypeInput = BrowserTypeInput.builder().text("text").tabId("tab_id").build()

        assertThat(browserTypeInput.text()).isEqualTo("text")
        assertThat(browserTypeInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTypeInput = BrowserTypeInput.builder().text("text").tabId("tab_id").build()

        val roundtrippedBrowserTypeInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTypeInput),
                jacksonTypeRef<BrowserTypeInput>(),
            )

        assertThat(roundtrippedBrowserTypeInput).isEqualTo(browserTypeInput)
    }
}
