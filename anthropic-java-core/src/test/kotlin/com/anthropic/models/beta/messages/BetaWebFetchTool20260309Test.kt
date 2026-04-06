// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchTool20260309Test {

    @Test
    fun create() {
        val betaWebFetchTool20260309 =
            BetaWebFetchTool20260309.builder()
                .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
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
                .useCache(true)
                .build()

        assertThat(betaWebFetchTool20260309.allowedCallers().getOrNull())
            .containsExactly(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
        assertThat(betaWebFetchTool20260309.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260309.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20260309.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebFetchTool20260309.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaWebFetchTool20260309.deferLoading()).contains(true)
        assertThat(betaWebFetchTool20260309.maxContentTokens()).contains(1L)
        assertThat(betaWebFetchTool20260309.maxUses()).contains(1L)
        assertThat(betaWebFetchTool20260309.strict()).contains(true)
        assertThat(betaWebFetchTool20260309.useCache()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchTool20260309 =
            BetaWebFetchTool20260309.builder()
                .addAllowedCaller(BetaWebFetchTool20260309.AllowedCaller.DIRECT)
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
                .useCache(true)
                .build()

        val roundtrippedBetaWebFetchTool20260309 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchTool20260309),
                jacksonTypeRef<BetaWebFetchTool20260309>(),
            )

        assertThat(roundtrippedBetaWebFetchTool20260309).isEqualTo(betaWebFetchTool20260309)
    }
}
