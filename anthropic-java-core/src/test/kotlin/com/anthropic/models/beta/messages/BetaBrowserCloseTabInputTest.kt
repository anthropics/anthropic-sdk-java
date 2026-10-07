package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserCloseTabInputTest {

    @Test
    fun create() {
        val betaBrowserCloseTabInput = BetaBrowserCloseTabInput.of("tab_id")

        assertThat(betaBrowserCloseTabInput.tabId()).isEqualTo("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserCloseTabInput = BetaBrowserCloseTabInput.of("tab_id")

        val roundtrippedBetaBrowserCloseTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserCloseTabInput),
                jacksonTypeRef<BetaBrowserCloseTabInput>(),
            )

        assertThat(roundtrippedBetaBrowserCloseTabInput).isEqualTo(betaBrowserCloseTabInput)
    }
}
