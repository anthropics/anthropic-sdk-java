package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadConsoleInputTest {

    @Test
    fun create() {
        val betaBrowserReadConsoleInput =
            BetaBrowserReadConsoleInput.builder().tabId("tab_id").build()

        assertThat(betaBrowserReadConsoleInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadConsoleInput =
            BetaBrowserReadConsoleInput.builder().tabId("tab_id").build()

        val roundtrippedBetaBrowserReadConsoleInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadConsoleInput),
                jacksonTypeRef<BetaBrowserReadConsoleInput>(),
            )

        assertThat(roundtrippedBetaBrowserReadConsoleInput).isEqualTo(betaBrowserReadConsoleInput)
    }
}
