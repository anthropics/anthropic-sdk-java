package com.anthropic.models.beta.organization.rbacroles.permissions

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PermissionListParamsTest {

    @Test
    fun create() {
        PermissionListParams.builder()
            .rbacRoleId("rbac_role_id")
            .limit(1L)
            .page("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
            .build()
    }

    @Test
    fun pathParams() {
        val params = PermissionListParams.builder().rbacRoleId("rbac_role_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_role_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun queryParams() {
        val params =
            PermissionListParams.builder()
                .rbacRoleId("rbac_role_id")
                .limit(1L)
                .page("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                .build()

        val queryParams = params._queryParams()

        assertThat(queryParams)
            .isEqualTo(
                QueryParams.builder()
                    .put("limit", "1")
                    .put("page", "eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0")
                    .build()
            )
    }

    @Test
    fun queryParamsWithoutOptionalFields() {
        val params = PermissionListParams.builder().rbacRoleId("rbac_role_id").build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
