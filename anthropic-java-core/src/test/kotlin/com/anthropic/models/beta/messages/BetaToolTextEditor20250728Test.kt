// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250728Test {

    @Test
    fun create() {
        val betaToolTextEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        assertThat(betaToolTextEditor20250728.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolTextEditor20250728.maxCharacters()).contains(1L)
        assertThat(betaToolTextEditor20250728.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20250728 =
            BetaToolTextEditor20250728.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .maxCharacters(1L)
                .strict(true)
                .build()

        val roundtrippedBetaToolTextEditor20250728 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20250728),
                jacksonTypeRef<BetaToolTextEditor20250728>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20250728).isEqualTo(betaToolTextEditor20250728)
    }
}
