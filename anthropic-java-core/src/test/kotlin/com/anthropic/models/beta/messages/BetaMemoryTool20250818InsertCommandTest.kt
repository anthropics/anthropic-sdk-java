// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818InsertCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818InsertCommand =
            BetaMemoryTool20250818InsertCommand.builder()
                .insertLine(2L)
                .insertText("- Review memory tool documentation\n")
                .path("/memories/todo.txt")
                .build()

        assertThat(betaMemoryTool20250818InsertCommand.insertLine()).isEqualTo(2L)
        assertThat(betaMemoryTool20250818InsertCommand.insertText())
            .isEqualTo("- Review memory tool documentation\n")
        assertThat(betaMemoryTool20250818InsertCommand.path()).isEqualTo("/memories/todo.txt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818InsertCommand =
            BetaMemoryTool20250818InsertCommand.builder()
                .insertLine(2L)
                .insertText("- Review memory tool documentation\n")
                .path("/memories/todo.txt")
                .build()

        val roundtrippedBetaMemoryTool20250818InsertCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818InsertCommand),
                jacksonTypeRef<BetaMemoryTool20250818InsertCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818InsertCommand)
            .isEqualTo(betaMemoryTool20250818InsertCommand)
    }
}
