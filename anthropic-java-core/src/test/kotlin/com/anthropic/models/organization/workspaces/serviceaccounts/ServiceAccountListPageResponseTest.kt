package com.anthropic.models.organization.workspaces.serviceaccounts

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.serviceaccounts.ServiceAccountWorkspaceMember
import com.anthropic.models.organization.workspaces.WorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountListPageResponseTest {

    @Test
    fun create() {
        val serviceAccountListPageResponse =
            ServiceAccountListPageResponse.builder()
                .addData(
                    ServiceAccountWorkspaceMember.builder()
                        .createdByActorId("created_by_actor_id")
                        .implicit(true)
                        .serviceAccountId("service_account_id")
                        .workspaceId("workspace_id")
                        .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(serviceAccountListPageResponse.data())
            .containsExactly(
                ServiceAccountWorkspaceMember.builder()
                    .createdByActorId("created_by_actor_id")
                    .implicit(true)
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )
        assertThat(serviceAccountListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serviceAccountListPageResponse =
            ServiceAccountListPageResponse.builder()
                .addData(
                    ServiceAccountWorkspaceMember.builder()
                        .createdByActorId("created_by_actor_id")
                        .implicit(true)
                        .serviceAccountId("service_account_id")
                        .workspaceId("workspace_id")
                        .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedServiceAccountListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountListPageResponse),
                jacksonTypeRef<ServiceAccountListPageResponse>(),
            )

        assertThat(roundtrippedServiceAccountListPageResponse)
            .isEqualTo(serviceAccountListPageResponse)
    }
}
