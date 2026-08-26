// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.invites

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InviteListPageResponseTest {

    @Test
    fun create() {
        val inviteListPageResponse =
            InviteListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(inviteListPageResponse.data())
            .containsExactly(
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
            )
        assertThat(inviteListPageResponse.firstId()).contains("first_id")
        assertThat(inviteListPageResponse.hasMore()).isEqualTo(true)
        assertThat(inviteListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val inviteListPageResponse =
            InviteListPageResponse.builder()
                .addData(
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
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedInviteListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(inviteListPageResponse),
                jacksonTypeRef<InviteListPageResponse>(),
            )

        assertThat(roundtrippedInviteListPageResponse).isEqualTo(inviteListPageResponse)
    }
}
