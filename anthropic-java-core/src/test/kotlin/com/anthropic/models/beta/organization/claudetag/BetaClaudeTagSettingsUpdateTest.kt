package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSettingsUpdateTest {

    @Test
    fun create() {
        val betaClaudeTagSettingsUpdate =
            BetaClaudeTagSettingsUpdate.builder()
                .enabled(true)
                .environmentId("x")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("x")
                .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                .build()

        assertThat(betaClaudeTagSettingsUpdate.enabled()).contains(true)
        assertThat(betaClaudeTagSettingsUpdate.environmentId()).contains("x")
        assertThat(betaClaudeTagSettingsUpdate.guestPolicy())
            .contains(
                BetaClaudeTagGuestPolicy.ofAllow(BetaClaudeTagGuestPolicyAllow.builder().build())
            )
        assertThat(betaClaudeTagSettingsUpdate.instructions()).contains("instructions")
        assertThat(betaClaudeTagSettingsUpdate.model()).contains("x")
        assertThat(betaClaudeTagSettingsUpdate.replyMode())
            .contains(
                BetaClaudeTagReplyMode.ofAutomatic(
                    BetaClaudeTagReplyModeAutomatic.builder().build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingsUpdate =
            BetaClaudeTagSettingsUpdate.builder()
                .enabled(true)
                .environmentId("x")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("x")
                .replyMode(BetaClaudeTagReplyModeAutomatic.builder().build())
                .build()

        val roundtrippedBetaClaudeTagSettingsUpdate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingsUpdate),
                jacksonTypeRef<BetaClaudeTagSettingsUpdate>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingsUpdate).isEqualTo(betaClaudeTagSettingsUpdate)
    }
}
