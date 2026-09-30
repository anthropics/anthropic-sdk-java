package com.anthropic.services.async.organization.serviceaccounts

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
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
        val workspaceServiceAsync = client.organization().serviceAccounts().workspaces()

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
        val workspaceServiceAsync = client.organization().serviceAccounts().workspaces()

        val serviceAccountWorkspaceMemberFuture =
            workspaceServiceAsync.add(
                WorkspaceAddParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
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
        val workspaceServiceAsync = client.organization().serviceAccounts().workspaces()

        val workspaceFuture =
            workspaceServiceAsync.remove(
                WorkspaceRemoveParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val workspace = workspaceFuture.get()
        workspace.validate()
    }
}
