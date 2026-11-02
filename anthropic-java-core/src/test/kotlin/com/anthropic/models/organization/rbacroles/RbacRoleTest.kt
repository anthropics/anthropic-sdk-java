package com.anthropic.models.organization.rbacroles

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacRoleTest {

    @Test
    fun create() {
        val rbacRole =
            RbacRole.builder()
                .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("Project Editor")
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(rbacRole.id()).isEqualTo("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
        assertThat(rbacRole.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(rbacRole.displayName()).isEqualTo("Project Editor")
        assertThat(rbacRole.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacRole =
            RbacRole.builder()
                .id("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .displayName("Project Editor")
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedRbacRole =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacRole),
                jacksonTypeRef<RbacRole>(),
            )

        assertThat(roundtrippedRbacRole).isEqualTo(rbacRole)
    }
}
