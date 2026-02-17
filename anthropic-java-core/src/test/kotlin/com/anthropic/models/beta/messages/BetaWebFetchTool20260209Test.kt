// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchTool20260209Test {

    @Test
    fun create() {
        val betaWebFetchTool20260209 =
            BetaWebFetchTool20260209.builder()
                .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
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
                .strict(true)
                .build()

        assertThat(betaWebFetchTool20260209.allowedCallers().getOrNull())
            .containsExactly(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
        assertThat(betaWebFetchTool20260209.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260209.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260209.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebFetchTool20260209.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaWebFetchTool20260209.deferLoading()).contains(true)
        assertThat(betaWebFetchTool20260209.maxContentTokens()).contains(1L)
        assertThat(betaWebFetchTool20260209.maxUses()).contains(1L)
        assertThat(betaWebFetchTool20260209.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchTool20260209 =
            BetaWebFetchTool20260209.builder()
                .addAllowedCaller(BetaWebFetchTool20260209.AllowedCaller.DIRECT)
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
                .strict(true)
                .build()

        val roundtrippedBetaWebFetchTool20260209 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchTool20260209),
                jacksonTypeRef<BetaWebFetchTool20260209>(),
            )

        assertThat(roundtrippedBetaWebFetchTool20260209).isEqualTo(betaWebFetchTool20260209)
    }
}
