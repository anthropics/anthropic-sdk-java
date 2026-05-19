// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401GlobInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401GlobInput =
            BetaManagedAgentsAgentToolset20260401GlobInput.builder()
                .pattern("pattern")
                .path("path")
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401GlobInput.pattern()).isEqualTo("pattern")
        assertThat(betaManagedAgentsAgentToolset20260401GlobInput.path()).contains("path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401GlobInput =
            BetaManagedAgentsAgentToolset20260401GlobInput.builder()
                .pattern("pattern")
                .path("path")
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401GlobInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401GlobInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401GlobInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401GlobInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401GlobInput)
    }
}
