// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchToolRequestErrorTest {

    @Test
    fun create() {
        val webSearchToolRequestError =
            WebSearchToolRequestError.builder()
                .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        assertThat(webSearchToolRequestError.errorCode())
            .isEqualTo(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolRequestError =
            WebSearchToolRequestError.builder()
                .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                .build()

        val roundtrippedWebSearchToolRequestError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolRequestError),
                jacksonTypeRef<WebSearchToolRequestError>(),
            )

        assertThat(roundtrippedWebSearchToolRequestError).isEqualTo(webSearchToolRequestError)
    }
}
