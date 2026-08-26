// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceMember
import com.anthropic.models.beta.organization.workspaces.BetaWorkspaceRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberListPageResponseTest {

    @Test
    fun create() {
        val memberListPageResponse =
            MemberListPageResponse.builder()
                .addData(
                    BetaWorkspaceMember.builder()
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(memberListPageResponse.data())
            .containsExactly(
                BetaWorkspaceMember.builder()
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                    .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
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
                    BetaWorkspaceMember.builder()
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                        .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
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
