package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFormInputToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserFormInputToolUseBlock =
            BetaBrowserFormInputToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFormInputInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserFormInputToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserFormInputToolUseBlock.input())
            .isEqualTo(
                BetaBrowserFormInputInput.builder()
                    .target(BetaBrowserRefTarget.of("ref"))
                    .value("string")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserFormInputToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputToolUseBlock =
            BetaBrowserFormInputToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFormInputInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .value("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserFormInputToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputToolUseBlock),
                jacksonTypeRef<BetaBrowserFormInputToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputToolUseBlock)
            .isEqualTo(betaBrowserFormInputToolUseBlock)
    }
}
