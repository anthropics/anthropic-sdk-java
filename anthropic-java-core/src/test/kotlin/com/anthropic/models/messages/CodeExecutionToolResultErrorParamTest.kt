// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val codeExecutionToolResultErrorParam =
            CodeExecutionToolResultErrorParam.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(codeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val codeExecutionToolResultErrorParam =
            CodeExecutionToolResultErrorParam.builder()
                .errorCode(CodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(codeExecutionToolResultErrorParam),
                jacksonTypeRef<CodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedCodeExecutionToolResultErrorParam)
            .isEqualTo(codeExecutionToolResultErrorParam)
    }
}
