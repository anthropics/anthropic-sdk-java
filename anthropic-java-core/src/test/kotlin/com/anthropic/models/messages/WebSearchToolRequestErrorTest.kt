// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchToolRequestErrorTest {

    @Test
    fun create() {
        val webSearchToolRequestError =
            WebSearchToolRequestError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        assertThat(webSearchToolRequestError.errorCode())
            .isEqualTo(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolRequestError =
            WebSearchToolRequestError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        val roundtrippedWebSearchToolRequestError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolRequestError),
                jacksonTypeRef<WebSearchToolRequestError>(),
            )

        assertThat(roundtrippedWebSearchToolRequestError).isEqualTo(webSearchToolRequestError)
    }
}
