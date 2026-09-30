package com.anthropic.models.organization.serviceaccounts

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.workspaces.WorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountWorkspaceMemberTest {

    @Test
    fun create() {
        val serviceAccountWorkspaceMember =
            ServiceAccountWorkspaceMember.builder()
                .createdByActorId("created_by_actor_id")
                .implicit(true)
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(serviceAccountWorkspaceMember.createdByActorId()).contains("created_by_actor_id")
        assertThat(serviceAccountWorkspaceMember.implicit()).contains(true)
        assertThat(serviceAccountWorkspaceMember.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(serviceAccountWorkspaceMember.workspaceId()).isEqualTo("workspace_id")
        assertThat(serviceAccountWorkspaceMember.workspaceRole())
            .isEqualTo(WorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serviceAccountWorkspaceMember =
            ServiceAccountWorkspaceMember.builder()
                .createdByActorId("created_by_actor_id")
                .implicit(true)
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val roundtrippedServiceAccountWorkspaceMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountWorkspaceMember),
                jacksonTypeRef<ServiceAccountWorkspaceMember>(),
            )

        assertThat(roundtrippedServiceAccountWorkspaceMember)
            .isEqualTo(serviceAccountWorkspaceMember)
    }
}
