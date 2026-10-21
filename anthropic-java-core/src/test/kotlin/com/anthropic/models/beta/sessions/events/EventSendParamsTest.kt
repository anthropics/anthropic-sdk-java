package com.anthropic.models.beta.sessions.events

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EventSendParamsTest {

    @Test
    fun create() {
        EventSendParams.builder()
            .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
            .addUserMessageEvent(
                listOf(
                    BetaManagedAgentsUserMessageEventParams.Content.ofText(
                        "Where is my order #1234?"
                    )
                )
            )
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            EventSendParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            "Where is my order #1234?"
                        )
                    )
                )
                .build()

        assertThat(params._pathParam(0)).isEqualTo("sesn_011CZkZAtmR3yMPDzynEDxu7")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            EventSendParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            "Where is my order #1234?"
                        )
                    )
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put(
                        "anthropic-beta",
                        listOf("message-batches-2024-09-24", "managed-agents-2026-04-01")
                            .joinToString(","),
                    )
                    .put("anthropic-workspace-id", "wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            EventSendParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            "Where is my order #1234?"
                        )
                    )
                )
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            EventSendParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            "Where is my order #1234?"
                        )
                    )
                )
                .build()

        val body = params._body()

        assertThat(body.events())
            .containsExactly(
                BetaManagedAgentsEventParams.ofUserMessage(
                    BetaManagedAgentsUserMessageEventParams.builder()
                        .addTextContent("Where is my order #1234?")
                        .build()
                )
            )
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            EventSendParams.builder()
                .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            "Where is my order #1234?"
                        )
                    )
                )
                .build()

        val body = params._body()

        assertThat(body.events())
            .containsExactly(
                BetaManagedAgentsEventParams.ofUserMessage(
                    BetaManagedAgentsUserMessageEventParams.builder()
                        .addTextContent("Where is my order #1234?")
                        .build()
                )
            )
    }
}
