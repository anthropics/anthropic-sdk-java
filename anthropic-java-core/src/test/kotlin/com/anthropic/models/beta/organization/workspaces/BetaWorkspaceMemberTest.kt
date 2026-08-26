// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaWorkspaceMemberTest {

    @Test
    fun create() {
        val betaWorkspaceMember =
            BetaWorkspaceMember.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        assertThat(betaWorkspaceMember.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(betaWorkspaceMember.workspaceId()).isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
        assertThat(betaWorkspaceMember.workspaceRole()).isEqualTo(BetaWorkspaceRole.WORKSPACE_ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaWorkspaceMember =
            BetaWorkspaceMember.builder()
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .workspaceId("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
                .workspaceRole(BetaWorkspaceRole.WORKSPACE_ADMIN)
                .build()

        val roundtrippedBetaWorkspaceMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWorkspaceMember),
                jacksonTypeRef<BetaWorkspaceMember>(),
            )

        assertThat(roundtrippedBetaWorkspaceMember).isEqualTo(betaWorkspaceMember)
    }
}
