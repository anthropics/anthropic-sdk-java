// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.ratelimits

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RateLimitListParamsTest {

    @Test
    fun create() {
        RateLimitListParams.builder()
            .workspaceId("workspace_id")
            .groupType(RateLimitListParams.GroupType.BATCH)
            .limit(1L)
            .page("page")
            .build()
    }

    @Test
    fun pathParams() {
        val params = RateLimitListParams.builder().workspaceId("workspace_id").build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            RateLimitListParams.builder()
                .workspaceId("workspace_id")
                .groupType(RateLimitListParams.GroupType.BATCH)
                .limit(1L)
                .page("page")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("group_type", "batch")
                    .put("limit", "1")
                    .put("page", "page")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = RateLimitListParams.builder().workspaceId("workspace_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
