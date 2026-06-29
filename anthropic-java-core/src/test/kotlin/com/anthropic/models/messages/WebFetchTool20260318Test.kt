// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchTool20260318Test {

    @Test
    fun create() {
        val webFetchTool20260318 =
            WebFetchTool20260318.builder()
                .addAllowedCaller(WebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(WebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .useCache(true)
                .build()

        assertThat(webFetchTool20260318.allowedCallers().getOrNull())
            .containsExactly(WebFetchTool20260318.AllowedCaller.DIRECT)
        assertThat(webFetchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260318.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webFetchTool20260318.citations())
            .contains(CitationsConfigParam.builder().enabled(true).build())
        assertThat(webFetchTool20260318.deferLoading()).contains(true)
        assertThat(webFetchTool20260318.maxContentTokens()).contains(1L)
        assertThat(webFetchTool20260318.maxUses()).contains(1L)
        assertThat(webFetchTool20260318.responseInclusion())
            .contains(WebFetchTool20260318.ResponseInclusion.FULL)
        assertThat(webFetchTool20260318.strict()).contains(true)
        assertThat(webFetchTool20260318.useCache()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseWebFetchTool20260318 = WebFetchTool20260318.builder().build()

        val webFetchTool20260318 =
            baseWebFetchTool20260318
                .toBuilder()
                .addAllowedCaller(WebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(webFetchTool20260318.allowedCallers().getOrNull())
            .containsExactly(WebFetchTool20260318.AllowedCaller.DIRECT)
        assertThat(webFetchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webFetchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchTool20260318 =
            WebFetchTool20260318.builder()
                .addAllowedCaller(WebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .citations(CitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(WebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .useCache(true)
                .build()

        val roundtrippedWebFetchTool20260318 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchTool20260318),
                jacksonTypeRef<WebFetchTool20260318>(),
            )

        assertThat(roundtrippedWebFetchTool20260318).isEqualTo(webFetchTool20260318)
    }
}
