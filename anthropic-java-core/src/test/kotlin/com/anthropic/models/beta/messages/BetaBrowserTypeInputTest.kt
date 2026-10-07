package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTypeInputTest {

    @Test
    fun create() {
        val betaBrowserTypeInput =
            BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build()

        assertThat(betaBrowserTypeInput.text()).isEqualTo("text")
        assertThat(betaBrowserTypeInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTypeInput =
            BetaBrowserTypeInput.builder().text("text").tabId("tab_id").build()

        val roundtrippedBetaBrowserTypeInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTypeInput),
                jacksonTypeRef<BetaBrowserTypeInput>(),
            )

        assertThat(roundtrippedBetaBrowserTypeInput).isEqualTo(betaBrowserTypeInput)
    }
}
