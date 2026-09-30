package com.anthropic.models.organization.workspaces.members

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.workspaces.WorkspaceMember
import com.anthropic.models.organization.workspaces.WorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberListPageResponseTest {

    @Test
    fun create() {
        val memberListPageResponse =
            MemberListPageResponse.builder()
                .addData(
                    WorkspaceMember.builder()
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(memberListPageResponse.data())
            .containsExactly(
                WorkspaceMember.builder()
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                    .build()
            )
        assertThat(memberListPageResponse.firstId()).contains("first_id")
        assertThat(memberListPageResponse.hasMore()).isEqualTo(true)
        assertThat(memberListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memberListPageResponse =
            MemberListPageResponse.builder()
                .addData(
                    WorkspaceMember.builder()
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .workspaceRole(WorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedMemberListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memberListPageResponse),
                jacksonTypeRef<MemberListPageResponse>(),
            )

        assertThat(roundtrippedMemberListPageResponse).isEqualTo(memberListPageResponse)
    }
}
