// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebSearchToolRequestErrorTest {

    @Test
    fun create() {
        val betaWebSearchToolRequestError =
            BetaWebSearchToolRequestError.builder()
                .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaWebSearchToolRequestError.errorCode())
            .isEqualTo(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebSearchToolRequestError =
            BetaWebSearchToolRequestError.builder()
                .errorCode(BetaWebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaWebSearchToolRequestError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebSearchToolRequestError),
                jacksonTypeRef<BetaWebSearchToolRequestError>(),
            )

        assertThat(roundtrippedBetaWebSearchToolRequestError)
            .isEqualTo(betaWebSearchToolRequestError)
    }
}
