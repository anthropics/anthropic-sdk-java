package com.anthropic.models.organization.serviceaccounts.workspaces

import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceAddParamsTest {

    @Test
    fun create() {
        WorkspaceAddParams.builder()
            .serviceAccountId("service_account_id")
            .workspaceId("workspace_id")
            .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("service_account_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            WorkspaceAddParams.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.workspaceId()).isEqualTo("workspace_id")
        assertThat(body.workspaceRole()).isEqualTo(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }
}
