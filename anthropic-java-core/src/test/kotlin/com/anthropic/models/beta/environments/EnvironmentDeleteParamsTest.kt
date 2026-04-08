// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EnvironmentDeleteParamsTest {

    @Test
    fun create() {
        EnvironmentDeleteParams.builder()
            .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            EnvironmentDeleteParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        assertThat(params._pathParam(0)).isEqualTo("env_011CZkZ9X2dpNyB7HsEFoRfW")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            EnvironmentDeleteParams.builder()
                .environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder().put("anthropic-beta", "message-batches-2024-09-24").build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params =
            EnvironmentDeleteParams.builder().environmentId("env_011CZkZ9X2dpNyB7HsEFoRfW").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
