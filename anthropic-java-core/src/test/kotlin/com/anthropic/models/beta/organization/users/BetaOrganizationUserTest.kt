// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.users

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.organization.BetaOrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationUserTest {

    @Test
    fun create() {
        val betaOrganizationUser =
            BetaOrganizationUser.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .name("Jane Doe")
                .role(BetaOrganizationRole.ADMIN)
                .build()

        assertThat(betaOrganizationUser.id()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(betaOrganizationUser.addedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaOrganizationUser.email()).isEqualTo("user@emaildomain.com")
        assertThat(betaOrganizationUser.name()).isEqualTo("Jane Doe")
        assertThat(betaOrganizationUser.role()).isEqualTo(BetaOrganizationRole.ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganizationUser =
            BetaOrganizationUser.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .name("Jane Doe")
                .role(BetaOrganizationRole.ADMIN)
                .build()

        val roundtrippedBetaOrganizationUser =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganizationUser),
                jacksonTypeRef<BetaOrganizationUser>(),
            )

        assertThat(roundtrippedBetaOrganizationUser).isEqualTo(betaOrganizationUser)
    }
}
