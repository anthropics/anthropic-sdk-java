// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.serviceaccounts.workspaces

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceRemoveResponseTest {

    @Test
    fun create() {
        val workspaceRemoveResponse =
            WorkspaceRemoveResponse.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        assertThat(workspaceRemoveResponse.serviceAccountId()).isEqualTo("service_account_id")
        assertThat(workspaceRemoveResponse.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val workspaceRemoveResponse =
            WorkspaceRemoveResponse.builder()
                .serviceAccountId("service_account_id")
                .workspaceId("workspace_id")
                .build()

        val roundtrippedWorkspaceRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(workspaceRemoveResponse),
                jacksonTypeRef<WorkspaceRemoveResponse>(),
            )

        assertThat(roundtrippedWorkspaceRemoveResponse).isEqualTo(workspaceRemoveResponse)
    }
}
