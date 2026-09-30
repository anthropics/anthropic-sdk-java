package com.anthropic.models.beta.organization.rbacgroups.members

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MemberListPageResponseTest {

    @Test
    fun create() {
        val memberListPageResponse =
            MemberListPageResponse.builder()
                .addData(
                    BetaRbacGroupMember.builder()
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .email("user@emaildomain.com")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
                .hasMore(false)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                .build()

        assertThat(memberListPageResponse.data())
            .containsExactly(
                BetaRbacGroupMember.builder()
                    .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .email("user@emaildomain.com")
                    .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                    .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .build()
            )
        assertThat(memberListPageResponse.hasMore()).isEqualTo(false)
        assertThat(memberListPageResponse.nextPage())
            .contains("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val memberListPageResponse =
            MemberListPageResponse.builder()
                .addData(
                    BetaRbacGroupMember.builder()
                        .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .email("user@emaildomain.com")
                        .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                        .userId("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .build()
                )
                .hasMore(false)
                .nextPage("eyJjdXJzb3IiOiAicmJhY19ncm91cF8wMSJ9")
                .build()

        val roundtrippedMemberListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(memberListPageResponse),
                jacksonTypeRef<MemberListPageResponse>(),
            )

        assertThat(roundtrippedMemberListPageResponse).isEqualTo(memberListPageResponse)
    }
}
