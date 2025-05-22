// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestMcpToolResultBlockParamTest {

    @Test
    fun create() {
        val betaRequestMcpToolResultBlockParam =
            BetaRequestMcpToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()

        assertThat(betaRequestMcpToolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaRequestMcpToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaRequestMcpToolResultBlockParam.content())
            .contains(BetaRequestMcpToolResultBlockParam.Content.ofString("string"))
        assertThat(betaRequestMcpToolResultBlockParam.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestMcpToolResultBlockParam =
            BetaRequestMcpToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .content("string")
                .isError(true)
                .build()

        val roundtrippedBetaRequestMcpToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestMcpToolResultBlockParam),
                jacksonTypeRef<BetaRequestMcpToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaRequestMcpToolResultBlockParam)
            .isEqualTo(betaRequestMcpToolResultBlockParam)
    }
}
