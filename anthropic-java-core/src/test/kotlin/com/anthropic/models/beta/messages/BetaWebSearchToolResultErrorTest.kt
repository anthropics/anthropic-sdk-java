// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchToolResultErrorTest {

    @Test
    fun create() {
        val betaWebSearchToolResultError =
            BetaWebSearchToolResultError.builder()
                .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaWebSearchToolResultError.errorCode())
            .isEqualTo(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolResultError =
            BetaWebSearchToolResultError.builder()
                .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaWebSearchToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolResultError),
                jacksonTypeRef<BetaWebSearchToolResultError>(),
            )

        assertThat(roundtrippedBetaWebSearchToolResultError).isEqualTo(betaWebSearchToolResultError)
    }
}
