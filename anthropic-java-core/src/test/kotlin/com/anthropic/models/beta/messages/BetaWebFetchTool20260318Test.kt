// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchTool20260318Test {

    @Test
    fun create() {
        val betaWebFetchTool20260318 =
            BetaWebFetchTool20260318.builder()
                .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .useCache(true)
                .build()

        assertThat(betaWebFetchTool20260318.allowedCallers().getOrNull())
            .containsExactly(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
        assertThat(betaWebFetchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260318.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebFetchTool20260318.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaWebFetchTool20260318.deferLoading()).contains(true)
        assertThat(betaWebFetchTool20260318.maxContentTokens()).contains(1L)
        assertThat(betaWebFetchTool20260318.maxUses()).contains(1L)
        assertThat(betaWebFetchTool20260318.responseInclusion())
            .contains(BetaWebFetchTool20260318.ResponseInclusion.FULL)
        assertThat(betaWebFetchTool20260318.strict()).contains(true)
        assertThat(betaWebFetchTool20260318.useCache()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaWebFetchTool20260318 = BetaWebFetchTool20260318.builder().build()

        val betaWebFetchTool20260318 =
            baseBetaWebFetchTool20260318
                .toBuilder()
                .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .build()

        assertThat(betaWebFetchTool20260318.allowedCallers().getOrNull())
            .containsExactly(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
        assertThat(betaWebFetchTool20260318.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260318.blockedDomains().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchTool20260318 =
            BetaWebFetchTool20260318.builder()
                .addAllowedCaller(BetaWebFetchTool20260318.AllowedCaller.DIRECT)
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .deferLoading(true)
                .maxContentTokens(1L)
                .maxUses(1L)
                .responseInclusion(BetaWebFetchTool20260318.ResponseInclusion.FULL)
                .strict(true)
                .useCache(true)
                .build()

        val roundtrippedBetaWebFetchTool20260318 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchTool20260318),
                jacksonTypeRef<BetaWebFetchTool20260318>(),
            )

        assertThat(roundtrippedBetaWebFetchTool20260318).isEqualTo(betaWebFetchTool20260318)
    }
}
