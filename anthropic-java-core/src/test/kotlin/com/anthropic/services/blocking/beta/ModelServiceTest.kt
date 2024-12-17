// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.BetaModelListParams
import com.anthropic.models.BetaModelRetrieveParams
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
        val modelService = client.beta().models()
        val betaModelInfo =
            modelService.retrieve(BetaModelRetrieveParams.builder().modelId("model_id").build())
        println(betaModelInfo)
        betaModelInfo.validate()
    }

    @Test
    fun callList() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val modelService = client.beta().models()
        val betaListResponseModelResponse = modelService.list(BetaModelListParams.builder().build())
        println(betaListResponseModelResponse)
        betaListResponseModelResponse.data().forEach { it.validate() }
    }
}
