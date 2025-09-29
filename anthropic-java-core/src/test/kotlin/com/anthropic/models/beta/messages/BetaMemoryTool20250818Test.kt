// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818Test {

    @Test
    fun create() {
        val betaMemoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaMemoryTool20250818.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818 =
            BetaMemoryTool20250818.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaMemoryTool20250818 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818),
                jacksonTypeRef<BetaMemoryTool20250818>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818).isEqualTo(betaMemoryTool20250818)
    }
}
