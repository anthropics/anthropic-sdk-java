// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDiagnosticsParamTest {

    @Test
    fun create() {
        val betaDiagnosticsParam =
            BetaDiagnosticsParam.builder().previousMessageId("previous_message_id").build()

        assertThat(betaDiagnosticsParam.previousMessageId()).contains("previous_message_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDiagnosticsParam =
            BetaDiagnosticsParam.builder().previousMessageId("previous_message_id").build()

        val roundtrippedBetaDiagnosticsParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDiagnosticsParam),
                jacksonTypeRef<BetaDiagnosticsParam>(),
            )

        assertThat(roundtrippedBetaDiagnosticsParam).isEqualTo(betaDiagnosticsParam)
    }
}
