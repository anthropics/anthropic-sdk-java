package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagGuestPolicyAllowTest {

    @Test
    fun create() {
        val betaClaudeTagGuestPolicyAllow = BetaClaudeTagGuestPolicyAllow.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagGuestPolicyAllow = BetaClaudeTagGuestPolicyAllow.builder().build()

        val roundtrippedBetaClaudeTagGuestPolicyAllow =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagGuestPolicyAllow),
                jacksonTypeRef<BetaClaudeTagGuestPolicyAllow>(),
            )

        assertThat(roundtrippedBetaClaudeTagGuestPolicyAllow)
            .isEqualTo(betaClaudeTagGuestPolicyAllow)
    }
}
