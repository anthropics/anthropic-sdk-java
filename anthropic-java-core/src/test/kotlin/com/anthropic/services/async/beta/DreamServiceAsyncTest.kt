package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.dreams.BetaOutputBehaviorCreateNew
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DreamServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.create(
                DreamCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addMemoryStoreInput("x")
                    .model("string")
                    .instructions("x")
                    .outputBehavior(BetaOutputBehaviorCreateNew.builder().build())
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun retrieve() {
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.retrieve(
                DreamRetrieveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun list() {
        val dreamServiceAsync = client.beta().dreams()

        val pageFuture = dreamServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.archive(
                DreamArchiveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }

    @Test
    fun cancel() {
        val dreamServiceAsync = client.beta().dreams()

        val betaDreamFuture =
            dreamServiceAsync.cancel(
                DreamCancelParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaDream = betaDreamFuture.get()
        betaDream.validate()
    }
}
