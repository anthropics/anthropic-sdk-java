// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserStateBlockParamTest {

    @Test
    fun create() {
        val betaBrowserStateBlockParam =
            BetaBrowserStateBlockParam.builder()
                .addTab(
                    BetaBrowserStateTabEntry.builder()
                        .tabId("tab_id")
                        .title("title")
                        .url("url")
                        .active(true)
                        .build()
                )
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .addTabOpenedStateChange("tab_id")
                .build()

        assertThat(betaBrowserStateBlockParam.tabs())
            .containsExactly(
                BetaBrowserStateTabEntry.builder()
                    .tabId("tab_id")
                    .title("title")
                    .url("url")
                    .active(true)
                    .build()
            )
        assertThat(betaBrowserStateBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaBrowserStateBlockParam.stateChanges().getOrNull())
            .containsExactly(BetaBrowserStateChange.ofTabOpened("tab_id"))
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaBrowserStateBlockParam =
            BetaBrowserStateBlockParam.builder()
                .addTab(
                    BetaBrowserStateTabEntry.builder()
                        .tabId("tab_id")
                        .title("title")
                        .url("url")
                        .build()
                )
                .build()

        val betaBrowserStateBlockParam =
            baseBetaBrowserStateBlockParam
                .toBuilder()
                .addStateChange(BetaBrowserStateChange.ofTabOpened("tab_id"))
                .build()

        assertThat(betaBrowserStateBlockParam.stateChanges().getOrNull())
            .containsExactly(BetaBrowserStateChange.ofTabOpened("tab_id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserStateBlockParam =
            BetaBrowserStateBlockParam.builder()
                .addTab(
                    BetaBrowserStateTabEntry.builder()
                        .tabId("tab_id")
                        .title("title")
                        .url("url")
                        .active(true)
                        .build()
                )
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .addTabOpenedStateChange("tab_id")
                .build()

        val roundtrippedBetaBrowserStateBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserStateBlockParam),
                jacksonTypeRef<BetaBrowserStateBlockParam>(),
            )

        assertThat(roundtrippedBetaBrowserStateBlockParam).isEqualTo(betaBrowserStateBlockParam)
    }
}
