// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250429Test {

    @Test
    fun create() {
        val betaToolTextEditor20250429 =
            BetaToolTextEditor20250429.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaToolTextEditor20250429.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20250429 =
            BetaToolTextEditor20250429.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaToolTextEditor20250429 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20250429),
                jacksonTypeRef<BetaToolTextEditor20250429>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20250429).isEqualTo(betaToolTextEditor20250429)
    }
}
