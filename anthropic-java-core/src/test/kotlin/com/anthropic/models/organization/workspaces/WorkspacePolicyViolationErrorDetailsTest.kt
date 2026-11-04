package com.anthropic.models.organization.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspacePolicyViolationErrorDetailsTest {

    @Test
    fun create() {
        val workspacePolicyViolationErrorDetails =
            WorkspacePolicyViolationErrorDetails.of(WorkspacePolicyField.EXTERNAL_KEY_ID)

        assertThat(workspacePolicyViolationErrorDetails.field())
            .isEqualTo(WorkspacePolicyField.EXTERNAL_KEY_ID)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspacePolicyViolationErrorDetails =
            WorkspacePolicyViolationErrorDetails.of(WorkspacePolicyField.EXTERNAL_KEY_ID)

        val roundtrippedWorkspacePolicyViolationErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspacePolicyViolationErrorDetails),
                jacksonTypeRef<WorkspacePolicyViolationErrorDetails>(),
            )

        assertThat(roundtrippedWorkspacePolicyViolationErrorDetails)
            .isEqualTo(workspacePolicyViolationErrorDetails)
    }
}
