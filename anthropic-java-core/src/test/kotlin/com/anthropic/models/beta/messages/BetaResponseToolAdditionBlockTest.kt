package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolAdditionBlockTest {

    @Test
    fun create() {
        val betaResponseToolAdditionBlock =
            BetaResponseToolAdditionBlock.builder().referenceTool("name").build()

        assertThat(betaResponseToolAdditionBlock.tool())
            .isEqualTo(BetaResponseToolAdditionBlock.Tool.ofReference("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolAdditionBlock =
            BetaResponseToolAdditionBlock.builder().referenceTool("name").build()

        val roundtrippedBetaResponseToolAdditionBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolAdditionBlock),
                jacksonTypeRef<BetaResponseToolAdditionBlock>(),
            )

        assertThat(roundtrippedBetaResponseToolAdditionBlock)
            .isEqualTo(betaResponseToolAdditionBlock)
    }
}
