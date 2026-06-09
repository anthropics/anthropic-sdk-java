// File generated from our OpenAPI spec by Stainless.

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
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
                .build()

        assertThat(betaManagedAgentsSystemMessageEventParams.content())
            .containsExactly(
                BetaManagedAgentsSystemContentBlock.builder()
                    .text("Where is my order #1234?")
                    .type(BetaManagedAgentsSystemContentBlock.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsSystemMessageEventParams.type())
            .isEqualTo(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSystemMessageEventParams =
            BetaManagedAgentsSystemMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsSystemMessageEventParams.Type.SYSTEM_MESSAGE)
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
