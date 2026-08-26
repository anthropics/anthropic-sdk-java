// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.apikeys

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ApiKeyListParamsTest {

    @Test
    fun create() {
        ApiKeyListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .createdByUserId("created_by_user_id")
            .limit(1L)
            .status(ApiKeyListParams.Status.ACTIVE)
            .workspaceId("workspace_id")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            ApiKeyListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .createdByUserId("created_by_user_id")
                .limit(1L)
                .status(ApiKeyListParams.Status.ACTIVE)
                .workspaceId("workspace_id")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("created_by_user_id", "created_by_user_id")
                    .put("limit", "1")
                    .put("status", "active")
                    .put("workspace_id", "workspace_id")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = ApiKeyListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
