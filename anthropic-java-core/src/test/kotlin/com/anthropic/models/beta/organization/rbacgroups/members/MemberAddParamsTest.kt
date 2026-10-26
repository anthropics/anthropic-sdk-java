package com.anthropic.models.beta.organization.rbacgroups.members

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberAddParamsTest {

    @Test
    fun create() {
        MemberAddParams.builder()
            .rbacGroupId("rbac_group_id")
            .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
            .build()
    }

    @Test
    fun pathParams() {
        val params =
            MemberAddParams.builder()
                .rbacGroupId("rbac_group_id")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_group_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }

    @Test
    fun body() {
        val params =
            MemberAddParams.builder()
                .rbacGroupId("rbac_group_id")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val body = params._body()

        assertThat(body.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }
}
