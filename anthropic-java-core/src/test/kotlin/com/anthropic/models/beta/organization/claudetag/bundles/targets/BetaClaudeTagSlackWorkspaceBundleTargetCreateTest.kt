package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackWorkspaceBundleTargetCreateTest {

    @Test
    fun create() {
        val betaClaudeTagSlackWorkspaceBundleTargetCreate =
            BetaClaudeTagSlackWorkspaceBundleTargetCreate.of("xxxxxxxx")

        assertThat(betaClaudeTagSlackWorkspaceBundleTargetCreate.slackWorkspaceId())
            .isEqualTo("xxxxxxxx")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackWorkspaceBundleTargetCreate =
            BetaClaudeTagSlackWorkspaceBundleTargetCreate.of("xxxxxxxx")

        val roundtrippedBetaClaudeTagSlackWorkspaceBundleTargetCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackWorkspaceBundleTargetCreate),
                jacksonTypeRef<BetaClaudeTagSlackWorkspaceBundleTargetCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackWorkspaceBundleTargetCreate)
            .isEqualTo(betaClaudeTagSlackWorkspaceBundleTargetCreate)
    }
}
