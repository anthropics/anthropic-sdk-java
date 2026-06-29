// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchTool20260318Test {

    @Test
    fun create() {
        val webSearchTool20260318 =
            WebSearchTool20260318.builder()
                .addAllowedCaller(WebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(WebSearchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .userLocation(
                    UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        assertThat(webSearchTool20260318.allowedCallers().getOrNull())
            .containsExactly(WebSearchTool20260318.AllowedCaller.DIRECT)
        assertThat(webSearchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20260318.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webSearchTool20260318.deferLoading()).contains(true)
        assertThat(webSearchTool20260318.maxUses()).contains(1L)
        assertThat(webSearchTool20260318.responseInclusion())
            .contains(WebSearchTool20260318.ResponseInclusion.FULL)
        assertThat(webSearchTool20260318.strict()).contains(true)
        assertThat(webSearchTool20260318.userLocation())
            .contains(
                UserLocation.builder()
                    .city("New York")
                    .country("US")
                    .region("California")
                    .timezone("America/New_York")
                    .build()
            )
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseWebSearchTool20260318 = WebSearchTool20260318.builder().build()

        val webSearchTool20260318 =
            baseWebSearchTool20260318
                .toBuilder()
                .addAllowedCaller(WebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(webSearchTool20260318.allowedCallers().getOrNull())
            .containsExactly(WebSearchTool20260318.AllowedCaller.DIRECT)
        assertThat(webSearchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchTool20260318 =
            WebSearchTool20260318.builder()
                .addAllowedCaller(WebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(WebSearchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .userLocation(
                    UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val roundtrippedWebSearchTool20260318 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchTool20260318),
                jacksonTypeRef<WebSearchTool20260318>(),
            )

        assertThat(roundtrippedWebSearchTool20260318).isEqualTo(webSearchTool20260318)
    }
}
