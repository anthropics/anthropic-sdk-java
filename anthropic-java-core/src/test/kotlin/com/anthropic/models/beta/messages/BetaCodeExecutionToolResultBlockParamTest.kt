// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val betaCodeExecutionToolResultBlockParam =
            BetaCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaCodeExecutionToolResultBlockParam.content())
            .isEqualTo(
                BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(
                    BetaCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(betaCodeExecutionToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaCodeExecutionToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultBlockParam =
            BetaCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BetaCodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultBlockParam)
            .isEqualTo(betaCodeExecutionToolResultBlockParam)
    }
}
