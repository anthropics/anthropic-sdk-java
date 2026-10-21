package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserMessageEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserMessageEventParams =
            BetaManagedAgentsUserMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .build()

        assertThat(betaManagedAgentsUserMessageEventParams.content())
            .containsExactly(
                BetaManagedAgentsUserMessageEventParams.Content.ofText("Where is my order #1234?")
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserMessageEventParams =
            BetaManagedAgentsUserMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .build()

        val roundtrippedBetaManagedAgentsUserMessageEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserMessageEventParams),
                jacksonTypeRef<BetaManagedAgentsUserMessageEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserMessageEventParams)
            .isEqualTo(betaManagedAgentsUserMessageEventParams)
    }
}
