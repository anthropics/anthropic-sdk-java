// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val betaBashCodeExecutionToolResultBlockParam =
            BetaBashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaBashCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaBashCodeExecutionToolResultBlockParam.content())
            .isEqualTo(
                BetaBashCodeExecutionToolResultBlockParam.Content
                    .ofBetaBashCodeExecutionToolResultErrorParam(
                        BetaBashCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                BetaBashCodeExecutionToolResultErrorParam.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .build()
                    )
            )
        assertThat(betaBashCodeExecutionToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaBashCodeExecutionToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionToolResultBlockParam =
            BetaBashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaBashCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT
                        )
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaBashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BetaBashCodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionToolResultBlockParam)
            .isEqualTo(betaBashCodeExecutionToolResultBlockParam)
    }
}
