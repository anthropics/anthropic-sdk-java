package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFindInputTest {

    @Test
    fun create() {
        val browserFindInput = BrowserFindInput.builder().query("query").tabId("tab_id").build()

        assertThat(browserFindInput.query()).isEqualTo("query")
        assertThat(browserFindInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFindInput = BrowserFindInput.builder().query("query").tabId("tab_id").build()

        val roundtrippedBrowserFindInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFindInput),
                jacksonTypeRef<BrowserFindInput>(),
            )

        assertThat(roundtrippedBrowserFindInput).isEqualTo(browserFindInput)
    }
}
