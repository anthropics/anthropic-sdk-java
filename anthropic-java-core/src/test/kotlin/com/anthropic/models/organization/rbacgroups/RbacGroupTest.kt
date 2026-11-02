package com.anthropic.models.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RbacGroupTest {

    @Test
    fun create() {
        val rbacGroup =
            RbacGroup.builder()
                .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Engineering")
                .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .sourceType(RbacGroup.SourceType.DIRECT)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(rbacGroup.id()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(rbacGroup.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(rbacGroup.name()).isEqualTo("Engineering")
        assertThat(rbacGroup.roleIds().getOrNull())
            .containsExactly("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
        assertThat(rbacGroup.sourceType()).isEqualTo(RbacGroup.SourceType.DIRECT)
        assertThat(rbacGroup.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val rbacGroup =
            RbacGroup.builder()
                .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Engineering")
                .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .sourceType(RbacGroup.SourceType.DIRECT)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedRbacGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(rbacGroup),
                jacksonTypeRef<RbacGroup>(),
            )

        assertThat(roundtrippedRbacGroup).isEqualTo(rbacGroup)
    }
}
