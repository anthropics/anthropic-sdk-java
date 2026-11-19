package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeRbacGroupManagedSettingsSummaryTest {

    @Test
    fun create() {
        val betaClaudeCodeRbacGroupManagedSettingsSummary =
            BetaClaudeCodeRbacGroupManagedSettingsSummary.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaClaudeCodeRbacGroupManagedSettingsSummary.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeCodeRbacGroupManagedSettingsSummary.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
        assertThat(betaClaudeCodeRbacGroupManagedSettingsSummary.rbacGroupStatus())
            .isEqualTo(
                BetaClaudeCodeRbacGroupStatus.ofActive(
                    BetaClaudeCodeRbacGroupStatusActive.builder().build()
                )
            )
        assertThat(betaClaudeCodeRbacGroupManagedSettingsSummary.updatedAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeRbacGroupManagedSettingsSummary =
            BetaClaudeCodeRbacGroupManagedSettingsSummary.builder()
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .rbacGroupId("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
                .rbacGroupStatus(BetaClaudeCodeRbacGroupStatusActive.builder().build())
                .updatedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaClaudeCodeRbacGroupManagedSettingsSummary =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeRbacGroupManagedSettingsSummary),
                jacksonTypeRef<BetaClaudeCodeRbacGroupManagedSettingsSummary>(),
            )

        assertThat(roundtrippedBetaClaudeCodeRbacGroupManagedSettingsSummary)
            .isEqualTo(betaClaudeCodeRbacGroupManagedSettingsSummary)
    }
}
