// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WorkspaceListParamsTest {

    @Test
    fun create() {
        WorkspaceListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .includeArchived(true)
            .limit(1L)
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            WorkspaceListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .includeArchived(true)
                .limit(1L)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("include_archived", "true")
                    .put("limit", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = WorkspaceListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
