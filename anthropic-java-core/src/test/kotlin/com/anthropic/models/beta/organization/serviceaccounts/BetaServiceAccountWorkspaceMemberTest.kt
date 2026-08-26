// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServiceAccountWorkspaceMemberTest {

    @Test
    fun create() {
        val betaServiceAccountWorkspaceMember =
            BetaServiceAccountWorkspaceMember.builder()
                .createdByActorId("created_by_actor_id")
                .implicit(true)
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(betaServiceAccountWorkspaceMember.createdByActorId())
            .contains("created_by_actor_id")
        assertThat(betaServiceAccountWorkspaceMember.implicit()).contains(true)
        assertThat(betaServiceAccountWorkspaceMember.serviceAccountId())
            .isEqualTo("service_account_id")
        assertThat(betaServiceAccountWorkspaceMember.workspaceId()).isEqualTo("workspace_id")
        assertThat(betaServiceAccountWorkspaceMember.workspaceRole())
            .isEqualTo(BetaWorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServiceAccountWorkspaceMember =
            BetaServiceAccountWorkspaceMember.builder()
                .createdByActorId("created_by_actor_id")
                .implicit(true)
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val roundtrippedBetaServiceAccountWorkspaceMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServiceAccountWorkspaceMember),
                jacksonTypeRef<BetaServiceAccountWorkspaceMember>(),
            )

        assertThat(roundtrippedBetaServiceAccountWorkspaceMember)
            .isEqualTo(betaServiceAccountWorkspaceMember)
    }
}
