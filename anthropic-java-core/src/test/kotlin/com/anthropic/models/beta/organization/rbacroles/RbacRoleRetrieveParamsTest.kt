package com.anthropic.models.beta.organization.rbacroles

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleRetrieveParamsTest {

    @Test
    fun create() {
        RbacRoleRetrieveParams.builder().rbacRoleId("rbac_role_id").build()
    }

    @Test
    fun pathParams() {
        val params = RbacRoleRetrieveParams.builder().rbacRoleId("rbac_role_id").build()

        assertThat(params._pathParam(0)).isEqualTo("rbac_role_id")
        // out-of-bound path param
        assertThat(params._pathParam(1)).isEqualTo("")
    }
}
