package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolChangeMcpToolsetReferenceTest {

    @Test
    fun create() {
        val betaResponseToolChangeMcpToolsetReference =
            BetaResponseToolChangeMcpToolsetReference.of("server_name")

        assertThat(betaResponseToolChangeMcpToolsetReference.serverName()).isEqualTo("server_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolChangeMcpToolsetReference =
            BetaResponseToolChangeMcpToolsetReference.of("server_name")

        val roundtrippedBetaResponseToolChangeMcpToolsetReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolChangeMcpToolsetReference),
                jacksonTypeRef<BetaResponseToolChangeMcpToolsetReference>(),
            )

        assertThat(roundtrippedBetaResponseToolChangeMcpToolsetReference)
            .isEqualTo(betaResponseToolChangeMcpToolsetReference)
    }
}
