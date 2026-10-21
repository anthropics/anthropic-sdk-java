package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsTextBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsTextBlock = BetaManagedAgentsTextBlock.of("Where is my order #1234?")

        assertThat(betaManagedAgentsTextBlock.text()).isEqualTo("Where is my order #1234?")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsTextBlock = BetaManagedAgentsTextBlock.of("Where is my order #1234?")

        val roundtrippedBetaManagedAgentsTextBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsTextBlock),
                jacksonTypeRef<BetaManagedAgentsTextBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsTextBlock).isEqualTo(betaManagedAgentsTextBlock)
    }
}
