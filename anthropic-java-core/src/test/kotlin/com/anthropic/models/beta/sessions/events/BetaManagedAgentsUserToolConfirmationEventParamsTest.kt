// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserToolConfirmationEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserToolConfirmationEventParams =
            BetaManagedAgentsUserToolConfirmationEventParams.builder()
                .result(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
                .toolUseId("x")
                .type(BetaManagedAgentsUserToolConfirmationEventParams.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .build()

        assertThat(betaManagedAgentsUserToolConfirmationEventParams.result())
            .isEqualTo(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
        assertThat(betaManagedAgentsUserToolConfirmationEventParams.toolUseId()).isEqualTo("x")
        assertThat(betaManagedAgentsUserToolConfirmationEventParams.type())
            .isEqualTo(BetaManagedAgentsUserToolConfirmationEventParams.Type.USER_TOOL_CONFIRMATION)
        assertThat(betaManagedAgentsUserToolConfirmationEventParams.denyMessage())
            .contains("deny_message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserToolConfirmationEventParams =
            BetaManagedAgentsUserToolConfirmationEventParams.builder()
                .result(BetaManagedAgentsUserToolConfirmationEventParams.Result.ALLOW)
                .toolUseId("x")
                .type(BetaManagedAgentsUserToolConfirmationEventParams.Type.USER_TOOL_CONFIRMATION)
                .denyMessage("deny_message")
                .build()

        val roundtrippedBetaManagedAgentsUserToolConfirmationEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserToolConfirmationEventParams),
                jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserToolConfirmationEventParams)
            .isEqualTo(betaManagedAgentsUserToolConfirmationEventParams)
    }
}
