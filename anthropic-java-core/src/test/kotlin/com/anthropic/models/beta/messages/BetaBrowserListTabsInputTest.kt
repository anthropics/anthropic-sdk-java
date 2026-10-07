package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserListTabsInputTest {

    @Test
    fun create() {
        val betaBrowserListTabsInput = BetaBrowserListTabsInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserListTabsInput = BetaBrowserListTabsInput.builder().build()

        val roundtrippedBetaBrowserListTabsInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserListTabsInput),
                jacksonTypeRef<BetaBrowserListTabsInput>(),
            )

        assertThat(roundtrippedBetaBrowserListTabsInput).isEqualTo(betaBrowserListTabsInput)
    }
}
