package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScreenshotInputTest {

    @Test
    fun create() {
        val betaBrowserScreenshotInput =
            BetaBrowserScreenshotInput.builder().tabId("tab_id").build()

        assertThat(betaBrowserScreenshotInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScreenshotInput =
            BetaBrowserScreenshotInput.builder().tabId("tab_id").build()

        val roundtrippedBetaBrowserScreenshotInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScreenshotInput),
                jacksonTypeRef<BetaBrowserScreenshotInput>(),
            )

        assertThat(roundtrippedBetaBrowserScreenshotInput).isEqualTo(betaBrowserScreenshotInput)
    }
}
