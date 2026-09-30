package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacRoleTest {

    @Test
    fun create() {
        val betaRbacRole =
            BetaRbacRole.builder()
                .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Project Editor")
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaRbacRole.id()).isEqualTo("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
        assertThat(betaRbacRole.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaRbacRole.name()).isEqualTo("Project Editor")
        assertThat(betaRbacRole.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRole =
            BetaRbacRole.builder()
                .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Project Editor")
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaRbacRole =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRole),
                jacksonTypeRef<BetaRbacRole>(),
            )

        assertThat(roundtrippedBetaRbacRole).isEqualTo(betaRbacRole)
    }
}
