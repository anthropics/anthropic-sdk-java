// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserToolConfirmationEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserToolConfirmationEvent =
            BetaManagedAgentsUserToolConfirmationEvent.builder()
                .id("id")
                .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsUserToolConfirmationEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsUserToolConfirmationEvent.result())
            .isEqualTo(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
        assertThat(betaManagedAgentsUserToolConfirmationEvent.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaManagedAgentsUserToolConfirmationEvent.type())
            .isEqualTo(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
        assertThat(betaManagedAgentsUserToolConfirmationEvent.denyMessage())
            .contains("deny_message")
        assertThat(betaManagedAgentsUserToolConfirmationEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsUserToolConfirmationEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserToolConfirmationEvent =
            BetaManagedAgentsUserToolConfirmationEvent.builder()
                .id("id")
                .result(BetaManagedAgentsUserToolConfirmationEvent.Result.ALLOW)
                .toolUseId("tool_use_id")
                .type(BetaManagedAgentsUserToolConfirmationEvent.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsUserToolConfirmationEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserToolConfirmationEvent),
                jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserToolConfirmationEvent)
            .isEqualTo(betaManagedAgentsUserToolConfirmationEvent)
    }
}
