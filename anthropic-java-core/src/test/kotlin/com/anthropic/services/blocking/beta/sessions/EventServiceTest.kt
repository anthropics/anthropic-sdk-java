package com.anthropic.services.blocking.beta.sessions

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaType
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.events.EventStreamParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EventServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val eventService = client.beta().sessions().events()

        val page = eventService.list("sesn_011CZkZAtmR3yMPDzynEDxu7")

        page.response().validate()
    }

    @Test
    fun send() {
        val eventService = client.beta().sessions().events()

        val betaManagedAgentsSendSessionEvents =
            eventService.send(
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
            )

        betaManagedAgentsSendSessionEvents.validate()
    }

    @Test
    fun streamStreaming() {
        val eventService = client.beta().sessions().events()

        val betaManagedAgentsStreamSessionEventsStreamResponse =
            eventService.streamStreaming(
                EventStreamParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .addEventDelta(BetaManagedAgentsDeltaType.AGENT_MESSAGE)
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaManagedAgentsStreamSessionEventsStreamResponse.use {
            betaManagedAgentsStreamSessionEventsStreamResponse.stream().forEach {
                betaManagedAgentsStreamSessionEvents ->
                betaManagedAgentsStreamSessionEvents.validate()
            }
        }
    }
}
