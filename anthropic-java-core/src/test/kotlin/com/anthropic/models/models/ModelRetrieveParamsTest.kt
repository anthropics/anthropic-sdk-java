// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelRetrieveParamsTest {

    @Test
    fun create() {
        ModelRetrieveParams.builder()
            .modelId("model_id")
            .addBeta(AnthropicBeta.of("string"))
            .build()
    }

    @Test
    fun pathParams() {
        val params = ModelRetrieveParams.builder().modelId("model_id").build()

        assertThat(params._pathParam(0)).isEqualTo("model_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ModelRetrieveParams.builder()
                .modelId("model_id")
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = ModelRetrieveParams.builder().modelId("model_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
