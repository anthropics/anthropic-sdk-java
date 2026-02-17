// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchToolResultErrorBlockParamTest {

    @Test
    fun create() {
        val webFetchToolResultErrorBlockParam =
            WebFetchToolResultErrorBlockParam.builder()
                .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(webFetchToolResultErrorBlockParam.errorCode())
            .isEqualTo(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchToolResultErrorBlockParam =
            WebFetchToolResultErrorBlockParam.builder()
                .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedWebFetchToolResultErrorBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchToolResultErrorBlockParam),
                jacksonTypeRef<WebFetchToolResultErrorBlockParam>(),
            )

        assertThat(roundtrippedWebFetchToolResultErrorBlockParam)
            .isEqualTo(webFetchToolResultErrorBlockParam)
    }
}
