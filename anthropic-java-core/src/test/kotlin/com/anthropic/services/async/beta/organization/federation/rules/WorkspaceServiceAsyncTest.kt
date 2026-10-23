package com.anthropic.services.async.beta.organization.federation.rules

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceAsyncTest {

    private val client =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val workspaceServiceAsync =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val pageFuture = workspaceServiceAsync.list("federation_rule_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceServiceAsync =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaFederationRuleWorkspaceFuture =
            workspaceServiceAsync.add(
                WorkspaceAddParams.builder()
                    .federationRuleId("federation_rule_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("workspace_id")
                    .build()
            )

        val betaFederationRuleWorkspace = betaFederationRuleWorkspaceFuture.get()
        betaFederationRuleWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceServiceAsync =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val workspaceFuture =
            workspaceServiceAsync.remove(
                WorkspaceRemoveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        val workspace = workspaceFuture.get()
        workspace.validate()
    }
}
