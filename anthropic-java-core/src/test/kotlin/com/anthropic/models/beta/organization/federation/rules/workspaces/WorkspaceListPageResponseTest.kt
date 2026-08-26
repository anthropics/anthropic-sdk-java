// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules.workspaces

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.federation.rules.BetaFederationRuleWorkspace
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListPageResponseTest {

    @Test
    fun create() {
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
                    BetaFederationRuleWorkspace.builder()
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .createdByActorId("created_by_actor_id")
                        .federationRuleId("federation_rule_id")
                        .workspaceId("workspace_id")
                        .workspaceName("workspace_name")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(workspaceListPageResponse.data())
            .containsExactly(
                BetaFederationRuleWorkspace.builder()
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .createdByActorId("created_by_actor_id")
                    .federationRuleId("federation_rule_id")
                    .workspaceId("workspace_id")
                    .workspaceName("workspace_name")
                    .build()
            )
        assertThat(workspaceListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
                    BetaFederationRuleWorkspace.builder()
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .createdByActorId("created_by_actor_id")
                        .federationRuleId("federation_rule_id")
                        .workspaceId("workspace_id")
                        .workspaceName("workspace_name")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedWorkspaceListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceListPageResponse),
                jacksonTypeRef<WorkspaceListPageResponse>(),
            )

        assertThat(roundtrippedWorkspaceListPageResponse).isEqualTo(workspaceListPageResponse)
    }
}
