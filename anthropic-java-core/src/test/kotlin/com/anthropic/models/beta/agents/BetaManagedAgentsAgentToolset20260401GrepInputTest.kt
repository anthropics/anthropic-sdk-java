// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401GrepInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401GrepInput =
            BetaManagedAgentsAgentToolset20260401GrepInput.builder()
                .pattern("pattern")
                .path("path")
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401GrepInput.pattern()).isEqualTo("pattern")
        assertThat(betaManagedAgentsAgentToolset20260401GrepInput.path()).contains("path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401GrepInput =
            BetaManagedAgentsAgentToolset20260401GrepInput.builder()
                .pattern("pattern")
                .path("path")
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401GrepInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401GrepInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401GrepInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401GrepInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401GrepInput)
    }
}
