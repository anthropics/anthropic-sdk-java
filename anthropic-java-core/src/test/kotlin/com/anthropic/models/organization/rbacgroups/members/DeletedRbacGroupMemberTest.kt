package com.anthropic.models.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeletedRbacGroupMemberTest {

    @Test
    fun create() {
        val deletedRbacGroupMember =
            DeletedRbacGroupMember.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(deletedRbacGroupMember.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(deletedRbacGroupMember.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val deletedRbacGroupMember =
            DeletedRbacGroupMember.builder()
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedDeletedRbacGroupMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(deletedRbacGroupMember),
                jacksonTypeRef<DeletedRbacGroupMember>(),
            )

        assertThat(roundtrippedDeletedRbacGroupMember).isEqualTo(deletedRbacGroupMember)
    }
}
