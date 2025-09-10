// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchToolResultBlockParamTest {

    @Test
    fun create() {
        val betaWebFetchToolResultBlockParam =
            BetaWebFetchToolResultBlockParam.builder()
                .content(
                    BetaWebFetchToolResultErrorBlockParam.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaWebFetchToolResultBlockParam.content())
            .isEqualTo(
                BetaWebFetchToolResultBlockParam.Content.ofBetaWebFetchToolResultErrorBlockParam(
                    BetaWebFetchToolResultErrorBlockParam.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(betaWebFetchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaWebFetchToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchToolResultBlockParam =
            BetaWebFetchToolResultBlockParam.builder()
                .content(
                    BetaWebFetchToolResultErrorBlockParam.builder()
                        .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaWebFetchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchToolResultBlockParam),
                jacksonTypeRef<BetaWebFetchToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaWebFetchToolResultBlockParam)
            .isEqualTo(betaWebFetchToolResultBlockParam)
    }
}
