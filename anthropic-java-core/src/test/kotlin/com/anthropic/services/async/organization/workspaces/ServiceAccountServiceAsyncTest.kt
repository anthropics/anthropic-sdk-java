package com.anthropic.services.async.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
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
        val serviceAccountServiceAsync = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .build()
            )

        val serviceAccountWorkspaceMember = serviceAccountWorkspaceMemberFuture.get()
        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.update(
                ServiceAccountUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val serviceAccountWorkspaceMember = serviceAccountWorkspaceMemberFuture.get()
        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().workspaces().serviceAccounts()

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
        val serviceAccountServiceAsync = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMemberFuture =
            serviceAccountServiceAsync.add(
                ServiceAccountAddParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        val serviceAccountWorkspaceMember = serviceAccountWorkspaceMemberFuture.get()
        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountServiceAsync = client.organization().workspaces().serviceAccounts()

        val serviceAccountFuture =
            serviceAccountServiceAsync.remove(
                ServiceAccountRemoveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .build()
            )

        val serviceAccount = serviceAccountFuture.get()
        serviceAccount.validate()
    }
}
