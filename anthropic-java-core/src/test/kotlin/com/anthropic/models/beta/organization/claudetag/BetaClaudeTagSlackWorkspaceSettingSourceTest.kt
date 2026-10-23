package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackWorkspaceSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackWorkspaceSettingSource =
            BetaClaudeTagSlackWorkspaceSettingSource.of("slack_workspace_id")

        assertThat(betaClaudeTagSlackWorkspaceSettingSource.slackWorkspaceId())
            .isEqualTo("slack_workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackWorkspaceSettingSource =
            BetaClaudeTagSlackWorkspaceSettingSource.of("slack_workspace_id")

        val roundtrippedBetaClaudeTagSlackWorkspaceSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackWorkspaceSettingSource),
                jacksonTypeRef<BetaClaudeTagSlackWorkspaceSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackWorkspaceSettingSource)
            .isEqualTo(betaClaudeTagSlackWorkspaceSettingSource)
    }
}
