// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChangeMcpToolReferenceTest {

    @Test
    fun create() {
        val betaToolChangeMcpToolReference =
            BetaToolChangeMcpToolReference.builder().name("name").serverName("server_name").build()

        assertThat(betaToolChangeMcpToolReference.name()).isEqualTo("name")
        assertThat(betaToolChangeMcpToolReference.serverName()).isEqualTo("server_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChangeMcpToolReference =
            BetaToolChangeMcpToolReference.builder().name("name").serverName("server_name").build()

        val roundtrippedBetaToolChangeMcpToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChangeMcpToolReference),
                jacksonTypeRef<BetaToolChangeMcpToolReference>(),
            )

        assertThat(roundtrippedBetaToolChangeMcpToolReference)
            .isEqualTo(betaToolChangeMcpToolReference)
    }
}
