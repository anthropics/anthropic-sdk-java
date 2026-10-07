package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserKeyInputTest {

    @Test
    fun create() {
        val betaBrowserKeyInput =
            BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()

        assertThat(betaBrowserKeyInput.text()).isEqualTo("text")
        assertThat(betaBrowserKeyInput.repeat()).contains(1L)
        assertThat(betaBrowserKeyInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserKeyInput =
            BetaBrowserKeyInput.builder().text("text").repeat(1L).tabId("tab_id").build()

        val roundtrippedBetaBrowserKeyInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserKeyInput),
                jacksonTypeRef<BetaBrowserKeyInput>(),
            )

        assertThat(roundtrippedBetaBrowserKeyInput).isEqualTo(betaBrowserKeyInput)
    }
}
