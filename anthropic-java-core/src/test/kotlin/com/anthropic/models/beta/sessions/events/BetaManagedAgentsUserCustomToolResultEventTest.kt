// File generated from our OpenAPI spec by Stainless.

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
                .type(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEvent.id()).isEqualTo("id")
        assertThat(betaManagedAgentsUserCustomToolResultEvent.customToolUseId())
            .isEqualTo("custom_tool_use_id")
        assertThat(betaManagedAgentsUserCustomToolResultEvent.type())
            .isEqualTo(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
        assertThat(betaManagedAgentsUserCustomToolResultEvent.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEvent.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserCustomToolResultEvent.isError()).contains(true)
        assertThat(betaManagedAgentsUserCustomToolResultEvent.processedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserCustomToolResultEvent =
            BetaManagedAgentsUserCustomToolResultEvent.builder()
                .id("id")
                .customToolUseId("custom_tool_use_id")
                .type(BetaManagedAgentsUserCustomToolResultEvent.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .processedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
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
