// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchToolResultErrorBlockTest {

    @Test
    fun create() {
        val webFetchToolResultErrorBlock =
            WebFetchToolResultErrorBlock.builder()
                .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(webFetchToolResultErrorBlock.errorCode())
            .isEqualTo(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchToolResultErrorBlock =
            WebFetchToolResultErrorBlock.builder()
                .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedWebFetchToolResultErrorBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchToolResultErrorBlock),
                jacksonTypeRef<WebFetchToolResultErrorBlock>(),
            )

        assertThat(roundtrippedWebFetchToolResultErrorBlock).isEqualTo(webFetchToolResultErrorBlock)
    }
}
