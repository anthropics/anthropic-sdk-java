// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.ModelRetrieveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
class ModelServiceTest {

    @Test
    fun callRetrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelService = client.models()
        val modelInfo =
            modelService.retrieve(ModelRetrieveParams.builder().modelId("model_id").build())
        println(modelInfo)
        modelInfo.validate()
    }

    @Test
    fun callList() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelService = client.models()
        val listResponseModelInfo = modelService.list()
        println(listResponseModelInfo)
        listResponseModelInfo.data().forEach { it.validate() }
    }
}
