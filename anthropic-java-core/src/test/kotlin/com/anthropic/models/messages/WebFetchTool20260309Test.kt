// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchTool20260309Test {

    @Test
    fun create() {
        val webFetchTool20260309 =
            WebFetchTool20260309.builder()
                .addAllowedCaller(WebFetchTool20260309.AllowedCaller.DIRECT)
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
                .useCache(true)
                .build()

        assertThat(webFetchTool20260309.allowedCallers().getOrNull())
            .containsExactly(WebFetchTool20260309.AllowedCaller.DIRECT)
        assertThat(webFetchTool20260309.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260309.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260309.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webFetchTool20260309.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(webFetchTool20260309.deferLoading()).contains(true)
        assertThat(webFetchTool20260309.maxContentTokens()).contains(1L)
        assertThat(webFetchTool20260309.maxUses()).contains(1L)
        assertThat(webFetchTool20260309.strict()).contains(true)
        assertThat(webFetchTool20260309.useCache()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchTool20260309 =
            WebFetchTool20260309.builder()
                .addAllowedCaller(WebFetchTool20260309.AllowedCaller.DIRECT)
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
                .useCache(true)
                .build()

        val roundtrippedWebFetchTool20260309 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchTool20260309),
                jacksonTypeRef<WebFetchTool20260309>(),
            )

        assertThat(roundtrippedWebFetchTool20260309).isEqualTo(webFetchTool20260309)
    }
}
