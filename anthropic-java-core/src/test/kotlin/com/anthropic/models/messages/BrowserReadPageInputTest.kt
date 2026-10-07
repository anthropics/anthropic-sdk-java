package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadPageInputTest {

    @Test
    fun create() {
        val browserReadPageInput =
            BrowserReadPageInput.builder()
                .depth(1L)
                .filter(BrowserReadPageFilter.ALL)
                .ref("ref")
                .tabId("tab_id")
                .build()

        assertThat(browserReadPageInput.depth()).contains(1L)
        assertThat(browserReadPageInput.filter()).contains(BrowserReadPageFilter.ALL)
        assertThat(browserReadPageInput.ref()).contains("ref")
        assertThat(browserReadPageInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadPageInput =
            BrowserReadPageInput.builder()
                .depth(1L)
                .filter(BrowserReadPageFilter.ALL)
                .ref("ref")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserReadPageInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadPageInput),
                jacksonTypeRef<BrowserReadPageInput>(),
            )

        assertThat(roundtrippedBrowserReadPageInput).isEqualTo(browserReadPageInput)
    }
}
