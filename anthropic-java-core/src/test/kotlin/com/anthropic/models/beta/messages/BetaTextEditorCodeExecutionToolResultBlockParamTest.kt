// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTextEditorCodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val betaTextEditorCodeExecutionToolResultBlockParam =
            BetaTextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode
                                .INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaTextEditorCodeExecutionToolResultBlockParam.content())
            .isEqualTo(
                BetaTextEditorCodeExecutionToolResultBlockParam.Content
                    .ofBetaTextEditorCodeExecutionToolResultErrorParam(
                        BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode
                                    .INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
            )
        assertThat(betaTextEditorCodeExecutionToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(betaTextEditorCodeExecutionToolResultBlockParam.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTextEditorCodeExecutionToolResultBlockParam =
            BetaTextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    BetaTextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(
                            BetaTextEditorCodeExecutionToolResultErrorParam.ErrorCode
                                .INVALID_TOOL_INPUT
                        )
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaTextEditorCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTextEditorCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BetaTextEditorCodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedBetaTextEditorCodeExecutionToolResultBlockParam)
            .isEqualTo(betaTextEditorCodeExecutionToolResultBlockParam)
    }
}
