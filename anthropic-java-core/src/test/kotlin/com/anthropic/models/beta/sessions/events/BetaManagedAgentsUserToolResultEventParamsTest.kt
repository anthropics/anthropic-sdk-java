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
                .addTextContent("Where is my order #1234?")
                .isError(true)
                .build()

        assertThat(betaManagedAgentsUserToolResultEventParams.toolUseId()).isEqualTo("x")
        assertThat(betaManagedAgentsUserToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserToolResultEventParams.Content.ofText(
                    "Where is my order #1234?"
                )
            )
        assertThat(betaManagedAgentsUserToolResultEventParams.isError()).contains(true)
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaManagedAgentsUserToolResultEventParams =
            BetaManagedAgentsUserToolResultEventParams.of("x")

        val betaManagedAgentsUserToolResultEventParams =
            baseBetaManagedAgentsUserToolResultEventParams
                .toBuilder()
                .addContent(
                    BetaManagedAgentsUserToolResultEventParams.Content.ofText(
                        "Where is my order #1234?"
                    )
                )
                .build()

        assertThat(betaManagedAgentsUserToolResultEventParams.content().getOrNull())
            .containsExactly(
                BetaManagedAgentsUserToolResultEventParams.Content.ofText(
                    "Where is my order #1234?"
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserToolResultEventParams =
            BetaManagedAgentsUserToolResultEventParams.builder()
                .toolUseId("x")
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
