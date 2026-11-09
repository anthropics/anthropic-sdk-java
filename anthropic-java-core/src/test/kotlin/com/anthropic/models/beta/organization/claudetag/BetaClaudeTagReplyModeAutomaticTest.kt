package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagReplyModeAutomaticTest {

    @Test
    fun create() {
        val betaClaudeTagReplyModeAutomatic = BetaClaudeTagReplyModeAutomatic.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagReplyModeAutomatic = BetaClaudeTagReplyModeAutomatic.builder().build()

        val roundtrippedBetaClaudeTagReplyModeAutomatic =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagReplyModeAutomatic),
                jacksonTypeRef<BetaClaudeTagReplyModeAutomatic>(),
            )

        assertThat(roundtrippedBetaClaudeTagReplyModeAutomatic)
            .isEqualTo(betaClaudeTagReplyModeAutomatic)
    }
}
