package com.anthropic.models.organization.workspaces.members

import com.anthropic.models.organization.workspaces.WorkspaceRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberUpdateParamsTest {

    @Test
    fun create() {
        MemberUpdateParams.builder()
            .workspaceId("workspace_id")
            .userId("user_id")
            .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberUpdateParams.builder()
                .workspaceId("workspace_id")
                .userId("user_id")
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
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
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val body = params._body()

        assertThat(body.workspaceRole()).isEqualTo(WorkspaceRole.WORKSPACE_ADMIN)
    }
}
