package com.anthropic.models.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupMemberTest {

    @Test
    fun create() {
        val rbacGroupMember =
            RbacGroupMember.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(rbacGroupMember.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(rbacGroupMember.email()).isEqualTo("user@emaildomain.com")
        assertThat(rbacGroupMember.rbacGroupId()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(rbacGroupMember.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroupMember =
            RbacGroupMember.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedRbacGroupMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroupMember),
                jacksonTypeRef<RbacGroupMember>(),
            )

        assertThat(roundtrippedRbacGroupMember).isEqualTo(rbacGroupMember)
    }
}
