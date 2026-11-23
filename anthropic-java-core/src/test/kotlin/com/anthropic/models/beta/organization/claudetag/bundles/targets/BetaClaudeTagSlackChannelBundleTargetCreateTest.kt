package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelBundleTargetCreateTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannelBundleTargetCreate =
            BetaClaudeTagSlackChannelBundleTargetCreate.of("slch_01SvpyRQK3nN6eH9uhRA7n1q")

        assertThat(betaClaudeTagSlackChannelBundleTargetCreate.slackChannelId())
            .isEqualTo("slch_01SvpyRQK3nN6eH9uhRA7n1q")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannelBundleTargetCreate =
            BetaClaudeTagSlackChannelBundleTargetCreate.of("slch_01SvpyRQK3nN6eH9uhRA7n1q")

        val roundtrippedBetaClaudeTagSlackChannelBundleTargetCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannelBundleTargetCreate),
                jacksonTypeRef<BetaClaudeTagSlackChannelBundleTargetCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannelBundleTargetCreate)
            .isEqualTo(betaClaudeTagSlackChannelBundleTargetCreate)
    }
}
