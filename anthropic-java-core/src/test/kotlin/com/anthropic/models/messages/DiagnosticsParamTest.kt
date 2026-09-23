package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DiagnosticsParamTest {

    @Test
    fun create() {
        val diagnosticsParam =
            DiagnosticsParam.builder().previousMessageId("previous_message_id").build()

        assertThat(diagnosticsParam.previousMessageId()).contains("previous_message_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val diagnosticsParam =
            DiagnosticsParam.builder().previousMessageId("previous_message_id").build()

        val roundtrippedDiagnosticsParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(diagnosticsParam),
                jacksonTypeRef<DiagnosticsParam>(),
            )

        assertThat(roundtrippedDiagnosticsParam).isEqualTo(diagnosticsParam)
    }
}
