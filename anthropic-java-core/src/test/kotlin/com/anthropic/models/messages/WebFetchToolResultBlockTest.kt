// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchToolResultBlockTest {

    @Test
    fun create() {
        val webFetchToolResultBlock =
            WebFetchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebFetchToolResultErrorBlock.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        assertThat(webFetchToolResultBlock.caller())
            .isEqualTo(WebFetchToolResultBlock.Caller.ofDirect(DirectCaller.builder().build()))
        assertThat(webFetchToolResultBlock.content())
            .isEqualTo(
                WebFetchToolResultBlock.Content.ofWebFetchToolResultErrorBlock(
                    WebFetchToolResultErrorBlock.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(webFetchToolResultBlock.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchToolResultBlock =
            WebFetchToolResultBlock.builder()
                .caller(DirectCaller.builder().build())
                .content(
                    WebFetchToolResultErrorBlock.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .build()

        val roundtrippedWebFetchToolResultBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchToolResultBlock),
                jacksonTypeRef<WebFetchToolResultBlock>(),
            )

        assertThat(roundtrippedWebFetchToolResultBlock).isEqualTo(webFetchToolResultBlock)
    }
}
