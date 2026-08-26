// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberAddParamsTest {

    @Test
    fun create() {
        MemberAddParams.builder()
            .workspaceId("workspace_id")
            .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
            .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberAddParams.builder()
                .workspaceId("workspace_id")
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            MemberAddParams.builder()
                .workspaceId("workspace_id")
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceRole(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(body.workspaceRole()).isEqualTo(BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN)
    }
}
