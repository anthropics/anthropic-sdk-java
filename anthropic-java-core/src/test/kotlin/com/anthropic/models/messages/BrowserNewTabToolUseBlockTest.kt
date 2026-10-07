package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserNewTabToolUseBlockTest {

    @Test
    fun create() {
        val browserNewTabToolUseBlock =
            BrowserNewTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNewTabInput.builder().build())
                .build()

        assertThat(browserNewTabToolUseBlock.id()).isEqualTo("id")
        assertThat(browserNewTabToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserNewTabToolUseBlock.input())
            .isEqualTo(BrowserNewTabInput.builder().build())
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserNewTabToolUseBlock =
            BrowserNewTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserNewTabInput.builder().build())
                .build()

        val roundtrippedBrowserNewTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserNewTabToolUseBlock),
                jacksonTypeRef<BrowserNewTabToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserNewTabToolUseBlock).isEqualTo(browserNewTabToolUseBlock)
    }
}
