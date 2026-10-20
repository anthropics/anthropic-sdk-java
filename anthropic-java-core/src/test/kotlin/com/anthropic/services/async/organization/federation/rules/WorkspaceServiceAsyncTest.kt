package com.anthropic.services.async.organization.federation.rules

import com.anthropic.TestServerExtension
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.models.organization.federation.rules.workspaces.WorkspaceAddParams
import com.anthropic.models.organization.federation.rules.workspaces.WorkspaceRemoveParams
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
        val workspaceServiceAsync = client.organization().federation().rules().workspaces()

        val pageFuture = workspaceServiceAsync.list("federation_rule_id")

        val page = pageFuture.get()
        page.response().validate()
    }

    @Test
    fun add() {
        val workspaceServiceAsync = client.organization().federation().rules().workspaces()

        val federationRuleWorkspaceFuture =
            workspaceServiceAsync.add(
                WorkspaceAddParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val federationRuleWorkspace = federationRuleWorkspaceFuture.get()
        federationRuleWorkspace.validate()
    }

    @Test
    fun remove() {
        val workspaceServiceAsync = client.organization().federation().rules().workspaces()

        val workspaceFuture =
            workspaceServiceAsync.remove(
                WorkspaceRemoveParams.builder()
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val workspace = workspaceFuture.get()
        workspace.validate()
    }
}
