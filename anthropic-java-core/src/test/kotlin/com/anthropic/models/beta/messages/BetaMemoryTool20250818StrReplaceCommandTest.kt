// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818StrReplaceCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818StrReplaceCommand =
            BetaMemoryTool20250818StrReplaceCommand.builder()
                .newStr("Favorite color: green")
                .oldStr("Favorite color: blue")
                .path("/memories/preferences.txt")
                .build()

        assertThat(betaMemoryTool20250818StrReplaceCommand.newStr())
            .isEqualTo("Favorite color: green")
        assertThat(betaMemoryTool20250818StrReplaceCommand.oldStr())
            .isEqualTo("Favorite color: blue")
        assertThat(betaMemoryTool20250818StrReplaceCommand.path())
            .isEqualTo("/memories/preferences.txt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818StrReplaceCommand =
            BetaMemoryTool20250818StrReplaceCommand.builder()
                .newStr("Favorite color: green")
                .oldStr("Favorite color: blue")
                .path("/memories/preferences.txt")
                .build()

        val roundtrippedBetaMemoryTool20250818StrReplaceCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818StrReplaceCommand),
                jacksonTypeRef<BetaMemoryTool20250818StrReplaceCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818StrReplaceCommand)
            .isEqualTo(betaMemoryTool20250818StrReplaceCommand)
    }
}
