// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolCallerTest {

    @Test
    fun create() {
        val betaServerToolCaller = BetaServerToolCaller.of("srvtoolu_SQfNkl1n_JR_")

        assertThat(betaServerToolCaller.toolId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolCaller = BetaServerToolCaller.of("srvtoolu_SQfNkl1n_JR_")

        val roundtrippedBetaServerToolCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolCaller),
                jacksonTypeRef<BetaServerToolCaller>(),
            )

        assertThat(roundtrippedBetaServerToolCaller).isEqualTo(betaServerToolCaller)
    }
}
