package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.dreams.BetaOutputBehaviorCreateNew
import com.anthropic.models.beta.dreams.DreamArchiveParams
import com.anthropic.models.beta.dreams.DreamCancelParams
import com.anthropic.models.beta.dreams.DreamCreateParams
import com.anthropic.models.beta.dreams.DreamRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class DreamServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun create() {
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.create(
                DreamCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .addMemoryStoreInput("x")
                    .model("string")
                    .instructions("x")
                    .outputBehavior(BetaOutputBehaviorCreateNew.builder().build())
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun retrieve() {
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.retrieve(
                DreamRetrieveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun list() {
        val dreamService = client.beta().dreams()

        val page = dreamService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.archive(
                DreamArchiveParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaDream.validate()
    }

    @Test
    fun cancel() {
        val dreamService = client.beta().dreams()

        val betaDream =
            dreamService.cancel(
                DreamCancelParams.builder()
                    .dreamId("dream_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        betaDream.validate()
    }
}
