// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolResultErrorTest {

    @Test
    fun create() {
        val betaToolSearchToolResultError =
            BetaToolSearchToolResultError.builder()
                .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(betaToolSearchToolResultError.errorCode())
            .isEqualTo(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
        assertThat(betaToolSearchToolResultError.errorMessage()).contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolResultError =
            BetaToolSearchToolResultError.builder()
                .errorCode(BetaToolSearchToolResultError.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedBetaToolSearchToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolResultError),
                jacksonTypeRef<BetaToolSearchToolResultError>(),
            )

        assertThat(roundtrippedBetaToolSearchToolResultError)
            .isEqualTo(betaToolSearchToolResultError)
    }
}
