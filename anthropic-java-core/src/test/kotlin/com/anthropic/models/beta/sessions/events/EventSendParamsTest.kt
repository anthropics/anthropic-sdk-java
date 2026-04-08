// File generated from our OpenAPI spec by Stainless.

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
            .addUserMessageEvent(
                listOf(
                    BetaManagedAgentsUserMessageEventParams.Content.ofText(
                        BetaManagedAgentsTextBlock.builder()
                            .text("Where is my order #1234?")
                            .type(BetaManagedAgentsTextBlock.Type.TEXT)
                            .build()
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
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
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
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
                        )
                    )
                )
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
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
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
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
                .addUserMessageEvent(
                    listOf(
                        BetaManagedAgentsUserMessageEventParams.Content.ofText(
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
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
                        .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
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
                            BetaManagedAgentsTextBlock.builder()
                                .text("Where is my order #1234?")
                                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                                .build()
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
                        .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                        .build()
                )
            )
    }
}
