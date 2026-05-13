// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaDiagnosticsTest {

    @Test
    fun create() {
        val betaDiagnostics = BetaDiagnostics.builder().modelChangedCacheMissReason(0L).build()

        assertThat(betaDiagnostics.cacheMissReason())
            .contains(
                BetaDiagnostics.CacheMissReason.ofModelChanged(
                    BetaCacheMissModelChanged.builder().cacheMissedInputTokens(0L).build()
                )
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaDiagnostics = BetaDiagnostics.builder().modelChangedCacheMissReason(0L).build()

        val roundtrippedBetaDiagnostics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaDiagnostics),
                jacksonTypeRef<BetaDiagnostics>(),
            )

        assertThat(roundtrippedBetaDiagnostics).isEqualTo(betaDiagnostics)
    }
}
