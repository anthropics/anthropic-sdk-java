package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagReplyModeThreadTest {

    @Test
    fun create() {
        val betaClaudeTagReplyModeThread = BetaClaudeTagReplyModeThread.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyModeThread = BetaClaudeTagReplyModeThread.builder().build()

        val roundtrippedBetaClaudeTagReplyModeThread =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyModeThread),
                jacksonTypeRef<BetaClaudeTagReplyModeThread>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyModeThread).isEqualTo(betaClaudeTagReplyModeThread)
    }
}
