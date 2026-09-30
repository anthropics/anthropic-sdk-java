package com.anthropic.models.organization.workspaces.serviceaccounts

import com.anthropic.models.organization.workspaces.NoBillingWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServiceAccountAddParamsTest {

    @Test
    fun create() {
        ServiceAccountAddParams.builder()
            .workspaceId("workspace_id")
            .serviceAccountId("service_account_id")
            .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            ServiceAccountAddParams.builder()
                .workspaceId("workspace_id")
                .serviceAccountId("service_account_id")
                .workspaceRole(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(body.workspaceRole()).isEqualTo(NoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }
}
