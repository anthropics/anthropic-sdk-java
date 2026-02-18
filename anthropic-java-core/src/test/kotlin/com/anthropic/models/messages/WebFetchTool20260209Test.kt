// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchTool20260209Test {

    @Test
    fun create() {
        val webFetchTool20260209 =
            WebFetchTool20260209.builder()
                .addAllowedCaller(WebFetchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        assertThat(webFetchTool20260209.allowedCallers().getOrNull())
            .containsExactly(WebFetchTool20260209.AllowedCaller.DIRECT)
        assertThat(webFetchTool20260209.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260209.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260209.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webFetchTool20260209.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(webFetchTool20260209.deferLoading()).contains(true)
        assertThat(webFetchTool20260209.maxContentTokens()).contains(1L)
        assertThat(webFetchTool20260209.maxUses()).contains(1L)
        assertThat(webFetchTool20260209.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchTool20260209 =
            WebFetchTool20260209.builder()
                .addAllowedCaller(WebFetchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        val roundtrippedWebFetchTool20260209 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchTool20260209),
                jacksonTypeRef<WebFetchTool20260209>(),
            )

        assertThat(roundtrippedWebFetchTool20260209).isEqualTo(webFetchTool20260209)
    }
}
