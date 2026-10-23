package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackWorkspaceBundleTargetTest {

    @Test
    fun create() {
        val betaClaudeTagSlackWorkspaceBundleTarget =
            BetaClaudeTagSlackWorkspaceBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackWorkspaceId("slack_workspace_id")
                .build()

        assertThat(betaClaudeTagSlackWorkspaceBundleTarget.id()).isEqualTo("id")
        assertThat(betaClaudeTagSlackWorkspaceBundleTarget.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaClaudeTagSlackWorkspaceBundleTarget.slackWorkspaceId())
            .isEqualTo("slack_workspace_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackWorkspaceBundleTarget =
            BetaClaudeTagSlackWorkspaceBundleTarget.builder()
                .id("id")
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .slackWorkspaceId("slack_workspace_id")
                .build()

        val roundtrippedBetaClaudeTagSlackWorkspaceBundleTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackWorkspaceBundleTarget),
                jacksonTypeRef<BetaClaudeTagSlackWorkspaceBundleTarget>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackWorkspaceBundleTarget)
            .isEqualTo(betaClaudeTagSlackWorkspaceBundleTarget)
    }
}
