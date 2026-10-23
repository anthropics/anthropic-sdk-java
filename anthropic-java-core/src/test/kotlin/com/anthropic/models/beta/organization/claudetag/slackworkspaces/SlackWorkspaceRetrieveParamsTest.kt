package com.anthropic.models.beta.organization.claudetag.slackworkspaces

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SlackWorkspaceRetrieveParamsTest {

    @Test
    fun create() {
        SlackWorkspaceRetrieveParams.builder()
            .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
            .includeInherited(true)
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            SlackWorkspaceRetrieveParams.builder().id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ").build()

        assertThat(params._pathParam(0)).isEqualTo("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            SlackWorkspaceRetrieveParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
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
            SlackWorkspaceRetrieveParams.builder().id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            SlackWorkspaceRetrieveParams.builder()
                .id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ")
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
            SlackWorkspaceRetrieveParams.builder().id("slws_01Hq8TzW5mKc2YbX7vNd4RfJ").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
