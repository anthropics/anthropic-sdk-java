package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceMemberLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val workspaceMemberLimitExceededErrorDetails =
            WorkspaceMemberLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceMemberLimitExceededErrorDetails =
            WorkspaceMemberLimitExceededErrorDetails.builder().build()

        val roundtrippedWorkspaceMemberLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceMemberLimitExceededErrorDetails),
                jacksonTypeRef<WorkspaceMemberLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedWorkspaceMemberLimitExceededErrorDetails)
            .isEqualTo(workspaceMemberLimitExceededErrorDetails)
    }
}
