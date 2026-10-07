package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserReadNetworkInputTest {

    @Test
    fun create() {
        val browserReadNetworkInput = BrowserReadNetworkInput.builder().tabId("tab_id").build()

        assertThat(browserReadNetworkInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserReadNetworkInput = BrowserReadNetworkInput.builder().tabId("tab_id").build()

        val roundtrippedBrowserReadNetworkInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserReadNetworkInput),
                jacksonTypeRef<BrowserReadNetworkInput>(),
            )

        assertThat(roundtrippedBrowserReadNetworkInput).isEqualTo(browserReadNetworkInput)
    }
}
