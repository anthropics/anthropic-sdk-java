package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserSwitchTabToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserSwitchTabToolUseBlock =
            BetaBrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserSwitchTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserSwitchTabToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserSwitchTabToolUseBlock.input())
            .isEqualTo(BetaBrowserSwitchTabInput.of("tab_id"))
        assertThat(betaBrowserSwitchTabToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserSwitchTabToolUseBlock =
            BetaBrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserSwitchTabInput.of("tab_id"))
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserSwitchTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserSwitchTabToolUseBlock),
                jacksonTypeRef<BetaBrowserSwitchTabToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserSwitchTabToolUseBlock)
            .isEqualTo(betaBrowserSwitchTabToolUseBlock)
    }
}
