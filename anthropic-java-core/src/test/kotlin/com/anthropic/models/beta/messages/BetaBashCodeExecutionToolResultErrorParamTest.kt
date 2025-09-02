// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBashCodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val betaBashCodeExecutionToolResultErrorParam =
            BetaBashCodeExecutionToolResultErrorParam.builder()
                .errorCode(BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaBashCodeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBashCodeExecutionToolResultErrorParam =
            BetaBashCodeExecutionToolResultErrorParam.builder()
                .errorCode(BetaBashCodeExecutionToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaBashCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBashCodeExecutionToolResultErrorParam),
                jacksonTypeRef<BetaBashCodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedBetaBashCodeExecutionToolResultErrorParam)
            .isEqualTo(betaBashCodeExecutionToolResultErrorParam)
    }
}
