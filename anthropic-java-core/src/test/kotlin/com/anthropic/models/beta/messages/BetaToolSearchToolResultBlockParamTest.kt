// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolResultBlockParamTest {

    @Test
    fun create() {
        val betaToolSearchToolResultBlockParam =
            BetaToolSearchToolResultBlockParam.builder()
                .content(
                    BetaToolSearchToolResultErrorParam.builder()
                        .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaToolSearchToolResultBlockParam.content())
            .isEqualTo(
                BetaToolSearchToolResultBlockParam.Content.ofBetaToolSearchToolResultErrorParam(
                    BetaToolSearchToolResultErrorParam.builder()
                        .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(betaToolSearchToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaToolSearchToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolResultBlockParam =
            BetaToolSearchToolResultBlockParam.builder()
                .content(
                    BetaToolSearchToolResultErrorParam.builder()
                        .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaToolSearchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolResultBlockParam),
                jacksonTypeRef<BetaToolSearchToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaToolSearchToolResultBlockParam)
            .isEqualTo(betaToolSearchToolResultBlockParam)
    }
}
