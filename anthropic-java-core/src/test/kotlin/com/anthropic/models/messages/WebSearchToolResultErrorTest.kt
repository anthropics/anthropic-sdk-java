// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchToolResultErrorTest {

    @Test
    fun create() {
        val webSearchToolResultError =
            WebSearchToolResultError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        assertThat(webSearchToolResultError.errorCode())
            .isEqualTo(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultError =
            WebSearchToolResultError.of(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)

        val roundtrippedWebSearchToolResultError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultError),
                jacksonTypeRef<WebSearchToolResultError>(),
            )

        assertThat(roundtrippedWebSearchToolResultError).isEqualTo(webSearchToolResultError)
    }
}
