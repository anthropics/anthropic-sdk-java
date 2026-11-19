package com.anthropic.services.async.beta.sessions

import com.anthropic.CompletionExecutorAssertingInterceptor
import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaType
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.events.EventStreamParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EventServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .addInterceptor(CompletionExecutorAssertingInterceptor())
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val eventServiceAsync =
            client.beta().sessions().events().withOptions { it.putQueryParam("beta", "true") }

        val pageFuture = eventServiceAsync.list("sesn_011CZkZAtmR3yMPDzynEDxu7")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun send() {
        val eventServiceAsync =
            client.beta().sessions().events().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsSendSessionEventsFuture =
            eventServiceAsync.send(
                EventSendParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addUserMessageEvent(
                        listOf(
                            BetaManagedAgentsUserMessageEventParams.Content.ofText(
                                "Where is my order #1234?"
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
        val eventServiceAsync =
            client.beta().sessions().events().withOptions { it.putQueryParam("beta", "true") }

        val betaManagedAgentsStreamSessionEventsStreamResponse =
            eventServiceAsync.streamStreaming(
                EventStreamParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
                    .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
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
