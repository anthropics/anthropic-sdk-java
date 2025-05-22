// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionToolResultErrorParamTest {

    @Test
    fun create() {
        val betaCodeExecutionToolResultErrorParam =
            BetaCodeExecutionToolResultErrorParam.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaCodeExecutionToolResultErrorParam.errorCode())
            .isEqualTo(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultErrorParam =
            BetaCodeExecutionToolResultErrorParam.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaCodeExecutionToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultErrorParam),
                jacksonTypeRef<BetaCodeExecutionToolResultErrorParam>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultErrorParam)
            .isEqualTo(betaCodeExecutionToolResultErrorParam)
    }
}
