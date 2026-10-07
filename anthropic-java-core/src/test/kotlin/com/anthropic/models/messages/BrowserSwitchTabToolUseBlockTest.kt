package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserSwitchTabToolUseBlockTest {

    @Test
    fun create() {
        val browserSwitchTabToolUseBlock =
            BrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserSwitchTabInput.of("tab_id"))
                .build()

        assertThat(browserSwitchTabToolUseBlock.id()).isEqualTo("id")
        assertThat(browserSwitchTabToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserSwitchTabToolUseBlock.input())
            .isEqualTo(BrowserSwitchTabInput.of("tab_id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserSwitchTabToolUseBlock =
            BrowserSwitchTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserSwitchTabInput.of("tab_id"))
                .build()

        val roundtrippedBrowserSwitchTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserSwitchTabToolUseBlock),
                jacksonTypeRef<BrowserSwitchTabToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserSwitchTabToolUseBlock).isEqualTo(browserSwitchTabToolUseBlock)
    }
}
