package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemContentBlock
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSystemMessageEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsSystemMessageEventParams =
            BetaManagedAgentsSystemMessageEventParams.builder()
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .build()

        assertThat(betaManagedAgentsSystemMessageEventParams.content())
            .containsExactly(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSystemMessageEventParams =
            BetaManagedAgentsSystemMessageEventParams.builder()
                .addContent(BetaManagedAgentsSystemContentBlock.of("Where is my order #1234?"))
                .build()

        val roundtrippedBetaManagedAgentsSystemMessageEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSystemMessageEventParams),
                jacksonTypeRef<BetaManagedAgentsSystemMessageEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSystemMessageEventParams)
            .isEqualTo(betaManagedAgentsSystemMessageEventParams)
    }
}
