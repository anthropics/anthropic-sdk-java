package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolRemovalBlockTest {

    @Test
    fun create() {
        val betaResponseToolRemovalBlock =
            BetaResponseToolRemovalBlock.builder().referenceTool("name").build()

        assertThat(betaResponseToolRemovalBlock.tool())
            .isEqualTo(BetaResponseToolRemovalBlock.Tool.ofReference("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolRemovalBlock =
            BetaResponseToolRemovalBlock.builder().referenceTool("name").build()

        val roundtrippedBetaResponseToolRemovalBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolRemovalBlock),
                jacksonTypeRef<BetaResponseToolRemovalBlock>(),
            )

        assertThat(roundtrippedBetaResponseToolRemovalBlock).isEqualTo(betaResponseToolRemovalBlock)
    }
}
