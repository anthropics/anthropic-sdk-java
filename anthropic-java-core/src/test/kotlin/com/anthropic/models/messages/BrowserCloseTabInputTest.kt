package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserCloseTabInputTest {

    @Test
    fun create() {
        val browserCloseTabInput = BrowserCloseTabInput.of("tab_id")

        assertThat(browserCloseTabInput.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserCloseTabInput = BrowserCloseTabInput.of("tab_id")

        val roundtrippedBrowserCloseTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserCloseTabInput),
                jacksonTypeRef<BrowserCloseTabInput>(),
            )

        assertThat(roundtrippedBrowserCloseTabInput).isEqualTo(browserCloseTabInput)
    }
}
