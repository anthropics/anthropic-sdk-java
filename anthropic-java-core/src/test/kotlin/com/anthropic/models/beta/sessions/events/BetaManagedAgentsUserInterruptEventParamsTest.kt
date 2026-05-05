// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsUserInterruptEventParamsTest {

    @Test
    fun create() {
        val betaManagedAgentsUserInterruptEventParams =
            BetaManagedAgentsUserInterruptEventParams.builder()
                .type(BetaManagedAgentsUserInterruptEventParams.Type.USER_INTERRUPT)
                .sessionThreadId("session_thread_id")
                .build()

        assertThat(betaManagedAgentsUserInterruptEventParams.type())
            .isEqualTo(BetaManagedAgentsUserInterruptEventParams.Type.USER_INTERRUPT)
        assertThat(betaManagedAgentsUserInterruptEventParams.sessionThreadId())
            .contains("session_thread_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsUserInterruptEventParams =
            BetaManagedAgentsUserInterruptEventParams.builder()
                .type(BetaManagedAgentsUserInterruptEventParams.Type.USER_INTERRUPT)
                .sessionThreadId("session_thread_id")
                .build()

        val roundtrippedBetaManagedAgentsUserInterruptEventParams =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsUserInterruptEventParams),
                jacksonTypeRef<BetaManagedAgentsUserInterruptEventParams>(),
            )

        assertThat(roundtrippedBetaManagedAgentsUserInterruptEventParams)
            .isEqualTo(betaManagedAgentsUserInterruptEventParams)
    }
}
