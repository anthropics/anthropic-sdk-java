// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserListParamsTest {

    @Test
    fun create() {
        UserListParams.builder()
            .afterId("after_id")
            .beforeId("before_id")
            .email("dev@stainless.com")
            .limit(1L)
            .addRole("string")
            .build()
    }

    @Test
    fun queryParams() {
        val params =
            UserListParams.builder()
                .afterId("after_id")
                .beforeId("before_id")
                .email("dev@stainless.com")
                .limit(1L)
                .addRole("string")
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
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = UserListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
