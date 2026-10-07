package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserReadNetworkInputTest {

    @Test
    fun create() {
        val betaBrowserReadNetworkInput =
            BetaBrowserReadNetworkInput.builder().tabId("tab_id").build()

        assertThat(betaBrowserReadNetworkInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserReadNetworkInput =
            BetaBrowserReadNetworkInput.builder().tabId("tab_id").build()

        val roundtrippedBetaBrowserReadNetworkInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserReadNetworkInput),
                jacksonTypeRef<BetaBrowserReadNetworkInput>(),
            )

        assertThat(roundtrippedBetaBrowserReadNetworkInput).isEqualTo(betaBrowserReadNetworkInput)
    }
}
