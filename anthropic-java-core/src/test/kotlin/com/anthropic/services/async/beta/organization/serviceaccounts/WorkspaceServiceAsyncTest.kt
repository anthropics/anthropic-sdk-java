// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.serviceaccounts

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceAsyncTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceServiceAsync = client.beta().organization().serviceAccounts().workspaces()

        val pageFuture = workspaceServiceAsync.list("service_account_id")

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
        val workspaceServiceAsync = client.beta().organization().serviceAccounts().workspaces()

        val betaServiceAccountWorkspaceMemberFuture =
            workspaceServiceAsync.add(
                WorkspaceAddParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("workspace_id")
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
        val workspaceServiceAsync = client.beta().organization().serviceAccounts().workspaces()

        val workspaceFuture =
            workspaceServiceAsync.remove(
                WorkspaceRemoveParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val workspace = workspaceFuture.get()
        workspace.validate()
    }
}
