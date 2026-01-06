// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models

import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ModelListParamsTest {

    @Test
    fun create() {
        ModelListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .limit(1L)
            .addBeta(AnthropicBeta.of("string"))
            .build()
    }

    @Test
    fun headers() {
        val params =
            ModelListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .limit(1L)
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().put("anthropic-beta", "string").build())
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = ModelListParams.builder().build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun queryParams() {
        val params =
            ModelListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .limit(1L)
                .addBeta(AnthropicBeta.of("string"))
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("limit", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ModelListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
