package com.anthropic.services.blocking.organization.serviceaccounts

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceTest {

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().serviceAccounts().workspaces()

        val page = workspaceService.list("service_account_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().serviceAccounts().workspaces()

        val serviceAccountWorkspaceMember =
            workspaceService.add(
                WorkspaceAddParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.organization().serviceAccounts().workspaces()

        val workspace =
            workspaceService.remove(
                WorkspaceRemoveParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        workspace.validate()
    }
}
