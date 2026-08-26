// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts.workspaces

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListPageResponseTest {

    @Test
    fun create() {
        val workspaceListPageResponse =
            WorkspaceListPageResponse.builder()
                .addData(
                    BetaServiceAccountWorkspaceMember.builder()
                        .createdByActorId("created_by_actor_id")
                        .implicit(true)
                        .serviceAccountId("service_account_id")
                        .workspaceId("workspace_id")
                        .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(workspaceListPageResponse.data())
            .containsExactly(
                BetaServiceAccountWorkspaceMember.builder()
                    .createdByActorId("created_by_actor_id")
                    .implicit(true)
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
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
                    BetaServiceAccountWorkspaceMember.builder()
                        .createdByActorId("created_by_actor_id")
                        .implicit(true)
                        .serviceAccountId("service_account_id")
                        .workspaceId("workspace_id")
                        .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
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
