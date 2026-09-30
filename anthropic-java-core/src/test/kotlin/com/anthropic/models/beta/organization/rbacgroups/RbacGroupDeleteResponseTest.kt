package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupDeleteResponseTest {

    @Test
    fun create() {
        val rbacGroupDeleteResponse =
            RbacGroupDeleteResponse.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        assertThat(rbacGroupDeleteResponse.id()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupDeleteResponse =
            RbacGroupDeleteResponse.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        val roundtrippedRbacGroupDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupDeleteResponse),
                jacksonTypeRef<RbacGroupDeleteResponse>(),
            )

        assertThat(roundtrippedRbacGroupDeleteResponse).isEqualTo(rbacGroupDeleteResponse)
    }
}
