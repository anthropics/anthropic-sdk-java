package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFindInputTest {

    @Test
    fun create() {
        val betaBrowserFindInput =
            BetaBrowserFindInput.builder().query("query").tabId("tab_id").build()

        assertThat(betaBrowserFindInput.query()).isEqualTo("query")
        assertThat(betaBrowserFindInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFindInput =
            BetaBrowserFindInput.builder().query("query").tabId("tab_id").build()

        val roundtrippedBetaBrowserFindInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFindInput),
                jacksonTypeRef<BetaBrowserFindInput>(),
            )

        assertThat(roundtrippedBetaBrowserFindInput).isEqualTo(betaBrowserFindInput)
    }
}
