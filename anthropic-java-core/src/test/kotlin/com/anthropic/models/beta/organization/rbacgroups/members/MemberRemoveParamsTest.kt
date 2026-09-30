package com.anthropic.models.beta.organization.rbacgroups.members

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberRemoveParamsTest {

    @Test
    fun create() {
        MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberRemoveParams.builder().rbacGroupId("rbac_group_id").userId("user_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        assertThat(params._pathParam(1)).isEqualTo("user_id")
        // out-of-bound path param
        assertThat(params._pathParam(2)).isEqualTo("")
    }
}
