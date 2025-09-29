// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818DeleteCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818DeleteCommand =
            BetaMemoryTool20250818DeleteCommand.builder().path("/memories/old_file.txt").build()

        assertThat(betaMemoryTool20250818DeleteCommand.path()).isEqualTo("/memories/old_file.txt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818DeleteCommand =
            BetaMemoryTool20250818DeleteCommand.builder().path("/memories/old_file.txt").build()

        val roundtrippedBetaMemoryTool20250818DeleteCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818DeleteCommand),
                jacksonTypeRef<BetaMemoryTool20250818DeleteCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818DeleteCommand)
            .isEqualTo(betaMemoryTool20250818DeleteCommand)
    }
}
