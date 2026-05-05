// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.sessions.threads

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.threads.events.EventListParams
import com.anthropic.models.beta.sessions.threads.events.EventStreamParams
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class EventServiceTest {

    @Disabled("buildURL drops path-level query params (SDK-4349)")
    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val eventService = client.beta().sessions().threads().events()

        val page =
            eventService.list(
                EventListParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .build()
            )

        page.response().validate()
    }

    @Test
    fun streamStreaming() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val eventService = client.beta().sessions().threads().events()

        val betaManagedAgentsStreamSessionThreadEventsStreamResponse =
            eventService.streamStreaming(
                EventStreamParams.builder()
                    .sessionId("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .threadId("sthr_011CZkZVWa6oIjw0rgXZpnBt")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaManagedAgentsStreamSessionThreadEventsStreamResponse.use {
            betaManagedAgentsStreamSessionThreadEventsStreamResponse.stream().forEach {
                betaManagedAgentsStreamSessionThreadEvents ->
                betaManagedAgentsStreamSessionThreadEvents.validate()
            }
        }
    }
}
