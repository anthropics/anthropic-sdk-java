package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFormInputInputTest {

    @Test
    fun create() {
        val browserFormInputInput =
            BrowserFormInputInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .value("string")
                .tabId("tab_id")
                .build()

        assertThat(browserFormInputInput.target()).isEqualTo(BrowserRefTarget.of("ref"))
        assertThat(browserFormInputInput.value())
            .isEqualTo(BrowserFormInputValue.ofString("string"))
        assertThat(browserFormInputInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFormInputInput =
            BrowserFormInputInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .value("string")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserFormInputInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFormInputInput),
                jacksonTypeRef<BrowserFormInputInput>(),
            )

        assertThat(roundtrippedBrowserFormInputInput).isEqualTo(browserFormInputInput)
    }
}
