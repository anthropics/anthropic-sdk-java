// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchTool20260318Test {

    @Test
    fun create() {
        val betaWebSearchTool20260318 =
            BetaWebSearchTool20260318.builder()
                .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
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

        assertThat(betaWebSearchTool20260318.allowedCallers().getOrNull())
            .containsExactly(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
        assertThat(betaWebSearchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20260318.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebSearchTool20260318.deferLoading()).contains(true)
        assertThat(betaWebSearchTool20260318.maxUses()).contains(1L)
        assertThat(betaWebSearchTool20260318.responseInclusion())
            .contains(BetaWebSearchTool20260318.ResponseInclusion.FULL)
        assertThat(betaWebSearchTool20260318.strict()).contains(true)
        assertThat(betaWebSearchTool20260318.userLocation())
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
    fun addToUnsetListsOnToBuilder() {
        val baseBetaWebSearchTool20260318 = BetaWebSearchTool20260318.builder().build()

        val betaWebSearchTool20260318 =
            baseBetaWebSearchTool20260318
                .toBuilder()
                .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaWebSearchTool20260318.allowedCallers().getOrNull())
            .containsExactly(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
        assertThat(betaWebSearchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebSearchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchTool20260318 =
            BetaWebSearchTool20260318.builder()
                .addAllowedCaller(BetaWebSearchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .responseInclusion(BetaWebSearchTool20260318.ResponseInclusion.FULL)
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

        val roundtrippedBetaWebSearchTool20260318 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchTool20260318),
                jacksonTypeRef<BetaWebSearchTool20260318>(),
            )

        assertThat(roundtrippedBetaWebSearchTool20260318).isEqualTo(betaWebSearchTool20260318)
    }
}
