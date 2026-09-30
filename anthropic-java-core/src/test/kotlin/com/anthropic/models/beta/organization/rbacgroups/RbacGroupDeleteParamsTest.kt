package com.anthropic.models.beta.organization.rbacgroups

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupDeleteParamsTest {

    @Test
    fun create() {
        RbacGroupDeleteParams.builder().rbacGroupId("rbac_group_id").build()
    }

    @Test
    fun pathParams() {
        val params = RbacGroupDeleteParams.builder().rbacGroupId("rbac_group_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
