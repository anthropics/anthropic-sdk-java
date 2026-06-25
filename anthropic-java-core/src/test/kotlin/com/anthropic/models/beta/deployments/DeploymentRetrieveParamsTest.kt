// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeploymentRetrieveParamsTest {

    @Test
    fun create() {
        DeploymentRetrieveParams.builder()
            .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            DeploymentRetrieveParams.builder().deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai").build()

        assertThat(params._pathParam(0)).isEqualTo("depl_011CZkZcDH3vPqd7xnEfwTai")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            DeploymentRetrieveParams.builder()
                .deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai")
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
            DeploymentRetrieveParams.builder().deploymentId("depl_011CZkZcDH3vPqd7xnEfwTai").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }
}
