package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFileUploadToolUseBlockTest {

    @Test
    fun create() {
        val betaBrowserFileUploadToolUseBlock =
            BetaBrowserFileUploadToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFileUploadInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        assertThat(betaBrowserFileUploadToolUseBlock.id()).isEqualTo("id")
        assertThat(betaBrowserFileUploadToolUseBlock.input())
            .isEqualTo(
                BetaBrowserFileUploadInput.builder()
                    .target(BetaBrowserRefTarget.of("ref"))
                    .addDocumentId("string")
                    .addPath("string")
                    .tabId("tab_id")
                    .build()
            )
        assertThat(betaBrowserFileUploadToolUseBlock.caller())
            .contains(BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build()))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFileUploadToolUseBlock =
            BetaBrowserFileUploadToolUseBlock.builder()
                .id("id")
                .input(
                    BetaBrowserFileUploadInput.builder()
                        .target(BetaBrowserRefTarget.of("ref"))
                        .addDocumentId("string")
                        .addPath("string")
                        .tabId("tab_id")
                        .build()
                )
                .caller(BetaDirectCaller.builder().build())
                .build()

        val roundtrippedBetaBrowserFileUploadToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFileUploadToolUseBlock),
                jacksonTypeRef<BetaBrowserFileUploadToolUseBlock>(),
            )

        assertThat(roundtrippedBetaBrowserFileUploadToolUseBlock)
            .isEqualTo(betaBrowserFileUploadToolUseBlock)
    }
}
