package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserJavascriptExecInputTest {

    @Test
    fun create() {
        val browserJavascriptExecInput =
            BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()

        assertThat(browserJavascriptExecInput.text()).isEqualTo("text")
        assertThat(browserJavascriptExecInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserJavascriptExecInput =
            BrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()

        val roundtrippedBrowserJavascriptExecInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserJavascriptExecInput),
                jacksonTypeRef<BrowserJavascriptExecInput>(),
            )

        assertThat(roundtrippedBrowserJavascriptExecInput).isEqualTo(browserJavascriptExecInput)
    }
}
