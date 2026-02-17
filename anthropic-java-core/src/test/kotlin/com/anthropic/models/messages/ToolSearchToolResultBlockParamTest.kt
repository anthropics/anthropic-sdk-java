// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolResultBlockParamTest {

    @Test
    fun create() {
        val toolSearchToolResultBlockParam =
            ToolSearchToolResultBlockParam.builder()
                .content(
                    ToolSearchToolResultErrorParam.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        assertThat(toolSearchToolResultBlockParam.content())
            .isEqualTo(
                ToolSearchToolResultBlockParam.Content.ofToolSearchToolResultErrorParam(
                    ToolSearchToolResultErrorParam.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(toolSearchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(toolSearchToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolResultBlockParam =
            ToolSearchToolResultBlockParam.builder()
                .content(
                    ToolSearchToolResultErrorParam.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .build()

        val roundtrippedToolSearchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolResultBlockParam),
                jacksonTypeRef<ToolSearchToolResultBlockParam>(),
            )

        assertThat(roundtrippedToolSearchToolResultBlockParam)
            .isEqualTo(toolSearchToolResultBlockParam)
    }
}
