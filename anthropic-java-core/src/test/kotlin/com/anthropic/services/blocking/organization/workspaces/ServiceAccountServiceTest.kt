package com.anthropic.services.blocking.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ServiceAccountServiceTest {

    @Test
    fun retrieve() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMember =
            serviceAccountService.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .build()
            )

        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun update() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMember =
            serviceAccountService.update(
                ServiceAccountUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        serviceAccountWorkspaceMember.validate()
    }

    @Test
    fun list() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().workspaces().serviceAccounts()

        val page = serviceAccountService.list("workspace_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val client =
            AnthropicOkHttpClient.builder()
                .baseUrl(TestServerExtension.BASE_URL)
                .apiKey("my-anthropic-api-key")
                .build()
        val serviceAccountService = client.organization().workspaces().serviceAccounts()

        val serviceAccountWorkspaceMember =
            serviceAccountService.add(
                ServiceAccountAddParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
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
        val serviceAccountService = client.organization().workspaces().serviceAccounts()

        val serviceAccount =
            serviceAccountService.remove(
                ServiceAccountRemoveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .build()
            )

        serviceAccount.validate()
    }
}
