// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.sessions

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.events.EventStreamParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EventServiceAsyncTest {

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val eventServiceAsync = client.beta().sessions().events()

        val pageFuture = eventServiceAsync.list("sesn_011CZkZAtmR3yMPDzynEDxu7")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun send() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val eventServiceAsync = client.beta().sessions().events()

        val betaManagedAgentsSendSessionEventsFuture =
            eventServiceAsync.send(
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
            )

        val betaManagedAgentsSendSessionEvents = betaManagedAgentsSendSessionEventsFuture.get()
        betaManagedAgentsSendSessionEvents.validate()
    }

    @Test
    fun streamStreaming() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val eventServiceAsync = client.beta().sessions().events()

        val betaManagedAgentsStreamSessionEventsStreamResponse =
            eventServiceAsync.streamStreaming(
                EventStreamParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val onCompleteFuture =
            betaManagedAgentsStreamSessionEventsStreamResponse
                .subscribe { betaManagedAgentsStreamSessionEvents ->
                    betaManagedAgentsStreamSessionEvents.validate()
                }
                .onCompleteFuture()
        onCompleteFuture.get()
    }
}
