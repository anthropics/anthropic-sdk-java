// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolReferenceBlockParamTest {

    @Test
    fun create() {
        val betaToolReferenceBlockParam =
            BetaToolReferenceBlockParam.builder()
                .toolName("tool_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaToolReferenceBlockParam.toolName()).isEqualTo("tool_name")
        assertThat(betaToolReferenceBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolReferenceBlockParam =
            BetaToolReferenceBlockParam.builder()
                .toolName("tool_name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaToolReferenceBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolReferenceBlockParam),
                jacksonTypeRef<BetaToolReferenceBlockParam>(),
            )

        assertThat(roundtrippedBetaToolReferenceBlockParam).isEqualTo(betaToolReferenceBlockParam)
    }
}
