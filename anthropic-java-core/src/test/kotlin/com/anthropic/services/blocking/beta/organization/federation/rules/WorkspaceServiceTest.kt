package com.anthropic.services.blocking.beta.organization.federation.rules

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestServerExtension::class)
internal class WorkspaceServiceTest {

    private val client =
        AnthropicOkHttpClient.builder()
            .baseUrl(TestServerExtension.BASE_URL)
            .apiKey("my-anthropic-api-key")
            .build()

    @Test
    fun list() {
        val workspaceService =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val page = workspaceService.list("federation_rule_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceService =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val betaFederationRuleWorkspace =
            workspaceService.add(
                WorkspaceAddParams.builder()
                    .federationRuleId("federation_rule_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .workspaceId("workspace_id")
                    .build()
            )

        betaFederationRuleWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceService =
            client.beta().organization().federation().rules().workspaces().withOptions {
                it.putQueryParam("beta", "true")
            }

        val workspace =
            workspaceService.remove(
                WorkspaceRemoveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
                    .build()
            )

        workspace.validate()
    }
}
