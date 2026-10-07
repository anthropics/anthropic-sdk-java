package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserGetPageTextInputTest {

    @Test
    fun create() {
        val betaBrowserGetPageTextInput =
            BetaBrowserGetPageTextInput.builder().tabId("tab_id").build()

        assertThat(betaBrowserGetPageTextInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserGetPageTextInput =
            BetaBrowserGetPageTextInput.builder().tabId("tab_id").build()

        val roundtrippedBetaBrowserGetPageTextInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserGetPageTextInput),
                jacksonTypeRef<BetaBrowserGetPageTextInput>(),
            )

        assertThat(roundtrippedBetaBrowserGetPageTextInput).isEqualTo(betaBrowserGetPageTextInput)
    }
}
