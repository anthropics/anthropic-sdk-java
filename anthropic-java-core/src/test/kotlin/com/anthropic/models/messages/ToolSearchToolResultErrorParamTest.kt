// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolResultErrorParamTest {

    @Test
    fun create() {
        val toolSearchToolResultErrorParam =
            ToolSearchToolResultErrorParam.builder()
                .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(toolSearchToolResultErrorParam.errorCode())
            .isEqualTo(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
        assertThat(toolSearchToolResultErrorParam.errorMessage()).contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolResultErrorParam =
            ToolSearchToolResultErrorParam.builder()
                .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedToolSearchToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolResultErrorParam),
                jacksonTypeRef<ToolSearchToolResultErrorParam>(),
            )

        assertThat(roundtrippedToolSearchToolResultErrorParam)
            .isEqualTo(toolSearchToolResultErrorParam)
    }
}
