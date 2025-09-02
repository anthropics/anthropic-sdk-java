// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val betaBashCodeExecutionToolResultError =
            BetaBashCodeExecutionToolResultError.builder()
                .errorCode(BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaBashCodeExecutionToolResultError.errorCode())
            .isEqualTo(BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionToolResultError =
            BetaBashCodeExecutionToolResultError.builder()
                .errorCode(BetaBashCodeExecutionToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaBashCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionToolResultError),
                jacksonTypeRef<BetaBashCodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionToolResultError)
            .isEqualTo(betaBashCodeExecutionToolResultError)
    }
}
