package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserKeyInputTest {

    @Test
    fun create() {
        val browserKeyInput =
            BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()

        assertThat(browserKeyInput.text()).isEqualTo("text")
        assertThat(browserKeyInput.repeat()).contains(1L)
        assertThat(browserKeyInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserKeyInput =
            BrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()

        val roundtrippedBrowserKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserKeyInput),
                jacksonTypeRef<BrowserKeyInput>(),
            )

        assertThat(roundtrippedBrowserKeyInput).isEqualTo(browserKeyInput)
    }
}
