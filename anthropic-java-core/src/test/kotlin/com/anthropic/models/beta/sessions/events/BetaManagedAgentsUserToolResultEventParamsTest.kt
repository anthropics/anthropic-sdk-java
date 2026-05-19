// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserToolResultEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserToolResultEventParams =
            BetaManagedAgentsUserToolResultEventParams.builder()
                .toolUseId("x")
                .type(BetaManagedAgentsUserToolResultEventParams.Type.USER_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsUserToolResultEventParams.toolUseId()).isEqualTo("x")
        assertThat(betaManagedAgentsUserToolResultEventParams.type())
            .isEqualTo(BetaManagedAgentsUserToolResultEventParams.Type.USER_TOOL_RESULT)
        assertThat(betaManagedAgentsUserToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserToolResultEventParams.Content.ofText(
                    BetaManagedAgentsTextBlock.builder()
                        .text("Where is my order #1234?")
                        .type(BetaManagedAgentsTextBlock.Type.TEXT)
                        .build()
                )
            )
        assertThat(betaManagedAgentsUserToolResultEventParams.isError()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserToolResultEventParams =
            BetaManagedAgentsUserToolResultEventParams.builder()
                .toolUseId("x")
                .type(BetaManagedAgentsUserToolResultEventParams.Type.USER_TOOL_RESULT)
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        val roundtrippedBetaManagedAgentsUserToolResultEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserToolResultEventParams),
                jacksonTypeRef<BetaManagedAgentsUserToolResultEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserToolResultEventParams)
            .isEqualTo(betaManagedAgentsUserToolResultEventParams)
    }
}
