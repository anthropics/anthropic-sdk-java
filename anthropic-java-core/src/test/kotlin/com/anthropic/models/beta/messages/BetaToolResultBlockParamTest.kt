// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolResultBlockParamTest {

    @Test
    fun create() {
        val betaToolResultBlockParam =
            BetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()

        assertThat(betaToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolResultBlockParam.content())
            .contains(BetaToolResultBlockParam.Content.ofString("string"))
        assertThat(betaToolResultBlockParam.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolResultBlockParam =
            BetaToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()

        val roundtrippedBetaToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolResultBlockParam),
                jacksonTypeRef<BetaToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaToolResultBlockParam).isEqualTo(betaToolResultBlockParam)
    }
}
