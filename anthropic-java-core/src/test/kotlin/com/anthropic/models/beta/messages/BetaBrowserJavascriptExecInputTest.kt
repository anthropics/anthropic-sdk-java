package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserJavascriptExecInputTest {

    @Test
    fun create() {
        val betaBrowserJavascriptExecInput =
            BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()

        assertThat(betaBrowserJavascriptExecInput.text()).isEqualTo("text")
        assertThat(betaBrowserJavascriptExecInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserJavascriptExecInput =
            BetaBrowserJavascriptExecInput.builder().text("text").tabId("tab_id").build()

        val roundtrippedBetaBrowserJavascriptExecInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserJavascriptExecInput),
                jacksonTypeRef<BetaBrowserJavascriptExecInput>(),
            )

        assertThat(roundtrippedBetaBrowserJavascriptExecInput)
            .isEqualTo(betaBrowserJavascriptExecInput)
    }
}
