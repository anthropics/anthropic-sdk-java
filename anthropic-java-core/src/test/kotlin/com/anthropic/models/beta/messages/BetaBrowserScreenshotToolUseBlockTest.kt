package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScreenshotToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserScreenshotToolUseBlock =
            BetaBrowserScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserScreenshotInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserScreenshotToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserScreenshotToolUseBlock.input())
            .isEqualTo(BetaBrowserScreenshotInput.builder().tabId("tab_id").build())
        assertThat(betaBrowserScreenshotToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScreenshotToolUseBlock =
            BetaBrowserScreenshotToolUseBlock.builder()
                .id("id")
                .input(BetaBrowserScreenshotInput.builder().tabId("tab_id").build())
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserScreenshotToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScreenshotToolUseBlock),
                jacksonTypeRef<BetaBrowserScreenshotToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserScreenshotToolUseBlock)
            .isEqualTo(betaBrowserScreenshotToolUseBlock)
    }
}
