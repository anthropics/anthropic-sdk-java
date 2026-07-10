// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DreamServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.create(
                DreamCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .addMemoryStoreInput("x")
                    .model("string")
                    .instructions("x")
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.retrieve(
                DreamRetrieveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamServiceAsync = client.beta().dreams()

        val pageFuture = dreamServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.archive(
                DreamArchiveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun cancel() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.cancel(
                DreamCancelParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }
}
