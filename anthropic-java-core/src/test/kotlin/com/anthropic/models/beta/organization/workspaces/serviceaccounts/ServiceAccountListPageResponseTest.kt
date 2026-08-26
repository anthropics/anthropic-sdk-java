// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.serviceaccounts

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.serviceaccounts.BetaServiceAccountWorkspaceMember
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountListPageResponseTest {

    @Test
    fun create() {
        val serviceAccountListPageResponse =
            ServiceAccountListPageResponse.builder()
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

        assertThat(serviceAccountListPageResponse.data())
            .containsExactly(
                BetaServiceAccountWorkspaceMember.builder()
                    .createdByActorId("created_by_actor_id")
                    .implicit(true)
                    .serviceAccountId("service_account_id")
                    .workspaceId("workspace_id")
                    .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
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

        val roundtrippedServiceAccountListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serviceAccountListPageResponse),
                jacksonTypeRef<ServiceAccountListPageResponse>(),
            )

        assertThat(roundtrippedServiceAccountListPageResponse)
            .isEqualTo(serviceAccountListPageResponse)
    }
}
