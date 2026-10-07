package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserCloseTabToolUseBlockTest {

    @Test
    fun create() {
        val browserCloseTabToolUseBlock =
            BrowserCloseTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserCloseTabInput.of("tab_id"))
                .build()

        assertThat(browserCloseTabToolUseBlock.id()).isEqualTo("id")
        assertThat(browserCloseTabToolUseBlock.caller())
            .isEqualTo(ToolUseCaller.ofDirect(DirectCaller.builder().build()))
        assertThat(browserCloseTabToolUseBlock.input()).isEqualTo(BrowserCloseTabInput.of("tab_id"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserCloseTabToolUseBlock =
            BrowserCloseTabToolUseBlock.builder()
                .id("id")
                .caller(DirectCaller.builder().build())
                .input(BrowserCloseTabInput.of("tab_id"))
                .build()

        val roundtrippedBrowserCloseTabToolUseBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserCloseTabToolUseBlock),
                jacksonTypeRef<BrowserCloseTabToolUseBlock>(),
            )

        assertThat(roundtrippedBrowserCloseTabToolUseBlock).isEqualTo(browserCloseTabToolUseBlock)
    }
}
