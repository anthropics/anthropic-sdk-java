// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchToolResultErrorBlockTest {

    @Test
    fun create() {
        val betaWebFetchToolResultErrorBlock =
            BetaWebFetchToolResultErrorBlock.builder()
                .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaWebFetchToolResultErrorBlock.errorCode())
            .isEqualTo(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchToolResultErrorBlock =
            BetaWebFetchToolResultErrorBlock.builder()
                .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaWebFetchToolResultErrorBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchToolResultErrorBlock),
                jacksonTypeRef<BetaWebFetchToolResultErrorBlock>(),
            )

        assertThat(roundtrippedBetaWebFetchToolResultErrorBlock)
            .isEqualTo(betaWebFetchToolResultErrorBlock)
    }
}
