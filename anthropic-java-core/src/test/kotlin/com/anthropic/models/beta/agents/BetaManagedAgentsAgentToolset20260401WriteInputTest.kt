// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsAgentToolset20260401WriteInputTest {

    @Test
    fun create() {
        val betaManagedAgentsAgentToolset20260401WriteInput =
            BetaManagedAgentsAgentToolset20260401WriteInput.builder()
                .content("content")
                .filePath("file_path")
                .build()

        assertThat(betaManagedAgentsAgentToolset20260401WriteInput.content()).isEqualTo("content")
        assertThat(betaManagedAgentsAgentToolset20260401WriteInput.filePath())
            .isEqualTo("file_path")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsAgentToolset20260401WriteInput =
            BetaManagedAgentsAgentToolset20260401WriteInput.builder()
                .content("content")
                .filePath("file_path")
                .build()

        val roundtrippedBetaManagedAgentsAgentToolset20260401WriteInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsAgentToolset20260401WriteInput),
                jacksonTypeRef<BetaManagedAgentsAgentToolset20260401WriteInput>(),
            )

        assertThat(roundtrippedBetaManagedAgentsAgentToolset20260401WriteInput)
            .isEqualTo(betaManagedAgentsAgentToolset20260401WriteInput)
    }
}
