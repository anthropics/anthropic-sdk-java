// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818CreateCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818CreateCommand =
            BetaMemoryTool20250818CreateCommand.builder()
                .fileText("Meeting notes:\n- Discussed project timeline\n- Next steps defined\n")
                .path("/memories/notes.txt")
                .build()

        assertThat(betaMemoryTool20250818CreateCommand.fileText())
            .isEqualTo("Meeting notes:\n- Discussed project timeline\n- Next steps defined\n")
        assertThat(betaMemoryTool20250818CreateCommand.path()).isEqualTo("/memories/notes.txt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818CreateCommand =
            BetaMemoryTool20250818CreateCommand.builder()
                .fileText("Meeting notes:\n- Discussed project timeline\n- Next steps defined\n")
                .path("/memories/notes.txt")
                .build()

        val roundtrippedBetaMemoryTool20250818CreateCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818CreateCommand),
                jacksonTypeRef<BetaMemoryTool20250818CreateCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818CreateCommand)
            .isEqualTo(betaMemoryTool20250818CreateCommand)
    }
}
