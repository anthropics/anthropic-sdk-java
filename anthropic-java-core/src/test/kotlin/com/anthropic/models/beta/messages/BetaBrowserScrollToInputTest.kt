package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserScrollToInputTest {

    @Test
    fun create() {
        val betaBrowserScrollToInput =
            BetaBrowserScrollToInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserScrollToInput.target()).isEqualTo(BetaBrowserRefTarget.of("ref"))
        assertThat(betaBrowserScrollToInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserScrollToInput =
            BetaBrowserScrollToInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserScrollToInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserScrollToInput),
                jacksonTypeRef<BetaBrowserScrollToInput>(),
            )

        assertThat(roundtrippedBetaBrowserScrollToInput).isEqualTo(betaBrowserScrollToInput)
    }
}
