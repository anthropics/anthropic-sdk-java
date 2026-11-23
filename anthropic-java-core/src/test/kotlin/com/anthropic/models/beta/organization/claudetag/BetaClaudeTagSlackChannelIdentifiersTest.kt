package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSlackChannelIdentifiersTest {

    @Test
    fun create() {
        val betaClaudeTagSlackChannelIdentifiers =
            BetaClaudeTagSlackChannelIdentifiers.builder()
                .channelId("C0456EFGH")
                .workspaceId("T0123ABCD")
                .build()

        assertThat(betaClaudeTagSlackChannelIdentifiers.channelId()).isEqualTo("C0456EFGH")
        assertThat(betaClaudeTagSlackChannelIdentifiers.workspaceId()).isEqualTo("T0123ABCD")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSlackChannelIdentifiers =
            BetaClaudeTagSlackChannelIdentifiers.builder()
                .channelId("C0456EFGH")
                .workspaceId("T0123ABCD")
                .build()

        val roundtrippedBetaClaudeTagSlackChannelIdentifiers =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSlackChannelIdentifiers),
                jacksonTypeRef<BetaClaudeTagSlackChannelIdentifiers>(),
            )

        assertThat(roundtrippedBetaClaudeTagSlackChannelIdentifiers)
            .isEqualTo(betaClaudeTagSlackChannelIdentifiers)
    }
}
