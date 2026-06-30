// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentThinkingPreviewTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentThinkingPreview =
            BetaManagedAgentsAgentThinkingPreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                .build()

        assertThat(betaManagedAgentsAgentThinkingPreview.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentThinkingPreview.type())
            .isEqualTo(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentThinkingPreview =
            BetaManagedAgentsAgentThinkingPreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentThinkingPreview.Type.AGENT_THINKING)
                .build()

        val roundtrippedBetaManagedAgentsAgentThinkingPreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentThinkingPreview),
                jacksonTypeRef<BetaManagedAgentsAgentThinkingPreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentThinkingPreview)
            .isEqualTo(betaManagedAgentsAgentThinkingPreview)
    }
}
