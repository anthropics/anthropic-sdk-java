// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401ReadInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401ReadInput =
            BetaManagedAgentsAgentToolset20260401ReadInput.builder()
                .filePath("file_path")
                .addViewRange(0L)
                .addViewRange(0L)
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401ReadInput.filePath()).isEqualTo("file_path")
        assertThat(betaManagedAgentsAgentToolset20260401ReadInput.viewRange().getOrNull())
            .containsExactly(0L, 0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401ReadInput =
            BetaManagedAgentsAgentToolset20260401ReadInput.builder()
                .filePath("file_path")
                .addViewRange(0L)
                .addViewRange(0L)
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401ReadInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401ReadInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401ReadInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401ReadInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401ReadInput)
    }
}
