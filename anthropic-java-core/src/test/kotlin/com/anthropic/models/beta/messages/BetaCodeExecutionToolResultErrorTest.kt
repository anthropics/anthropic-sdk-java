// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionToolResultErrorTest {

    @Test
    fun create() {
        val betaCodeExecutionToolResultError =
            BetaCodeExecutionToolResultError.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaCodeExecutionToolResultError.errorCode())
            .isEqualTo(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionToolResultError =
            BetaCodeExecutionToolResultError.builder()
                .errorCode(BetaCodeExecutionToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaCodeExecutionToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionToolResultError),
                jacksonTypeRef<BetaCodeExecutionToolResultError>(),
            )

        assertThat(roundtrippedBetaCodeExecutionToolResultError)
            .isEqualTo(betaCodeExecutionToolResultError)
    }
}
