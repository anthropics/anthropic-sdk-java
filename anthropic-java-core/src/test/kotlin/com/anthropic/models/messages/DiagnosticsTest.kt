package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DiagnosticsTest {

    @Test
    fun create() {
        val diagnostics = Diagnostics.builder().modelChangedCacheMissReason(0L).build()

        assertThat(diagnostics.cacheMissReason()).contains(CacheMissReason.ofModelChanged(0L))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val diagnostics = Diagnostics.builder().modelChangedCacheMissReason(0L).build()

        val roundtrippedDiagnostics =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(diagnostics),
                jacksonTypeRef<Diagnostics>(),
            )

        assertThat(roundtrippedDiagnostics).isEqualTo(diagnostics)
    }
}
