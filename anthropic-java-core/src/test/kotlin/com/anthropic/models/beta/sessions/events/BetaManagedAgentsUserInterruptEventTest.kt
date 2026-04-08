// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserInterruptEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserInterruptEvent =
            BetaManagedAgentsUserInterruptEvent.builder()
                .id("id")
                .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsUserInterruptEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsUserInterruptEvent.type())
            .isEqualTo(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
        assertThat(betaManagedAgentsUserInterruptEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserInterruptEvent =
            BetaManagedAgentsUserInterruptEvent.builder()
                .id("id")
                .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        val roundtrippedBetaManagedAgentsUserInterruptEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserInterruptEvent),
                jacksonTypeRef<BetaManagedAgentsUserInterruptEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserInterruptEvent)
            .isEqualTo(betaManagedAgentsUserInterruptEvent)
    }
}
