// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.anthropic.models.messages.Model
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAdvisorTool20260301Test {

    @Test
    fun create() {
        val betaAdvisorTool20260301 =
            BetaAdvisorTool20260301.builder()
                .model(Model.CLAUDE_OPUS_4_7)
                .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caching(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .build()

        assertThat(betaAdvisorTool20260301.model()).isEqualTo(Model.CLAUDE_OPUS_4_7)
        assertThat(betaAdvisorTool20260301.allowedCallers().getOrNull())
            .containsExactly(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
        assertThat(betaAdvisorTool20260301.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaAdvisorTool20260301.caching())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaAdvisorTool20260301.deferLoading()).contains(true)
        assertThat(betaAdvisorTool20260301.maxUses()).contains(1L)
        assertThat(betaAdvisorTool20260301.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAdvisorTool20260301 =
            BetaAdvisorTool20260301.builder()
                .model(Model.CLAUDE_OPUS_4_7)
                .addAllowedCaller(BetaAdvisorTool20260301.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .caching(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .maxUses(1L)
                .strict(true)
                .build()

        val roundtrippedBetaAdvisorTool20260301 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAdvisorTool20260301),
                jacksonTypeRef<BetaAdvisorTool20260301>(),
            )

        assertThat(roundtrippedBetaAdvisorTool20260301).isEqualTo(betaAdvisorTool20260301)
    }
}
