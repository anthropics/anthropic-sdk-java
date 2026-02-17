// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BashCodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val bashCodeExecutionToolResultError =
            BashCodeExecutionToolResultError.builder()
                .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(bashCodeExecutionToolResultError.errorCode())
            .isEqualTo(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val bashCodeExecutionToolResultError =
            BashCodeExecutionToolResultError.builder()
                .errorCode(BashCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBashCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(bashCodeExecutionToolResultError),
                jacksonTypeRef<BashCodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedBashCodeExecutionToolResultError)
            .isEqualTo(bashCodeExecutionToolResultError)
    }
}
