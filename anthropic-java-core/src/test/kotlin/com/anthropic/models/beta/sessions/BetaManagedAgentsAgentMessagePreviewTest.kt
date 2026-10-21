package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentMessagePreviewTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentMessagePreview = BetaManagedAgentsAgentMessagePreview.of("id")

        assertThat(betaManagedAgentsAgentMessagePreview.id()).isEqualTo("id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentMessagePreview = BetaManagedAgentsAgentMessagePreview.of("id")

        val roundtrippedBetaManagedAgentsAgentMessagePreview =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentMessagePreview),
                jacksonTypeRef<BetaManagedAgentsAgentMessagePreview>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentMessagePreview)
            .isEqualTo(betaManagedAgentsAgentMessagePreview)
    }
}
