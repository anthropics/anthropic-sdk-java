// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberUpdateParamsTest {

    @Test
    fun create() {
        MemberUpdateParams.builder()
            .workspaceId("workspace_id")
            .userId("user_id")
            .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberUpdateParams.builder()
                .workspaceId("workspace_id")
                .userId("user_id")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        assertThat(params._pathParam(1)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            MemberUpdateParams.builder()
                .workspaceId("workspace_id")
                .userId("user_id")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.workspaceRole()).isEqualTo(BetaWorkspaceRole.WORKSPACE_ADMIN)
    }
}
