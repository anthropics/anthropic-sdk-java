package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserNewTabInputTest {

    @Test
    fun create() {
        val betaBrowserNewTabInput = BetaBrowserNewTabInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserNewTabInput = BetaBrowserNewTabInput.builder().build()

        val roundtrippedBetaBrowserNewTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserNewTabInput),
                jacksonTypeRef<BetaBrowserNewTabInput>(),
            )

        assertThat(roundtrippedBetaBrowserNewTabInput).isEqualTo(betaBrowserNewTabInput)
    }
}
