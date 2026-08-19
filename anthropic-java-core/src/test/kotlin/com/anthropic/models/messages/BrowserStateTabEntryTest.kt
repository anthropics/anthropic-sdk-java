// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateTabEntryTest {

    @Test
    fun create() {
        val browserStateTabEntry =
            BrowserStateTabEntry.builder()
                .tabId("tab_id")
                .title("title")
                .url("url")
                .active(true)
                .build()

        assertThat(browserStateTabEntry.tabId()).isEqualTo("tab_id")
        assertThat(browserStateTabEntry.title()).isEqualTo("title")
        assertThat(browserStateTabEntry.url()).isEqualTo("url")
        assertThat(browserStateTabEntry.active()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateTabEntry =
            BrowserStateTabEntry.builder()
                .tabId("tab_id")
                .title("title")
                .url("url")
                .active(true)
                .build()

        val roundtrippedBrowserStateTabEntry =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateTabEntry),
                jacksonTypeRef<BrowserStateTabEntry>(),
            )

        assertThat(roundtrippedBrowserStateTabEntry).isEqualTo(browserStateTabEntry)
    }
}
