package com.anthropic.models.organization.users

import com.anthropic.core.jsonMapper
import com.anthropic.models.organization.OrganizationRole
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationUserTest {

    @Test
    fun create() {
        val organizationUser =
            OrganizationUser.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .name("Jane Doe")
                .role(OrganizationRole.ADMIN)
                .build()

        assertThat(organizationUser.id()).isEqualTo("user_01WCz1FkmYMm4gnmykNKUu3Q")
        assertThat(organizationUser.addedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(organizationUser.email()).isEqualTo("user@emaildomain.com")
        assertThat(organizationUser.name()).isEqualTo("Jane Doe")
        assertThat(organizationUser.role()).isEqualTo(OrganizationRole.ADMIN)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationUser =
            OrganizationUser.builder()
                .id("user_01WCz1FkmYMm4gnmykNKUu3Q")
                .addedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .email("user@emaildomain.com")
                .name("Jane Doe")
                .role(OrganizationRole.ADMIN)
                .build()

        val roundtrippedOrganizationUser =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationUser),
                jacksonTypeRef<OrganizationUser>(),
            )

        assertThat(roundtrippedOrganizationUser).isEqualTo(organizationUser)
    }
}
