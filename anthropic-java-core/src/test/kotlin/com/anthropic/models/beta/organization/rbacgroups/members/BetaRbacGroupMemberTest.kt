package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacGroupMemberTest {

    @Test
    fun create() {
        val betaRbacGroupMember =
            BetaRbacGroupMember.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .build()

        assertThat(betaRbacGroupMember.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaRbacGroupMember.email()).isEqualTo("user@emaildomain.com")
        assertThat(betaRbacGroupMember.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaRbacGroupMember.userId()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacGroupMember =
            BetaRbacGroupMember.builder()
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .build()

        val roundtrippedBetaRbacGroupMember =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacGroupMember),
                jacksonTypeRef<BetaRbacGroupMember>(),
            )

        assertThat(roundtrippedBetaRbacGroupMember).isEqualTo(betaRbacGroupMember)
    }
}
