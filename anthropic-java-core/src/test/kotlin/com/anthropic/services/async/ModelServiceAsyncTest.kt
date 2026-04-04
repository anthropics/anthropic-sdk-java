// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.models.ModelRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlinx.coroutines.runBlocking

@ExtendWith(TestServerExtension::class)
internal class ModelServiceAsyncTest {

    @Test
    fun retrieve() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val modelServiceAsync = client.models()

            val modelInfo =
                modelServiceAsync.retrieve(
                    ModelRetrieveParams.builder()
                        .modelId("model_id")
                        .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                        .build()
                )

            modelInfo.validate()
        }
    }

    @Test
    fun list() {
        runBlocking {
            val client =
                AnthropicOkHttpClientAsync.builder()
                    .baseUrl(TestServerExtension.BASE_URL)
                    .apiKey("my-anthropic-api-key")
                    .build()
            val modelServiceAsync = client.models()

            val page = modelServiceAsync.list()

            page.response().validate()
        }
    }
}
