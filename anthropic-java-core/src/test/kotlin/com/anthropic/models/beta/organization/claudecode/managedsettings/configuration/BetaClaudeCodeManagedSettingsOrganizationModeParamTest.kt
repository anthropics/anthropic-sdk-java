package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeManagedSettingsOrganizationModeParamTest {

    @Test
    fun create() {
        val betaClaudeCodeManagedSettingsOrganizationModeParam =
            BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsOrganizationModeParam =
            BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build()

        val roundtrippedBetaClaudeCodeManagedSettingsOrganizationModeParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsOrganizationModeParam),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsOrganizationModeParam>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsOrganizationModeParam)
            .isEqualTo(betaClaudeCodeManagedSettingsOrganizationModeParam)
    }
}
