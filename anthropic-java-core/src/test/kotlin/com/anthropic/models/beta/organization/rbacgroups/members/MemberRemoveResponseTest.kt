package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberRemoveResponseTest {

    @Test
    fun create() {
        val memberRemoveResponse =
            MemberRemoveResponse.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(memberRemoveResponse.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(memberRemoveResponse.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memberRemoveResponse =
            MemberRemoveResponse.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedMemberRemoveResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memberRemoveResponse),
                jacksonTypeRef<MemberRemoveResponse>(),
            )

        assertThat(roundtrippedMemberRemoveResponse).isEqualTo(memberRemoveResponse)
    }
}
