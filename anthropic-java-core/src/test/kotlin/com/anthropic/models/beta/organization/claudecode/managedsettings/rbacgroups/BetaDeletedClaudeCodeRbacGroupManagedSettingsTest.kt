package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDeletedClaudeCodeRbacGroupManagedSettingsTest {

    @Test
    fun create() {
        val betaDeletedClaudeCodeRbacGroupManagedSettings =
            BetaDeletedClaudeCodeRbacGroupManagedSettings.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        assertThat(betaDeletedClaudeCodeRbacGroupManagedSettings.rbacGroupId())
            .isEqualTo("rbac_group_012rppKaSVsmTo6NqRDXQXNF")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDeletedClaudeCodeRbacGroupManagedSettings =
            BetaDeletedClaudeCodeRbacGroupManagedSettings.of("rbac_group_012rppKaSVsmTo6NqRDXQXNF")

        val roundtrippedBetaDeletedClaudeCodeRbacGroupManagedSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDeletedClaudeCodeRbacGroupManagedSettings),
                jacksonTypeRef<BetaDeletedClaudeCodeRbacGroupManagedSettings>(),
            )

        assertThat(roundtrippedBetaDeletedClaudeCodeRbacGroupManagedSettings)
            .isEqualTo(betaDeletedClaudeCodeRbacGroupManagedSettings)
    }
}
