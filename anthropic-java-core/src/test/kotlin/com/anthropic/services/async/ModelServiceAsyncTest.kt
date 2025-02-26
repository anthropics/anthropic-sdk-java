// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.ModelRetrieveParams
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
        val modelServiceAsync = client.models()

        val modelInfoFuture =
            modelServiceAsync.retrieve(ModelRetrieveParams.builder().modelId("model_id").build())

        val modelInfo = modelInfoFuture.get()
        modelInfo.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelServiceAsync = client.models()

        val pageFuture = modelServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }
}
