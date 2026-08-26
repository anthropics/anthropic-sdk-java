// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserListPageResponseTest {

    @Test
    fun create() {
        val userListPageResponse =
            UserListPageResponse.builder()
                .addData(
                    BetaOrganizationUser.builder()
                        .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .email("user@emaildomain.com")
                        .name("Jane Doe")
                        .role(BetaOrganizationRole.ADMIN)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        assertThat(userListPageResponse.data())
            .containsExactly(
                BetaOrganizationUser.builder()
                    .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                    .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                    .email("user@emaildomain.com")
                    .name("Jane Doe")
                    .role(BetaOrganizationRole.ADMIN)
                    .build()
            )
        assertThat(userListPageResponse.firstId()).contains("first_id")
        assertThat(userListPageResponse.hasMore()).isEqualTo(true)
        assertThat(userListPageResponse.lastId()).contains("last_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val userListPageResponse =
            UserListPageResponse.builder()
                .addData(
                    BetaOrganizationUser.builder()
                        .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                        .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                        .email("user@emaildomain.com")
                        .name("Jane Doe")
                        .role(BetaOrganizationRole.ADMIN)
                        .build()
                )
                .firstId("first_id")
                .hasMore(true)
                .lastId("last_id")
                .build()

        val roundtrippedUserListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(userListPageResponse),
                jacksonTypeRef<UserListPageResponse>(),
            )

        assertThat(roundtrippedUserListPageResponse).isEqualTo(userListPageResponse)
    }
}
