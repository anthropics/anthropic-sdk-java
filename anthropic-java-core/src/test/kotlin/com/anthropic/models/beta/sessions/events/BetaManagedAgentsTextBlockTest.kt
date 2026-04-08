// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsTextBlock =
            BetaManagedAgentsTextBlock.builder()
                .text("Where is my order #1234?")
                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsTextBlock.text()).isEqualTo("Where is my order #1234?")
        assertThat(betaManagedAgentsTextBlock.type())
            .isEqualTo(BetaManagedAgentsTextBlock.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextBlock =
            BetaManagedAgentsTextBlock.builder()
                .text("Where is my order #1234?")
                .type(BetaManagedAgentsTextBlock.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsTextBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextBlock),
                jacksonTypeRef<BetaManagedAgentsTextBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextBlock).isEqualTo(betaManagedAgentsTextBlock)
    }
}
