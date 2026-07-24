// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolChangeMcpToolsetReferenceTest {

    @Test
    fun create() {
        val betaToolChangeMcpToolsetReference =
            BetaToolChangeMcpToolsetReference.builder().serverName("server_name").build()

        assertThat(betaToolChangeMcpToolsetReference.serverName()).isEqualTo("server_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChangeMcpToolsetReference =
            BetaToolChangeMcpToolsetReference.builder().serverName("server_name").build()

        val roundtrippedBetaToolChangeMcpToolsetReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChangeMcpToolsetReference),
                jacksonTypeRef<BetaToolChangeMcpToolsetReference>(),
            )

        assertThat(roundtrippedBetaToolChangeMcpToolsetReference)
            .isEqualTo(betaToolChangeMcpToolsetReference)
    }
}
