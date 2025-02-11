// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ToolResultBlockParamTest {

    @Test
    fun createToolResultBlockParam() {
        val toolResultBlockParam =
            ToolResultBlockParam.builder()
                .toolUseId("tool_use_id")
                .cacheControl(CacheControlEphemeral.builder().build())
                .content("string")
                .isError(true)
                .build()
        assertThat(toolResultBlockParam).isNotNull
        assertThat(toolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(toolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().build())
        assertThat(toolResultBlockParam.content())
            .contains(ToolResultBlockParam.Content.ofString("string"))
        assertThat(toolResultBlockParam.isError()).contains(true)
    }
}
