package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserToolResultEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserToolResultEvent =
            BetaManagedAgentsUserToolResultEvent.builder()
                .id("id")
                .toolUseId("tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsUserToolResultEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsUserToolResultEvent.toolUseId()).isEqualTo("tool_use_id")
        assertThat(betaManagedAgentsUserToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserToolResultEvent.Content.ofText("Where is my order #1234?")
            )
        assertThat(betaManagedAgentsUserToolResultEvent.isError()).contains(true)
        assertThat(betaManagedAgentsUserToolResultEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsUserToolResultEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsUserToolResultEvent =
            BetaManagedAgentsUserToolResultEvent.builder().id("id").toolUseId("tool_use_id").build()

        val betaManagedAgentsUserToolResultEvent =
            baseBetaManagedAgentsUserToolResultEvent
                .toBuilder()
                .addContent(
                    BetaManagedAgentsUserToolResultEvent.Content.ofText("Where is my order #1234?")
                )
                .build()

        assertThat(betaManagedAgentsUserToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserToolResultEvent.Content.ofText("Where is my order #1234?")
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserToolResultEvent =
            BetaManagedAgentsUserToolResultEvent.builder()
                .id("id")
                .toolUseId("tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsUserToolResultEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserToolResultEvent),
                jacksonTypeRef<BetaManagedAgentsUserToolResultEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserToolResultEvent)
            .isEqualTo(betaManagedAgentsUserToolResultEvent)
    }
}
