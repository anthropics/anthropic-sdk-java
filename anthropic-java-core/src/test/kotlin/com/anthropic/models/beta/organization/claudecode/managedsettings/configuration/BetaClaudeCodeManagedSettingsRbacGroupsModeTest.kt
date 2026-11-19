package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeCodeManagedSettingsRbacGroupsModeTest {

    @Test
    fun create() {
        val betaClaudeCodeManagedSettingsRbacGroupsMode =
            BetaClaudeCodeManagedSettingsRbacGroupsMode.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsRbacGroupsMode =
            BetaClaudeCodeManagedSettingsRbacGroupsMode.builder().build()

        val roundtrippedBetaClaudeCodeManagedSettingsRbacGroupsMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsRbacGroupsMode),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsRbacGroupsMode>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsRbacGroupsMode)
            .isEqualTo(betaClaudeCodeManagedSettingsRbacGroupsMode)
    }
}
