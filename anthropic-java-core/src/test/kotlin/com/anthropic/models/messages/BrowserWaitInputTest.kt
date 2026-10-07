package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserWaitInputTest {

    @Test
    fun create() {
        val browserWaitInput = BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build()

        assertThat(browserWaitInput.duration()).isEqualTo(0.0)
        assertThat(browserWaitInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserWaitInput = BrowserWaitInput.builder().duration(0.0).tabId("tab_id").build()

        val roundtrippedBrowserWaitInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserWaitInput),
                jacksonTypeRef<BrowserWaitInput>(),
            )

        assertThat(roundtrippedBrowserWaitInput).isEqualTo(browserWaitInput)
    }
}
