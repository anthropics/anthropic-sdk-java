package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagGuestPolicyRestrictTest {

    @Test
    fun create() {
        val betaClaudeTagGuestPolicyRestrict = BetaClaudeTagGuestPolicyRestrict.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagGuestPolicyRestrict = BetaClaudeTagGuestPolicyRestrict.builder().build()

        val roundtrippedBetaClaudeTagGuestPolicyRestrict =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagGuestPolicyRestrict),
                jacksonTypeRef<BetaClaudeTagGuestPolicyRestrict>(),
            )

        assertThat(roundtrippedBetaClaudeTagGuestPolicyRestrict)
            .isEqualTo(betaClaudeTagGuestPolicyRestrict)
    }
}
