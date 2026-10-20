package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.models.ModelRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ModelServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val modelServiceAsync = client.beta().models()

        val betaModelInfoFuture =
            modelServiceAsync.retrieve(
                ModelRetrieveParams.builder()
                    .modelId("model_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val betaModelInfo = betaModelInfoFuture.get()
        betaModelInfo.validate()
    }

    @Test
    fun list() {
        val modelServiceAsync = client.beta().models()

        val pageFuture = modelServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
