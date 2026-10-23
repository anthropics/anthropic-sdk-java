package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackWorkspaceIdentifiersTest {

    @Test
    fun create() {
        val betaClaudeTagSlackWorkspaceIdentifiers =
            BetaClaudeTagSlackWorkspaceIdentifiers.of("workspace_id")

        assertThat(betaClaudeTagSlackWorkspaceIdentifiers.workspaceId()).isEqualTo("workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackWorkspaceIdentifiers =
            BetaClaudeTagSlackWorkspaceIdentifiers.of("workspace_id")

        val roundtrippedBetaClaudeTagSlackWorkspaceIdentifiers =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackWorkspaceIdentifiers),
                jacksonTypeRef<BetaClaudeTagSlackWorkspaceIdentifiers>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackWorkspaceIdentifiers)
            .isEqualTo(betaClaudeTagSlackWorkspaceIdentifiers)
    }
}
