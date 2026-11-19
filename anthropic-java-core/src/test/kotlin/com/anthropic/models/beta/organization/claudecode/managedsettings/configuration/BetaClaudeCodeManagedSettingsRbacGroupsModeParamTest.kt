package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeManagedSettingsRbacGroupsModeParamTest {

    @Test
    fun create() {
        val betaClaudeCodeManagedSettingsRbacGroupsModeParam =
            BetaClaudeCodeManagedSettingsRbacGroupsModeParam.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsRbacGroupsModeParam =
            BetaClaudeCodeManagedSettingsRbacGroupsModeParam.builder().build()

        val roundtrippedBetaClaudeCodeManagedSettingsRbacGroupsModeParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsRbacGroupsModeParam),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsRbacGroupsModeParam>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsRbacGroupsModeParam)
            .isEqualTo(betaClaudeCodeManagedSettingsRbacGroupsModeParam)
    }
}
