package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserNavigateInputTest {

    @Test
    fun create() {
        val betaBrowserNavigateInput =
            BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build()

        assertThat(betaBrowserNavigateInput.url()).isEqualTo("url")
        assertThat(betaBrowserNavigateInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserNavigateInput =
            BetaBrowserNavigateInput.builder().url("url").tabId("tab_id").build()

        val roundtrippedBetaBrowserNavigateInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserNavigateInput),
                jacksonTypeRef<BetaBrowserNavigateInput>(),
            )

        assertThat(roundtrippedBetaBrowserNavigateInput).isEqualTo(betaBrowserNavigateInput)
    }
}
