package com.anthropic.services.async.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.organization.serviceaccounts.ServiceAccountUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ServiceAccountServiceAsyncTest {

    @Test
    fun create() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().serviceAccounts()

        val serviceAccountFuture =
            serviceAccountServiceAsync.create(
                ServiceAccountCreateParams.builder()
                    .name("ci-deploy-bot")
                    .description("description")
                    .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                    .build()
            )

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().serviceAccounts()

        val serviceAccountFuture = serviceAccountServiceAsync.retrieve("service_account_id")

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().serviceAccounts()

        val serviceAccountFuture =
            serviceAccountServiceAsync.update(
                ServiceAccountUpdateParams.builder()
                    .serviceAccountId("service_account_id")
                    .description("description")
                    .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                    .build()
            )

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().serviceAccounts()

        val pageFuture = serviceAccountServiceAsync.list()

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun archive() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().serviceAccounts()

        val serviceAccountFuture = serviceAccountServiceAsync.archive("service_account_id")

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }
}
