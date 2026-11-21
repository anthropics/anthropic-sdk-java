package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceHumanReviewDisabledErrorDetailsTest {

    @Test
    fun create() {
        val workspaceHumanReviewDisabledErrorDetails =
            WorkspaceHumanReviewDisabledErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceHumanReviewDisabledErrorDetails =
            WorkspaceHumanReviewDisabledErrorDetails.builder().build()

        val roundtrippedWorkspaceHumanReviewDisabledErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceHumanReviewDisabledErrorDetails),
                jacksonTypeRef<WorkspaceHumanReviewDisabledErrorDetails>(),
            )

        assertThat(roundtrippedWorkspaceHumanReviewDisabledErrorDetails)
            .isEqualTo(workspaceHumanReviewDisabledErrorDetails)
    }
}
