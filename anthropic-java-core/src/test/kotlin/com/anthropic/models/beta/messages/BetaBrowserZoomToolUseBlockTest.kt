package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserZoomToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserZoomToolUseBlock =
            BetaBrowserZoomToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserZoomToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserZoomToolUseBlock.input())
            .isEqualTo(
                BetaBrowserZoomInput.builder()
                    .region(listOf(0L, 0L, 0L, 0L))
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserZoomToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserZoomToolUseBlock =
            BetaBrowserZoomToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserZoomInput.builder()
                        .region(listOf(0L, 0L, 0L, 0L))
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserZoomToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserZoomToolUseBlock),
                jacksonTypeRef<BetaBrowserZoomToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserZoomToolUseBlock).isEqualTo(betaBrowserZoomToolUseBlock)
    }
}
