// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebSearchToolResultBlockTest {

    @Test
    fun create() {
        val webSearchToolResultBlock =
            WebSearchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebSearchToolResultError.builder()
                        .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(webSearchToolResultBlock.caller())
            .isEqualTo(WebSearchToolResultBlock.Caller.ofDirect(DirectCaller.builder().build()))
        assertThat(webSearchToolResultBlock.content())
            .isEqualTo(
                WebSearchToolResultBlockContent.ofError(
                    WebSearchToolResultError.builder()
                        .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(webSearchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webSearchToolResultBlock =
            WebSearchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebSearchToolResultError.builder()
                        .errorCode(WebSearchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedWebSearchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webSearchToolResultBlock),
                jacksonTypeRef<WebSearchToolResultBlock>(),
            )

        assertThat(roundtrippedWebSearchToolResultBlock).isEqualTo(webSearchToolResultBlock)
    }
}
