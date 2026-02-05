// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCompactionBlockParamTest {

    @Test
    fun create() {
        val betaCompactionBlockParam =
            BetaCompactionBlockParam.builder()
                .content("content")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaCompactionBlockParam.content()).contains("content")
        assertThat(betaCompactionBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCompactionBlockParam =
            BetaCompactionBlockParam.builder()
                .content("content")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaCompactionBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCompactionBlockParam),
                jacksonTypeRef<BetaCompactionBlockParam>(),
            )

        assertThat(roundtrippedBetaCompactionBlockParam).isEqualTo(betaCompactionBlockParam)
    }
}
