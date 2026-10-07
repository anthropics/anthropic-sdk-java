package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserGetPageTextInputTest {

    @Test
    fun create() {
        val browserGetPageTextInput = BrowserGetPageTextInput.builder().tabId("tab_id").build()

        assertThat(browserGetPageTextInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserGetPageTextInput = BrowserGetPageTextInput.builder().tabId("tab_id").build()

        val roundtrippedBrowserGetPageTextInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserGetPageTextInput),
                jacksonTypeRef<BrowserGetPageTextInput>(),
            )

        assertThat(roundtrippedBrowserGetPageTextInput).isEqualTo(browserGetPageTextInput)
    }
}
