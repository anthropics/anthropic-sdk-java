// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InviteListParamsTest {

    @Test
    fun create() {
        InviteListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .email("dev@stainless.com")
            .limit(1L)
            .addRole("string")
            .addStatus(InviteListParams.Status.ACCEPTED)
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            InviteListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .email("dev@stainless.com")
                .limit(1L)
                .addRole("string")
                .addStatus(InviteListParams.Status.ACCEPTED)
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("after_id", "after_id")
                    .put("before_id", "before_id")
                    .put("email", "dev@stainless.com")
                    .put("limit", "1")
                    .put("roles[]", "string")
                    .put("statuses[]", "accepted")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = InviteListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
