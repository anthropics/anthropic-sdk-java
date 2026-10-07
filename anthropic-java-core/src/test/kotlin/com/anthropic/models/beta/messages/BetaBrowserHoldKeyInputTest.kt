package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserHoldKeyInputTest {

    @Test
    fun create() {
        val betaBrowserHoldKeyInput =
            BetaBrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()

        assertThat(betaBrowserHoldKeyInput.duration()).isEqualTo(0.0)
        assertThat(betaBrowserHoldKeyInput.text()).isEqualTo("text")
        assertThat(betaBrowserHoldKeyInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserHoldKeyInput =
            BetaBrowserHoldKeyInput.builder().duration(0.0).text("text").tabId("tab_id").build()

        val roundtrippedBetaBrowserHoldKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserHoldKeyInput),
                jacksonTypeRef<BetaBrowserHoldKeyInput>(),
            )

        assertThat(roundtrippedBetaBrowserHoldKeyInput).isEqualTo(betaBrowserHoldKeyInput)
    }
}
