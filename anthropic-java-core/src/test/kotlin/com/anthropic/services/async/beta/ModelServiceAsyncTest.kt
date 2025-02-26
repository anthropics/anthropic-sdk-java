// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.BetaModelRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class ModelServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelServiceAsync = client.beta().models()

        val betaModelInfoFuture =
            modelServiceAsync.retrieve(
                BetaModelRetrieveParams.builder().modelId("model_id").build()
            )

        val betaModelInfo = betaModelInfoFuture.get()
        betaModelInfo.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelServiceAsync = client.beta().models()

        val pageFuture = modelServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
