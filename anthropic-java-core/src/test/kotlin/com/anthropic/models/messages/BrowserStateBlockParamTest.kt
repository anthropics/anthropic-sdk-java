// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserStateBlockParamTest {

    @Test
    fun create() {
        val browserStateBlockParam =
            BrowserStateBlockParam.builder()
                .addTab(
                    BrowserStateTabEntry.builder()
                        .tabId("tab_id")
                        .title("title")
                        .url("url")
                        .active(true)
                        .build()
                )
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .addTabOpenedStateChange("tab_id")
                .build()

        assertThat(browserStateBlockParam.tabs())
            .containsExactly(
                BrowserStateTabEntry.builder()
                    .tabId("tab_id")
                    .title("title")
                    .url("url")
                    .active(true)
                    .build()
            )
        assertThat(browserStateBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(browserStateBlockParam.stateChanges().getOrNull())
            .containsExactly(BrowserStateChange.ofTabOpened("tab_id"))
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBrowserStateBlockParam =
            BrowserStateBlockParam.builder()
                .addTab(
                    BrowserStateTabEntry.builder().tabId("tab_id").title("title").url("url").build()
                )
                .build()

        val browserStateBlockParam =
            baseBrowserStateBlockParam
                .toBuilder()
                .addStateChange(BrowserStateChange.ofTabOpened("tab_id"))
                .build()

        assertThat(browserStateBlockParam.stateChanges().getOrNull())
            .containsExactly(BrowserStateChange.ofTabOpened("tab_id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserStateBlockParam =
            BrowserStateBlockParam.builder()
                .addTab(
                    BrowserStateTabEntry.builder()
                        .tabId("tab_id")
                        .title("title")
                        .url("url")
                        .active(true)
                        .build()
                )
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .addTabOpenedStateChange("tab_id")
                .build()

        val roundtrippedBrowserStateBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserStateBlockParam),
                jacksonTypeRef<BrowserStateBlockParam>(),
            )

        assertThat(roundtrippedBrowserStateBlockParam).isEqualTo(browserStateBlockParam)
    }
}
