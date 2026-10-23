package com.anthropic.models.beta.organization.claudetag

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaClaudeTagSettingsTest {

    @Test
    fun create() {
        val betaClaudeTagSettings =
            BetaClaudeTagSettings.builder()
                .enabled(true)
                .environmentId("environment_id")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("model")
                .build()

        assertThat(betaClaudeTagSettings.enabled()).contains(true)
        assertThat(betaClaudeTagSettings.environmentId()).contains("environment_id")
        assertThat(betaClaudeTagSettings.guestPolicy())
            .contains(
                BetaClaudeTagGuestPolicy.ofAllow(BetaClaudeTagGuestPolicyAllow.builder().build())
            )
        assertThat(betaClaudeTagSettings.instructions()).contains("instructions")
        assertThat(betaClaudeTagSettings.model()).contains("model")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeTagSettings =
            BetaClaudeTagSettings.builder()
                .enabled(true)
                .environmentId("environment_id")
                .guestPolicy(BetaClaudeTagGuestPolicyAllow.builder().build())
                .instructions("instructions")
                .model("model")
                .build()

        val roundtrippedBetaClaudeTagSettings =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeTagSettings),
                jacksonTypeRef<BetaClaudeTagSettings>(),
            )

        assertThat(roundtrippedBetaClaudeTagSettings).isEqualTo(betaClaudeTagSettings)
    }
}
