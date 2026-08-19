// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateChangeTabOpenedTest {

    @Test
    fun create() {
        val browserStateChangeTabOpened = BrowserStateChangeTabOpened.of("tab_id")

        assertThat(browserStateChangeTabOpened.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateChangeTabOpened = BrowserStateChangeTabOpened.of("tab_id")

        val roundtrippedBrowserStateChangeTabOpened =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateChangeTabOpened),
                jacksonTypeRef<BrowserStateChangeTabOpened>(),
            )

        assertThat(roundtrippedBrowserStateChangeTabOpened).isEqualTo(browserStateChangeTabOpened)
    }
}
