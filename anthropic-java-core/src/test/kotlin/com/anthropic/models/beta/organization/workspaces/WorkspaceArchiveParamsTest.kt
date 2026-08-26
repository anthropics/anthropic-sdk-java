// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceArchiveParamsTest {

    @Test
    fun create() {
        WorkspaceArchiveParams.builder().workspaceId("workspace_id").build()
    }

    @Test
    fun pathParams() {
        val params = WorkspaceArchiveParams.builder().workspaceId("workspace_id").build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
