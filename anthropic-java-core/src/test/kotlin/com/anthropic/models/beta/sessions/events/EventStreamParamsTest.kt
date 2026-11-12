package com.anthropic.models.beta.sessions.events

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventStreamParamsTest {

    @Test
    fun create() {
        EventStreamParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .build()
    }

    @Test
    fun pathParams() {
        val params = EventStreamParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            EventStreamParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
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
        val params = EventStreamParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            EventStreamParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(QueryParams.builder().put("event_deltas[]", "agent.message").build())
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = EventStreamParams.builder().sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
