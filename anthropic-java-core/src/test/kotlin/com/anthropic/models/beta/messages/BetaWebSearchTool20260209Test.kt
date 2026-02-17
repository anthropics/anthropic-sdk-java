// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchTool20260209Test {

    @Test
    fun create() {
        val betaWebSearchTool20260209 =
            BetaWebSearchTool20260209.builder()
                .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        assertThat(betaWebSearchTool20260209.allowedCallers().getOrNull())
            .containsExactly(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
        assertThat(betaWebSearchTool20260209.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20260209.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20260209.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebSearchTool20260209.deferLoading()).contains(true)
        assertThat(betaWebSearchTool20260209.maxUses()).contains(1L)
        assertThat(betaWebSearchTool20260209.strict()).contains(true)
        assertThat(betaWebSearchTool20260209.userLocation())
            .contains(
                BetaUserLocation.builder()
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
        val betaWebSearchTool20260209 =
            BetaWebSearchTool20260209.builder()
                .addAllowedCaller(BetaWebSearchTool20260209.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .userLocation(
                    BetaUserLocation.builder()
                        .city("New York")
                        .country("US")
                        .region("California")
                        .timezone("America/New_York")
                        .build()
                )
                .build()

        val roundtrippedBetaWebSearchTool20260209 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchTool20260209),
                jacksonTypeRef<BetaWebSearchTool20260209>(),
            )

        assertThat(roundtrippedBetaWebSearchTool20260209).isEqualTo(betaWebSearchTool20260209)
    }
}
