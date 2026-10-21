package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamSessionsInputTest {

    @Test
    fun create() {
        val betaDreamSessionsInput = BetaDreamSessionsInput.builder().addSessionId("string").build()

        assertThat(betaDreamSessionsInput.sessionIds()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamSessionsInput = BetaDreamSessionsInput.builder().addSessionId("string").build()

        val roundtrippedBetaDreamSessionsInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamSessionsInput),
                jacksonTypeRef<BetaDreamSessionsInput>(),
            )

        assertThat(roundtrippedBetaDreamSessionsInput).isEqualTo(betaDreamSessionsInput)
    }
}
