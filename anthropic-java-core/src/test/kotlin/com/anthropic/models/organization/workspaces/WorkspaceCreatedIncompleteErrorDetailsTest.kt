package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceCreatedIncompleteErrorDetailsTest {

    @Test
    fun create() {
        val workspaceCreatedIncompleteErrorDetails =
            WorkspaceCreatedIncompleteErrorDetails.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        assertThat(workspaceCreatedIncompleteErrorDetails.workspaceId())
            .isEqualTo("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceCreatedIncompleteErrorDetails =
            WorkspaceCreatedIncompleteErrorDetails.of("wrkspc_01JwQvzr7rXLA5AGx3HKfFUJ")

        val roundtrippedWorkspaceCreatedIncompleteErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceCreatedIncompleteErrorDetails),
                jacksonTypeRef<WorkspaceCreatedIncompleteErrorDetails>(),
            )

        assertThat(roundtrippedWorkspaceCreatedIncompleteErrorDetails)
            .isEqualTo(workspaceCreatedIncompleteErrorDetails)
    }
}
