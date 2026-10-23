package com.anthropic.services.blocking.beta.organization.workspaces

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountAddParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRemoveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountUpdateParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class ServiceAccountServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun retrieve() {
        val serviceAccountService =
            client.beta().organization().workspaces().serviceAccounts().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaServiceAccountWorkspaceMember =
            serviceAccountService.retrieve(
                ServiceAccountRetrieveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun update() {
        val serviceAccountService =
            client.beta().organization().workspaces().serviceAccounts().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaServiceAccountWorkspaceMember =
            serviceAccountService.update(
                ServiceAccountUpdateParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun list() {
        val serviceAccountService =
            client.beta().organization().workspaces().serviceAccounts().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = serviceAccountService.list("workspace_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val serviceAccountService =
            client.beta().organization().workspaces().serviceAccounts().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaServiceAccountWorkspaceMember =
            serviceAccountService.add(
                ServiceAccountAddParams.builder()
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .serviceAccountId("service_account_id")
                    .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )

        betaServiceAccountWorkspaceMember.validate()
    }

    @Test
    fun remove() {
        val serviceAccountService =
            client.beta().organization().workspaces().serviceAccounts().withOptions {
                it.putQueryParam("beta", "true")
            }

        val serviceAccount =
            serviceAccountService.remove(
                ServiceAccountRemoveParams.builder()
                    .workspaceId("workspace_id")
                    .serviceAccountId("service_account_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        serviceAccount.validate()
    }
}
