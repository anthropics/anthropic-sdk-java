// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DreamServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.create(
                DreamCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addMemoryStoreInput("x")
                    .model("string")
                    .instructions("x")
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.retrieve(
                DreamRetrieveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamService = client.beta().dreams()

        val page = dreamService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.archive(
                DreamArchiveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun cancel() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.cancel(
                DreamCancelParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaDream.validate()
    }
}
