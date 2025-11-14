// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchTool20250910Test {

    @Test
    fun create() {
        val betaWebFetchTool20250910 =
            BetaWebFetchTool20250910.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        assertThat(betaWebFetchTool20250910.allowedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20250910.blockedDomains().getOrNull()).containsExactly("string")
        assertThat(betaWebFetchTool20250910.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaWebFetchTool20250910.citations())
            .contains(BetaCitationsConfigParam.builder().enabled(true).build())
        assertThat(betaWebFetchTool20250910.maxContentTokens()).contains(1L)
        assertThat(betaWebFetchTool20250910.maxUses()).contains(1L)
        assertThat(betaWebFetchTool20250910.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchTool20250910 =
            BetaWebFetchTool20250910.builder()
                .addAllowedDomain("string")
                .addBlockedDomain("string")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .citations(BetaCitationsConfigParam.builder().enabled(true).build())
                .maxContentTokens(1L)
                .maxUses(1L)
                .strict(true)
                .build()

        val roundtrippedBetaWebFetchTool20250910 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchTool20250910),
                jacksonTypeRef<BetaWebFetchTool20250910>(),
            )

        assertThat(roundtrippedBetaWebFetchTool20250910).isEqualTo(betaWebFetchTool20250910)
    }
}
