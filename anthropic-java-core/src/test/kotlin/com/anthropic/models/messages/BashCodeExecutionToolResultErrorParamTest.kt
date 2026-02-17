// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val bashCodeExecutionToolResultErrorParam =
            BashCodeExecutionToolResultErrorParam.builder()
                .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(bashCodeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultErrorParam =
            BashCodeExecutionToolResultErrorParam.builder()
                .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBashCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultErrorParam),
                jacksonTypeRef<BashCodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedBashCodeExecutionToolResultErrorParam)
            .isEqualTo(bashCodeExecutionToolResultErrorParam)
    }
}
