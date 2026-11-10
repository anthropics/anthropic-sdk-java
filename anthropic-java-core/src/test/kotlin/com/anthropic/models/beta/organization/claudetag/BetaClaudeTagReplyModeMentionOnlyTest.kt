package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagReplyModeMentionOnlyTest {

    @Test
    fun create() {
        val betaClaudeTagReplyModeMentionOnly = BetaClaudeTagReplyModeMentionOnly.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyModeMentionOnly = BetaClaudeTagReplyModeMentionOnly.builder().build()

        val roundtrippedBetaClaudeTagReplyModeMentionOnly =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyModeMentionOnly),
                jacksonTypeRef<BetaClaudeTagReplyModeMentionOnly>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyModeMentionOnly)
            .isEqualTo(betaClaudeTagReplyModeMentionOnly)
    }
}
