// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationInviteTest {

    @Test
    fun create() {
        val betaOrganizationInvite =
            BetaOrganizationInvite.builder()
                .id("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
                .acceptedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .email("user@emaildomain.com")
                .expiresAt(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
                .invitedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .addRbacGroupId("string")
                .role(BetaOrganizationRole.ADMIN)
                .status(BetaOrganizationInvite.Status.PENDING)
                .build()

        assertThat(betaOrganizationInvite.id()).isEqualTo("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
        assertThat(betaOrganizationInvite.acceptedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaOrganizationInvite.email()).isEqualTo("user@emaildomain.com")
        assertThat(betaOrganizationInvite.expiresAt())
            .isEqualTo(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
        assertThat(betaOrganizationInvite.invitedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaOrganizationInvite.rbacGroupIds()).containsExactly("string")
        assertThat(betaOrganizationInvite.role()).isEqualTo(BetaOrganizationRole.ADMIN)
        assertThat(betaOrganizationInvite.status()).isEqualTo(BetaOrganizationInvite.Status.PENDING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationInvite =
            BetaOrganizationInvite.builder()
                .id("invite_015gWxCN9Hfg2QhZwTK7Mdeu")
                .acceptedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .email("user@emaildomain.com")
                .expiresAt(OffsetDateTime.parse("2024-11-20T23:58:27.427722Z"))
                .invitedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .addRbacGroupId("string")
                .role(BetaOrganizationRole.ADMIN)
                .status(BetaOrganizationInvite.Status.PENDING)
                .build()

        val roundtrippedBetaOrganizationInvite =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationInvite),
                jacksonTypeRef<BetaOrganizationInvite>(),
            )

        assertThat(roundtrippedBetaOrganizationInvite).isEqualTo(betaOrganizationInvite)
    }
}
