// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchTool20250305Test {

    @Test
    fun create() {
        val betaWebSearchTool20250305 =
            BetaWebSearchTool20250305.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaWebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        assertThat(betaWebSearchTool20250305.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20250305.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20250305.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebSearchTool20250305.maxUses()).contains(1L)
        assertThat(betaWebSearchTool20250305.strict()).contains(true)
        assertThat(betaWebSearchTool20250305.userLocation())
            .contains(
                BetaWebSearchTool20250305.UserLocation.builder()
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
        val betaWebSearchTool20250305 =
            BetaWebSearchTool20250305.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaWebSearchTool20250305.UserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val roundtrippedBetaWebSearchTool20250305 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchTool20250305),
                jacksonTypeRef<BetaWebSearchTool20250305>(),
            )

        assertThat(roundtrippedBetaWebSearchTool20250305).isEqualTo(betaWebSearchTool20250305)
    }
}
