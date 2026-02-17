// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val codeExecutionToolResultBlockParam =
            CodeExecutionToolResultBlockParam.builder()
                .content(
                    CodeExecutionToolResultErrorParam.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(codeExecutionToolResultBlockParam.content())
            .isEqualTo(
                CodeExecutionToolResultBlockParamContent.ofErrorParam(
                    CodeExecutionToolResultErrorParam.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(codeExecutionToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(codeExecutionToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlockParam =
            CodeExecutionToolResultBlockParam.builder()
                .content(
                    CodeExecutionToolResultErrorParam.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlockParam),
                jacksonTypeRef<CodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlockParam)
            .isEqualTo(codeExecutionToolResultBlockParam)
    }
}
