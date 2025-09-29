// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818RenameCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818RenameCommand =
            BetaMemoryTool20250818RenameCommand.builder()
                .newPath("/memories/final.txt")
                .oldPath("/memories/draft.txt")
                .build()

        assertThat(betaMemoryTool20250818RenameCommand.newPath()).isEqualTo("/memories/final.txt")
        assertThat(betaMemoryTool20250818RenameCommand.oldPath()).isEqualTo("/memories/draft.txt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818RenameCommand =
            BetaMemoryTool20250818RenameCommand.builder()
                .newPath("/memories/final.txt")
                .oldPath("/memories/draft.txt")
                .build()

        val roundtrippedBetaMemoryTool20250818RenameCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818RenameCommand),
                jacksonTypeRef<BetaMemoryTool20250818RenameCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818RenameCommand)
            .isEqualTo(betaMemoryTool20250818RenameCommand)
    }
}
