package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserSwitchTabInputTest {

    @Test
    fun create() {
        val browserSwitchTabInput = BrowserSwitchTabInput.of("tab_id")

        assertThat(browserSwitchTabInput.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserSwitchTabInput = BrowserSwitchTabInput.of("tab_id")

        val roundtrippedBrowserSwitchTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserSwitchTabInput),
                jacksonTypeRef<BrowserSwitchTabInput>(),
            )

        assertThat(roundtrippedBrowserSwitchTabInput).isEqualTo(browserSwitchTabInput)
    }
}
