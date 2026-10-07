package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserNewTabToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserNewTabToolUseBlock =
            BetaBrowserNewTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNewTabInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserNewTabToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserNewTabToolUseBlock.input())
            .isEqualTo(BetaBrowserNewTabInput.builder().build())
        assertThat(betaBrowserNewTabToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserNewTabToolUseBlock =
            BetaBrowserNewTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserNewTabInput.builder().build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserNewTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserNewTabToolUseBlock),
                jacksonTypeRef<BetaBrowserNewTabToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserNewTabToolUseBlock)
            .isEqualTo(betaBrowserNewTabToolUseBlock)
    }
}
