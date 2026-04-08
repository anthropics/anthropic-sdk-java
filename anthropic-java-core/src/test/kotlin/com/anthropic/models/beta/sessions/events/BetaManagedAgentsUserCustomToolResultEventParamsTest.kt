// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserCustomToolResultEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserCustomToolResultEventParams =
            BetaManagedAgentsUserCustomToolResultEventParams.builder()
                .customToolUseId("x")
                .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEventParams.customToolUseId())
            .isEqualTo("x")
        assertThat(betaManagedAgentsUserCustomToolResultEventParams.type())
            .isEqualTo(
                BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT
            )
        assertThat(betaManagedAgentsUserCustomToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEventParams.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserCustomToolResultEventParams.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserCustomToolResultEventParams =
            BetaManagedAgentsUserCustomToolResultEventParams.builder()
                .customToolUseId("x")
                .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val roundtrippedBetaManagedAgentsUserCustomToolResultEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserCustomToolResultEventParams),
                jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserCustomToolResultEventParams)
            .isEqualTo(betaManagedAgentsUserCustomToolResultEventParams)
    }
}
