package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.http.QueryParams
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleListParamsTest {

    @Test
    fun create() {
        RbacRoleListParams.builder().limit(1L).page("eyJjdXJzb3IiOiAicmJhY19yb2xlXzAxIn0").build()
    }

    @Test
    fun queryParams() {
        val params =
            RbacRoleListParams.builder()
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
        val params = RbacRoleListParams.builder().build()

        val queryParams = params._queryParams()

        assertThat(queryParams).isEqualTo(QueryParams.builder().build())
    }
}
