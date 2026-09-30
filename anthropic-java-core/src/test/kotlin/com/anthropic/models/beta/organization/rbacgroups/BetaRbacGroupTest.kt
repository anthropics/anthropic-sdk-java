package com.anthropic.models.beta.organization.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRbacGroupTest {

    @Test
    fun create() {
        val betaRbacGroup =
            BetaRbacGroup.builder()
                .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Engineering")
                .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .sourceType(BetaRbacGroup.SourceType.DIRECT)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        assertThat(betaRbacGroup.id()).isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaRbacGroup.createdAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
        assertThat(betaRbacGroup.name()).isEqualTo("Engineering")
        assertThat(betaRbacGroup.roleIds().getOrNull())
            .containsExactly("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
        assertThat(betaRbacGroup.sourceType()).isEqualTo(BetaRbacGroup.SourceType.DIRECT)
        assertThat(betaRbacGroup.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacGroup =
            BetaRbacGroup.builder()
                .id("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .createdAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .name("Engineering")
                .addRoleId("rbac_role_016J8xVtKpDq3Wy9ZmN2hR4s")
                .sourceType(BetaRbacGroup.SourceType.DIRECT)
                .updatedAt(OffsetDateTime.parse("2024-10-30T23:58:27.427722Z"))
                .build()

        val roundtrippedBetaRbacGroup =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacGroup),
                jacksonTypeRef<BetaRbacGroup>(),
            )

        assertThat(roundtrippedBetaRbacGroup).isEqualTo(betaRbacGroup)
    }
}
