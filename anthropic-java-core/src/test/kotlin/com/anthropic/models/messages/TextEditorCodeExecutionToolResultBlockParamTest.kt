// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TextEditorCodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val textEditorCodeExecutionToolResultBlockParam =
            TextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    TextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(textEditorCodeExecutionToolResultBlockParam.content())
            .isEqualTo(
                TextEditorCodeExecutionToolResultBlockParam.Content
                    .ofTextEditorCodeExecutionToolResultErrorParam(
                        TextEditorCodeExecutionToolResultErrorParam.builder()
                            .errorCode(
                                TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                            )
                            .errorMessage("error_message")
                            .build()
                    )
            )
        assertThat(textEditorCodeExecutionToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(textEditorCodeExecutionToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val textEditorCodeExecutionToolResultBlockParam =
            TextEditorCodeExecutionToolResultBlockParam.builder()
                .content(
                    TextEditorCodeExecutionToolResultErrorParam.builder()
                        .errorCode(TextEditorCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedTextEditorCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(textEditorCodeExecutionToolResultBlockParam),
                jacksonTypeRef<TextEditorCodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedTextEditorCodeExecutionToolResultBlockParam)
            .isEqualTo(textEditorCodeExecutionToolResultBlockParam)
    }
}
