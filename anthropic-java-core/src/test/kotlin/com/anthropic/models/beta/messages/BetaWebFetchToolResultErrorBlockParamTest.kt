// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWebFetchToolResultErrorBlockParamTest {

    @Test
    fun create() {
        val betaWebFetchToolResultErrorBlockParam =
            BetaWebFetchToolResultErrorBlockParam.builder()
                .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(betaWebFetchToolResultErrorBlockParam.errorCode())
            .isEqualTo(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebFetchToolResultErrorBlockParam =
            BetaWebFetchToolResultErrorBlockParam.builder()
                .errorCode(BetaWebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedBetaWebFetchToolResultErrorBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebFetchToolResultErrorBlockParam),
                jacksonTypeRef<BetaWebFetchToolResultErrorBlockParam>(),
            )

        assertThat(roundtrippedBetaWebFetchToolResultErrorBlockParam)
            .isEqualTo(betaWebFetchToolResultErrorBlockParam)
    }
}
