// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServerToolCaller20260120Test {

    @Test
    fun create() {
        val betaServerToolCaller20260120 =
            BetaServerToolCaller20260120.builder().toolId("srvtoolu_SQfNkl1n_JR_").build()

        assertThat(betaServerToolCaller20260120.toolId()).isEqualTo("srvtoolu_SQfNkl1n_JR_")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServerToolCaller20260120 =
            BetaServerToolCaller20260120.builder().toolId("srvtoolu_SQfNkl1n_JR_").build()

        val roundtrippedBetaServerToolCaller20260120 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServerToolCaller20260120),
                jacksonTypeRef<BetaServerToolCaller20260120>(),
            )

        assertThat(roundtrippedBetaServerToolCaller20260120).isEqualTo(betaServerToolCaller20260120)
    }
}
