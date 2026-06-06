// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolResultErrorParamTest {

    @Test
    fun create() {
        val betaToolSearchToolResultErrorParam =
            BetaToolSearchToolResultErrorParam.builder()
                .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        assertThat(betaToolSearchToolResultErrorParam.errorCode())
            .isEqualTo(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
        assertThat(betaToolSearchToolResultErrorParam.errorMessage()).contains("error_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolResultErrorParam =
            BetaToolSearchToolResultErrorParam.builder()
                .errorCode(BetaToolSearchToolResultErrorParam.ErrorCode.INVALID_TOOL_INPUT)
                .errorMessage("error_message")
                .build()

        val roundtrippedBetaToolSearchToolResultErrorParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolResultErrorParam),
                jacksonTypeRef<BetaToolSearchToolResultErrorParam>(),
            )

        assertThat(roundtrippedBetaToolSearchToolResultErrorParam)
            .isEqualTo(betaToolSearchToolResultErrorParam)
    }
}
