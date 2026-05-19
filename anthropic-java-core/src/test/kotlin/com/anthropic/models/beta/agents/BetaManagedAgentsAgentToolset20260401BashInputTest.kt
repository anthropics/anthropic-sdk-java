// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401BashInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401BashInput =
            BetaManagedAgentsAgentToolset20260401BashInput.builder()
                .command("command")
                .restart(true)
                .timeoutMs(0L)
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401BashInput.command()).contains("command")
        assertThat(betaManagedAgentsAgentToolset20260401BashInput.restart()).contains(true)
        assertThat(betaManagedAgentsAgentToolset20260401BashInput.timeoutMs()).contains(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401BashInput =
            BetaManagedAgentsAgentToolset20260401BashInput.builder()
                .command("command")
                .restart(true)
                .timeoutMs(0L)
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401BashInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401BashInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401BashInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401BashInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401BashInput)
    }
}
