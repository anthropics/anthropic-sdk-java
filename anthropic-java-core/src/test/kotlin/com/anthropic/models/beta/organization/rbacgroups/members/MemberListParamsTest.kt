package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberListParamsTest {

    @Test
    fun create() {
        MemberListParams.builder()
            .rbacGroupId("rbac_group_id")
            .limit(1L)
            .page("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
            .build()
    }

    @Test
    fun pathParams() {
        val params = MemberListParams.builder().rbacGroupId("rbac_group_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            MemberListParams.builder()
                .rbacGroupId("rbac_group_id")
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
        val params = MemberListParams.builder().rbacGroupId("rbac_group_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
