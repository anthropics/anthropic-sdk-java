package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackGridChannelSettingSourceTest {

    @Test
    fun create() {
        val betaClaudeTagSlackGridChannelSettingSource =
            BetaClaudeTagSlackGridChannelSettingSource.of(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("C0456EFGH")
                    .workspaceId("T0123ABCD")
                    .build()
            )

        assertThat(betaClaudeTagSlackGridChannelSettingSource.slack())
            .isEqualTo(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("C0456EFGH")
                    .workspaceId("T0123ABCD")
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackGridChannelSettingSource =
            BetaClaudeTagSlackGridChannelSettingSource.of(
                BetaClaudeTagSlackChannelIdentifiers.builder()
                    .channelId("C0456EFGH")
                    .workspaceId("T0123ABCD")
                    .build()
            )

        val roundtrippedBetaClaudeTagSlackGridChannelSettingSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackGridChannelSettingSource),
                jacksonTypeRef<BetaClaudeTagSlackGridChannelSettingSource>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackGridChannelSettingSource)
            .isEqualTo(betaClaudeTagSlackGridChannelSettingSource)
    }
}
