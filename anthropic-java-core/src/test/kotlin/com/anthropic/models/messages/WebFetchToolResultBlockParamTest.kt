// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchToolResultBlockParamTest {

    @Test
    fun create() {
        val webFetchToolResultBlockParam =
            WebFetchToolResultBlockParam.builder()
                .content(
                    WebFetchToolResultErrorBlockParam.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        assertThat(webFetchToolResultBlockParam.content())
            .isEqualTo(
                WebFetchToolResultBlockParam.Content.ofWebFetchToolResultErrorBlockParam(
                    WebFetchToolResultErrorBlockParam.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
            )
        assertThat(webFetchToolResultBlockParam.toolUseId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
        assertThat(webFetchToolResultBlockParam.cacheControl())
            .contains(CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build())
        assertThat(webFetchToolResultBlockParam.caller())
            .contains(WebFetchToolResultBlockParam.Caller.ofDirect(DirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchToolResultBlockParam =
            WebFetchToolResultBlockParam.builder()
                .content(
                    WebFetchToolResultErrorBlockParam.builder()
                        .errorCode(WebFetchToolResultErrorCode.INVALID_TOOL_INPUT)
                        .build()
                )
                .toolUseId("srvtoolu_SQfNkl1n_JR_")
                .cacheControl(
                    CacheControlEphemeral.builder().ttl(CacheControlEphemeral.Ttl.TTL_5M).build()
                )
                .caller(DirectCaller.builder().build())
                .build()

        val roundtrippedWebFetchToolResultBlockParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchToolResultBlockParam),
                jacksonTypeRef<WebFetchToolResultBlockParam>(),
            )

        assertThat(roundtrippedWebFetchToolResultBlockParam).isEqualTo(webFetchToolResultBlockParam)
    }
}
