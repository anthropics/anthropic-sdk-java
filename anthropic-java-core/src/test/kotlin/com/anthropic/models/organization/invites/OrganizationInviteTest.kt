package com.anthropic.models.organization.invites

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.OrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationInviteTest {

    @Test
    fun create() {
        val organizationInvite =
            OrganizationInvite.builder()
                .id("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
                .acceptedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .email("user@emaildomain.com")
                .expiresAt(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
                .invitedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .addRbacGroupId("string")
                .role(OrganizationRole.ADMIN)
                .status(OrganizationInvite.Status.PENDING)
                .build()

        assertThat(organizationInvite.id()).isEqualTo("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
        assertThat(organizationInvite.acceptedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(organizationInvite.email()).isEqualTo("user@emaildomain.com")
        assertThat(organizationInvite.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
        assertThat(organizationInvite.invitedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(organizationInvite.rbacGroupIds()).containsExactly("string")
        assertThat(organizationInvite.role()).isEqualTo(OrganizationRole.ADMIN)
        assertThat(organizationInvite.status()).isEqualTo(OrganizationInvite.Status.PENDING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationInvite =
            OrganizationInvite.builder()
                .id("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
                .acceptedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .email("user@emaildomain.com")
                .expiresAt(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
                .invitedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .addRbacGroupId("string")
                .role(OrganizationRole.ADMIN)
                .status(OrganizationInvite.Status.PENDING)
                .build()

        val roundtrippedOrganizationInvite =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationInvite),
                jacksonTypeRef<OrganizationInvite>(),
            )

        assertThat(roundtrippedOrganizationInvite).isEqualTo(organizationInvite)
    }
}
