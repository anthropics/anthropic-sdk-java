// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountArchiveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountUpdateParams
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
        val serviceAccountServiceAsync = client.beta().organization().serviceAccounts()

        val betaServiceAccountFuture =
            serviceAccountServiceAsync.create(
                ServiceAccountCreateParams.builder()
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .name("ci-deploy-bot")
                    .description("description")
                    .organizationRole(ServiceAccountCreateParams.OrganizationRole.ADMIN)
                    .build()
            )

        val betaServiceAccount = betaServiceAccountFuture.get()
        betaServiceAccount.validate()
    }

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().serviceAccounts()

        val betaServiceAccountFuture =
            serviceAccountServiceAsync.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaServiceAccount = betaServiceAccountFuture.get()
        betaServiceAccount.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().serviceAccounts()

        val betaServiceAccountFuture =
            serviceAccountServiceAsync.update(
                ServiceAccountUpdateParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .description("description")
                    .organizationRole(ServiceAccountUpdateParams.OrganizationRole.ADMIN)
                    .build()
            )

        val betaServiceAccount = betaServiceAccountFuture.get()
        betaServiceAccount.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().serviceAccounts()

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
        val serviceAccountServiceAsync = client.beta().organization().serviceAccounts()

        val betaServiceAccountFuture =
            serviceAccountServiceAsync.archive(
                ServiceAccountArchiveParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaServiceAccount = betaServiceAccountFuture.get()
        betaServiceAccount.validate()
    }
}
