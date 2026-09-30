package com.anthropic.models.beta.organization.rbacgroups

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupUpdateParamsTest {

    @Test
    fun create() {
        RbacGroupUpdateParams.builder().rbacGroupId("rbac_group_id").name("Engineering").build()
    }

    @Test
    fun pathParams() {
        val params = RbacGroupUpdateParams.builder().rbacGroupId("rbac_group_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            RbacGroupUpdateParams.builder().rbacGroupId("rbac_group_id").name("Engineering").build()

        val body = params._body()

        assertThat(body.name()).contains("Engineering")
    }

    @Test
    fun bodyWithoutOptionalFields() {
        val params = RbacGroupUpdateParams.builder().rbacGroupId("rbac_group_id").build()

        val body = params._body()
    }
}
