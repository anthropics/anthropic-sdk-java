package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagReplyModeAlwaysAwakeTest {

    @Test
    fun create() {
        val betaClaudeTagReplyModeAlwaysAwake = BetaClaudeTagReplyModeAlwaysAwake.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyModeAlwaysAwake = BetaClaudeTagReplyModeAlwaysAwake.builder().build()

        val roundtrippedBetaClaudeTagReplyModeAlwaysAwake =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyModeAlwaysAwake),
                jacksonTypeRef<BetaClaudeTagReplyModeAlwaysAwake>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyModeAlwaysAwake)
            .isEqualTo(betaClaudeTagReplyModeAlwaysAwake)
    }
}
