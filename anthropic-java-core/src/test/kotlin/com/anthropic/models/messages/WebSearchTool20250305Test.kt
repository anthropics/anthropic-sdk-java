// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchTool20250305Test {

    @Test
    fun create() {
        val webSearchTool20250305 =
            WebSearchTool20250305.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        assertThat(webSearchTool20250305.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20250305.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(webSearchTool20250305.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webSearchTool20250305.maxUses()).contains(1L)
        assertThat(webSearchTool20250305.strict()).contains(true)
        assertThat(webSearchTool20250305.userLocation())
            .contains(
                WebSearchTool20250305.UserLocation.builder()
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
        val webSearchTool20250305 =
            WebSearchTool20250305.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    WebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val roundtrippedWebSearchTool20250305 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchTool20250305),
                jacksonTypeRef<WebSearchTool20250305>(),
            )

        assertThat(roundtrippedWebSearchTool20250305).isEqualTo(webSearchTool20250305)
    }
}
