package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserSwitchTabInputTest {

    @Test
    fun create() {
        val betaBrowserSwitchTabInput = BetaBrowserSwitchTabInput.of("tab_id")

        assertThat(betaBrowserSwitchTabInput.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserSwitchTabInput = BetaBrowserSwitchTabInput.of("tab_id")

        val roundtrippedBetaBrowserSwitchTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserSwitchTabInput),
                jacksonTypeRef<BetaBrowserSwitchTabInput>(),
            )

        assertThat(roundtrippedBetaBrowserSwitchTabInput).isEqualTo(betaBrowserSwitchTabInput)
    }
}
