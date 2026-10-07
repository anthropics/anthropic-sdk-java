package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserScrollToInputTest {

    @Test
    fun create() {
        val browserScrollToInput =
            BrowserScrollToInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .tabId("tab_id")
                .build()

        assertThat(browserScrollToInput.target()).isEqualTo(BrowserRefTarget.of("ref"))
        assertThat(browserScrollToInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserScrollToInput =
            BrowserScrollToInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserScrollToInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserScrollToInput),
                jacksonTypeRef<BrowserScrollToInput>(),
            )

        assertThat(roundtrippedBrowserScrollToInput).isEqualTo(browserScrollToInput)
    }
}
