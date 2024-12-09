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
                .type(ToolResultBlockParam.Type.TOOL_RESULT)
                .content(ToolResultBlockParam.Content.ofString("string"))
                .isError(true)
                .build()
        assertThat(toolResultBlockParam).isNotNull
        assertThat(toolResultBlockParam.toolUseId()).isEqualTo("tool_use_id")
        assertThat(toolResultBlockParam.type()).isEqualTo(ToolResultBlockParam.Type.TOOL_RESULT)
        assertThat(toolResultBlockParam.content())
            .contains(ToolResultBlockParam.Content.ofString("string"))
        assertThat(toolResultBlockParam.isError()).contains(true)
    }
}
