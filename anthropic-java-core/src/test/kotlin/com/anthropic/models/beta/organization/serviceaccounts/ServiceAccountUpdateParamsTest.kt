// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountUpdateParamsTest {

    @Test
    fun create() {
        ServiceAccountUpdateParams.builder()
            .serviceAccountId("service_account_id")
            .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
            .description("description")
            .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountUpdateParams.builder().serviceAccountId("service_account_id").build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun headers() {
        val params =
            ServiceAccountUpdateParams.builder()
                .serviceAccountId("service_account_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .description("description")
                .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
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
            ServiceAccountUpdateParams.builder().serviceAccountId("service_account_id").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ServiceAccountUpdateParams.builder()
                .serviceAccountId("service_account_id")
                .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                .description("description")
                .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                .build()

        val body = params._body()

        assertThat(body.description()).contains("description")
        assertThat(body.organizationRole())
            .contains(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params =
            ServiceAccountUpdateParams.builder().serviceAccountId("service_account_id").build()

        val body = params._body()
    }
}
