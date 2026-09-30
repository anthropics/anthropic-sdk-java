package com.anthropic.services.blocking.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountUpdateParams
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
        val serviceAccountService = client.organization().serviceAccounts()

        val serviceAccount =
            serviceAccountService.create(
                ServiceAccountCreateParams.builder()
                    .name("ci-deploy-bot")
                    .description("description")
                    .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                    .build()
            )

        serviceAccount.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().serviceAccounts()

        val serviceAccount = serviceAccountService.retrieve("service_account_id")

        serviceAccount.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().serviceAccounts()

        val serviceAccount =
            serviceAccountService.update(
                ServiceAccountUpdateParams.builder()
                    .serviceAccountId("service_account_id")
                    .description("description")
                    .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                    .build()
            )

        serviceAccount.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().serviceAccounts()

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
        val serviceAccountService = client.organization().serviceAccounts()

        val serviceAccount = serviceAccountService.archive("service_account_id")

        serviceAccount.validate()
    }
}
