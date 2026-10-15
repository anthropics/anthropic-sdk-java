package com.anthropic.services.blocking.organization.federation.rules

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.models.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.organization.federation.rules.workspaces.WorkspaceRemoveParams
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
        val workspaceService = client.organization().federation().rules().workspaces()

        val page = workspaceService.list("federation_rule_id")

        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceService = client.organization().federation().rules().workspaces()

        val federationRuleWorkspace =
            workspaceService.add(
                WorkspaceAddParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        federationRuleWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceService = client.organization().federation().rules().workspaces()

        val workspace =
            workspaceService.remove(
                WorkspaceRemoveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        workspace.validate()
    }
}
