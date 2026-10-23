package com.anthropic.models.beta.organization.claudetag.bundles.targets

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelBundleTargetCreateTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannelBundleTargetCreate =
            BetaClaudeTagSlackChannelBundleTargetCreate.of("xxxxxxxx")

        assertThat(betaClaudeTagSlackChannelBundleTargetCreate.slackChannelId())
            .isEqualTo("xxxxxxxx")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannelBundleTargetCreate =
            BetaClaudeTagSlackChannelBundleTargetCreate.of("xxxxxxxx")

        val roundtrippedBetaClaudeTagSlackChannelBundleTargetCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannelBundleTargetCreate),
                jacksonTypeRef<BetaClaudeTagSlackChannelBundleTargetCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannelBundleTargetCreate)
            .isEqualTo(betaClaudeTagSlackChannelBundleTargetCreate)
    }
}
