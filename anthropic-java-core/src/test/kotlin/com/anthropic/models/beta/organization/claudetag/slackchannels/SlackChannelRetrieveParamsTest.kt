package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackChannelRetrieveParamsTest {

    @Test
    fun create() {
        SlackChannelRetrieveParams.builder()
            .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
            .includeInherited(true)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            SlackChannelRetrieveParams.builder().id("slch_01SvpyRQK3nN6eH9uhRA7n1q").build()

        assertThat(params._pathParam(0)).isEqualTo("slch_01SvpyRQK3nN6eH9uhRA7n1q")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SlackChannelRetrieveParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .includeInherited(true)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "experimental-claude-tag-2026-10-21")
                            .joinToString(","),
                    )
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            SlackChannelRetrieveParams.builder().id("slch_01SvpyRQK3nN6eH9uhRA7n1q").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            SlackChannelRetrieveParams.builder()
                .id("slch_01SvpyRQK3nN6eH9uhRA7n1q")
                .includeInherited(true)
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("include_inherited", "true").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params =
            SlackChannelRetrieveParams.builder().id("slch_01SvpyRQK3nN6eH9uhRA7n1q").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
