package com.anthropic.models.beta.agents

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AgentArchiveParamsTest {

    @Test
    fun create() {
        AgentArchiveParams.builder()
            .agentId("agent_011CZkYpogX7uDKUyvBTophP")
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = AgentArchiveParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        assertThat(params._pathParam(0)).isEqualTo("agent_011CZkYpogX7uDKUyvBTophP")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            AgentArchiveParams.builder()
                .agentId("agent_011CZkYpogX7uDKUyvBTophP")
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
        val params = AgentArchiveParams.builder().agentId("agent_011CZkYpogX7uDKUyvBTophP").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
