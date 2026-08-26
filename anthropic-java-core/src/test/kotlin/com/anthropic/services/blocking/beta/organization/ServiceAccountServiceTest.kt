// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ServiceAccountServiceTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.beta().organization().serviceAccounts()

        val betaServiceAccount =
            serviceAccountService.create(
                ServiceAccountCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .name("ci-deploy-bot")
                    .description("description")
                    .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                    .build()
            )

        betaServiceAccount.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.beta().organization().serviceAccounts()

        val betaServiceAccount =
            serviceAccountService.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaServiceAccount.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.beta().organization().serviceAccounts()

        val betaServiceAccount =
            serviceAccountService.update(
                ServiceAccountUpdateParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .description("description")
                    .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                    .build()
            )

        betaServiceAccount.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.beta().organization().serviceAccounts()

        val page = serviceAccountService.list()

        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.beta().organization().serviceAccounts()

        val betaServiceAccount =
            serviceAccountService.archive(
                ServiceAccountArchiveParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaServiceAccount.validate()
    }
}
