// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSystemMessageEventTest {

    @Test
    fun create() {
        val betaManagedAgentsSystemMessageEvent =
            BetaManagedAgentsSystemMessageEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsSystemMessageEvent.Type.SYSTEM_MESSAGE)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsSystemMessageEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsSystemMessageEvent.content())
            .containsExactly(
                BetaManagedAgentsSystemContentBlock.builder()
                    .text("Where is my order #1234?")
                    .type(BetaManagedAgentsSystemContentBlock.Type.TEXT)
                    .build()
            )
        assertThat(betaManagedAgentsSystemMessageEvent.type())
            .isEqualTo(BetaManagedAgentsSystemMessageEvent.Type.SYSTEM_MESSAGE)
        assertThat(betaManagedAgentsSystemMessageEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSystemMessageEvent =
            BetaManagedAgentsSystemMessageEvent.builder()
                .id("id")
                .addTextContent("Where is my order #1234?")
                .type(BetaManagedAgentsSystemMessageEvent.Type.SYSTEM_MESSAGE)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsSystemMessageEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSystemMessageEvent),
                jacksonTypeRef<BetaManagedAgentsSystemMessageEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSystemMessageEvent)
            .isEqualTo(betaManagedAgentsSystemMessageEvent)
    }
}
