package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupListParamsTest {

    @Test
    fun create() {
        RbacGroupListParams.builder().limit(1L).page("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9").build()
    }

    @Test
    fun queryParams() {
        val params =
            RbacGroupListParams.builder()
                .limit(1L)
                .page("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("page", "eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = RbacGroupListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
