// File generated from our OpenAPI spec by Stainless.

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
                .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                .build()

        assertThat(betaManagedAgentsUserMessageEventParams.content())
            .containsExactly(
                BetaManagedAgentsUserMessageEventParams.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserMessageEventParams.type())
            .isEqualTo(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserMessageEventParams =
            BetaManagedAgentsUserMessageEventParams.builder()
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
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
