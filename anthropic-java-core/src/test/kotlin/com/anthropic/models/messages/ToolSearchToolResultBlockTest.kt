package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ToolSearchToolResultBlockTest {

    @Test
    fun create() {
        val toolSearchToolResultBlock =
            ToolSearchToolResultBlock.builder()
                .content(
                    ToolSearchToolResultError.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        assertThat(toolSearchToolResultBlock.content())
            .isEqualTo(
                ToolSearchToolResultBlock.Content.ofToolSearchToolResultError(
                    ToolSearchToolResultError.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
            )
        assertThat(toolSearchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val toolSearchToolResultBlock =
            ToolSearchToolResultBlock.builder()
                .content(
                    ToolSearchToolResultError.builder()
                        .errorCode(ToolSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .errorMessage("error_message")
                        .build()
                )
                .toolUseId("srvtoolu_c")
                .build()

        val roundtrippedToolSearchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(toolSearchToolResultBlock),
                jacksonTypeRef<ToolSearchToolResultBlock>(),
            )

        assertThat(roundtrippedToolSearchToolResultBlock).isEqualTo(toolSearchToolResultBlock)
    }
}
