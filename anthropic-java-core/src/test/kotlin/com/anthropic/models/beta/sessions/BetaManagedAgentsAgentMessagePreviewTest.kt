// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentMessagePreviewTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentMessagePreview =
            BetaManagedAgentsAgentMessagePreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                .build()

        assertThat(betaManagedAgentsAgentMessagePreview.id()).isEqualTo("id")
        assertThat(betaManagedAgentsAgentMessagePreview.type())
            .isEqualTo(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentMessagePreview =
            BetaManagedAgentsAgentMessagePreview.builder()
                .id("id")
                .type(BetaManagedAgentsAgentMessagePreview.Type.AGENT_MESSAGE)
                .build()

        val roundtrippedBetaManagedAgentsAgentMessagePreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentMessagePreview),
                jacksonTypeRef<BetaManagedAgentsAgentMessagePreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentMessagePreview)
            .isEqualTo(betaManagedAgentsAgentMessagePreview)
    }
}
