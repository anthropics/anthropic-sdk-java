// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolResultErrorTest {

    @Test
    fun create() {
        val toolSearchToolResultError =
            ToolSearchToolResultError.builder()
                .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(toolSearchToolResultError.errorCode())
            .isEqualTo(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
        assertThat(toolSearchToolResultError.errorMessage()).contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolResultError =
            ToolSearchToolResultError.builder()
                .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedToolSearchToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolResultError),
                jacksonTypeRef<ToolSearchToolResultError>(),
            )

        assertThat(roundtrippedToolSearchToolResultError).isEqualTo(toolSearchToolResultError)
    }
}
