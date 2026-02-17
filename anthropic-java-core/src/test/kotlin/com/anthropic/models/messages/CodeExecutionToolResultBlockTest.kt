// File generated from our OpenAPI spec by Stainless.

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
                    CodeExecutionToolResultError.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(codeExecutionToolResultBlock.content())
            .isEqualTo(
                CodeExecutionToolResultBlockContent.ofError(
                    CodeExecutionToolResultError.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(codeExecutionToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultBlock =
            CodeExecutionToolResultBlock.builder()
                .content(
                    CodeExecutionToolResultError.builder()
                        .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedCodeExecutionToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultBlock),
                jacksonTypeRef<CodeExecutionToolResultBlock>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultBlock).isEqualTo(codeExecutionToolResultBlock)
    }
}
