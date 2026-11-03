package com.anthropic.models.organization.apikeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceApiKeyLimitExceededErrorDetailsTest {

    @Test
    fun create() {
        val workspaceApiKeyLimitExceededErrorDetails =
            WorkspaceApiKeyLimitExceededErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceApiKeyLimitExceededErrorDetails =
            WorkspaceApiKeyLimitExceededErrorDetails.builder().build()

        val roundtrippedWorkspaceApiKeyLimitExceededErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceApiKeyLimitExceededErrorDetails),
                jacksonTypeRef<WorkspaceApiKeyLimitExceededErrorDetails>(),
            )

        assertThat(roundtrippedWorkspaceApiKeyLimitExceededErrorDetails)
            .isEqualTo(workspaceApiKeyLimitExceededErrorDetails)
    }
}
