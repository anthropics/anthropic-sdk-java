// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401EditInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401EditInput =
            BetaManagedAgentsAgentToolset20260401EditInput.builder()
                .filePath("file_path")
                .newString("new_string")
                .oldString("old_string")
                .replaceAll(true)
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401EditInput.filePath()).isEqualTo("file_path")
        assertThat(betaManagedAgentsAgentToolset20260401EditInput.newString())
            .isEqualTo("new_string")
        assertThat(betaManagedAgentsAgentToolset20260401EditInput.oldString())
            .isEqualTo("old_string")
        assertThat(betaManagedAgentsAgentToolset20260401EditInput.replaceAll()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401EditInput =
            BetaManagedAgentsAgentToolset20260401EditInput.builder()
                .filePath("file_path")
                .newString("new_string")
                .oldString("old_string")
                .replaceAll(true)
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401EditInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401EditInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401EditInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401EditInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401EditInput)
    }
}
