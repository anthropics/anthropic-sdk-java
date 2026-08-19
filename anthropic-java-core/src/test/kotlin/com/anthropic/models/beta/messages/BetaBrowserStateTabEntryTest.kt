// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateTabEntryTest {

    @Test
    fun create() {
        val betaBrowserStateTabEntry =
            BetaBrowserStateTabEntry.builder()
                .tabId("tab_id")
                .title("title")
                .url("url")
                .active(true)
                .build()

        assertThat(betaBrowserStateTabEntry.tabId()).isEqualTo("tab_id")
        assertThat(betaBrowserStateTabEntry.title()).isEqualTo("title")
        assertThat(betaBrowserStateTabEntry.url()).isEqualTo("url")
        assertThat(betaBrowserStateTabEntry.active()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateTabEntry =
            BetaBrowserStateTabEntry.builder()
                .tabId("tab_id")
                .title("title")
                .url("url")
                .active(true)
                .build()

        val roundtrippedBetaBrowserStateTabEntry =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateTabEntry),
                jacksonTypeRef<BetaBrowserStateTabEntry>(),
            )

        assertThat(roundtrippedBetaBrowserStateTabEntry).isEqualTo(betaBrowserStateTabEntry)
    }
}
