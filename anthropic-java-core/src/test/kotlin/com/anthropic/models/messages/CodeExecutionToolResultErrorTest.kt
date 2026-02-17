// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val codeExecutionToolResultError =
            CodeExecutionToolResultError.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(codeExecutionToolResultError.errorCode())
            .isEqualTo(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultError =
            CodeExecutionToolResultError.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultError),
                jacksonTypeRef<CodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultError).isEqualTo(codeExecutionToolResultError)
    }
}
