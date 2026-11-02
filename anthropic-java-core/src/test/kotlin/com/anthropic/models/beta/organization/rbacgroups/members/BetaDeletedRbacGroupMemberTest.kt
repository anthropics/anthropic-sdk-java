package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedRbacGroupMemberTest {

    @Test
    fun create() {
        val betaDeletedRbacGroupMember =
            BetaDeletedRbacGroupMember.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(betaDeletedRbacGroupMember.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaDeletedRbacGroupMember.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedRbacGroupMember =
            BetaDeletedRbacGroupMember.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedBetaDeletedRbacGroupMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedRbacGroupMember),
                jacksonTypeRef<BetaDeletedRbacGroupMember>(),
            )

        assertThat(roundtrippedBetaDeletedRbacGroupMember).isEqualTo(betaDeletedRbacGroupMember)
    }
}
