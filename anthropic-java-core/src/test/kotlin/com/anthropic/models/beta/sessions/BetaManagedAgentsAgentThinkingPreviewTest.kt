package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThinkingPreviewTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThinkingPreview = BetaManagedAgentsAgentThinkingPreview.of("id")

        assertThat(betaManagedAgentsAgentThinkingPreview.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThinkingPreview = BetaManagedAgentsAgentThinkingPreview.of("id")

        val roundtrippedBetaManagedAgentsAgentThinkingPreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThinkingPreview),
                jacksonTypeRef<BetaManagedAgentsAgentThinkingPreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThinkingPreview)
            .isEqualTo(betaManagedAgentsAgentThinkingPreview)
    }
}
