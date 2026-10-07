package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserCloseTabToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserCloseTabToolUseBlock =
            BetaBrowserCloseTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserCloseTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserCloseTabToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserCloseTabToolUseBlock.input())
            .isEqualTo(BetaBrowserCloseTabInput.of("tab_id"))
        assertThat(betaBrowserCloseTabToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserCloseTabToolUseBlock =
            BetaBrowserCloseTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserCloseTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserCloseTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserCloseTabToolUseBlock),
                jacksonTypeRef<BetaBrowserCloseTabToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserCloseTabToolUseBlock)
            .isEqualTo(betaBrowserCloseTabToolUseBlock)
    }
}
