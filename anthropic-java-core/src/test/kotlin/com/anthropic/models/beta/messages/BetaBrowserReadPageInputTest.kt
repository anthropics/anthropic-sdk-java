package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadPageInputTest {

    @Test
    fun create() {
        val betaBrowserReadPageInput =
            BetaBrowserReadPageInput.builder()
                .depth(1L)
                .filter(BetaBrowserReadPageFilter.ALL)
                .ref("ref")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserReadPageInput.depth()).contains(1L)
        assertThat(betaBrowserReadPageInput.filter()).contains(BetaBrowserReadPageFilter.ALL)
        assertThat(betaBrowserReadPageInput.ref()).contains("ref")
        assertThat(betaBrowserReadPageInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadPageInput =
            BetaBrowserReadPageInput.builder()
                .depth(1L)
                .filter(BetaBrowserReadPageFilter.ALL)
                .ref("ref")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserReadPageInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadPageInput),
                jacksonTypeRef<BetaBrowserReadPageInput>(),
            )

        assertThat(roundtrippedBetaBrowserReadPageInput).isEqualTo(betaBrowserReadPageInput)
    }
}
