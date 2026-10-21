package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserCustomToolResultEventTest {

    @Test
    fun create() {
        val betaManagedAgentsUserCustomToolResultEvent =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsUserCustomToolResultEvent.customToolUseId())
            .isEqualTo("custom_tool_use_id")
        assertThat(betaManagedAgentsUserCustomToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEvent.Content.ofText(
                    "Where is my order #1234?"
                )
            )
        assertThat(betaManagedAgentsUserCustomToolResultEvent.isError()).contains(true)
        assertThat(betaManagedAgentsUserCustomToolResultEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaManagedAgentsUserCustomToolResultEvent.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsUserCustomToolResultEvent =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .build()

        val betaManagedAgentsUserCustomToolResultEvent =
            baseBetaManagedAgentsUserCustomToolResultEvent
                .toBuilder()
                .addContent(
                    BetaManagedAgentsUserCustomToolResultEvent.Content.ofText(
                        "Where is my order #1234?"
                    )
                )
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEvent.Content.ofText(
                    "Where is my order #1234?"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserCustomToolResultEvent =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsUserCustomToolResultEvent =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserCustomToolResultEvent),
                jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEvent>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserCustomToolResultEvent)
            .isEqualTo(betaManagedAgentsUserCustomToolResultEvent)
    }
}
