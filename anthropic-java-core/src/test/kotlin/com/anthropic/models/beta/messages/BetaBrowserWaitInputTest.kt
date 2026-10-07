package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserWaitInputTest {

    @Test
    fun create() {
        val betaBrowserWaitInput =
            BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build()

        assertThat(betaBrowserWaitInput.duration()).isEqualTo(0.0)
        assertThat(betaBrowserWaitInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserWaitInput =
            BetaBrowserWaitInput.builder().duration(0.0).tabId("tab_id").build()

        val roundtrippedBetaBrowserWaitInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserWaitInput),
                jacksonTypeRef<BetaBrowserWaitInput>(),
            )

        assertThat(roundtrippedBetaBrowserWaitInput).isEqualTo(betaBrowserWaitInput)
    }
}
