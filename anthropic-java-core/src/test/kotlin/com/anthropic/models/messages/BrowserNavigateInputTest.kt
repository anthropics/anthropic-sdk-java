package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserNavigateInputTest {

    @Test
    fun create() {
        val browserNavigateInput = BrowserNavigateInput.builder().url("url").tabId("tab_id").build()

        assertThat(browserNavigateInput.url()).isEqualTo("url")
        assertThat(browserNavigateInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserNavigateInput = BrowserNavigateInput.builder().url("url").tabId("tab_id").build()

        val roundtrippedBrowserNavigateInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserNavigateInput),
                jacksonTypeRef<BrowserNavigateInput>(),
            )

        assertThat(roundtrippedBrowserNavigateInput).isEqualTo(browserNavigateInput)
    }
}
