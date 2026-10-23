package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagDeletedSlackChannelTest {

    @Test
    fun create() {
        val betaClaudeTagDeletedSlackChannel = BetaClaudeTagDeletedSlackChannel.of("id")

        assertThat(betaClaudeTagDeletedSlackChannel.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagDeletedSlackChannel = BetaClaudeTagDeletedSlackChannel.of("id")

        val roundtrippedBetaClaudeTagDeletedSlackChannel =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagDeletedSlackChannel),
                jacksonTypeRef<BetaClaudeTagDeletedSlackChannel>(),
            )

        assertThat(roundtrippedBetaClaudeTagDeletedSlackChannel)
            .isEqualTo(betaClaudeTagDeletedSlackChannel)
    }
}
