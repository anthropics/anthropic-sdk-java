// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolResultBlockParamTest {

    @Test
    fun create() {
        val toolResultBlockParam =
            ToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(CacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()

        assertThat(toolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(toolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
        assertThat(toolResultBlockParam.content())
            .contains(ToolResultBlockParam.Content.ofString("string"))
        assertThat(toolResultBlockParam.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolResultBlockParam =
            ToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(CacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()

        val roundtrippedToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolResultBlockParam),
                jacksonTypeRef<ToolResultBlockParam>(),
            )

        assertThat(roundtrippedToolResultBlockParam).isEqualTo(toolResultBlockParam)
    }
}
