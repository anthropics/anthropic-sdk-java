package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceMemberTest {

    @Test
    fun create() {
        val workspaceMember =
            WorkspaceMember.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(workspaceMember.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(workspaceMember.workspaceId()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
        assertThat(workspaceMember.workspaceRole()).isEqualTo(WorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceMember =
            WorkspaceMember.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val roundtrippedWorkspaceMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceMember),
                jacksonTypeRef<WorkspaceMember>(),
            )

        assertThat(roundtrippedWorkspaceMember).isEqualTo(workspaceMember)
    }
}
