// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaMemoryTool20250818ViewCommandTest {

    @Test
    fun create() {
        val betaMemoryTool20250818ViewCommand =
            BetaMemoryTool20250818ViewCommand.builder()
                .path("/memories")
                .addViewRange(1L)
                .addViewRange(10L)
                .build()

        assertThat(betaMemoryTool20250818ViewCommand.path()).isEqualTo("/memories")
        assertThat(betaMemoryTool20250818ViewCommand.viewRange().getOrNull())
            .containsExactly(1L, 10L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaMemoryTool20250818ViewCommand =
            BetaMemoryTool20250818ViewCommand.builder()
                .path("/memories")
                .addViewRange(1L)
                .addViewRange(10L)
                .build()

        val roundtrippedBetaMemoryTool20250818ViewCommand =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMemoryTool20250818ViewCommand),
                jacksonTypeRef<BetaMemoryTool20250818ViewCommand>(),
            )

        assertThat(roundtrippedBetaMemoryTool20250818ViewCommand)
            .isEqualTo(betaMemoryTool20250818ViewCommand)
    }
}
