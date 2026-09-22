package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaResponseToolChangeMcpToolReferenceTest {

    @Test
    fun create() {
        val betaResponseToolChangeMcpToolReference =
            BetaResponseToolChangeMcpToolReference.builder()
                .name("name")
                .serverName("server_name")
                .build()

        assertThat(betaResponseToolChangeMcpToolReference.name()).isEqualTo("name")
        assertThat(betaResponseToolChangeMcpToolReference.serverName()).isEqualTo("server_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaResponseToolChangeMcpToolReference =
            BetaResponseToolChangeMcpToolReference.builder()
                .name("name")
                .serverName("server_name")
                .build()

        val roundtrippedBetaResponseToolChangeMcpToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaResponseToolChangeMcpToolReference),
                jacksonTypeRef<BetaResponseToolChangeMcpToolReference>(),
            )

        assertThat(roundtrippedBetaResponseToolChangeMcpToolReference)
            .isEqualTo(betaResponseToolChangeMcpToolReference)
    }
}
