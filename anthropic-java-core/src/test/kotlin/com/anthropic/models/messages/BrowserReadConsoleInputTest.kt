package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadConsoleInputTest {

    @Test
    fun create() {
        val browserReadConsoleInput = BrowserReadConsoleInput.builder().tabId("tab_id").build()

        assertThat(browserReadConsoleInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadConsoleInput = BrowserReadConsoleInput.builder().tabId("tab_id").build()

        val roundtrippedBrowserReadConsoleInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadConsoleInput),
                jacksonTypeRef<BrowserReadConsoleInput>(),
            )

        assertThat(roundtrippedBrowserReadConsoleInput).isEqualTo(browserReadConsoleInput)
    }
}
