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
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEventParams.customToolUseId())
            .isEqualTo("x")
        assertThat(betaManagedAgentsUserCustomToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEventParams.Content.ofText(
                    "Where is my order #1234?"
                )
            )
        assertThat(betaManagedAgentsUserCustomToolResultEventParams.isError()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsUserCustomToolResultEventParams =
            BetaManagedAgentsUserCustomToolResultEventParams.of("x")

        val betaManagedAgentsUserCustomToolResultEventParams =
            baseBetaManagedAgentsUserCustomToolResultEventParams
                .toBuilder()
                .addContent(
                    BetaManagedAgentsUserCustomToolResultEventParams.Content.ofText(
                        "Where is my order #1234?"
                    )
                )
                .build()

        assertThat(betaManagedAgentsUserCustomToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserCustomToolResultEventParams.Content.ofText(
                    "Where is my order #1234?"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserCustomToolResultEventParams =
            BetaManagedAgentsUserCustomToolResultEventParams.builder()
                .customToolUseId("x")
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
