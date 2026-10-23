package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionToolResultBlockTest {

    @Test
    fun create() {
        val codeExecutionToolResultBlock =
            CodeExecutionToolResultBlock.builder()
                .content(
                    CodeExecutionToolResultError.of(
                        CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        assertThat(codeExecutionToolResultBlock.content())
            .isEqualTo(
                CodeExecutionToolResultBlockContent.ofError(
                    CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                )
            )
        assertThat(codeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlock =
            CodeExecutionToolResultBlock.builder()
                .content(
                    CodeExecutionToolResultError.of(
                        CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT
                    )
                )
                .toolUseId("srvtoolu_c")
                .build()

        val roundtrippedCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlock),
                jacksonTypeRef<CodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlock).isEqualTo(codeExecutionToolResultBlock)
    }
}
