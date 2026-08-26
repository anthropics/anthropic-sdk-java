// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces.members

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberListParamsTest {

    @Test
    fun create() {
        MemberListParams.builder()
            .workspaceId("workspace_id")
            .afterId("after_id")
            .beforeId("before_id")
            .limit(1L)
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemberListParams.builder().workspaceId("workspace_id").build()

        assertThat(params._pathParam(0)).isEqualTo("workspace_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            MemberListParams.builder()
                .workspaceId("workspace_id")
                .afterId("after_id")
                .beforeId("before_id")
                .limit(1L)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("limit", "1")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = MemberListParams.builder().workspaceId("workspace_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
