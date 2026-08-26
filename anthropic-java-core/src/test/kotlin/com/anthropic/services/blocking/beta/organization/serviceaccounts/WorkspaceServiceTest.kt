// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.organization.serviceaccounts

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveParams
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
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
        val workspaceService = client.beta().organization().serviceAccounts().workspaces()

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
        val workspaceService = client.beta().organization().serviceAccounts().workspaces()

        val betaServiceAccountWorkspaceMember =
            workspaceService.add(
                WorkspaceAddParams.builder()
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("workspace_id")
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val workspaceService = client.beta().organization().serviceAccounts().workspaces()

        val workspace =
            workspaceService.remove(
                WorkspaceRemoveParams.builder()
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        workspace.validate()
    }
}
