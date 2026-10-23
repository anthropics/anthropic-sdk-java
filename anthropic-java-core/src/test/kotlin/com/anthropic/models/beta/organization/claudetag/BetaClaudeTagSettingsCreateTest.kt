package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSettingsCreateTest {

    @Test
    fun create() {
        val betaClaudeTagSettingsCreate =
            BetaClaudeTagSettingsCreate.builder()
                .enabled(true)
                .environmentId("x")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("x")
                .build()

        assertThat(betaClaudeTagSettingsCreate.enabled()).contains(true)
        assertThat(betaClaudeTagSettingsCreate.environmentId()).contains("x")
        assertThat(betaClaudeTagSettingsCreate.guestPolicy())
            .contains(
                BetaClaudeTagGuestPolicy.ofAllow(BetaClaudeTagGuestPolicyAllow.builder().build())
            )
        assertThat(betaClaudeTagSettingsCreate.instructions()).contains("instructions")
        assertThat(betaClaudeTagSettingsCreate.model()).contains("x")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettingsCreate =
            BetaClaudeTagSettingsCreate.builder()
                .enabled(true)
                .environmentId("x")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("x")
                .build()

        val roundtrippedBetaClaudeTagSettingsCreate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettingsCreate),
                jacksonTypeRef<BetaClaudeTagSettingsCreate>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettingsCreate).isEqualTo(betaClaudeTagSettingsCreate)
    }
}
