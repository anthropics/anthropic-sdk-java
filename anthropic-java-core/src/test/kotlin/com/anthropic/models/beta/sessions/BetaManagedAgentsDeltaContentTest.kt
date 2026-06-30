// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsDeltaContentTest {

    @Test
    fun create() {
        val betaManagedAgentsDeltaContent =
            BetaManagedAgentsDeltaContent.builder()
                .content(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
                .type(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
                .index(0L)
                .build()

        assertThat(betaManagedAgentsDeltaContent.content())
            .isEqualTo(
                BetaManagedAgentsTextBlock.builder()
                    .text("Where is my order #1234?")
                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsDeltaContent.type())
            .isEqualTo(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
        assertThat(betaManagedAgentsDeltaContent.index()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeltaContent =
            BetaManagedAgentsDeltaContent.builder()
                .content(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
                .type(BetaManagedAgentsDeltaContent.Type.CONTENT_DELTA)
                .index(0L)
                .build()

        val roundtrippedBetaManagedAgentsDeltaContent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeltaContent),
                jacksonTypeRef<BetaManagedAgentsDeltaContent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeltaContent)
            .isEqualTo(betaManagedAgentsDeltaContent)
    }
}
