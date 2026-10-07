package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserTripleClickInputTest {

    @Test
    fun create() {
        val browserTripleClickInput =
            BrowserTripleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(browserTripleClickInput.target())
            .isEqualTo(
                BrowserClickTarget.ofCoordinate(
                    BrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(browserTripleClickInput.modifiers()).contains("modifiers")
        assertThat(browserTripleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserTripleClickInput =
            BrowserTripleClickInput.builder()
                .target(BrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserTripleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserTripleClickInput),
                jacksonTypeRef<BrowserTripleClickInput>(),
            )

        assertThat(roundtrippedBrowserTripleClickInput).isEqualTo(browserTripleClickInput)
    }
}
