package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserZoomInputTest {

    @Test
    fun create() {
        val betaBrowserZoomInput =
            BetaBrowserZoomInput.builder().region(listOf(0L, 0L, 0L, 0L)).tabId("tab_id").build()

        assertThat(betaBrowserZoomInput.region()).containsExactly(0L, 0L, 0L, 0L)
        assertThat(betaBrowserZoomInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserZoomInput =
            BetaBrowserZoomInput.builder().region(listOf(0L, 0L, 0L, 0L)).tabId("tab_id").build()

        val roundtrippedBetaBrowserZoomInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserZoomInput),
                jacksonTypeRef<BetaBrowserZoomInput>(),
            )

        assertThat(roundtrippedBetaBrowserZoomInput).isEqualTo(betaBrowserZoomInput)
    }
}
