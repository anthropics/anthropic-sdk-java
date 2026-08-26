// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ServiceAccountServiceAsyncTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().workspaces().serviceAccounts()

        val betaServiceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val betaServiceAccountWorkspaceMember = betaServiceAccountWorkspaceMemberFuture.get()
        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().workspaces().serviceAccounts()

        val betaServiceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.update(
                ServiceAccountUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val betaServiceAccountWorkspaceMember = betaServiceAccountWorkspaceMemberFuture.get()
        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().workspaces().serviceAccounts()

        val pageFuture = serviceAccountServiceAsync.list("workspace_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().workspaces().serviceAccounts()

        val betaServiceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.add(
                ServiceAccountAddParams.builder()
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .serviceAccountId("service_account_id")
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val betaServiceAccountWorkspaceMember = betaServiceAccountWorkspaceMemberFuture.get()
        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.beta().organization().workspaces().serviceAccounts()

        val serviceAccountFuture =
            serviceAccountServiceAsync.remove(
                ServiceAccountRemoveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }
}
