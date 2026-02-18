// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchTool20260209Test {

    @Test
    fun create() {
        val webSearchTool20260209 =
            WebSearchTool20260209.builder()
                .addAllowedCaller(WebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20260209.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        assertThat(webSearchTool20260209.allowedCallers().getOrNull())
            .containsExactly(WebSearchTool20260209.AllowedCaller.DIRECT)
        assertThat(webSearchTool20260209.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20260209.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20260209.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webSearchTool20260209.deferLoading()).contains(true)
        assertThat(webSearchTool20260209.maxUses()).contains(1L)
        assertThat(webSearchTool20260209.strict()).contains(true)
        assertThat(webSearchTool20260209.userLocation())
            .contains(
                WebSearchTool20260209.UserLocation.builder()
                    .city("New York")
                    .country("US")
                    .region("California")
                    .timezone("America/New_York")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchTool20260209 =
            WebSearchTool20260209.builder()
                .addAllowedCaller(WebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20260209.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val roundtrippedWebSearchTool20260209 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchTool20260209),
                jacksonTypeRef<WebSearchTool20260209>(),
            )

        assertThat(roundtrippedWebSearchTool20260209).isEqualTo(webSearchTool20260209)
    }
}
