// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchTool20250910Test {

    @Test
    fun create() {
        val webFetchTool20250910 =
            WebFetchTool20250910.builder()
                .addAllowedCaller(WebFetchTool20250910.AllowedCaller.DIRECT)
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

        assertThat(webFetchTool20250910.allowedCallers().getOrNull())
            .containsExactly(WebFetchTool20250910.AllowedCaller.DIRECT)
        assertThat(webFetchTool20250910.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20250910.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20250910.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webFetchTool20250910.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(webFetchTool20250910.deferLoading()).contains(true)
        assertThat(webFetchTool20250910.maxContentTokens()).contains(1L)
        assertThat(webFetchTool20250910.maxUses()).contains(1L)
        assertThat(webFetchTool20250910.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchTool20250910 =
            WebFetchTool20250910.builder()
                .addAllowedCaller(WebFetchTool20250910.AllowedCaller.DIRECT)
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

        val roundtrippedWebFetchTool20250910 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchTool20250910),
                jacksonTypeRef<WebFetchTool20250910>(),
            )

        assertThat(roundtrippedWebFetchTool20250910).isEqualTo(webFetchTool20250910)
    }
}
