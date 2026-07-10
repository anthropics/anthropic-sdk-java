// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDreamSessionsInputTest {

    @Test
    fun create() {
        val betaDreamSessionsInput =
            BetaDreamSessionsInput.builder()
                .addSessionId("string")
                .type(BetaDreamSessionsInput.Type.SESSIONS)
                .build()

        assertThat(betaDreamSessionsInput.sessionIds()).containsExactly("string")
        assertThat(betaDreamSessionsInput.type()).isEqualTo(BetaDreamSessionsInput.Type.SESSIONS)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDreamSessionsInput =
            BetaDreamSessionsInput.builder()
                .addSessionId("string")
                .type(BetaDreamSessionsInput.Type.SESSIONS)
                .build()

        val roundtrippedBetaDreamSessionsInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDreamSessionsInput),
                jacksonTypeRef<BetaDreamSessionsInput>(),
            )

        assertThat(roundtrippedBetaDreamSessionsInput).isEqualTo(betaDreamSessionsInput)
    }
}
