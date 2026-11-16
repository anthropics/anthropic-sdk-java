package com.anthropic.models.beta.agents.versions

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionListParamsTest {

    @Test
    fun create() {
        VersionListParams.builder()
            .agentId("agent_011CZkYpogX7uDKUyvBTophP")
            .limit(0)
            .page("page")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = VersionListParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        assertThat(params._pathParam(0)).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            VersionListParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .limit(0)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("computer-use-2024-10-22", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = VersionListParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            VersionListParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
                .limit(0)
                .page("page")
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("limit", "0").put("page", "page").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = VersionListParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
