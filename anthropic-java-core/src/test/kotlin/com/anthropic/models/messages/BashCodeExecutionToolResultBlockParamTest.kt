// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionToolResultBlockParamTest {

    @Test
    fun create() {
        val bashCodeExecutionToolResultBlockParam =
            BashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BashCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(bashCodeExecutionToolResultBlockParam.content())
            .isEqualTo(
                BashCodeExecutionToolResultBlockParam.Content
                    .ofBashCodeExecutionToolResultErrorParam(
                        BashCodeExecutionToolResultErrorParam.builder()
                            .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                            .build()
                    )
            )
        assertThat(bashCodeExecutionToolResultBlockParam.toolUseId())
            .isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(bashCodeExecutionToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultBlockParam =
            BashCodeExecutionToolResultBlockParam.builder()
                .content(
                    BashCodeExecutionToolResultErrorParam.builder()
                        .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedBashCodeExecutionToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultBlockParam),
                jacksonTypeRef<BashCodeExecutionToolResultBlockParam>(),
            )

        assertThat(roundtrippedBashCodeExecutionToolResultBlockParam)
            .isEqualTo(bashCodeExecutionToolResultBlockParam)
    }
}
