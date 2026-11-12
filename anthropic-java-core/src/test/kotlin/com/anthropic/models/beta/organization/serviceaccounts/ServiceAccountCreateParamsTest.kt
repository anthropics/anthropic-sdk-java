package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.http.Headers
import com.anthropic.models.beta.AnthropicBeta
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountCreateParamsTest {

    @Test
    fun create() {
        ServiceAccountCreateParams.builder()
            .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
            .name("ci-deploy-bot")
            .description("description")
            .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
            .build()
    }

    @Test
    fun headers() {
        val params =
            ServiceAccountCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .name("ci-deploy-bot")
                .description("description")
                .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                .build()

        val headers = params._headers()

        assertThat(headers)
            .isEqualTo(
                Headers.builder()
                    .put("anthropic-beta", listOf("computer-use-2024-10-22").joinToString(","))
                    .build()
            )
    }

    @Test
    fun headersWithoutOptionalFields() {
        val params = ServiceAccountCreateParams.builder().name("ci-deploy-bot").build()

        val headers = params._headers()

        assertThat(headers).isEqualTo(Headers.builder().build())
    }

    @Test
    fun body() {
        val params =
            ServiceAccountCreateParams.builder()
                .addBeta(AnthropicBeta.COMPUTER_USE_2024_10_22)
                .name("ci-deploy-bot")
                .description("description")
                .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                .build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("ci-deploy-bot")
        assertThat(body.description()).contains("description")
        assertThat(body.organizationRole())
            .contains(ServiceAccountCreateParams.OrganizationRole.ADMIN)
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = ServiceAccountCreateParams.builder().name("ci-deploy-bot").build()

        val body = params._body()

        assertThat(body.name()).isEqualTo("ci-deploy-bot")
    }
}
